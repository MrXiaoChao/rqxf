package com.zity.rqxf.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;
import com.zity.rqxf.R;
import com.zity.rqxf.adapter.DepartmentAdapter;
import com.zity.rqxf.app.App;
import com.zity.rqxf.base.BaseActivity;
import com.zity.rqxf.bean.Department;
import com.zity.rqxf.http.GsonRequest;
import com.zity.rqxf.http.Url;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by luochao on 2017/6/16.
 * 部门职责
 */

public class DepartmentActivity extends BaseActivity {
    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_tooltar_title)
    TextView tvTooltarTitle;
    @BindView(R.id.lv_department)
    ListView lvDepartment;

    @Override
    protected int getLayout() {
        return R.layout.activity_deparment;
    }

    @Override
    protected void initData() {
        tvTooltarTitle.setText("部门职责");
        getDataFromService();
    }


    @OnClick(R.id.iv_toolbar_back)
    public void onClick() {
        onBackPressedSupport();
    }

    //获取各部门职责列表
    private void getDataFromService() {
        TypeToken type = new TypeToken<List<Department>>() {
        };
        GsonRequest<List<Department>> request = new GsonRequest<List<Department>>(Url.GBMZZLB, type, new Response.Listener<List<Department>>() {
            @Override
            public void onResponse(final List<Department> list) {
                if (list != null && list.size() > 0) {
                    DepartmentAdapter adapter = new DepartmentAdapter(DepartmentActivity.this, list);
                    lvDepartment.setAdapter(adapter);
                }

                lvDepartment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent =new Intent(DepartmentActivity.this,WebViewActivity.class);
                        intent.putExtra("title","部门职责详情");
                        intent.putExtra("URL",Url.GBMZZXQ+"orgId="+list.get(position).getOrgId());
                        startActivity(intent);
                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        App.getInstance().getHttpQueue().add(request);
    }

}
