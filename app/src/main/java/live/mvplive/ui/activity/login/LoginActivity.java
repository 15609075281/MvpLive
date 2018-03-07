package live.mvplive.ui.activity.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import live.mvplive.Bean.LoginUser;
import live.mvplive.R;

import live.mvplive.module.presenter.LoginPresenter;
import live.mvplive.module.view.LoginView;
import live.mvplive.ui.activity.MainActivity;

/**
 * Created by Administrator on 2017/10/24 0024.
 * <p>
 * 登录
 */
@ContentView(R.layout.loginactivity)
public class LoginActivity extends Activity implements LoginView {


    @ViewInject(R.id.phone)
    private EditText phone;

    @ViewInject(R.id.code)
    private EditText code;

    @ViewInject(R.id.get_code)
    private TextView get_code;

    @ViewInject(R.id.login)
    private TextView login;

    @ViewInject(R.id.progress)
    private ProgressBar progressBar;
    @ViewInject(R.id.LoginLogo)
    private ImageView LoginLogo;

    //P 功能处理
    private LoginPresenter loginPresenter;
    //Mob短信验证
    private EventHandler eventHandler;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        login.setOnClickListener(lister);
        get_code.setOnClickListener(lister);

        LoginLogo.setImageResource(R.drawable.welcom);
        loginPresenter = new LoginPresenter(this);
        // 如果希望在读取通信录的时候提示用户，可以添加下面的代码，并且必须在其他代码调用之前，否则不起作用；如果没这个需求，可以不加这行代码
        // SMSSDK.setAskPermisionOnReadContact(true);
        // 创建EventHandler对象
        eventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {

                Log.e("data.toString()", data.toString());
                get_code.setOnClickListener(null);
                if (data instanceof Throwable) {
                    Throwable throwable = (Throwable) data;
                    final String msg = throwable.getMessage();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.e("msg,msg", msg);
                            try {
                                JSONObject jsonObject = new JSONObject(msg);
                                Toast.makeText(LoginActivity.this, jsonObject.getString("detail"), Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                } else {
                    Log.e("event_false", event + "");
                    if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {//2
                        // 处理你自己的逻辑
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this, "验证码发送成功", Toast.LENGTH_SHORT).show();

                                loginPresenter.CountDown();
                            }
                        });

                    }
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.e("验证成功", "验证成功");
                                loginPresenter.login();
                            }
                        });

                    }
                }
            }
        };
        // 获取短信验证码 注册监听器
        SMSSDK.registerEventHandler(eventHandler);
    }

    android.view.View.OnClickListener lister = new android.view.View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.get_code:
                    loginPresenter.pHone_Card();
                    break;

                case R.id.login:
                    SMSSDK.submitVerificationCode("86", phone.getText().toString(), code.getText().toString());
                    break;

                default:
            }
        }
    };


    @Override
    public String GetPhone() {
        //获取输入的手机号
        return phone.getText().toString();
    }

    @Override
    public String GetCode() {
        //获取输入的验证码
        return code.getText().toString();
    }

    @Override
    public void ShowLoading() {
        progressBar.setVisibility(View.VISIBLE);//打开加载对话框
    }

    @Override
    public void HideLoading() {
        progressBar.setVisibility(View.GONE);//关闭加载对话框
    }

    @Override
    public void Success(LoginUser loginUser) {
        Toast.makeText(LoginActivity.this, loginUser.getUserPhone() + "登录成功", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    @Override
    public void Fail(String s) {
        Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void CardSuccess() {
        //手机号格式正确
        SMSSDK.getVerificationCode("86", phone.getText().toString());

    }

    @Override
    public void CardFail() {
        //手机号格式错误
        Toast.makeText(LoginActivity.this, "手机号输入错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void CountDownStart(String s) {
        get_code.setText(s + "s");
    }

    @Override
    public void CountDownEnd() {
        get_code.setText("输入验证码");
        get_code.setOnClickListener(lister);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);//关闭
    }
}
