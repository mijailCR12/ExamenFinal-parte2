package com.example.examenfinal.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Word {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;

    @ColumnInfo(name = "word")
    private String Word = "";

    @ColumnInfo(name = "imagen")
    private String Imagen = "";

    public Word() {
    }

    public Word(int id, String word, String imagen) {
        this.id = id;
        this.Word = word;
        this.Imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return Word;
    }

    public void setWord(String word) {
        this.Word = word;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        this.Imagen = imagen;
    }

}
