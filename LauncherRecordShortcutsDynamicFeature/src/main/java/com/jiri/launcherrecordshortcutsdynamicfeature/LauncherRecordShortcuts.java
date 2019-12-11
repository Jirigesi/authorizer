/*
 * Copyright (©) 2019 Jeff Harris <jefftharris@gmail.com>
 * All rights reserved. Use of the code is allowed under the
 * Artistic License 2.0 terms, as specified in the LICENSE file
 * distributed with this code, or available from
 * http://www.opensource.org/licenses/artistic-license-2.0.php
 */
package com.jiri.launcherrecordshortcutsdynamicfeature;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

import uci.net2.tjado.passwdsafe.PasswdSafeApp;
import uci.net2.tjado.passwdsafe.PasswdSafeFileDataFragment;
import uci.net2.tjado.passwdsafe.PasswdSafeListFragment;
import uci.net2.tjado.passwdsafe.Preferences;
import uci.net2.tjado.passwdsafe.file.PasswdFileData;
import uci.net2.tjado.passwdsafe.file.PasswdFileDataUser;
import uci.net2.tjado.passwdsafe.file.PasswdRecordFilter;
import uci.net2.tjado.passwdsafe.lib.PasswdSafeUtil;
import uci.net2.tjado.passwdsafe.lib.view.GuiUtils;
import uci.net2.tjado.passwdsafe.lib.ObjectHolder;
import uci.net2.tjado.passwdsafe.util.Pair;
import uci.net2.tjado.passwdsafe.view.CopyField;
import uci.net2.tjado.passwdsafe.view.PasswdFileDataView;
import uci.net2.tjado.passwdsafe.view.PasswdLocation;
import uci.net2.tjado.passwdsafe.view.PasswdRecordListData;

import org.pwsafe.lib.file.PwsRecord;

import java.util.List;

