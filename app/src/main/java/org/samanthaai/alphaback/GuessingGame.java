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

import java.util.ArrayList;

public class GuessingGame extends AppCompatActivity {

    public static int guessIndex;  // displayed on right side of screen - users guess
    public static int randomNum; // index of random letter generated
    private static TextView guess;  // global TextView to manipulate color
    private static TextView alphabetLetter;  // not sure if this is needed globally
    private static boolean correctAnswer = false;
    private static String[] englishAlphabet = {"a", "b", "c", "d", "e", "f", "g"}; // this is the entire english alphabet


    private static ArrayList<Integer> alreadyDisplayed;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_one);

        // create array that will keep track of random letters already displayed (so no dupes are shown)
        alreadyDisplayed = new ArrayList<Integer>();


        alreadyDisplayed.add(3);
        alreadyDisplayed.add(5);
        alreadyDisplayed.add(1);


        Log.e("alreadyDisplayed --> ", alreadyDisplayed.contains(2) + "");






        // find the view (used for ending the game)
        Button endGame = (Button) findViewById(R.id.end_game_button);

        // assign listener to button (used for ending the game)
        endGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });

        // generate a random number to display the initial random alphabet letter that user will guess against
        randomNum = generateRandomNumber();

        // find view of alphabet letter
        alphabetLetter = (TextView) findViewById(R.id.alphabet_letter_body);

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
                compareLetters(randomNum, guessIndex);
                guess.setText("a");
                return true;
            case KeyEvent.KEYCODE_B:
                guessIndex = 1;
                compareLetters(randomNum, guessIndex);
                guess.setText("b");
                return true;
            case KeyEvent.KEYCODE_C:
                guessIndex = 2;
                compareLetters(randomNum, guessIndex);
                guess.setText("c");
                return true;
            case KeyEvent.KEYCODE_D:
                guessIndex = 3;
                compareLetters(randomNum, guessIndex);
                guess.setText("d");
                return true;
            case KeyEvent.KEYCODE_E:
                guessIndex = 4;
                compareLetters(randomNum, guessIndex);
                guess.setText("e");
                return true;
            case KeyEvent.KEYCODE_F:
                guessIndex = 5;
                compareLetters(randomNum, guessIndex);
                guess.setText("f");
                return true;
            case KeyEvent.KEYCODE_G:
                guessIndex = 6;
                compareLetters(randomNum, guessIndex);
                guess.setText("g");
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

        if (guessIndex == (randomNum - 1)) {

            correctAnswer = true;
            guess.setTextColor(Color.parseColor("#0000FF"));
            Toast toast = Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();

            // generate a random number to display random alphabet letter that user will guess against
            randomNum = generateRandomNumber();

            // display random alphabet letter on left-side of the screen
            alphabetLetter.setText(englishAlphabet[randomNum]);




            return true;

        } else {

            correctAnswer = false;
            Toast toast = Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
            guess.setTextColor(Color.parseColor("#FF0000"));
            return false;

        }

    }


    public int generateRandomNumber() {

        // generate a random number to display random alphabet letter that user will guess against
        // range needs to exclude letter 'a' (zero index) because nothing precedes 'a'
        int minimum = 1;  // inclusive
        int maximum = englishAlphabet.length - 1;  // inclusive
        randomNum = minimum + (int) (Math.random() * maximum);

        return randomNum;
    }







}
