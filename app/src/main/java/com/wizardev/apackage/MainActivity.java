package com.wizardev.apackage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.wizardev.apackage.dialog.DialogFromBottom;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.show_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFromBottom dialogFromBottom = new DialogFromBottom(MainActivity.this);
                dialogFromBottom.setContentView(R.layout.bottom_dialog_layout);
                dialogFromBottom.show();
            }
        });
    }
}
