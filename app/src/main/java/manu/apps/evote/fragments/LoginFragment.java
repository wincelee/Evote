package manu.apps.evote.fragments;

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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import manu.apps.evote.classes.GlobalVariables;
import manu.apps.evote.classes.User;
import manu.apps.evote.R;

public class LoginFragment extends Fragment implements View.OnClickListener, TextWatcher {

    TextInputLayout tilEmail, tilIdNumber, tilPassword;
    TextInputEditText etEmail, etIdNumber, etPassword;
    MaterialButton btnLogin;
    ProgressBar pbLogin;

    TextView tvRegister;

    NavController navController;

    FirebaseAuth firebaseAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_fragment, container, false);
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
        tilPassword = view.findViewById(R.id.til_password);

        etEmail = view.findViewById(R.id.et_email);
        etIdNumber = view.findViewById(R.id.et_id_number);
        etPassword = view.findViewById(R.id.et_password);

        btnLogin = view.findViewById(R.id.btn_login);
        pbLogin = view.findViewById(R.id.pb_login);
        tvRegister = view.findViewById(R.id.tv_register);

        btnLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);

        etEmail.addTextChangedListener(this);
        etIdNumber.addTextChangedListener(this);
        etPassword.addTextChangedListener(this);


    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        tilEmail.setErrorEnabled(false);
        tilIdNumber.setErrorEnabled(false);
        tilPassword.setErrorEnabled(false);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:

                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    tilEmail.setError("Email is required");
                }

                if (TextUtils.isEmpty(email)){
                    tilEmail.setError("Id Number is required");
                }

                if (TextUtils.isEmpty(password)){

                    tilPassword.setError("Password is required");

                } else {

                    btnLogin.setVisibility(View.GONE);
                    pbLogin.setVisibility(View.VISIBLE);

                    firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                Log.i("User Login Log =====", "loginUserWithEmail:success");

                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                                GlobalVariables.currentUser = snapshot.getValue(User.class);

                                                Toast.makeText(getActivity(), "Login is successful", Toast.LENGTH_SHORT).show();

                                                navController.navigate(R.id.action_login_to_positions_fragment);

                                                btnLogin.setVisibility(View.VISIBLE);
                                                pbLogin.setVisibility(View.GONE);

                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });


                            } else {

                                btnLogin.setVisibility(View.VISIBLE);
                                pbLogin.setVisibility(View.GONE);
                                Toast.makeText(getActivity(), "Error !" + task.getException(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }
                break;
            case R.id.tv_register:

                tvRegister.setTextColor(getResources().getColor(R.color.colorAccent));
                navController.navigate(R.id.action_login_to_register_fragment);

                break;

            default:
                break;
        }
    }
}