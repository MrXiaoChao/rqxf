package com.zity.rqxf.fragment;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zity.rqxf.R;
import com.zity.rqxf.activity.ChangPassWordActivity;
import com.zity.rqxf.activity.LoginActivity;
import com.zity.rqxf.base.BaseFragment;
import com.zity.rqxf.widegt.MyDialog;
import com.zity.rqxf.widegt.SPUtils;

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
    @BindView(R.id.rl_password)
    RelativeLayout rlPassword;
    @BindView(R.id.rl_lddp)
    RelativeLayout rlLddp;
    @BindView(R.id.rl_loginout)
    RelativeLayout rlLoginout;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_orgname)
    TextView tvOrgname;
    @BindView(R.id.tv_uName)
    TextView tvUName;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initData() {
        tvTooltarTitle.setText("我  的");
        ivToolbarBack.setVisibility(View.GONE);
        String uName = (String) SPUtils.get(getActivity(), "uName", "");
        String userId = (String) SPUtils.get(getActivity(), "userId", "");
        String postionName = (String) SPUtils.get(getActivity(), "postionName", "");
        String orgName = (String) SPUtils.get(getActivity(), "orgName", "");
        tvName.setText(uName);
        tvOrgname.setText(orgName);
        tvUName.setText(postionName);
    }


    @OnClick({R.id.rl_password, R.id.rl_lddp, R.id.rl_loginout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_password://修改密码
                Intent intent = new Intent(getActivity(), ChangPassWordActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_lddp://领导点评
                break;
            case R.id.rl_loginout://退出登录
                final MyDialog dialog = new MyDialog(getActivity());
                dialog.setTitle("提示");
                dialog.setMessage("是否确认退出当前用户?");
                dialog.setYesOnclickListener("确定", new MyDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        dialog.dismiss(); //关闭dialog
                        Intent intent_loginout = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent_loginout);
                    }
                });
                dialog.setNoOnclickListener("取消", new MyDialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                break;
        }
    }
}
