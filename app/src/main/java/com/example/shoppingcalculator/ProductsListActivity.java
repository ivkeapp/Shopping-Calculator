package com.example.shoppingcalculator;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductsListActivity extends AppCompatActivity {

    DatabaseHelper db;
    TextView sum;
    Button delete, bSum;
    List<String> productsList = new ArrayList<>();
    List<String> pricesList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);
        recyclerView = findViewById(R.id.recycler_view);
        sum = findViewById(R.id.sum);
        db = new DatabaseHelper(this);
        delete = findViewById(R.id.delete);
        bSum = findViewById(R.id.calc);
        bSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum.setText(String.valueOf(sum(pricesList))+" RSD");
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteAll();
            }
        });
        Cursor result = db.getAllProducts();
        if(result.getCount() == 0){
            return;
        }
        while(result.moveToNext()){
            productsList.add(result.getString(1));
            pricesList.add(String.valueOf(0));
            //Log.v("himan", result.getString(1));
        }
        adapter = new ListAdapter(productsList, pricesList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }

    private int sum(List<String> list){
        int sum = 0;
        for(String x : list){
            sum += Integer.parseInt(x);
        }
        return sum;
    }
}
