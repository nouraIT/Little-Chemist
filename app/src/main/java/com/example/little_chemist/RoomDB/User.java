package com.example.little_chemist.RoomDB;

import androidx.annotation.NonNull;
import androidx.room.*;


@Entity
public class User {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "user_name")
    public String username;

    @ColumnInfo(name = "password")
    public String password;

    public User(@NonNull String un,@NonNull String pass) {
        username = un;
        password = pass;
    }
    public User(){

    }
}
