package live.mvplive.module.model;

import java.util.Timer;

import live.mvplive.Bean.LoginUser;

/**
 * Created by Administrator on 2017/10/24 0024.
 */

public interface LoginModel {

    /**
     * 登录验证
     *
     * @param UserName
     * @param Code
     * @param onLoginLister
     */
    void login(String UserName, String Code, OnLoginLister onLoginLister);

    interface OnLoginLister {

        void Success(LoginUser loginUser);

        void Failed(String s);

    }

    /**
     * 验证手机号格式是否正确
     *
     * @param phone
     * @param onLoginCodeLister
     */
    void phone_card(String phone, OnLoginCodeLister onLoginCodeLister);

    interface OnLoginCodeLister {

        void CardSuccess();

        void CardFailed();
    }

    /**
     * 倒计时
     */
    void CountDown(Timer timer, OnCountDownLister onCountDownLister);

    interface OnCountDownLister {

        void Start(String s);

        void End( );
    }

}
