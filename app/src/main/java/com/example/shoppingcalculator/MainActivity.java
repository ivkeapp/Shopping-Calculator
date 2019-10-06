package com.example.shoppingcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button addField;
    Button next;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinearLayout root = findViewById(R.id.root);
        db = new DatabaseHelper(this);
        addField = findViewById(R.id.addView);
        next = findViewById(R.id.next);

        addField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View rowView = inflater.inflate(R.layout.field, null);
                root.addView(rowView, root.getChildCount() - 2);
                //Log.v("countViews", String.valueOf(root.getChildCount()));
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < root.getChildCount(); i++) {

                    View view = root.getChildAt(i);
                    EditText et_value = view.findViewById(R.id.name);
                    boolean isInserted = false;
                    if(et_value!=null){
                        String value = et_value.getText().toString().trim();
                        if(TextUtils.isEmpty(value)){
                            et_value.setError("Unesi proizvod");
                        } else {
                            isInserted = db.insertProduct(value);
                        }
                        if(isInserted) {
                            Toast.makeText(MainActivity.this, "Inserted to DB", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this, ProductsListActivity.class);
                            startActivity(intent);
                        }
                        else
                            Toast.makeText(MainActivity.this, "Failed to insert into DB", Toast.LENGTH_LONG).show();
                        Log.v("Values", " "+value);
                    }
                }
            }
        });

    }
}
