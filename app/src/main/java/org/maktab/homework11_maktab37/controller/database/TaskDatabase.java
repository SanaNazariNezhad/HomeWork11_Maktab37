package org.maktab.homework11_maktab37.controller.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import org.maktab.homework11_maktab37.controller.model.Task;
import org.maktab.homework11_maktab37.controller.model.User;

@Database(entities = {Task.class, User.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class TaskDatabase extends RoomDatabase {

    public abstract TaskDatabaseDAO getTaskDatabaseDAO();

}
