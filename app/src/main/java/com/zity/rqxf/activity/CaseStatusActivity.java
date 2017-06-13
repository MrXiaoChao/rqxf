package com.zity.rqxf.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.blankj.utilcode.utils.ToastUtils;
import com.google.gson.reflect.TypeToken;
import com.zity.rqxf.R;
import com.zity.rqxf.adapter.StatementListAdapter;
import com.zity.rqxf.app.App;
import com.zity.rqxf.base.BaseActivity;
import com.zity.rqxf.bean.StatementList;
import com.zity.rqxf.http.GsonRequest;
import com.zity.rqxf.http.Url;
import com.zity.rqxf.widegt.SPUtils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by luochao on 2017/6/13.
 * 案件查询
 */

public class CaseStatusActivity extends BaseActivity {
    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_tooltar_title)
    TextView tvTooltarTitle;
    @BindView(R.id.tv_starttime)
    TextView tvStarttime;
    @BindView(R.id.tv_endtime)
    TextView tvEndtime;
    @BindView(R.id.et_person)
    EditText etPerson;
    @BindView(R.id.btn_query)
    Button btnQuery;
    @BindView(R.id.lv_casestatus)
    ListView lvCasestatus;
    @BindView(R.id.tv_cancel)
    TextView cancel;
    private String userId;
    private StatementListAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_casestatus;
    }

    @Override
    protected void initData() {
        userId = (String) SPUtils.get(CaseStatusActivity.this, "userId", "");
        tvTooltarTitle.setText("案件查询");
    }


    //时间选择器
    private Calendar showDate;

    private void showDateDialogStart() {
        showDate = Calendar.getInstance();
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                showDate.set(Calendar.YEAR, year);
                showDate.set(Calendar.MONTH, monthOfYear);
                showDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                tvStarttime.setText(DateFormat.format("yyyy-MM-dd", showDate));
            }
        }, showDate.get(Calendar.YEAR), showDate.get(Calendar.MONTH), showDate.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void showDateDialogEnd() {
        showDate = Calendar.getInstance();
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                showDate.set(Calendar.YEAR, year);
                showDate.set(Calendar.MONTH, monthOfYear);
                showDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                tvEndtime.setText(DateFormat.format("yyyy-MM-dd", showDate));
            }
        }, showDate.get(Calendar.YEAR), showDate.get(Calendar.MONTH), showDate.get(Calendar.DAY_OF_MONTH)).show();
    }


    @OnClick({R.id.iv_toolbar_back, R.id.tv_starttime, R.id.tv_endtime, R.id.btn_query, R.id.tv_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_toolbar_back:
                onBackPressedSupport();
                break;
            case R.id.tv_starttime:
                showDateDialogStart();
                break;
            case R.id.tv_endtime:
                showDateDialogEnd();
                break;
            case R.id.btn_query:
                if (adapter != null && listclear!=null) {
                    adapter.clear(listclear);
                }
                String startTime = tvStarttime.getText().toString();
                String endTime = tvEndtime.getText().toString();
                String name = etPerson.getText().toString();
                getDataFromService(userId, name, startTime, endTime);
                break;
            case R.id.tv_cancel:
                tvStarttime.setText("");
                tvEndtime.setText("");
                etPerson.setText("");
                break;
        }
    }

    List<StatementList> listclear;

    //从服务器查询数据
    private void getDataFromService(String userId, String name, String startTime, String endTime) {
        TypeToken type = new TypeToken<List<StatementList>>() {
        };
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("name", name);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("page", "1");
        map.put("rows", "30");

        GsonRequest<List<StatementList>> request = new GsonRequest<List<StatementList>>(Request.Method.POST, map, Url.AJTJLB, type, new Response.Listener<List<StatementList>>() {
            @Override
            public void onResponse(List<StatementList> list) {
                listclear = list;
                if (list != null && list.size() > 0) {
                    adapter = new StatementListAdapter(CaseStatusActivity.this, list);
                    lvCasestatus.setAdapter(adapter);
                } else {
                    ToastUtils.showShortToast("查询无结果");
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
