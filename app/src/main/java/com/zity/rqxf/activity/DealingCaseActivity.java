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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.blankj.utilcode.utils.StringUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.google.gson.JsonObject;
import com.zity.rqxf.R;
import com.zity.rqxf.app.App;
import com.zity.rqxf.base.BaseActivity;
import com.zity.rqxf.bean.Shenhe;
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
 * 延期申请
 */

public class DealingCaseActivity extends BaseActivity {
    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_tooltar_title)
    TextView tvTooltarTitle;
    @BindView(R.id.et_day)
    EditText etDay;
    @BindView(R.id.et_yqly)
    EditText etYqly;
    @BindView(R.id.ll_yqsq)
    LinearLayout llYqsq;
    @BindView(R.id.et_blr)
    EditText etBlr;
    @BindView(R.id.et_yqyy)
    EditText etYqyy;
    @BindView(R.id.sp_sfty)
    Spinner spSfty;
    @BindView(R.id.et_yy)
    EditText etYy;
    @BindView(R.id.et_days)
    EditText etDays;
    @BindView(R.id.ll_yqshhx)
    LinearLayout llYqshhx;
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
        return R.layout.activity_dealingcase;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        userId = (String) SPUtils.get(DealingCaseActivity.this, "userId", "");
        flag = intent.getStringExtra("flag");
        id = intent.getStringExtra("id");//事件的id
        // 11延期申请 2延期审核
        if (StringUtils.equals(flag, "11")) {
            llYqsq.setVisibility(View.VISIBLE);
            llYqshhx.setVisibility(View.GONE);
            tvTooltarTitle.setText("延期申请");
        } else {
            llYqshhx.setVisibility(View.VISIBLE);
            llYqsq.setVisibility(View.GONE);
            tvTooltarTitle.setText("延期审核");
            getDataFromService();

            spSfty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String yiyuan = getResources().getStringArray(R.array.shi)[position];
                    if (StringUtils.equals("是", yiyuan)) {
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


    @OnClick({R.id.iv_toolbar_back, R.id.btn_dianpin, R.id.btn_quxiao})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_toolbar_back:
                onBackPressedSupport();
                break;
            case R.id.btn_dianpin:
                //延案申请
                String day = etDay.getText().toString().trim();
                String opinio = etYqly.getText().toString().trim();
                String yy = etYy.getText().toString().trim();
                if (StringUtils.equals(flag, "11")) {
                    sendSQtoService(id, day, opinio);
                } else {
                    sendSHSQtoService(selectText,id,userId,yy);
                }
                break;
            case R.id.btn_quxiao:
                onBackPressedSupport();
                break;
        }
    }

    //延期申请
    private void sendSQtoService(final String childId, final String days, final String opinio) {
        StringRequest request = new StringRequest(Request.Method.POST, Url.XQSQ, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (StringUtils.equals("false", response)) {
                    ToastUtils.showShortToast("申请失败");
                } else {
                    ToastUtils.showShortToast("申请成功");
                    onBackPressedSupport();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String e = error.getMessage();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("userId", userId);
                map.put("childId", childId);
                map.put("days", days);
                map.put("opinio", opinio);
                return map;
            }
        };
        App.getInstance().getHttpQueue().add(request);
    }

    //延期审核回显
    private void getDataFromService() {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        GsonRequest<Shenhe> request = new GsonRequest<Shenhe>(Request.Method.POST, map, Url.XQSHHX, Shenhe.class, new Response.Listener<Shenhe>() {
            @Override
            public void onResponse(Shenhe response) {
                if (response != null) {
                    etDays.setText(response.getPreprolongtime() + "");
                    etYqyy.setText(response.getOpinion());
                    etBlr.setText(response.getPerson());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String a = error.getMessage();
            }
        });
        App.getInstance().getHttpQueue().add(request);
    }

    //延期申请审核
    private void sendSHSQtoService(final String select, final String id, final String userid, final String rejectReason) {
       StringRequest request =new StringRequest(Request.Method.POST, Url.XQSHSQ, new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {
               if (StringUtils.equals("false", response)) {
                   ToastUtils.showShortToast("申请失败");
               } else {
                   ToastUtils.showShortToast("申请成功");
                   onBackPressedSupport();
               }
           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {

           }
       }){
           @Override
           protected Map<String, String> getParams() throws AuthFailureError {
               Map<String,String> map =new HashMap<>();
               map.put("select",select);
               map.put("id",id);
               map.put("userId",userid);
               map.put("rejectReason",rejectReason);
               return map;
           }
       };
       App.getInstance().getHttpQueue().add(request);
    }
}
