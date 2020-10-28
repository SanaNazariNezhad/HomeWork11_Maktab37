package org.maktab.homework11_maktab37.repository;

import android.content.Context;
import androidx.room.Room;
import org.maktab.homework11_maktab37.database.TaskDatabase;
import org.maktab.homework11_maktab37.database.TaskDatabaseDAO;
import org.maktab.homework11_maktab37.model.Task;

import java.io.File;
import java.util.List;
import java.util.UUID;

public class TaskDBRepository implements IRepository {

    private static TaskDBRepository sInstance;

    private TaskDatabaseDAO mTaskDAO;
    private Context mContext;
    private List<Task> mTasks;

    public static TaskDBRepository getInstance(Context context) {
        if (sInstance == null)
            sInstance = new TaskDBRepository(context);

        return sInstance;
    }

    private TaskDBRepository(Context context) {
        mContext = context.getApplicationContext();
        TaskDatabase taskDatabase = Room.databaseBuilder(mContext,
                TaskDatabase.class,
                "task.db")
                .allowMainThreadQueries()
                .build();

        mTaskDAO = taskDatabase.getTaskDatabaseDAO();
    }

    @Override
    public List<Task> getTasks() {
        return mTaskDAO.getTasks();
    }

    @Override
    public List<Task> searchTasks(String searchValue) {
      return mTaskDAO.searchTasks(searchValue);
    }

    @Override
    public Task getTask(UUID taskId) {
        return mTaskDAO.getTask(taskId);
    }

    @Override
    public void insertTask(Task task) {
       mTaskDAO.insertTask(task);
    }

    @Override
    public void insertTasks(List<Task> tasks) {
        mTaskDAO.insertTasks(tasks);
    }

    @Override
    public void updateTask(Task task) {
        mTaskDAO.updateTask(task);
    }

    @Override
    public void deleteTask(Task task) {
        mTaskDAO.deleteTask(task);
    }

    @Override
    public void deleteAllTask() {
        mTaskDAO.deleteAllTask();
    }

    @Override
    public List<Task> getTodoTask(long userId) {
        return mTaskDAO.getTodoTask(userId);
    }

    @Override
    public List<Task> getDoingTask(long userId) {
        return mTaskDAO.getDoingTask(userId);
    }

    @Override
    public List<Task> getDoneTask(long userId) {
        return mTaskDAO.getDoneTask(userId);
    }

    @Override
    public File getPhotoFile(Task task) {
        // /data/data/com.example.criminalintent/files/
        File filesDir = mContext.getFilesDir();

        // /data/data/com.example.criminalintent/files/IMG_ktui4u544nmkfuy48485.jpg
        File photoFile = new File(filesDir, task.getPhotoFileName());
        return photoFile;
    }

}
