package com.zity.rqxf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zity.rqxf.R;
import com.zity.rqxf.bean.Department;
import com.zity.rqxf.bean.Statement;

import java.util.List;

/**
 * Created by luochao on 2017/6/12.
 * 部门职责
 */

public class DepartmentAdapter extends BaseAdapter {
    private Context context;
    private List<Department> list;

    public DepartmentAdapter(Context context, List<Department> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_department, null);
            viewHolder.tv_department_title = (TextView) convertView.findViewById(R.id.tv_department_title);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_department_title.setText(list.get(position).getName());
        return convertView;
    }

    class ViewHolder {
        TextView tv_department_title;
    }
}
