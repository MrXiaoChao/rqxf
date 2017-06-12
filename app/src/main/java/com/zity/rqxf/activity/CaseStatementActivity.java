package com.zity.rqxf.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zity.rqxf.R;
import com.zity.rqxf.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by luochao on 2017/6/12.
 * 案件统计
 */

public class CaseStatementActivity extends BaseActivity {
    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_tooltar_title)
    TextView tvTooltarTitle;
    @BindView(R.id.rl_xztj)
    RelativeLayout rlXztj;
    @BindView(R.id.rl_xfbjtj)
    RelativeLayout rlXfbjtj;
    @BindView(R.id.rl_sjtj)
    RelativeLayout rlSjtj;
    @BindView(R.id.rl_blzttj)
    RelativeLayout rlBlzttj;

    @Override
    protected int getLayout() {
        return R.layout.activity_casestatement;
    }

    @Override
    protected void initData() {
        tvTooltarTitle.setText("案件统计");
    }


    @OnClick({R.id.iv_toolbar_back, R.id.rl_xztj, R.id.rl_xfbjtj, R.id.rl_sjtj, R.id.rl_blzttj})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_toolbar_back:
                onBackPressedSupport();
                break;
            case R.id.rl_xztj://各乡镇办件统计
                Intent intent1 =new Intent(CaseStatementActivity.this,StatementActivity.class);
                intent1.putExtra("flagtitle","1");
                startActivity(intent1);
                break;
            case R.id.rl_xfbjtj://信访情况办件统计
                Intent intent2 =new Intent(CaseStatementActivity.this,StatementActivity.class);
                intent2.putExtra("flagtitle","2");
                startActivity(intent2);
                break;
            case R.id.rl_sjtj://按时间统计
                Intent intent3 =new Intent(CaseStatementActivity.this,StatementActivity.class);
                intent3.putExtra("flagtitle","3");
                startActivity(intent3);
                break;
            case R.id.rl_blzttj://按办理状态统计
                Intent intent4 =new Intent(CaseStatementActivity.this,StatementActivity.class);
                intent4.putExtra("flagtitle","4");
                startActivity(intent4);
                break;
        }
    }
}
