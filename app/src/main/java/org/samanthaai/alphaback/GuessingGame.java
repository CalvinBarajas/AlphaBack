package org.samanthaai.alphaback;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
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

    private static int guessIndex;  // displayed on right side of screen - users guess
    private static int randomNum = 0; // index of random letter generated
    private static TextView guess;  // global TextView to manipulate color
    private static TextView alphabetLetter;  // not sure if this is needed globally
    private static Set<Integer> alreadyDisplayed; // keep track of letters already displayed to end-user
    private static long startTime; // keep time of game duration
    private static long stopTime; // keep time of game duration
    private static long elapsedTime; // make this available to the FinalScore class
    private static int penalty = 0; // keep track of mistakes
    private static String[] englishAlphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"}; // this is the entire english alphabet
    private static MediaPlayer mp;  // use release()


    SoundPool mySoundPool;  // create and configure a SoundPool instance
    private static int soundRight;  // answer is correct
    private static int soundWrong;  // answer is incorrect
    private static int soundGameOver;  // the game is over 26 guesses have been reached



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_one);

        // to create sound for game
        initializeSoundPool();

        // initialize penalty to zero
        // penalty = 0;

        Log.e("001 penalty --->", penalty + "");

        startTime = System.currentTimeMillis();
        Log.d("currentTimeMillis0 --->", System.currentTimeMillis() + "");


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

        // change font style for alphabet letter (left-side of viewport)
        Typeface myCustomFont2 = Typeface.createFromAsset(getAssets(), "fonts/acme.ttf");
        alphabetLetter.setTypeface(myCustomFont2);


        // display random alphabet letter on left-side of the screen
        alphabetLetter.setText(englishAlphabet[randomNum]);

        // find view for body text two
        guess = (TextView) findViewById(R.id.guess_body);

        // change font style for guess body (right-side of viewport)
        Typeface myCustomFont3 = Typeface.createFromAsset(getAssets(), "fonts/acme.ttf");
        guess.setTypeface(myCustomFont3);

        Log.d("currentTimeMillis1 --->", System.currentTimeMillis() + "");

    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        // mp.release();

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
            case KeyEvent.KEYCODE_K:
                guessIndex = 10;
                compareLetters(randomNum, guessIndex);
                guess.setText("k");
                return true;
            case KeyEvent.KEYCODE_L:
                guessIndex = 11;
                compareLetters(randomNum, guessIndex);
                guess.setText("l");
                return true;
            case KeyEvent.KEYCODE_M:
                guessIndex = 12;
                compareLetters(randomNum, guessIndex);
                guess.setText("m");
                return true;
            case KeyEvent.KEYCODE_N:
                guessIndex = 13;
                compareLetters(randomNum, guessIndex);
                guess.setText("n");
                return true;
            case KeyEvent.KEYCODE_O:
                guessIndex = 14;
                compareLetters(randomNum, guessIndex);
                guess.setText("o");
                return true;
            case KeyEvent.KEYCODE_P:
                guessIndex = 15;
                compareLetters(randomNum, guessIndex);
                guess.setText("p");
                return true;
            case KeyEvent.KEYCODE_Q:
                guessIndex = 16;
                compareLetters(randomNum, guessIndex);
                guess.setText("q");
                return true;
            case KeyEvent.KEYCODE_R:
                guessIndex = 17;
                compareLetters(randomNum, guessIndex);
                guess.setText("r");
                return true;
            case KeyEvent.KEYCODE_S:
                guessIndex = 18;
                compareLetters(randomNum, guessIndex);
                guess.setText("s");
                return true;
            case KeyEvent.KEYCODE_T:
                guessIndex = 19;
                compareLetters(randomNum, guessIndex);
                guess.setText("t");
                return true;
            case KeyEvent.KEYCODE_U:
                guessIndex = 20;
                compareLetters(randomNum, guessIndex);
                guess.setText("u");
                return true;
            case KeyEvent.KEYCODE_V:
                guessIndex = 21;
                compareLetters(randomNum, guessIndex);
                guess.setText("v");
                return true;
            case KeyEvent.KEYCODE_W:
                guessIndex = 22;
                compareLetters(randomNum, guessIndex);
                guess.setText("w");
                return true;
            case KeyEvent.KEYCODE_X:
                guessIndex = 23;
                compareLetters(randomNum, guessIndex);
                guess.setText("x");
                return true;
            case KeyEvent.KEYCODE_Y:
                guessIndex = 24;
                compareLetters(randomNum, guessIndex);
                guess.setText("y");
                return true;
            case KeyEvent.KEYCODE_Z:
                guessIndex = 25;
                compareLetters(randomNum, guessIndex);
                guess.setText("z");
                return true;
            // In case someone clicks the back button
            case KeyEvent.KEYCODE_BACK:
                Toast toast1 = Toast.makeText(getApplicationContext(), "Exiting Game", Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast1.show();
                onPause();
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


//            AudioManager audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
//            // For example to set the volume of played media to maximum.
//            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);



            mySoundPool.play (soundRight, 1.0f, 1.0f, 1, 0, 1);





//            // if guess is right, send an auditory notification as well as a visual one
//            mp = MediaPlayer.create(getApplicationContext(), R.raw.right);
//            mp.setVolume(1.0f, 1.0f);
//            mp.start();


            // add successfully guessed letter to array
            alreadyDisplayed.add(randomNum);

            guess.setTextColor(Color.parseColor("#0000FF"));

            // generate a random number to display random alphabet letter that user will guess against
            randomNum = generateRandomNumber();

            // display random alphabet letter on left-side of the screen
            alphabetLetter.setText(englishAlphabet[randomNum]);

            // find progress bar view (graphical representation)
            TextView progressBar = (TextView) findViewById(R.id.progress_bar);

            // append another "block" to the progress bar
            progressBar.append("█");

            // find progress bar view (numerical representation)
            TextView progressBarCount = (TextView) findViewById(R.id.progress_bar_count);

            // display progress bar in blocks
            progressBarCount.setText(alreadyDisplayed.size() + "/25");


            Log.d("currentTimeMillis3 --->", System.currentTimeMillis() + "");


        } else {

            penalty += 1;



            mySoundPool.play (soundWrong, 1.0f, 1.0f, 1, 0, 1);





//            // if guess is wrong, send an auditory notification as well as a visual one
//            mp = MediaPlayer.create(getApplicationContext(), R.raw.wrong);
//            mp.setVolume(1.0f, 1.0f);
//            mp.start();

            // toast message when a non-alpha LC is used
            Toast toast = Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
            guess.setTextColor(Color.parseColor("#FF0000"));


            // Get instance of Vibrator from current Context
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

            // Vibrate in milliseconds
            v.vibrate(100);







            Log.d("currentTimeMillis4 --->", System.currentTimeMillis() + "");

        }

    }


    public int generateRandomNumber() {

        Log.d("genRandNum 002 --->", randomNum + "");

        int n = 0;

        while (true) {

            Log.d("currentTimeMillis5 --->", System.currentTimeMillis() + "");


            n++;
            Log.d("value of n --->", n + "");

            if (alreadyDisplayed.size() >= englishAlphabet.length - 1) {

                gameOver();



                break;
            }

            // generate a random number to display random alphabet letter that user will guess against
            // range needs to exclude letter 'a' (zero index) because nothing precedes 'a'
            int minimum = 1;  // inclusive
            int maximum = englishAlphabet.length - 1;  // inclusive
            randomNum = minimum + (int) (Math.random() * maximum);

            Log.d("genRandNum 004 --->", randomNum + "");
            Log.d("while evaluates to -->", alreadyDisplayed.contains(randomNum) + "");

            // print test copy of array
            Log.d("ARRAY CONTENTS --->", alreadyDisplayed.toString());


            if (alreadyDisplayed.contains(randomNum)) {
                Log.d("AAA --->", " ---> already in the array");
                continue;
            } else {
                Log.d("BBB --->", "unique number, this one can be used");
                break;
            }


        }

        Log.d("genRandNum 006 --->", randomNum + "");
        return randomNum;


    }


    public void gameOver() {

        Log.d("currentTimeMillis6 --->", System.currentTimeMillis() + "");

        // keep track of how long game took to complete
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        Log.d("elapsedTime 00001 --->", elapsedTime + "");


        Log.d("GAME OVER --->", "COMPLETED...");

        mySoundPool.play(soundGameOver, 1.0f, 1.0f, 1, 0, 1);

        Intent intent = new Intent(this, FinalScore.class);
        startActivity(intent);


    }


    // Getter (from setters and getters concept)
    public static long getElapsedTime() {
        return elapsedTime;
    }

    // Getter (from setters and getters concept)
    public static int getPenalty() {
        return penalty;
    }

    // Getter (from setters and getters concept)
    public static int getAlphabetArraySize() {
        return englishAlphabet.length;
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.e("onPause() -->", "The onPause() Event GuessingGame.java");
        finish();
    }


    private void initializeSoundPool() {

        ///////////////////////////////////////////////////////////////////////
        ///////////////////// Begin - SoundPool Example ///////////////////////
        ///////////////////////////////////////////////////////////////////////

        // check the API version in order to use the correct code
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            //code greater or equal to API 21 (lollipop)
            Log.e("VER.SDK_INT --->", Build.VERSION.SDK_INT + "" );
            Log.e("VER_CODES.LOLLIPOP --->", Build.VERSION_CODES.LOLLIPOP + "" );

            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .build();

            mySoundPool = new SoundPool.Builder()
                    .setMaxStreams(3)
                    .setAudioAttributes(audioAttributes)
                    .build();

            soundRight = mySoundPool.load(this, R.raw.right, 1);
            soundWrong = mySoundPool.load(this, R.raw.wrong, 1);
            soundGameOver = mySoundPool.load(this, R.raw.gameover, 1);

        } else {
            Log.e("Does This Execute --->", "NoNoNoNoNoNo" );
            //code for all other versions
            // for older API versions (i.e., API less than 21)
            mySoundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 1);
            soundRight = mySoundPool.load(this, R.raw.right, 1);
            soundWrong = mySoundPool.load(this, R.raw.wrong, 1);
            soundGameOver = mySoundPool.load(this, R.raw.gameover, 1);
        }



        ///////////////////////////////////////////////////////////////////////
        ///////////////////// End - SoundPool Example ///////////////////////
        ///////////////////////////////////////////////////////////////////////





    }




}
