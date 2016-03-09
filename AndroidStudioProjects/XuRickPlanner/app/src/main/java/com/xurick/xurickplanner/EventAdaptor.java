package com.xurick.xurickplanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.text.format.DateFormat; //different from the standard java one


import java.util.LinkedList;


/**
 * Created by chrsrck on 3/9/16.
 */
public class EventAdaptor extends BaseAdapter{

    private Context mContext;
    private LinkedList<Event> mLinkedList;

    public EventAdaptor(Context context, LinkedList<Event> list){
        mContext = context;
        mLinkedList = list;
    }


    @Override
    public int getCount() {
        return mLinkedList.size();
    }

    @Override
    public Object getItem(int position) {
        return mLinkedList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }// tag items for easy reference instead

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.event_list_item, null);
            holder = new ViewHolder();
            holder.eventName = (TextView) convertView.findViewById(R.id.eventName);
            holder.dueDateText = (TextView) convertView.findViewById(R.id.dueDateText);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Event event = mLinkedList.get(position);
        holder.eventName.setText(event.getTitle());

        //Maybe experiment with the Calendar object here?
        //use getBestDateTimePattern for localization options


        if (position == 0){
            String dueDate = android.text.format.DateFormat.format("'Today' kk:mm",event.getDate()).toString();
            holder.dueDateText.setText("Today " + event.getDate().toString());
        }
        else{
            String dueDate = android.text.format.DateFormat.format("MMM dd",event.getDate()).toString();
            holder.dueDateText.setText(dueDate);
        }

        return convertView;
    }

    private static class ViewHolder{
        TextView eventName;
        TextView dueDateText;
    }
}
