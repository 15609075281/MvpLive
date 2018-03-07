package live.mvplive.ui.fragment.popuwindow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import live.mvplive.R;

/**
 * Created by Administrator on 2017/12/11.
 */

public class PopuWindow_fragment extends AppCompatActivity {

    private TextView button;
    private PopupWindow popuwindow;
    private View view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popuwindow_xml);
        button = findViewById(R.id.text_butten);
        // 视图加载
        view = LayoutInflater.from(PopuWindow_fragment.this).inflate(R.layout.layout_popusindow, null);
        //设置长宽度
        popuwindow = new PopupWindow(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
         // 加载视图
        popuwindow.setContentView(view);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //显示视图
                popuwindow.showAsDropDown(button);
            }
        });

    }
}
