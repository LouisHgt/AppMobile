package com.filrouge.iut.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.filrouge.iut.fragments.CollectionFragment;
import com.filrouge.iut.fragments.CustomizationFragment;
import com.filrouge.iut.fragments.OpeningFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new CollectionFragment();
            case 1:
                return new OpeningFragment();
            case 2:
                return new CustomizationFragment();
            default:
                return new OpeningFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3; // Nombre d'onglets
    }
}