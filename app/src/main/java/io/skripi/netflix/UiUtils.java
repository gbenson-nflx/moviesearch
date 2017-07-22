package moviesearch.netflix;

import android.content.res.Resources;
import android.util.TypedValue;

public class UiUtils {

    public static float dpToPx(Resources resources, int dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources
                .getDisplayMetrics());
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }
}
