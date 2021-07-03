package com.example.examenfinal.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {Word.class}, version = 2)
public abstract class AppDataBase extends RoomDatabase {

    public abstract WordDao wordDao();

}