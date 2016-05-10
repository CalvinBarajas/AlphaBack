package org.samanthaai.alphaback;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // find the view (used for ending the game)
        Button endGame = (Button) findViewById(R.id.end_game_button);

        // assign listener to button (used for ending the game)
        endGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });


        // find the view
        Button startGame = (Button) findViewById(R.id.button1);

        // assign listener to button
        startGame.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, GuessingGame.class);
                startActivity(intent);
            }


        });


        // find the view
        TextView textView = (TextView) findViewById(R.id.hello_world);
        Typeface myCustomFont1 = Typeface.createFromAsset(getAssets(), "fonts/school.ttf");
        textView.setTypeface(myCustomFont1);





    }
}
