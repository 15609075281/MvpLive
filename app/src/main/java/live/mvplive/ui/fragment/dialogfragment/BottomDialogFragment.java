package live.mvplive.ui.fragment.dialogfragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import live.mvplive.R;

/**
 * Created by Administrator on 2017/11/16.
 */
public class BottomDialogFragment extends DialogFragment {

    View view = null;
    Dialog dialog;
    ListView listView;
    String[] sss = new String[]{"第一个", "第一个", "第一个", "第一个", "第一个", "第一个", "第一个"};
    List<String> list = new ArrayList<>();

    public static BottomDialogFragment getIntent() {
        BottomDialogFragment dialogFragment = new BottomDialogFragment();
        Bundle bundle = new Bundle();
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }


    private void get_Intent(View view) {
        dialog = new Dialog(getActivity(), R.style.AppTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消

//        Window dialogWindow = dialog.getWindow();
//        dialogWindow.setGravity(Gravity.BOTTOM);
//        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
//        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
//        lp.x = 0; // 新位置X坐标
//        lp.y = -20; // 新位置Y坐标
//        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
////      lp.height = WindowManager.LayoutParams.WRAP_CONTENT; // 高度
////      lp.alpha = 9f; // 透明度
//        view.measure(0, 0);
//        lp.height = view.getMeasuredHeight();
//        lp.alpha = 9f; // 透明度
//        dialogWindow.setAttributes(lp);
//        dialog.show();


        // 设置宽度为屏宽, 靠近屏幕底部。
        Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (view == null)
            view = LayoutInflater.from(getContext()).inflate(R.layout.bottomd_dialog_fragment_xml, null, false);

        get_Intent(view);

        listView = (ListView) view.findViewById(R.id.list_bottom);
        for (int x = 0; x < sss.length; x++) {
            list.add(sss[x]);
        }
        A_adapter a_adapter = new A_adapter();
        listView.setAdapter(a_adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(), "点击的是" + i + list.get(i), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        return dialog;
    }

    class A_adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View cv, ViewGroup viewGroup) {
            vd c = null;
            if (cv == null) {
                cv = LayoutInflater.from(getContext()).inflate(R.layout.item_list, null);
                c = new vd();
                c.textView = (TextView) cv.findViewById(R.id.item_t1);
                cv.setTag(c);
            } else {
                c = (vd) cv.getTag();
            }
            c.textView.setText(list.get(i));
            return cv;
        }

        class vd {
            TextView textView;
        }

    }


}
