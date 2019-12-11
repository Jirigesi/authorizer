/*
 * Copyright (©) 2019 Jeff Harris <jefftharris@gmail.com>
 * All rights reserved. Use of the code is allowed under the
 * Artistic License 2.0 terms, as specified in the LICENSE file
 * distributed with this code, or available from
 * http://www.opensource.org/licenses/artistic-license-2.0.php
 */
package net2.tjado.passwdsafe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.play.core.splitinstall.SplitInstallManagerFactory;
import com.google.android.play.core.splitinstall.SplitInstallRequest;

public class DownloadDynamicModule extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        PasswdSafeApp.setupTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_dynamic_module);

        Button buttonX = (Button)findViewById(R.id.download_module_button);
        buttonX.setOnClickListener((view) -> {
            SplitInstallRequest request =
                    SplitInstallRequest.newBuilder().addModule("").build();
            SplitInstallManagerFactory
                    .create(this)
                    .startInstall(request)
                    .addOnSuccessListener((result) -> {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setMessage("Download Successful")
                               .setTitle("Thanks");
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    })
                    .addOnFailureListener(result -> {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setMessage("Download failed! " + result.toString())
                               .setTitle("Sorry");
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    });
        });

    }
}