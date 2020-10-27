package org.maktab.homework11_maktab37.database;

import androidx.room.Embedded;
import androidx.room.Relation;

import org.maktab.homework11_maktab37.model.User;

import java.util.List;

public class UserWithTask {
    @Embedded
    public User user;
    @Relation(
            parentColumn = "primaryId",
            entityColumn = "userId"
    )
    public List<User> mUserList;
}
