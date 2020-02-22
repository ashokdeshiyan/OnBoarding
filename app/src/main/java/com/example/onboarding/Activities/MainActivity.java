package com.example.onboarding.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.onboarding.Adapters.MyAdapter;
import com.example.onboarding.R;
import com.example.onboarding.databinding.ActivityMainBinding;

import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;


    private int     pageposition =0;
    private MyAdapter myAdapter;
    private LinearLayout mdotlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);





        myAdapter = new MyAdapter(this);
        binding.viewpager.setAdapter(myAdapter);

        CircleIndicator  indicator = findViewById(R.id.indicator);
        indicator.setViewPager(binding.viewpager);



        binding.skipbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, HomeActvity.class);
                startActivity(i);
            }
        });


        binding.viewpager.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pageposition =position;

                if (pageposition==2){
                    binding.skipbtn.setText("Finish");
                }else{
                    binding.skipbtn.setText("Skip");
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        final Handler handler =new Handler();

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                binding.viewpager.setCurrentItem(pageposition++,true);
            }
        };
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        },500,800);

    }


}
