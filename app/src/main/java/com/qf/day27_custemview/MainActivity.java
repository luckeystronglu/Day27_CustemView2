package com.qf.day27_custemview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.qf.widget.NumberImageView;

public class MainActivity extends AppCompatActivity {

    private NumberImageView imageView;
    private int number = 90;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (NumberImageView) findViewById(R.id.niv);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setNumber(++number);
            }
        });
    }
}
