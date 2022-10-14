package com.example.kopapirollo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView player;
    private ImageView ai;
    private TextView playerResult;
    private TextView aiResult;
    private Button rock;
    private Button paper;
    private Button scissors;

    private int playerWinCount;
    private int aiWinCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.setImageResource(R.drawable.rock);
                int aiChoice = aiPick();
                switch(aiChoice){
                    case 1:
                        Toast.makeText(MainActivity.this, "Döntetlen", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(MainActivity.this, "Vereség", Toast.LENGTH_SHORT).show();
                        aiWinCount++;
                        aiResult.setText("Gép: "+aiWinCount);
                        break;
                    case 3:
                        Toast.makeText(MainActivity.this, "Győzelem", Toast.LENGTH_SHORT).show();
                        playerWinCount++;
                        playerResult.setText("Ember: "+playerWinCount);
                        break;
                }
                checkWin();
            }
        });
        paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.setImageResource(R.drawable.paper);
                int aiChoice = aiPick();
                switch(aiChoice){
                    case 1:
                        Toast.makeText(MainActivity.this, "Győzelem", Toast.LENGTH_SHORT).show();
                        playerWinCount++;
                        playerResult.setText("Ember: "+playerWinCount);
                        break;
                    case 2:
                        Toast.makeText(MainActivity.this, "Döntetlen", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(MainActivity.this, "Vereség", Toast.LENGTH_SHORT).show();
                        aiWinCount++;
                        aiResult.setText("Gép: "+aiWinCount);
                        break;
                }
                checkWin();
            }
        });
        scissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.setImageResource(R.drawable.scissors);
                int aiChoice = aiPick();
                switch(aiChoice){
                    case 1:
                        Toast.makeText(MainActivity.this, "Vereség", Toast.LENGTH_SHORT).show();
                        aiWinCount++;
                        aiResult.setText("Gép: "+aiWinCount);
                        break;
                    case 2:
                        Toast.makeText(MainActivity.this, "Győzelem", Toast.LENGTH_SHORT).show();
                        playerWinCount++;
                        playerResult.setText("Ember: "+playerWinCount);
                        break;
                    case 3:
                        Toast.makeText(MainActivity.this, "Döntetlen", Toast.LENGTH_SHORT).show();
                        break;
                }
                checkWin();
            }
        });

    }

    private int aiPick(){
        switch ((int) (Math.random()*3)+1){
            case 1:
                ai.setImageResource(R.drawable.rock);
                return 1;
            case 2:
                ai.setImageResource(R.drawable.paper);
                return 2;
            case 3:
                ai.setImageResource(R.drawable.scissors);
                return 3;
        }
        return 0;
    }

    private void checkWin(){
        if(aiWinCount==3 || playerWinCount==3){
            AlertDialog.Builder build = new AlertDialog.Builder(MainActivity.this);
            if(aiWinCount==3){
                build.setTitle("Vesztettél");
            }else if(playerWinCount==3){
                build.setTitle("Győztél");
            }
            build.setCancelable(false);
            build.setMessage("Szertnél új játékot játszani?");
            build.setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            build.setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    init();
                }
            });
        }
    }

    private void init(){
        player=findViewById(R.id.playerChoice);
        ai=findViewById(R.id.aiChoice);
        playerResult = findViewById(R.id.playerResult);
        aiResult = findViewById(R.id.aiResult);
        rock = findViewById(R.id.rock);
        paper = findViewById(R.id.paper);
        scissors = findViewById(R.id.scissors);

        playerResult.setText("Ember: 0");
        aiResult.setText("Gép: 0");

        playerWinCount=0;
        aiWinCount=0;
    }
}