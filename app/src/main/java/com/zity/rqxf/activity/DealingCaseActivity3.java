package com.zity.rqxf.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.blankj.utilcode.utils.StringUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.zity.rqxf.R;
import com.zity.rqxf.app.App;
import com.zity.rqxf.base.BaseActivity;
import com.zity.rqxf.bean.Jasq;
import com.zity.rqxf.bean.Jasqhx;
import com.zity.rqxf.http.GsonRequest;
import com.zity.rqxf.http.Url;
import com.zity.rqxf.widegt.SPUtils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by luochao on 2017/7/3.
 * 结案申请审核回显
 */

public class DealingCaseActivity3 extends BaseActivity {
    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_tooltar_title)
    TextView tvTooltarTitle;
    @BindView(R.id.et_clyjsbh)
    EditText etClyjsbh;
    @BindView(R.id.et_blyj)
    EditText etBlyj;
    @BindView(R.id.et_xfryj)
    EditText etXfryj;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.et_xfrxxyj)
    EditText etXfrxxyj;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.sp_blfs)
    Spinner spBlfs;
    @BindView(R.id.et_blyj1)
    EditText etBlyj1;
    @BindView(R.id.ll_jasqsh)
    LinearLayout llJasqsh;
    @BindView(R.id.btn_dianpin)
    Button btnDianpin;
    @BindView(R.id.btn_quxiao)
    Button btnQuxiao;
    private String id;
    private String userId;
    private String flag;
    private String selectText;
    private Jasq jasq = new Jasq();

    @Override
    protected int getLayout() {
        return R.layout.activity_dealingcase3;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        userId = (String) SPUtils.get(DealingCaseActivity3.this, "userId", "");
        flag = intent.getStringExtra("flag");
        id = intent.getStringExtra("id");//事件的id


        if (StringUtils.equals("13", flag)) {
            tvTooltarTitle.setText("结案申请");
            llJasqsh.setVisibility(View.GONE);
            //回显的内容不能编辑
            etClyjsbh.setEnabled(false);
            etBlyj.setEnabled(false);
            etXfryj.setEnabled(false);
            getData(userId, id);
        } else {
            tvTooltarTitle.setText("结案审核");
            llJasqsh.setVisibility(View.VISIBLE);
            etClyjsbh.setEnabled(false);
            etBlyj.setEnabled(false);
            etXfryj.setEnabled(false);
            tvTime.setEnabled(false);
            etXfrxxyj.setEnabled(false);
            getDataJash(id);

            spBlfs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String yiyuan = getResources().getStringArray(R.array.blfs)[position];
                    if (StringUtils.equals("同意", yiyuan)) {
                        selectText = "0";
                    } else {
                        selectText = "1";
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }


    @OnClick({R.id.iv_toolbar_back, R.id.btn_dianpin, R.id.btn_quxiao, R.id.tv_time})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_toolbar_back:
                onBackPressedSupport();
                break;
            case R.id.btn_dianpin:
                String time = tvTime.getText().toString().trim();
                String xfryj = etXfrxxyj.getText().toString().trim();
                if (StringUtils.equals("13", flag)) {
                    if (jasq != null) {
                        sendJasq(id, jasq.getCode(), userId, jasq.getContent(), jasq.getOpinion(), time, xfryj);
                    }
                }
                if (StringUtils.equals("4", flag)){
                    String yijian= etBlyj1.getText().toString().trim();
                    sendDataToService(userId,id,yijian,selectText);
                }
                break;
            case R.id.btn_quxiao:
                onBackPressedSupport();
                break;
            case R.id.tv_time:
                showDateDialog();
                break;
        }
    }

    //结案申请回显
    private void getData(String userId, String id) {
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("id", id);
        GsonRequest<Jasq> request = new GsonRequest<Jasq>(Request.Method.POST, map, Url.JASQHX, Jasq.class, new Response.Listener<Jasq>() {
            @Override
            public void onResponse(Jasq response) {
                if (response != null) {
                    jasq = response;
                    etClyjsbh.setText(response.getCode());
                    etBlyj.setText(response.getOpinion());
                    etXfryj.setText(response.getContent());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        App.getInstance().getHttpQueue().add(request);
    }


    //结案申请
    private void sendJasq(final String id, final String code, final String userId, final String preopinion, final String petitionerOpinion, final String setTime, final String petitionerContent) {
        StringRequest request = new StringRequest(Request.Method.POST, Url.JASQ, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    if (StringUtils.equals("false", response)) {
                        ToastUtils.showShortToast("申请失败");
                    } else {
                        ToastUtils.showShortToast("申请成功");
                        onBackPressedSupport();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String a = error.getMessage();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("id", id);
                map.put("code", code);
                map.put("userId", userId);
                map.put("preopinion", preopinion);
                map.put("petitionerOpinion", petitionerOpinion);
                map.put("setTime", setTime);
                map.put("petitionerContent", petitionerContent);
                return map;
            }
        };
        App.getInstance().getHttpQueue().add(request);
    }

    //结案审核回显
    private void getDataJash(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        GsonRequest<Jasqhx> request = new GsonRequest<Jasqhx>(Request.Method.POST, map, Url.JASHHX, Jasqhx.class, new Response.Listener<Jasqhx>() {
            @Override
            public void onResponse(Jasqhx response) {
                if (response != null) {
                    etClyjsbh.setText(response.getCode());
                    etBlyj.setText(response.getOpinion());
                    etXfryj.setText(String.valueOf(response.getContent()));
                    tvTime.setText(response.getSetTime());
                    etXfrxxyj.setText(response.getPetitionerContent());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        App.getInstance().getHttpQueue().add(request);
    }

    //结案审核提交
    private void sendDataToService(final String userId, final String id, final String finaleOpinion, final String style) {
        StringRequest request = new StringRequest(Request.Method.POST, Url.JASHTJ, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    if (StringUtils.equals("false", response)) {
                        ToastUtils.showShortToast("申请失败");
                    } else {
                        ToastUtils.showShortToast("申请成功");
                        onBackPressedSupport();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("userId", userId);
                map.put("id", id);
                map.put("finaleOpinion", finaleOpinion);
                map.put("style", style);
                return map;
            }
        };
        App.getInstance().getHttpQueue().add(request);
    }


    //时间选择器
    private Calendar showDate;

    private void showDateDialog() {
        showDate = Calendar.getInstance();
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                showDate.set(Calendar.YEAR, year);
                showDate.set(Calendar.MONTH, monthOfYear);
                showDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                tvTime.setText(DateFormat.format("yyyy-MM-dd", showDate));
            }
        }, showDate.get(Calendar.YEAR), showDate.get(Calendar.MONTH), showDate.get(Calendar.DAY_OF_MONTH)).show();
    }
}
