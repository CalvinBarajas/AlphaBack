package org.samanthaai.alphaback;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class GuessingGame extends AppCompatActivity {

    public static int guessIndex;  // displayed on right side of screen - users guess
    public static int randomNum = 0; // index of random letter generated
    private static TextView guess;  // global TextView to manipulate color
    private static TextView alphabetLetter;  // not sure if this is needed globally
    private static String[] englishAlphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"}; // this is the entire english alphabet
    private static Set<Integer> alreadyDisplayed; // keep track of letters already displayed to end-user
    private static long startTime; // keep time of game duration
    private static long stopTime; // keep time of game duration



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_one);


        startTime = System.currentTimeMillis();
        Log.e("startTime 001 --->", startTime + "");




        // create array that will keep track of random letters already displayed (so no dupes are shown)
        alreadyDisplayed = new HashSet<Integer>();

        // find the view (used for ending the game)
        Button endGame = (Button) findViewById(R.id.end_game_button);

        // assign listener to button (used for ending the game)
        endGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });

        //Log.e("genRandNum 000 --->", randomNum + "");

        // generate a random number to display the initial random alphabet letter that user will guess against
        randomNum = generateRandomNumber();
        //Log.e("genRandNum 001 --->", randomNum + "");



        // find view of alphabet letter
        alphabetLetter = (TextView) findViewById(R.id.alphabet_letter_body);

        // display random alphabet letter on left-side of the screen
        alphabetLetter.setText(englishAlphabet[randomNum]);

        // find view for body text two
        guess = (TextView) findViewById(R.id.guess_body);

        Log.e("startTime 002 --->", startTime + "");

    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_A:
                guessIndex = 0;

                Log.e("startTime 003 --->", startTime + "");

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
            case KeyEvent.KEYCODE_H:
                guessIndex = 7;
                compareLetters(randomNum, guessIndex);
                guess.setText("h");
                return true;
            case KeyEvent.KEYCODE_I:
                guessIndex = 8;
                compareLetters(randomNum, guessIndex);
                guess.setText("i");
                return true;
            case KeyEvent.KEYCODE_J:
                guessIndex = 9;
                compareLetters(randomNum, guessIndex);
                guess.setText("j");
                return true;
            default:
                guess.setTextColor(Color.parseColor("#00FF00"));
                guess.setText("?");
                // display toast in case user types in something other than a letter
                Toast toast = Toast.makeText(getApplicationContext(), "Lower-Case Letters Only", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.show();
                return false;
        }

    }


    public void compareLetters(int randomNum, int guessIndex) {

        if (guessIndex == (randomNum - 1)) {

            // if guess is right, send an auditory notification as well as a visual one
            MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.right);
            mp.start();

            // add successfully guessed letter to array
            alreadyDisplayed.add(randomNum);

            guess.setTextColor(Color.parseColor("#0000FF"));

            // generate a random number to display random alphabet letter that user will guess against
            randomNum = generateRandomNumber();

            // display random alphabet letter on left-side of the screen
            alphabetLetter.setText(englishAlphabet[randomNum]);

            // find progress bar view (graphical representation)
            TextView progressBar = (TextView) findViewById(R.id.progress_bar);

            // display progress bar in blocks
            progressBar.append("█");

            // find progress bar view (numerical representation)
            TextView progressBarCount = (TextView) findViewById(R.id.progress_bar_count);

            // display progress bar in blocks
            progressBarCount.setText(alreadyDisplayed.size() + "/26");


            Log.e("startTime 004 --->", startTime + "");




        } else {

            // if guess is wrong, send an auditory notification as well as a visual one
            MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.wrong);
            mp.start();

            // toast message when a non-alpha LC is used
            Toast toast = Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
            guess.setTextColor(Color.parseColor("#FF0000"));


            Log.e("startTime 005 --->", startTime + "");

        }

    }





    public int generateRandomNumber() {

        Log.e("genRandNum 002 --->", randomNum + "");

        int n = 0;

        while(true) {

            Log.e("startTime 006 --->", startTime + "");


            n++;
            Log.e("value of n --->", n + "");

            if(alreadyDisplayed.size() >= englishAlphabet.length - 1){

                gameOver();

                break;
            }

            // generate a random number to display random alphabet letter that user will guess against
            // range needs to exclude letter 'a' (zero index) because nothing precedes 'a'
            int minimum = 1;  // inclusive
            int maximum = englishAlphabet.length - 1;  // inclusive
            randomNum = minimum + (int) (Math.random() * maximum);

            Log.e("genRandNum 004 --->", randomNum + "");
            Log.e("while evaluates to -->", alreadyDisplayed.contains(randomNum) + "");

            // print test copy of array
            Log.e("ARRAY CONTENTS --->", alreadyDisplayed.toString());



            if(alreadyDisplayed.contains(randomNum))  {
                Log.e("AAA --->", " ---> already in the array");
                continue;
            } else {
                Log.e("BBB --->", "unique number, this one can be used");
                break;
            }




        }

        Log.e("genRandNum 006 --->", randomNum + "");
        return randomNum;


    }


    public void gameOver() {

        Log.e("startTime 007 --->", startTime + "");

        // keep track of how long game took to complete
        stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        Log.e("elapsedTime --->", elapsedTime + "");



        Log.e("GAME OVER --->", "COMPLETED...");

        // if guess is right, send an auditory confirmation that game is over
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.gameover);
        mp.start();

        Toast toast = Toast.makeText(getApplicationContext(), "Game Over", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();


        Intent intent = new Intent(this, FinalScore.class);
        startActivity(intent);


    }














}
