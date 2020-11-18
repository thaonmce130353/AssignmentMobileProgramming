package com.demo.custom.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.assignmentmobileprogramming.MainActivity;
import com.demo.assignmentmobileprogramming.R;
import com.demo.database.ImageDatabase;
import com.demo.object.info.Image;
import com.demo.object.info.Product;

import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {
    ArrayList<Product> products;
    Context context;
    ImageDatabase dbImage;
    MainActivity mainActivity;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Image image = dbImage.getOneImageByProductId(products.get(position).getId());
        if (image != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(image.getUrl(), 0, image.getUrl().length);
            holder.imgFood.setImageBitmap(bitmap);
        } else
            holder.imgFood.setImageResource(R.drawable.dessert);
        holder.txtFoodName.setText(reduceString(products.get(position).getName()));
        holder.txtPrice.setText(String.format("%1$,.2f$", products.get(position).getPrice()));

        holder.txtBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               try {
                   MainActivity.openDetailFragment(products.get(position).getId());
               } catch (Exception e) {
                   Log.i("Nhan", e.getMessage());
               }
            }
        });
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

    private String reduceString(String str) {
        String[] arr = str.split("\\s+");
        String result = "";
        int words = 0;
        while (result.length() < 15 && words < arr.length){
            result += arr[words] + " ";
            words++;
        }
        if(words == arr.length)
            return result;
        return result.substring(0, result.length() - 1) + "...";
    }
}
