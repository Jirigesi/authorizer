/*
 * Copyright (©) 2019 Jeff Harris <jefftharris@gmail.com>
 * All rights reserved. Use of the code is allowed under the
 * Artistic License 2.0 terms, as specified in the LICENSE file
 * distributed with this code, or available from
 * http://www.opensource.org/licenses/artistic-license-2.0.php
 */
package uci.tjado.passwdsafe;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class menu_test_jiri
{

    @Rule
    public ActivityTestRule<FileListActivity> mActivityTestRule = new ActivityTestRule<>(
            FileListActivity.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.WRITE_EXTERNAL_STORAGE");

    @Test
    public void menu_test_jiri()
    {
        ViewInteraction textView = onView(
                allOf(withText("Authorizer"),
                      childAtPosition(
                              allOf(withId(R.id.action_bar),
                                    childAtPosition(
                                            withId(R.id.action_bar_container),
                                            0)),
                              1),
                      isDisplayed()));
        textView.check(matches(withText("Authorizer")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.menu_file_new),
                      withContentDescription("New Local File"),
                      childAtPosition(
                              childAtPosition(
                                      withId(R.id.action_bar),
                                      2),
                              0),
                      isDisplayed()));
        textView2.check(matches(withText("")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.text), withText("Alarms"),
                      childAtPosition(
                              childAtPosition(
                                      IsInstanceOf.<View>instanceOf(
                                              android.widget.LinearLayout.class),
                                      1),
                              0),
                      isDisplayed()));
        textView3.check(matches(withText("Alarms")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position)
    {

        return new TypeSafeMatcher<View>()
        {
            @Override
            public void describeTo(Description description)
            {
                description.appendText(
                        "Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view)
            {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup &&
                       parentMatcher.matches(parent)
                       && view.equals(((ViewGroup)parent).getChildAt(position));
            }
        };
    }
}
