package com.example.tictactoe;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int player = 0, flag, buttonFlag;
    int[] state = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    LinearLayout gameOver;
    TextView winner;
    GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropIn(View view) {
        ImageView image = (ImageView) view;
        int winningPositions[][] = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
        flag = 0;
        buttonFlag = 0;

        gameOver = findViewById(R.id.linearLayout);
        winner = findViewById(R.id.winnerName);

        if(player == 0 && state[Integer.parseInt(image.getTag().toString())] == 2) {
            image.setImageResource(R.drawable.x);
            player = 1;
            state[Integer.parseInt(image.getTag().toString())] = 0;
        }

        else if(player == 1 && state[Integer.parseInt(image.getTag().toString())] == 2){
            image.setImageResource(R.drawable.o);
            player = 0;
            state[Integer.parseInt(image.getTag().toString())] = 1;
        }

        for(int[] wp: winningPositions) {

            if(state[wp[0]] == state[wp[1]] && state[wp[1]] == state[wp[2]] && state[wp[0]] != 2) {
                gameOver.animate().alpha(1f);

                if(state[wp[0]] == 0) {
                    winner.setText("Player 1 WINS");
                }

                else {
                    winner.setText("Player 2 WINS");
                }

                flag = 1;
                buttonFlag = 1;
            }
        }

        for(int i = 0; i <= 8; i++ ) {
            if(state[i] == 2) {
                flag = 1;
            }
        }

        if(flag == 0) {
            winner.setText("Match Draw");
            gameOver.animate().alpha(1f);
            buttonFlag = 1;
        }

        image.animate().rotationBy(180).setDuration(300);
    }

    public void newGame(View view) {
        if(buttonFlag == 0) {
            return;
        }

        winner = findViewById(R.id.winnerName);
        gameOver = findViewById(R.id.linearLayout);
        gridLayout = findViewById(R.id.gridView);

        gameOver.animate().alpha(0f);

        for(int i = 0; i < state.length; i++) {
            state[i] = 2;
        }

        for(int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }
}
