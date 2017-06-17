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
import com.blankj.utilcode.utils.ToastUtils;
import com.google.gson.reflect.TypeToken;
import com.zity.rqxf.R;
import com.zity.rqxf.adapter.DepartmentAdapter;
import com.zity.rqxf.app.App;
import com.zity.rqxf.base.BaseActivity;
import com.zity.rqxf.bean.Department;
import com.zity.rqxf.http.GsonRequest;
import com.zity.rqxf.http.Url;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by luochao on 2017/6/17.
 * 地区分类列表
 * 这里的数据结局跟部门职责数据结构一样的，所以适配器跟实体类都用部门职责的
 */

public class AreaListActivity extends BaseActivity {
    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_tooltar_title)
    TextView tvTooltarTitle;
    @BindView(R.id.lv_arealist)
    ListView lvArealist;

    @Override
    protected int getLayout() {
        return R.layout.activity_arealist;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        tvTooltarTitle.setText(title);
        if (StringUtils.equals(title, "社会服务类")) {
            getDataFromService("1");
        } else if (StringUtils.equals(title, "执法监督类")) {
            getDataFromService("2");
        } else if (StringUtils.equals(title, "经济发展类")) {
            getDataFromService("3");
        } else if (StringUtils.equals(title, "社会服务类(一)")) {
            getDataFromService("4");
        } else if (StringUtils.equals(title, "社会服务类(二)")) {
            getDataFromService("5");
        }
    }


    @OnClick(R.id.iv_toolbar_back)
    public void onClick() {
        onBackPressedSupport();
    }

    //获取地区分类列表
    private void getDataFromService(String flagNumber) {
        TypeToken type = new TypeToken<List<Department>>() {
        };
        Map<String, String> map = new HashMap<>();
        map.put("flagNumber", flagNumber);
        GsonRequest<List<Department>> request = new GsonRequest<List<Department>>(Request.Method.POST, map, Url.DQFL, type, new Response.Listener<List<Department>>() {
            @Override
            public void onResponse(final List<Department> list) {
                if (list != null && list.size() > 0) {
                    DepartmentAdapter adapter = new DepartmentAdapter(AreaListActivity.this, list);
                    lvArealist.setAdapter(adapter);
                }else {
                    ToastUtils.showShortToast("该用户无数据");
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
