package com.demo.custom.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.assignmentmobileprogramming.R;
import com.demo.fragment.HomeFragment;
import com.demo.object.info.Type;

import java.util.ArrayList;

public class ButtonListAdapter extends RecyclerView.Adapter<ButtonListAdapter.ViewHolder>{
    ArrayList<Type> types;
    Context context;

    public ButtonListAdapter(ArrayList<Type> types, Context context) {
        this.types = types;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.button_custom, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.btnType.setText(types.get(position).getName());
        holder.btnType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "ABC", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return types.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button btnType;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnType = itemView.findViewById(R.id.btnType);

        }
    }
}
