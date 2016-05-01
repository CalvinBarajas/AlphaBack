package org.samanthaai.alphaback;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GameOne extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_one);

        // create alphabet array
        ArrayList<String> sourceArray = new ArrayList<String>();

        // populate array with letters from the alphabet
        sourceArray.add("A");
        sourceArray.add("B");
        sourceArray.add("C");
        sourceArray.add("D");
        sourceArray.add("E");
        sourceArray.add("F");
        sourceArray.add("G");
        sourceArray.add("H");
        sourceArray.add("I");
        sourceArray.add("J");

        // generate a random number (between 1 and 10 inclusive) to display random letter




    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        // find view for body text two
        TextView guess = (TextView) findViewById(R.id.guess_body);

        // display toast in case user types in something other than a letter
        Toast toast = Toast.makeText(getApplicationContext(), "LETTERS ONLY PLEASE", Toast.LENGTH_SHORT);

        switch (keyCode) {
            case KeyEvent.KEYCODE_B:
                guess.setTextColor(Color.RED);
                guess.setText("b");
                return true;
            case KeyEvent.KEYCODE_F:
                guess.setTextColor(Color.BLACK);
                guess.setText("f");
                return true;
            default:
                guess.setTextColor(Color.RED);
                guess.setText("_");
                toast.show();
                return false;
        }
    }
}
