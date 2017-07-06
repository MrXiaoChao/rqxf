package com.zity.rqxf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zity.rqxf.R;
import com.zity.rqxf.bean.Fkls;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by luochao on 2017/6/20.
 * 反馈历史
 */

public class FklsAdapter extends BaseAdapter {
    private Context context;
    private List<Fkls.ListBean> list;

    public FklsAdapter(Context context, List<Fkls.ListBean> list) {
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
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_fkls, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvName.setText(list.get(position).getName());
        viewHolder.tvTime.setText(list.get(position).getFeedbackdate());
        viewHolder.tvGzfs.setText(list.get(position).getWorking());
        viewHolder.tvCyr.setText(list.get(position).getParticipant());
        viewHolder.tvGznr.setText(list.get(position).getContent());
        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_gzfs)
        TextView tvGzfs;
        @BindView(R.id.tv_cyr)
        TextView tvCyr;
        @BindView(R.id.tv_gznr)
        TextView tvGznr;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
