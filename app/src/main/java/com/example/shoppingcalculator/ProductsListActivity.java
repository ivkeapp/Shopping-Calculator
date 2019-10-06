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

        //final LinearLayout root = findViewById(R.id.root2);
        db = new DatabaseHelper(this);
        delete = findViewById(R.id.delete);
        bSum = findViewById(R.id.calc);
        bSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum.setText(String.valueOf(sum(pricesList)));
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteAll();
            }
        });

        //tv = findViewById(R.id.tv);
        Cursor result = db.getAllProducts();
        if(result.getCount() == 0){
            return;
        }
        //StringBuffer strBuff = new StringBuffer();
        while(result.moveToNext()){
            productsList.add(result.getString(1));
            pricesList.add(String.valueOf(0));
            Log.v("himan", result.getString(1));
            //strBuff.append("ID: " + result.getString(0) + "\n");
            //strBuff.append("Name: " + result.getString(1) + "\n");
        }
        //pricesList  = new ArrayList<>(productsList.size());
        adapter = new ListAdapter(productsList, pricesList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);



//                        for (int i = 0; i < productsList.size(); i++) {
//                            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                            final View rowView = inflater.inflate(R.layout.list, null);
//                            root.addView(rowView, root.getChildCount() - 1);
//                    View view = root.getChildAt(i);
//                    TextView et_value = view.findViewById(R.id.list_item);
//                    et_value.setText(productsList.get(i));
//                    int value = 0;
//                    if(et_value!=null){
//                        try {
//                            value = Integer.parseInt(et_value.getText().toString().trim());
//                        }catch (NumberFormatException e){
//                            Toast.makeText(this, "Unesite broj", Toast.LENGTH_SHORT).show();
//                        }
//
//                    //iSum[0] += value;
//                    //list.add(value);
//                    Log.v("Values", " "+value);
//                    }
//                }
                //sum.setText(String.valueOf(sum(list)));

        //tv.setText(strBuff);
    }

    private int sum(List<String> list){
        int sum = 0;
        for(String x : list){
            sum += Integer.parseInt(x);
        }
        return sum;
    }
}
