package info.androidhive.materialtabs.utility;

import android.content.Context;
import android.graphics.Typeface;

public class Utils {

    public static Typeface setFontStyle(Context mContext, String style) {
        Typeface tfFontUI = null;
        //set regular style
        if (style.equals("Helvetica_Regular")) {
            tfFontUI = Typeface.createFromAsset(mContext.getAssets(), "Helvetica_Regular.ttf");
            return tfFontUI;
        }
        //set bold style
        if (style.equals("Helvetica_Light")) {
            tfFontUI = Typeface.createFromAsset(mContext.getAssets(), "Helvetica_Light.ttf");
            return tfFontUI;
        }

        return tfFontUI;

    }






}
