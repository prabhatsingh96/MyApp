package com.example.fluper.myapp.appUtil;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.fluper.myapp.R;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity {

    private DrawerLayout dl;
    private ImageView humburger;
    private ImageView edit;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private GridView gv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        navigationDrawer();

        SearchView searchView = findViewById (R.id.notification_icon);
        gv = findViewById (R.id.mGridView);

        List<String> galaxies=new ArrayList<> ();
        galaxies.add("Cartwheel");
        galaxies.add("Whirlpool");
        galaxies.add("Andromeda I");
        galaxies.add("Andromeda II");
        galaxies.add("Sombrero");
        galaxies.add("Messier 81");
        galaxies.add("Messier 87");
        galaxies.add("Canis Majos Over-density");
        galaxies.add("Messier 92");
        galaxies.add("Black Eye");
        galaxies.add("Centaurus A");
        galaxies.add("Centaurus B");
        galaxies.add("Milky Way");
        galaxies.add("IC 1011");
        galaxies.add("Star Bust");
        galaxies.add("Hoag's Object");
        galaxies.add("Pinwheel");
        galaxies.add("Triangulum");
        galaxies.add("Cosmos Redshift");
        galaxies.add("Ursa Minor");
        galaxies.add("Virgo Stellar-Stream");

        //ADAPTER
        final ArrayAdapter adapter=new ArrayAdapter<String> (this,android.R.layout.simple_list_item_1,galaxies);
        gv.setAdapter(adapter);

        searchView.setOnQueryTextListener (new SearchView.OnQueryTextListener () {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                gv.setVisibility (View.VISIBLE);
                return false;
            }
        });

    }


    public void navigationDrawer() {

        dl = findViewById(R.id.drawer_layout);
        // nv = findViewById(R.id.navigation_id);
        //   nv.setVisibility(View.GONE);

        toolbar = findViewById(R.id.include_tool_layout);
        humburger = toolbar.findViewById(R.id.side_bar_icon);
     //   edit = toolbar.findViewById(R.id.notification_icon);
      //  edit.setVisibility(View.GONE);

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