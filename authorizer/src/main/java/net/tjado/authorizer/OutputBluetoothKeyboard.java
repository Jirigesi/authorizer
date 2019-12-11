/**
 * Authorizer
 *
 *  Copyright 2016 by Tjado Mäcke <tjado@maecke.de>
 *  Licensed under GNU General Public License 3.0.
 *
 * @license GPL-3.0 <https://opensource.org/licenses/GPL-3.0>
 */

package net.tjado.authorizer;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHidDevice;
import android.bluetooth.BluetoothHidDeviceAppSdpSettings;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.os.Build;
import android.os.SystemClock;
import androidx.annotation.RequiresApi;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@RequiresApi(Build.VERSION_CODES.P)
public class OutputBluetoothKeyboard implements OutputInterface
{
    private static final String TAG = "OutputBluetoothKeyboard";

    private UsbHidKbd kbdKeyInterpreter;
    private Context context;

    private BluetoothAdapter btAdapter;
    private BluetoothDevice btDevice;
    private BluetoothHidDevice btHidDevice;

    private String btOutputString;
    private byte[] btOutputBytes;

    static final byte HID_RID_KEYBOARD = 0x01;
    static final byte HID_RID_MOUSE = 0x02;

    private final byte[] hidDescriptor = new byte[] {
            // Generated by HID Descriptor Tool

            // Generic HID descriptor
            // ... removed due to issues with Windows 10

            // Report descriptor - Keyboard
            (byte) 0x05, (byte) 0x01,                    // USAGE_PAGE (Generic Desktop)
            (byte) 0x09, (byte) 0x06,                    // USAGE (Keyboard)
            (byte) 0xa1, (byte) 0x01,                    // COLLECTION (Application)
            (byte) 0x85, (byte) HID_RID_KEYBOARD,        //   REPORT_ID (1)
            (byte) 0x05, (byte) 0x07,                    //   USAGE_PAGE (Keyboard)
            (byte) 0x19, (byte) 0xe0,                    //   USAGE_MINIMUM (Keyboard LeftControl)
            (byte) 0x29, (byte) 0xe7,                    //   USAGE_MAXIMUM (Keyboard Right GUI)
            (byte) 0x15, (byte) 0x00,                    //   LOGICAL_MINIMUM (0)
            (byte) 0x25, (byte) 0x01,                    //   LOGICAL_MAXIMUM (1)
            (byte) 0x75, (byte) 0x01,                    //   REPORT_SIZE (1)
            (byte) 0x95, (byte) 0x08,                    //   REPORT_COUNT (8)
            (byte) 0x81, (byte) 0x02,                    //   INPUT (Data,Var,Abs)
            (byte) 0x95, (byte) 0x01,                    //   REPORT_COUNT (1)
            (byte) 0x75, (byte) 0x08,                    //   REPORT_SIZE (8)
            (byte) 0x81, (byte) 0x03,                    //   INPUT (Cnst,Var,Abs)
            (byte) 0x95, (byte) 0x05,                    //   REPORT_COUNT (5)
            (byte) 0x75, (byte) 0x01,                    //   REPORT_SIZE (1)
            (byte) 0x05, (byte) 0x08,                    //   USAGE_PAGE (LEDs)
            (byte) 0x19, (byte) 0x01,                    //   USAGE_MINIMUM (Num Lock)
            (byte) 0x29, (byte) 0x05,                    //   USAGE_MAXIMUM (Kana)
            (byte) 0x91, (byte) 0x02,                    //   OUTPUT (Data,Var,Abs)
            (byte) 0x95, (byte) 0x01,                    //   REPORT_COUNT (1)
            (byte) 0x75, (byte) 0x03,                    //   REPORT_SIZE (3)
            (byte) 0x91, (byte) 0x03,                    //   OUTPUT (Cnst,Var,Abs)
            (byte) 0x95, (byte) 0x06,                    //   REPORT_COUNT (6)
            (byte) 0x75, (byte) 0x08,                    //   REPORT_SIZE (8)
            (byte) 0x15, (byte) 0x00,                    //   LOGICAL_MINIMUM (0)
            (byte) 0x25, (byte) 0x65,                    //   LOGICAL_MAXIMUM (101)
            (byte) 0x05, (byte) 0x07,                    //   USAGE_PAGE (Keyboard)
            (byte) 0x19, (byte) 0x00,                    //   USAGE_MINIMUM (Reserved (no event indicated))
            (byte) 0x29, (byte) 0x65,                    //   USAGE_MAXIMUM (Keyboard Application)
            (byte) 0x81, (byte) 0x00,                    //   INPUT (Data,Ary,Abs)
            (byte) 0xc0,                                 // END_COLLECTION

            // Report descriptor - Mouse
            (byte) 0x05, (byte) 0x01,                    // USAGE_PAGE (Generic Desktop)
            (byte) 0x09, (byte) 0x02,                    // USAGE (Mouse)
            (byte) 0xa1, (byte) 0x01,                    // COLLECTION (Application)
            (byte) 0x85, (byte) HID_RID_MOUSE,           //   REPORT_ID (1)
            (byte) 0x09, (byte) 0x01,                    //   USAGE (Pointer)
            (byte) 0xa1, (byte) 0x00,                    //   COLLECTION (Physical)
            (byte) 0x05, (byte) 0x09,                    //     USAGE_PAGE (Button)
            (byte) 0x19, (byte) 0x01,                    //     USAGE_MINIMUM (Button 1)
            (byte) 0x29, (byte) 0x03,                    //     USAGE_MAXIMUM (Button 3)
            (byte) 0x15, (byte) 0x00,                    //     LOGICAL_MINIMUM (0)
            (byte) 0x25, (byte) 0x01,                    //     LOGICAL_MAXIMUM (1)
            (byte) 0x95, (byte) 0x03,                    //     REPORT_COUNT (3)
            (byte) 0x75, (byte) 0x01,                    //     REPORT_SIZE (1)
            (byte) 0x81, (byte) 0x02,                    //     INPUT (Data,Var,Abs)
            (byte) 0x95, (byte) 0x01,                    //     REPORT_COUNT (1)
            (byte) 0x75, (byte) 0x05,                    //     REPORT_SIZE (5)
            (byte) 0x81, (byte) 0x03,                    //     INPUT (Cnst,Var,Abs)
            (byte) 0x05, (byte) 0x01,                    //     USAGE_PAGE (Generic Desktop)
            (byte) 0x09, (byte) 0x30,                    //     USAGE (X)
            (byte) 0x09, (byte) 0x31,                    //     USAGE (Y)
            (byte) 0x15, (byte) 0x81,                    //     LOGICAL_MINIMUM (-127)
            (byte) 0x25, (byte) 0x7f,                    //     LOGICAL_MAXIMUM (127)
            (byte) 0x75, (byte) 0x08,                    //     REPORT_SIZE (8)
            (byte) 0x95, (byte) 0x02,                    //     REPORT_COUNT (2)
            (byte) 0x81, (byte) 0x06,                    //     INPUT (Data,Var,Rel)
            (byte) 0xc0,                                 //   END_COLLECTION
            (byte) 0xc0                                 // END_COLLECTION
    };

