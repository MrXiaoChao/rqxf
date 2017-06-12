package com.zity.rqxf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zity.rqxf.R;
import com.zity.rqxf.bean.Statement;

import java.util.List;

/**
 * Created by luochao on 2017/6/12.
 * 案件统计适配器
 */

public class StatementAdapter extends BaseAdapter {
    private Context context;
    private List<Statement> list;

    public StatementAdapter(Context context, List<Statement> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size() == 0 ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_statement, null);
            viewHolder.tv_statment_title = (TextView) convertView.findViewById(R.id.tv_statment_title);
            viewHolder.tv_statment_count = (TextView) convertView.findViewById(R.id.tv_statment_count);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_statment_title.setText(list.get(position).getName());
        viewHolder.tv_statment_count.setText(String.valueOf(list.get(position).getCount()));
        return convertView;
    }

    class ViewHolder {
        TextView tv_statment_title;
        TextView tv_statment_count;
    }
}
