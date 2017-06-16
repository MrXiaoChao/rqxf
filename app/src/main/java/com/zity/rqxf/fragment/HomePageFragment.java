package com.zity.rqxf.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.zity.rqxf.R;
import com.zity.rqxf.activity.CaseStatementActivity;
import com.zity.rqxf.activity.CaseStatusActivity;
import com.zity.rqxf.activity.MainActivity;
import com.zity.rqxf.activity.PolicyActivity;
import com.zity.rqxf.base.BaseFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by luochao on 2017/3/20.
 * 首页
 */

public class HomePageFragment extends BaseFragment {
    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_tooltar_title)
    TextView tvTooltarTitle;
    @BindView(R.id.mineMainview)
    GridView mineMainview;
    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;
    // 图片封装为一个数组
    private int[] icon = {R.mipmap.tongji, R.mipmap.chaxun, R.mipmap.fenlei, R.mipmap.anjian, R.mipmap.dianping, R.mipmap.wode,
            R.mipmap.fagui};
    private String[] iconName = {"案件统计", "案件查询", "地区分类", "待办案件", "领导点评", "我  的",
            "政策法规"};


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_homepage;
    }

    @Override
    protected void initData() {
        initview();
        initGridViewEven();
    }

    private void initview() {
        tvTooltarTitle.setText("首  页");
        ivToolbarBack.setVisibility(View.GONE);
        //新建List
        data_list = new ArrayList<Map<String, Object>>();
        //获取数据
        getData();
        //新建适配器
        String[] from = {"image", "text"};
        int[] to = {R.id.hompage_image, R.id.homepage_text};
        sim_adapter = new SimpleAdapter(getActivity(), data_list, R.layout.item_hompage, from, to);
        //配置适配器
        mineMainview.setAdapter(sim_adapter);
    }

    private void initGridViewEven() {
        mineMainview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {

                    case 0://案件统计
                        Intent intent = new Intent(getActivity(), CaseStatementActivity.class);
                        startActivity(intent);
                        break;
                    case 1://案件查询
                        Intent intent_casestatus = new Intent(getActivity(), CaseStatusActivity.class);
                        startActivity(intent_casestatus);
                        break;
                    case 2://地区分类
                        break;
                    case 3://待办案件
                        MainActivity mainActivity1 = (MainActivity) getActivity();
                        mainActivity1.setButtonToFragment1();
                        break;
                    case 4://领导点评
                        break;
                    case 5://我的
                        MainActivity mainActivity = (MainActivity) getActivity();
                        mainActivity.setButtonToFragment();
                        break;
                    case 6://政策法规
                        Intent intent_policy = new Intent(getActivity(), PolicyActivity.class);
                        startActivity(intent_policy);
                        break;

                }
            }
        });
    }

    public List<Map<String, Object>> getData() {
        //icon和iconName的长度是相同的，这里任选其一都可以
        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
        }
        return data_list;
    }

}
