package org.samanthaai.alphaback;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class GuessingGame extends AppCompatActivity {
    // global variables
    private static int guessLetterIndex; // index of guess letter made by the player
    private static int randomLetterIndex = 0; // index of random alphabet letter generated
    private static TextView guess; // for manipulation of font color and transparency
    private static TextView alphabetLetter; // for displaying random alphabet letter
    private static Set<Integer> alreadyDisplayed; // keep track of letters already displayed
    private static long elapsedTime; // passed to FinalScore class for scoring purposes
    private static int penalty; // keep track of bad guesses for scoring purposes
    private static String[] englishAlphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"}; // 26 letters of english alphabet
    private static SoundPool mySoundPool; // right answer, wrong answer, and final score sound
    private static int soundRight; // if guess is correct
    private static int soundWrong; // if guess is incorrect
    private static int soundGameOver; // game over after 26 correct answers
    private static final Animation FADEOUT = new AlphaAnimation(1.0f, 0.0f); // animate guess letter (fade-to-invisible)
    public static int gameLengthSeconds = 0; // for displaying and calculating game duration
    public static int gameLengthMinutes = 0; // for displaying and calculating game duration
    public static Timer timer; // for displaying and calculating game duration

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_one);

        // keep track of game duration while displaying clock
        timer = new Timer(); // instantiate timer object
        timer.scheduleAtFixedRate(new TimerTask() { //Set the schedule function and rate
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView textView = (TextView) findViewById(R.id.chronometer);
                        textView.setText(String.valueOf(gameLengthMinutes) + ":" + String.valueOf(gameLengthSeconds));
                        if (gameLengthSeconds == 60) {
                            textView.setText(String.valueOf(gameLengthMinutes) + ":" + String.valueOf(gameLengthSeconds));
                            gameLengthSeconds = 0;
                            gameLengthMinutes += 1;
                        }
                        gameLengthSeconds += 1;
                    }
                });
            }
        }, 0, 1000);

        // animate the guess letter (fade-to-invisible)
        FADEOUT.setDuration(2000);

        // call method to create sound for game
        initializeSoundPool();

        // initialize penalty to zero
        penalty = 0;

        // create array for keeping track of random letters already displayed (no dupes)
        alreadyDisplayed = new HashSet<Integer>();

        // generate random number in order to display initial alphabet letter
        randomLetterIndex = generateRandomNumber();

        // find view for random alphabet letter
        alphabetLetter = (TextView) findViewById(R.id.alphabet_letter_body);

        // find view for guess letter
        guess = (TextView) findViewById(R.id.guess_body);

        // change font style for guess letter and random alphabet letter
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/acme.ttf");
        alphabetLetter.setTypeface(customFont);
        guess.setTypeface(customFont);

        // display initial random alphabet letter for player to guess against
        alphabetLetter.setText(englishAlphabet[randomLetterIndex]);
    }

    // capture which key on keyboard player presses
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_A:
                guessLetterIndex = 0;
                compareLetters(randomLetterIndex, guessLetterIndex);
                // animate guess letter (fade-to-invisible)
                guess.startAnimation(FADEOUT);
                guess.setVisibility(View.INVISIBLE);
                guess.setText("a");
                return true;
            case KeyEvent.KEYCODE_B:
                guessLetterIndex = 1;
                compareLetters(randomLetterIndex, guessLetterIndex);
                // animate guess letter (fade-to-invisible)
                guess.startAnimation(FADEOUT);
                guess.setVisibility(View.INVISIBLE);
                guess.setText("b");
                return true;
            case KeyEvent.KEYCODE_C:
                guessLetterIndex = 2;
                compareLetters(randomLetterIndex, guessLetterIndex);
                // animate guess letter (fade-to-invisible)
                guess.startAnimation(FADEOUT);
                guess.setVisibility(View.INVISIBLE);
                guess.setText("c");
                return true;
            case KeyEvent.KEYCODE_D:
                guessLetterIndex = 3;
                compareLetters(randomLetterIndex, guessLetterIndex);
                // animate guess letter (fade-to-invisible)
                guess.startAnimation(FADEOUT);
                guess.setVisibility(View.INVISIBLE);
                guess.setText("d");
                return true;
            case KeyEvent.KEYCODE_E:
                guessLetterIndex = 4;
                compareLetters(randomLetterIndex, guessLetterIndex);
                // animate guess letter (fade-to-invisible)
                guess.startAnimation(FADEOUT);
                guess.setVisibility(View.INVISIBLE);
                guess.setText("e");
                return true;
            case KeyEvent.KEYCODE_F:
                guessLetterIndex = 5;
                compareLetters(randomLetterIndex, guessLetterIndex);
                // animate guess letter (fade-to-invisible)
                guess.startAnimation(FADEOUT);
                guess.setVisibility(View.INVISIBLE);
                guess.setText("f");
                return true;
            case KeyEvent.KEYCODE_G:
                guessLetterIndex = 6;
                compareLetters(randomLetterIndex, guessLetterIndex);
                // animate guess letter (fade-to-invisible)
                guess.startAnimation(FADEOUT);
                guess.setVisibility(View.INVISIBLE);
                guess.setText("g");
                return true;
            case KeyEvent.KEYCODE_H:
                guessLetterIndex = 7;
                compareLetters(randomLetterIndex, guessLetterIndex);
                // animate guess letter (fade-to-invisible)
                guess.startAnimation(FADEOUT);
                guess.setVisibility(View.INVISIBLE);
                guess.setText("h");
                return true;
            case KeyEvent.KEYCODE_I:
                guessLetterIndex = 8;
                compareLetters(randomLetterIndex, guessLetterIndex);
                // animate guess letter (fade-to-invisible)
                guess.startAnimation(FADEOUT);
                guess.setVisibility(View.INVISIBLE);
                guess.setText("i");
                return true;
            case KeyEvent.KEYCODE_J:
                guessLetterIndex = 9;
                compareLetters(randomLetterIndex, guessLetterIndex);
                // animate guess letter (fade-to-invisible)
                guess.startAnimation(FADEOUT);
                guess.setVisibility(View.INVISIBLE);
                guess.setText("j");
                return true;
            case KeyEvent.KEYCODE_K:
                guessLetterIndex = 10;
                compareLetters(randomLetterIndex, guessLetterIndex);
                // animate guess letter (fade-to-invisible)
                guess.startAnimation(FADEOUT);
                guess.setVisibility(View.INVISIBLE);
                guess.setText("k");
                return true;
            case KeyEvent.KEYCODE_L:
                guessLetterIndex = 11;
                compareLetters(randomLetterIndex, guessLetterIndex);
                // animate guess letter (fade-to-invisible)
                guess.startAnimation(FADEOUT);
                guess.setVisibility(View.INVISIBLE);
                guess.setText("l");
                return true;
            case KeyEvent.KEYCODE_M:
                guessLetterIndex = 12;
                compareLetters(randomLetterIndex, guessLetterIndex);
                // animate guess letter (fade-to-invisible)
                guess.startAnimation(FADEOUT);
                guess.setVisibility(View.INVISIBLE);
                guess.setText("m");
                return true;
            case KeyEvent.KEYCODE_N:
                guessLetterIndex = 13;
                compareLetters(randomLetterIndex, guessLetterIndex);
                // animate guess letter (fade-to-invisible)
                guess.startAnimation(FADEOUT);
                guess.setVisibility(View.INVISIBLE);
                guess.setText("n");
                return true;
            case KeyEvent.KEYCODE_O:
                guessLetterIndex = 14;
                compareLetters(randomLetterIndex, guessLetterIndex);
                // animate guess letter (fade-to-invisible)
                guess.startAnimation(FADEOUT);
                guess.setVisibility(View.INVISIBLE);
                guess.setText("o");
                return true;
            case KeyEvent.KEYCODE_P:
                guessLetterIndex = 15;
                compareLetters(randomLetterIndex, guessLetterIndex);
                // animate guess letter (fade-to-invisible)
                guess.startAnimation(FADEOUT);
                guess.setVisibility(View.INVISIBLE);
                guess.setText("p");
                return true;
            case KeyEvent.KEYCODE_Q:
                guessLetterIndex = 16;
                compareLetters(randomLetterIndex, guessLetterIndex);
                // animate guess letter (fade-to-invisible)
                guess.startAnimation(FADEOUT);
                guess.setVisibility(View.INVISIBLE);
                guess.setText("q");
                return true;
            case KeyEvent.KEYCODE_R:
                guessLetterIndex = 17;
                compareLetters(randomLetterIndex, guessLetterIndex);
                // animate guess letter (fade-to-invisible)
                guess.startAnimation(FADEOUT);
                guess.setVisibility(View.INVISIBLE);
                guess.setText("r");
                return true;
            case KeyEvent.KEYCODE_S:
                guessLetterIndex = 18;
                compareLetters(randomLetterIndex, guessLetterIndex);
                // animate guess letter (fade-to-invisible)
                guess.startAnimation(FADEOUT);
                guess.setVisibility(View.INVISIBLE);
                guess.setText("s");
                return true;
            case KeyEvent.KEYCODE_T:
                guessLetterIndex = 19;
                compareLetters(randomLetterIndex, guessLetterIndex);
                // animate guess letter (fade-to-invisible)
                guess.startAnimation(FADEOUT);
                guess.setVisibility(View.INVISIBLE);
                guess.setText("t");
                return true;
            case KeyEvent.KEYCODE_U:
                guessLetterIndex = 20;
                compareLetters(randomLetterIndex, guessLetterIndex);
                // animate guess letter (fade-to-invisible)
                guess.startAnimation(FADEOUT);
                guess.setVisibility(View.INVISIBLE);
                guess.setText("u");
                return true;
            case KeyEvent.KEYCODE_V:
                guessLetterIndex = 21;
                compareLetters(randomLetterIndex, guessLetterIndex);
                // animate guess letter (fade-to-invisible)
                guess.startAnimation(FADEOUT);
                guess.setVisibility(View.INVISIBLE);
                guess.setText("v");
                return true;
            case KeyEvent.KEYCODE_W:
                guessLetterIndex = 22;
                compareLetters(randomLetterIndex, guessLetterIndex);
                // animate guess letter (fade-to-invisible)
                guess.startAnimation(FADEOUT);
                guess.setVisibility(View.INVISIBLE);
                guess.setText("w");
                return true;
            case KeyEvent.KEYCODE_X:
                guessLetterIndex = 23;
                compareLetters(randomLetterIndex, guessLetterIndex);
                // animate guess letter (fade-to-invisible)
                guess.startAnimation(FADEOUT);
                guess.setVisibility(View.INVISIBLE);
                guess.setText("x");
                return true;
            case KeyEvent.KEYCODE_Y:
                guessLetterIndex = 24;
                compareLetters(randomLetterIndex, guessLetterIndex);
                // animate guess letter (fade-to-invisible)
                guess.startAnimation(FADEOUT);
                guess.setVisibility(View.INVISIBLE);
                guess.setText("y");
                return true;
            case KeyEvent.KEYCODE_Z:
                guessLetterIndex = 25;
                compareLetters(randomLetterIndex, guessLetterIndex);
                // animate guess letter (fade-to-invisible)
                guess.startAnimation(FADEOUT);
                guess.setVisibility(View.INVISIBLE);
                guess.setText("z");
                return true;
            // In case someone clicks the back button
            case KeyEvent.KEYCODE_BACK:
                // if they press back button
                Intent intent = new Intent(GuessingGame.this, MainActivity.class);
                startActivity(intent);
                return true;
            default:
                mySoundPool.play(soundWrong, 1.0f, 1.0f, 1, 0, 1);  // play "wrong" sound, only LC alpha are acceptable
                guess.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.RED));  // set wrong answer guess color
                // animate guess letter (fade-to-invisible)
                guess.startAnimation(FADEOUT);
                guess.setVisibility(View.INVISIBLE);
                guess.setText("?");
                // in case player tries something other than LC letter (i.e., number, symbol)
                Toast toast = Toast.makeText(getApplicationContext(), "Lower-Case Letters Only", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.show();
                return false;
        }
    }

    // compare the guess letter and random letter and record results
    public void compareLetters(int randomLetterIndex, int guessLetterIndex) {
        if (guessLetterIndex == (randomLetterIndex - 1)) {
            // give auditory and visual notification that player guessed "right"
            mySoundPool.play(soundRight, 1.0f, 1.0f, 1, 0, 1);
            guess.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.GREEN));  // set right answer guess color

            // add successfully guessed letter to array in order to eliminate dupes
            alreadyDisplayed.add(randomLetterIndex);

            // generate the next random number to display random alphabet letter that player will guess against
            randomLetterIndex = generateRandomNumber();

            // display random alphabet letter using random index number
            alphabetLetter.setText(englishAlphabet[randomLetterIndex]);

            // find progress bar view (graphical representation)
            TextView progressBar = (TextView) findViewById(R.id.progress_bar);

            // append another "brick" to the progress bar (graphical representation)
            progressBar.append("█");

            // find progress bar view (numerical representation)
            TextView progressBarCount = (TextView) findViewById(R.id.progress_bar_count);

            // display progress bar count (alphabet has 26 letters, but we exclude "a" because nothing comes before it)
            progressBarCount.setText(alreadyDisplayed.size() + "/25");

        } else {
            // increment penalty by one (keep track of how many wrong "tries")
            penalty += 1;

            // give auditory and visual notification that player guessed "wrong"
            mySoundPool.play(soundWrong, 1.0f, 1.0f, 1, 0, 1);
            guess.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.RED));  // set wrong answer guess color

            // give visual notification that player guessed incorrectly
            Toast toast = Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();

            // give sensory notification via vibrate that player guessed incorrectly
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(100);
        }
    }

    // generate random number for displaying random letter
    public int generateRandomNumber() {
        while (true) {
            // if the "alreadyDisplayed" array has reached qty 25 letters (no "a"), game over
            if (alreadyDisplayed.size() >= englishAlphabet.length - 1) {
                gameOver();
                break;
            }

            // generate random number
            int minimum = 1;  // starts at one because zero ("a") and no previous letter
            int maximum = englishAlphabet.length - 1;  // inclusive (alphabet has 26 letters, no "a" means only 25)
            randomLetterIndex = minimum + (int) (Math.random() * maximum); // generate num between 1 (b) and 25 (z) inclusive

            // check if the randomLetterIndex is already in the array/list (if already used, generate new number)
            if (alreadyDisplayed.contains(randomLetterIndex)) {
                continue; // already in the array, try again
            } else {
                break; // unique number, this one can be used
            }
        }
        return randomLetterIndex;
    }

    // if the "alreadyDisplayed" array has reached qty 25 letters (no "a"), call this method
    public void gameOver() {
        // calculate how long game took to play
        elapsedTime = (gameLengthSeconds + (gameLengthMinutes * 60));

        // give auditory notification that game has ended before results are displayed
        mySoundPool.play(soundGameOver, 1.0f, 1.0f, 1, 0, 1);

        timer.cancel(); // this stops the clock from running
        gameLengthSeconds = 0; // re-initialize time for next game
        gameLengthMinutes = 0; // re-initialize time for next game

        // launch final page with game score results
        Intent intent = new Intent(this, FinalScore.class);
        startActivity(intent);
    }


    // allow another activity to retrieve elapsed time results
    public static long getElapsedTime() {
        return elapsedTime;
    }

    // allow another activity to retrieve failed guesses
    public static int getPenalty() {
        return penalty;
    }

    // allow another activity to retrieve array size
    public static int getAlphabetArraySize() {
        return englishAlphabet.length;
    }

    // reset variables if activity focus changes
    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel(); // this stops the clock from running
        gameLengthSeconds = 0; // re-initialize time for next game
        gameLengthMinutes = 0; // re-initialize time for next game
        finish();
    }

    // generate game sounds with SoundPool class
    private void initializeSoundPool() {
        // check the API version in order to use the correct code
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .build();

            mySoundPool = new SoundPool.Builder()
                    .setMaxStreams(3)
                    .setAudioAttributes(audioAttributes)
                    .build();

            soundRight = mySoundPool.load(this, R.raw.right, 1); // correct guess
            soundWrong = mySoundPool.load(this, R.raw.wrong, 1); // incorrect guess
            soundGameOver = mySoundPool.load(this, R.raw.gameover, 1); // game over

        } else {
            //code for all other versions
            // for older API versions (i.e., API less than 21)
            mySoundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 1);
            soundRight = mySoundPool.load(this, R.raw.right, 1);
            soundWrong = mySoundPool.load(this, R.raw.wrong, 1);
            soundGameOver = mySoundPool.load(this, R.raw.gameover, 1);
        }
    }
}
