package com.example.scoretracker;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.scoretracker.databinding.FragmentLoginBinding;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private FirebaseApp firebaseApp;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseApp = FirebaseApp.initializeApp(requireContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
       return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.loginButton.setOnClickListener(v -> onLogin());

        binding.signupButton.setOnClickListener(v -> {
            NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.action_loginFragment_to_signupFragment);
        });
    }

    private void onLogin() {
        String emailID = binding.emailEditText.getText().toString().trim();
        String password = binding.passwordEditText.getText().toString().trim();

        binding.loginButton.setVisibility(View.GONE);

        FirebaseAuth.getInstance(firebaseApp).signInWithEmailAndPassword(emailID, password).addOnCompleteListener(task -> {
            if (task.isComplete()) {
                if (task.isSuccessful()) {
                    onLoginSuccess();
                } else {
                    Toast.makeText(requireContext(), "Log in failed", Toast.LENGTH_SHORT).show();
                }
                binding.loginButton.setVisibility(View.VISIBLE);
            }
        });

    }

    private void onLoginSuccess() {
        String userId = FirebaseAuth.getInstance(firebaseApp).getUid();
        FirebaseFirestore.getInstance(firebaseApp).collection("users")
                .document(userId)
                .get().addOnCompleteListener(task -> {
            if (task.isComplete() && task.isSuccessful()) {
                DocumentSnapshot snapshot = task.getResult();
                String userType = snapshot.getString("userType");
                Activity activity= requireActivity();
                if (userType.equals("ADMIN")) {
                    Toast.makeText(requireContext(), "Logged in as admin", Toast.LENGTH_SHORT).show();

                    ((AuthActivity)activity).startAdminActivity();
                } else {
                    Toast.makeText(requireContext(), "Logged in as user", Toast.LENGTH_SHORT).show();
                    ((AuthActivity)activity).startUserActivity();
                }
            }
        });
    }
}