package com.example.scoretracker.admin.cricket;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.scoretracker.R;
import com.example.scoretracker.admin.AdminActivity;
import com.example.scoretracker.databinding.FragmentAdminCricketBinding;

public class AdminCricketFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentAdminCricketBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_admin_cricket, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavHostFragment navHostFragment = (NavHostFragment) getChildFragmentManager().findFragmentById(R.id.fragment_container_nav_host);
        NavigationUI.setupActionBarWithNavController((AdminActivity)requireActivity(), navHostFragment.getNavController());

    }
}