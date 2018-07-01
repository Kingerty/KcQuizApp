package app.com.example.nnabugwukc.kcquizapp.data;

/**
 * Created by Frederick on 5/19/2018.
 */

import android.graphics.drawable.Drawable;

public class DataList {

    private String mOptionItem;
    private String letter;

    public DataList(String mOption, String letter) {
        this.mOptionItem = mOption;
        this.letter = letter;
    }

    public String getmOptionItem() {
        return mOptionItem;
    }

    public void setmOptionItem(String mOptionItem) {
        this.mOptionItem = mOptionItem;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getLetter() {
        return letter;
    }
}
