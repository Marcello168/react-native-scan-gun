package com.reactlibrary;

import android.util.Log;
import android.view.KeyEvent;
import java.util.Date;
import static android.content.ContentValues.TAG;

public class ScanGunManager {

    private final static long MESSAGE_DELAY = 500;             //延迟500ms，判断扫码是否完成。
    private StringBuffer mStringBufferResult;                  //扫码内容
    private boolean mCaps;                                     //大小写区分
    private Date lastDate;//上一次的扫码
    private Date nextDate;//上一次的扫码

    private ScanCodeCallback scanCodeCallback;

    public interface OnScanCodeListener {
        void onScanCode(String value);
    }


    //类初始化时，不初始化这个对象(延时加载，真正用的时候再创建)
    private static ScanGunManager instance;

    //构造器私有化
    private ScanGunManager(){}

    //方法同步，调用效率低
    public static synchronized ScanGunManager getInstance(){
        if(instance==null){
            instance=new ScanGunManager();
            instance.mStringBufferResult = new StringBuffer();
        }
        return instance;
    }


    public void setReceiveCallback(ScanCodeCallback scanCodeCallback) {
        this.scanCodeCallback = scanCodeCallback;

    }

    public void clearScanBarCodeText(){
        if (mStringBufferResult!= null){
            mStringBufferResult.delete(0,mStringBufferResult.length());
        }
    }

    /**
     * 扫码枪事件解析
     * @param event
     */
    public void analysisKeyEvent(KeyEvent event) {

        int keyCode = event.getKeyCode();
        String devicesName = event.getDevice().getName();
        Log.d(TAG, "设备名称:" + devicesName);
        //字母大小写判断
        checkLetterStatus(event);
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            lastDate = new Date();
        }else if(event.getAction() == KeyEvent.ACTION_UP) {
            nextDate = new Date();
            Log.d(TAG, "lastDate" + lastDate);
            Log.d(TAG, "nextDate" + nextDate);
            long count = nextDate.getTime() - lastDate.getTime();
            Log.d(TAG, "时间差:" + count);

        }


        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            char aChar = getInputCode(event);;
            if (aChar != 0) {
                if (mStringBufferResult!=null){
                    mStringBufferResult.append(aChar);
                }
            }

            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                //若为回车键，直接返回
                Log.d(TAG, "扫码🔫结果:" + mStringBufferResult.toString());
                String barCode = mStringBufferResult.toString();
                if (scanCodeCallback != null) {
                    scanCodeCallback.onScanCode(barCode);
                }
                mStringBufferResult.delete(0,mStringBufferResult.length());
            } else {
                //延迟post，若500ms内，有其他事件

            }

        }
    }

    //检查shift键
    private void checkLetterStatus(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (keyCode == KeyEvent.KEYCODE_SHIFT_RIGHT || keyCode == KeyEvent.KEYCODE_SHIFT_LEFT) {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                //按着shift键，表示大写
                mCaps = true;
            } else {
                //松开shift键，表示小写
                mCaps = false;
            }
        }
    }


    //获取扫描内容
    private char getInputCode(KeyEvent event) {

        int keyCode = event.getKeyCode();

        char aChar;

        if (keyCode >= KeyEvent.KEYCODE_A && keyCode <= KeyEvent.KEYCODE_Z) {
            //字母
            aChar = (char) ((mCaps ? 'A' : 'a') + keyCode - KeyEvent.KEYCODE_A);
        } else if (keyCode >= KeyEvent.KEYCODE_0 && keyCode <= KeyEvent.KEYCODE_9) {
            //数字
            aChar = (char) ('0' + keyCode - KeyEvent.KEYCODE_0);
        } else {
            //其他符号
            //
            aChar = keyValue(mCaps, keyCode);

        }

        return aChar;

    }

    /**
     * 按键对应的char表
     */
    private char keyValue(boolean caps, int keyCode) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_0:
                return caps ? ')' : '0';
            case KeyEvent.KEYCODE_1:
                return caps ? '!' : '1';
            case KeyEvent.KEYCODE_2:
                return caps ? '@' : '2';
            case KeyEvent.KEYCODE_3:
                return caps ? '#' : '3';
            case KeyEvent.KEYCODE_4:
                return caps ? '$' : '4';
            case KeyEvent.KEYCODE_5:
                return caps ? '%' : '5';
            case KeyEvent.KEYCODE_6:
                return caps ? '^' : '6';
            case KeyEvent.KEYCODE_7:
                return caps ? '&' : '7';
            case KeyEvent.KEYCODE_8:
                return caps ? '*' : '8';
            case KeyEvent.KEYCODE_9:
                return caps ? '(' : '9';
            case KeyEvent.KEYCODE_NUMPAD_SUBTRACT:
                return '-';
            case KeyEvent.KEYCODE_MINUS:
                return '_';
            case KeyEvent.KEYCODE_EQUALS:
                return '=';
            case KeyEvent.KEYCODE_NUMPAD_ADD:
                return '+';
            case KeyEvent.KEYCODE_GRAVE:
                return caps ? '~' : '`';
            case KeyEvent.KEYCODE_BACKSLASH:
                return caps ? '|' : '\\';
            case KeyEvent.KEYCODE_LEFT_BRACKET:
                return caps ? '{' : '[';
            case KeyEvent.KEYCODE_RIGHT_BRACKET:
                return caps ? '}' : ']';
            case KeyEvent.KEYCODE_SEMICOLON:
                return caps ? ':' : ';';
            case KeyEvent.KEYCODE_APOSTROPHE:
                return caps ? '"' : '\'';
            case KeyEvent.KEYCODE_COMMA:
                return caps ? '<' : ',';
            case KeyEvent.KEYCODE_PERIOD:
                return caps ? '>' : '.';
            case KeyEvent.KEYCODE_SLASH:
                return caps ? '?' : '/';
            default:
                return 0;
        }
    }

}
