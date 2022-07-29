package com.example.scoretracker.admin.cricket;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.scoretracker.R;
import com.example.scoretracker.adapter.CricketMatchAdapter;
import com.example.scoretracker.databinding.FragmentAdminCricketMatchesBinding;
import com.example.scoretracker.mock.MockData;

public class AdminCricketMatchesFragment extends Fragment {

    private FragmentAdminCricketMatchesBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_admin_cricket_matches, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CricketMatchAdapter adapter = new CricketMatchAdapter(requireContext(), MockData.matches);
        binding.recyclerView.setAdapter(adapter);
    }

    public void addMatch() {
        // TODO: Navigate to AddMatchFragment
    }
}