    public OutputBluetoothKeyboard(Language lang, Context ctx)
    {
        setLanguage(lang);
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        context = ctx;
    }

    public void destruct()
    {
        deinitializeBluetoothHidDevice();
    }

    public boolean setLanguage(Language lang)
    {
        String className = "net.tjado.authorizer.UsbHidKbd_" + lang;
        try {
            kbdKeyInterpreter = (UsbHidKbd) Class.forName(className).newInstance();
            Utilities.dbginfo(TAG, "Set language " + lang);
            return true;
        }
        catch (Exception e) {
            Utilities.dbginfo(TAG, "Language " + lang + " not found");
            kbdKeyInterpreter = new UsbHidKbd_en_US();
            return false;
        }
    }

    public boolean checkBluetoothStatus()
    {
        if (btAdapter != null) {
            return btAdapter.isEnabled();
        }

        return false;
    }

    public void initializeBluetoothHidDevice()
    {
        Utilities.dbginfo(TAG,"initializeBluetoothHidDevice");

        BluetoothHidDeviceAppSdpSettings btHidDeviceSqp = new BluetoothHidDeviceAppSdpSettings(
                "Authorizer Bluetooth Keyboard",
                "Authorizer - Android Password Manager",
                "tjado.net",
                BluetoothHidDevice.SUBCLASS1_COMBO,
                hidDescriptor
        );

        BluetoothProfile.ServiceListener serviceListener = new BluetoothProfile.ServiceListener() {
            @Override
            public void onServiceConnected(int profile, BluetoothProfile btHidDeviceProxy) {
                if (profile == BluetoothProfile.HID_DEVICE) {
                    Utilities.dbginfo(TAG, "onServiceConnected: HID_DEVICE");

                    btHidDevice = (BluetoothHidDevice) btHidDeviceProxy;
                    btHidDevice.registerApp(btHidDeviceSqp, null, null, Runnable::run, callback);
                }
            }

            @Override
            public void onServiceDisconnected(int profile) {
                if (profile == BluetoothProfile.HID_DEVICE) {
                    Utilities.dbginfo(TAG, "onServiceDisconnected: HID_DEVICE");
                }
            }
        };

        btAdapter.getProfileProxy(context, serviceListener, BluetoothProfile.HID_DEVICE);
    }

