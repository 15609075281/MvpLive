package live.mvplive.ui.fragment.dialogfragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.jph.takephoto.app.TakePhotoFragment;
import com.jph.takephoto.app.TakePhotoFragmentActivity;

import live.mvplive.R;

/**
 * Created by Administrator on 2017/12/8.
 */

public class HeadDialogFragment1 extends DialogFragment   {

    Dialog dialog;

    View view;

    ImageView head_1;


    static void get_dialog() {
        HeadDialogFragment1 headDialogFragment1 = new HeadDialogFragment1();
        Bundle bundle = new Bundle();
        headDialogFragment1.setArguments(bundle);
    }

    private void get_Intent(View view) {
        dialog = new Dialog(getActivity(), R.style.AppTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false); // 外部点击取消
        // 设置宽度为屏宽, 靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        if (view == null)
            view = LayoutInflater.from(getContext()).inflate(R.layout.head_dialog_fragment1_xml, null, false);
        get_Intent(view);

        head_1 = view.findViewById(R.id.head_1);


        return dialog;
    }


}
