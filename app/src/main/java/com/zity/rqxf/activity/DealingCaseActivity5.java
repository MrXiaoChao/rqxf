package com.zity.rqxf.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blankj.utilcode.utils.StringUtils;
import com.zity.rqxf.R;
import com.zity.rqxf.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by luochao on 2017/7/3.
 * 办案人反馈
 */

public class DealingCaseActivity5 extends BaseActivity {
    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.ll_fkls)
    LinearLayout llFkls;
    @BindView(R.id.btn_dianpin)
    Button btnDianpin;
    @BindView(R.id.btn_quxiao)
    Button btnQuxiao;
    @BindView(R.id.ll_button)
    LinearLayout llButton;


    @Override
    protected int getLayout() {
        return R.layout.activity_dealingcase5;
    }

    @Override
    protected void initData() {
        Intent intent =getIntent();
        String flag =intent.getStringExtra("flag");
        //2中情况反馈 跟 反馈历史页面是不一样的`下面做了判断
        if (StringUtils.equals("8",flag)){
           llFkls.setVisibility(View.GONE);
        }else {
            llFkls.setVisibility(View.VISIBLE);
            llButton.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.iv_toolbar_back, R.id.btn_dianpin, R.id.btn_quxiao})
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
        }
    }
}
