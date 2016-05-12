package com.dev.christopher.smartjug;


import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dev.christopher.smartjug.adapter.ViewPagerAdapter;
import com.dev.christopher.smartjug.adapter.transformer.ZoomOutPageTransformer;


public class RegisterActivity extends AppCompatActivity {
    private ViewPager pager;
    private ViewPagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        pager = (ViewPager)findViewById(R.id.pager);
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new Register1Fragment());
        pagerAdapter.addFragment(new Register2Fragment());
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        pager.setAdapter(pagerAdapter);
        pager.setPageTransformer(true,new ZoomOutPageTransformer());




    }

}
