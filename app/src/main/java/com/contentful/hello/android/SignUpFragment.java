package com.contentful.hello.android;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class SignUpFragment extends Fragment {

    TextView createAccountTextView,passwordTextView,capitalTextView,numberTextView,eightCharTextView;
    EditText fullNameEditText, nickNameEditText, emailEditText, passwordEditText, phoneNum, pinCode;

    private Spinner country, province;

    RadioButton nickNameRadioButton;
    LinearLayout linearLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.sign_up_fragment,container,false);

        createAccountTextView = view.findViewById(R.id.headingTextView);
        passwordTextView = view.findViewById(R.id.passwordText);
        capitalTextView = view.findViewById(R.id.capitalLetterTextView);
        numberTextView = view.findViewById(R.id.numberTextView);
        eightCharTextView = view.findViewById(R.id.eightCharTextView);

        fullNameEditText = view.findViewById(R.id.nameEditText);
        nickNameEditText = view.findViewById(R.id.nickNameEditText);
        emailEditText = view.findViewById(R.id.emailEditText);
        passwordEditText = view.findViewById(R.id.passwordEditText);


        phoneNum = view.findViewById(R.id.edtTxt_phoneNumber);
        pinCode = view.findViewById(R.id.edtTxt_pinCode);
        country = view.findViewById(R.id.spin_country);
        province = view.findViewById(R.id.spin_province);

        nickNameRadioButton = view.findViewById(R.id.nicknameRadioButton);

        linearLayout = view.findViewById(R.id.linearLayout);
        linearLayout.setElevation((float)15.0);

        updateSpinnerItems();
        
        return view;

    }

    private void updateSpinnerItems() {

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.country_arrays));
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        country.setAdapter(dataAdapter);
    }
}
