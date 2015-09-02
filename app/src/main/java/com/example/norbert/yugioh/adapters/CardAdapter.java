package com.example.norbert.yugioh.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.norbert.yugioh.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class CardAdapter extends ArrayAdapter<String> implements Filterable {
    private List<String> mOriginal = null;
    private List<String> mFiltered = null;
    private ItemFilter mFilter = new ItemFilter();

    public static class ViewHolder {
        @Bind(R.id.cardName)
        TextView cardName;
    }

    public CardAdapter(Context context, List<String> cards) {
        super(context, 0, cards);

        this.mOriginal = new ArrayList<>();
        mOriginal.addAll(cards);
        this.mFiltered = new ArrayList<>();
        mFiltered.addAll(cards);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);

            ButterKnife.bind(viewHolder, convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.cardName.setText(mFiltered.get(position));
        return convertView;
    }

    public Filter getFilter() {
        return mFilter;
    }

    private class ItemFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<String> list = mOriginal;

            int count = list.size();
            final ArrayList<String> nlist = new ArrayList<String>(count);

            String filterableString;

            for (int i = 0; i < count; i++) {
                filterableString = list.get(i);
                if (filterableString.toLowerCase().contains(filterString)) {
                    nlist.add(filterableString);
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mFiltered = (ArrayList<String>) results.values;
            notifyDataSetChanged();
            clear();

            for (int i = 0, l = mFiltered.size(); i < l; i++) {
                add(mFiltered.get(i));
            }

            notifyDataSetInvalidated();
        }
    }
}
