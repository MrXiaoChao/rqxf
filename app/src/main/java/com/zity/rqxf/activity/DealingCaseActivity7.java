package com.zity.rqxf.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.zity.rqxf.R;
import com.zity.rqxf.adapter.DialogAdapter;
import com.zity.rqxf.app.App;
import com.zity.rqxf.base.BaseActivity;
import com.zity.rqxf.bean.Xfcl;
import com.zity.rqxf.http.GsonRequest;
import com.zity.rqxf.http.Url;
import com.zity.rqxf.widegt.SPUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by luochao on 2017/7/3.
 * 信访处理
 */

public class DealingCaseActivity7 extends BaseActivity {

    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_tooltar_title)
    TextView tvTooltarTitle;
    @BindView(R.id.tv_zrdw)
    TextView tvZrdw;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.et_blzj)
    EditText etBlzj;
    @BindView(R.id.btn_dianpin)
    Button btnDianpin;
    @BindView(R.id.btn_quxiao)
    Button btnQuxiao;
    private String id;
    private String userId;
    private String selectText;
    private String flag;
    private DialogAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_dealingcase7;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        //上个页面传过来的ID
        id = intent.getStringExtra("id");
        //用户ID
        userId = (String) SPUtils.get(DealingCaseActivity7.this, "userId", "");
        tvTooltarTitle.setText("信访处理");
        getDataFromService();
    }


    @OnClick({R.id.iv_toolbar_back, R.id.btn_dianpin, R.id.btn_quxiao, R.id.tv_zrdw})
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
            case R.id.tv_zrdw:
                if (adapter != null) {
                    DialogPlus dialog = DialogPlus.newDialog(this)
                            .setAdapter(adapter)
                            .setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(DialogPlus dialog, Object item, View view, int position) {

                                }
                            })
                            .setExpanded(true)
                            .setGravity(Gravity.CENTER)
                            .create();
                    dialog.show();
                }
                break;
        }
    }

    //信访处理回显
    private void getDataFromService() {
        TypeToken type = new TypeToken<List<Xfcl>>() {
        };
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        GsonRequest<List<Xfcl>> request = new GsonRequest<List<Xfcl>>(Request.Method.POST, map, Url.XFCL, type, new Response.Listener<List<Xfcl>>() {
            @Override
            public void onResponse(List<Xfcl> list) {
                if (list != null && list.size() > 0) {
                    adapter = new DialogAdapter(DealingCaseActivity7.this, list);
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
