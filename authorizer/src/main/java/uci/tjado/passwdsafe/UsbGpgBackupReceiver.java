/**
 * Authorizer
 *
 *  Copyright 2016 by Tjado Mäcke <tjado@maecke.de>
 *  Licensed under GNU General Public License 3.0.
 *
 * @license GPL-3.0 <https://opensource.org/licenses/GPL-3.0>
 */

package uci.tjado.passwdsafe;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.content.SharedPreferences;
import android.hardware.usb.UsbManager;

import uci.tjado.passwdsafe.lib.PasswdSafeUtil;


public class UsbGpgBackupReceiver extends BroadcastReceiver
{
    private static final String TAG = "UsbGpgBackupReceiver";


    /* (non-Javadoc)
     * @see android.content.BroadcastReceiver#onReceive(android.content.Context, android.content.Intent)
     */
    @Override
    public void onReceive(Context context, Intent intent)
    {
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) context. getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);
        boolean ramLow = mi.lowMemory;


        if (!UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(intent.getAction())) {
            PasswdSafeUtil.dbginfo(TAG, "onReceive - aborting");
            return;
        }

        SharedPreferences prefs = Preferences.getSharedPrefs(context);
        if (!Preferences.getFileBackupUsbGpg(prefs)) {
            PasswdSafeUtil.dbginfo(TAG, "onReceive - not enabled");
            return;
        }
        PasswdSafeUtil.dbginfo(TAG, "onReceive - starting activity...");

        Intent i = new Intent();
        i.setClassName("net.tjado.passwdsafe", "net.tjado.passwdsafe.UsbGpgBackupActivity");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
