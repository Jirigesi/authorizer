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
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class BGB_test
{

    @Rule
    public ActivityTestRule<FileListActivity> mActivityTestRule = new ActivityTestRule<>(
            FileListActivity.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.WRITE_EXTERNAL_STORAGE");

    @Test
    public void bGB_test()
    {
        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                      childAtPosition(
                              allOf(withId(R.id.action_bar),
                                    childAtPosition(
                                            withId(R.id.action_bar_container),
                                            0)),
                              1),
                      isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction navigationMenuItemView = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                              childAtPosition(
                                      withId(R.id.navigation_drawer),
                                      0)),
                        2),
                      isDisplayed()));
        navigationMenuItemView.perform(click());

        ViewInteraction linearLayout = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.recycler_view),
                              childAtPosition(
                                      withId(android.R.id.list_container),
                                      0)),
                        2),
                      isDisplayed()));
        linearLayout.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.message), withText(
                        "If this function is enabled, a backup dialog will open as soon as a USB mass storage is connected. You can select a backup folder by pressing the \"Backup now!\" button. A file named \"pubkey.asc\" - containing a GPG public key - has to be inside this folder. If it exists, a copy of the default file will be encrypted with this key and stored in the selected folder."),
                      childAtPosition(
                              childAtPosition(
                                      withId(R.id.scrollView),
                                      0),
                              0),
                      isDisplayed()));
        textView.check(matches(withText(
                "If this function is enabled, a backup dialog will open as soon as a USB mass storage is connected. You can select a backup folder by pressing the \"Backup now!\" button. A file named \"pubkey.asc\" - containing a GPG public key - has to be inside this folder. If it exists, a copy of the default file will be encrypted with this key and stored in the selected folder.")));
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
