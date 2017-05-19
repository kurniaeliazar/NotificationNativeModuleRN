import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  Alert,
  Button
} from 'react-native';

import NotificationModule from './components/native/NotificationModule';
import SecondEventModule from './components/native/SecondEventModule';
import TestModule from './components/native/TestingModule';
import { DeviceEventEmitter } from 'react-native';

class RNProject extends Component<any, any> {
  componentWillMount() {
     DeviceEventEmitter.addListener('TestingModule', (data) => { console.log(data.content));
  }

  componentDidMount(){
    // this.getArray();
    // this.getObj();
    // this.getString();
    // this.getSendArray();
    // this.getSendObject();
    // this.fetchData();
  }

  render() {
    return (
      <View style={styles.containerStyle}>

        <Text style={styles.title}>
          Say Hello to React Native!
        </Text>

        <View style={styles.boxStyle}>

          <Button
            onPress={this.sendingArguments}
            title="Sending Arguments To JS 2"
            color="#841584"
          />

          <Button
            onPress={this.sendingPromises}
            title="Sending Promises"
            color="#841584"
          />

          <Button
            onPress={this.sendingEvents}
            title="Sending emit events"
            color="#841584"
          />

          {/*<Button
            onPress={this.sendingEvents}
            title="Sending emit events"
            color="#841584"
          />*/}

          {/*<Button
            onPress={this._onPressButton}
            title="Local notification example"
            color="#841584"
          />

          <Button
            onPress={this._onScheduleNotifButton}
            title="Schedule a notification : 18:00"
            color="#841584"
          />

          <Button
            onPress={this._onCancelSchedulButton}
            title="Cancel a notification"
            color="#841584"
          />*/}

        </View>
      </View>
    );
  }

 sendingArguments(){
    let stringArg : String = "Hello world";
    let booleanArg : Boolean = true;
    let doubleArg : Number = 8.7654321;

    let data = {
        id : "data1",
        content : "content"
    };

    let dataArray = ["Test1", "Test2", "Test3"];
    TestModule.showArgumentsExample(stringArg, booleanArg, doubleArg, data, dataArray);
 }

 sendingPromises(){
    TestModule.promiseExample(2)
              .then((number) => console.log(number))
              .catch((key,message) => console.log("error"));
 }

 sendingCallback(){

 }

 sendingEvents(){
    TestModule.sendEvents();
 }


  _onPressButton() {
    //NotificationModule.showNotification('Test', 'Haloha');
    SecondEventModule.showDialog();
  }

  _onScheduleNotifButton(){
    NotificationModule.scheduleNotification('ScheduleTest', 'ScheduleText');
  }

  _onCancelSchedulButton(){
    NotificationModule.cancelNotification('ScheduleTest', 'ScheduleText');
  }

  _showDialog(){
      Alert.alert('Log Out', 'Are you sure you want to Log Out', [
          { text: 'No', onPress: null, style: 'cancel' },
          { text: 'Yes', onPress: null }
      ], { cancelable: false });
  }

  async getString() {
    try {
      var value = await SecondEventModule.getStringSample();
      console.log(value);
    } catch (e) {
      console.error(e);
    }
  }

  async getObj() {
      try {
        var {ObjectKey, ObjectKey2} = await SecondEventModule.getObjectSample();
        console.log(ObjectKey);
        console.log(ObjectKey2);
      } catch (e) {
        console.error(e);
      }
  }

  async getArray() {
      try {
        var value = await SecondEventModule.getArraySample();
        console.log(value);
      } catch (e) {
        console.error(e);
      }
  }

  async getSendArray() {
      try {
        var value = await SecondEventModule.sendArrayArguments(["test1","test2"]);
        console.log(value);
      } catch (e) {
        console.error(e);
      }
  }

  async getSendObject() {

      var input = { ObjectKey3 : "testArgument"};
      try {
        var value = await SecondEventModule.sendObjectArguments(input);
        console.log(value);
      } catch (e) {
        console.error(e);
      }
  }

  async fetchData() {
    const response = await fetch('http://calapi.inadiutorium.cz/api/v0/en/calendars/default/today');
    const json = await response.json()
    console.log(json);
  }

}


export default RNProject;

const styles = StyleSheet.create({
  containerStyle: {
    borderWidth: 1,
    borderRadius: 2,
    borderColor: '#ddd',
    borderBottomWidth: 0,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.1,
    shadowRadius: 2,
    elevation: 1,
    marginLeft: 5,
    marginRight: 5,
    marginTop: 10,
    flex: 1,
    justifyContent: 'center',
  },
  boxStyle: {
    flexDirection: 'column', 
    justifyContent: 'space-between',
    marginLeft: 15,
    marginRight: 15
  },
  title: {
    fontSize: 20,
    margin: 10,
    alignSelf: 'center',
  },
  buttonStyle: {
    backgroundColor: '#fff',
    borderRadius: 5,
    borderWidth: 1,
    borderColor: '#007aff',
  }
});
