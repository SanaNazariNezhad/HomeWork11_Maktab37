package org.maktab.homework11_maktab37.controller.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.maktab.homework11_maktab37.R;

public class TaskListFragment extends Fragment {

    private static final String ARG_Username = "username";

    private String mParam1;
    private String mParam2;

    public TaskListFragment() {
        // Required empty public constructor
    }

    public static TaskListFragment newInstance(String username) {
        TaskListFragment fragment = new TaskListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_Username, username);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_Username);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);

        return view;
    }
}