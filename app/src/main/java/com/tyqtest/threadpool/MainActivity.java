package com.tyqtest.threadpool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button_CacheThreadPool;
    private Button button_FixedThreadPool;
    private Button button_ScheduleThreadPool;
    private Button button_SingleThreadPool;
    private static  final String TAG = "Executor";
    private static  final  int count = 3;
    private static ExecutorService mCacheThreadPool = null;
    private  static ExecutorService mFixedThreadPool = null;
    private static ScheduledExecutorService mScheduledExecutorService = null;
    private static ExecutorService mSingleThreadExecutor = null;




    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.CacheThreadPool_bnt:
                ExecutorServiceThread(mCacheThreadPool);
                break;
            case R.id.FixedThreadPool:
                ExecutorServiceThread(mFixedThreadPool);

                break;
            case R.id.ScheduleThreadPool:
                ExecutorScheduleThread(mScheduledExecutorService);
                break;
            case  R.id.SingleThreadExecutor:
                ExecutorServiceThread(mSingleThreadExecutor);
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findById();
        initExecutorPool();
        button_CacheThreadPool.setOnClickListener(this);
        button_ScheduleThreadPool.setOnClickListener(this);
        button_FixedThreadPool.setOnClickListener(this);
        button_SingleThreadPool.setOnClickListener(this);


    }
    private  void initExecutorPool(){
        mCacheThreadPool = Executors.newCachedThreadPool();
        mFixedThreadPool  = Executors.newFixedThreadPool(count);
        mScheduledExecutorService =  Executors.newScheduledThreadPool(count);
        mSingleThreadExecutor = Executors.newSingleThreadExecutor();///
    }///
    private  void findById(){
        button_CacheThreadPool = (Button) findViewById(R.id.CacheThreadPool_bnt);
        button_FixedThreadPool = (Button) findViewById(R.id.FixedThreadPool);
        button_ScheduleThreadPool = (Button) findViewById(R.id.ScheduleThreadPool);
        button_SingleThreadPool = (Button) findViewById(R.id.SingleThreadExecutor);
    }
    private  void ExecutorServiceThread(ExecutorService executorService){
        for (int i= 0; i <9; i++){
            final int index = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try{
                        Thread.sleep(2*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.i(TAG,"Thread:" +Thread.currentThread().getId()+"activeCount:" +Thread.activeCount() +" Index :" +index);

                }
            });
        }
    }
    private void ExecutorScheduleThread(ScheduledExecutorService scheduledExecutorService){
    for (int i = 0; i < 9; i ++) {
        final int index  =i;
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i(TAG,"Thread : "+ Thread.currentThread().getId()+" activeCount"+ Thread.activeCount() + "Index:" +index);

            }
        },2,TimeUnit.SECONDS);

    }
    }

}
