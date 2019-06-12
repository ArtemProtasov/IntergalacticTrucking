package com.example.intergalactictrucking.ui.settings;

import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.intergalactictrucking.R;
import com.example.intergalactictrucking.base.BaseFragment;
import com.example.intergalactictrucking.utils.UtilsDialog;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SettingsFragment extends BaseFragment {

    Button buttonchangeName;
    Button buttonchangePassword;
    Button buttonlogOut;
    ConstraintLayout rootView;
    TextView textViewUserName;
    TextView textViewUserEmail;
    TextView textViewUserPhoneNumber;
    private NavController navController;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference("userProfile");

    @Override
    protected int contentResource() {
        return R.layout.fragment_settings;
    }

    @Override
    protected void setupViewById() {
        navController = Navigation.findNavController(getActivity(), R.id.main_nav_fragment);

        rootView = getView().findViewById(R.id.rootView);

        textViewUserName = getView().findViewById(R.id.userName);
        textViewUserEmail = getView().findViewById(R.id.userEmail);
        textViewUserPhoneNumber = getView().findViewById(R.id.userPhoneNumber);

        buttonchangeName = getView().findViewById(R.id.changeName);
        buttonchangePassword = getView().findViewById(R.id.changePassword);
        buttonlogOut = getView().findViewById(R.id.logOut);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void setupView() {
        buttonlogOut.setOnClickListener(v -> {
            firebaseAuth.signOut();
            navController.navigate(R.id.action_settingsFragment_to_authActivity);
        });

        databaseReference.child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HashMap<String, String> userProfile = (HashMap<String, String>) dataSnapshot.getValue();
                textViewUserName.setText(userProfile.get("userFullName"));
                textViewUserPhoneNumber.setText(userProfile.get("userPhoneNumber"));
                textViewUserEmail.setText(firebaseAuth.getCurrentUser().getEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        buttonchangeName.setOnClickListener(v -> {

            View view = getLayoutInflater().inflate(R.layout.alert_dialog_change_user_phone_number, null);
            new AlertDialog.Builder(getActivity())
                    .setView(view)
                    .setPositiveButton("Изменить", (dialog, which) -> {
                        EditText editTextPhoneNumber = view.findViewById(R.id.editTextPhoneNumber);
                        databaseReference.child(firebaseAuth.getCurrentUser().getUid()).child("userPhoneNumber").setValue(editTextPhoneNumber.getText())
                                .addOnCompleteListener(task -> {
                                    if (task.isSuccessful()) {
                                        Snackbar.make(
                                                rootView,
                                                "Вы успешно обновили номер телефона!",
                                                Snackbar.LENGTH_SHORT
                                        ).show();
                                    } else {
                                        UtilsDialog.showBasicDialog(getActivity(), "Ok", "Не удалось сменить номер телефона. Повторите попытку...").show();
                                    }
                                });
                    })
                    .setNegativeButton("Отмена", (dialog, which) -> dialog.dismiss())
                    .create()
                    .show();
        });

        buttonchangePassword.setOnClickListener(v -> {
            View view = getLayoutInflater().inflate(R.layout.alert_dialog_change_user_password, null);
            new AlertDialog.Builder(getActivity())
                    .setView(view)
                    .setPositiveButton("Изменить", (dialog, which) -> {
                        EditText editTextUserPassword = view.findViewById(R.id.editTextPassword);
                        firebaseAuth.getCurrentUser().updatePassword(editTextUserPassword.getText().toString())
                                .addOnCompleteListener(task -> {
                                    if (task.isSuccessful()) {
                                        Snackbar.make(
                                                rootView,
                                                "Вы успешно изменили пароль!",
                                                Snackbar.LENGTH_SHORT
                                        ).show();
                                    } else {
                                        UtilsDialog.showBasicDialog(getActivity(), "Ok", "Не удалось изменить пароль. Повторите попытку...").show();
                                    }
                                });
                    })
                    .setNegativeButton("Отмена", (dialog, which) -> dialog.dismiss())
                    .create()
                    .show();
        });
    }
}
