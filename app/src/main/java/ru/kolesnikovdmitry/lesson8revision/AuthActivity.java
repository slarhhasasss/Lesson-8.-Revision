package ru.kolesnikovdmitry.lesson8revision;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class AuthActivity extends AppCompatActivity {

    EditText mEditTextName;
    EditText mEditTextAge;
    TextView mTextViewName;
    TextView mTextViewAge;
    Button   mButtonSubmit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        mEditTextAge  = findViewById(R.id.editTextAgeActivityAuth);
        mEditTextName = findViewById(R.id.editTextNameActivityAuth);
        mTextViewAge  = findViewById(R.id.textViewAgeActivityAuth);
        mTextViewName = findViewById(R.id.textViewNameActivityAuth);
    }

    public void onClickActivityAuth(View view) {
        switch (view.getId()) {
            case R.id.buttonSubmitActivityAuth:
                if(mEditTextName.getText().toString().equals("")) {
                    Snackbar.make(view, "Вы не ввели имя!", Snackbar.LENGTH_SHORT).show();
                    break;
                }
                if(mEditTextAge.getText().toString().equals("")) {
                    Snackbar.make(view, "Вы не ввели вохраст!", Snackbar.LENGTH_SHORT).show();
                    break;
                }

                String userNameAu = mEditTextName.getText().toString();
                int userAgeAu     = Integer.valueOf(mEditTextAge.getText().toString());

                MainActivity.userName = userNameAu;
                MainActivity.userAge  = userAgeAu;
                setResult(RESULT_OK);
                finish();
                break;
            default:
                break;
        }
    }
}
