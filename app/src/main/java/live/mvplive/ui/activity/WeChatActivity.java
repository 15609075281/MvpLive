package live.mvplive.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;

import java.util.HashMap;

import live.mvplive.wxapi.WXPayEntryActivity;

/**
 * Created by Administrator on 2017/11/30.
 */

public class WeChatActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    /**
     * 微信支付
     * 传入商品价格和  商品名称给后台，由后台完成加密和请求操作，返回给前端，在请求微信支付完成支付
     */
    private void wh(   ) {

        final IWXAPI mWxApi = WXAPIFactory.createWXAPI(getApplicationContext(), null);
        mWxApi.registerApp(WXPayEntryActivity.APP_ID);
        if (mWxApi != null) {
            PayReq req = new PayReq();
            req.appId = WXPayEntryActivity.APP_ID;// 微信开放平台审核通过的应用APPID

            try {
                JSONObject jsonObject = new JSONObject("后台返回的数据");
                if (jsonObject.getString("msg").equals("200")) {
                    JSONObject jsonObject1 = new JSONObject(jsonObject.getString("result"));

                    req.appId = jsonObject1.getString("appid");
                    req.partnerId = jsonObject1.getString("partnerid");// 微信支付分配的商户号
                    req.prepayId = jsonObject1.getString("prepayid");
                    req.nonceStr = jsonObject1.getString("noncestr");
                    req.timeStamp = jsonObject1.getString("timestamp");
                    req.packageValue = jsonObject1.getString("package");
                    req.sign = jsonObject1.getString("sign");

                    mWxApi.sendReq(req);

                } else {
                    //消息接收失败
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


    public void    start_wpay(){

        final IWXAPI mWxApi = WXAPIFactory.createWXAPI(getApplicationContext(), null);
        mWxApi.registerApp(WXPayEntryActivity.APP_ID);

        PayReq request = new PayReq();
        request.appId = "wxd930ea5d5a258f4f";
        request.partnerId = "1900000109";
        request.prepayId= "1101000000140415649af9fc314aa427";
        request.packageValue = "Sign=WXPay";
        request.nonceStr= "1101000000140429eb40476f8896f4c9";
        request.timeStamp= "1398746574";
        request.sign= "7FFECB600D7157C5AA49810D2D8F28BC2811827B";
        mWxApi.sendReq(request);
    }




}
