package com.contentful.hello.android;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SignUpFragment extends Fragment {

    TextView createAccountTextView,passwordTextView,capitalTextView,numberTextView,eightCharTextView;
    EditText fullNameEditText,nickNameEditText,emailEditText,passwordEditText;
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

        nickNameRadioButton = view.findViewById(R.id.nicknameRadioButton);

        linearLayout = view.findViewById(R.id.linearLayout);
        linearLayout.setElevation((float)15.0);

        return view;

    }
}
