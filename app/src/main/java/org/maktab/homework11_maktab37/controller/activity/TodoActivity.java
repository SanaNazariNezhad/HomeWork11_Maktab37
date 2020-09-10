package org.maktab.homework11_maktab37.controller.activity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import org.maktab.homework11_maktab37.controller.fragment.TodoFragment;
import org.maktab.homework11_maktab37.controller.repository.IRepository;
import org.maktab.homework11_maktab37.controller.repository.TaskRepository;

public class TodoActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, TodoActivity.class);
        return intent;
    }


    @Override
    public Fragment createFragment() {
        return TodoFragment.newInstance();
    }
}