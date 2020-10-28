package org.maktab.homework11_maktab37.controller.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import org.maktab.homework11_maktab37.controller.fragment.TaskListFragment;

public class TaskListActivity extends SingleFragmentActivity {

    public static final String EXTRA_USERNAME = "org.maktab.homework11_maktab37.controller.activity.extra_username";
    public static final String EXTRA_PASSWORD = "org.maktab.homework11_maktab37.controller.activity.extra_password";
    private static String mUsername;
    private static String mPassword;

    public static Intent newIntent(Context context, String username,String password) {
        mUsername = username;
        mPassword = password;
        Intent intent = new Intent(context, TaskListActivity.class);
        intent.putExtra(EXTRA_USERNAME,username);
        intent.putExtra(EXTRA_PASSWORD,password);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        TaskListFragment taskListFragment = TaskListFragment.newInstance(mUsername,mPassword);
        return taskListFragment;
    }
}