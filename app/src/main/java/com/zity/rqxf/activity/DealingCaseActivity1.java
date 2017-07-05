package com.zity.rqxf.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.zity.rqxf.R;
import com.zity.rqxf.app.App;
import com.zity.rqxf.base.BaseActivity;
import com.zity.rqxf.bean.Zrr;
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
 * 修改责任人
 */

public class DealingCaseActivity1 extends BaseActivity {
    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_tooltar_title)
    TextView tvTooltarTitle;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.et_sbald)
    EditText etSbald;
    @BindView(R.id.tew2)
    TextView tew2;
    @BindView(R.id.et_bald)
    EditText etBald;
    @BindView(R.id.et_cbar)
    EditText etCbar;
    @BindView(R.id.et_bar)
    EditText etBar;
    @BindView(R.id.btn_dianpin)
    Button btnDianpin;
    @BindView(R.id.btn_quxiao)
    Button btnQuxiao;

    private String id;
    private String userId;
    private String selectText;
    private String flag;

    @Override
    protected int getLayout() {
        return R.layout.activity_dealingcase1;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        userId = (String) SPUtils.get(DealingCaseActivity1.this, "userId", "");
        flag = intent.getStringExtra("flag");
        id = intent.getStringExtra("id");//事件的id
    }

    @OnClick({R.id.iv_toolbar_back, R.id.btn_dianpin, R.id.btn_quxiao})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_toolbar_back:
                onBackPressedSupport();
                break;
            case R.id.btn_dianpin:
                break;
            case R.id.btn_quxiao:
                onBackPressedSupport();
                break;
        }
    }
    

    //修改责任人回显
    private void sendZrrhx(String id) {
        Map<String, String> map = new HashMap<>();
        GsonRequest<Zrr> request = new GsonRequest<Zrr>(Request.Method.POST, map, Url.XGZRRHX, Zrr.class, new Response.Listener<Zrr>() {
            @Override
            public void onResponse(Zrr response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        App.getInstance().getHttpQueue().add(request);
    }
}
