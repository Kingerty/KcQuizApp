package app.com.example.nnabugwukc.kcquizapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import app.com.example.nnabugwukc.kcquizapp.adapters.OptionAdapter;
import app.com.example.nnabugwukc.kcquizapp.data.DataList;

public class StartActivity extends AppCompatActivity
        implements View.OnClickListener {
    //---------------------
    private List<DataList> mOptions;
    private RelativeLayout mStartBtnBg,mTopBg;
    //main mQuestion textView
    private AppCompatTextView mStartBtn;
    private TextView mPoint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //overridePendingTransition(R.anim.fadein,R.anim.fadeout);
        overridePendingTransition(R.anim.slide_in_from_right,R.anim.fadeout_a_bit);
        setContentView(R.layout.activity_start_page);
        //---------------------------
        mStartBtn = (AppCompatTextView)findViewById(R.id.startBtn);
        mStartBtnBg = (RelativeLayout)findViewById(R.id.startBtnBg);
        mTopBg = (RelativeLayout)findViewById(R.id.topBg);
        mPoint = (TextView) findViewById(R.id.point);
        //-----------------------
        //get values stored in SharedPreference
        SharedPreferences mPref = getSharedPreferences("QUIZ_GAME", MODE_PRIVATE);
        if(mPref.getBoolean("game_finished",false)){
            mPoint.setVisibility(View.VISIBLE);
            mPoint.setText("Answered\n"+mPref.getInt("my_score",-1)+"/10");
        }
        Animation anim1 = AnimationUtils.loadAnimation(this,R.anim.slide_up_set_anim);
        mStartBtnBg.startAnimation(anim1);
        runZoonInAnim(mTopBg);
        mStartBtn.setOnClickListener(this);
    }
    public void runZoonInAnim(View view){
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.fadein_with_zoom_in);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(anim);

    }
    @Override
    public void onClick(View v) {
        if(v == mStartBtn) {
            //get values stored in SharedPreference
            SharedPreferences mPref = getSharedPreferences("QUIZ_GAME", MODE_PRIVATE);
            SharedPreferences.Editor editor = mPref.edit();
            if(mPref.getBoolean("game_finished",false)){
                editor.clear();
                editor.apply();
            }
            Intent intent = new Intent(StartActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fadein_a_bit,R.anim.slide_out_from_right);

    }
}
