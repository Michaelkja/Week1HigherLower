package com.example.micha.week1_higherlower;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.micha.week1_higherlower.R.drawable.d1;
import static com.example.micha.week1_higherlower.R.drawable.d2;
import static com.example.micha.week1_higherlower.R.drawable.d3;
import static com.example.micha.week1_higherlower.R.drawable.d4;
import static com.example.micha.week1_higherlower.R.drawable.d5;
import static com.example.micha.week1_higherlower.R.drawable.d6;

public class MainActivity extends AppCompatActivity {
    //Local variables
    private ImageView imageViewDice;
    private TextView tv_score;
    private TextView tv_highScore;
    private ListView mListView;
    private ArrayAdapter<String> mAdapter;
    private List<String> dRoll;
    private int score = 0;
    private int highscore = 0;
    private int theRoll;
    private Random random;
    private int number, numberNew;

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dRoll = new ArrayList<>();
        mListView = findViewById(R.id.list_view_throws);
        // test

        imageViewDice = findViewById(R.id.image_view_dice);

        tv_score = findViewById(R.id.tv_score);
        tv_highScore = findViewById(R.id.tv_highScore);

        random = new Random();

        number = 1;

        // The fab to guess the outcome of the dice will be higher
        fab = findViewById(R.id.fab_higher);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberNew = random.nextInt(6)+1;
                if (numberNew > number) {
                    score++;
                    Toast.makeText(MainActivity.this, "You win!", Toast.LENGTH_SHORT).show();
                } else if (score > highscore) {
                    highscore = score;
                    tv_highScore.setText("Highscore: " + Integer.toString(highscore));
                    score = 0;
                } else {
                    score = 0;
                    Toast.makeText(MainActivity.this, "You Lose!", Toast.LENGTH_SHORT).show();
                }
                tv_score.setText("score: " + Integer.toString(score));
                number = numberNew;
                rollDice();

            }
        });
        // The fab to guess the outcome of the dice will be lower
        fab = findViewById(R.id.fab_lower);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberNew = random.nextInt(6)+1;
                if (numberNew < number) {
                    score++;
                    Toast.makeText(MainActivity.this, "You win!", Toast.LENGTH_SHORT).show();
                } else if (score > highscore) {
                    highscore = score;
                    tv_highScore.setText("Highscore: " + Integer.toString(highscore));
                    score = 0;
                } else {
                    score = 0;
                    Toast.makeText(MainActivity.this, "You Lose!", Toast.LENGTH_SHORT).show();
                }
                tv_score.setText("score: " + Integer.toString(score));
                number = numberNew;
                rollDice();
            }
        });
    }


    //give each dice a number value
    private void rollDice() {

        switch (numberNew) {
            case 1:
                imageViewDice.setImageResource(d1);
                theRoll = 1;
                break;
            case 2:
                imageViewDice.setImageResource(d2);
                theRoll = 2;
                break;
            case 3:
                imageViewDice.setImageResource(d3);
                theRoll = 3;
                break;
            case 4:
                imageViewDice.setImageResource(d4);
                theRoll = 4;
                break;
            case 5:
                imageViewDice.setImageResource(d5);
                theRoll = 5;
                break;
            case 6:
                imageViewDice.setImageResource(d6);
                theRoll = 6;
                break;
        }
        addToList();
    }

    //Add the dice roll to the listView
    private void addToList() {
        String text = "You rolled " + Integer.toString(theRoll);
        dRoll.add(text);
        updateUI();
    }

    private void updateUI() {
        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dRoll);
            mListView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }
}