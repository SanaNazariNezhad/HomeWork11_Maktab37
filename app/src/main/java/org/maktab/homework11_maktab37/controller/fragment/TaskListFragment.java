package org.maktab.homework11_maktab37.controller.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import org.maktab.homework11_maktab37.R;
import org.maktab.homework11_maktab37.controller.activity.SearchActivity;
import org.maktab.homework11_maktab37.model.User;
import org.maktab.homework11_maktab37.repository.IUserRepository;
import org.maktab.homework11_maktab37.repository.UserDBRepository;

public class TaskListFragment extends Fragment {

    private static final String ARG_Username = "username";
    private static final String ARG_Password = "password";
    private String mUsername,mPassword;
    private IUserRepository mRepository;
    private User mUser;

    private TabLayout mTabLayout;
    private ViewPager2 mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    private TabsFragment mFragmentTodo,mFragmentDoing,mFragmentDone;


    public TaskListFragment() {
        // Required empty public constructor
    }

    public static TaskListFragment newInstance(String username,String password) {
        TaskListFragment fragment = new TaskListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_Username, username);
        args.putString(ARG_Password, password);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        mUsername = getArguments().getString(ARG_Username);
        mPassword = getArguments().getString(ARG_Password);
        mFragmentTodo = TabsFragment.newInstance(mUsername,mPassword,"todo");
        mFragmentDoing = TabsFragment.newInstance(mUsername,mPassword,"doing");
        mFragmentDone = TabsFragment.newInstance(mUsername,mPassword,"done");
        mRepository = UserDBRepository.getInstance(getActivity());
        mUser = mRepository.getUser(mUsername,mPassword);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);
        findViews(view);
        updateView();
        initTab();
        return view;
    }

    private void updateView() {
        mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 0){
                    mFragmentTodo.setUserVisibleHint(true);
                }
                else if (position == 1){
                    mFragmentDoing.setUserVisibleHint(true);
                }
                else {
                    mFragmentDone.setUserVisibleHint(true);
                }
            }
        });
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_task_list, menu);
        updateSubtitle();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.app_bar_search:
                String  search = (String) item.getTooltipText();
                Intent intent = SearchActivity.newIntent(getActivity(),search,mUser.getPrimaryId());
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateSubtitle() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(mUsername);
    }

    private void findViews(View view) {
        mTabLayout = view.findViewById(R.id.tabs);
        mViewPager = view.findViewById(R.id.viewpager);
    }

    private void initTab() {

        addTabs();
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mViewPagerAdapter = new ViewPagerAdapter(getActivity(), mTabLayout.getTabCount());
        mViewPager.setAdapter(mViewPagerAdapter);

        mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mTabLayout.setScrollPosition(position, 0f, true);
            }
        });


        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void addTabs() {
        mTabLayout.addTab(mTabLayout.newTab().setText("TODO"));
        mTabLayout.addTab(mTabLayout.newTab().setText("DOING"));
        mTabLayout.addTab(mTabLayout.newTab().setText("DONE"));
    }

    private class ViewPagerAdapter extends FragmentStateAdapter {
        int mNumOfTabs;

        public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, int numOfTabs) {
            super(fragmentActivity);
            mNumOfTabs = numOfTabs;
        }

        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return mFragmentTodo;
                case 1:
//                    TabsFragment doingFragment = TabsFragment.newInstance(mUsername,mPassword,"doing");
                    return mFragmentDoing;
                case 2:
//                    TabsFragment doneFragment = TabsFragment.newInstance(mUsername,mPassword,"done");
                    return mFragmentDone;
                default:
                    return null;
            }
        }

        @Override
        public int getItemCount() {
            return mNumOfTabs;
        }

        
    }
}
