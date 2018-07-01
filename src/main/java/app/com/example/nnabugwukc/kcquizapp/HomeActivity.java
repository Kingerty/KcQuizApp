package app.com.example.nnabugwukc.kcquizapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import app.com.example.nnabugwukc.kcquizapp.adapters.OptionAdapter;
import app.com.example.nnabugwukc.kcquizapp.data.DataList;

public class HomeActivity extends AppCompatActivity
        implements View.OnClickListener, AdapterView.OnItemClickListener {
    private String[] mQuestionsArray = {
            "The tiny opening through which plants breathe is known as ____",
            "___ doctor takes care of the skin",
            "Nigeria is divided into ___ geopolitical zones",
            "Pre-marital sexual relationship can lead to ___",
            "The love for ones country is known as ___",
            "The bone that protects the brain is called ____",
            "The essence of mulching includes all the following except",
            "Plants with nodules are called ___",
            "An insect that attacks yam tubers is ___",
            "Animals with complex stomach are called ____"
    };
    private String[] mQuestionNumArray = {"1","2","3","4","5","6","7","8","9","10"};
    private String[] mAnswersArray = {
            "Stomata",
            "Dermatologist",
            "6",
            "Unwanted pregnancy",
            "Patriotism",
            "Skull",
            "Change in temperature",
            "legumes",
            "Weevil",
            "Ruminants"
    };
    private int[] mNumArray = {
            0,
            2,
            1,
            1,
            2,
            0,
            2,
            0,
            1,
            1
    };
    private String[][] mOptionsArray = {
            {"Stomata","Osmosis","Hole"},
            {"Dentist","Gynaecologist","Dermatologist"},
            {"7","6","8"},
            {"Madness","Unwanted pregnancy","Loss of appetite"},
            {"Statism","Aligience","Patriotism"},
            {"Skull","Fibular","Tibia"},
            {"Control of weeds","Increase in fertility ","Change in temperature"},
            {"Legumes","Membrane","Eukaryotic"},
            {"Beetle","Weevil","Cricket"},
            {"Rumen","Ruminants","Mammals"}
    };
    private String[] mLettersArray = {"A","B","C"};
    //store the selected option
    //private String mSelectedOption;
    private int mPoint = 0,mSelectedOption;
    //these counters are used to iterate through the two dimensional array
    private int mCounter1,mCounter2;
    //---------------------
    private List<DataList> mOptions;
    private ListView mOptionListView;
    private OptionAdapter mOptionsAdapter;
    //----------------------
    //main mQuestion textView
    private TextView mQuestion,mQuestionNumber,mPointView;
    private ImageView mOkBtn;
    private RelativeLayout mTopBg,mTopBg2;
    private boolean doubleBackToExitPressedOnce;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //overridePendingTransition(R.anim.fadein,R.anim.fadeout);
        overridePendingTransition(R.anim.fadein_a_bit,R.anim.slide_out_from_right);
        setContentView(R.layout.app_bar_home);
        mQuestion = (TextView)findViewById(R.id.topQuestion);
        mQuestionNumber = (TextView)findViewById(R.id.questionNumber);
        mPointView = (TextView)findViewById(R.id.point);
        mTopBg = (RelativeLayout) findViewById(R.id.topView);
        mTopBg2 = (RelativeLayout) findViewById(R.id.topView2);
        //--------
        mOkBtn = (ImageView) findViewById(R.id.btnOk);
        mOkBtn.setOnClickListener(this);
        mQuestion.setText(mQuestionsArray[0]);
        mQuestionNumber.setText(mQuestionNumArray[0]);
        mOptionListView = (ListView)findViewById(R.id.optionList);
        runZoonInAnim(mTopBg);
        /*mOptions = getOptionList(0);
        mOptionsAdapter = new OptionAdapter(this, mOptions);
        mOptionListView.setAdapter(mOptionsAdapter);*/
        runAnimation(mOptionListView,1,0);
        mOptionListView.setOnItemClickListener(this);
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
    public void runFadeOutInAnimation(final View view){
        Animation anim1 = AnimationUtils.loadAnimation(this,R.anim.fadeout);
        final Animation anim2 = AnimationUtils.loadAnimation(this,R.anim.fadein);
        view.startAnimation(anim1);
        anim1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                moveToNextQuestion();
                view.startAnimation(anim2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    private List<DataList> getOptionList(int counter) {
        List<DataList> res = new ArrayList<DataList>();
        for(int i = 0;i < mLettersArray.length;i++){
            res.add(new DataList(mOptionsArray[counter][i], mLettersArray[i]));
            //
        }
        return res;
    }

    private void runAnimation(ListView mOptionListView, int type,int counter) {
        Context context = mOptionListView.getContext();
        LayoutAnimationController controller = null;
        if(type == 0) controller = AnimationUtils.loadLayoutAnimation(context,R.anim.layout_anim_down);
        else if(type == 1) controller = AnimationUtils.loadLayoutAnimation(context,R.anim.layout_anim_up);
        else if(type == 2) controller = AnimationUtils.loadLayoutAnimation(context,R.anim.layout_anim_from_right);
        else if(type == 3) controller = AnimationUtils.loadLayoutAnimation(context,R.anim.layout_anim_from_left);

        //mAdapter = new RecyclerAdapter(this,mListData);
        mOptions = getOptionList(counter);
        mOptionsAdapter = new OptionAdapter(this, mOptions);
        mOptionListView.setAdapter(mOptionsAdapter);
        //
        mOptionListView.setLayoutAnimation(controller);
        mOptionsAdapter.notifyDataSetChanged();
        //mOptionListView.getAdapter().notifyDataSetChanged();
        mOptionListView.scheduleLayoutAnimation();
        //
        //this.mOptionListView.setAdapter(mOptionsAdapter);
        //this.mOptionListView.getAdapter().n
    }

    public void moveToNextQuestion(){
        if(mCounter1 < 9) {
            mCounter1 += 1;
        }
        mQuestion.setText(mQuestionsArray[mCounter1]);
        mQuestionNumber.setText(mQuestionNumArray[mCounter1]);
        /*mOptions = getOptionList(mCounter1);
        mOptionsAdapter = new OptionAdapter(this, mOptions);
        mOptionListView.setAdapter(mOptionsAdapter);*/
    }
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    @Override
    public void onClick(View v) {
        if(v == mOkBtn) {
            mOptions.clear();
            if(mCounter2 < 9) {
                mCounter2 += 1;
                mPoint += mSelectedOption;
                mPointView = (TextView)findViewById(R.id.point);
                mPointView.setText(""+mPoint);
                runFadeOutInAnimation(mTopBg2);
                runAnimation(mOptionListView,2,mCounter2);
            }else{
                SharedPreferences mPref = getSharedPreferences("QUIZ_GAME", MODE_PRIVATE);
                SharedPreferences.Editor editor = mPref.edit();
                editor.putBoolean("game_finished",true);
                editor.putInt("my_score",mPoint);
                editor.apply();
                Intent intent = new Intent(HomeActivity.this,StartActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //String selected = mOptionListView.getItemAtPosition(position).toString();

        if(mNumArray[mCounter2] == position){
            mSelectedOption = 1;
        }else{
            mSelectedOption = 0;
        }
        //Toast.makeText(this, "Selected="+mSelectedOption, Toast.LENGTH_SHORT).show();
        /*Toast.makeText(this, selected+" selected. counter="+mCounter2, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "AnswerArry="+mAnswersArray[mCounter2], Toast.LENGTH_SHORT).show();*/
    }
}
