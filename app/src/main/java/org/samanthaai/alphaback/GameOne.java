package org.samanthaai.alphaback;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GameOne extends AppCompatActivity {

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

        // populate array with letters from the alphabet
        sourceArray.add("a");
        sourceArray.add("b");
        sourceArray.add("c");
        sourceArray.add("d");
        sourceArray.add("e");
        sourceArray.add("f");
        sourceArray.add("g");
        sourceArray.add("h");
        sourceArray.add("i");
        sourceArray.add("j");
        sourceArray.add("k");
        sourceArray.add("l");
        sourceArray.add("m");
        sourceArray.add("n");
        sourceArray.add("o");
        sourceArray.add("p");
        sourceArray.add("q");
        sourceArray.add("r");
        sourceArray.add("s");
        sourceArray.add("t");
        sourceArray.add("u");
        sourceArray.add("v");
        sourceArray.add("w");
        sourceArray.add("x");
        sourceArray.add("y");
        sourceArray.add("z");
















        // generate a random number to display random alphabet letter that user will guess against
        int r = new Random().nextInt(26);  // gives r such that 0 <= r < 26

        // find view of alphabet letter
        TextView alphabetLetter = (TextView) findViewById(R.id.alphabet_letter_body);

        // display random alphabet letter
        alphabetLetter.setText(sourceArray.get(r));
    }










    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        // find view for body text two
        TextView guess = (TextView) findViewById(R.id.guess_body);

        // display toast in case user types in something other than a letter
        Toast toast = Toast.makeText(getApplicationContext(), "Lower-Case Letters Only", Toast.LENGTH_SHORT);

        guess.setTextColor(Color.parseColor("#757575"));

        switch (keyCode) {
            case KeyEvent.KEYCODE_A:
                guess.setText("a");
                Log.e("What is the keyCode? ", KeyEvent.KEYCODE_A + " is the keyCode");
                return true;
            case KeyEvent.KEYCODE_B:
                guess.setText("b");
                Log.e("What is the keyCode? ", keyCode + " is the keyCode");
                return true;
            case KeyEvent.KEYCODE_C:
                guess.setText("c");
                Log.e("What is the keyCode? ", keyCode + " is the keyCode");
                return true;
            case KeyEvent.KEYCODE_D:
                guess.setText("d");
                return true;
            case KeyEvent.KEYCODE_E:
                guess.setText("e");
                return true;
            case KeyEvent.KEYCODE_F:
                guess.setText("f");
                return true;
            case KeyEvent.KEYCODE_G:
                guess.setText("g");
                return true;
            case KeyEvent.KEYCODE_H:
                guess.setText("h");
                return true;
            case KeyEvent.KEYCODE_I:
                guess.setText("i");
                return true;
            case KeyEvent.KEYCODE_J:
                guess.setText("j");
                return true;
            case KeyEvent.KEYCODE_K:
                guess.setText("k");
                return true;
            case KeyEvent.KEYCODE_L:
                guess.setText("l");
                return true;
            case KeyEvent.KEYCODE_M:
                guess.setText("m");
                return true;
            case KeyEvent.KEYCODE_N:
                guess.setText("n");
                return true;
            case KeyEvent.KEYCODE_O:
                guess.setText("o");
                return true;
            case KeyEvent.KEYCODE_P:
                guess.setText("p");
                return true;
            case KeyEvent.KEYCODE_Q:
                guess.setText("q");
                return true;
            case KeyEvent.KEYCODE_R:
                guess.setText("r");
                return true;
            case KeyEvent.KEYCODE_S:
                guess.setText("s");
                return true;
            case KeyEvent.KEYCODE_T:
                guess.setText("t");
                return true;
            case KeyEvent.KEYCODE_U:
                guess.setText("u");
                return true;
            case KeyEvent.KEYCODE_V:
                guess.setText("v");
                return true;
            case KeyEvent.KEYCODE_W:
                guess.setText("w");
                return true;
            case KeyEvent.KEYCODE_X:
                guess.setText("x");
                return true;
            case KeyEvent.KEYCODE_Y:
                guess.setText("y");
                return true;
            case KeyEvent.KEYCODE_Z:
                guess.setText("z");
                return true;
            default:
                guess.setTextColor(Color.RED);
                guess.setText("?");
                toast.show();
                return false;
        }
    }
























}
