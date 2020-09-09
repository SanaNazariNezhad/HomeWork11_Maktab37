package org.maktab.homework11_maktab37;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import org.maktab.homework11_maktab37.controller.activity.SingleFragmentActivity;
import org.maktab.homework11_maktab37.controller.activity.TodoActivity;
import org.maktab.homework11_maktab37.controller.fragment.DoingFragment;
import org.maktab.homework11_maktab37.controller.fragment.TodoFragment;

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