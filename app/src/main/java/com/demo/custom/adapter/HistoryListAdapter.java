package com.demo.custom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.assignmentmobileprogramming.R;
import com.demo.object.info.Order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HistoryListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Order> orders;

    public HistoryListAdapter(Context context, int layout, List<Order> orders) {
        this.context = context;
        this.layout = layout;
        this.orders = orders;
    }

    @Override
    public int getCount() {
        return orders.size();
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

        SimpleDateFormat fmtOut = new SimpleDateFormat("dd-MM-yyyy");

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);

        //anh xa view
        TextView txtName = (TextView) convertView.findViewById(R.id.nameFood);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.priceFood);
        TextView txtTime = (TextView) convertView.findViewById(R.id.timeOrder);

        //gan gia tri
        Order order = orders.get(position);
        txtName.setText("Order code: " + order.getOrderId());
        txtPrice.setText("$" + order.getTotalMoney());
        txtTime.setText(order.getOrderDay());

        return convertView;
    }
}
