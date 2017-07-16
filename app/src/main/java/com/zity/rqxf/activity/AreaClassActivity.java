package com.zity.rqxf.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zity.rqxf.R;
import com.zity.rqxf.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by luochao on 2017/6/17.
 * 地区分类
 */

public class AreaClassActivity extends BaseActivity {
    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_tooltar_title)
    TextView tvTooltarTitle;
    @BindView(R.id.ll_xzbsc)
    LinearLayout llXzbsc;
    @BindView(R.id.ll_shfwl)
    LinearLayout llShfwl;
    @BindView(R.id.ll_zfjdl)
    LinearLayout llZfjdl;
    @BindView(R.id.ll_jjfzl)
    LinearLayout llJjfzl;
    @BindView(R.id.ll_dqzwl1)
    LinearLayout llDqzwl1;
    @BindView(R.id.ll_dqzwl2)
    LinearLayout llDqzwl2;

    @Override
    protected int getLayout() {
        return R.layout.activity_areaclass;
    }

    @Override
    protected void initData() {
        tvTooltarTitle.setText("地区分类");
    }


    @OnClick({R.id.iv_toolbar_back, R.id.ll_xzbsc, R.id.ll_shfwl, R.id.ll_zfjdl, R.id.ll_jjfzl, R.id.ll_dqzwl1, R.id.ll_dqzwl2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_toolbar_back:
                onBackPressedSupport();
                break;
            case R.id.ll_xzbsc:
//                Intent intent1=new Intent(AreaClassActivity.this,AreaListActivity.class);
//                startActivity(intent1);
                break;
            case R.id.ll_shfwl:
                Intent intent2=new Intent(AreaClassActivity.this,AreaListActivity.class);
                intent2.putExtra("title","社会服务类");
                startActivity(intent2);
                break;
            case R.id.ll_zfjdl:
                Intent intent3=new Intent(AreaClassActivity.this,AreaListActivity.class);
                intent3.putExtra("title","执法监督类");
                startActivity(intent3);
                break;
            case R.id.ll_jjfzl:
                Intent intent4=new Intent(AreaClassActivity.this,AreaListActivity.class);
                intent4.putExtra("title","经济发展类");
                startActivity(intent4);
                
                break;
            case R.id.ll_dqzwl1:
                Intent intent5=new Intent(AreaClassActivity.this,AreaListActivity.class);
                intent5.putExtra("title","社会服务类(一)");
                startActivity(intent5);
                break;
            case R.id.ll_dqzwl2:
                Intent intent6=new Intent(AreaClassActivity.this,AreaListActivity.class);
                intent6.putExtra("title","社会服务类(二)");
                startActivity(intent6);
                break;
        }
    }
}
