package com.zity.rqxf.activity;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.blankj.utilcode.utils.EmptyUtils;
import com.blankj.utilcode.utils.StringUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.zity.rqxf.R;
import com.zity.rqxf.app.App;
import com.zity.rqxf.base.BaseActivity;
import com.zity.rqxf.bean.User;
import com.zity.rqxf.http.GsonRequest;
import com.zity.rqxf.http.Url;
import com.zity.rqxf.widegt.MyDialog;
import com.zity.rqxf.widegt.SPUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by luochao on 2017/6/12.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.et_user)
    EditText etUser;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_forgetpassword)
    TextView tvForgetpassword;
    @BindView(R.id.btn_enter)
    Button btnEnter;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {

    }


    @OnClick(R.id.btn_enter)
    public void onClick() {
        String user = etUser.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        if (EmptyUtils.isEmpty(user)) {
            ToastUtils.showShortToast("账号不能为空");
            etUser.requestFocus();//把焦点转移到要填写信息之处
            return;
        } else if (EmptyUtils.isEmpty(password)) {
            ToastUtils.showShortToast("密码不能为空");
            etPassword.requestFocus();
            return;
        }
        getDataFromService(user, password);
    }

    @Override
    public void onBackPressedSupport() {
        showExitDialog();
    }

    private void showExitDialog() {
        final MyDialog dialog = new MyDialog(LoginActivity.this);
        dialog.setTitle("提示");
        dialog.setMessage("你确定退出应用吗?");
        dialog.setYesOnclickListener("确定", new MyDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                App.getInstance().exitApp();
            }
        });
        dialog.setNoOnclickListener("取消", new MyDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    //从服务器获取数据
    private void getDataFromService(String username, String password) {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        final GsonRequest<User> request = new GsonRequest<User>(Request.Method.POST, map, Url.LOGIN, User.class, new Response.Listener<User>() {
            @Override
            public void onResponse(User response) {
                if (StringUtils.equals(response.getStatus(), "true")) {
                    String uName= response.getUName();
                    String userId=response.getUserId();
                    String postionName=response.getPostionName();
                    String orgName=response.getOrgName();

                    SPUtils.put(LoginActivity.this,"uName",uName);
                    SPUtils.put(LoginActivity.this,"userId",userId);
                    SPUtils.put(LoginActivity.this,"postionName",postionName);
                    SPUtils.put(LoginActivity.this,"orgName",orgName);

                    ToastUtils.showShortToast(response.getMsg());
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    ToastUtils.showShortToast(response.getMsg());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        App.getInstance().getHttpQueue().add(request);
    }
}
