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

import com.example.scoretracker.databinding.FragmentSignupBinding;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class SignupFragment extends Fragment {
    private FragmentSignupBinding binding;
    private FirebaseApp firebaseApp;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseApp=FirebaseApp.initializeApp(requireContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.signupButton.setOnClickListener(v -> onSignup());
    }
    private void onSignup()
    {
        String emailID = binding.emailEditText.getText().toString().trim();
        String password = binding.passwordEditText.getText().toString().trim();
        String confirm_password = binding.confirmPasswordEditText.getText().toString().trim();

        if(!password.equals(confirm_password)) {
            Toast.makeText(requireContext(), "Passwords don't match!", Toast.LENGTH_SHORT).show();
            return;
        }

        binding.signupButton.setVisibility(View.GONE);

        FirebaseAuth.getInstance(firebaseApp).createUserWithEmailAndPassword(emailID,password).addOnCompleteListener(task -> {
            if(task.isComplete()) {
                if (task.isSuccessful()) {
                    Toast.makeText(requireContext(), "Sign up successful", Toast.LENGTH_SHORT).show();
                    onSignupSuccessful();
                } else {
                    Exception ex = task.getException();
                    if (ex instanceof FirebaseAuthWeakPasswordException) {
                        Toast.makeText(requireContext(), "Weak password!", Toast.LENGTH_SHORT).show();
                    }
                }
                binding.signupButton.setVisibility(View.VISIBLE);
            }

        });

    }

    private void onSignupSuccessful() {
        // Auto loggedin
        String UID=FirebaseAuth.getInstance(firebaseApp).getUid();
        Map<String,Object> data=new HashMap<>();
        data.put("userType","USERS");

        FirebaseFirestore.getInstance(firebaseApp).collection("users").document(UID).set(data, SetOptions.merge());
        Activity activity = requireActivity();
        ((AuthActivity)activity).startUserActivity();
    }


}