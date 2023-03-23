package com.example.compete6.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyFragmentAdapter extends FragmentStateAdapter {
    private List<Fragment> fragmentList=new ArrayList<>();
    public MyFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle,List<Fragment> fragments) {
        super(fragmentManager, lifecycle);
        this.fragmentList=fragments;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList==null?null:fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList==null?0:fragmentList.size();
    }
}
