package live.mvplive.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by Administrator on 2017/6/29 0029.
 */

public class WXPayEntryActivity extends FragmentActivity implements IWXAPIEventHandler {

    private static final String TAG = " .WXPayEntryActivity";

    private IWXAPI msgApi;
    public static final String APP_ID = "放入app_id";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        msgApi = WXAPIFactory.createWXAPI(this, APP_ID);
        msgApi.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        msgApi.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    /**
     * 得到支付结果回调
     * 0	成功	展示成功页面
     * -1	错误	可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等。
     * -2	用户取消	无需处理。发生场景：用户不支付了，点击取消，返回APP。
     */
    @Override
    public void onResp(BaseResp baseResp) {

    }


}