package com.demo.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.assignmentmobileprogramming.MainActivity;
import com.demo.assignmentmobileprogramming.R;
import com.demo.custom.adapter.ProductListAdapter;
import com.demo.database.ImageDatabase;
import com.demo.database.OrderDatabase;
import com.demo.database.OrderDetailDatabase;
import com.demo.database.ProductDatabase;
import com.demo.object.info.Image;
import com.demo.object.info.Order;
import com.demo.object.info.OrderDetail;
import com.demo.object.info.Product;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class ProductDetailFragment extends Fragment implements ImageListener {
    private TextView txtNameFood, txtPrice, txtDescription;
    private Button btnBuyDetail;
    ImageDatabase dbImage;
    ProductDatabase dbProduct;
    ArrayList<Bitmap> imgsForCarousel;
    Product p;
    ArrayList<Product> products;
    CarouselView carouselView;
    ProductListAdapter productAdapter;
    OrderDatabase dbOrder;
    OrderDetailDatabase dbOrderDetail;

    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_detail_fragment, container, false);
        dbProduct = new ProductDatabase(getActivity());
        dbImage = new ImageDatabase(getActivity());
        dbOrder = new OrderDatabase(getActivity());
        dbOrderDetail = new OrderDetailDatabase(getActivity());


        Bundle bundle = getArguments();
        p = dbProduct.findProductById(bundle.getInt("productId"));
        init(view, p);

        products = dbProduct.getProductByType(p.getTypeId());
        carouselView.setImageListener(this);

        RecyclerView recyclerViewProduct = view.findViewById(R.id.recycle_view_product_detail);
        recyclerViewProduct.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerViewProduct.setLayoutManager(gridLayoutManager);

        productAdapter = new ProductListAdapter(products, getActivity().getApplicationContext());

        recyclerViewProduct.setAdapter(productAdapter);

        return view;
    }

    private void init(View view, Product p) {
        txtDescription = view.findViewById(R.id.txtDescription);
        txtPrice = view.findViewById(R.id.txtPrice);
        txtNameFood = view.findViewById(R.id.txtFoodName);
        btnBuyDetail = view.findViewById(R.id.btnBuyDetail);

        txtDescription.setText(p.getDescription());
        txtPrice.setText(String.format("%1$,.2f$", p.getPrice()));
        txtNameFood.setText(p.getName());
        imgsForCarousel = new ArrayList<>();

        ArrayList<Image> images = dbImage.getImageByProductId(p.getId());
        if (images.size() != 0)
            imgsForCarousel.add(BitmapFactory.decodeByteArray(images.get(0).getUrl(), 0, images.get(0).getUrl().length));
        carouselView = view.findViewById(R.id.carouselProductDetail);

        carouselView.setPageCount(imgsForCarousel.size());

        btnBuyDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayDialog();
            }
        });
    }


    private void displayDialog() {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialog_order, null);
        final TextView txtQuantity = alertLayout.findViewById(R.id.txtQuantity);
        ImageView imgMinus = alertLayout.findViewById(R.id.imgMinus);
        ImageView imgAdd = alertLayout.findViewById(R.id.imgAdd);

        final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

        alert.setView(alertLayout);
        alert.setCancelable(false);

        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(txtQuantity.getText().toString());
                txtQuantity.setText(String.valueOf(quantity + 1));
            }
        });

        imgMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(txtQuantity.getText().toString());
                if (quantity > 0) {
                    txtQuantity.setText(String.valueOf(quantity - 1));
                }
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        alert.setPositiveButton("Buy", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                int quantity = Integer.parseInt(txtQuantity.getText().toString());
                if (txtQuantity.getText().toString().equals("0")) {
                    Toast.makeText(getActivity(), "Quantity must be greater than 0!", Toast.LENGTH_SHORT).show();
                } else {

                    dbOrder.addNewOrder(new Order((float) (quantity * p.getPrice()), format.format(Calendar.getInstance().getTime()), 1, MainActivity.userId, 0));
                    ArrayList<Order> orders = dbOrder.getAllOrder();
                    int idOrder = orders.get(orders.size() - 1).getOrderId();
                    float priceAfterSaleOff = (float) (p.getPrice() * (100 - p.getPercentSaleOff()) / 100F);
                    dbOrderDetail.addNewOrderDetail(new OrderDetail((float) p.getPrice(), priceAfterSaleOff, p.getPercentSaleOff(), quantity, priceAfterSaleOff * quantity, idOrder, p.getId()));

                    MainActivity.openHistoryFragment();
                    dialog.dismiss();
                }
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }


    @Override
    public void setImageForPosition(int position, ImageView imageView) {
        imageView.setImageBitmap(imgsForCarousel.get(position));
    }


}
