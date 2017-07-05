package com.zity.rqxf.activity;

import android.content.Intent;
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
import com.zity.rqxf.R;
import com.zity.rqxf.app.App;
import com.zity.rqxf.base.BaseActivity;
import com.zity.rqxf.bean.Yijiansh;
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
 * 处理意见审核回显
 */

public class DealingCaseActivity2 extends BaseActivity {
    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_tooltar_title)
    TextView tvTooltarTitle;
    @BindView(R.id.et_bar)
    EditText etBar;
    @BindView(R.id.et_blqk)
    EditText etBlqk;
    @BindView(R.id.et_nblyj)
    EditText etNblyj;
    @BindView(R.id.et_yy)
    EditText etYy;
    @BindView(R.id.sp_shi)
    Spinner spShi;
    @BindView(R.id.ll_yqshhx)
    LinearLayout llYqshhx;
    @BindView(R.id.btn_dianpin)
    Button btnDianpin;
    @BindView(R.id.btn_quxiao)
    Button btnQuxiao;
    @BindView(R.id.ll_bar)
    LinearLayout llBar;
    @BindView(R.id.ll_shifou)
    LinearLayout llShifou;
    @BindView(R.id.ll_yy)
    LinearLayout llYy;
    private String id;
    private String userId;
    private String flag;
    private String selectText;

    @Override
    protected int getLayout() {
        return R.layout.activity_dealingcase2;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        userId = (String) SPUtils.get(DealingCaseActivity2.this, "userId", "");
        flag = intent.getStringExtra("flag");
        id = intent.getStringExtra("id");//事件的id
        //12 拟办申请 3 办理意见审核
        if (StringUtils.equals("12", flag)) {
            tvTooltarTitle.setText("拟办申请");
            llBar.setVisibility(View.GONE);
            llShifou.setVisibility(View.GONE);
            llYy.setVisibility(View.GONE);
        } else {
            tvTooltarTitle.setText("办理意见审核");
            llBar.setVisibility(View.VISIBLE);
            llShifou.setVisibility(View.VISIBLE);
            llYy.setVisibility(View.VISIBLE);
            getDataFromService();

            spShi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                String opinion=etBlqk.getText().toString().trim();
                String preoinion=etNblyj.getText().toString().trim();
                String opinion1=etYy.getText().toString().trim();
                if (StringUtils.equals("12", flag)) {
                   sendNBSQ(id,userId,opinion,preoinion);
                } else {
                    sendNBSH(selectText,id,userId,opinion1);
                }
                break;
            case R.id.btn_quxiao:
                onBackPressedSupport();
                break;
        }
    }
    //拟办申请
    private void sendNBSQ(final String id, final String userId, final String opinion , final String preopinion){
        StringRequest request =new StringRequest(Request.Method.POST, Url.NBSQ, new Response.Listener<String>() {
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
                map.put("id",id);
                map.put("userId",userId);
                map.put("opinion",opinion);
                map.put("preopinion",preopinion);
                return map;
            }
        };
        App.getInstance().getHttpQueue().add(request);
    }
    //处理意见审核回显
    private void getDataFromService(){
        Map<String,String> map =new HashMap<>();
        map.put("id",id);
        GsonRequest<Yijiansh> request =new GsonRequest<Yijiansh>(Request.Method.POST, map, Url.SHHX, Yijiansh.class, new Response.Listener<Yijiansh>() {
            @Override
            public void onResponse(Yijiansh response) {
                if (response!=null){
                    etBar.setText(response.getName());
                    etBlqk.setText(response.getOpinion());
                    etNblyj.setText(response.getPreopinion());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        App.getInstance().getHttpQueue().add(request);
    }
    //拟办申请审核
    private void sendNBSH(final String style, final String id, final String userId, final String opinion){
        StringRequest request =new StringRequest(Request.Method.POST, Url.NBSQSH, new Response.Listener<String>() {
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
                map.put("style",style);
                map.put("id",id);
                map.put("userId",userId);
                map.put("reason",opinion);
                return map;
            }
        };
        App.getInstance().getHttpQueue().add(request);
    }
}
