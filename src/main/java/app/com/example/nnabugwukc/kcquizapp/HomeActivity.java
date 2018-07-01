package app.com.example.nnabugwukc.kcquizapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import app.com.example.nnabugwukc.kcquizapp.adapters.OptionAdapter;
import app.com.example.nnabugwukc.kcquizapp.data.DataList;

public class HomeActivity extends AppCompatActivity
        implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private String[] mQuestionsArray = {
            "1. The tiny opening through which plants breathe is known as ____",
            "2. ___ doctor takes care of the skin",
            "3. Nigeria is divided into ___ geopolitical zones",
            "4. Pre-marital sexual relationship can lead to ___",
            "5. The love for ones country is known as ___",
            "6. The bone that protects the brain is called ____",
            "7. The essence of mulching includes all the following except",
            "8. Plants with nodules are called ___",
            "9. An insect that attacks yam tubers is ___",
            "10. Animals with complex stomach are called ____"
    };
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
    private String[][] mOptionsArray = {
            {"Stomata","Osmosis","Hole"},
            {"Dentist","Gynaecologist","Dermatologist"},
            {"7","6","8"},
            {"Madness","Unwanted pregnancy","Loss of appetite"},
            {"Statism","Aligience","Patriotism"},
            {"Skull","Fibular","Tibia"},
            {"Control of weeds","Increase in fertility ","Change in temperature"},
            {"Legumes","","",""},
            {"","","",""},
            {"","","",""}
    };
    private String[] mLettersArray = {"A","B","C"};
    //these counters are used to iterate through the two dimensional array
    private int mCounter1,mCounter2;
    //---------------------
    private List<DataList> mOptions;
    private ListView mOptionListView;
    private OptionAdapter mOptionsAdapter;
    //main mQuestion textView
    private TextView mQuestion;
    private ImageView mOkBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //overridePendingTransition(R.anim.fadein,R.anim.fadeout);
        setContentView(R.layout.app_bar_home);
        mQuestion = (TextView)findViewById(R.id.topQuestion);
        mOkBtn = (ImageView) findViewById(R.id.btnOk);
        mOkBtn.setOnClickListener(this);
        mQuestion.setText(mQuestionsArray[0]);
        mOptionListView = (ListView)findViewById(R.id.optionList);
        mOptions = getOptionList(0);
        mOptionsAdapter = new OptionAdapter(this, mOptions);
        mOptionListView.setAdapter(mOptionsAdapter);
    }
    private List<DataList> getOptionList(int counter) {
        List<DataList> res = new ArrayList<DataList>();
        for(int i = 0;i < mLettersArray.length;i++){
            res.add(new DataList(mOptionsArray[counter][i], mLettersArray[i]));
            //
        }
        return res;
    }

    private void runAnimation(View recyclerView, int type) {
        Context context = recyclerView.getContext();
        LayoutAnimationController controller = null;
        if(type == 0) controller = AnimationUtils.loadLayoutAnimation(context,R.anim.layout_anim_down);
        else if(type == 1) controller = AnimationUtils.loadLayoutAnimation(context,R.anim.layout_anim_up);
        else if(type == 2) controller = AnimationUtils.loadLayoutAnimation(context,R.anim.layout_anim_from_right);
        else if(type == 3) controller = AnimationUtils.loadLayoutAnimation(context,R.anim.layout_anim_from_left);

        /*mAdapter = new RecyclerAdapter(this,mListData);
        recyclerView.setAdapter(mAdapter);
        //
        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();*/
    }

    public void moveToNextQuestion(){
        mOptions.clear();;
        mCounter1 += 1;
        mQuestion.setText(mQuestionsArray[mCounter1]);
        mOptions = getOptionList(mCounter1);
        mOptionsAdapter = new OptionAdapter(this, mOptions);
        mOptionListView.setAdapter(mOptionsAdapter);
    }
    private void initControls() {
        /*viewPager = (AutoScrollViewPager) findViewById(R.id.topViewPager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        initViewPagerControls();*/

    }
    private void initViewPagerControls() {
/*
        // layouts of all welcome sliders
        // add few more layouts if you want
        layouts = new int[]{
                R.layout.head_page1,
                R.layout.head_page2,
                R.layout.head_page3};

        // adding bottom dots
        addBottomDots(0);

        // making notification bar transparent
        changeStatusBarColor();

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.fadein_with_zoom_in);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                viewPager.startAutoScroll();
                viewPager.setInterval(3000);
                viewPager.setCycle(true);
                viewPager.setStopScrollWhenTouch(true);
                viewPager.setAutoScrollDurationFactor(4);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        viewPager.startAnimation(anim);
        //viewPager.setPageTransformer(true, new ZoomOutTranformer());*/
    }
    /**
     * performs a bounce animation to any view passed as parameter
     * @param view the view to be animated
     */
    /*public void doBounceAnimation(View view){
        //Button button = (Button)findViewById(R.id.button);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.buble_scale);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        view.startAnimation(myAnim);
    }*/

    /**
     * performs an automatic bounce animation to any view passed as parameter
     * @param view the view to be animated automatically
     */
    public void autoAnimateWithBounce(final View view){
        int currentPage = 0;
        Timer timer;
        final long DELAY_MS = 1000;//delay in milliseconds before task is to be executed
        final long PERIOD_MS = 4000; // time in milliseconds between successive task executions.

            final Handler handler = new Handler();
            final Runnable Update = new Runnable() {
                public void run() {
                    //doBounceAnimation(view);
                }
            };

            timer = new Timer(); // This will create a new Thread
            timer .schedule(new TimerTask() { // task to be scheduled

                @Override
                public void run() {
                    handler.post(Update);
                }
            }, DELAY_MS, PERIOD_MS);
    }/*
    //run task on a separate thread
    private class LoadingThread extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            addListItems();
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            *//*mProgressBar.setVisibility(View.GONE);
            //initiate recycler view function
            recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            //if you want items in ur recycler view to display in a grid form
            //recyclerView.setLayoutManager(new GridLayoutManager(this,4));
            //recyclerView.setItemAnimator(new DefaultItemAnimator());
            runAnimation(recyclerView,1);
            //my preference
            SharedPreferences mPref = getSharedPreferences("JOURNAL_NOTE_ADDED",MODE_PRIVATE);
            SharedPreferences.Editor editor = mPref.edit();
            //can only perform this when new note is added
            if(mPref.getBoolean("note_added",false)) {
                //remove the tag after loading new note
                editor.remove("note_added");
                editor.apply();
            }*//*

        }

        @Override
        protected void onPreExecute() {
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }*/
    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
/*
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            if(isLogout){
                super.onBackPressed();
                overridePendingTransition(R.anim.fadein_a_bit,R.anim.slide_out_from_right);
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
    }*/

    @Override
    public void onClick(View v) {
        if(v == mOkBtn) {
            moveToNextQuestion();
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //lockType.setSelection(0);
        //((TextView)parent.getChildAt(0)).setTextColor(0x808080);
    }
}
