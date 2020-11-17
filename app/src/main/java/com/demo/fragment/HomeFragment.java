package com.demo.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.assignmentmobileprogramming.MainActivity;
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

import java.util.ArrayList;

public class HomeFragment extends Fragment implements ImageClickListener, View.OnClickListener, ImageListener {
    private Button btnSearch;
    private EditText txtSearch;
    FrameLayout frameLayoutSearch;
    CarouselView carouselView;
    TypeDatabase dbType;
    ProductDatabase dbProduct;
    ArrayList<Type> types;
    ArrayList<Product> products;
    ArrayList<Product> productSaleOff;
    ButtonListAdapter typeAdapter;
    ProductListAdapter productAdapter;
    ImageDatabase dbImage;
    ArrayList<Bitmap> imgsForCarousel;
    int[] sampleImages = {R.drawable.lau, R.drawable.soup, R.drawable.dessert, R.drawable.tradao, R.drawable.xao};

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.home_fragment, container, false);
        try {
            dbType = new TypeDatabase(getActivity());
            dbProduct = new ProductDatabase(getActivity());
            dbImage = new ImageDatabase(getActivity());
            types = new ArrayList<>();
            types.add(new Type(-1, "ALL", true));
            types.addAll(dbType.getAllType());
            products = dbProduct.getAllProduct();
            btnSearch = view.findViewById(R.id.btnSearch);
            txtSearch = view.findViewById(R.id.txtSearch);
            frameLayoutSearch = view.findViewById(R.id.frameSearch);

            frameLayoutSearch.setTranslationZ(1);

            btnSearch.setOnClickListener(this);

            init(view);
            carouselView.setImageListener(this);

            carouselView.setImageClickListener(this);

            RecyclerView recyclerViewType = view.findViewById(R.id.recycle_view_type);
            recyclerViewType.setHasFixedSize(true);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            recyclerViewType.setLayoutManager(layoutManager);

            typeAdapter = new ButtonListAdapter(types, getActivity().getApplicationContext(), this);

            recyclerViewType.setAdapter(typeAdapter);

            RecyclerView recyclerViewProduct = view.findViewById(R.id.recycle_view_product_detail);
            recyclerViewProduct.setHasFixedSize(true);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
            recyclerViewProduct.setLayoutManager(gridLayoutManager);

            productAdapter = new ProductListAdapter(products, getActivity().getApplicationContext());

            recyclerViewProduct.setAdapter(productAdapter);
        } catch (Exception e) {

        }
        return view;

    }

    @Override
    public void onClick(int position) {
        try {
            MainActivity.openDetailFragment(productSaleOff.get(position).getId());
        } catch (Exception e) {

        }
    }

    private void init(View view) {
        try {
            productSaleOff = dbProduct.getAllProductSaleOff();
            imgsForCarousel = new ArrayList<>();
            for (Product p : productSaleOff) {
                ArrayList<Image> images = dbImage.getImageByProductId(p.getId());
                if (images.size() != 0)
                    imgsForCarousel.add(BitmapFactory.decodeByteArray(images.get(0).getUrl(), 0, images.get(0).getUrl().length));
            }
            carouselView = view.findViewById(R.id.carouselView);
            if (imgsForCarousel.size() > 5)
                carouselView.setPageCount(5);
            else
                carouselView.setPageCount(imgsForCarousel.size());
        } catch (Exception e) {

        }
    }

    public void updateListProduct(int tId) {
        try {
            products.clear();
            if (tId == -1)
                products.addAll(dbProduct.getAllProduct());
            else
                products.addAll(dbProduct.getProductByType(tId));
            productAdapter.notifyDataSetChanged();
        } catch (Exception e) {

        }
    }

    /**
     * Onclick button
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        try {
            products.clear();
            String searchStr = txtSearch.getText().toString();
            products.addAll(dbProduct.getProductByName(searchStr));
            for (Type type : types) {
                String[] typeName = type.getName().split("\\s+");
                for (String s : typeName) {
                    if (s.toLowerCase().equals(searchStr.toLowerCase())) {
                        products.addAll(dbProduct.getProductByType(type.getId()));
                        break;
                    }
                }
            }
            productAdapter.notifyDataSetChanged();
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        } catch (Exception e) {

        }
    }

    @Override
    public void setImageForPosition(int position, ImageView imageView) {
        try {
            imageView.setImageBitmap(imgsForCarousel.get(position));
        } catch (Exception e) {

        }
    }
}
