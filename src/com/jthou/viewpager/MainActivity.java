package com.jthou.viewpager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Window;

public class MainActivity extends FragmentActivity {

	private ViewPager mViewPager;
	private ViewPagerIndicator mIndicator;

	private List<String> mTitles = Arrays.asList("短信", "推荐", "收藏", "iphone", "ipod", "ipad", "macbook", "iwatch", "kindle");

	private List<Fragment> mFragments = new ArrayList<Fragment>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		mIndicator = (ViewPagerIndicator) findViewById(R.id.id_indicator);
		mIndicator.setTabTitles(R.array.titles);
		mViewPager = (ViewPager) findViewById(R.id.id_viewPager);
		for (String title : mTitles) {
			Fragment fragment = JthouFragment.newInstance(title);
			mFragments.add(fragment);
		}

		mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

			@Override
			public int getCount() {
				return mFragments.size();
			}

			@Override
			public Fragment getItem(int arg0) {
				return mFragments.get(arg0);
			}
		});
		mIndicator.setViewPager(mViewPager, 0);

	}

}
