package com.example.yanghan.gravity.ui.team;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yanghan.gravity.R;

import java.util.ArrayList;

/**
 * Created by 程果 on 2016/3/6.
 */
public class RefreshListAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context context;
    private ArrayList<ListViewItem> items;

    public RefreshListAdapter(Context context, ArrayList<ListViewItem> arrayList) {
        this.context = context;
        this.items = arrayList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item_layout, parent, false);

            viewHolder.userImg = (ImageView) convertView.findViewById(R.id.user_header_img);
            viewHolder.userName = (TextView) convertView.findViewById(R.id.user_name);
            viewHolder.userComment = (TextView) convertView.findViewById(R.id.user_coomment);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.userImg.setImageResource(items.get(position).getUserImg());
        viewHolder.userName.setText(items.get(position).getUserName());
        viewHolder.userComment.setText(items.get(position).getUserComment());
        return convertView;
    }

    public void onDateChange(ArrayList<ListViewItem> items) {
        this.items = items;
        this.notifyDataSetChanged();
    }

    public class ViewHolder {
        //用户头像
        ImageView userImg;
        //用户名字
        TextView userName;
        //用户的评论
        TextView userComment;
    }

}
