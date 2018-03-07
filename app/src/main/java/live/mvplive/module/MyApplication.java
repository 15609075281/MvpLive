package live.mvplive.module;

import android.app.Application;

import com.jingewenku.abrahamcaijin.commonutil.application.Utils;
import com.mob.MobApplication;
import com.squareup.leakcanary.BuildConfig;
import com.squareup.leakcanary.LeakCanary;

import org.xutils.x;

import cn.ittiger.player.Config;
import cn.ittiger.player.PlayerManager;
import cn.ittiger.player.factory.ExoPlayerFactory;

/**
 * Created by Administrator on 2017/10/24 0024.
 */

public class MyApplication extends MobApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        //多方位
        Utils.init(getApplicationContext());

        //leaks
        leaks();
        //xUtils
        xUtils();

    }

    /**
     * xUtils
     */
    public void xUtils() {
        //xUtils
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);// 是否输出debug日志, 开启debug会影响性能.
    }

    /**
     * leaks
     */
    public void leaks() {
        //leaks
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
    }
    /**
     *
     *直播
     *
     */

    public void vidod(){
        //该配置最好在Application中实现
        PlayerManager.loadConfig(
                new Config.Builder(this)
                        .buildPlayerFactory(new ExoPlayerFactory(this))//使用ExoPlayer内核作为视频播放器，默认使用MediaPlayer
                        .enableSmallWindowPlay()//开启小窗口播放，默认不开其
                        .cache(true)//开启缓存功能，默认不开启
                        .cacheProxy(null)//自定义缓存配置，不设置则采用默认的缓存配置
                        .build()
        );
    }
}
