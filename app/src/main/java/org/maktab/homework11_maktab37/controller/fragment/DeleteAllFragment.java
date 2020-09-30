package org.maktab.homework11_maktab37.controller.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;

import org.maktab.homework11_maktab37.R;
import org.maktab.homework11_maktab37.controller.model.Task;
import org.maktab.homework11_maktab37.controller.repository.IRepository;
import org.maktab.homework11_maktab37.controller.repository.TaskDBRepository;
import org.maktab.homework11_maktab37.controller.repository.TaskRepository;

import java.util.List;

public class DeleteAllFragment extends DialogFragment {

    private IRepository mRepository;
    private List<Task> mTasks;

    public DeleteAllFragment() {
        // Required empty public constructor
    }

    public static DeleteAllFragment newInstance() {
        DeleteAllFragment fragment = new DeleteAllFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepository = TaskDBRepository.getInstance(getActivity());
        mTasks = mRepository.getTasks();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_delete_all, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (mTasks.size() != 0) {
            builder.setTitle(R.string.delete_all_title);
            builder.setIcon(R.drawable.ic_high_importance);
            builder.setView(view);
            builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mRepository.deleteAllTask();

                }
            })
                    .setNegativeButton(R.string.no, null);
        } else {
            builder.setTitle(R.string.no_tasks);
            builder.setNegativeButton(R.string.exit,null);
        }


        AlertDialog dialog = builder.create();
        return dialog;
    }
}