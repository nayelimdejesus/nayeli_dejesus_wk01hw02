package com.nayelidj.wk01hw02_solo;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LibDao {
    @Insert
    void addUser(User user);
    @Insert
    long[] addMultUser(User...user);

    @Query("SELECT * FROM User") //gets all info of user
    List<User> getAllUser();
    @Query("SELECT COUNT(*) FROM User") //gets count of user
    int countUser();
    @Query("SELECT * FROM User where username=:username")
    List<User> getUser(String username);
}
