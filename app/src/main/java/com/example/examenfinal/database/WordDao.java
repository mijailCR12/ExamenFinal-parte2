package com.example.examenfinal.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WordDao {
    @Query("SELECT * FROM word")
    List<Word> getAll();

    @Query("SELECT * FROM word WHERE id LIKE :wordId LIMIT 1")
    Word findById(int wordId);

    @Update
    void update(Word word);

    @Insert
    void insertData(Word word);

    @Delete
    void delete(Word word);

}
