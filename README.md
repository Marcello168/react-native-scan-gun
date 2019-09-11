
# react-native-scan-gun

## Getting started

`$ npm install react-native-scan-gun --save`

### Mostly automatic installation

`$ react-native link react-native-scan-gun`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-scan-gun` and add `RNScanGun.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNScanGun.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

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

#### Windows
[Read it! :D](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `RNScanGun.sln` in `node_modules/react-native-scan-gun/windows/RNScanGun.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using Scan.Gun.RNScanGun;` to the usings at the top of the file
  - Add `new RNScanGunPackage()` to the `List<IReactPackage>` returned by the `Packages` method


## Usage
```javascript
import RNScanGun from 'react-native-scan-gun';

// TODO: What to do with the module?
RNScanGun;
```
  