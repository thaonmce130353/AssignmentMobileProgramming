package com.demo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.demo.assignmentmobileprogramming.R;
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

    TypeDatabase dbType;
    ProductDatabase dbProduct;
    ImageDatabase dbImage;
    OrderDatabase dbOrder;
    OrderDetailDatabase dbOrderDetail;

    ArrayList<Type> types;
    ArrayList<Product> products;
    ArrayList<Order> orders;
    ArrayList<OrderDetail> orderDetails;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_fragment, container, false);
        init(view);

        types.add(new Type(-1, "ALL", true));
        types.addAll(dbType.getAllType());
        products = dbProduct.getAllProduct();
        orders = dbOrder.getAllOrder();
        orderDetails = dbOrderDetail.getAllOrderDetail();
        Toast.makeText(this.getActivity(), "Success", Toast.LENGTH_SHORT).show();
        return view;
    }

    /**
     * init variable
     * @param view
     */
    public void init(View view){
        listView = view.findViewById(R.id.listView);
        txtCurrent = view.findViewById(R.id.txtCurrentOrder);
        txtHistory = view.findViewById(R.id.txtHistory);

        dbType = new TypeDatabase(getActivity());
        dbProduct = new ProductDatabase(getActivity());
        dbImage = new ImageDatabase(getActivity());
        dbOrder = new OrderDatabase(getActivity());
        dbOrderDetail = new OrderDetailDatabase(getActivity());

        orders = new ArrayList<>();
        orderDetails = new ArrayList<>();
        products = new ArrayList<>();
        types = new ArrayList<>();
    }
}
