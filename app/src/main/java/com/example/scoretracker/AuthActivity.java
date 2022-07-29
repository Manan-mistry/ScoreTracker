package com.example.scoretracker;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.scoretracker.admin.AdminActivity;
import com.example.scoretracker.user.UserActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class AuthActivity extends AppCompatActivity {

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String uid;
        if ((uid = FirebaseAuth.getInstance().getUid()) != null) {
            FirebaseFirestore.getInstance()
                    .collection("users")
                    .document(uid).get()
                    .addOnCompleteListener(task -> {
                        if (task.isComplete() && task.isSuccessful()) {
                            DocumentSnapshot snapshot = task.getResult();
                            if (snapshot.getString("userType").equals("ADMIN")) {
                                startAdminActivity();
                            } else {
                                startUserActivity();
                            }
                        }
                    });
        }
        setContentView(R.layout.activity_auth);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_auth);
        navController = navHostFragment.getNavController();

        NavigationUI.setupActionBarWithNavController(this, navController);

    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
    public void startUserActivity(){
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);
        finish();
    }
    public void startAdminActivity(){
        Intent intent = new Intent(this, AdminActivity.class);
        startActivity(intent);
        finish();
    }
}