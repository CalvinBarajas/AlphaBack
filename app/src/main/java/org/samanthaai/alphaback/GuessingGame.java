package org.samanthaai.alphaback;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GuessingGame extends AppCompatActivity {

    public static String guessLetter;  // displayed on right side of screen - users guess
    private static String randomAlphabetLetter; // displayed on left side of the screen - computer generated

    // this is the entire english alphabet
    private static String[] englishAlphabet = {"a", "b", "c", "d", "e", "f", "g"};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_one);

        // find the view
        Button endGame = (Button) findViewById(R.id.end_game_button);
        // assign listener to button
        endGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });

        // create alphabet array
        ArrayList<String> sourceArray = new ArrayList<String>();

        // end-user will reduce the size of this array with each correct guess until all letters are used
        sourceArray.add("a");
        sourceArray.add("b");
        sourceArray.add("c");
        sourceArray.add("d");
        sourceArray.add("e");
        sourceArray.add("f");

        // generate a random number to display random alphabet letter that user will guess against
        int minimum = 1;  // inclusive
        //int maximum = sourceAlphabet.length;  // inclusive (6)
        //int randomNum = minimum + (int)(Math.random() * maximum);

        // find view of alphabet letter
        TextView alphabetLetter = (TextView) findViewById(R.id.alphabet_letter_body);

        // find random letter to display
        //randomAlphabetLetter = sourceArray.get(randomNum);

        // display random alphabet letter on left-side of the screen
        alphabetLetter.setText(randomAlphabetLetter);

    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        // find view for body text two
        TextView guess = (TextView) findViewById(R.id.guess_body);

        String comp;

        switch (keyCode) {
            case KeyEvent.KEYCODE_A:
                guessLetter = "a";
                comp = compareLetters(randomAlphabetLetter, guessLetter);
                guess.setText(comp);
                return true;
            case KeyEvent.KEYCODE_B:
                guessLetter = "b";
                comp = compareLetters(randomAlphabetLetter, guessLetter);
                guess.setText(comp);
                return true;
            case KeyEvent.KEYCODE_C:
                guessLetter = "c";
                comp = compareLetters(randomAlphabetLetter, guessLetter);
                guess.setText(comp);
                return true;
            case KeyEvent.KEYCODE_D:
                guessLetter = "d";
                comp = compareLetters(randomAlphabetLetter, guessLetter);
                guess.setText(comp);
                return true;
            case KeyEvent.KEYCODE_E:
                guessLetter = "e";
                comp = compareLetters(randomAlphabetLetter, guessLetter);
                guess.setText(comp);
                return true;
            case KeyEvent.KEYCODE_F:
                guessLetter = "f";
                comp = compareLetters(randomAlphabetLetter, guessLetter);
                guess.setText(comp);
                return true;
            default:
                guessLetter = "?";
                guess.setText(guessLetter);
                // display toast in case user types in something other than a letter
                Toast toast = Toast.makeText(getApplicationContext(), "Lower-Case Letters Only", Toast.LENGTH_SHORT);
                toast.show();
                return false;
        }

    }



    public String compareLetters(String left, String right) {

        if(left.equals(right)){
            Log.e("Right", "You got it!!!!!!!!!!!!!!");
            return right;
        } else {
            Log.e("Wrong", "Wrong f-in letter again!!!!");
            return "?";
        }

    }









}
