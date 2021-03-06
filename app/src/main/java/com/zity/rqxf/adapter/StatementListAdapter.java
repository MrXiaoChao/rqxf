package com.zity.rqxf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zity.rqxf.R;
import com.zity.rqxf.bean.StatementList;

import java.util.List;

/**
 * Created by luochao on 2017/6/12.
 * 案件统计列表适配器
 */

public class StatementListAdapter extends BaseAdapter {
    private Context context;
    private List<StatementList> list;

    public StatementListAdapter(Context context, List<StatementList> list) {
        this.context = context;
        this.list = list;
    }
    public void clear(List<StatementList> list){
        list.clear();
        notifyDataSetChanged();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_statement_list, null);
            viewHolder.item_name = (TextView) convertView.findViewById(R.id.item_name);
            viewHolder.item_time = (TextView) convertView.findViewById(R.id.item_time);
            viewHolder.item_content = (TextView) convertView.findViewById(R.id.item_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.item_name.setText(list.get(position).getLetterPerson());
        viewHolder.item_time.setText(list.get(position).getTime());
        viewHolder.item_content.setText(list.get(position).getContent());
        return convertView;
    }

    class ViewHolder {
        TextView item_name;
        TextView item_time;
        TextView item_content;
    }
}
