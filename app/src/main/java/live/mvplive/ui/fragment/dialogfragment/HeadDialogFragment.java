package live.mvplive.ui.fragment.dialogfragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;
import live.mvplive.R;
import live.mvplive.ui.activity.main.Live_for_activity_util;

/**
 * Created by Administrator on 2017/11/17.
 */
@ContentView(R.layout.head_dialog_fragment)
public class HeadDialogFragment extends Live_for_activity_util {

    View view;
    Dialog dialog;
    Handler handler;
    @ViewInject(R.id.head)
    ImageView head;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        handler = new Handler();
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                The_Person_Head_lActivity headDialogFragment = The_Person_Head_lActivity.new_fragment();
                headDialogFragment.show(getSupportFragmentManager(), "head_d");
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("ssssssssssssssss1", requestCode + "");
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("ssssssssssssssss", requestCode + "");
        Log.e("ssssssssssssssss",  getIntent().getStringExtra("head") + "");

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
}
