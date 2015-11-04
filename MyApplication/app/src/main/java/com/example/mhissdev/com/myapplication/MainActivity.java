package com.example.mhissdev.com.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.paperdb.Paper;


public class MainActivity extends AppCompatActivity {

    /* Variables */
    private Button btnFalse;
    private Button btnTrue;
    private TextView lblQuestion;
    private ImageView imgPicture;
    private TextView lblScore;
    private ArrayList<QuestionObject> questions;
    private int score;
    private int numQuestions;
    private int currentQuestion;
    private Boolean answer;
    private String playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Assign variables to interface items */
        btnFalse = (Button)findViewById(R.id.btnFalse);
        btnTrue = (Button)findViewById(R.id.btnTrue);
        lblQuestion = (TextView)findViewById(R.id.lblQuestion);
        imgPicture = (ImageView)findViewById(R.id.imgPicture);
        lblScore = (TextView)findViewById(R.id.lblScore);

        /* Init vars */
        score = 0;
        numQuestions = 0;
        currentQuestion = 0;

        /* Add listener for false button */
        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check answer
                if(currentQuestion <  numQuestions){
                    checkAnswer(false);
                }
            }
        });

        /* Add listener for true button */
        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check answer
                if(currentQuestion <  numQuestions){
                    checkAnswer(true);
                }
            }
        });

        /* Setup questions */
        setupQuestions();

        /* Init Paper*/
        Paper.init(this);
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

    private void setupQuestions(){
        // Init question array list
        questions = new ArrayList<QuestionObject>();

        /* Question 1 */
        questions.add(new QuestionObject("Bath is in the county of Avon?", false, R.drawable.bath));

        /* Question 2 */
        questions.add(new QuestionObject("London is the capital of England?", true, R.drawable.uk_flag));

        // Set number of questions
        numQuestions = questions.size();

        // Do next question
        nextQuestion();
    }

    private void nextQuestion(){

        if(currentQuestion <  numQuestions) {
            // Set question text
            lblQuestion.setText(questions.get(currentQuestion).getQuestion());

            // Set Image
            imgPicture.setImageResource(questions.get(currentQuestion).getPicure());
        }
        else{
            // Quiz has finished
            endGame();
        }

    }

    private void checkAnswer(boolean answer){

        // Check Answer
        if(answer == questions.get(currentQuestion).getAnswer()){
            // Answer is correct
            Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();

            // Update score
            score++;
            lblScore.setText("Score:" + Integer.toString(score));
        }
        else{
            // Answer is wrong
            Toast.makeText(MainActivity.this, "Wrong!!", Toast.LENGTH_SHORT).show();
        }

        // Do next question
        currentQuestion++;
        nextQuestion();

    }


    private void endGame(){

        /*
        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("Congratulations!")
                .setMessage("You scored " + Integer.toString(score) + " points this round.")
                .setNeutralButton("OK", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        // OMG this looks HORRIBLE!!!!!!!!!!!!
                    }
                })
                .create();

        alertDialog.show();
        */

        // Build dialogue
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Please enter your name:");

        // See http://stackoverflow.com/questions/10903754/input-text-dialog-android
        // Set up the input
        final EditText input = new EditText(this);

        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        builder.setView(input);

        // Set up the buttons
        // Ok button
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                playerName = input.getText().toString();

                // Handle high score objects using paper
                HighScoreObject highScore = new HighScoreObject(score,  "MyName", new Date().getTime());

                // Get user preferences
                List<HighScoreObject> highScores = Paper.book().read("highscores", new ArrayList<HighScoreObject>());

                // Add item
                highScores.add(highScore);

                // Write using paper
                Paper.book().write("highScores", highScores);

                finish();
            }
        });

        // Cancel button
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }


}
