package com.example.android.viewpagerexample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

/**
 * Created by TEXHOKPAT on 3/24/2016.
 */
public class CustomPagerAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = CustomPagerAdapter.class.getSimpleName();

    public CustomPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Return the Fragment associated with a specified position.
     */
    @Override
    public Fragment getItem(int position) {

        Log.i(TAG, "getItem: " + (position + 1));

        Fragment fragment = new SlidePageSupportFragment();

         // option 1 - pass position as parameter
        ((SlidePageSupportFragment) fragment).setPageNumber(position);

        // option 2 -  pass position in a bundle
        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);

        return fragment;
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return 3;
    }
}

