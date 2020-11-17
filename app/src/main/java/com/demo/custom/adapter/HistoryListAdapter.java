package com.demo.custom.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.assignmentmobileprogramming.MainActivity;
import com.demo.assignmentmobileprogramming.R;
import com.demo.object.info.Order;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HistoryListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Order> orders;
    private SimpleDateFormat format;

    public HistoryListAdapter(Context context, int layout, List<Order> orders) {
        this.context = context;
        this.layout = layout;
        this.orders = orders;
        format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        SimpleDateFormat fmtOut = new SimpleDateFormat("dd-MM-yyyy");

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);

        //anh xa view
        TextView txtName = (TextView) convertView.findViewById(R.id.nameFood);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.priceFood);
        TextView txtTime = (TextView) convertView.findViewById(R.id.timeOrder);
        TextView txtStatus = (TextView) convertView.findViewById(R.id.orderStatus);

        //gan gia tri
        Order order = orders.get(position);
        txtName.setText("Order code: " + order.getOrderId());
        txtPrice.setText("$" + order.getTotalMoney());
        txtTime.setText(order.getOrderDay());

        switch (order.getStatus()){
            case 0: txtStatus.setText("Chờ xác nhận"); break;
            case 1: txtStatus.setText("Đã xác nhận"); break;
            case 2: txtStatus.setText("Đã thanh toán"); break;
            default: txtStatus.setText("Wrong data!"); break;
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    MainActivity.openDetailOrderFragment(orders.get(position).getOrderId());
                } catch (Exception e) {
                    Log.i("HistoryListAdapter: ", e.getMessage());
                }
            }
        });
        return convertView;
    }
}
