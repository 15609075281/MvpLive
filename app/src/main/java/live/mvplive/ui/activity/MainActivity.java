package live.mvplive.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import live.mvplive.R;
import live.mvplive.adapter.MainFragmentAdapter;
import android.support.design.widget.TabLayout;

import com.jingewenku.abrahamcaijin.commonutil.AppToastMgr;

/**
 * Created by Administrator on 2017/10/31 0031.
 *  需求：创建首页的主体，作为支界面的主干
 *  HomeFragment(首页)  FragmentA  FragmentB   PersonFragment(个人主页)
 *
 * ViewPager+ TabLayout+Fragment
 */

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewInject(R.id.tabLayout)
    private TabLayout mTabLayout;
    @ViewInject(R.id.viewPager)
    private ViewPager mViewPager;

    private MainFragmentAdapter myFragmentPagerAdapter;

    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;
    private TabLayout.Tab four;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//隐藏掉整个ActionBar
        x.view().inject(this);
        //初始化视图
        initViews();
        AppToastMgr.ToastLongBottomLeft(getApplicationContext(),"我就看看是不是真的");

    }

    private void initViews() {
        //使用适配器将ViewPager与Fragment绑定在一起
        myFragmentPagerAdapter = new MainFragmentAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(myFragmentPagerAdapter);
        //将TabLayout与ViewPager绑定在一起
        mTabLayout.setupWithViewPager(mViewPager);
        //指定Tab的位置
        one = mTabLayout.getTabAt(0);
        two = mTabLayout.getTabAt(1);
        three = mTabLayout.getTabAt(2);
        four = mTabLayout.getTabAt(3);
        //设置Tab的图标，假如不需要则把下面的代码删去
        one.setIcon(R.mipmap.ic_launcher);
        two.setIcon(R.mipmap.ic_launcher);
        three.setIcon(R.mipmap.ic_launcher);
        four.setIcon(R.mipmap.ic_launcher);
    }




}
