/*
 * Copyright (©) 2019 Jeff Harris <jefftharris@gmail.com>
 * All rights reserved. Use of the code is allowed under the
 * Artistic License 2.0 terms, as specified in the LICENSE file
 * distributed with this code, or available from
 * http://www.opensource.org/licenses/artistic-license-2.0.php
 */

package com.example.otpaddactivitydynamicmodule;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import uci.tjado.passwdsafe.R;
import uci.tjado.passwdsafe.otp.AddSecretTextWatcher;
import uci.tjado.passwdsafe.otp.AddTextWatcher;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;

//import org.fedorahosted.freeotp.TokenPersistence;


public class AddActivity extends Activity
        implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private final int SHA1_OFFSET = 1;
    private EditText mSecret;
    private EditText mInterval;
    private EditText mCounter;
    private Spinner mAlgorithm;
    private RadioButton mHOTP;

    private Uri mImageURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_add);

        mSecret = (EditText) findViewById(R.id.secret);
        mInterval = (EditText) findViewById(R.id.interval);
        mCounter = (EditText) findViewById(R.id.counter);
        mAlgorithm = (Spinner) findViewById(R.id.algorithm);
        mHOTP = (RadioButton) findViewById(R.id.hotp);

        // Select the default algorithm
        mAlgorithm.setSelection(SHA1_OFFSET);

        // Setup the Counter toggle
        mHOTP.setOnCheckedChangeListener(this);

        // Setup the buttons
        findViewById(R.id.cancel).setOnClickListener(this);
        findViewById(R.id.add).setOnClickListener(this);
        findViewById(R.id.add).setEnabled(false);
        //mImage.setOnClickListener(this);

        // Set constraints on when the Add button is enabled
        TextWatcher tw = new AddTextWatcher(this);
        mSecret.addTextChangedListener(new AddSecretTextWatcher(this));
        mInterval.addTextChangedListener(tw);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        //case R.id.image:
        //    startActivityForResult(new Intent(Intent.ACTION_PICK,
        //                                      android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI), 0);
        //    break;

        case R.id.cancel:
            finish();
            break;

        case R.id.add:
            // Get the fields
            String issuer = "";
            String label = "";
            String secret = Uri.encode(mSecret.getText().toString());
            String algorithm = mAlgorithm.getSelectedItem().toString().toLowerCase(
                    Locale.US);
            int interval = Integer.parseInt(mInterval.getText().toString());
            int digits = ((RadioButton) findViewById(
                    R.id.digits6)).isChecked() ? 6 : 8;

            // Create the URI
            String uri = String.format(Locale.US,
                                       "otpauth://%sotp/%s:%s?secret=%s&algorithm=%s&digits=%d&period=%d",
                                       mHOTP.isChecked() ? "h" : "t", issuer, label,
                                       secret, algorithm, digits, interval);

            // Add optional parameters.
            if (mHOTP.isChecked()) {
                int counter = Integer.parseInt(mCounter.getText().toString());
                uri = uri.concat(String.format("&counter=%d", counter));
            }
            if (mImageURL != null) {
                try {
                    String enc = URLEncoder.encode(mImageURL.toString(), "utf-8");
                    uri = uri.concat(String.format("&image=%s", enc));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            // Add the token
            //if (TokenPersistence.addWithToast(this, uri) != null)
             //   finish();

            Intent resultIntent = new Intent();
            resultIntent.putExtra("uri", uri);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
            break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        findViewById(R.id.counter_row).setVisibility(isChecked ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            mImageURL = data.getData();
            //Picasso.with(this)
            //       .load(mImageURL)
            //       .placeholder(R.drawable.logo)
            //       .into(mImage);
        }
    }
}
