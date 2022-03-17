package manu.apps.evote.fragments;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import manu.apps.evote.classes.Config;
import manu.apps.evote.R;

public class RegisterFragment extends Fragment implements View.OnClickListener, TextWatcher {

    TextInputLayout tilEmail, tilIdNumber, tilCounty, tilConstituency, tilPassword;
    EditText etEmail, etIdNumber, etCounty, etConstituency, etPassword;
    MaterialButton btnRegister;
    ProgressBar pbRegister;
    TextView tvLogin;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.register_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        firebaseAuth = FirebaseAuth.getInstance();

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        tilEmail = view.findViewById(R.id.til_email);
        tilIdNumber = view.findViewById(R.id.til_id_number);
        tilCounty = view.findViewById(R.id.til_county);
        tilConstituency = view.findViewById(R.id.til_constituency);
        tilPassword = view.findViewById(R.id.til_password);

        etEmail = view.findViewById(R.id.et_email);
        etIdNumber = view.findViewById(R.id.et_id_number);
        etCounty = view.findViewById(R.id.et_county);
        etConstituency = view.findViewById(R.id.et_constituency);
        etPassword = view.findViewById(R.id.et_password);

        btnRegister = view.findViewById(R.id.btn_register);

        pbRegister = view.findViewById(R.id.pb_register);

        tvLogin = view.findViewById(R.id.tv_login);


        btnRegister.setOnClickListener(this);
        tvLogin.setOnClickListener(this);

        etEmail.addTextChangedListener(this);
        etIdNumber.addTextChangedListener(this);
        etCounty.addTextChangedListener(this);
        etConstituency.addTextChangedListener(this);
        etPassword.addTextChangedListener(this);

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        tilEmail.setErrorEnabled(false);
        tilIdNumber.setErrorEnabled(false);
        tilCounty.setErrorEnabled(false);
        tilConstituency.setErrorEnabled(false);
        tilPassword.setErrorEnabled(false);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:

                final String email = etEmail.getText().toString().trim();
                final String idNumber = etIdNumber.getText().toString().trim();
                final String county = etCounty.getText().toString().trim();
                final String constituency = etConstituency.getText().toString().trim();
                final String password = etPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    tilEmail.setError("Email is required");
                    return;
                }

                if (TextUtils.isEmpty(idNumber)) {
                    tilIdNumber.setError("Id Number is required");
                    return;
                }

                if (TextUtils.isEmpty(county)) {
                    tilCounty.setError("County is required");
                    return;
                }

                if (TextUtils.isEmpty(constituency)) {
                    tilConstituency.setError("Constituency is required");
                    return;
                }

                if (password.length() < 8) {
                    tilPassword.setError("Password is less than 8 characters");
                    return;
                }else {

                    btnRegister.setVisibility(View.GONE);
                    pbRegister.setVisibility(View.VISIBLE);

                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                                assert firebaseUser != null;
                                String userId = firebaseUser.getUid();
                                databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userId);

                                HashMap<String, String> hashMap = new HashMap<>();
                                hashMap.put("userId", userId);
                                hashMap.put("email", email);
                                hashMap.put("idNumber", idNumber);
                                hashMap.put("county", county);
                                hashMap.put("constituency", constituency);

                                databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){

                                            Log.i("User Creation Log =====", "createUserWithEmail:success");


                                            btnRegister.setVisibility(View.VISIBLE);
                                            pbRegister.setVisibility(View.GONE);

                                            final Dialog registerDialog = new Dialog(getActivity());
                                            registerDialog.setContentView(R.layout.layout_register_dialog);
                                            registerDialog.show();
                                            registerDialog.setCancelable(false);

                                            // Setting dialog background to transparent
                                            registerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                                            // Setting size of the dialog
                                            registerDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,
                                                    LinearLayout.LayoutParams.WRAP_CONTENT);

                                            TextView tvRegistrationMessage = registerDialog.findViewById(R.id.tv_registration_message);
                                            MaterialButton btnProceed = registerDialog.findViewById(R.id.btn_proceed);

                                            tvRegistrationMessage.setText("Your account was created successfully" + " " + email);

                                            btnProceed.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {

                                                    navController.navigate(R.id.action_register_to_positions_fragment);

                                                    registerDialog.dismiss();
                                                }
                                            });

                                        }else {
                                            Toast.makeText(getActivity(), task.getException().toString(), Toast.LENGTH_SHORT).show();
                                            Config.showSnackBar(getActivity(), "We encountered an error while storing details");
                                        }
                                    }
                                });

                            } else {

                                btnRegister.setVisibility(View.VISIBLE);
                                pbRegister.setVisibility(View.GONE);

                                Config.showSnackBar(getActivity(), "We encountered an error while registering");

                                Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
                break;
            case R.id.tv_login:

                tvLogin.setTextColor(getResources().getColor(R.color.colorAccent));
                navController.navigateUp();

                break;
            default:
                break;
        }
    }
}