package org.samanthaai.alphaback;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinalScore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score);



        // find the view (used for ending the game)
        Button endGame = (Button) findViewById(R.id.end_game_button);

        // assign listener to button (used for ending the game)
        endGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });



        // find the view for time duration
        TextView timeDuration = (TextView) findViewById(R.id.time_duration);

        // set text for time duration
        if((GuessingGame.getElapsedTime()/1000) > 60) {
            timeDuration.setText(((GuessingGame.getElapsedTime()/1000) / 60) + " min " + ((GuessingGame.getElapsedTime()/1000) % 60) + " sec"); // if over a minute
        } else {
            timeDuration.setText(((GuessingGame.getElapsedTime()/1000)) + " sec");  // if under a minute
        }

        // find the view for wrong answers
        TextView wrongAnswers = (TextView) findViewById(R.id.wrong_answers);

        // set text for wrong answers
        wrongAnswers.setText(GuessingGame.getPenalty() + " wrg x 2 pts = " + GuessingGame.getPenalty() * 2);


        // find the view for final score
        TextView finalScore = (TextView) findViewById(R.id.final_score);

        // set text for wrong answers

        Log.e("GuessingGame.getPenalty() --->", GuessingGame.getPenalty() + "");

        if((26 - (GuessingGame.getPenalty() * 2)) <= 0) {
            finalScore.setText("0/26 pts");
        } else {
            finalScore.setText(26 - (GuessingGame.getPenalty() * 2) + "/26 pts");
        }





    }
}
