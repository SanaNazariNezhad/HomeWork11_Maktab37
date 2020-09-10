package org.maktab.homework11_maktab37.controller.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.maktab.homework11_maktab37.DatePickerFragment;
import org.maktab.homework11_maktab37.R;
import org.maktab.homework11_maktab37.controller.model.Task;
import org.maktab.homework11_maktab37.controller.repository.IRepository;
import org.maktab.homework11_maktab37.controller.repository.TaskRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class InsertTaskFragment extends DialogFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String EXTRA_USER_SELECTED_DATE = "org.maktab.homework11_maktab37.userSelectedTask";
    public static final String FRAGMENT_TAG_DATE_PICKER = "DatePicker";
    public static final int REQUEST_CODE_DATE_PICKER = 0;

    private Button mButtonSave, mButtonCancel,mButtonDate,mButtonTime;
    private CheckBox mCheckBoxTodo,mCheckBoxDoing,mCheckBoxDone;
    private TextInputLayout mTitleForm;
    private TextInputLayout mDescriptionForm;
    private TextInputEditText mTitle;
    private TextInputEditText mDescription;
    private IRepository mRepository;
    private Task mTask;

    public InsertTaskFragment() {
        // Required empty public constructor
    }

    public static InsertTaskFragment newInstance() {
        InsertTaskFragment fragment = new InsertTaskFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepository = TaskRepository.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_insert_task, container, false);
        findViews(view);
        listeners();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != Activity.RESULT_OK || data == null)
            return;

        if (requestCode == REQUEST_CODE_DATE_PICKER) {
            Calendar userSelectedDate =
                    (Calendar) data.getSerializableExtra(DatePickerFragment.EXTRA_USER_SELECTED_DATE);

            updateTaskDate(userSelectedDate.getTime());
        }
    }

    private void findViews(View view) {
        mTitleForm = view.findViewById(R.id.title_form_insert);
        mTitle = view.findViewById(R.id.title_insert);
        mDescriptionForm = view.findViewById(R.id.description_form_insert);
        mDescription = view.findViewById(R.id.description_insert);
        mButtonDate = view.findViewById(R.id.btn_date_insert);
        mButtonTime = view.findViewById(R.id.btn_time_insert);
        mButtonSave = view.findViewById(R.id.btn_save_insert);
        mButtonCancel = view.findViewById(R.id.btn_cancel_insert);
        mCheckBoxTodo = view.findViewById(R.id.checkBox_todo);
        mCheckBoxDoing = view.findViewById(R.id.checkBox_doing);
        mCheckBoxDone = view.findViewById(R.id.checkBox_done);
    }

    private void listeners() {
        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendResult();
                dismiss();
            }
        });
        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mButtonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date taskDate = Calendar.getInstance().getTime();
                DatePickerFragment datePickerFragment =
                        DatePickerFragment.newInstance(taskDate);

                //create parent-child relations between CDF and DPF
                datePickerFragment.setTargetFragment(
                        InsertTaskFragment.this,
                        REQUEST_CODE_DATE_PICKER);

                datePickerFragment.show(
                        getActivity().getSupportFragmentManager(),
                        FRAGMENT_TAG_DATE_PICKER);

            }
        });
        mButtonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void sendResult() {
        Fragment fragment = getTargetFragment();

        int requestCode = getTargetRequestCode();
        int resultCode = Activity.RESULT_OK;
        Intent intent = new Intent();
        extractTask();
//        intent.putExtra(EXTRA_USER_SELECTED_DATE, userSelectedTask);

        fragment.onActivityResult(requestCode, resultCode, intent);
    }

    private void extractTask() {
        if (mTitle.getText()!=null && mDescription.getText()!=null && mButtonDate.getText()!=null &&
                mButtonTime.getText()!=null && (mCheckBoxTodo.isChecked() || mCheckBoxDoing.isChecked()
                || mCheckBoxDone.isChecked())) {
            Calendar calendar = Calendar.getInstance();
            Date date = new Date();
//            Task task = new Task(mTitle.getText().toString(), mDescription.getText().toString(), )
        }
        else {
            int strId = R.string.toast_insert;
            Toast toast = Toast.makeText(getActivity(),strId , Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void updateTask(Task task) {
        mRepository.updateTask(task);
    }

    private void updateTaskDate(Date userSelectedDate) {
//        mTask.setDate(userSelectedDate);
//        updateTask(mTask);

        DateFormat dateFormat = getDateFormat();
        mButtonDate.setText(dateFormat.format(userSelectedDate));

    }


    private DateFormat getDateFormat() {
        return new SimpleDateFormat("yyyy/MM/dd");
    }

    private DateFormat getTimeFormat() {
        return new SimpleDateFormat("HH:mm:ss");
    }
}