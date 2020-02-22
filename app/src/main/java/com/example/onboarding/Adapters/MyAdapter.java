package com.example.onboarding.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.onboarding.R;

public class MyAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;



    public MyAdapter(Context context) {
        this.context =context;

    }

    public  int[] imag = {
            R.drawable.eat,
            R.drawable.sleep,
            R.drawable.code,
    };

    public String[] title = {
            "Eat",
            "Sleep",
            "Code",
    };

    public String disc[]={
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                    "Lorem Ipsum has been the industry's standard"+
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
            "Lorem Ipsum has been the industry's standard",
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                    "Lorem Ipsum has been the industry's standard",
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                    "Lorem Ipsum has been the industry's standard"+
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                    "Lorem Ipsum has been the industry's standard",

    };



    @Override
    public int getCount() {
        return imag.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.pageviewley,container,false);

        ImageView slideImage = view.findViewById(R.id.imageView);
        TextView titleslide = view.findViewById(R.id.tvheading);
        TextView discslide = view.findViewById(R.id.tvdisc);

        slideImage.setImageResource(imag[position]);
        titleslide.setText(title[position]);
        discslide.setText(disc[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}
