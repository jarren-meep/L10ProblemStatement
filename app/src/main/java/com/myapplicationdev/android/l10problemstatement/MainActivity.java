package com.myapplicationdev.android.l10problemstatement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<Fragment> al;
    MyFragmentPagerAdapter adapter;
    ViewPager vPager;
    Button btn;
    int reqCode = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vPager = findViewById(R.id.viewpager1);
btn = findViewById(R.id.button);
        FragmentManager fm = getSupportFragmentManager();

        al = new ArrayList<Fragment>();
        al.add(new FragmentJuan());
        al.add(new FragmentToo());
        al.add(new FragmentTree());

        adapter = new MyFragmentPagerAdapter(fm, al);

        vPager.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, 5);

                Intent intent = new Intent(MainActivity.this,
                        ScheduledNotificationReceiver.class);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        MainActivity.this, reqCode,
                        intent, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager)
                        getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                        pendingIntent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onPause() {
        SharedPreferences prefs = getSharedPreferences("page",MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("page",vPager.getCurrentItem());
        editor.commit();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences("page",MODE_PRIVATE);
        int x = prefs.getInt("page",0);
        vPager.setCurrentItem(x, true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.back:
                int previousPage = vPager.getCurrentItem() - 1;
                vPager.setCurrentItem(previousPage, true);
                return true;
            case R.id.next:
                int max = vPager.getChildCount();
                if (vPager.getCurrentItem() < max-1){
                    int nextPage = vPager.getCurrentItem() + 1;
                    vPager.setCurrentItem(nextPage, true);
                }
                return true;
            case R.id.rand:
                Random random = new Random();
                vPager.setCurrentItem(random.nextInt(2), true);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}