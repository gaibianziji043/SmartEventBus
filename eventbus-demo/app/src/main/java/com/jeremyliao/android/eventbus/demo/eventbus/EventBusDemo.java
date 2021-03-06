package com.jeremyliao.android.eventbus.demo.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.jeremyliao.android.eventbus.demo.R;
import com.jeremyliao.android.eventbus.demo.event.MessageEvent;
import com.jeremyliao.android.eventbus.demo.event.StickyMessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus_demo);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        Toast.makeText(this, "receive massage: " + event.msg, Toast.LENGTH_SHORT).show();
    }

    public void sendMsg(View v) {
        EventBus.getDefault().post(new MessageEvent("msg from event bus"));
    }

    public void sendStickyMsg(View v) {
        EventBus.getDefault().postSticky(new StickyMessageEvent("msg from event bus"));
        startActivity(new Intent(this, EventBusStickyDemo.class));
    }
}
