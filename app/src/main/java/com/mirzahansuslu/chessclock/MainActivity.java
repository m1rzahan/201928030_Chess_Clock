package com.mirzahansuslu.chessclock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    /*SeekBar timer_sb;
    TextView timer_tv;
    Button start_btn;
    CountDownTimer countDownTimer;
    Boolean counterIsActive = false;*/
    private static final long START_TIME_IN_MILLIS = 600000;
    private TextView mTextViewCountDown;
    private TextView mTextViewCountDown2;
    private Button mButtonStartPause;
    private Button mButtonStartPause2;
    private Button mButtonReset;
    private CountDownTimer countDownTimer2;
    private CountDownTimer countDownTimer;
    private boolean mTimerRunning;
    private boolean mTimerRunning2;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private long mTimeLeftInMillis2 = START_TIME_IN_MILLIS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextViewCountDown = findViewById(R.id.twCountdown);
        mTextViewCountDown2 = findViewById(R.id.twCountdown2);
        mButtonStartPause = findViewById(R.id.buttonStartPause);
        mButtonStartPause2 = findViewById(R.id.buttonStartPause2);
       // mButtonReset = findViewById(R.id.buttonReset);
        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(mTimerRunning) {
                //pauseTimerMethod(mButtonStartPause);
                firstUserPauseTimer();
            }
            else {
                //startTimerMethod(mButtonStartPause);
                firstUserStartTimer();
            }
            }
        });
        mButtonStartPause2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mTimerRunning2) {
                   //pauseTimerMethod(mButtonStartPause2);
                    secondUserPauseTimer();
                }
                else {
                    //startTimerMethod(mButtonStartPause2);
                    secondUserStartTime();
                }
            }
        });

       /* mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            resetTimer();
            }
        })*/;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settingsmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int item_id = item.getItemId();
        if(item_id==R.id.setTimer) {

        }
        if(item_id==R.id.resetTimerEvent) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Are you sure?");
            builder.setCancelable(true);
            builder.setNegativeButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    resetTimer();
                    //resetTimer2();
                }
            });
            builder.setPositiveButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            AlertDialog alertDialog= builder.create();
            alertDialog.show();
            /*resetTimer();
            resetTimer2();*/
        }
        if(item_id==R.id.setTimer) {

        }

        if(item_id==R.id.exit) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Are you sure?");
            builder.setCancelable(true);
            builder.setNegativeButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            builder.setPositiveButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            AlertDialog alertDialog= builder.create();
            alertDialog.show();
        }

        return true;
    }
    private void startTimerMethod(Button button) {
        countDownTimer = new CountDownTimer(mTimeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                if (button == mButtonStartPause) {
                    firstUserUpdate();


                }
                else if(button==mButtonStartPause2) {
                    secondUserUpdate();
                }
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                mButtonStartPause.setText("Start");

                mButtonStartPause.setVisibility(View.INVISIBLE);

                //mButtonReset.setVisibility(View.VISIBLE);
            }
        }.start();
        mTimerRunning = true;
        mButtonStartPause.setText("pause");
    }
    private void firstUserStartTimer() {
    countDownTimer = new CountDownTimer(mTimeLeftInMillis,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            mTimeLeftInMillis = millisUntilFinished;
            firstUserUpdate();

        }

        @Override
        public void onFinish() {
        mTimerRunning = false;
        mButtonStartPause.setText("Start");

        mButtonStartPause.setVisibility(View.INVISIBLE);

        //mButtonReset.setVisibility(View.VISIBLE);
        }
    }.start();
    mTimerRunning = true;
    mButtonStartPause.setText("pause");

    //mButtonReset.setVisibility(View.INVISIBLE);

    }
    private void secondUserStartTime() {
        countDownTimer2 = new CountDownTimer(mTimeLeftInMillis2,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis2 = millisUntilFinished;
                secondUserUpdate();

            }

            @Override
            public void onFinish() {
                mTimerRunning2 = false;
                mButtonStartPause2.setText("Start");

                mButtonStartPause2.setVisibility(View.INVISIBLE);

                //mButtonReset.setVisibility(View.VISIBLE);
            }
        }.start();
        mTimerRunning2 = true;
        mButtonStartPause2.setText("pause");

        //mButtonReset.setVisibility(View.INVISIBLE);

    }

    private void firstUserPauseTimer() {
        countDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("Start");

        //mButtonReset.setVisibility(View.VISIBLE);
    }
    private void pauseTimerMethod(Button button) {
        countDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("Start");

        //mButtonReset.setVisibility(View.VISIBLE);
    }
    private void secondUserPauseTimer() {
        countDownTimer2.cancel();
        mTimerRunning2 = false;
        mButtonStartPause2.setText("Start");

        //mButtonReset.setVisibility(View.VISIBLE);
    }
    private void resetTimer() {
    mTimeLeftInMillis = START_TIME_IN_MILLIS;
    firstUserUpdate();
    //mButtonReset.setVisibility(View.INVISIBLE);
    mButtonStartPause.setVisibility(View.VISIBLE);
        mTimeLeftInMillis2 = START_TIME_IN_MILLIS;
        secondUserUpdate();
        // mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause2.setVisibility(View.VISIBLE);

    }

    private void firstUserUpdate() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormat = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        mTextViewCountDown.setText(timeLeftFormat);


    }
    private void secondUserUpdate() {
        int minutes = (int) (mTimeLeftInMillis2 / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis2 / 1000) % 60;
        String timeLeftFormat = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        mTextViewCountDown2.setText(timeLeftFormat);


    }
    /*public void start_timer(View view) {

    }*/
}