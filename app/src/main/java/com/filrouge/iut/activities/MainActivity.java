package com.filrouge.iut.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.filrouge.iut.R;
import com.filrouge.iut.adapters.ViewPagerAdapter;
import com.filrouge.iut.utils.CardManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialiser le CardManager
        CardManager.getInstance();

        // Initialiser ViewPager et son adapter
        viewPager = findViewById(R.id.viewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        // Configurer le BottomNavigationView
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        setupBottomNavigation();

        // Commencer avec la page d'ouverture (au milieu)
        viewPager.setCurrentItem(1, false);
    }

    private void setupBottomNavigation() {
        // Gérer la navigation entre les onglets
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.navigation_collection) {
                viewPager.setCurrentItem(0, true);
                return true;
            } else if (itemId == R.id.navigation_open) {
                viewPager.setCurrentItem(1, true);
                return true;
            } else if (itemId == R.id.navigation_customize) {
                viewPager.setCurrentItem(2, true);
                return true;
            }

            return false;
        });

        // Mettre à jour la navigation quand ViewPager change
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.navigation_collection);
                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.navigation_open);
                        break;
                    case 2:
                        bottomNavigationView.setSelectedItemId(R.id.navigation_customize);
                        break;
                }
            }
        });
    }
}