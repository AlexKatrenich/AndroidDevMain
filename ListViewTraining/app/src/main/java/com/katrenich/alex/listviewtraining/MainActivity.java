package com.katrenich.alex.listviewtraining;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.lv_alphabetic_list);
        List<Character> alphabet = getAllphabet();

        ListAdapter adapter = new ArrayAdapter<Character>(this,
                android.R.layout.simple_list_item_1,
                alphabet);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.colorAccent));
            }
        });

//        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
//        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
//        listView.getCheckedItemPosition();

    }




    private List<Character> getAllphabet(){
        List<Character> characterList = new ArrayList<>();

        for (char i = 'A'; i < 'Z'; i++) {
            characterList.add(i);
        }

        return characterList;
    }

}
