/**
 * Authorizer
 *
 *  Copyright 2016 by Tjado Mäcke <tjado@maecke.de>
 *  Licensed under GNU General Public License 3.0.
 *
 * @license GPL-3.0 <https://opensource.org/licenses/GPL-3.0>
 */

package net2.tjado.passwdsafe;

import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.content.SharedPreferences;
import android.hardware.usb.UsbManager;

import net2.tjado.passwdsafe.lib.PasswdSafeUtil;


public class UsbGpgBackupReceiver extends BroadcastReceiver
{
    private static final String TAG = "UsbGpgBackupReceiver";

    /* (non-Javadoc)
     * @see android.content.BroadcastReceiver#onReceive(android.content.Context, android.content.Intent)
     */
    @Override
    public void onReceive(Context context, Intent intent)
    {
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
        i.setClassName("net2.tjado.passwdsafe", "net2.tjado.passwdsafe.UsbGpgBackupActivity");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
