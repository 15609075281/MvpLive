package live.mvplive.module.model.Impl;

import android.os.Message;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import live.mvplive.Bean.LoginUser;
import live.mvplive.module.model.LoginModel;

/**
 * Created by Administrator on 2017/10/24 0024.
 */

public class LoginImplModel implements LoginModel {

    /**
     * 手机号格式验证
     *
     * @param phone
     * @param onLoginCodeLister
     */
    @Override
    public void phone_card(final String phone, final OnLoginCodeLister onLoginCodeLister) {

        new Thread() {
            @Override
            public void run() {
                super.run();
                if (isChinaPhoneLegal(phone)) {
                    onLoginCodeLister.CardSuccess();
                } else {
                    onLoginCodeLister.CardFailed();
                }

            }
        }.start();

    }

    /**
     * 倒计时
     *
     * @param timer
     * @param onCountDownLister
     */
    @Override
    public void CountDown(Timer timer, OnCountDownLister onCountDownLister) {
        timer1(timer, onCountDownLister);
    }

    /**
     * 网络 登录验证
     *
     * @param UserName
     * @param Code
     * @param onLoginLister
     */
    @Override
    public void login(final String UserName, final String Code, final OnLoginLister onLoginLister) {

        http(UserName, Code, onLoginLister);//通过请求网络返回执行结果

    }


    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除1和4的任意数
     * 17+除9的任意数
     * 147
     */
    public boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 网络延迟请求返回对应结果
     */

    public void http(final String UserName, final String Code, final OnLoginLister onLoginLister) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(2000);//延迟网络操作
                    if (UserName.isEmpty() == false && Code.isEmpty() == false) {
                        onLoginLister.Success(new LoginUser(UserName, Code));
                    } else {
                        onLoginLister.Failed("验证码错误");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }


    /**
     * 倒计时
     */
    public void timer1(Timer timer, final OnCountDownLister onCountDownLister) {
        //min 1分钟
        int min = 1;
        long start = System.currentTimeMillis();
        //end 计算结束时间
        final long end = start + min * 60 * 1000;
        //延迟0毫秒（即立即执行）开始，每隔1000毫秒执行一次
        timer.schedule(new TimerTask() {
            public void run() {
                //show是剩余时间，即要显示的时间
                long show = end - System.currentTimeMillis();
                long h = show / 1000 / 60 / 60;//时
                long m = show / 1000 / 60 % 60;//分
                long seconds = show / 1000 % 60;//秒
                onCountDownLister.Start(String.valueOf(seconds));
            }
        }, 0, 1000);
        //计时结束时候，停止全部timer计时计划任务
        timer.schedule(new TimerTask() {
            public void run() {
                onCountDownLister.End();
            }
        }, new Date(end));
    }

}
