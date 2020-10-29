package org.maktab.homework11_maktab37.controller.activity;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;

import org.maktab.homework11_maktab37.controller.fragment.AdminListFragment;

public class AdminListActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, AdminListActivity.class);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        return AdminListFragment.newInstance();
    }
}