package com.demo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.demo.assignmentmobileprogramming.R;
import com.demo.custom.adapter.HistoryListAdapter;
import com.demo.database.ImageDatabase;
import com.demo.database.OrderDatabase;
import com.demo.database.OrderDetailDatabase;
import com.demo.database.ProductDatabase;
import com.demo.database.TypeDatabase;
import com.demo.object.info.Order;
import com.demo.object.info.OrderDetail;
import com.demo.object.info.Product;
import com.demo.object.info.Type;

import java.util.ArrayList;


public class HistoryFragment  extends Fragment {

    private ListView listView;
    private TextView txtCurrent, txtHistory;

    OrderDatabase dbOrder;

    ArrayList<Order> orders;

    HistoryListAdapter historyListAdapter;

    private static final int userId = 2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_fragment, container, false);

        init(view);

        historyListAdapter = new HistoryListAdapter(getActivity(), R.layout.history_order_list, orders);
        listView.setAdapter(historyListAdapter);
        return view;
    }

    public void init(View view){
        listView = view.findViewById(R.id.listView);
        dbOrder = new OrderDatabase(getActivity());
        orders = dbOrder.getAllOrderByUserId(userId);
    }
}
