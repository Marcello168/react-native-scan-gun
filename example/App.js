/*
 * @Author: gongyonghui
 * @Date: 2019-09-11 13:22:02
 * @LastEditors: gongyonghui
 * @LastEditTime: 2019-09-11 15:00:08
 * @Description: file content
 */
/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 * @lint-ignore-every XPLATJSCOPYRIGHT1
 */

import React, { Component } from 'react';
import { Platform, StyleSheet, Text, View, DeviceEventEmitter, NativeModules } from 'react-native';
import RNScanGun from 'react-native-scan-gun';

const instructions = Platform.select({
  ios: 'Press Cmd+R to reload,\n' + 'Cmd+D or shake for dev menu',
  android:
    'Double tap R on your keyboard to reload,\n' +
    'Shake or press menu button for dev menu',
});

export default class App extends Component {

  constructor(props) {
    super(props)
    this.state = {
      code: ''
    }
  }

  componentDidMount = () => {
    console.log('NativeModules :', NativeModules);
    DeviceEventEmitter.addListener(RNScanGun.onScanCodeRecevieData, (code) => {
      this.setState({ code: code })
    })
  };

  render() {

    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>请扫二维码</Text>
        <Text style={styles.instructions}>{this.state.code}</Text>
        <Text style={styles.instructions}>{instructions}</Text>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});
