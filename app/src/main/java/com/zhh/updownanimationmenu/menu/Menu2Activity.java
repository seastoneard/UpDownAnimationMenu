package com.zhh.updownanimationmenu.menu;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;
import com.zhh.updownanimationmenu.R;
import com.zhh.updownanimationmenu.adapter.MyPagerAdapter;
import com.zhh.updownanimationmenu.fragment.FourFragment;
import com.zhh.updownanimationmenu.fragment.MainFragment;
import com.zhh.updownanimationmenu.fragment.ThreeFragment;
import com.zhh.updownanimationmenu.fragment.TwoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhh on 2017/3/31.
 */

public class Menu2Activity extends AppCompatActivity {

    private BottomBar mBottomBar;
    private MainFragment mMainFragment;
    private TwoFragment twoFragment;
    private ThreeFragment threeFragment;
    private FourFragment fourFragment;
    private FragmentTransaction transaction;
    private ViewPager viewPager;
    private CoordinatorLayout mCoordinator;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottombar_viewpager_layout);
        mCoordinator = (CoordinatorLayout) findViewById(R.id.mCoordinator);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        initViewPager();
        mBottomBar = BottomBar.attachShy(mCoordinator, viewPager, savedInstanceState);
        mBottomBar.setItems(R.menu.bottombar_menu);
        mBottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                //单击事件 menuItemId 是 R.menu.bottombar_menu 中 item 的 id
                switch (menuItemId) {
                    case R.id.bb_menu_main:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.bb_menu_chart:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.bb_menu_timeline:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.bb_menu_about:
                        viewPager.setCurrentItem(3);
                        break;
                    default:
                        break;

                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                //重选事件，当前已经选择了这个，又点了这个tab。微博点击首页刷新页面
            }
        });
        // 当点击不同按钮的时候，设置不同的颜色
        // 可以用以下三种方式来设置颜色.
        mBottomBar.mapColorForTab(0, ContextCompat.getColor(this, R.color.colorPrimary));
        mBottomBar.mapColorForTab(1, ContextCompat.getColor(this, R.color.colorAccent));
        mBottomBar.mapColorForTab(2, ContextCompat.getColor(this, R.color.green));
        mBottomBar.mapColorForTab(3, ContextCompat.getColor(this, R.color.orange));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //保存BottomBar的状态
        mBottomBar.onSaveInstanceState(outState);
    }


    private void initViewPager() {
        fragmentList = new ArrayList<>();
        fragmentList.add(null == mMainFragment ? mMainFragment = MainFragment.newInstance() : mMainFragment);
        fragmentList.add(null == twoFragment ? twoFragment = TwoFragment.newInstance() : twoFragment);
        fragmentList.add(null == threeFragment ? threeFragment = ThreeFragment.newInstance() : threeFragment);
        fragmentList.add(null == fourFragment ? fourFragment = FourFragment.newInstance() : fourFragment);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), fragmentList));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBottomBar.selectTabAtPosition(position, true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
