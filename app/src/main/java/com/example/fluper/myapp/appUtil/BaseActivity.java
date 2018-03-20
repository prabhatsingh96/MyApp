package com.example.fluper.myapp.appUtil;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.example.fluper.myapp.R;

public class BaseActivity extends AppCompatActivity {

    private DrawerLayout dl;
    private ImageView humburger;
    private ImageView edit;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        navigationDrawer();
    }


    public void navigationDrawer() {

        dl = findViewById(R.id.drawer_layout);
        // nv = findViewById(R.id.navigation_id);
        //   nv.setVisibility(View.GONE);

        toolbar = findViewById(R.id.include_tool_layout);
        humburger = toolbar.findViewById(R.id.side_bar_icon);
        edit = toolbar.findViewById(R.id.notification_icon);
        edit.setVisibility(View.GONE);

        ActionBarDrawerToggle abdt = new ActionBarDrawerToggle(this, dl, toolbar, R.string.open, R.string.close);
        dl.addDrawerListener(abdt);


        humburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // nv.setVisibility(View.VISIBLE);
                dl.openDrawer(Gravity.START);

            }
        });
    }

}