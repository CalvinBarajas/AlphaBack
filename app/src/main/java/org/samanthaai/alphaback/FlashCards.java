package org.samanthaai.alphaback;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FlashCards extends AppCompatActivity {

    // global variables
    private static String[] englishAlphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"}; // this is the entire english alphabet
    private static LinearLayout colorBackground;
    private static int displayedLetterIndex;



    SoundPool mySoundPool;  // create and configure a SoundPool instance
    private static int soundBtnPress;  // answer is correct











    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_cards);


        // to create sound for game
        initializeSoundPool();


        // initialize displayedLetterIndex variable
        displayedLetterIndex = 25;

        // find view in order to change background color of activity_flash_cards.xml
        colorBackground = (LinearLayout) findViewById(R.id.flash_cards_bg);

        // find the view for LARGE LETTER
        final TextView largeLetter = (TextView) findViewById(R.id.large_letter);

        // change font style for LARGE LETTER
        Typeface myCustomFont3 = Typeface.createFromAsset(getAssets(), "fonts/acme.ttf");
        largeLetter.setTypeface(myCustomFont3);

        // set initial letter for LARGE LETTER
        largeLetter.setText(englishAlphabet[displayedLetterIndex]);

        // find view for NEXT BUTTON
        Button nextButton = (Button) findViewById(R.id.next_btn);

        // assign listener to NEXT BUTTON
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mySoundPool.play(soundBtnPress, 1.0f, 1.0f, 1, 0, 1);

                // if index is already at letter Z (25), increment by 1 (26)
                if (displayedLetterIndex <= 25) {
                    displayedLetterIndex += 1;
                }

                // if index is at 26, reset variable to A (0)
                if (displayedLetterIndex > 25) {
                    displayedLetterIndex = 0;
                    Toast toast = Toast.makeText(getApplicationContext(), "Starting At The Beginning", Toast.LENGTH_SHORT);
                    toast.show();
                }

                largeLetter.setText(englishAlphabet[displayedLetterIndex]);
            }
        });



        // find view for PREV BUTTON
        Button prevButton = (Button) findViewById(R.id.prev_btn);

        // assign listener to PREV BUTTON
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mySoundPool.play(soundBtnPress, 1.0f, 1.0f, 1, 0, 1);

                // if index is already at letter Z (25), increment by 1 (26)
                if (displayedLetterIndex >= 0) {
                    displayedLetterIndex -= 1;
                }

                if (displayedLetterIndex < 0) {
                    displayedLetterIndex = 25;
                    Toast toast = Toast.makeText(getApplicationContext(), "Starting At The End", Toast.LENGTH_SHORT);
                    toast.show();
                }

                largeLetter.setText(englishAlphabet[displayedLetterIndex]);
            }
        });





    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.e("onPause() -->", "The onPause() Event GuessingGame.java");

        Intent intent = new Intent(FlashCards.this, MainActivity.class);
        startActivity(intent);


        finish();
        Log.e("B5-001 FINISH()---> ", "DID FINISH() EXECUTE?");


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

            soundBtnPress = mySoundPool.load(this, R.raw.right, 1);

        } else {
            Log.e("Does This Execute --->", "NoNoNoNoNoNo" );
            //code for all other versions
            // for older API versions (i.e., API less than 21)
            mySoundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 1);
            soundBtnPress = mySoundPool.load(this, R.raw.right, 1);
        }



        ///////////////////////////////////////////////////////////////////////
        ///////////////////// End - SoundPool Example ///////////////////////
        ///////////////////////////////////////////////////////////////////////





    }


























}
