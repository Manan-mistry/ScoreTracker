package com.example.scoretracker.admin;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.scoretracker.R;

public class AdminActivity extends AppCompatActivity {

    private NavHostFragment activityNavHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        activityNavHost = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.admin_nav_host);

    }

    @Override
    public boolean onSupportNavigateUp() {

        NavHostFragment fragmentNavHost =
                (NavHostFragment) activityNavHost.getChildFragmentManager()
                        .getFragments().get(0)
                        .getChildFragmentManager()
                        .findFragmentById(R.id.fragment_container_nav_host);
        NavController fragmentController = fragmentNavHost.getNavController();

        return fragmentController.navigateUp() || super.onSupportNavigateUp();
    }
}