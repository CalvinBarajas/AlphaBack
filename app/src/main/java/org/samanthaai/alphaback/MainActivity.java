package org.samanthaai.alphaback;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find view for "quit" button (end game)
        Button endGame = (Button) findViewById(R.id.end_game_button);

        // assign listener to button (used for ending the game)
        assert endGame != null;
        endGame.setOnClickListener(new View.OnClickListener() {
            @NonNull
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // find view for "guessing game" start button
        Button startGuessingGame = (Button) findViewById(R.id.guessing_game_btn);

        // assign listener to "guessing game" start button
        assert startGuessingGame != null;
        startGuessingGame.setOnClickListener(new View.OnClickListener() {
            @NonNull
            @Override
            public void onClick(View view) {
                // launch GuessingGame activity
                Intent intent = new Intent(MainActivity.this, GuessingGame.class);
                startActivity(intent);
            }
        });

        // find view for home page header text and apply custom font
        TextView textView = (TextView) findViewById(R.id.homepage_header_tv);
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/school.ttf");
        textView.setTypeface(customFont);

        // find view for the flash cards start button
        Button flashCardsBtn = (Button) findViewById(R.id.flash_cards_btn);

        // assign listener to FLASH CARDS BUTTON and launch activity
        assert flashCardsBtn != null;
        flashCardsBtn.setOnClickListener(new View.OnClickListener() {
            @NonNull
            @Override
            public void onClick(View view) {
                // launch FlashCards activity
                Intent intent = new Intent(MainActivity.this, FlashCards.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
