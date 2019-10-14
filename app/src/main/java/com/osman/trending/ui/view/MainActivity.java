package com.osman.trending.ui.view;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.osman.trending.R;
import com.osman.trending.ui.adapter.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragmentsContainer) != null) {

            if (savedInstanceState != null) {
                return;
            }

            MainTerndingFragment fragment = new MainTerndingFragment();
            fragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentsContainer, fragment).commitNow();
        }
    }
}