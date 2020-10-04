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
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.maktab.homework11_maktab37.R;
import org.maktab.homework11_maktab37.controller.model.Task;
import org.maktab.homework11_maktab37.controller.repository.IRepository;
import org.maktab.homework11_maktab37.controller.repository.TaskDBRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

//TODO....................................

public class SearchFragment extends Fragment {
    public static final String FRAGMENT_TAG_EDIT_TASK = "EditTask";
    public static final int REQUEST_CODE_EDIT_TASK = 0;
    public static final String ARGS_SEARCH = "ARGS_SEARCH";
    private IRepository mRepository;
    private RecyclerView mRecyclerView;
    private SearchAdapter mAdapter;
    private List<Task> mTasks;
    private String mSearchString;
    private TextInputEditText mEditTextSearch;
    private TextInputLayout mTextInputLayoutSearch;
    private ImageView mImageViewSearch;

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepository = TaskDBRepository.getInstance(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        findView(view);
        listener();
        return view;
    }

    private void listener() {
        mImageViewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search();
                initView();
            }
        });
    }

    private void findView(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_search);
        mTextInputLayoutSearch = view.findViewById(R.id.search_form);
        mEditTextSearch = view.findViewById(R.id.search);
        mImageViewSearch = view.findViewById(R.id.search_img);
    }

    private void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
    }

    private void search() {
       /* // Get the intent, verify the action and get the query
        Intent intent = getActivity().getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);*/
            mTasks = mRepository.searchTasks(mEditTextSearch.getText().toString());
    }

    private void updateUI() {

        if (mAdapter == null) {
            mAdapter = new SearchAdapter(mTasks);
            mRecyclerView.setAdapter(mAdapter);
        }
        else {
            mAdapter.setTasks(mTasks);
            mAdapter.notifyDataSetChanged();
        }
    }

    private class SearchHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewTitle;
        private TextView mTextViewDate;
        private ImageView mImageViewProfile;
        private Task mTask;

        public SearchHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewTitle = itemView.findViewById(R.id.txtview_title);
            mTextViewDate = itemView.findViewById(R.id.txtview_date);
            mImageViewProfile = itemView.findViewById(R.id.image_profile);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    EditTaskFragment editTaskFragment = EditTaskFragment.newInstance(mTask.getId());

                    editTaskFragment.setTargetFragment(
                            SearchFragment.this,
                            REQUEST_CODE_EDIT_TASK);

                    editTaskFragment.show(
                            getActivity().getSupportFragmentManager(),
                            FRAGMENT_TAG_EDIT_TASK);
                }
            });
        }

        public void bindTaskSearch(Task task) {
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
            String totalDate = "";
            DateFormat dateFormat = getDateFormat();
            String date = dateFormat.format(task.getDate());

            DateFormat timeFormat = getTimeFormat();
            String time = timeFormat.format(task.getDate());

            totalDate = date + "  " + time;

            return totalDate;
        }

        private DateFormat getDateFormat() {
            return new SimpleDateFormat("MMM dd,yyyy");
        }

        private DateFormat getTimeFormat() {
            return new SimpleDateFormat("h:mm a");
        }
    }

    private class SearchAdapter extends RecyclerView.Adapter<SearchHolder> {

        private List<Task> mTasks;

        public List<Task> getTasks(){
            return mTasks;
        }

        public void setTasks(List<Task> tasks) {
            mTasks = tasks;
        }

        public SearchAdapter(List<Task> tasks) {
            mTasks = tasks;
        }

        @Override
        public int getItemCount() {
            return mTasks.size();
        }

        @NonNull
        @Override
        public SearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.task_row_list,parent,false);
            SearchHolder searchHolder = new SearchHolder(view);
            return searchHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull SearchHolder holder, int position) {

            Task task = mTasks.get(position);

            holder.bindTaskSearch(task);
        }

    }
}