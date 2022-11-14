package com.example.keephere;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Report> list;

    public MyAdapter(Context context, ArrayList<Report> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Report report = list.get(position);
        holder.barcode.setText(report.getBarcode());
        holder.date.setText(report.getDate());
        holder.fittingRoom.setText(report.getFittingRoom());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView barcode, date, fittingRoom;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            barcode = itemView.findViewById(R.id.tvBarcode);
            date = itemView.findViewById(R.id.tvDate);
            fittingRoom = itemView.findViewById(R.id.tvFittingRoom);
        }
    }
}
