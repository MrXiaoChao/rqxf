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
import com.blankj.utilcode.utils.ToastUtils;
import com.zity.rqxf.R;
import com.zity.rqxf.app.App;
import com.zity.rqxf.base.BaseActivity;
import com.zity.rqxf.bean.Success;
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
        tvTooltarTitle.setText("负责人修改");
        Intent intent = getIntent();
        userId = (String) SPUtils.get(DealingCaseActivity1.this, "userId", "");
        flag = intent.getStringExtra("flag");
        id = intent.getStringExtra("id");//事件的id
        sendZrrhx(id);
    }

    @OnClick({R.id.iv_toolbar_back, R.id.btn_dianpin, R.id.btn_quxiao})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_toolbar_back:
                onBackPressedSupport();
                break;
            case R.id.btn_dianpin:
                String municipalMeaders=etSbald.getText().toString().trim();
                String baoleader=etBald.getText().toString().trim();
                String baoprincipals=etCbar.getText().toString().trim();
                String bananren=etBar.getText().toString().trim();
                sendZrr(id,municipalMeaders,baoleader,baoprincipals,bananren);
                break;
            case R.id.btn_quxiao:
                onBackPressedSupport();
                break;
        }
    }


    //修改责任人回显
    private void sendZrrhx(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        GsonRequest<Zrr> request = new GsonRequest<Zrr>(Request.Method.POST, map, Url.XGZRRHX, Zrr.class, new Response.Listener<Zrr>() {
            @Override
            public void onResponse(Zrr response) {
                if (response != null) {
                    etSbald.setText(response.getMunicipalMeaders());
                    etBald.setText(response.getBaoleader());
                    etCbar.setText(response.getBaoprincipals());
                    etBar.setText(response.getBananren());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        App.getInstance().getHttpQueue().add(request);
    }

    //提交修改责任人请求
    private void sendZrr(String id,
                         String municipalMeaders,//(市包案领导)
                         String baoleader, //(包案领导)
                         String baoprincipals,//(村包案人)
                         String bananren //(办案人)
    ) {
        Map<String,String> map =new HashMap<>();
        map.put("id",id);
        map.put("municipalMeaders",municipalMeaders);
        map.put("baoleader",baoleader);
        map.put("baoprincipals",baoprincipals);
        map.put("bananren",bananren);
        GsonRequest<Success> request =new GsonRequest<Success>(Request.Method.POST, map, Url.XGZRR, Success.class, new Response.Listener<Success>() {
            @Override
            public void onResponse(Success response) {
                if (response.isStutas()) {
                    ToastUtils.showShortToast(response.getMsg());
                    onBackPressedSupport();
                } else {
                    ToastUtils.showShortToast(response.getMsg());
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
