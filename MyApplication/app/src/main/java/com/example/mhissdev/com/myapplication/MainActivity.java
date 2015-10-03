package com.example.mhissdev.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /* Variables */
    private Button btnFalse;
    private Button btnTrue;
    private TextView lblQuestion;
    private ImageView imgPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Assign variables to interface items */
        this.btnFalse = (Button)findViewById(R.id.btnFalse);
        this.btnTrue = (Button)findViewById(R.id.btnTrue);
        this.lblQuestion = (TextView)findViewById(R.id.lblQuestion);
        this.imgPicture = (ImageView)findViewById(R.id.imgPicture);

        /* Set question text */
        this.lblQuestion.setText("Is Bath in the county of Avon?");

        /* Set image */
        this.imgPicture.setImageResource(R.drawable.bath);

        /* Add listener for false button */
        this.btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Show toast */
                Toast.makeText(MainActivity.this, "Correct, Bath is in the county of Somerset!!", Toast.LENGTH_SHORT).show();
            }
        });

        /* Add listener for true button */
        this.btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Show toast */
                Toast.makeText(MainActivity.this, "Wrong, Bath is in the county of Somerset!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
