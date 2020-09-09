package org.maktab.homework11_maktab37.controller.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.maktab.homework11_maktab37.InsertTaskFragment;
import org.maktab.homework11_maktab37.R;
import org.maktab.homework11_maktab37.controller.model.Task;
import org.maktab.homework11_maktab37.controller.repository.IRepository;
import org.maktab.homework11_maktab37.controller.repository.TaskRepository;

import java.util.List;

public class DoneFragment extends Fragment {

    public static final String FRAGMENT_TAG_INSERT_TASK = "InsertTask";
    public static final int REQUEST_CODE_INSERT_TASK = 0;

    private RecyclerView mRecyclerViewDone;
    private DoneAdapter mDoneAdapter;
    private IRepository mRepository;
    private List<Task> mTasks;
    private RelativeLayout mLayoutEmptyDone;
    private FloatingActionButton mActionButtonInsert;

    public DoneFragment() {
        // Required empty public constructor
    }

    public static DoneFragment newInstance() {
        DoneFragment fragment = new DoneFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepository = TaskRepository.getInstance();
        List<Task> tasks = mRepository.getDoneTask();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_done, container, false);
        findViews(view);
        initViews();
        listeners();
        return view;
    }

    private void listeners() {
        mActionButtonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InsertTaskFragment insertTaskFragment = InsertTaskFragment.newInstance();

                insertTaskFragment.setTargetFragment(
                        DoneFragment.this,
                        REQUEST_CODE_INSERT_TASK);

                /*insertTaskFragment.show(
                        getActivity().getSupportFragmentManager(),
                        FRAGMENT_TAG_INSERT_TASK);*/

            }
        });
    }

    private void initViews() {
        mRecyclerViewDone.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (mTasks.size()==0)
            mLayoutEmptyDone.setVisibility(View.VISIBLE);
        mDoneAdapter = new DoneAdapter(mTasks);
        mRecyclerViewDone.setAdapter(mDoneAdapter);
    }

    private void findViews(View view) {
        mRecyclerViewDone = view.findViewById(R.id.recycler_done);
        mLayoutEmptyDone = view.findViewById(R.id.layout_empty_doneTask);
        if (mTasks.size() == 0) {
            mActionButtonInsert = view.findViewById(R.id.fab_empty_done);
        }else {
            mActionButtonInsert = view.findViewById(R.id.fab_done);
        }
    }

    private class DoneHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewTitle;
        private TextView mTextViewDate;
        private ImageView mImageViewProfile;
        private Task mTask;

        public DoneHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewTitle = itemView.findViewById(R.id.txtview_title);
            mTextViewDate = itemView.findViewById(R.id.txtview_date);
            mImageViewProfile = itemView.findViewById(R.id.image_profile);
        }

        public void bindTaskDone(Task task) {
            mTask = task;
            mTextViewTitle.setText(task.getTitle());
            mTextViewDate.setText(task.getDate().toString());
            String string = task.getTitle().substring(0,1);
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound(string, Color.RED);
            mImageViewProfile.setImageDrawable(drawable);
        }

        public ImageView getImageViewProfile(){
            return mImageViewProfile;
        }
    }

    private class DoneAdapter extends RecyclerView.Adapter<DoneHolder>{

        private List<Task> mTasks;

        public List<Task> getTasks(){
            return mTasks;
        }

        public void setTasks(List<Task> tasks) {
            mTasks = tasks;
        }

        public DoneAdapter(List<Task> tasks) {
            mTasks = tasks;
        }

        @Override
        public int getItemCount() {
            return mTasks.size();
        }

        @NonNull
        @Override
        public DoneHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.task_row_list,parent,false);
            DoneHolder doneHolder = new DoneHolder(view);
            return doneHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull DoneHolder holder, int position) {

            Task task = mTasks.get(position);

            holder.bindTaskDone(task);

        }

    }
}