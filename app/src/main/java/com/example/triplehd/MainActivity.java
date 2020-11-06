package com.example.triplehd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FrameLayout fragmentMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Lấy id của các đối tượng
        getObject();
        //Setting cho menu của navigation
        actionToolBar();
        //CLick items trong menu của navigations
        actionNavigation();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentMain,new FragmentHome()).commit();
        navigationView.setCheckedItem(R.id.nav_home);

    }

    private void getObject() {
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        fragmentMain = findViewById(R.id.fragmentMain);
    }

    private void actionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_action_menu);
        toolbar.setLogo(R.drawable.logo);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    public void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }
    private void actionNavigation() {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        //Highlight item được lựa chọn
                        menuItem.setChecked(true);
                        //Thay đổi Layout khi click vào items
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        Fragment fragment = null;
                        int id = menuItem.getItemId();
                        switch (id) {
                            case R.id.nav_home:
                                fragment = new FragmentHome();
                                break;
                            case R.id.nav_category:
                                fragment = new FragmentCategory();
                                break;
                            case R.id.nav_list_movie:
                                fragment = new FragmentListmovie();
                                break;
                            case R.id.nav_login:
                                fragment = new FragmentLogin();
                                break;
                        }
                        fragmentTransaction.replace(R.id.fragmentMain,fragment);
                        fragmentTransaction.commit();
                        //Đóng Drawer khi chọn vào items
                        drawerLayout.closeDrawers();

                        return true;
                    }
                });
    }

    //Thêm button search bên phải giao diện
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    //Select button search trên giao diện
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.btn_search) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Fragment fragment = null;
            fragment = new FragmentSearch();
            fragmentTransaction.replace(R.id.fragmentMain,fragment);
            fragmentTransaction.commit();
        }
        return true;
    }
}