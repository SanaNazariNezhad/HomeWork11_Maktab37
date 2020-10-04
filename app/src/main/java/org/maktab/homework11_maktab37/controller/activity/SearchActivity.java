package org.maktab.homework11_maktab37.controller.activity;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;

import org.maktab.homework11_maktab37.controller.fragment.SearchFragment;

//TODO.................................

public class SearchActivity extends SingleFragmentActivity {

    public static final String EXTRA_SEARCH_VALUE = "EXTRA_SEARCH_VALUE";

    public static Intent newIntent(Context context,String search) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra(EXTRA_SEARCH_VALUE,search);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        return SearchFragment.newInstance();
    }
}