package org.samanthaai.alphaback;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinalScore extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score);

        // create local variables from getters
        int elapsedTimeInSeconds = (int) GuessingGame.getElapsedTime();
        int penaltyTries = GuessingGame.getPenalty();
        int alphabetArraySize = GuessingGame.getAlphabetArraySize();

        // find view for "returning home" button
        Button playAgain = (Button) findViewById(R.id.play_again_btn);

        // assign listener to button (used for ending the game)
        playAgain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(FinalScore.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // find view for time duration field
        TextView timeDuration = (TextView) findViewById(R.id.time_duration);

        // set text for time duration
        if(elapsedTimeInSeconds > 60) {
            timeDuration.setText((elapsedTimeInSeconds / 60) + " min " + (elapsedTimeInSeconds % 60) + " sec"); // if over a minute
        } else {
            timeDuration.setText(elapsedTimeInSeconds + " sec");  // if under a minute
        }

        // find view for total number of guesses (right and wrong)
        TextView totalTries = (TextView) findViewById(R.id.total_tries);

        // display text of total number of tries (right and wrong)
        totalTries.setText(penaltyTries + alphabetArraySize + "");

        // find view for penalty field
        TextView penalty = (TextView) findViewById(R.id.penalty);

        // set text for penalty
        if(GuessingGame.getPenalty() >= 1) {
            penalty.setText(penaltyTries + " x 5 = " + penaltyTries * 5 + " sec");
        } else {
            penalty.setText("None - Great Job!"); // encouragement for zero mistakes
        }

        // find view for total time (with penalty time)
        TextView totalTime = (TextView) findViewById(R.id.total_time);

        // set text for total time (5 second penalty per bad guess)
        totalTime.setText(elapsedTimeInSeconds + (penaltyTries * 5) + " sec");

        // find view for letter grade
        TextView letterGrade = (TextView) findViewById(R.id.letter_grade);

        // display letter grade
        letterGrade.setText(calculateLetterGrade(elapsedTimeInSeconds, penaltyTries));
    }

    // calculate letter grade (arbitrary for now)
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
    }

    @Override
    protected void onPause() {
        super.onPause();
         finish();
    }
}