    public void deinitializeBluetoothHidDevice()
    {
        Utilities.dbginfo(TAG,"deinitializeBluetoothHidDevice");

        // disconnect not necessary as unregisterApp will do it for us...
        //if(btDevice != null) {
        //    Utilities.dbginfo(TAG,"disconnect: " + btDevice);
        //    btHidDevice.disconnect(btDevice);
        //}

        if(btHidDevice != null) {
            Utilities.dbginfo(TAG,"unregisterApp");
            btHidDevice.unregisterApp();

            Utilities.dbginfo(TAG,"closeProfileProxy");
            btAdapter.closeProfileProxy(BluetoothProfile.HID_DEVICE, btHidDevice);
        }
    }

    private final BluetoothHidDevice.Callback callback = new BluetoothHidDevice.Callback()
    {
        @Override
        public void onConnectionStateChanged(BluetoothDevice device, final int state) {
            Utilities.dbginfo(TAG, "onConnectionStateChanged - " + device + ":" + state);
            if (device.equals(btDevice)) {
                switch (state) {
                case BluetoothProfile.STATE_CONNECTED:
                    Utilities.dbginfo(TAG, "onConnectionStateChanged: CONNECTED");

                    // delay required otherwise the device is not ready yet to receive the data
                    SystemClock.sleep(300);

                    // connection ready ... send data to device
                    send();

                    // disconnect and free up the HidDevice
                    deinitializeBluetoothHidDevice();

                    break;
                case BluetoothProfile.STATE_CONNECTING:
                    Utilities.dbginfo(TAG, "onConnectionStateChanged: CONNECTING");
                    break;
                case BluetoothProfile.STATE_DISCONNECTING:
                    Utilities.dbginfo(TAG, "onConnectionStateChanged: DISCONNECTING");
                    break;
                case BluetoothProfile.STATE_DISCONNECTED:
                    Utilities.dbginfo(TAG, "onConnectionStateChanged: DISCONNECTED");
                    break;
                }
            }
        }
    };

    public Set<BluetoothDevice> getBondedDevices()
    {
        if (btAdapter != null) {
            return btAdapter.getBondedDevices();
        }

        return new HashSet<>();
    }

    public void connectDeviceAndSend(BluetoothDevice device, String output)
    {
        Utilities.dbginfo(TAG, "connectDeviceAndSend: " + device);

        if (device != null) {
            btDevice = device;
            btOutputString = output;

            int state = btHidDevice.getConnectionState(device);
            if (state != BluetoothProfile.STATE_CONNECTING && state != BluetoothProfile.STATE_CONNECTED){
                btHidDevice.connect(device);
            } else {
                send();
                deinitializeBluetoothHidDevice();
            }
        }
    }

    public void connectDeviceAndSend(BluetoothDevice device, byte[] output)
    {
        Utilities.dbginfo(TAG, "connectDeviceAndSend: " + device);

        if (device != null) {
            btDevice = device;
            btOutputBytes = output;

            if ((output.length % 8) != 0) {
                Utilities.dbginfo(TAG, "connectDeviceAndSend: MOD ERROR: " + output.length);
                return;
            }

            int state = btHidDevice.getConnectionState(device);
            if (state != BluetoothProfile.STATE_CONNECTING && state != BluetoothProfile.STATE_CONNECTED){
                btHidDevice.connect(device);
            } else if (state == BluetoothProfile.STATE_CONNECTING || state == BluetoothProfile.STATE_DISCONNECTING){
                Utilities.dbginfo(TAG, "connectDeviceAndSend: CONNECTING/DISCONNECTING... abort");
            } else {
                send();
                deinitializeBluetoothHidDevice();
            }
        }
    }

