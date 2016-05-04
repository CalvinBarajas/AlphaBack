package org.samanthaai.alphaback;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GuessingGame extends AppCompatActivity {

    public static int guessIndex;  // displayed on right side of screen - users guess
    public static int randomNum; // index of random letter generated

    // this is the entire english alphabet
    private static String[] englishAlphabet = {"a", "b", "c", "d"};

    private static TextView guess;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_one);

        // find the view (used for ending the game)
        Button endGame = (Button) findViewById(R.id.end_game_button);

        // assign listener to button (used for ending the game)
        endGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });

        // generate a random number to display random alphabet letter that user will guess against
        // range needs to exclude letter 'a' (zero index)
        int minimum = 1;  // inclusive
        int maximum = englishAlphabet.length;  // inclusive (4)
        randomNum = minimum + (int)(Math.random() * maximum);

        // find view of alphabet letter
        TextView alphabetLetter = (TextView) findViewById(R.id.alphabet_letter_body);

        // display random alphabet letter on left-side of the screen
        alphabetLetter.setText(englishAlphabet[randomNum]);



        // find view for body text two
        guess = (TextView) findViewById(R.id.guess_body);












    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        guess.setTextColor(Color.parseColor("#00FF00"));

        switch (keyCode) {
            case KeyEvent.KEYCODE_A:
                guessIndex = 0;
                Log.e("guessIndex 000", guessIndex + "");
                Log.e("randomNum 000", randomNum + "");
                compareLetters(randomNum, guessIndex);
                guess.setText("a");
                return true;
            case KeyEvent.KEYCODE_B:
                guessIndex = 1;
                Log.e("guessIndex 001", guessIndex + "");
                Log.e("randomNum 001", randomNum + "");
                compareLetters(randomNum, guessIndex);
                guess.setText("b");
                return true;
            case KeyEvent.KEYCODE_C:
                guessIndex = 2;
                Log.e("guessIndex 002", guessIndex + "");
                Log.e("randomNum 002", randomNum + "");
                compareLetters(randomNum, guessIndex);
                guess.setText("c");
                return true;
            default:
                guess.setText("?");
                // display toast in case user types in something other than a letter
                Toast toast = Toast.makeText(getApplicationContext(), "Lower-Case Letters Only", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);

                toast.show();
                return false;
        }

    }



    public boolean compareLetters(int randomNum, int guessIndex) {

        if(guessIndex == (randomNum - 1)) {

            guess.setTextColor(Color.parseColor("#009688"));
            Toast toast = Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
            return true;

        } else {

            Toast toast = Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
            guess.setTextColor(Color.parseColor("#FF0000"));
            return false;

        }

    }









}
