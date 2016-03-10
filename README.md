# ViewPagerIndicator
ViewPager指示器

# Usage
<com.jthou.viewpager.ViewPagerIndicator
        android:id="@+id/id_indicator"
        android:layout_width="match_parent"
        android:layout_height="45dp"
        android:background="@android:color/black"
        android:orientation="horizontal"
        jthou:visible_tab_count="3" >

        <TextView
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:gravity="center"
            android:text="短信"
            android:textColor="@android:color/white"
            android:textSize="14sp"
            android:width="0dp" />

        <TextView
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:gravity="center"
            android:text="推荐"
            android:textColor="@android:color/white"
            android:textSize="14sp" />

        <TextView
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:gravity="center"
            android:text="收藏"
            android:textColor="@android:color/white"
            android:textSize="14sp" />

        <TextView
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:gravity="center"
            android:text="iphone"
            android:textColor="@android:color/white"
            android:textSize="14sp"
            android:width="0dp" />

        <TextView
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:gravity="center"
            android:text="ipod"
            android:textColor="@android:color/white"
            android:textSize="14sp" />

        <TextView
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:gravity="center"
            android:text="ipad"
            android:textColor="@android:color/white"
            android:textSize="14sp" />

        <TextView
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:gravity="center"
            android:text="macbook"
            android:textColor="@android:color/white"
            android:textSize="14sp"
            android:width="0dp" />

        <TextView
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:gravity="center"
            android:text="iwatch"
            android:textColor="@android:color/white"
            android:textSize="14sp" />

        <TextView
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:gravity="center"
            android:text="kindle"
            android:textColor="@android:color/white"
            android:textSize="14sp" />
    </com.jthou.viewpager.ViewPagerIndicator>
    
    mIndicator = (ViewPagerIndicator) findViewById(R.id.id_indicator);
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
		
		<com.jthou.viewpager.ViewPagerIndicator
        android:id="@+id/id_indicator"
        android:layout_width="match_parent"
        android:layout_height="45dp"
        android:background="@android:color/black"
        android:orientation="horizontal"
        jthou:visible_tab_count="3" >
    </com.jthou.viewpager.ViewPagerIndicator>
    
    mIndicator = (ViewPagerIndicator) findViewById(R.id.id_indicator);
		mIndicator.setTabTitles(R.array.titles);
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
		
		<string-array name="titles" >
        <item>短信</item>
        <item>推荐</item>
        <item>收藏</item>
        <item>iphone</item>
        <item>ipod</item>
        <item>ipad</item>
        <item>macbook</item>
        <item>iwatch</item>
        <item>kindle</item>
    </string-array>
    
# XML attributes
    <declare-styleable name="ViewPagerIndicator">
    <attr name="visible_tab_count" format="integer" />
    </declare-styleable>
