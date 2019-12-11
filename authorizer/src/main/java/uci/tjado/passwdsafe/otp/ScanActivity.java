/*
 * Copyright (©) 2019 Jeff Harris <jefftharris@gmail.com>
 * All rights reserved. Use of the code is allowed under the
 * Artistic License 2.0 terms, as specified in the LICENSE file
 * distributed with this code, or available from
 * http://www.opensource.org/licenses/artistic-license-2.0.php
 */

package uci.tjado.passwdsafe.otp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;

import uci.tjado.passwdsafe.R;

import io.fotoapparat.Fotoapparat;
import io.fotoapparat.view.CameraView;

public class ScanActivity extends Activity
{
    private Fotoapparat fotoapparat;
    private static ScanBroadcastReceiver receiver;

    public class ScanBroadcastReceiver extends BroadcastReceiver
    {
        public static final String ACTION = "org.fedorahosted.freeotp.ACTION_CODE_SCANNED";

        @Override
        public void onReceive(Context context, Intent intent) {
            String text = intent.getStringExtra("scanResult");
            addTokenAndFinish(text);
        }
    }

    public static boolean hasCamera(Context context) {
        PackageManager pm = context.getPackageManager();
        return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    private void addTokenAndFinish(String uri) {
        Token token = null;
        try {
            token = new Token(uri);
        } catch (Token.TokenUriInvalidException e) {
            e.printStackTrace();
        }

        //do not receive any more broadcasts
        this.unregisterReceiver(receiver);

        Intent resultIntent = new Intent();
        resultIntent.putExtra("uri", uri);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
/*
        //check if token already exists
        if (new TokenPersistence(ScanActivity.this).tokenExists(token)) {
            finish();
            return;
        }

        TokenPersistence.saveAsync(ScanActivity.this, token);
        if (token == null || token.getImage() == null) {
            finish();
            return;
        }

        final ImageView image = (ImageView) findViewById(R.id.image);
        Picasso.with(ScanActivity.this)
                .load(token.getImage())
                .placeholder(R.drawable.scan)
                .into(image, new Callback() {
                    @Override
                    public void onSuccess() {
                        findViewById(R.id.progress).setVisibility(View.INVISIBLE);
                        image.setAlpha(0.9f);
                        image.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, 2000);
                    }

                    @Override
                    public void onError() {
                        finish();
                    }
                });*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            this.unregisterReceiver(receiver);
        }
        catch (IllegalArgumentException e) {
            // catch exception, when trying to unregister receiver again
            // there seems to be no way to check, if receiver if registered
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        receiver = new ScanBroadcastReceiver();
        this.registerReceiver(receiver, new IntentFilter(ScanBroadcastReceiver.ACTION));
        setContentView(R.layout.activity_otp_scan);
        CameraView cameraView = findViewById(R.id.camera_view);

//        fotoapparat = Fotoapparat
//                .with(this)
//                .into(cameraView)
//                .previewScaleType(ScaleType.CENTER_CROP)
//                .photoSize(SizeSelectors.biggestSize())
//                .lensPosition(LensPositionSelectors.back())
//                .focusMode(Selectors.firstAvailable(
//                        FocusModeSelectors.continuousFocus(),
//                        autoFocus(),
//                        fixed()
//                ))
//                .frameProcessor(new ScanFrameProcessor(this))
//                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        fotoapparat.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        fotoapparat.stop();
    }
}
