package ru.kolesnikovdmitry.lesson8revision;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class QuestActivity extends AppCompatActivity {

    public final static String KEY_INTENT_ANSWER = "ru.kolesnikovdmitry.lesson8revision.KEY_INTENT_ANSWER";
    TextView mTextViewQuest;
    Button   mButtonYes;
    Button   mButtonNo;
    Button   mButtonToRes;
    String[] questions = {"Вы любите мужчин?",
            "Вы хотели бы стать космонавтом?",
            "Если бы вам дали в руки дилдо, Вы бы запихнули его себе в зад?",
            "У вас был секс с девушкой?",
            "Вы слушаете русский реп?",
            "Стоят два стула: с дилдосами и с пиками. Вы бы сели на дилдосы?",
            "Вы бы выбрали в жопу раз?",
            "Вы согласны с высказыванием, что один - раз не пидорас?"};
    int[] mArrayAns;
    int COUNTER_QUESTS = 0;
    int MAX_COUNTER    = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest);

        mArrayAns   = new int[8];
        MAX_COUNTER = mArrayAns.length;

        mTextViewQuest = findViewById(R.id.textViewActivityQuest);
        mTextViewQuest.setTextColor(getResources().getColor(MainActivity.mTextColorId));

        mButtonNo    = findViewById(R.id.buttonNoActivityQuest);
        mButtonYes   = findViewById(R.id.buttonYesActivityQuest);
        mButtonToRes = findViewById(R.id.buttonToResActivityQuest);

        mTextViewQuest.setText(questions[COUNTER_QUESTS]);
    }

    public void onClickActivityQuest(View view) {
        switch (view.getId()) {
            case R.id.buttonYesActivityQuest:
                mArrayAns[COUNTER_QUESTS] = 1;
                COUNTER_QUESTS++;
                if(COUNTER_QUESTS < MAX_COUNTER) {
                    mTextViewQuest.setText(questions[COUNTER_QUESTS]);
                }
                else {
                    mButtonToRes.setVisibility(View.VISIBLE);
                    mButtonYes.setVisibility(View.INVISIBLE);
                    mButtonNo.setVisibility(View.INVISIBLE);
                    mTextViewQuest.setText("Отлично, вы прошли тест, нажмите, чтобы узнать результаты!");
                }
                break;
            case R.id.buttonNoActivityQuest:
                mArrayAns[COUNTER_QUESTS] = 0;
                COUNTER_QUESTS++;
                if(COUNTER_QUESTS < MAX_COUNTER) {
                    mTextViewQuest.setText(questions[COUNTER_QUESTS]);
                }
                else {
                    mButtonToRes.setVisibility(View.VISIBLE);
                    mButtonYes.setVisibility(View.INVISIBLE);
                    mButtonNo.setVisibility(View.INVISIBLE);
                    mTextViewQuest.setText("Отлично, вы прошли тест, нажмите, чтобы узнать результаты!");
                }
                break;
            case R.id.buttonToResActivityQuest:
                float orientation = checkForOrientation();
                Intent ansIntent = new Intent();
                ansIntent.putExtra(KEY_INTENT_ANSWER, String.valueOf(orientation));
                setResult(RESULT_OK, ansIntent);
                finish();
                break;
            default:
                break;
        }
    }

    private float checkForOrientation() {
        int opr = 0;
        for(int i = 0; i < MAX_COUNTER; i++) {
            opr += mArrayAns[i];
        }
        return (float) opr / (MAX_COUNTER);
    }
}
