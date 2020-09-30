package org.maktab.homework11_maktab37.controller.repository;

import android.util.Log;

import org.maktab.homework11_maktab37.controller.model.Task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.UUID;
//............I did not use this class in project any more..................

public class TaskRepository implements IRepository {

    public static TaskRepository sInstance;
    private List<Task> mTasks;

    private TaskRepository() {
        mTasks = new ArrayList<>();
        Log.d("TaskSize_constructor" , " " + mTasks.size());

    }

    @Override
    public List<Task> getTasks() {
        return mTasks;
    }

    @Override
    public List<Task> searchTasks(String searchValue) {
        return null;
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
        Log.d("TaskSize_insert" , " " + mTasks.size());
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
    public void deleteAllTask() {
        mTasks.clear();
    }

    @Override
    public void updateTask(Task task) {
        Task findTask = getTask(task.getId());
        findTask.setTitle(task.getTitle());
        findTask.setDescription(task.getDescription());
        findTask.setDate(task.getDate());
        findTask.setState(task.getState());

    }

    @Override
    public List<Task> getTodoTask() {
        List<Task> todoTasks = new ArrayList<>();
        for (Task task : mTasks) {
            if (task.getState().equalsIgnoreCase("Todo"))
                todoTasks.add(task);

        }
        return todoTasks;
    }

    @Override
    public List<Task> getDoingTask() {
        List<Task> doingTasks = new ArrayList<>();
        for (Task task : mTasks) {
            if (task.getState().equalsIgnoreCase("Doing"))
                doingTasks.add(task);

        }
        return doingTasks;
    }

    @Override
    public List<Task> getDoneTask() {
        List<Task> doneTasks = new ArrayList<>();
        for (Task task : mTasks) {
            if (task.getState().equalsIgnoreCase("Done"))
                doneTasks.add(task);

        }
        return doneTasks;
    }

    public static TaskRepository getInstance() {
        if (sInstance == null)
            sInstance = new TaskRepository();

        return sInstance;
    }
}
