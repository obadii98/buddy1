package com.bracketsps.buddy_final;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ActionMode;
import android.widget.Button;

import com.bracketsps.buddy_final.ui.allFragment.FragmentMain;
import com.bracketsps.buddy_final.ui.allFragment.LoginSectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.bracketsps.buddy_final.ui.allFragment.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {
    public static ViewPager s;
    public static SectionsPagerAdapter sectionsPagerAdapter;
    public static LoginSectionsPagerAdapter loginSectionsPagerAdapter;
    public static ViewPager viewPager;

    @Override
    public void onBackPressed() {
        s.setCurrentItem(0, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginSectionsPagerAdapter = new LoginSectionsPagerAdapter(this,getSupportFragmentManager());
        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        s = viewPager;
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

    }


}
