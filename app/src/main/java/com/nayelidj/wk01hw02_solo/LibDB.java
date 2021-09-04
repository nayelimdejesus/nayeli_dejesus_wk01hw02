package com.nayelidj.wk01hw02_solo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version=1, exportSchema = false)
public abstract class LibDB extends RoomDatabase {

    public abstract LibDao Lib();

    private static LibDB sInstance;
    public static final String User = "user";

    public static synchronized LibDB getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(context.getApplicationContext(),
                    LibDB.class, "lib.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return sInstance;

    }

    public void seed() {
        if (Lib().countUser() == 0) {
            runInTransaction(new Runnable() {
                @Override
                public void run() {
                    //user added
                    User user1 = new User("Bret", "userOne");
                    User user2 = new User("Antonette", "userTwo");
                    User user3 = new User("Samantha", "userThree");
                    User user4 = new User("Karianne", "userFour");
                    User user5 = new User("Kamren", "userFive");
                    User user6 = new User("Leopoldo_Corkery", "userSix");
                    User user7 = new User("Elwyn.Skiles", "userSeven");
                    User user8 = new User("Maxime_Nienow", "userEight");
                    User user9 = new User("Delphine", "userNine");
                    User user10 = new User("Moriah.Stanton", "userTen");

                    Lib().addMultUser(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10);


                }
            });
        }
    }
}