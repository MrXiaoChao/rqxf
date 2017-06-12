package com.zity.rqxf.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zity.rqxf.R;
import com.zity.rqxf.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by luochao on 2017/6/12.
 */

public class ChangPassWordActivity extends BaseActivity {
    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_tooltar_title)
    TextView tvTooltarTitle;
    @BindView(R.id.et_currentpassword)
    EditText etCurrentpassword;
    @BindView(R.id.iv_currentpassword)
    ImageView ivCurrentpassword;
    @BindView(R.id.et_newpassword)
    EditText etNewpassword;
    @BindView(R.id.iv_newpassword)
    ImageView ivNewpassword;
    @BindView(R.id.et_verifypassword)
    EditText etVerifypassword;
    @BindView(R.id.iv_et_verifypassword)
    ImageView ivEtVerifypassword;
    @BindView(R.id.btn_save)
    Button btnSave;

    @Override
    protected int getLayout() {
        return R.layout.activity_changpassword;
    }

    @Override
    protected void initData() {
        tvTooltarTitle.setText("修改密码");

    }

    @OnClick(R.id.iv_toolbar_back)
    public void onClick() {
        onBackPressedSupport();
    }
}
