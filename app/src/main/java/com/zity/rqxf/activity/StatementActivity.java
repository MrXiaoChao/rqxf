package com.zity.rqxf.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.blankj.utilcode.utils.StringUtils;
import com.google.gson.reflect.TypeToken;
import com.zity.rqxf.R;
import com.zity.rqxf.adapter.StatementAdapter;
import com.zity.rqxf.adapter.StatementAdapter1;
import com.zity.rqxf.app.App;
import com.zity.rqxf.base.BaseActivity;
import com.zity.rqxf.bean.Statement;
import com.zity.rqxf.bean.Statement1;
import com.zity.rqxf.http.GsonRequest;
import com.zity.rqxf.http.Url;
import com.zity.rqxf.widegt.SPUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by luochao on 2017/6/12.
 * 案件统计列表
 */

public class StatementActivity extends BaseActivity {

    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_tooltar_title)
    TextView tvTooltarTitle;
    @BindView(R.id.lv_statement)
    ListView lvStatement;

    @Override
    protected int getLayout() {
        return R.layout.activity_statement;
    }

    @Override
    protected void initData() {
        initview();
    }

    private void initview() {
        String userId = (String) SPUtils.get(StatementActivity.this, "userId", "");
        Intent intent = getIntent();
        String flagtitle = intent.getStringExtra("flagtitle");
        if (StringUtils.equals(flagtitle, "1")) {
            tvTooltarTitle.setText("乡镇案件统计");
            getDataFromService1(userId);
        } else if (StringUtils.equals(flagtitle, "2")) {
            tvTooltarTitle.setText("信访情况办件统计");
            getDataFromService2(userId);
        } else if (StringUtils.equals(flagtitle, "3")) {
            tvTooltarTitle.setText("按时间统计");
            getDataFromService3(userId);
        } else if (StringUtils.equals(flagtitle, "4")) {
            tvTooltarTitle.setText("按办理状态统计");
            getDataFromService4(userId);
        }
    }
    //listview 的点击事件
    private void initEven(final List<Statement> list){
        lvStatement.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent(StatementActivity.this,StatementListActivity.class);
                intent.putExtra("title",list.get(position).getName());
                intent.putExtra("emg",list.get(position).getEmg());
                intent.putExtra("statistical_type",list.get(position).getStatistical_type());
                startActivity(intent);
            }
        });
    }

    //listview 的点击事件
    private void initEven1(final List<Statement1> list){
        lvStatement.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent(StatementActivity.this,StatementListActivity.class);
                intent.putExtra("title",list.get(position).getName());
                intent.putExtra("emg",list.get(position).getEmg());
                intent.putExtra("statistical_type",String.valueOf(list.get(position).getStatistical_type()));
                startActivity(intent);
            }
        });
    }

    @OnClick(R.id.iv_toolbar_back)
    public void onClick() {
        onBackPressedSupport();
    }

    //乡镇办件统计
    private void getDataFromService1(String userId) {
        TypeToken type = new TypeToken<List<Statement>>() {
        };
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);

        GsonRequest<List<Statement>> request = new GsonRequest<List<Statement>>(Request.Method.POST, map, Url.XZBJ, type, new Response.Listener<List<Statement>>() {
            @Override
            public void onResponse(List<Statement> list) {
                if (list != null && list.size() > 0) {
                    StatementAdapter adapter = new StatementAdapter(StatementActivity.this, list);
                    lvStatement.setAdapter(adapter);
                    initEven(list);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        App.getInstance().getHttpQueue().add(request);
    }

    //按信访情况办件统计
    private void getDataFromService2(String userId) {
        TypeToken type = new TypeToken<List<Statement>>() {
        };
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);

        GsonRequest<List<Statement>> request = new GsonRequest<List<Statement>>(Request.Method.POST, map, Url.XFQK, type, new Response.Listener<List<Statement>>() {
            @Override
            public void onResponse(List<Statement> list) {
                if (list != null && list.size() > 0) {
                    StatementAdapter adapter = new StatementAdapter(StatementActivity.this, list);
                    lvStatement.setAdapter(adapter);
                    initEven(list);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        App.getInstance().getHttpQueue().add(request);
    }

    //按时间办件统计
    private void getDataFromService3(String userId) {
        TypeToken type = new TypeToken<List<Statement1>>() {
        };
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);

        GsonRequest<List<Statement1>> request = new GsonRequest<List<Statement1>>(Request.Method.POST, map, Url.TIME, type, new Response.Listener<List<Statement1>>() {
            @Override
            public void onResponse(List<Statement1> list) {
                if (list != null && list.size() > 0) {
                    StatementAdapter1 adapter = new StatementAdapter1(StatementActivity.this, list);
                    lvStatement.setAdapter(adapter);
                    initEven1(list);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        App.getInstance().getHttpQueue().add(request);
    }


    //按办理状态统计
    private void getDataFromService4(String userId) {
        TypeToken type = new TypeToken<List<Statement>>() {
        };
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);

        GsonRequest<List<Statement>> request = new GsonRequest<List<Statement>>(Request.Method.POST, map, Url.BLZT, type, new Response.Listener<List<Statement>>() {
            @Override
            public void onResponse(List<Statement> list) {
                if (list != null && list.size() > 0) {
                    StatementAdapter adapter = new StatementAdapter(StatementActivity.this, list);
                    lvStatement.setAdapter(adapter);
                    initEven(list);
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
