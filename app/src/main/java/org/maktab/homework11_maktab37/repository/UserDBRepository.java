package org.maktab.homework11_maktab37.repository;

import android.content.Context;

import androidx.room.Room;

import org.maktab.homework11_maktab37.database.TaskDatabase;
import org.maktab.homework11_maktab37.database.TaskDatabaseDAO;
import org.maktab.homework11_maktab37.model.Task;
import org.maktab.homework11_maktab37.model.User;
import org.maktab.homework11_maktab37.repository.IUserRepository;

import java.util.List;

public class UserDBRepository implements IUserRepository {

    private static UserDBRepository sInstance;
    private TaskDatabaseDAO mTaskDAO;
    private Context mContext;

    public static UserDBRepository getInstance(Context context) {
        if (sInstance == null)
            sInstance = new UserDBRepository(context);

        return sInstance;
    }

    private UserDBRepository(Context context) {
        mContext = context.getApplicationContext();
        TaskDatabase taskDatabase = Room.databaseBuilder(mContext,
                TaskDatabase.class,
                "task.db")
                .allowMainThreadQueries()
                .build();

        mTaskDAO = taskDatabase.getTaskDatabaseDAO();
    }

    @Override
    public List<User> getUsers() {
        return mTaskDAO.getUsers();
    }

    @Override
    public User getUser(String username,String password) {
        return mTaskDAO.getUser(username,password);
    }

    @Override
    public void insertUser(User user) {
        mTaskDAO.insertUser(user);
    }

    @Override
    public void deleteUser(User user) {
        mTaskDAO.deleteUser(user);
    }

    @Override
    public void deleteUserTasks(long userId) {
        mTaskDAO.deleteUserTasks(userId);
    }

    @Override
    public List<Task> getUserTasks(long userId) {
        return mTaskDAO.getUserTasks(userId);
    }

    @Override
    public int numberOfTask(long userId) {
        return mTaskDAO.numberOfTask(userId);
    }


}
