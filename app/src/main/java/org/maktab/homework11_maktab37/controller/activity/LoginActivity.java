package org.maktab.homework11_maktab37.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import org.maktab.homework11_maktab37.R;
import org.maktab.homework11_maktab37.controller.fragment.LoginFragment;

public class LoginActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }


    @Override
    public Fragment createFragment() {
        return new LoginFragment();
    }
}