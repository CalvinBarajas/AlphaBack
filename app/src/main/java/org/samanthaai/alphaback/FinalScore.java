package org.samanthaai.alphaback;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;

public class FinalScore extends AppCompatActivity {

    private static MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score);

        // Declare variables
        int elapsedTimeInSeconds = new BigDecimal((GuessingGame.getElapsedTime()/1000)).intValueExact(); // throws Arithmetic Exception if outside bounds
        int penaltyTries = GuessingGame.getPenalty();
        int alphabetArraySize = GuessingGame.getAlphabetArraySize();


//        // if guess is right, send an auditory confirmation that game is over
//        mp = MediaPlayer.create(getApplicationContext(), R.raw.gameover);
//        mp.setVolume(1.0f, 1.0f);
//        mp.start();

        // find the view (used for ending the game)
        Button endGame = (Button) findViewById(R.id.end_game_button);

        // assign listener to button (used for ending the game)
        endGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });

        // find the view for TIME DURATION
        TextView timeDuration = (TextView) findViewById(R.id.time_duration);

        // set text for TIME DURATION
        if(elapsedTimeInSeconds > 60) {
            timeDuration.setText((elapsedTimeInSeconds / 60) + " min " + (elapsedTimeInSeconds % 60) + " sec"); // if over a minute
        } else {
            timeDuration.setText(elapsedTimeInSeconds + " sec");  // if under a minute
        }

        // find view for TOTAL TRIES
        TextView totalTries = (TextView) findViewById(R.id.total_tries);

        // display text of TOTAL TRIES
        totalTries.setText(penaltyTries + alphabetArraySize + "");

        // find the view for PENALTY
        TextView penalty = (TextView) findViewById(R.id.penalty);

        // set text for PENALTY
        if(GuessingGame.getPenalty() >= 1) {
            penalty.setText(penaltyTries + " x 5 = " + penaltyTries * 5 + " sec");
        } else {
            penalty.setText("None - Great Job!");
        }

        // find the view for TOTAL TIME
        TextView totalTime = (TextView) findViewById(R.id.total_time);

        // set text for TOTAL TIME
        if((26 - (GuessingGame.getPenalty() * 2)) <= 0) {
            totalTime.setText("0 sec");
        } else {
            totalTime.setText(elapsedTimeInSeconds + (penaltyTries * 5) + " sec");
        }


        // find the view for LETTER GRADE
        TextView letterGrade = (TextView) findViewById(R.id.letter_grade);

        // display the LETTER GRADE
        letterGrade.setText(calculateLetterGrade(elapsedTimeInSeconds, penaltyTries));

    }


    public static String calculateLetterGrade(int seconds, int penalty) {

        int grade = seconds + penalty;

        if(grade <= 100) {
            return "A - Excellent";
        } else if(grade <= 125) {
            return "B - Good";
        } else if(grade <= 150) {
            return "C - Okay";
        } else if(grade <= 175) {
            return "D -Borderline";
        } else {
            return "Keep Practicing";
        }

    } // closes calculateLetterGrade

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("onPause() -->", "The onPause() Event FinalScore.java");
        finish();
    }




} // closes the class
