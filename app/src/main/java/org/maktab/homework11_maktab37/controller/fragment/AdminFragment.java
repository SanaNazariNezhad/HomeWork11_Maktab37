package org.maktab.homework11_maktab37.controller.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.maktab.homework11_maktab37.R;
import org.maktab.homework11_maktab37.model.User;
import org.maktab.homework11_maktab37.repository.IUserRepository;
import org.maktab.homework11_maktab37.repository.UserDBRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class AdminFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private UserAdapter mAdapter;
    private List<User> mUsers;
    private IUserRepository mIUserRepository;

    public AdminFragment() {
        // Required empty public constructor
    }

    public static AdminFragment newInstance() {
        AdminFragment fragment = new AdminFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIUserRepository = UserDBRepository.getInstance(getActivity());
        mUsers = mIUserRepository.getUsers();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin, container, false);
        findView(view);
        initView();
        return view;
    }

    private void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
    }

    private void findView(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_admin);
    }

    private void updateUI() {

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

        public UserHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewUserName = itemView.findViewById(R.id.user_name);
            mTextViewRegistry = itemView.findViewById(R.id.registry_date);
            mTextViewNumberOfTasks = itemView.findViewById(R.id.number_of_tasks);
        }

        public void bindUserDetail(User user){
            DateFormat dateFormat = getDateFormat();
            String date = dateFormat.format(user.getDate());
            mTextViewUserName.setText(user.getUsername());
            mTextViewRegistry.setText(date);

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
}