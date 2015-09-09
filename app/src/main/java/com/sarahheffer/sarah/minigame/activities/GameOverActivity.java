package com.sarahheffer.sarah.minigame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sarahheffer.sarah.minigame.R;

public class GameOverActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int numStars = getIntent().getExtras().getInt("STARS");
        setContentView(R.layout.activity_game_over);

        TextView starStats = (TextView) findViewById(R.id.stars_collected);
        starStats.setText(getResources().getString(R.string.stars_collected, numStars));

        Button playAgain = (Button) findViewById(R.id.play_again_button);
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameOverActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
}
