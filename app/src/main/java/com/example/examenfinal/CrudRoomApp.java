package com.example.examenfinal;

import android.app.Application;

import androidx.room.Room;

import com.example.examenfinal.database.AppDataBase;
import com.example.examenfinal.database.DataBaseMigrations;

public class CrudRoomApp extends Application {

    private static CrudRoomApp INSTANCE;
    private AppDataBase dataBase;

    public static CrudRoomApp getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        dataBase = Room.databaseBuilder(this, AppDataBase.class, "database_word")
                .addMigrations(DataBaseMigrations.MIGRATION_1_TO_2)
                .allowMainThreadQueries()
                .build();

        INSTANCE = this;
    }

    public AppDataBase getDataBase() {
        return dataBase;
    }

}
