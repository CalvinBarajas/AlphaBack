package org.samanthaai.alphaback;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FlashCards extends AppCompatActivity {

    // global variables
    private static String[] englishAlphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"}; // english alphabet
    private static int displayedLetterIndex;
    private static SoundPool mySoundPool;  // create and configure a SoundPool instance
    private static int soundBtnPress;  // answer is correct
    private static TextView largeLetter; // letter displayed at the center of flash card

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_cards);

        // call method to initialize game sound
        initializeSoundPool();

        // find view for "large letter" (center of flash card)
        largeLetter = (TextView) findViewById(R.id.large_letter);

        // set font style for "large letter" to something easy to read
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/acme.ttf");
        largeLetter.setTypeface(customFont);

        // set initial letter for "large letter" to letter "z"
        displayedLetterIndex = 25;
        largeLetter.setText(englishAlphabet[displayedLetterIndex]);

        // find view for "next" button
        Button nextButton = (Button) findViewById(R.id.next_btn);

        // assign listener to "next" button
        assert nextButton != null;
        nextButton.setOnClickListener(new View.OnClickListener() {
            @NonNull
            @Override
            public void onClick(View v) {
                mySoundPool.play(soundBtnPress, 1.0f, 1.0f, 1, 0, 1); // play sound

                if (displayedLetterIndex <= 25) {
                    displayedLetterIndex += 1; } // if index is already at letter "z" (25), increment by 1 (26)

                if (displayedLetterIndex > 25) {
                    displayedLetterIndex = 0; // if index is at 26, assign variable to "a" (index 0)
                    Toast toast = Toast.makeText(getApplicationContext(), "Starting At The Beginning", Toast.LENGTH_SHORT);
                    toast.show(); }

                largeLetter.setText(englishAlphabet[displayedLetterIndex]);
            }
        });

        // find view for "prev" button
        Button prevButton = (Button) findViewById(R.id.prev_btn);

        // assign listener to "prev" button
        assert prevButton != null;
        prevButton.setOnClickListener(new View.OnClickListener() {
            @NonNull
            @Override
            public void onClick(View v) {
                mySoundPool.play(soundBtnPress, 1.0f, 1.0f, 1, 0, 1); // play sound

                if (displayedLetterIndex >= 0) {
                    displayedLetterIndex -= 1; }// if index is already at letter "a" (index 0), decrement by 1 (-1)

                if (displayedLetterIndex < 0) {
                    displayedLetterIndex = 25; // if index is at -1, assign variable to "z" (index 25)
                    Toast toast = Toast.makeText(getApplicationContext(), "Starting At The End", Toast.LENGTH_SHORT);
                    toast.show(); }

                largeLetter.setText(englishAlphabet[displayedLetterIndex]);
            }
        });
    }

    // sound pool method
    private void initializeSoundPool() {
        // check the API version in order to use the correct code
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //code greater or equal to API 21 (lollipop)
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .build();

            mySoundPool = new SoundPool.Builder()
                    .setMaxStreams(3)
                    .setAudioAttributes(audioAttributes)
                    .build();

            soundBtnPress = mySoundPool.load(this, R.raw.right, 1); // only one sound needed

        } else {
            //code for all other versions
            // for older API versions (i.e., API less than 21)
            mySoundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 1);
            soundBtnPress = mySoundPool.load(this, R.raw.right, 1);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        // return to home page
        Intent intent = new Intent(FlashCards.this, MainActivity.class);
        startActivity(intent);

        finish();
    }
}
