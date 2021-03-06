package com.gastos.gastosprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class PhoneNumberActivity extends AppCompatActivity {

    private EditText phoneEdt;
    private Button getOtpBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);
        phoneEdt = findViewById(R.id.idEdtMobile);
        mAuth = FirebaseAuth.getInstance();
        getOtpBtn = findViewById(R.id.idBtnGetOTP);
        String phoneNumber = phoneEdt.getText().toString();
        getOtpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int l = phoneEdt.getText().length();
                if (TextUtils.isEmpty(phoneEdt.getText().toString()) && l<10) {
                    Toast.makeText(PhoneNumberActivity.this, "Please enter a valid phone number..", Toast.LENGTH_SHORT).show();
                    return;
                }else if(phoneEdt.getText().toString().length()!=10){
                    Toast.makeText(PhoneNumberActivity.this, "Please enter a valid 10 digit phone number..", Toast.LENGTH_SHORT).show();
                    return;
                }
                sendOTP(phoneEdt.getText().toString());
            }
        });
    }

    private void sendOTP(String phoneNumber) {
        String phone = "+" + "91" + phoneNumber;
        Toast.makeText(this, "OTP has been sent to your number..", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(PhoneNumberActivity.this, VerifyOTPActivity.class);
        i.putExtra("phone", phone);
        startActivity(i);

    }
}