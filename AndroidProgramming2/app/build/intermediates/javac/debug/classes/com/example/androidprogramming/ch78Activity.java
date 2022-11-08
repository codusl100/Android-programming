package com.example.AndroidProgramming;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ch78Activity extends ListActivity {
    static final int GET_STRING = 1;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Button choice = (Button) findViewById(R.id.choice);
        Button move = (Button) findViewById(R.id.move);
        text = (TextView) findViewById(R.id.text1);

        setContentView(R.layout.ch78_activity);
        String[] values = {"www.naver.com", "www.daum.net",
                "www.google.com", "www.github.com", "www.notion.so"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);

        choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ch78Activity.this, ch78SubActivity.class);
                startActivityForResult(intent, GET_STRING);
            }
        });

    }
    @Override //Item 클릭 이벤트 처리
    protected void onListItemClick(ListView l, View v, int position, long id){
        String item = (String) getListAdapter().getItem(position);
        Toast.makeText(this, item + "selected", Toast.LENGTH_LONG).show();
    }

}