    private void send()
    {
        Utilities.dbginfo(TAG, "send");
        try
        {
            if (btOutputBytes != null) {

                int blockSize = 8;
                int blockCount = btOutputBytes.length / blockSize;

                int start = 0;
                for (int i = 0; i < blockCount; i++) {
                    byte[] scancode = Arrays.copyOfRange(btOutputBytes, start, start + blockSize);
                    Utilities.dbginfo(TAG, "send: " + Utilities.bytesToHex(scancode) );

                    sendReport(scancode);
                    clean();
                    start += blockSize;
                }

                btOutputBytes = null;
            } else if (btOutputString != null) {
                sendText(btOutputString);
                btOutputString = null;
            }
        } catch (IOException e) {
            Utilities.dbginfo(TAG, "send error: " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    private void sendReport(byte[] scancode)
    {
        btHidDevice.sendReport(btDevice, HID_RID_KEYBOARD, scancode);
    }

    private void clean() throws IOException
    {
        // overwriting the last keystroke, otherwise it will be repeated until the next writing
        // and it would not be possible to repeat the keystroke
        byte[] scancode_reset = kbdKeyInterpreter.getScancode(null);
        Utilities.dbginfo(TAG, "RST > " + Utilities.bytesToHex(scancode_reset));
        sendReport(scancode_reset);
    }

    public byte[] convertTextToScancode(String text) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );

        for (int i = 0; i < text.length(); i++) {
            String textCharString = String.valueOf(text.charAt(i) );

            try {
                byte[] scancode = kbdKeyInterpreter.getScancode(textCharString);
                Utilities.dbginfo(TAG, "convertTextToScancode: '" + textCharString + "' > " + Utilities
                        .bytesToHex(scancode) );

                outputStream.write( scancode );
            }
            catch (NoSuchElementException e) {
                Utilities.dbginfo(TAG,  "'" + textCharString + "' mapping not found" );
            } catch (IOException e) {
                e.printStackTrace();
                Utilities.dbginfo(TAG,  "convertText:" + e.getLocalizedMessage() );
            }
        }

        return outputStream.toByteArray();
    }

    public int sendText(String output) throws IOException
    {
        byte[] scancode;
        int ret = 0;

        for (int i = 0; i < output.length(); i++) {
            String textCharString = String.valueOf(output.charAt(i) );

            try {
                scancode = kbdKeyInterpreter.getScancode(textCharString);
                Utilities.dbginfo(TAG, "'" + textCharString + "' > " + Utilities
                        .bytesToHex(scancode) );

                sendReport(scancode);
                clean();
            }
            catch (NoSuchElementException e) {
                Utilities.dbginfo(TAG,  "'" + textCharString + "' mapping not found" );
                ret = 1;
            }
        }

        return ret;
    }

    public int sendSingleKey(String keyName) throws IOException
    {
        byte[] scancode;
        int ret = 0;

        try {
            scancode = kbdKeyInterpreter.getScancode(keyName);
            Utilities.dbginfo(TAG, "'" + keyName + "' > " + Utilities
                    .bytesToHex(scancode) );

            sendReport(scancode);
            clean();
        }
        catch (NoSuchElementException e) {
            Utilities.dbginfo(TAG,  "'" + keyName + "' mapping not found" );
            ret = 1;
        }

        return ret;
    }

    public int sendReturn() throws IOException
    {
        return sendSingleKey("return");
    }

    public int sendTabulator() throws IOException
    {
        return sendSingleKey("tabulator");
    }

    public byte[] getSingleKey(String keyName) throws IOException
    {
        byte[] scancode;

        try {
            return kbdKeyInterpreter.getScancode(keyName);
        }
        catch (NoSuchElementException e) {
            Utilities.dbginfo(TAG,  "'" + keyName + "' mapping not found" );
        }

        return new byte[0];
    }

    public byte[] getReturn() throws IOException
    {
        return getSingleKey("return");
    }

    public byte[] getTabulator() throws IOException
    {
        return getSingleKey("tabulator");
    }


    public void sendScancode(byte[] output) throws FileNotFoundException,
                                                   IOException
    {
        if( output.length == 8) {
            Utilities.dbginfo(TAG, Utilities.bytesToHex(output) );
            sendReport(output);

            clean();
        } else if (output.length == 1) {
            byte[] scancode = new byte[] {0x00, 0x00, output[0], 0x00, 0x00, 0x00, 0x00, 0x00};

            Utilities.dbginfo(TAG, Utilities.bytesToHex(scancode) );
            sendReport(scancode);

            clean();
        }

    }

}
