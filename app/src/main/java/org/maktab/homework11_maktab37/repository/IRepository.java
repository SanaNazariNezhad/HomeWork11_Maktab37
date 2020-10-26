package org.maktab.homework11_maktab37.repository;

import org.maktab.homework11_maktab37.model.Task;

import java.io.File;
import java.util.List;
import java.util.UUID;

public interface IRepository {

    List<Task> getTasks();
    List<Task> searchTasks(String searchValue);
    Task getTask(UUID taskId);
    void insertTask(Task task);
    void updateTask(Task task);
    void deleteTask(Task task);
    void deleteAllTask();
    List<Task> getTodoTask();
    List<Task> getDoingTask();
    List<Task> getDoneTask();
    File getPhotoFile(Task task);
}
