package com.hunter.addemo;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private List<Fragment> mFragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.viewPage);
        mFragments = new ArrayList<>();
        mFragments.add(SingleVideoFragment.getInstance(""));
        mFragments.add(ImageFragment.getInstance(R.raw.a));
        mFragments.add(ImageFragment.getInstance(R.raw.b));
        mFragments.add(ImageFragment.getInstance(R.raw.c));
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(),mFragments);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(4);
    }
}
