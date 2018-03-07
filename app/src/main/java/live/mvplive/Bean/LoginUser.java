package live.mvplive.Bean;

/**
 * Created by Administrator on 2017/10/24 0024.
 * <p>
 * <p>
 * 登录 user
 */

public class LoginUser {

    private String UserPhone;//用户手机号
    private String Code;//验证码

    public LoginUser() {

    }

    public LoginUser(String P, String C) {
        this.UserPhone = P;
        this.Code = C;
    }

    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "UserPhone='" + UserPhone + '\'' +
                ", Code='" + Code + '\'' +
                '}';
    }
}
