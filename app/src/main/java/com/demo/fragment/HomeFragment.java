package com.demo.fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import android.app.Fragment;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.demo.assignmentmobileprogramming.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

public class HomeFragment extends Fragment implements ImageClickListener {
    private Button btnSearch;
    FrameLayout frameLayoutSearch;
    CarouselView carouselView;
    int[] sampleImages = {R.drawable.lau, R.drawable.soup, R.drawable.dessert, R.drawable.tradao, R.drawable.xao};
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

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
        return view;

    }

    @Override
    public void onClick(int position) {
        Toast.makeText(getActivity(), "Click " + position, Toast.LENGTH_SHORT).show();
    }
}
