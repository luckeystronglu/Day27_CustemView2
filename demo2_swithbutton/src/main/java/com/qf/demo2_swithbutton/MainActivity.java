package com.qf.demo2_swithbutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.qf.widget.SwitchButton;

public class MainActivity extends AppCompatActivity implements SwitchButton.OnSwitchListener {

    private SwitchButton switchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchButton = (SwitchButton) findViewById(R.id.swithbutton);
        switchButton.setOnSwitchListener(this);
    }

    @Override
    public void onSwitch(SwitchButton switchButton, boolean ischecked) {
        Log.d("print", "当前按钮的状态：" + ischecked);
    }
}
