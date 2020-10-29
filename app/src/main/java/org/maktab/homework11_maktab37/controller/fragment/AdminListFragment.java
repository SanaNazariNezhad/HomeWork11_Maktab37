package org.maktab.homework11_maktab37.controller.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import org.maktab.homework11_maktab37.controller.activity.AdminDetailActivity;
import org.maktab.homework11_maktab37.R;
import org.maktab.homework11_maktab37.model.Task;
import org.maktab.homework11_maktab37.model.User;
import org.maktab.homework11_maktab37.repository.IRepository;
import org.maktab.homework11_maktab37.repository.IUserRepository;
import org.maktab.homework11_maktab37.repository.TaskDBRepository;
import org.maktab.homework11_maktab37.repository.UserDBRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class AdminListFragment extends Fragment {
    public static final int REQUEST_CODE_USER_TASK_DETAIL = 0;
    private RecyclerView mRecyclerView;
    private UserAdapter mAdapter;
    private List<User> mUsers;
    private User mUserUndo;
    private List<Task> mUserTasks;
    private IUserRepository mIUserRepository;
    private IRepository mIRepository;
    private RelativeLayout mRelativeLayout;

    public AdminListFragment() {
        // Required empty public constructor
    }

    public static AdminListFragment newInstance() {
        AdminListFragment fragment = new AdminListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIUserRepository = UserDBRepository.getInstance(getActivity());
        mIRepository = TaskDBRepository.getInstance(getActivity());
//        mUsers = mIUserRepository.getUsers();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_list, container, false);
        findView(view);
        initView();
        return view;
    }

    private void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        swipeRecycler();
        updateUI();
    }

    private void swipeRecycler() {
        /*  set swipe touch listener */
        SwipeableRecyclerView swipeTouchListener = new
                SwipeableRecyclerView(mRecyclerView,
                new SwipeableRecyclerView.SwipeListener() {

                    @Override
                    public boolean canSwipeRight(int position) {
                        //enable/disable right swipe on checkbox base else use true/false
                        return true;
                    }

                    @Override
                    public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                        //on recycler view swipe right dismiss update adapter
                        onRecyclerViewDismiss(reverseSortedPositions, mUsers);
                    }
                });

        //add item touch listener to recycler view
        mRecyclerView.addOnItemTouchListener(swipeTouchListener);
    }

    private void onRecyclerViewDismiss(int[] reverseSortedPositions, List<User> users) {
        for (int position : reverseSortedPositions) {
            mUserUndo = users.get(position);
            mIUserRepository.deleteUser(users.get(position));
            mUserTasks = mIUserRepository.getUserTasks(users.get(position).getPrimaryId());
            mIUserRepository.deleteUserTasks(users.get(position).getPrimaryId());
        }
        updateUI();
        showSnackBar();
    }

    private void showSnackBar() {
        Snackbar snackbar = Snackbar.make(mRelativeLayout, R.string.user_dismiss_success, Snackbar.LENGTH_SHORT);
        snackbar.setAction(R.string.user_dismiss_undo, new MyUndoListener());
        snackbar.show();
    }

    private void findView(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_admin);
        mRelativeLayout = view.findViewById(R.id.admin_layout);
    }

    private void updateUI() {
        mUsers = mIUserRepository.getUsers();
        if (mAdapter == null) {
            mAdapter = new UserAdapter(mUsers);
            mRecyclerView.setAdapter(mAdapter);
        }
        else {
            mAdapter.setUsers(mUsers);
            mAdapter.notifyDataSetChanged();
        }
    }

    private class UserHolder extends RecyclerView.ViewHolder{
        private TextView mTextViewUserName;
        private TextView mTextViewRegistry;
        private TextView mTextViewNumberOfTasks;
        private User mUser;

        public UserHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewUserName = itemView.findViewById(R.id.user_name);
            mTextViewRegistry = itemView.findViewById(R.id.registry_date);
            mTextViewNumberOfTasks = itemView.findViewById(R.id.number_of_tasks);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = AdminDetailActivity.newIntent(getActivity(),mUser.getPrimaryId());
                    startActivity(intent);
                }
            });
        }

        public void bindUserDetail(User user){
            mUser = user;
            DateFormat dateFormat = getDateFormat();
            String date = dateFormat.format(user.getDate());
            mTextViewUserName.setText(user.getUsername());
            mTextViewRegistry.setText(date);
            mTextViewNumberOfTasks.setText(mIUserRepository.numberOfTask(user.getPrimaryId()) + "");

        }

        private DateFormat getDateFormat() {
            return new SimpleDateFormat("MMM dd,yyyy,h:mm a");
        }
    }

    private class UserAdapter extends RecyclerView.Adapter<UserHolder>{

        private List<User> mUsers;

        public List<User> getUsers() {
            return mUsers;
        }

        public void setUsers(List<User> users) {
            mUsers = users;
        }

        public UserAdapter(List<User> users) {
            mUsers = users;
        }

        @NonNull
        @Override
        public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.user_admin_row_list,parent,false);
            UserHolder userHolder = new UserHolder(view);
            return userHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull UserHolder holder, int position) {

            User user = mUsers.get(position);
            holder.bindUserDetail(user);
        }

        @Override
        public int getItemCount() {
            return mUsers.size();
        }
    }

    public class MyUndoListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            mIUserRepository.insertUser(mUserUndo);
            mIRepository.insertTasks(mUserTasks);
            updateUI();
        }
    }
}