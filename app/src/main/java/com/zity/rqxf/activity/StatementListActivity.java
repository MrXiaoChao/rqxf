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
import com.google.gson.reflect.TypeToken;
import com.zity.rqxf.R;
import com.zity.rqxf.adapter.StatementListAdapter;
import com.zity.rqxf.app.App;
import com.zity.rqxf.base.BaseActivity;
import com.zity.rqxf.bean.Statement;
import com.zity.rqxf.bean.StatementList;
import com.zity.rqxf.http.GsonRequest;
import com.zity.rqxf.http.Url;
import com.zity.rqxf.widegt.SPUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by luochao on 2017/6/12.
 * 案件列表
 */

public class StatementListActivity extends BaseActivity {
    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_tooltar_title)
    TextView tvTooltarTitle;
    @BindView(R.id.lv_statement_list)
    ListView lvStatementList;

    @Override
    protected int getLayout() {
        return R.layout.activity_statement_list;
    }

    @Override
    protected void initData() {
        String userId = (String) SPUtils.get(StatementListActivity.this, "userId", "");
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String emg = intent.getStringExtra("emg");
        String statistical_type = intent.getStringExtra("statistical_type");
        tvTooltarTitle.setText(title);
        getDataFromService(userId, emg, statistical_type);
        initEven();
    }

    //listview的点击事件
    private void initEven(){
        lvStatementList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent(StatementListActivity.this,CaseDetailActivity.class);
                intent.putExtra("id",list1.get(position).getLetterId());
                startActivity(intent);
            }
        });
    }

    @OnClick(R.id.iv_toolbar_back)
    public void onClick() {
        onBackPressedSupport();
    }
    private List<StatementList> list1=new ArrayList<>();
    //从服务器获取数据
    private void getDataFromService(String userId, String emg, String statistical_type) {
        TypeToken type = new TypeToken<List<StatementList>>() {
        };
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("emg", emg);
        map.put("statistical_type", statistical_type);
        map.put("page", "1");
        map.put("rows", "20");
        GsonRequest<List<StatementList>> request = new GsonRequest<List<StatementList>>(Request.Method.POST, map, Url.AJTJLB, type, new Response.Listener<List<StatementList>>() {
            @Override
            public void onResponse(List<StatementList> list) {
                if (list != null && list.size() > 0) {
                    list1=list;
                    StatementListAdapter adapter = new StatementListAdapter(StatementListActivity.this, list);
                    lvStatementList.setAdapter(adapter);
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
