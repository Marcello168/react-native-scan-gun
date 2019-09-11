<!--
 * @Author: gongyonghui
 * @Date: 2019-09-11 13:12:42
 * @LastEditors: gongyonghui
 * @LastEditTime: 2019-09-11 15:13:35
 * @Description: file content
 -->

# React native 扫码枪  react-native-scan-gun

## 开始使用

`$ npm install react-native-scan-gun --save`

### 自动链接原生库

`$ react-native link react-native-scan-gun`

###  链接完原生库后 需要在`MainActivity`里面 增加 如下代码

1. 导入 `import com.reactlibrary.ScanGunManager;`
2. 重写`MainActivity`的`dispatchKeyEvent`方法 代码如下

   ```java
       /*监听键盘事件,除了返回事件都将它拦截,使用我们自定义的拦截器处理该事件*/
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() != KeyEvent.KEYCODE_BACK) {
            ScanGunManager.getInstance().analysisKeyEvent(event);
            return true;
        }
        return super.dispatchKeyEvent(event);
    }
   ```
   

### 手动安装




#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNScanGunPackage;` to the imports at the top of the file
  - Add `new RNScanGunPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-scan-gun'
  	project(':react-native-scan-gun').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-scan-gun/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-scan-gun')
  	```




## 使用方法
```javascript
import RNScanGun from 'react-native-scan-gun';

// TODO: What to do with the module?
RNScanGun;

// 监听扫码回调
    DeviceEventEmitter.addListener(RNScanGun.onScanCodeRecevieData, (code) => {
      this.setState({ code: code })
	})
	
	//手动清除上一次的二维码
	RNScanGun.clearScanBarCodeText()
```
  