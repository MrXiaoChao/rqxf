package com.zity.rqxf.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.blankj.utilcode.utils.ToastUtils;
import com.zity.rqxf.R;
import com.zity.rqxf.adapter.CaseListAdapter;
import com.zity.rqxf.app.App;
import com.zity.rqxf.base.BaseFragment;
import com.zity.rqxf.bean.CaseList;
import com.zity.rqxf.http.GsonRequest;
import com.zity.rqxf.http.Url;
import com.zity.rqxf.widegt.SPUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by luochao on 2017/3/20.
 * 待办案件
 */

public class CaseFragment extends BaseFragment {
    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_tooltar_title)
    TextView tvTooltarTitle;
    @BindView(R.id.lv_case)
    ListView lvCase;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_case;
    }

    @Override
    protected void initData() {
        String userId = (String) SPUtils.get(getActivity(), "userId", "");
        tvTooltarTitle.setText("待办案件");
        ivToolbarBack.setVisibility(View.GONE);
        getDataFromService(userId);
    }

    //从服务器获取代办案件的列表
    private void getDataFromService(String userId) {
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("page", "1");
        map.put("rows", "20");
        GsonRequest<CaseList> request = new GsonRequest<CaseList>(Request.Method.POST, map, Url.DBLB, CaseList.class, new Response.Listener<CaseList>() {
            @Override
            public void onResponse(CaseList caseList) {
                if (caseList != null && caseList.getList() != null) {
                    CaseListAdapter adapter = new CaseListAdapter(getActivity(), caseList);
                    lvCase.setAdapter(adapter);
                }else {
                    ToastUtils.showShortToast("该用户无数据");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        App.getInstance().getHttpQueue().add(request);
    }
}
