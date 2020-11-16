package com.demo.fragment;

import android.os.Bundle;


import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.demo.assignmentmobileprogramming.R;
import com.demo.custom.adapter.OrderDetailListAdapter;
import com.demo.database.OrderDatabase;
import com.demo.database.OrderDetailDatabase;
import com.demo.object.info.OrderDetail;

import java.util.ArrayList;

public class OrderDetailFragment extends Fragment {

    OrderDetailDatabase orderDetailDatabase;
    ArrayList<OrderDetail> orderDetail;
    ListView listView;
    TextView txtCode;
    OrderDetailListAdapter orderDetailListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_detail, container, false);
        init(view);

        orderDetailListAdapter = new OrderDetailListAdapter(getActivity(), R.layout.history_orderdetail_list, orderDetail);
        listView.setAdapter(orderDetailListAdapter);
        return view;
    }

    public void init(View view){
        orderDetailDatabase = new OrderDetailDatabase(getActivity());

        listView = view.findViewById(R.id.orderDetailListView);
        txtCode = (TextView) view.findViewById(R.id.orderCode);

        orderDetailDatabase = new OrderDetailDatabase(getActivity());

        Bundle bundle = getArguments();
        orderDetail = orderDetailDatabase.getAllOrderDetailByOrderId(bundle.getInt("orderId"));
        txtCode.setText("Code: " + bundle.getInt("orderId"));
    }
}