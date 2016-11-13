package com.panda.teller.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
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
import com.panda.teller.network.HttpResponseListener;
import com.panda.teller.network.HttpUtil;
import com.panda.teller.utils.StringUtil;

/**
 * Created by root on 16-10-31.
 * 邮箱注册页面
 * 点击提交后将命令服务器发送邮件，此时邮箱和密码已经登记到了服务器
 * 手机进入一个等待页面，此时默认循环调用服务器的登录api，但是因为未验证所以无正确的返回值
 * 用户点击邮件中的激活链接，此时等待页面消失，进入主页
 *
 * 暂时不考虑邮件验证，我们提交后若收到正确的响应就直接跳转到MainActivity
 */

public class RegrEmailActivity extends Activity {
    /**
     * TODO: 邮箱注册页
     */
    TextView emailWarn;
    TextView toMobileAct;
    EditText email;
    EditText userPsw;
    EditText userPswCheck;
    Button emailSubmit;

    String string_emailAddress;
    String string_userPsw;
    String sting_userPswCheck;
//    public static void actionStart(Context context) {
//        Intent intent = new Intent(context, RegrEmailActivity.class);
//        context.startActivity(intent);
//    }


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.regr_email);

        emailWarn = (TextView)findViewById(R.id.txt_regr_email_warn);
        email = (EditText)findViewById(R.id.edtTxt_regr_Email);
        userPsw = (EditText)findViewById(R.id.edtTxt_regr_Emailpwd);
        userPswCheck = (EditText)findViewById(R.id.edtTxt_regr_EmailPwdCheck);
        emailSubmit = (Button)findViewById(R.id.btn_regr_EmailSubmit);
        toMobileAct = (TextView)findViewById(R.id.btn_regr_toMobile);

        emailSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 1.获取输入框信息
                string_emailAddress = email.getText().toString();
                string_userPsw = userPsw.getText().toString();
                sting_userPswCheck = userPswCheck.getText().toString();

                // 2.判断密码框和确认密码框内容是否一致
                if(StringUtil.isEmpty(string_emailAddress)) {
                    emailWarn.setText("请输入邮箱");
                    return ;
                } else if(StringUtil.checkEmail(string_emailAddress)) {
                    emailWarn.setText("请输入正确的邮箱");
                    return ;
                }else if(StringUtil.isEmpty(string_userPsw)) {
                    emailWarn.setText("请输入密码");
                    return ;
                } else if(StringUtil.isEmpty(string_userPsw)) {
                    emailWarn.setText("请输入确认密码");
                    return ;
                }else if(!StringUtil.isEqual(string_userPsw, sting_userPswCheck)) {
                    emailWarn.setText("密码与确认密码不一致");
                    return ;
                }

                // 2.提交表单（密码只提交一份）
                String address = "http://47.88.24.11:8080/v1/user/";
                User user = new User();
                user.setEmail(string_emailAddress);
                user.setPwd(string_userPsw);
                Gson gson = new Gson();
                String jsonStr = gson.toJson(user);
                HttpUtil.sendHttpPOSTRequest(address, jsonStr, new HttpResponseListener() {
                    @Override
                    public void onResponse(String response) {
                        // 比对response和api中的内容是否相同
                        Log.d("RegrEmailActivity", response);
                        Gson gson = new Gson();
                        User u = gson.fromJson(response, User.class);
                        if(u.isValid()) {
                            MainActivity.actionStart(RegrEmailActivity.this, Integer.toString(u.getId()));
                        }
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.e("RegrEmailActivity", e.getMessage());
                    }
                });
            }
        });
        toMobileAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegrEmailActivity.this, RegrMobileActivity.class);
                startActivity(intent);
            }
        });
    }
}
