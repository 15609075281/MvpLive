package live.mvplive.module.view;

import live.mvplive.Bean.LoginUser;

/**
 * Created by Administrator on 2017/10/25 0025.
 */

public interface LoginView {

    String GetPhone();//获取输入的手机号

    String GetCode();//获取输入的验证码

    void ShowLoading();//显示加载对话框

    void HideLoading();//关闭加载对话框

    void Success(LoginUser loginUser);//请求获得成功结果

    void Fail(String s);//请求获得失败结果

    void CardSuccess();//验证是否是正确手机号正确

    void CardFail();//验证是否是正确手机号失败

    void CountDownStart(String s);//倒计时进行时

    void CountDownEnd( );//倒计时结束


}
