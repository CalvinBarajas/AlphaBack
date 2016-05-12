package org.samanthaai.alphaback;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_cards);

        // initialize displayedLetterIndex variable
        displayedLetterIndex = 25;

        // find view in order to change background color of activity_flash_cards.xml
        colorBackground = (LinearLayout) findViewById(R.id.flash_cards_bg);

        // find the view for LARGE LETTER
        final TextView largeLetter = (TextView) findViewById(R.id.large_letter);

        // change font style for LARGE LETTER
        Typeface myCustomFont3 = Typeface.createFromAsset(getAssets(), "fonts/school.ttf");
        largeLetter.setTypeface(myCustomFont3);

        // set initial letter for LARGE LETTER
        largeLetter.setText(englishAlphabet[displayedLetterIndex]);

        // find view for NEXT BUTTON
        Button nextButton = (Button) findViewById(R.id.next_btn);

        // assign listener to NEXT BUTTON
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
}
