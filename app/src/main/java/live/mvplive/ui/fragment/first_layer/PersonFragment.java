package live.mvplive.ui.fragment.first_layer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import live.mvplive.R;
import live.mvplive.ui.fragment.dialogfragment.BottomDialogFragment;
import live.mvplive.ui.fragment.dialogfragment.HeadDialogFragment;

/**
 * Created by Administrator on 2017/11/2 0002.
 * <p>
 * 第四页   我的中心
 */
@ContentView(R.layout.person_fragment_xml)
public class PersonFragment extends Fragment {

    @ViewInject(R.id.click)
    TextView textView;
    @ViewInject(R.id.click1)
    TextView textView1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(PersonFragment.this, inflater, container);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomDialogFragment.getIntent().show(getChildFragmentManager(), "dialog_");
            }
        });
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), HeadDialogFragment.class));
            }
        });
    }
}
