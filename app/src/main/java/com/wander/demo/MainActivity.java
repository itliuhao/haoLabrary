package com.wander.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.wander.baselibrary.ioc.OnClickCheckUtils;
import com.wander.baselibrary.ioc.OnClick;
import com.wander.baselibrary.ioc.OnClickCheck;
import com.wander.baselibrary.ioc.ViewBind;
import com.wander.baselibrary.ioc.ViewUtils;

public class MainActivity extends AppCompatActivity {
    @ViewBind(R.id.tv_text)
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewUtils.inject(this);

        textView.setText("IOC TEXTVIEW");
    }

    @OnClick(R.id.tv_text)
//    @OnClickCheck(type = OnClickCheckUtils.CHECK_ALL)
    private void onClick() {
        Toast.makeText(this, "sdasdad", Toast.LENGTH_SHORT).show();
    }

}
