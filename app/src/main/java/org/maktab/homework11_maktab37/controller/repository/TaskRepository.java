package org.maktab.homework11_maktab37.controller.repository;

import org.maktab.homework11_maktab37.controller.model.Task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class TaskRepository implements IRepository {

    public static TaskRepository sInstance;
    private List<Task> mTasks;

    private TaskRepository() {
        mTasks = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 50; i++) {
            Date date;
            String str = "";
            String title = "";
            int rand = random.nextInt(3);
            if (rand == 0) {
                str = "Todo";
                title = "Salam";
            }
            else if (rand == 1) {
                str = "Doing";
                title = "Zana";
            }
            else if (rand == 2) {
                str = "Done";
                title = "Hi";
            }
            date = DateUtils.randomDate();
            Task task = new Task(title, "HomeWork", date, str);

            mTasks.add(task);
        }

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

    @Override
    public List<Task> getTodoTask() {
        List<Task> todoTasks = new ArrayList<>();
        for (Task task : mTasks) {
            if (task.getState().equalsIgnoreCase("todo"))
                todoTasks.add(task);

        }
        return todoTasks;
    }

    @Override
    public List<Task> getDoingTask() {
        List<Task> doingTasks = new ArrayList<>();
        for (Task task : mTasks) {
            if (task.getState().equalsIgnoreCase("doing"))
                doingTasks.add(task);

        }
        return doingTasks;
    }

    @Override
    public List<Task> getDoneTask() {
        List<Task> doneTasks = new ArrayList<>();
        for (Task task : mTasks) {
            if (task.getState().equalsIgnoreCase("done"))
                doneTasks.add(task);

        }
        return doneTasks;
    }

    public static TaskRepository getInstance() {
        if (sInstance == null)
            sInstance = new TaskRepository();

        return sInstance;
    }


    private static class DateUtils {
        public static final int YEAR_START = 2000;
        public static final int YEAR_END = 2020;

        public static Date randomDate() {
            GregorianCalendar gc = new GregorianCalendar();
            int year = randBetween(YEAR_START, YEAR_END);
            gc.set(gc.YEAR, year);
            int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
            gc.set(gc.DAY_OF_YEAR, dayOfYear);

            return gc.getTime();
        }

        public static int randBetween(int start, int end) {
            return start + (int) Math.round(Math.random() * (end - start));
        }
    }
}
