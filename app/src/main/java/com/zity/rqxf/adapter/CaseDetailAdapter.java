package com.zity.rqxf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.zity.rqxf.R;
import com.zity.rqxf.bean.CaseDetail;
import com.zity.rqxf.bean.CaseList;

import java.util.List;

/**
 * Created by luochao on 2017/6/20.
 * 案件详情
 */

public class CaseDetailAdapter extends BaseAdapter {
    private Context context;
    private List<CaseDetail.FunctionaryBean> list;

    public CaseDetailAdapter(Context context, List<CaseDetail.FunctionaryBean> list) {
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
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_casedetail, null);
            viewHolder.sjbald = (TextView) convertView.findViewById(R.id.tv_sjbald);
            viewHolder.bald = (TextView) convertView.findViewById(R.id.tv_bald);
            viewHolder.bazrr = (TextView) convertView.findViewById(R.id.tv_bazrr);
            viewHolder.bhfzr = (TextView) convertView.findViewById(R.id.tv_bhfzr);
            viewHolder.ll_fzry = (LinearLayout) convertView.findViewById(R.id.ll_fzry);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.sjbald.setText(list.get(position).getMunicipalMeaders());
        viewHolder.bald.setText(list.get(position).getBaoleader());
        viewHolder.bazrr.setText(list.get(position).getBananren());
        viewHolder.bhfzr.setText(list.get(position).getBaoprincipals());
        if (list.size() > 1) {
            viewHolder.ll_fzry.setVisibility(View.VISIBLE);
        }
        if (position==0){
            viewHolder.ll_fzry.setVisibility(View.GONE);
        }
        return convertView;
    }

    class ViewHolder {
        TextView sjbald;
        TextView bald;
        TextView bazrr;
        TextView bhfzr;
        LinearLayout ll_fzry;
    }

}
