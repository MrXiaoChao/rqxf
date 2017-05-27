package com.zity.rqxf.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zity.rqxf.R;
import com.zity.rqxf.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by luochao on 2017/3/20.
 * 代办案件
 */

public class CaseFragment extends BaseFragment {
    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_tooltar_title)
    TextView tvTooltarTitle;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_case;
    }

    @Override
    protected void initData() {
        tvTooltarTitle.setText("代办案件");
        ivToolbarBack.setVisibility(View.GONE);
    }
}
