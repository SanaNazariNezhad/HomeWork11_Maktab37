package org.maktab.homework11_maktab37.controller.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

import org.maktab.homework11_maktab37.R;
import org.maktab.homework11_maktab37.model.Task;
import org.maktab.homework11_maktab37.repository.IUserRepository;
import org.maktab.homework11_maktab37.repository.UserDBRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class AdminDetailFragment extends Fragment {
    public static final String FRAGMENT_TAG_EDIT_TASK = "AdminEditTasks";
    public static final int REQUEST_CODE_EDIT_TASK = 0;
    public static final String ARG_USER_ID = "Arg_userId";

    private RecyclerView mRecyclerView;
    private UserTasksAdapter mAdapter;
    private IUserRepository mRepository;
    private List<Task> mTasks;
    private long mUserId;
    public AdminDetailFragment() {
        // Required empty public constructor
    }

    public static AdminDetailFragment newInstance(long userId) {
        AdminDetailFragment fragment = new AdminDetailFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_USER_ID,userId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepository = UserDBRepository.getInstance(getActivity());
        mUserId = getArguments().getLong(ARG_USER_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_detail, container, false);
        findViews(view);
        initViews();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != Activity.RESULT_OK || data == null)
            return;

        if (requestCode == REQUEST_CODE_EDIT_TASK) {
            updateUI();
        }
    }

    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_user_tasks);
    }

    private void initViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
    }

    private void updateUI() {
        mTasks = mRepository.getUserTasks(mUserId);
        if (mAdapter == null) {
            mAdapter = new UserTasksAdapter(mTasks);
            mRecyclerView.setAdapter(mAdapter);
        }
        else {
            mAdapter.setTasks(mTasks);
            mAdapter.notifyDataSetChanged();
        }
    }

    private class UserTasksHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewTitle;
        private TextView mTextViewDate;
        private ImageView mImageViewProfile;
        private Task mTask;

        public UserTasksHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewTitle = itemView.findViewById(R.id.txtview_title);
            mTextViewDate = itemView.findViewById(R.id.txtview_date);
            mImageViewProfile = itemView.findViewById(R.id.image_profile);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AdminEditTasksFragment adminEditTasksFragment = AdminEditTasksFragment.newInstance(mTask.getId());

                    adminEditTasksFragment.setTargetFragment(
                            AdminDetailFragment.this,
                            REQUEST_CODE_EDIT_TASK);

                    adminEditTasksFragment.show(
                            getActivity().getSupportFragmentManager(),
                            FRAGMENT_TAG_EDIT_TASK);
                }
            });
        }

        public void bindUserTasks(Task task) {
            mTask = task;
            mTextViewTitle.setText(task.getTitle());
            String date = createDateFormat(task);
            mTextViewDate.setText(date);
            int color = Color.parseColor("#ff80aa");
            String string = task.getTitle().substring(0,1);
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound(string,color);
            mImageViewProfile.setImageDrawable(drawable);
        }

        private String createDateFormat (Task task){
            DateFormat dateFormat = getDateFormat();
            String date = dateFormat.format(task.getDate());

            return date;
        }

        private DateFormat getDateFormat() {
            return new SimpleDateFormat("MMM dd,yyyy h:mm a");
        }

    }

    private class UserTasksAdapter extends RecyclerView.Adapter<UserTasksHolder> {

        private List<Task> mTasks;

        public List<Task> getTasks(){
            return mTasks;
        }

        public void setTasks(List<Task> tasks) {
            mTasks = tasks;
        }

        public UserTasksAdapter(List<Task> tasks) {
            mTasks = tasks;
        }

        @Override
        public int getItemCount() {
            return mTasks.size();
        }

        @NonNull
        @Override
        public UserTasksHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.task_row_list,parent,false);
            UserTasksHolder userTasksHolder = new UserTasksHolder(view);
            return userTasksHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull UserTasksHolder holder, int position) {

            Task task = mTasks.get(position);

            holder.bindUserTasks(task);
        }

    }
}