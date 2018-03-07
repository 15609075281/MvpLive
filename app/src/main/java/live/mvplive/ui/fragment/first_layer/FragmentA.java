package live.mvplive.ui.fragment.first_layer;

import android.content.Intent;
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

import com.jingewenku.abrahamcaijin.commonutil.AppKeyBoardMgr;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import live.mvplive.R;
import live.mvplive.ui.fragment.dialogfragment.DialogFragment_two;

/**
 * Created by Administrator on 2017/11/2 0002.
 * <p>
 * 第二个界面
 */
@ContentView(R.layout.fragment_a_xml)
public class FragmentA extends Fragment {


    @ViewInject(R.id.tt1)
    TextView tt1;
    @ViewInject(R.id.tt2)
    TextView tt2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(FragmentA.this, inflater, container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                DialogFragment_two.new_fragment().show(getChildFragmentManager(), "s");

                DialogFragment_two dialogFragment_two = DialogFragment_two.new_fragment();
//                dialogFragment_two.setTargetFragment(dialogFragment_two, 2000);
                dialogFragment_two.show(getChildFragmentManager(), "one");
            }
        });
        tt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getContext(),"dddddddddddddddddddddddddddddddd",Toast.LENGTH_SHORT).show();
                Log.e("mmmmmmmmmmmmmmmmm","dddddddddddddddddddddddddddddddd");
            }
        });

//        new Handler().post(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(2000);
//                    //View 可以是LinearLayout,Button,TextView
//                    tt2.performClick();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("sdaasdasdasdasdas", requestCode + "ffffffff"+resultCode);
    }
}