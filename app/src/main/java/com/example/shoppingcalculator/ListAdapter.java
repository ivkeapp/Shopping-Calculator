package com.example.shoppingcalculator;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private List<String> list;
    private List<String> priceList;
    private Context mContext;

    public ListAdapter(List<String> list, List<String> priceList, Context mContext) {
        this.list = list;
        this.priceList = priceList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ListAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list, viewGroup, false);
        return new ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListAdapter.ListViewHolder listViewHolder, final int i) {
        listViewHolder.tv.setText(list.get(i));
        listViewHolder.et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                priceList.set(i, listViewHolder.et.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder{

        TextView tv;
        EditText et;

        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.list_item);
            et = itemView.findViewById(R.id.price);
        }
    }

}
