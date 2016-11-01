package com.panda.teller.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.panda.teller.R;
import com.panda.teller.models.User;
import com.panda.teller.utils.HttpResponseListener;
import com.panda.teller.utils.HttpUtil;
import com.panda.teller.utils.StringUtil;

/**
 * Created by root on 16-10-29.
 * 手机注册页面
 *
 * 后期需要加入验证码功能
 */

public class RegrMobileActivity extends Activity {

    TextView txtWarn;

    EditText edtMobile;

    EditText edtCode;

    EditText edtPwd;

    EditText edtPwd1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.regr_mobile);

        txtWarn = (TextView)  findViewById(R.id.txt_regr_mobile_warn);
        edtMobile = (EditText) findViewById(R.id.edtTxt_regr_mobile);
        edtCode = (EditText) findViewById(R.id.edtTxt_regr_code);
        edtPwd = (EditText) findViewById(R.id.edtTxt_regr_pwd);
        edtPwd1 = (EditText) findViewById(R.id.edtTxt_regr_pwd1);

        // 验证码按钮
        Button codeBtn = (Button) findViewById(R.id.btn_regr_code);
        codeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 请求验证码

            }
        });

        // 提交按钮
        Button submitBtn = (Button) findViewById(R.id.btn_regr_submit);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 1.获取输入框信息
                String mobile = edtMobile.getText().toString();
                String code = edtCode.getText().toString();
                String pwd = edtPwd.getText().toString();
                String pwd1 = edtPwd1.getText().toString();

                // 2.判断密码框和确认密码框内容是否一致
                if(StringUtil.isEmpty(mobile)) {
                    txtWarn.setText("请输入手机号");
                    return ;
                } else if(StringUtil.isEmpty(code)) {
                    txtWarn.setText("请输入验证码");
                    return ;
                } else if(StringUtil.isEmpty(pwd)) {
                    txtWarn.setText("请输入密码");
                    return ;
                } else if(StringUtil.isEmpty(pwd1)) {
                    txtWarn.setText("请输入确认密码");
                    return ;
                }

                if(!StringUtil.isEqual(pwd, pwd1)) {
                    txtWarn.setText("密码与确认密码不一致");
                    return ;
                }

                // 2.提交表单（密码只提交一份）
                String address = "http://47.88.24.11:8080/v1/user/";
                User user = new User();
                user.setMobile(mobile);
                user.setPwd(pwd);
                Gson gson = new Gson();
                String jsonStr = gson.toJson(user);
                HttpUtil.sendHttpPOSTRequest(address, jsonStr, new HttpResponseListener() {
                    @Override
                    public void onResponse(String response) {
                        // 比对response和api中的内容是否相同
                        Log.d("RegrMobileActivity", response);
                        Gson gson = new Gson();
                        User u = gson.fromJson(response, User.class);
                        if(u.isValid()) {
                            MainActivity.actionStart(RegrMobileActivity.this, Integer.toString(u.getId()));
                        }
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.e("RegrMobileActivity", e.getMessage());
                    }
                });
            }
        });

        // 跳转到邮箱注册页面
        TextView toEmailTv = (TextView) findViewById(R.id.btn_regr_toEmail);
        toEmailTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegrEmailActivity.actionStart(RegrMobileActivity.this);
            }
        });

    }

}
