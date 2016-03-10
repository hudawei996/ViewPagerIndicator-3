package com.jthou.viewpager;

import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 
 * @author jthou
 * @createdate 2016-3-8 下午6:31:00
 * @Description: ViewPager指示器
 */
public class ViewPagerIndicator extends LinearLayout implements OnClickListener {

	/**
	 * 绘制指示器(三角形)的画笔
	 */
	private Paint mPaint;
	
	/**
	 * 用于绘制指示器(三角形)的path
	 */
	private Path mPath;

	/**
	 * 刚开始的时候三角形的偏移量
	 */
	private int mInitTranstanleX;
	
	/**
	 * 在移动过程中的偏移量
	 */
	private int mTransTanleX;

	/**
	 * 绘制的三角形的宽度
	 */
	private int mTriangleWidth;
	
	/**
	 * 绘制的三角形的宽度
	 */
	private int mTriangleHeight;

	/**
	 * 屏幕宽度
	 */
	private int mScreentWidth;

	/**
	 * 显示的tab数量
	 */
	private int mVisibleTabCount;

	/**
	 * 默认显示tab个数
	 */
	private static final int DEFAULT_VISIBLE_TAB_COUNT = 4;

	/**
	 * 默认指示器宽度，屏幕宽度的18/1
	 */
	private static final float DEFAULT_INDICATOR_WIDTH = 1 / 18f;
	
	/**
	 * 默认字体颜色
	 */
	private static final int TEXT_NORMAL_COLOR = 0x77FFFFFF;
	
	/**
	 * 选中tab字体颜色
	 */
	private static final int TEXT_SELECT_COLOR = 0xFFFFFFFF;

	private List<String> mTitles;

	private ViewPager mViewPager;
	
	public ViewPagerIndicator(Context context) {
		this(context, null);
	}

	public ViewPagerIndicator(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ViewPagerIndicator(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ViewPagerIndicator);

		mVisibleTabCount = a.getInteger(R.styleable.ViewPagerIndicator_visible_tab_count, DEFAULT_VISIBLE_TAB_COUNT);
		if (mVisibleTabCount <= 0)
			mVisibleTabCount = DEFAULT_VISIBLE_TAB_COUNT;

		a.recycle();

		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		mScreentWidth = outMetrics.widthPixels;

		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setStyle(Style.FILL);
		mPaint.setPathEffect(new CornerPathEffect(3));
		mPaint.setColor(Color.WHITE);

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		// 要绘制的三角形宽度
		mTriangleWidth = (int) (w * DEFAULT_INDICATOR_WIDTH);
		mTriangleHeight = mTriangleWidth / 2;
		mInitTranstanleX = w / mVisibleTabCount / 2 - mTriangleWidth / 2;

		mPath = new Path();
		mPath.moveTo(0, 0);
		mPath.lineTo(mTriangleWidth, 0);
		mPath.lineTo(mTriangleWidth / 2, -mTriangleHeight);
		mPath.close();

	}

