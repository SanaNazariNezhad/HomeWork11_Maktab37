package org.maktab.homework11_maktab37.controller.repository;

import org.maktab.homework11_maktab37.controller.model.Task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskRepository implements IRepository{

    public static TaskRepository sInstance;
    private List<Task> mTasks;

    private TaskRepository() {
        mTasks = new ArrayList<>();

    }

    @Override
    public List<Task> getTasks() {
        return mTasks;
    }

    @Override
    public Task getTask(UUID id) {
        for (Task task : mTasks) {
            if (task.getId().equals(id))
                return task;
        }

        return null;
    }

    @Override
    public void insertTask(Task task) {
        mTasks.add(task);
    }

    @Override
    public void deleteTask(Task task) {
        for (int i = 0; i < mTasks.size(); i++) {
            if (mTasks.get(i).getId().equals(task.getId())) {
                mTasks.remove(i);
                return;
            }
        }
    }

    @Override
    public void updateTask(Task task) {
        Task findTask = getTask(task.getId());
        findTask.setTitle(task.getTitle());
        findTask.setDescription(task.getDescription());
        findTask.setDate(task.getDate());
        findTask.setState(task.getState());

    }

    public static TaskRepository getInstance() {
        if (sInstance == null)
            sInstance = new TaskRepository();

        return sInstance;
    }

}
