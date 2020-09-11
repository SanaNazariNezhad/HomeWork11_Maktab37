package org.maktab.homework11_maktab37.controller.repository;

import org.maktab.homework11_maktab37.controller.model.Task;

import java.util.List;
import java.util.UUID;

public interface IRepository {

    List<Task> getTasks();
    Task getTask(UUID taskId);
    void insertTask(Task task);
    void updateTask(Task task);
    void deleteTask(Task task);
    void deleteAllTask();
    List<Task> getTodoTask();
    List<Task> getDoingTask();
    List<Task> getDoneTask();
}
