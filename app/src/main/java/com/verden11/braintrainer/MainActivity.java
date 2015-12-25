package com.verden11.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton, button0, button1, button2, button3, playAgain;
    TextView resultTextView, pointsTextView, sumTextView, timerTextView;
    RelativeLayout gameRelativeLayout;

    ArrayList<Integer> answer = new ArrayList<>();
    int locationOfCorrenctAnswer;
    int score = 0;
    int numberOfQuestions = 0;

    public void playAgain(View view) {
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgain.setVisibility(View.INVISIBLE);

        generateQuestion();

        //length - 30s, tick every 1s
        // 30100 is set to resolve a problem such that time wont lagg at the beginning
        new CountDownTimer(30100, 100) {

            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");

            }

            @Override
            public void onFinish() {
                playAgain.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your score:" + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
            }
        }.start();

    }

    public void generateQuestion() {
        Random rand = new Random();
        int a = rand.nextInt(51);
        int b = rand.nextInt(51);
        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
        locationOfCorrenctAnswer = rand.nextInt(4);
        int incorrenctAnswer;
        answer.clear();
        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrenctAnswer) {
                answer.add(a + b);
            } else {
                incorrenctAnswer = rand.nextInt(101);
                while (incorrenctAnswer == a + b) {
                    incorrenctAnswer = rand.nextInt(101);
                }
                answer.add(incorrenctAnswer);
            }
        }
        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));
    }

    public void start(View view) {
        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.bPlayAgain));
    }

    public void chooseAnswer(View view) {
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrenctAnswer))) {
            Log.i("Correct", "Correct");
            score++;
            resultTextView.setText("Correct!");
        } else {
            resultTextView.setText("Wrong");
        }

        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestion();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = (Button) findViewById(R.id.bStart);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        playAgain = (Button) findViewById(R.id.bPlayAgain);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        gameRelativeLayout = (RelativeLayout) findViewById(R.id.gameRealitiveLayout);



    }
}
