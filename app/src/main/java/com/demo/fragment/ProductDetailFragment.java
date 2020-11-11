package com.demo.fragment;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.assignmentmobileprogramming.R;
import com.demo.custom.adapter.ProductListAdapter;
import com.demo.database.ImageDatabase;
import com.demo.database.ProductDatabase;
import com.demo.object.info.Image;
import com.demo.object.info.Product;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import java.util.ArrayList;

public class ProductDetailFragment extends Fragment implements ImageListener {
    private TextView txtNameFood, txtPrice, txtDescription;
    ImageDatabase dbImage;
    ProductDatabase dbProduct;
    ArrayList<Bitmap> imgsForCarousel;
    Product p;
    ArrayList<Product> products;
    CarouselView carouselView;
    ProductListAdapter productAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_detail_fragment, container, false);
        dbProduct = new ProductDatabase(getActivity());
        dbImage = new ImageDatabase(getActivity());


        Bundle bundle = getArguments();
        Toast.makeText(getActivity(), "" + bundle.getInt("productId"), Toast.LENGTH_SHORT).show();

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


        txtDescription.setText(p.getDescription());
        txtPrice.setText(String.format("%1$,.2f$", p.getPrice()));
        txtNameFood.setText(p.getName());
        imgsForCarousel = new ArrayList<>();

        ArrayList<Image> images = dbImage.getImageByProductId(p.getId());
        if (images.size() != 0)
            imgsForCarousel.add(BitmapFactory.decodeByteArray(images.get(0).getUrl(), 0, images.get(0).getUrl().length));
        carouselView = view.findViewById(R.id.carouselProductDetail);

        carouselView.setPageCount(imgsForCarousel.size());
    }

    @Override
    public void setImageForPosition(int position, ImageView imageView) {
        imageView.setImageBitmap(imgsForCarousel.get(position));
    }
}
