package com.example.little_chemist.RoomDB;

import androidx.room.*;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract userDAO userDao();
}
