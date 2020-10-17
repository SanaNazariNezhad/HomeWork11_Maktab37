package org.maktab.homework11_maktab37.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import org.maktab.homework11_maktab37.model.Task;
import org.maktab.homework11_maktab37.model.User;

@Database(entities = {Task.class, User.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class TaskDatabase extends RoomDatabase {

    public abstract TaskDatabaseDAO getTaskDatabaseDAO();

}
