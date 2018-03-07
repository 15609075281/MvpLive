package live.mvplive.ui.fragment.first_layer;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaochen.progressroundbutton.AnimDownloadProgressButton;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;
import java.util.Locale;
import cn.addapp.pickers.common.LineConfig;
import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.picker.SinglePicker;
import live.mvplive.R;

/**
 * Created by Administrator on 2017/11/2 0002.
 *  建成一个空的 class
 *   通过点击实现选择器
 */
@ContentView(R.layout.fragment_b_xml)
public class FragmentB extends Fragment {

    @ViewInject(R.id.fb1)
    TextView fb1;
    @ViewInject(R.id.fb2)
    TextView fb2;
    @ViewInject(R.id.anim_btn)
    AnimDownloadProgressButton anim_btn;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(FragmentB.this, inflater, container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        get_down();

        fb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onConstellationPicker();
            }
        });
    }

    public  void  get_down(){
        anim_btn.setCurrentText("安装");
        anim_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTheButton(R.id.anim_btn);
            }
        });

        anim_btn.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                Log.e("hasFocushasFocus",""+hasFocus);
                    fb2.setText("fffffffff");
                }else {
                    Log.e("hasFocushasFocus",""+hasFocus);
                    fb2.setText("222222222");
                }
            }
        });
    }
    private void showTheButton(int id) {
        switch (id) {
            case R.id.anim_btn:
                anim_btn.setState(AnimDownloadProgressButton.DOWNLOADING);
                //刷新ui界面数据
                anim_btn.setProgressText("下载中", anim_btn.getProgress() + 8);
                //当前进度
                if (anim_btn.getProgress() + 10 > 100) {
                    anim_btn.setState(AnimDownloadProgressButton.INSTALLING);
                    anim_btn.setCurrentText("安装中");
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            anim_btn.setState(AnimDownloadProgressButton.NORMAL);
                            anim_btn.setCurrentText("打开");
                        }
                    }, 1000);   //2秒
                }
                break;
        }
    }


    public void onConstellationPicker() {
        boolean isChinese = Locale.getDefault().getDisplayLanguage().contains("中文");
        SinglePicker<String> picker = new SinglePicker<>(getActivity(),
                isChinese ? new String[]{
                        "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座",
                        "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座"
                } : new String[]{
                        "Aquarius", "Pisces", "Aries", "Taurus", "Gemini", "Cancer",
                        "Leo", "Virgo", "Libra", "Scorpio", "Sagittarius", "Capricorn"
                });
        picker.setCanLoop(true);//禁用循环
        picker.setTopBackgroundColor(0xFFEEEEEE);
        picker.setTopHeight(50);
        picker.setTopLineColor(0xFF33B5E5);
        picker.setTopLineHeight(1);
        picker.setTitleText(isChinese ? "请选择" : "Please pick");
        picker.setTitleTextColor(0xFF999999);
        picker.setTitleTextSize(12);
        picker.setCancelTextColor(0xFF33B5E5);
        picker.setCancelTextSize(13);
        picker.setSubmitTextColor(0xFF33B5E5);
        picker.setSubmitTextSize(13);
        picker.setSelectedTextColor(0xFF999999);
        picker.setUnSelectedTextColor(0xFF999999);
        LineConfig config = new LineConfig();
        config.setColor(0xFFEE0000);//线颜色
        config.setAlpha(140);//线透明度
        config.setRatio((float) (1.0 / 8.0));//线比率
        picker.setLineConfig(config);
        picker.setItemWidth(200);
        picker.setBackgroundColor(0xFFE1E1E1);
        //picker.setSelectedItem(isChinese ? "处女座" : "Virgo");
        picker.setSelectedIndex(6);
        picker.setOnItemPickListener(new OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int index, String item) {

                Toast.makeText(getContext(), "index=" + index + ", item=" + item.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        picker.show();
    }


}
