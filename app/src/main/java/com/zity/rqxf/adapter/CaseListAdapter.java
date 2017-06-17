package com.zity.rqxf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zity.rqxf.R;
import com.zity.rqxf.bean.CaseList;
import com.zity.rqxf.bean.StatementList;

import java.util.List;

/**
 * Created by luochao on 2017/6/12.
 * 代办案件适配器
 */

public class CaseListAdapter extends BaseAdapter {
    private Context context;
    private CaseList caseList;

    public CaseListAdapter(Context context, CaseList caseList) {
        this.context = context;
        this.caseList = caseList;
    }

    @Override
    public int getCount() {
        return caseList.getList().size() == 0 ? 0 : caseList.getList().size();
    }

    @Override
    public Object getItem(int position) {
        return caseList.getList().get(position);
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
        viewHolder.item_name.setText(caseList.getList().get(position).getPetitioners());
        viewHolder.item_time.setText(caseList.getList().get(position).getTime());
        viewHolder.item_content.setText(caseList.getList().get(position).getContent());
        return convertView;
    }

    class ViewHolder {
        TextView item_name;
        TextView item_time;
        TextView item_content;
    }
}
