package live.mvplive.ui.fragment.dialogfragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import live.mvplive.R;

/**
 * Created by Administrator on 2017/11/3 0003.
 */
@ContentView(R.layout.dialog_fragment_xml)
public class DialogFragment_two extends DialogFragment  {

    @ViewInject(R.id.t1)
    TextView textView;


    View view;

    Dialog dialog;

    public static DialogFragment_two new_fragment() {
        DialogFragment_two person_head_lActivity = new DialogFragment_two();
        Bundle build = new Bundle();
        person_head_lActivity.setArguments(build);
        return person_head_lActivity;
    }

    private void get_Intent(View view) {
        dialog = new Dialog(getActivity(), R.style.AppTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消
        // 设置宽度为屏宽, 靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
    }


//    @NonNull
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_fragment_xml, null, false);
//        get_Intent(view);
//        TextView textView = (TextView) view.findViewById(R.id.t1);
//        textView.setText("dialog_1");
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
//        return dialog;
//
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(DialogFragment_two.this, inflater, container);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        get_Intent(getView());
        textView.setText("dialog_1");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.putExtra("one","哈哈哈哈");
                getTargetFragment().onActivityResult(2000,2000,intent);
//                dialog.dismiss();
            }
        });

    }


}
