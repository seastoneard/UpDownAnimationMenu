package com.zhh.updownanimationmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zhh.updownanimationmenu.menu.Menu0Activity;
import com.zhh.updownanimationmenu.menu.Menu1Activity;
import com.zhh.updownanimationmenu.menu.Menu2Activity;

/**
 * Created by zhh on 2017/3/31.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        findViewById(R.id.btn0).setOnClickListener(this);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
//        findViewById(R.id.btn3).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn0:
                startIntent(Menu0Activity.class);
                break;
            case R.id.btn1:
                startIntent(Menu1Activity.class);
                break;
            case R.id.btn2:
                startIntent(Menu2Activity.class);
                break;
            default:
                break;
        }
    }

    private void startIntent(Class<?> mClass) {
        startActivity(new Intent(this, mClass));
    }
}
