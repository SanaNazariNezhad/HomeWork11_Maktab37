package org.maktab.homework11_maktab37.controller.activity;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;

import org.maktab.homework11_maktab37.controller.fragment.DoingFragment;
import org.maktab.homework11_maktab37.controller.repository.IRepository;
import org.maktab.homework11_maktab37.controller.repository.TaskRepository;

public class DoingActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, DoingActivity.class);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        return DoingFragment.newInstance();
    }

}