package com.zity.rqxf.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;
import com.zity.rqxf.R;
import com.zity.rqxf.adapter.CaseDetailAdapter;
import com.zity.rqxf.app.App;
import com.zity.rqxf.base.BaseActivity;
import com.zity.rqxf.bean.CaseDetail;
import com.zity.rqxf.http.GsonRequest;
import com.zity.rqxf.http.Url;
import com.zity.rqxf.widegt.SPUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by luochao on 2017/6/20.
 * 案件详情
 */

public class CaseDetailActivity extends BaseActivity {
    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_tooltar_title)
    TextView tvTooltarTitle;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.btn_tybj)
    Button btnTybj;
    @BindView(R.id.tv_xfsj)
    TextView tvXfsj;
    @BindView(R.id.tv_szdq)
    TextView tvSzdq;
    @BindView(R.id.tv_wtsd)
    TextView tvWtsd;
    @BindView(R.id.ll_fzry)
    RelativeLayout llFzry;
    @BindView(R.id.lv_casedetail)
    ListView lvCasedetail;
    @BindView(R.id.tv_xfxz)
    TextView tvXfxz;
    @BindView(R.id.tv_fwqk)
    TextView tvFwqk;
    @BindView(R.id.tv_czxz)
    TextView tvCzxz;
    @BindView(R.id.tv_bfyr)
    TextView tvBfyr;
    @BindView(R.id.tv_sfrs)
    TextView tvSfrs;
    @BindView(R.id.tv_sfzh)
    TextView tvSfzh;
    @BindView(R.id.tv_lxdh)
    TextView tvLxdh;
    @BindView(R.id.btn_dianpin)
    Button btnDianpin;
    @BindView(R.id.btn_fanhui)
    Button btnFanhui;
    @BindView(R.id.tv_fywtjsq)
    TextView tvFywtjsq;
    @BindView(R.id.tv_ajjzqk)
    TextView tvAjjzqk;
    @BindView(R.id.tv_more)
    TextView tv_more;
    private CaseDetail caseDetail;

    @Override
    protected int getLayout() {
        return R.layout.activity_casedetail;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");//上个页面传过来的ID
        String userId = (String) SPUtils.get(CaseDetailActivity.this, "userId", "");//用户ID
        tvTooltarTitle.setText("案件详情");
        getDataFromService(id, userId, 2, 3);
    }

    //从服务器获取数据
    private void getDataFromService(String id, String userID, final int cases, int status) {
        TypeToken type = new TypeToken<List<CaseDetail>>() {
        };
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("userId", userID);
        map.put("cases", cases + "");
        map.put("status", status + "");
        GsonRequest<List<CaseDetail>> request = new GsonRequest<List<CaseDetail>>(Request.Method.POST, map, Url.XFXQ, type, new Response.Listener<List<CaseDetail>>() {
            @Override
            public void onResponse(List<CaseDetail> caseDetailList) {
                if (caseDetailList != null && caseDetailList.size() > 0) {
                    caseDetail = caseDetailList.get(0);
                    tvLxdh.setText(caseDetail.getPhone());
                    tvSfzh.setText(caseDetail.getDocumentNumber());
                    tvCzxz.setText(caseDetail.getRepeats());
                    tvFwqk.setText(caseDetail.getAppeal());
                    tvXfxz.setText(caseDetail.getLetterProperty());
                    tvAjjzqk.setText(caseDetail.getStage());
                    tvFywtjsq.setText(caseDetail.getContent());
                    tvWtsd.setText(caseDetail.getProblemPossession());
                    tvSzdq.setText(caseDetail.getAddress());
                    tvXfsj.setText(caseDetail.getProcessingDate() + "");
                    tvName.setText(caseDetail.getName());
                    tvBfyr.setText(caseDetail.getZenrenren());

                    if (caseDetail.getFunctionary() != null && caseDetail.getFunctionary().size() > 1) {
                        tv_more.setVisibility(View.VISIBLE);
                    }

                    if (caseDetail.getFunctionary() != null && caseDetail.getFunctionary().size() > 0) {
                        CaseDetailAdapter adapter = new CaseDetailAdapter(CaseDetailActivity.this, caseDetail.getFunctionary().subList(0, 1));
                        lvCasedetail.setAdapter(adapter);
                        setListViewHeightBasedOnChildren(lvCasedetail);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        App.getInstance().getHttpQueue().add(request);
    }

    //显示多条记录 跟显示一条记录
    private boolean isMore = true;

    @OnClick({R.id.iv_toolbar_back, R.id.btn_tybj, R.id.ll_fzry, R.id.btn_dianpin, R.id.btn_fanhui})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_toolbar_back:
                onBackPressedSupport();
                break;
            case R.id.btn_tybj:
                break;
            case R.id.ll_fzry:
                if (isMore) {
                    if (caseDetail.getFunctionary() != null && caseDetail.getFunctionary().size() > 0) {
                        tv_more.setVisibility(View.GONE);
                        CaseDetailAdapter adapter = new CaseDetailAdapter(CaseDetailActivity.this, caseDetail.getFunctionary());
                        lvCasedetail.setAdapter(adapter);
                        setListViewHeightBasedOnChildren(lvCasedetail);
                        isMore = false;
                    }
                } else {
                    if (caseDetail.getFunctionary() != null && caseDetail.getFunctionary().size() > 0) {
                        tv_more.setVisibility(View.VISIBLE);
                        CaseDetailAdapter adapter = new CaseDetailAdapter(CaseDetailActivity.this, caseDetail.getFunctionary().subList(0, 1));
                        lvCasedetail.setAdapter(adapter);
                        setListViewHeightBasedOnChildren(lvCasedetail);
                        isMore = true;
                    }
                }
                break;
            case R.id.btn_dianpin:
                break;
            case R.id.btn_fanhui:
                onBackPressedSupport();
                break;

        }
    }

    //解决listview 跟scrollview 只显示一条数据的问题
    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

}
