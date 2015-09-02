package com.example.norbert.yugioh.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.norbert.yugioh.Client.models.Card;
import com.example.norbert.yugioh.Client.models.CardDataResponse;
import com.example.norbert.yugioh.Client.models.YGOApiImpli;
import com.example.norbert.yugioh.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CardDetailActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.cardImage1)
    ImageView mCardImage;
    @Bind(R.id.ATTView)
    TextView mAttView;
    @Bind(R.id.TypeView)
    TextView mTypeView;
    @Bind(R.id.LevelView)
    TextView mLevelView;
    @Bind(R.id.AtkDefView)
    TextView mAttDefView;
    @Bind(R.id.PropertView)
    TextView mPropertyView;
    @Bind(R.id.CardText)
    TextView mCardText;
    @Bind(R.id.progress)
    ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);

        bindViews();

        mProgress.setVisibility(View.VISIBLE);

        final String cardName;
        String a = getIntent().getStringExtra("CARD_NAME");
        cardName = a;

        setUpToolbar();
        setImage(cardFormat(a));
        setTitle(cardName);
        loadCardData(cardName);
    }

    public void bindViews() {
        ButterKnife.bind(this);
    }

    public void setUpToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setImage(String cc) {
        Glide.with(mCardImage.getContext()).load("http://www.yugiohprices.com/api/card_image/" + cc).fitCenter().into(mCardImage);
    }

    public void loadCardData(String cardName) {
        YGOApiImpli.getInstance().getCardData(cardName, new Callback<CardDataResponse>() {
            @Override
            public void success(CardDataResponse cardDataResponse, Response response) {
                mProgress.setVisibility(View.INVISIBLE);
                Card cs = cardDataResponse.getData();
                mAttView.setText("Attribute: " + cs.getFamily());
                mTypeView.setText("Type: " + cs.getCard_type() + cs.getType());
                mLevelView.setText("Level/Rank: " + cs.getLevel());
                mAttDefView.setText("Attack/Defense: " + cs.getAtk() + cs.getDef());
                mPropertyView.setText("Property: " + cs.getProperty());
                mCardText.setText(cs.getText());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("ERROR", error.getMessage());
            }
        });
    }

    public String cardFormat(String cardName) {
        return cardName.replaceAll(" ", "+");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_card_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
