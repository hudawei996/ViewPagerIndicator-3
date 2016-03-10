package com.jthou.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class JthouFragment extends Fragment {

	private static final String KEY_TITLE = "key_title";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Bundle bundle = getArguments();
		String title = bundle.getString(KEY_TITLE);
		TextView tv = new TextView(getActivity());
		tv.setText(title);
		tv.setGravity(Gravity.CENTER);
		return tv;
	}

	public static Fragment newInstance(String title) {
		Bundle bundle = new Bundle();
		bundle.putString(KEY_TITLE, title);
		JthouFragment fragment = new JthouFragment();
		fragment.setArguments(bundle);
		return fragment;
	}

}
