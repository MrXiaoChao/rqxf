package com.zity.rqxf.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.blankj.utilcode.utils.StringUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.zity.rqxf.R;
import com.zity.rqxf.app.App;
import com.zity.rqxf.base.BaseActivity;
import com.zity.rqxf.bean.Success;
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
 * 办案人反馈
 */

public class DealingCaseActivity5 extends BaseActivity {


    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_tooltar_title)
    TextView tvTooltarTitle;
    @BindView(R.id.sp_gzfs)
    Spinner spGzfs;
    @BindView(R.id.et_cyr)
    EditText etCyr;
    @BindView(R.id.et_gznr)
    EditText etGznr;
    @BindView(R.id.btn_dianpin)
    Button btnDianpin;
    @BindView(R.id.btn_quxiao)
    Button btnQuxiao;
    private String id;
    private String userId;
    private String selectText;

    @Override
    protected int getLayout() {
        return R.layout.activity_dealingcase5;
    }

    @Override
    protected void initData() {
        tvTooltarTitle.setText("反馈");
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        userId = (String) SPUtils.get(DealingCaseActivity5.this, "userId", "");


        spGzfs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String yiyuan = getResources().getStringArray(R.array.gzfs)[position];
                if (StringUtils.equals("其他", yiyuan)) {
                    selectText = "0";
                } else if (StringUtils.equals("走访", yiyuan)) {
                    selectText = "1";
                }else if (StringUtils.equals("电话", yiyuan)) {
                    selectText = "2";
                }else if (StringUtils.equals("约谈", yiyuan)) {
                    selectText = "3";
                }else if (StringUtils.equals("会议", yiyuan)) {
                    selectText = "4";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @OnClick({R.id.iv_toolbar_back, R.id.btn_dianpin, R.id.btn_quxiao})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_toolbar_back:
                onBackPressedSupport();
                break;
            case R.id.btn_dianpin:
                String participant=etCyr.getText().toString().trim();
                String content=etGznr.getText().toString().trim();
                sendFkToService(id,selectText,userId,participant,content);
                break;
            case R.id.btn_quxiao:
                onBackPressedSupport();
                break;
        }
    }
    //反馈请求
    private void sendFkToService(String id,String working,String userId,String participant,String content){
        Map<String,String> map =new HashMap<>();
        map.put("id",id);
        map.put("working",working);
        map.put("userId",userId);
        map.put("participant",participant);
        map.put("content",content);
        GsonRequest<Success> request =new GsonRequest<Success>(Request.Method.POST, map, Url.BARFK, Success.class, new Response.Listener<Success>() {
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
