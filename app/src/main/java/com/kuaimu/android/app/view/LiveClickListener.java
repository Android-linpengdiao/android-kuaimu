package com.kuaimu.android.app.view;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

public class LiveClickListener implements View.OnTouchListener {
    private static final String TAG = "MyClickListener";
    private static int timeout = 300;//双击间延时
    private int clickCount = 0;//记录连续点击次数
    private Handler handler;
    private ClickCallBack clickCallBack;
    private long lastTime = 0;

    public interface ClickCallBack {

        void oneClick();//点击一次的回调

        void doubleClick(int w, int y);//连续点击两次的回调

    }


    public LiveClickListener(ClickCallBack clickCallBack) {
        this.clickCallBack = clickCallBack;
        handler = new Handler();
    }


    @Override
    public boolean onTouch(View v, final MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            clickCount++;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (clickCount == 1) {
                        clickCallBack.oneClick();
                        clickCount = 0;//计数清零
                        lastTime = 0;
                    } else if (clickCount >= 2) {
                        clickCallBack.doubleClick((int) event.getRawX(), (int) event.getRawY());
                        if (System.currentTimeMillis() - lastTime <timeout){
                            lastTime = System.currentTimeMillis();
                        }else {
                            clickCount = 0;//计数清零
                        }
                    }
                    handler.removeCallbacksAndMessages(null);//清空handler延时，并防内存泄漏
                }
            }, timeout);//延时timeout后执行run方法中的代码
        }
        return false;//让点击事件继续传播，方便再给View添加其他事件监听
    }
}
