package com.example.examenfinal.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.examenfinal.CrudRoomApp;
import com.example.examenfinal.R;
import com.example.examenfinal.database.Word;
import com.example.examenfinal.ui.adapter.RvAdapter;
import com.example.examenfinal.ui.common.DataListListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvListWord;
    private FloatingActionButton btnAgregar;
    private RvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new RvAdapter();

        rvListWord = findViewById(R.id.rv_list_word);
        btnAgregar = findViewById(R.id.btn_agregar);

        rvListWord.setAdapter(adapter);

        adapter.setRemoveListener(new DataListListener() {
            @Override
            public void onRemoveClick(Word word) {
                adapter.removeData(word);
            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FuntionActivity.class));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Word> datas = CrudRoomApp.getInstance().getDataBase().wordDao().getAll();
        adapter.setData(datas);
    }

}
