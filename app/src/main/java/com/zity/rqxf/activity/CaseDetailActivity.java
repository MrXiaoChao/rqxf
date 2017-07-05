package com.zity.rqxf.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.blankj.utilcode.utils.StringUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.google.gson.reflect.TypeToken;
import com.zity.rqxf.R;
import com.zity.rqxf.adapter.CaseDetailAdapter;
import com.zity.rqxf.app.App;
import com.zity.rqxf.base.BaseActivity;
import com.zity.rqxf.bean.CaseDetail;
import com.zity.rqxf.http.GsonRequest;
import com.zity.rqxf.http.Url;
import com.zity.rqxf.widegt.SPUtils;

import java.util.ArrayList;
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
    @BindView(R.id.tv_fywtjsq)
    TextView tvFywtjsq;
    @BindView(R.id.tv_ajjzqk)
    TextView tvAjjzqk;
    @BindView(R.id.tv_more)
    TextView tv_more;
    @BindView(R.id.btn_gridview)
    GridView btnGridview;


    private CaseDetail caseDetail;
    private List<CaseDetail.ButtonListBean> btnlist;
    private String id;
    private String userId;

    @Override
    protected int getLayout() {
        return R.layout.activity_casedetail;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        //上个页面传过来的ID
        id = intent.getStringExtra("id");
        //用户ID
        userId = (String) SPUtils.get(CaseDetailActivity.this, "userId", "");
        tvTooltarTitle.setText("案件详情");
        getDataFromService(id, userId, 2, 3);
        ButtonEven();
    }

    //从服务器获取数据
    List<CaseDetail.FunctionaryBean> functionaryBeanList = new ArrayList<>();

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
                    //获取按钮的列表
                    btnlist = caseDetailList.get(0).getButtonList();

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
                    tvXfsj.setText(caseDetail.getProcessingDate());
                    tvName.setText(caseDetail.getName());
                    tvBfyr.setText(caseDetail.getZenrenren());
                    //list里面的对象的所有字段如果都是为空的话`就直接给删除
                    functionaryBeanList = caseDetail.getFunctionary();
                    if (functionaryBeanList != null && functionaryBeanList.size() > 0) {
                        for (int i = 0; i < functionaryBeanList.size(); i++) {
                            if (functionaryBeanList.get(i).getBananren().equals("") && functionaryBeanList.get(i).getBaoleader().equals("")
                                    && functionaryBeanList.get(i).getBaoprincipals().equals("") && functionaryBeanList.get(i).getMunicipalMeaders().equals("")) {
                                functionaryBeanList.remove(i);
                            }
                        }
                    }
                    if (functionaryBeanList != null && functionaryBeanList.size() > 1) {
                        tv_more.setVisibility(View.VISIBLE);
                    }
                    if (functionaryBeanList != null && functionaryBeanList.size() > 1) {
                        CaseDetailAdapter adapter = new CaseDetailAdapter(CaseDetailActivity.this, functionaryBeanList.subList(0, 1));
                        lvCasedetail.setAdapter(adapter);
                        setListViewHeightBasedOnChildren(lvCasedetail);
                    } else {
                        CaseDetailAdapter adapter = new CaseDetailAdapter(CaseDetailActivity.this, functionaryBeanList);
                        lvCasedetail.setAdapter(adapter);
                        setListViewHeightBasedOnChildren(lvCasedetail);
                    }

                }
                //设置按钮
                setButton();
            }

        }, new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        App.getInstance().getHttpQueue().add(request);
    }

    //显示多条记录 跟显示一条记录
    private boolean isMore = true;

    @OnClick({R.id.iv_toolbar_back, R.id.ll_fzry})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_toolbar_back:
                onBackPressedSupport();
                break;
            case R.id.ll_fzry:
                if (isMore) {
                    if (functionaryBeanList != null && functionaryBeanList.size() > 0) {
                        if (functionaryBeanList.size() > 1) {
                            tv_more.setVisibility(View.GONE);
                        }
                        CaseDetailAdapter adapter = new CaseDetailAdapter(CaseDetailActivity.this, functionaryBeanList);
                        lvCasedetail.setAdapter(adapter);
                        setListViewHeightBasedOnChildren(lvCasedetail);
                        isMore = false;
                    }
                } else {
                    if (functionaryBeanList != null && functionaryBeanList.size() > 0) {
                        if (functionaryBeanList.size() > 1) {
                            tv_more.setVisibility(View.VISIBLE);
                        }
                        if (functionaryBeanList != null && functionaryBeanList.size() > 1) {
                            CaseDetailAdapter adapter = new CaseDetailAdapter(CaseDetailActivity.this, functionaryBeanList.subList(0, 1));
                            lvCasedetail.setAdapter(adapter);
                            setListViewHeightBasedOnChildren(lvCasedetail);
                        } else {
                            CaseDetailAdapter adapter = new CaseDetailAdapter(CaseDetailActivity.this, functionaryBeanList);
                            lvCasedetail.setAdapter(adapter);
                            setListViewHeightBasedOnChildren(lvCasedetail);
                        }
                        isMore = true;
                    }
                }
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

    /**
     * 解决scrollview 嵌套 gridview
     *
     * @param gridView
     * @param columns  gridview的列数
     */
    public static void setGridViewHeightBasedOnChildren(GridView gridView, int columns) {
        ListAdapter listAdapter = gridView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        int count = listAdapter.getCount();
        View listItem = listAdapter.getView(0, null, gridView);
        listItem.measure(0, 0);
        totalHeight = listItem.getMeasuredHeight() + 9;

        int yu = count % columns;

        ViewGroup.LayoutParams params = gridView.getLayoutParams();

        if (yu > 0) {
            params.height = (count - yu) / columns * (totalHeight + 9) + totalHeight;
        } else {
            params.height = count / columns * totalHeight + (count / columns - 1) * 9;
        }
        gridView.setLayoutParams(params);
    }

    //动态设置一些按钮
    private void setButton() {
        //根据按钮数的多少来显示不同的列数
        if (btnlist != null && btnlist.size() > 0) {
            if (btnlist.size() <= 1) {
                btnGridview.setNumColumns(1);
            } else {
                btnGridview.setNumColumns(2);
            }
        }
        //生成动态数组，并且转入数据
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        if (btnlist != null && btnlist.size() > 0) {
            for (int i = 0; i < btnlist.size(); i++) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("ItemText", btnlist.get(i).getStatusName());//按序号做ItemText
                list.add(map);
            }
            if (list != null && list.size() > 0) {
                SimpleAdapter adapter = new SimpleAdapter(this,
                        list,// 数据来源
                        R.layout.item_gridview,
                        //动态数组与ImageItem对应的子项
                        new String[]{"ItemText"},
                        //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                        new int[]{R.id.btn_dianpin});
                //添加并且显示
                btnGridview.setAdapter(adapter);
                setGridViewHeightBasedOnChildren(btnGridview, 2);
            }
        }
    }

    //按钮的点击事件
    private void ButtonEven() {
        btnGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long Id) {
                switch (btnlist.get(position).getStatusId()) {
                    case 1://信访处理
                        break;
                    case 2://延期审核
                        Intent intent2 = new Intent(CaseDetailActivity.this, DealingCaseActivity.class);
                        intent2.putExtra("flag", "2");
                        intent2.putExtra("id", id);
                        startActivity(intent2);
                        break;
                    case 3://处理意见审核
                        Intent intent3 = new Intent(CaseDetailActivity.this, DealingCaseActivity2.class);
                        intent3.putExtra("flag", "3");
                        intent3.putExtra("id", id);
                        startActivity(intent3);
                        break;
                    case 4://结案审核
                        Intent intent4 = new Intent(CaseDetailActivity.this, DealingCaseActivity3.class);
                        intent4.putExtra("flag", "4");
                        intent4.putExtra("id", id);
                        startActivity(intent4);
                        break;
                    case 5://结案
                        break;
                    case 6://接收
                       sendQuesToService(id,userId);
                        break;
                    case 7://受理
                        Intent intent7 = new Intent(CaseDetailActivity.this, DealingCaseActivity4.class);
                        intent7.putExtra("flag", "7");
                        intent7.putExtra("id", id);
                        startActivity(intent7);
                        break;
                    case 8://反馈
                        Intent intent8 = new Intent(CaseDetailActivity.this, DealingCaseActivity5.class);
                        intent8.putExtra("flag", "8");
                        intent8.putExtra("id", id);
                        startActivity(intent8);
                        break;
                    case 9://反馈历史
                        Intent intent9 = new Intent(CaseDetailActivity.this, DealingCaseActivity5.class);
                        intent9.putExtra("flag", "9");
                        intent9.putExtra("id", id);
                        startActivity(intent9);
                        break;
                    case 10://负责人修改
                        Intent intent10 = new Intent(CaseDetailActivity.this, DealingCaseActivity1.class);
                        intent10.putExtra("flag", "10");
                        intent10.putExtra("id", id);
                        startActivity(intent10);
                        break;
                    case 11://延期申请
                        Intent intent11 = new Intent(CaseDetailActivity.this, DealingCaseActivity.class);
                        intent11.putExtra("flag", "11");
                        intent11.putExtra("id", id);
                        startActivity(intent11);
                        break;
                    case 12://拟办申请
                        Intent intent12 = new Intent(CaseDetailActivity.this, DealingCaseActivity2.class);
                        intent12.putExtra("flag", "12");
                        intent12.putExtra("id", id);
                        startActivity(intent12);
                        break;
                    case 13://结案申请
                        Intent intent13 = new Intent(CaseDetailActivity.this, DealingCaseActivity3.class);
                        intent13.putExtra("flag", "13");
                        intent13.putExtra("id", id);
                        startActivity(intent13);
                        break;
                    case 14://信访局处理中
                        break;
                    case 15://责任单位处理
                        break;
                    case 16://点评
                        break;
                }
            }
        });
    }

    //接收之后发送的请求个后台`需要改变按钮状态
    private void sendQuesToService(final String id , final String userId) {
        StringRequest request = new StringRequest(Request.Method.POST, Url.JIESHOU, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (StringUtils.equals("false", response)) {
                    ToastUtils.showShortToast("接收失败");
                } else {
                    ToastUtils.showShortToast("接收成功");
                    getDataFromService(id, userId, 2, 3);
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
                return map;
            }
        };
        App.getInstance().getHttpQueue().add(request);
    }
}
