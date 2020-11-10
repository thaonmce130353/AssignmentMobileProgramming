package com.demo.fragment;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.assignmentmobileprogramming.R;
import com.demo.custom.adapter.ButtonListAdapter;
import com.demo.custom.adapter.ProductListAdapter;
import com.demo.database.ImageDatabase;
import com.demo.database.ProductDatabase;
import com.demo.database.TypeDatabase;
import com.demo.object.info.Image;
import com.demo.object.info.Product;
import com.demo.object.info.Type;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class HomeFragment extends Fragment implements ImageClickListener {
    private Button btnSearch;
    FrameLayout frameLayoutSearch;
    CarouselView carouselView;
    TypeDatabase dbType;
    ProductDatabase dbProduct;
    ImageDatabase dbImage;
    ArrayList<Type> types;
    ArrayList<Product> products;
    int[] sampleImages = {R.drawable.lau, R.drawable.soup, R.drawable.dessert, R.drawable.tradao, R.drawable.xao};

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        dbType = new TypeDatabase(getActivity());
        dbProduct = new ProductDatabase(getActivity());
        dbImage = new ImageDatabase(getActivity());
//        if (dbImage.getAllImage().size() == 0) {
//            BitmapDrawable bitmapDrawable = (BitmapDrawable) getActivity().getResources().getDrawable(R.drawable.tiger);
//            Bitmap bitmap = bitmapDrawable.getBitmap();
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
//            byte[] url = byteArrayOutputStream.toByteArray();
//            dbImage.addNew(new Image(0, url, true, 1));
//            Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
//        }
        types = dbType.getAllType();
        products = dbProduct.getAllProduct();
        Toast.makeText(getActivity(), "" + products, Toast.LENGTH_SHORT).show();
        btnSearch = view.findViewById(R.id.btnSearch);
        frameLayoutSearch = view.findViewById(R.id.frameSearch);

        frameLayoutSearch.setTranslationZ(1);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Click", Toast.LENGTH_SHORT).show();
            }
        });

        carouselView = view.findViewById(R.id.carouselView);
        carouselView.setPageCount(5);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(sampleImages[position]);
            }
        });

        carouselView.setImageClickListener(this);

        RecyclerView recyclerViewType = view.findViewById(R.id.recycle_view_type);
        recyclerViewType.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewType.setLayoutManager(layoutManager);

        ButtonListAdapter adapter = new ButtonListAdapter(types, getActivity().getApplicationContext());

        recyclerViewType.setAdapter(adapter);

        RecyclerView recyclerViewProduct = view.findViewById(R.id.recycle_view_product);
        recyclerViewProduct.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerViewProduct.setLayoutManager(gridLayoutManager);

        ProductListAdapter productListAdapter = new ProductListAdapter(products, getActivity().getApplicationContext());

        recyclerViewProduct.setAdapter(productListAdapter);
        return view;

    }

    @Override
    public void onClick(int position) {
        Toast.makeText(getActivity(), "Click " + position, Toast.LENGTH_SHORT).show();
    }

}
