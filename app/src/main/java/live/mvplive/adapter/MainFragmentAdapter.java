package live.mvplive.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import live.mvplive.ui.fragment.first_layer.FragmentA;
import live.mvplive.ui.fragment.first_layer.FragmentB;
import live.mvplive.ui.fragment.first_layer.HomeFragment;
import live.mvplive.ui.fragment.first_layer.PersonFragment;

/**
 * Created by Administrator on 2017/11/2 0002.
 *
 *  主界面  控制器  adapter     试一下Git是否同步
 */

public class MainFragmentAdapter extends FragmentPagerAdapter {

    private String[] mTitles = new String[]{"首页", "社区", "消息", "我的"};

    public MainFragmentAdapter(FragmentManager fm) {
        super(fm);
    public MainFragmentAdapter(FragmentManager fragmentmanager) {
        super(fragmentmanager);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return new FragmentA();
        } else if (position == 2) {
            return new FragmentB();
        } else if (position == 3) {
            return new PersonFragment();
        }
        return new HomeFragment();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}