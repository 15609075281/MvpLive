package live.mvplive.module.presenter;


import android.os.Handler;
import java.util.Timer;
import live.mvplive.Bean.LoginUser;
import live.mvplive.module.model.Impl.LoginImplModel;
import live.mvplive.module.model.LoginModel;
import live.mvplive.module.view.LoginView;

/**
 * Created by Administrator on 2017/10/25 0025.
 * 登录的处理中心
 */

public class LoginPresenter {

    private LoginView loginView;
    private LoginModel loginModel;

    private Handler mHandler;
    private Timer timer;
    private boolean isTimerCancel;

    public LoginPresenter(LoginView loginView) {

        this.loginView = loginView;
        loginModel = new LoginImplModel();
        mHandler = new Handler();
        timer = new Timer();
        isTimerCancel = false;
    }

    /**
     * 验证手机号
     */
    public void pHone_Card() {
        loginModel.phone_card(loginView.GetPhone(), new LoginModel.OnLoginCodeLister() {
            @Override
            public void CardSuccess() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.CardSuccess();//手机号格式正确
                        //执行倒计时 刷新ui界面
                    }
                });
            }

            @Override
            public void CardFailed() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.CardFail();
                    }
                });
            }
        });
    }

    /**
     * 倒计时计时器
     */
    public void CountDown() {
        if (isTimerCancel == true)
            timer = new Timer();
        loginModel.CountDown(timer, new LoginModel.OnCountDownLister() {
            @Override
            public void Start(final String s) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.CountDownStart(s);
                    }
                });

            }

            @Override
            public void End() {
                if (!isTimerCancel) {
                    timer.cancel();
                    isTimerCancel = true;
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.CountDownEnd();
                    }
                });
            }
        });
    }


    /**
     * 执行登录的功能
     */
    public void login() {
        loginView.ShowLoading();
        loginModel.login(loginView.GetPhone(), loginView.GetCode(), new LoginModel.OnLoginLister() {
            @Override
            public void Success(final LoginUser loginUser) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.Success(loginUser);
                        loginView.HideLoading();
                    }
                });

            }

            @Override
            public void Failed(final String s) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.Fail(s);
                        loginView.HideLoading();
                    }
                });

            }
        });
    }


}
