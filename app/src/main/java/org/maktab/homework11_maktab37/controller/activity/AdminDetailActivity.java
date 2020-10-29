package org.maktab.homework11_maktab37.controller.activity;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;

import org.maktab.homework11_maktab37.controller.fragment.AdminDetailFragment;

public class AdminDetailActivity extends SingleFragmentActivity {

    public static final String EXTRA_USER_ID = "extra_userId";
    private static long mUserId;

    public static Intent newIntent(Context context, long userId) {
        mUserId = userId;
        Intent intent = new Intent(context, AdminDetailActivity.class);
        intent.putExtra(EXTRA_USER_ID,userId);
        return intent;
    }


    @Override
    public Fragment createFragment() {
        return AdminDetailFragment.newInstance(mUserId);
    }
}