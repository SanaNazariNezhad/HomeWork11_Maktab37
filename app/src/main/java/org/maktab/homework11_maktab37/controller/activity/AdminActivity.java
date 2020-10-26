package org.maktab.homework11_maktab37.controller.activity;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;

import org.maktab.homework11_maktab37.controller.fragment.AdminFragment;

public class AdminActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, AdminActivity.class);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        return AdminFragment.newInstance();
    }
}