package com.demo.custom.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.assignmentmobileprogramming.R;
import com.demo.database.ImageDatabase;
import com.demo.object.info.Image;
import com.demo.object.info.Product;

import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {
    ArrayList<Product> products;
    Context context;
    ImageDatabase dbImage;

    public ProductListAdapter(ArrayList<Product> products, Context context) {
        this.products = products;
        this.context = context;
        dbImage = new ImageDatabase(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.food_card_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ArrayList<Image> images = dbImage.getAllImage();
        Toast.makeText(context, "" + images.size(), Toast.LENGTH_SHORT).show();
        if (images.size() != 0) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(images.get(0).getUrl(), 0, images.get(0).getUrl().length);
            holder.imgFood.setImageBitmap(bitmap);
        } else
            holder.imgFood.setImageResource(R.drawable.dessert);
        holder.txtFoodName.setText(products.get(position).getName());
        holder.txtPrice.setText(String.format("%1$,.2f$", products.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFood;
        TextView txtFoodName, txtPrice, txtBuy;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFood = itemView.findViewById(R.id.imgFood);
            txtFoodName = itemView.findViewById(R.id.txtFoodName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtBuy = itemView.findViewById(R.id.txtBuy);

        }
    }
}
