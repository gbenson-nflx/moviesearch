package moviesearch.netflix;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import moviesearch.netflix.ui.MovieListActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class AppTitleEspressoTest {

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(MovieListActivity.class);

    @Test
    public void checkAppTitleIsDisplayed() {
        String name = mActivityRule.getActivity().getString(R.string.app_name);
        onView(withText(name)).check(matches(isDisplayed()));
    }
}
