package com.zity.rqxf.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.zity.rqxf.R;
import com.zity.rqxf.adapter.FklsAdapter;
import com.zity.rqxf.app.App;
import com.zity.rqxf.base.BaseActivity;
import com.zity.rqxf.bean.Fkls;
import com.zity.rqxf.http.GsonRequest;
import com.zity.rqxf.http.Url;
import com.zity.rqxf.widegt.SPUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by luochao on 2017/7/3.
 * 办案人反馈历史
 */

public class DealingCaseActivity6 extends BaseActivity {

    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_tooltar_title)
    TextView tvTooltarTitle;
    @BindView(R.id.lv_fkls)
    ListView lvFkls;
    private String id;
    private String userId;
    private String selectText;

    @Override
    protected int getLayout() {
        return R.layout.activity_dealingcase6;
    }

    @Override
    protected void initData() {
        tvTooltarTitle.setText("反馈历史");
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        userId = (String) SPUtils.get(DealingCaseActivity6.this, "userId", "");
        getDataFromService(id);
    }


    @OnClick(R.id.iv_toolbar_back)
    public void onClick() {
        onBackPressedSupport();
    }
    //请求数据
    private void getDataFromService(String id){
        Map<String,String> map =new HashMap<>();
        map.put("id",id);
        GsonRequest<Fkls> request =new GsonRequest<Fkls>(Request.Method.POST, map, Url.BARFKLS, Fkls.class, new Response.Listener<Fkls>() {
            @Override
            public void onResponse(Fkls response) {
                if (response.getList()!=null && response.getList().size()>0){
                    FklsAdapter adapter =new FklsAdapter(DealingCaseActivity6.this,response.getList());
                    lvFkls.setAdapter(adapter);
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
