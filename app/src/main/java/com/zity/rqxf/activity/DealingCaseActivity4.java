package com.zity.rqxf.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
 * 办案人受理案件
 */

public class DealingCaseActivity4 extends BaseActivity {
    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_tooltar_title)
    TextView tvTooltarTitle;
    @BindView(R.id.sp_xzslfs)
    Spinner spXzslfs;
    @BindView(R.id.et_sjbald)
    EditText etSjbald;
    @BindView(R.id.et_bzcy)
    EditText etBzcy;
    @BindView(R.id.et_bmbacy)
    EditText etBmbacy;
    @BindView(R.id.et_cbacy)
    EditText etCbacy;
    @BindView(R.id.et_thly)
    EditText etThly;
    @BindView(R.id.btn_dianpin)
    Button btnDianpin;
    @BindView(R.id.btn_quxiao)
    Button btnQuxiao;
    @BindView(R.id.ll_thly)
    LinearLayout llThly;
    @BindView(R.id.ll_sl)
    LinearLayout llSl;
    @BindView(R.id.sp_xzslfs1)
    Spinner spXzslfs1;
    private String id;
    private String userId;
    private String flag;
    private String selectText;

    @Override
    protected int getLayout() {
        return R.layout.activity_dealingcase4;
    }

    @Override
    protected void initData() {
        tvTooltarTitle.setText("受理案件");
        Intent intent = getIntent();
        userId = (String) SPUtils.get(DealingCaseActivity4.this, "userId", "");
        flag = intent.getStringExtra("flag");
        id = intent.getStringExtra("id");//事件的id

        spXzslfs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spXzslfs.setSelection(0);
                String yiyuan = getResources().getStringArray(R.array.slfs)[position];
                if (StringUtils.equals("受理", yiyuan)) {
                    selectText = "0";
                    llThly.setVisibility(View.GONE);
                    llSl.setVisibility(View.VISIBLE);
                } else {
                    llThly.setVisibility(View.VISIBLE);
                    llSl.setVisibility(View.GONE);
                    selectText = "1";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spXzslfs1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spXzslfs1.setSelection(1);
                String yiyuan = getResources().getStringArray(R.array.slfs)[position];
                if (StringUtils.equals("受理", yiyuan)) {
                    selectText = "0";
                    llThly.setVisibility(View.GONE);
                    llSl.setVisibility(View.VISIBLE);
                } else {
                    llThly.setVisibility(View.VISIBLE);
                    llSl.setVisibility(View.GONE);
                    selectText = "1";
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
                String sjbald = etSjbald.getText().toString().trim();//市级包案领导
                String bzcy = etBzcy.getText().toString().trim();//乡镇/部门包案班子成员
                String bmbacy = etBmbacy.getText().toString().trim();//乡镇/部门包案成员
                String cbacy = etCbacy.getText().toString().trim();//村包案成员
                String etthly = etThly.getText().toString().trim();//退回理由
                if (StringUtils.equals("0", selectText)) {
                    sendSlaj(id, selectText, sjbald, bzcy, bmbacy, cbacy);
                } else {
                    sendThaj(id,userId,etthly);
                }
                break;
            case R.id.btn_quxiao:
                onBackPressedSupport();
                break;
        }
    }

    //办案人受理案件请求
    private void sendSlaj(String id,
                          String style,
                          String municipalMeaders,
                          String baoleader,
                          String baoprincipals,
                          String baohuprincipals) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("style", style);
        map.put("municipalMeaders", municipalMeaders);
        map.put("baoleader", baoleader);
        map.put("baoprincipals", baoprincipals);
        map.put("baohuprincipals", baohuprincipals);
        GsonRequest<Success> request = new GsonRequest<Success>(Request.Method.POST, map, Url.SLAN, Success.class, new Response.Listener<Success>() {
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

    //办案人退回案件请求
    private void sendThaj(String id,String userId,String opinion){
        Map<String,String> map = new HashMap<>();
        map.put("id",id);
        map.put("userId",userId);
        map.put("opinion",opinion);
        GsonRequest<Success> request =new GsonRequest<Success>(Request.Method.POST, map, Url.THAN, Success.class, new Response.Listener<Success>() {
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
