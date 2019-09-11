package com.example;

import android.view.KeyEvent;
import com.facebook.react.ReactActivity;
import com.reactlibrary.ScanGunManager;

public class MainActivity extends ReactActivity {

    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */
    @Override
    protected String getMainComponentName() {

        return "example";
    }

    /*监听键盘事件,除了返回事件都将它拦截,
  使用我们自定义的拦截器处理该事件*/
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() != KeyEvent.KEYCODE_BACK) {
            ScanGunManager.getInstance().analysisKeyEvent(event);
            return true;
        }
        return super.dispatchKeyEvent(event);
    }
}
