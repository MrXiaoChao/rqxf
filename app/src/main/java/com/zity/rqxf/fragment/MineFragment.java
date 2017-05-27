package com.zity.rqxf.fragment;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.EmptyUtils;
import com.zity.rqxf.R;
import com.zity.rqxf.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by luochao on 2017/3/20.
 * 我的
 */

public class MineFragment extends BaseFragment {
    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_tooltar_title)
    TextView tvTooltarTitle;



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initData() {
        tvTooltarTitle.setText("我  的");
        ivToolbarBack.setVisibility(View.GONE);
    }
}