	@Override
	protected void onFinishInflate() {
		int count = getChildCount();
		if (count > mVisibleTabCount) {
			for (int i = 0; i < count; i++) {
				View child = getChildAt(i);
				LayoutParams params = (LayoutParams) child.getLayoutParams();
				params.weight = 0;
				// 设置每个child的宽度是屏幕宽度除以默认显示子控件个数
				params.width = mScreentWidth / mVisibleTabCount;
				child.setLayoutParams(params);
			}
		}
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {

		canvas.save();

		canvas.translate(mInitTranstanleX + mTransTanleX, getHeight() + 2);
		canvas.drawPath(mPath, mPaint);

		canvas.restore();

		super.dispatchDraw(canvas);

	}

	public void scroll(int position, float positionOffset) {
		mTransTanleX = (int) ((mScreentWidth / mVisibleTabCount) * (position + positionOffset));
		invalidate();

		if (getChildCount() > 4 && position >= (mVisibleTabCount - 2) && positionOffset > 0) {
			if (mVisibleTabCount == 1) {
				int x = (int) (mScreentWidth * (position + positionOffset));
				scrollTo(x, 0);
			} else {
				// mScreentWidth / (mVisibleTabCount) 表示 每个tab的宽度
				// int x = (int) ((mScreentWidth / mVisibleTabCount) * (position
				// -
				// (mVisibleTabCount - 2)) + (mScreentWidth / mVisibleTabCount)
				// *
				// positionOffset);
				// 提取公因式(mScreentWidth / mVisibleTabCount)
				int x = (int) ((mScreentWidth / mVisibleTabCount) * (position - (mVisibleTabCount - 2) + positionOffset));
				scrollTo(x, 0);
			}
		}
	}

	public void setTabTitles(List<String> titles) {
		mTitles = titles;
		for (String title : titles) {
			addView(generateTextView(title));
		}
	}

	private View generateTextView(String title) {
		TextView tv = new TextView(getContext());
		tv.setText(title);
		tv.setTextColor(TEXT_NORMAL_COLOR);
		tv.setGravity(Gravity.CENTER);
		tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
		LayoutParams params = new LayoutParams(mScreentWidth / mVisibleTabCount, LayoutParams.MATCH_PARENT);
		params.weight = 0;
		tv.setLayoutParams(params);
		tv.setOnClickListener(this);
		tv.setTag(title);
		return tv;
	}

	/**
	 * 
	 * @author jthou
	 * @createdate 2016-3-8 下午5:46:43
	 * @Description: 设置tab标题
	 * @param id
	 * 
	 */
	public void setTabTitles(int id) {
		String[] titles = getResources().getStringArray(id);
		setTabTitles(Arrays.asList(titles));
	}

	@Override
	public void onClick(View v) {
		String tag = (String) v.getTag();
		int position = mTitles.indexOf(tag);
		if (position >= 0) {
			scroll(position, 0);
			this.mViewPager.setCurrentItem(position);
		}
	}

	public void setVisibleCount(int count) {
		this.mVisibleTabCount = count;
	}

	public interface OnPageChangeListener {

		public void onPageScrolled(int arg0, float arg1, int arg2);

		public void onPageSelected(int arg0);

		public void onPageScrollStateChanged(int arg0);

	}

	public OnPageChangeListener mListener;

	public void setOnPageChangeListener(OnPageChangeListener mListener) {
		this.mListener = mListener;
	}

	public void setViewPager(ViewPager vp, int position) {
		this.mViewPager = vp;
		this.mViewPager.setOnPageChangeListener(new android.support.v4.view.ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				if (mListener != null)
					mListener.onPageSelected(position);
				setHighLightTextColor(position);
			}

			@Override
			public void onPageScrolled(int position, float positionOffset, int arg2) {
				scroll(position, positionOffset);
				if (mListener != null)
					mListener.onPageScrolled(position, positionOffset, arg2);
				;
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				if (mListener != null)
					mListener.onPageScrollStateChanged(arg0);
			}
		});
		this.mViewPager.setCurrentItem(position);
		setHighLightTextColor(position);
	}
	
	/**
	 * 
	 * @author jthou
	 * @createdate 2016-3-8 下午6:25:07
	 * @Description: 重置所有的字体颜色
	 *
	 */
	private void resetTextColor() {
		final int count = getChildCount();
		for(int i=0;i<count;i++) {
			View child = getChildAt(i);
			if(child instanceof TextView) {
				((TextView)child).setTextColor(TEXT_NORMAL_COLOR);
			}
		}
	}
	
	/**
	 * 
	 * @author jthou
	 * @createdate 2016-3-8 下午6:26:58
	 * @Description: 设置高亮字体颜色
	 * @param position
	 *
	 */
	private void setHighLightTextColor(int position) {
		resetTextColor();
		View child = getChildAt(position);
		if(child instanceof TextView) {
			((TextView)child).setTextColor(TEXT_SELECT_COLOR);
		}
	}
	

}
