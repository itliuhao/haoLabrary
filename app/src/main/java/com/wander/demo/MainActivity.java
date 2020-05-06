package com.wander.demo;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wander.baselibrary.ioc.OnClick;
import com.wander.baselibrary.ioc.OnClickCheck;
import com.wander.baselibrary.ioc.OnClickCheckUtils;
import com.wander.baselibrary.ioc.ViewBind;
import com.wander.baselibrary.ioc.ViewUtils;

public class MainActivity extends AppCompatActivity {
    @ViewBind({R.id.tv_ioc})
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewUtils.inject(this);

    }

    @OnClick(R.id.tv_ioc)
    @OnClickCheck(type = OnClickCheckUtils.CHECK_CLICKFAST,time = 1000)
    private void onClick() {
        Toast.makeText(this, "sdasdad", Toast.LENGTH_SHORT).show();
    }

}
