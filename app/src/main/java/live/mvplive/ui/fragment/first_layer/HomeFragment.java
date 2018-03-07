package live.mvplive.ui.fragment.first_layer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import live.mvplive.R;
import live.mvplive.ui.util.BannerLayout;
import live.mvplive.ui.util.PicassoImageLoader;

/**
 * Created by Administrator on 2017/10/31 0031.
 * 第一页 首页
 */
@ContentView(R.layout.home_fragment_xml)
public class HomeFragment extends Fragment {

    @ViewInject(R.id.banner)
    BannerLayout bannerLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return x.view().inject(HomeFragment.this, inflater, container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final List<String> urls = new ArrayList<>();
        urls.add("http://img3.imgtn.bdimg.com/it/u=2674591031,2960331950&fm=23&gp=0.jpg");
        urls.add("http://img5.imgtn.bdimg.com/it/u=3639664762,1380171059&fm=23&gp=0.jpg");
        urls.add("http://img0.imgtn.bdimg.com/it/u=1095909580,3513610062&fm=23&gp=0.jpg");
        urls.add("http://img4.imgtn.bdimg.com/it/u=1030604573,1579640549&fm=23&gp=0.jpg");
        urls.add("http://img5.imgtn.bdimg.com/it/u=2583054979,2860372508&fm=23&gp=0.jpg");
        bannerLayout.setImageLoader(new PicassoImageLoader());
        bannerLayout.setViewUrls(urls);
        //添加监听事件
        bannerLayout.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });

        //低于三张
//        final List<String> urls2 = new ArrayList<>();
//        urls2.add("http://img3.imgtn.bdimg.com/it/u=2674591031,2960331950&fm=23&gp=0.jpg");
//        urls2.add("http://img5.imgtn.bdimg.com/it/u=3639664762,1380171059&fm=23&gp=0.jpg");
//        bannerLayout2.setImageLoader(new PicassoImageLoader());
//        bannerLayout2.setViewUrls(urls2);
    }
}
