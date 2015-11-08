package com.example.mhissdev.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.paperdb.Paper;

public class HighScoreActivity extends AppCompatActivity {

    private ListView listView;
    private List<HighScoreObject> highScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        Paper.init(this);
        highScores = Paper.book().read("highscores", new ArrayList<HighScoreObject>());
        Toast.makeText(this,"numHS: " + highScores.size(), Toast.LENGTH_SHORT).show();
        HighscoreAdapter adapter = new HighscoreAdapter(highScores);
        listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    private class HighscoreAdapter extends ArrayAdapter<HighScoreObject> {

        public HighscoreAdapter(List<HighScoreObject> items) {
            super(HighScoreActivity.this, 0, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(
                        R.layout.row_highscore, null);
            }

            //get the highscore object for the row we're looking at
            HighScoreObject highscore = highScores.get(position);
            Date date = new Date(highscore.getTimestamp());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

            TextView lblTitle = (TextView)convertView.findViewById(R.id.lblTitle);
            String strOutput = (position + 1) + ". " + highscore.getName() + " " + highscore.getScore() + " " + dateFormat.format(date);
            lblTitle.setText(strOutput);

            return convertView;
        }// end get view

    }// end adapter class

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_high_score, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
