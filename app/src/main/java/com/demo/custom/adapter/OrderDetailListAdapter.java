package com.demo.custom.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.assignmentmobileprogramming.R;
import com.demo.database.ImageDatabase;
import com.demo.database.OrderDatabase;
import com.demo.database.ProductDatabase;
import com.demo.object.info.Image;
import com.demo.object.info.Order;
import com.demo.object.info.OrderDetail;
import com.demo.object.info.Product;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<OrderDetail> ordersDetails;
    private SimpleDateFormat format;

    ImageDatabase dbImage;
    ProductDatabase productDatabase;

    public OrderDetailListAdapter(Context context, int layout, List<OrderDetail> ordersDetails) {
        this.context = context;
        this.ordersDetails = ordersDetails;
        this.layout = layout;
        productDatabase = new ProductDatabase(context);
        dbImage = new ImageDatabase(context);
    }

    @Override
    public int getCount() {
        return ordersDetails.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);

        //anh xa view
        TextView txtName = (TextView) convertView.findViewById(R.id.nameFood);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.priceFood);
        TextView txtQuantity = (TextView) convertView.findViewById(R.id.quantity);
        ImageView imgFood = (ImageView) convertView.findViewById(R.id.orderDetailImage);

        OrderDetail orderDetail = ordersDetails.get(position);
        Product product = productDatabase.findProductById(orderDetail.getProductId());

        ArrayList<Image> images = dbImage.getImageByProductId(product.getId());
        if (images.size() != 0) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(images.get(0).getUrl(), 0, images.get(0).getUrl().length);
            imgFood.setImageBitmap(bitmap);
        } else
            imgFood.setImageResource(R.drawable.dessert);
        txtName.setText(product.getName());
        txtPrice.setText(String.format("%1$,.2f$", orderDetail.getTotal()));
        txtQuantity.setText(String.valueOf(orderDetail.getQuantity()));

        return convertView;
    }
}
