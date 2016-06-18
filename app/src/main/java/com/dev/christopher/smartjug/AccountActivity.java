package com.dev.christopher.smartjug;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.dev.christopher.smartjug.adapter.ViewPagerAdapter;
import com.dev.christopher.smartjug.fragment.ProfileFragment;
import com.dev.christopher.smartjug.fragment.WatersFragment;


public class AccountActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    ViewPagerAdapter adapter;
    String profile,activity,chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        profile = getResources().getString(R.string.profile);
        activity = getResources().getString(R.string.activity);
        chart = getResources().getString(R.string.water_data);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.pager);

        ProfileFragment profileFragment = new ProfileFragment();
        WatersFragment watersFragment = new WatersFragment();

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(profileFragment,profile);
        adapter.addFragment(watersFragment,chart);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        

       /* pager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new Register1Fragment());

        pager.setAdapter(pagerAdapter);
        pager.setPageTransformer(true, new ZoomOutPageTransformer());*/



    }
}
