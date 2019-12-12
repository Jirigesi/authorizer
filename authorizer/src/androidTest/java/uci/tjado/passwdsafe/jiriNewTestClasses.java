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
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class jiriNewTestClasses
{

    @Rule
    public ActivityTestRule<FileListActivity> mActivityTestRule = new ActivityTestRule<>(
            FileListActivity.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.WRITE_EXTERNAL_STORAGE");

    @Test
    public void jiriNewTestClasses()
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
                        3),
                      isDisplayed()));
        navigationMenuItemView.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.release_notes), withText(
                        "\n0.4.0\n- New feature: Bluetooth Auto-Type (min. Android Pie required)\n- New feature: OTP Auto-Type Button\n- New keyboard layout (en_GB)\n- Wording adjusted\n- Additional small improvements\n- Bug fixes \n\n0.3.0\n- New feature: OTP support\n- New feature: Placeholders in username and password\n- Beta status removed\n- Increase of security \n\n0.2.6\n- New feature: GPG encrypted backup on USB mass storage\n- Bug fixes and clean up \n\n0.2.5 - New keyboard layouts and bug fixes. \n\n0.2.4\n- Using now superuser/root to access /dev/hidg0 (USB HID device)\n- Added setting to deactivate USB Keyboard output\n- A lot of design adjustments, e.g. major rework of TreeView\n- Added icons for password entries (shown in TreeView)\n- Some merges with the original PasswdSafe for Android app \n\n0.2.3 - Design enhancements. \n\n0.2.0 - First release of Authorizer - a new app based on (a fork of) Passwd Safe for Android. \n\n0.1.0 - Authorizer Proof-of-Concept version. \n\n--------------------------------------------- \n\nOld PasswdSafe Release Notes: \n\n6.4.3 - Bug fixes. \n\n6.4.2 - Bug fixes. \n\n6.4.1 - Fix crash when opening file with saved password. Fix dark text in keyboard. Improve German translation. Show licenses in About screen. \n\n6.4.0 - Add option to save file passwords protected by a fingerprint scan on Android 6.0 and higher \n\n6.3.0 - Improve keyboard usability. Add basic keyboard keys for password entry. Support cycling between keyboards. \n\n6.2.0 - Allow a few retries for an invalid password when opening a file. Add menu items to copy URL and email fields. Use a font with a slashed zero for passwords. \n\n6.1.2 - Fix crash when opening preferences. Bug fixes. \n\n6.1.1 - Request storage permission if needed. Fix copy-and-paste bug. \n\n6.1.0 - Add preference to choose a dark theme. Improve French translation. Add long-press handlers to copy user names and passwords. \n\n6.0.2 - Improve German translation. Attempt a fix for permission denial error when opening files. \n\n6.0.1 - Add a menu option to copy the password to the clipboard with a warning \n\n6.0.0 - User interface rewrite for material design and tablet support \n\n5.4.0\n- Add OneDrive support. After authenticating an account, choose the files to sync.\n- Add record preference to sort groups before, after, or mixed with records \n\n5.3.2 - Use previous file chooser by default \n\n5.3.1 - Add help for enabling internal storage for the new chooser or enabling the legacy chooser. \n\n5.3.0 - Add slider to reveal parts of a password. On Kitkat and higher, use the built-in document manager to open or create files. \n\n5.2.0 - Add ownCloud support. Accounts are chosen from the list in the ownCloud app. Once access is authorized, password files can be chosen to sync. \n\n5.1.0 - Add multi-window support for Samsung devices. Enable selection of notes text. \n\n5.0.0 - YubiKey NEO support for devices with NFC \n\n4.7.3 - Maintain an open file when the app is launched from the home screen \n\n4.7.2 - Performance improvement when opening a file with many password iterations. \n\n4.7.1 - Fix crash with preferences \n\n4.7.0 - Add German translation \n\n4.6.0\n- Attempt to detect read-only files on SD cards with Kitkat. Please send feedback if the detection is or is not working.\n- Show the current password while editing for reference\n- Show modification time of local files in file list \n\n4.5.0 - Add Box support with PasswdSafe Sync. Password files (.psafe3) are synced from the top folder and any folder tagged with 'passwdsafe'. \n\n4.4.0\n- Add a password preference for the set of symbols to use by default in password policies\n- The current group is set as the default for new records\n- Synchronized files can be set as a default file \n\n4.3.0 - Show a file's folder. \n\n4.2.0 - An input method is available which can paste fields from the last selected password record. The input method must be enabled in the system settings before it can be used. \n\nTo use:\n- Select a record in PasswdSafe\n- Open the app which will use the record's fields\n- Switch to the PasswdSafe input method\n- Select the fields to paste values into the app\n- Select the PasswdSafe icon to choose a different record if needed\n"),
                      childAtPosition(
                              childAtPosition(
                                      IsInstanceOf.<View>instanceOf(
                                              android.widget.ScrollView.class),
                                      0),
                              2),
                      isDisplayed()));
        textView.check(matches(withText(
                " 0.4.0 - New feature: Bluetooth Auto-Type (min. Android Pie required) - New feature: OTP Auto-Type Button - New keyboard layout (en_GB) - Wording adjusted - Additional small improvements - Bug fixes   0.3.0 - New feature: OTP support - New feature: Placeholders in username and password - Beta status removed - Increase of security   0.2.6 - New feature: GPG encrypted backup on USB mass storage - Bug fixes and clean up   0.2.5 - New keyboard layouts and bug fixes.   0.2.4 - Using now superuser/root to access /dev/hidg0 (USB HID device) - Added setting to deactivate USB Keyboard output - A lot of design adjustments, e.g. major rework of TreeView - Added icons for password entries (shown in TreeView) - Some merges with the original PasswdSafe for Android app   0.2.3 - Design enhancements.   0.2.0 - First release of Authorizer - a new app based on (a fork of) Passwd Safe for Android.   0.1.0 - Authorizer Proof-of-Concept version.   ---------------------------------------------   Old PasswdSafe Release Notes:   6.4.3 - Bug fixes.   6.4.2 - Bug fixes.   6.4.1 - Fix crash when opening file with saved password. Fix dark text in keyboard. Improve German translation. Show licenses in About screen.   6.4.0 - Add option to save file passwords protected by a fingerprint scan on Android 6.0 and higher   6.3.0 - Improve keyboard usability. Add basic keyboard keys for password entry. Support cycling between keyboards.   6.2.0 - Allow a few retries for an invalid password when opening a file. Add menu items to copy URL and email fields. Use a font with a slashed zero for passwords.   6.1.2 - Fix crash when opening preferences. Bug fixes.   6.1.1 - Request storage permission if needed. Fix copy-and-paste bug.   6.1.0 - Add preference to choose a dark theme. Improve French translation. Add long-press handlers to copy user names and passwords.   6.0.2 - Improve German translation. Attempt a fix for permission denial error when opening files.   6.0.1 - Add a menu option to copy the password to the clipboard with a warning   6.0.0 - User interface rewrite for material design and tablet support   5.4.0 - Add OneDrive support. After authenticating an account, choose the files to sync. - Add record preference to sort groups before, after, or mixed with records   5.3.2 - Use previous file chooser by default   5.3.1 - Add help for enabling internal storage for the new chooser or enabling the legacy chooser.   5.3.0 - Add slider to reveal parts of a password. On Kitkat and higher, use the built-in document manager to open or create files.   5.2.0 - Add ownCloud support. Accounts are chosen from the list in the ownCloud app. Once access is authorized, password files can be chosen to sync.   5.1.0 - Add multi-window support for Samsung devices. Enable selection of notes text.   5.0.0 - YubiKey NEO support for devices with NFC   4.7.3 - Maintain an open file when the app is launched from the home screen   4.7.2 - Performance improvement when opening a file with many password iterations.   4.7.1 - Fix crash with preferences   4.7.0 - Add German translation   4.6.0 - Attempt to detect read-only files on SD cards with Kitkat. Please send feedback if the detection is or is not working. - Show the current password while editing for reference - Show modification time of local files in file list   4.5.0 - Add Box support with PasswdSafe Sync. Password files (.psafe3) are synced from the top folder and any folder tagged with 'passwdsafe'.   4.4.0 - Add a password preference for the set of symbols to use by default in password policies - The current group is set as the default for new records - Synchronized files can be set as a default file   4.3.0 - Show a file's folder.   4.2.0 - An input method is available which can paste fields from the last selected password record. The input method must be enabled in the system settings before it can be used.   To use: - Select a record in PasswdSafe - Open the app which will use the record's fields - Switch to the PasswdSafe input method - Select the fields to paste values into the app - Select the PasswdSafe icon to choose a different record if needed ")));
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