public class LauncherRecordShortcuts extends AppCompatActivity
        implements PasswdSafeListFragment.Listener,
                   SharedPreferences.OnSharedPreferenceChangeListener
{
    /**
     * Intent flag to apply a filter to show records that have no aliases
     * referencing them
     */
    public static final String FILTER_NO_ALIAS = "filterNoAlias";

    /**
     * Intent flag to apply a filter to show records that have no shortcuts
     * referencing them
     */
    public static final String FILTER_NO_SHORTCUT = "filterNoShortcut";

    private enum Mode
    {
        SHORTCUT,
        CHOOSE_RECORD
    }

    private static final String TAG = "LauncherRecordShortcuts";

    private final PasswdFileDataView itsFileDataView = new PasswdFileDataView();
    private PasswdLocation itsLocation = new PasswdLocation();
    private Mode itsMode;
    private TextView itsFile;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        PasswdSafeApp.setupDialogTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(uci.net2.tjado.passwdsafe.R.layout.activity_launcher_record_shortcuts);
        SharedPreferences prefs = Preferences.getSharedPrefs(this);
        prefs.registerOnSharedPreferenceChangeListener(this);

        itsFile = (TextView)findViewById(uci.net2.tjado.passwdsafe.R.id.file);
        itsFileDataView.onAttach(this, prefs);

        Intent intent = getIntent();
        switch (intent.getAction()) {
        case Intent.ACTION_CREATE_SHORTCUT: {
            setTitle(uci.net2.tjado.passwdsafe.R.string.shortcut_record);
            itsMode = Mode.SHORTCUT;
            break;
        }
        case PasswdSafeApp.CHOOSE_RECORD_INTENT: {
            setTitle(uci.net2.tjado.passwdsafe.R.string.choose_record);
            itsMode = Mode.CHOOSE_RECORD;
            GuiUtils.setVisible(itsFile, false);
            break;
        }
        default: {
            finish();
            return;
        }
        }

        int options = PasswdRecordFilter.OPTS_DEFAULT;
        if (intent.getBooleanExtra(FILTER_NO_ALIAS, false)) {
            options |= PasswdRecordFilter.OPTS_NO_ALIAS;
        }
        if (intent.getBooleanExtra(FILTER_NO_SHORTCUT, false)) {
            options |= PasswdRecordFilter.OPTS_NO_SHORTCUT;
        }
        if (options != PasswdRecordFilter.OPTS_DEFAULT) {
            itsFileDataView.setRecordFilter(new PasswdRecordFilter(null,
                                                                   options));
        }

        if (savedInstanceState == null) {
            FragmentManager fragMgr = getSupportFragmentManager();
            FragmentTransaction txn = fragMgr.beginTransaction();
            txn.replace(uci.net2.tjado.passwdsafe.R.id.contents,
                        PasswdSafeListFragment.newInstance(itsLocation, true));
            txn.commit();
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();

        itsFileDataView.clearFileData();
        final ObjectHolder<String> fileTitle = new ObjectHolder<>();
        PasswdSafeFileDataFragment.useOpenFileData(
                new PasswdFileDataUser()
                {
                    @Override
                    public void useFileData(
                            @NonNull PasswdFileData fileData)
                    {
                        itsFileDataView.setFileData(fileData);
                        fileTitle.set(fileData.getUri().getIdentifier(
                                LauncherRecordShortcuts.this, true));
                    }
                });
        String fileTitleVal = fileTitle.get();
        if (fileTitleVal != null) {
            itsFile.setText(fileTitleVal);
        } else {
            itsFile.setText(uci.net2.tjado.passwdsafe.R.string.no_records_open_file);
            GuiUtils.setVisible(findViewById(uci.net2.tjado.passwdsafe.R.id.contents), false);
        }
    }

    @Override
    public void onPause()
    {
        super.onPause();
        itsFileDataView.clearFileData();
    }

    @Override
    public void onDestroy()
    {
        SharedPreferences prefs = Preferences.getSharedPrefs(this);
        prefs.unregisterOnSharedPreferenceChangeListener(this);
        itsFileDataView.onDetach();
        super.onDestroy();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences prefs, String key)
    {
        if (itsFileDataView.handleSharedPreferenceChanged(prefs, key)) {
            PasswdSafeFileDataFragment.useOpenFileData(new PasswdFileDataUser()
            {
                @Override
                public void useFileData(@NonNull PasswdFileData fileData)
                {
                    itsFileDataView.refreshFileData(fileData);
                }
            });
        }
    }

    @Override
    public void copyField(CopyField field, String recUuid)
    {
        // Not supported
    }

    @Override
    public boolean isCopySupported()
    {
        return false;
    }

    @Override
    public void changeLocation(PasswdLocation location)
    {
        PasswdSafeUtil.dbginfo(TAG, "changeLocation: ", location);
        if (location.isRecord()) {
            selectRecord(location.getRecord());
        } else if (!itsLocation.equals(location)) {
            FragmentManager fragMgr = getSupportFragmentManager();
            FragmentTransaction txn = fragMgr.beginTransaction();
            txn.setTransition(FragmentTransaction.TRANSIT_NONE);
            txn.replace(uci.net2.tjado.passwdsafe.R.id.contents,
                        PasswdSafeListFragment.newInstance(location, true));
            txn.addToBackStack(null);
            txn.commit();
        }
    }

    @Override
    public List<PasswdRecordListData> getBackgroundRecordItems(
            boolean incRecords, boolean incGroups)
    {
        return itsFileDataView.getRecords(incRecords, incGroups);
    }

    @Override
    public void updateViewList(PasswdLocation location)
    {
        PasswdSafeUtil.dbginfo(TAG, "updateViewList: ", location);
        itsLocation = location;
        itsFileDataView.setCurrGroups(itsLocation.getGroups());

        FragmentManager fragMgr = getSupportFragmentManager();
        Fragment contentsFrag = fragMgr.findFragmentById(uci.net2.tjado.passwdsafe.R.id.contents);
        if (contentsFrag instanceof PasswdSafeListFragment) {
            ((PasswdSafeListFragment)contentsFrag).updateLocationView(
                    itsLocation, PasswdSafeListFragment.Mode.ALL);
        }
    }

    /**
     * Select the given record and return a result
     */
    private void selectRecord(final String uuid)
    {
        switch (itsMode) {
        case SHORTCUT: {
            final ObjectHolder<Pair<Uri, String>> rc = new ObjectHolder<>();
            PasswdSafeFileDataFragment.useOpenFileData(
                    new PasswdFileDataUser()
                    {
                        @Override
                        public void useFileData(
                                @NonNull PasswdFileData fileData)
                        {
                            PwsRecord rec = fileData.getRecord(uuid);
                            String title = fileData.getTitle(rec);
                            rc.set(new Pair<>(fileData.getUri().getUri(),
                                              title));
                        }
                    });
            Pair<Uri, String> rcval = rc.get();
            if (rcval != null) {
                Intent shortcutIntent = PasswdSafeUtil.createOpenIntent(
                        rcval.first, uuid);

                Intent intent = new Intent();
                intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
                intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, rcval.second);
                intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                                Intent.ShortcutIconResource.fromContext(
                                        this, uci.net2.tjado.passwdsafe.R.mipmap.ic_launcher_passwdsafe));
                setResult(RESULT_OK, intent);
            }
            break;
        }
        case CHOOSE_RECORD: {
            Intent intent = new Intent();
            intent.putExtra(PasswdSafeApp.RESULT_DATA_UUID, uuid);
            setResult(RESULT_OK, intent);
            break;
        }
        }
        finish();
    }
}
