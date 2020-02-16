package ru.kolesnikovdmitry.lesson8revision;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItemView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    static public String userName      = null;
    static public int    userAge       = -1;
    final static int REQUEST_CODE_AUTH_ACTIVITY  = 1000;
    final static int REQUEST_CODE_QUEST_ACTIVITY = 1001;
    ActionBar         mActionBar;
    TextView          mTextViewMainActivity;
    FrameLayout       mFrameLayMain;
    Button            mButtonMainCheck;
    Button            mButtonMainExit;
    public static int mTextColorId = R.color.colorBlack;
    static boolean    ID_ENTRY = false;               //если false, значит еще не нажимали
    //subMenu
    public static final int SUB_MENU_GROUP_COLOR_ID = 200;
    public static final int SUB_MENU_COLOR_RED      = 201;
    public static final int SUB_MENU_COLOR_GREEN    = 202;
    public static final int SUB_MENU_COLOR_BLACK    = 203;
    public static final int SUB_MENU_COLOR_WHITE    = 204;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewMainActivity = findViewById(R.id.textViewQuestionActivityMain);
        mTextViewMainActivity.setTextColor(getResources().getColor(mTextColorId));

        mButtonMainCheck = findViewById(R.id.buttonCheckActivityMain);
        mButtonMainExit  = findViewById(R.id.buttonExitActivityMain);

        mActionBar = getSupportActionBar();
        mFrameLayMain = findViewById(R.id.frameLayActivityMain);
        assert mActionBar != null;
        if(userName == null) {
            mActionBar.setTitle("Нужно Авторизоваться");
        }
        else {
            mActionBar.setTitle(userName);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuItemAuth:
                if(!ID_ENTRY) {
                    Intent intent = new Intent(MainActivity.this, AuthActivity.class );
                    startActivityForResult(intent, REQUEST_CODE_AUTH_ACTIVITY);
                }
                else {
                    Snackbar.make(mFrameLayMain, "Вы уже авторизовались, " + userName, Snackbar.LENGTH_SHORT).show();
                }
                break;
            case SUB_MENU_COLOR_BLACK:
                mTextColorId = R.color.colorBlack;
                item.setChecked(!item.isCheckable());
                mTextViewMainActivity.setTextColor(getResources().getColor(mTextColorId));
                break;
            case SUB_MENU_COLOR_GREEN:
                mTextColorId = R.color.colorGreen;
                item.setChecked(!item.isCheckable());
                mTextViewMainActivity.setTextColor(getResources().getColor(mTextColorId));
                break;
            case SUB_MENU_COLOR_RED:
                mTextColorId = R.color.colorRed;
                item.setChecked(!item.isCheckable());
                mTextViewMainActivity.setTextColor(getResources().getColor(mTextColorId));
                break;
            case SUB_MENU_COLOR_WHITE:
                mTextColorId = R.color.colorWhite;
                item.setChecked(!item.isCheckable());
                mTextViewMainActivity.setTextColor(getResources().getColor(mTextColorId));
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_AUTH_ACTIVITY:
                    mActionBar.setTitle(userName);
                    ID_ENTRY = true;
                    ActionMenuItemView menuItem = findViewById(R.id.menuItemAuth);
                    menuItem.setVisibility(View.INVISIBLE);
                    break;
                case REQUEST_CODE_QUEST_ACTIVITY:
                    assert data != null;

                    String answer = data.getStringExtra(QuestActivity.KEY_INTENT_ANSWER);

                    assert answer != null;
                    float ansF = Float.valueOf(answer) * 100;
                    mButtonMainCheck.setVisibility(View.INVISIBLE);
                    mButtonMainExit.setVisibility(View.VISIBLE);
                    mTextViewMainActivity.setTextSize(30);
                    mTextViewMainActivity.setText("Вы на " + ansF + "% пидорас, поздравляю!");
                    break;
                default:
                    break;
            }
        } else {
            //создаем свой тост если тест не будет пройден
            Toast ownToast = new Toast(getApplicationContext());
            ownToast.setDuration(Toast.LENGTH_SHORT);
            ownToast.setGravity(Gravity.CENTER, 0, 0);
            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.toasts, (ViewGroup) findViewById(R.id.ToastLayCancel));
            TextView textToast = view.findViewById(R.id.toastTextView);
            textToast.setText("Вы не ответили на все вопросы!");
            ownToast.setView(view);
            ownToast.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu); //насаживаем меню
        //добавляем подменюшки
        SubMenu subMenuTextColor = menu.addSubMenu("Цвет текста");
        subMenuTextColor.add(SUB_MENU_GROUP_COLOR_ID, SUB_MENU_COLOR_BLACK, Menu.NONE, "Черный");
        subMenuTextColor.add(SUB_MENU_GROUP_COLOR_ID, SUB_MENU_COLOR_GREEN, Menu.NONE, "Зеленый");
        subMenuTextColor.add(SUB_MENU_GROUP_COLOR_ID, SUB_MENU_COLOR_RED  , Menu.NONE, "Красный");
        subMenuTextColor.add(SUB_MENU_GROUP_COLOR_ID, SUB_MENU_COLOR_WHITE, Menu.NONE, "Белый");
        subMenuTextColor.setGroupCheckable(SUB_MENU_GROUP_COLOR_ID, true, true);
        return true;
    }

    public void onClickActivityMain(View view) {
        switch (view.getId()) {
            case R.id.buttonCheckActivityMain:
                if(!ID_ENTRY) {
                    Snackbar snackbar = Snackbar.make(mFrameLayMain, "Сначала авторизуйтесь!", Snackbar.LENGTH_SHORT);
                    snackbar.setTextColor(getResources().getColor(mTextColorId));
                    snackbar.setBackgroundTint(getColor(R.color.colorPrimaryDark));
                    snackbar.setAction("Авторизоваться", onClickSnackbarActivityMain);
                    snackbar.show();
                }
                else {
                    Intent intentQuest = new Intent(MainActivity.this, QuestActivity.class);
                    startActivityForResult(intentQuest, REQUEST_CODE_QUEST_ACTIVITY);
                }
                break;
            case R.id.buttonExitActivityMain:
                finish();
                break;
            default:
                break;
        }
    }

    View.OnClickListener onClickSnackbarActivityMain = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, AuthActivity.class);
            startActivityForResult(intent, REQUEST_CODE_AUTH_ACTIVITY);
        }
    };
}
