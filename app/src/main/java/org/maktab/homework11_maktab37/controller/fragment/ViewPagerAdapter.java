package org.maktab.homework11_maktab37.controller.fragment;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

/*public class ViewPagerAdapter extends FragmentStateAdapter {
    int mNumOfTabs;
    Context mContext;

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, int numOfTabs) {
        super(fragmentActivity);
        mNumOfTabs = numOfTabs;
    }

    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                TodoFragment todoFragment = TodoFragment.newInstance(mContext);
                return todoFragment;
            case 1:
                DoingFragment doingFragment = new DoingFragment();
                return doingFragment;
            case 2:
                DoneFragment doneFragment = new DoneFragment();
                return doneFragment;
            default:
                return null;
        }
    }
    @Override
    public int getItemCount() {
        return mNumOfTabs;
    }
}*/


