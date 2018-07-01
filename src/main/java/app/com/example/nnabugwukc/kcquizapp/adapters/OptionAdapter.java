package app.com.example.nnabugwukc.kcquizapp.adapters;

/**
 * Created by Kingsley.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.List;

import app.com.example.nnabugwukc.kcquizapp.R;
import app.com.example.nnabugwukc.kcquizapp.data.DataList;

public class OptionAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<DataList> mListItem;

    public OptionAdapter(Context context, List<DataList> customizedListView) {
        layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mListItem = customizedListView;
    }

    @Override
    public int getCount() {
        return mListItem.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder listViewHolder;
        if(convertView == null){
            listViewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.home_list_item, parent, false);

            listViewHolder.mOptionItem = (TextView)convertView.findViewById(R.id.optionItem);
            listViewHolder.mLetter = (TextView)convertView.findViewById(R.id.letter);
            convertView.setTag(listViewHolder);
        }else{
            listViewHolder = (ViewHolder)convertView.getTag();
        }
        listViewHolder.mOptionItem.setText(mListItem.get(position).getmOptionItem());
        listViewHolder.mLetter.setText(mListItem.get(position).getLetter());

        return convertView;
    }

    static class ViewHolder{

        TextView mOptionItem;
        TextView mLetter;

    }
}