package com.zity.rqxf.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zity.rqxf.R;
import com.zity.rqxf.base.BaseActivity;
import com.zity.rqxf.http.Url;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by luochao on 2017/6/16.
 * 政策法规
 */

public class PolicyActivity extends BaseActivity {
    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_tooltar_title)
    TextView tvTooltarTitle;
    @BindView(R.id.rl_xztj)
    RelativeLayout rlXztj;
    @BindView(R.id.rl_gbmzz)
    RelativeLayout rlGbmzz;

    @Override
    protected int getLayout() {
        return R.layout.activity_policy;
    }

    @Override
    protected void initData() {
        tvTooltarTitle.setText("政策法规");
    }

    @OnClick({R.id.iv_toolbar_back, R.id.rl_xztj, R.id.rl_gbmzz})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_toolbar_back:
                onBackPressedSupport();
                break;
            case R.id.rl_xztj:
                Intent intent = new Intent(PolicyActivity.this, WebViewActivity.class);
                intent.putExtra("URL", Url.XFTL);
                intent.putExtra("title","信访条例详情");
                startActivity(intent);
                break;
            case R.id.rl_gbmzz:
                Intent intent_department=new Intent(PolicyActivity.this,DepartmentActivity.class);
                startActivity(intent_department);
                break;
        }
    }
}
