package com.example.intergalactictrucking.ui.auth.registration;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.intergalactictrucking.R;
import com.example.intergalactictrucking.base.BaseFragment;
import com.example.intergalactictrucking.data.UserProfile;
import com.example.intergalactictrucking.utils.UtilsDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationFragment extends BaseFragment {

    private NavController navController;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference("userProfile");

    EditText editTextinputLogin;
    EditText editTextinputPassword;
    EditText editTextinputRepeatPassword;
    EditText editTextinputCity;
    EditText editTextinputNameCompany;
    CheckBox checkBoxinputFace;
    Button buttonsignUp;
    EditText editTextUserFullName;
    EditText editTextUserPhoneNumber;

    @Override
    protected int contentResource() {
        return R.layout.fragment_registration;
    }

    @Override
    protected void setupViewById() {
        navController = Navigation.findNavController(getActivity(), R.id.auth_nav_fragment);

        editTextinputLogin = getView().findViewById(R.id.inputLogin);
        editTextinputPassword = getView().findViewById(R.id.inputPassword);
        editTextinputRepeatPassword = getView().findViewById(R.id.inputRepeatPassword);
        editTextinputCity = getView().findViewById(R.id.inputCity);
        editTextinputNameCompany = getView().findViewById(R.id.inputNameCompany);
        checkBoxinputFace = getView().findViewById(R.id.checkboxPrivatePerson);
        buttonsignUp = getView().findViewById(R.id.signUp);
        editTextUserFullName = getView().findViewById(R.id.inputUserFullName);
        editTextUserPhoneNumber = getView().findViewById(R.id.inputPhoneNumber);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void setupView() {
        buttonsignUp.setOnClickListener(v -> {
            if (editTextinputPassword.getText().toString().equals(editTextinputRepeatPassword.getText().toString())) {
                firebaseAuth.createUserWithEmailAndPassword(editTextinputLogin.getText().toString(), editTextinputPassword.getText().toString())
                        .addOnCompleteListener(getActivity(), task -> {
                            if (task.isSuccessful()) {
                                UserProfile userProfile = new UserProfile();
                                userProfile.setUserUID(firebaseAuth.getCurrentUser().getUid());
                                userProfile.setUserFullName(editTextUserFullName.getText().toString());
                                userProfile.setUserCity(editTextinputCity.getText().toString());
                                userProfile.setUserPhoneNumber(editTextUserPhoneNumber.getText().toString());
                                userProfile.setUserPrivatePerson(checkBoxinputFace.isChecked());
                                userProfile.setUserCompanyName(editTextinputNameCompany.getText().toString());
                                databaseReference.child(firebaseAuth.getCurrentUser().getUid())
                                        .setValue(userProfile).addOnCompleteListener(task1 -> {
                                    if (task.isSuccessful()) {
                                        navController.navigate(R.id.action_registrationFragment_to_mainActivity);
                                    } else {
                                        UtilsDialog.showBasicDialog(getActivity(), "Ok", "Регистрация не удалась. Повторите попытку...").show();
                                    }
                                });
                            } else {
                                UtilsDialog.showBasicDialog(getActivity(), "Ok", "Регистрация не удалась. Повторите попытку...").show();
                            }
                        });
            } else {
                UtilsDialog.showBasicDialog(getActivity(), "Ok", "Пароли не совпадают!").show();
            }
        });
    }
}
