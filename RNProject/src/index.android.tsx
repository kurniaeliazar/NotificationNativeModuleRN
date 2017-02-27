import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View
} from 'react-native';
import NotificationModule from './components/native/NotificationModule';
import AwesomeButton from 'react-native-awesome-button';

class RNProject extends Component<any, any> {
  render() {
    return (
      <View style={styles.containerStyle}>
        <Text style={styles.title}>
          Say Hello to React Native!
        </Text>
        <View style={styles.boxStyle}>
          <AwesomeButton
            states={{
              default: {
                backgroundStyle: {
                  backgroundColor: '#d35400',
                  minHeight: 40,
                  alignItems: 'center',
                  justifyContent: 'center',
                  borderRadius: 30
                },
                onPress: this._onPressButton,
                text: "Local notification example",
              }
            }}
          />

          <AwesomeButton
            states={{
              default: {
                backgroundStyle: {
                  backgroundColor: '#8e44ad',
                  minHeight: 40,
                  alignItems: 'center',
                  justifyContent: 'center',
                  borderRadius: 30,
                  marginTop: 10
                },
                onPress: this._onScheduleNotifButton,
                text: "Schedule a notification : 18:00",
              }
            }}
          />

           <AwesomeButton
            states={{
              default: {
                backgroundStyle: {
                  backgroundColor: '#16a085',
                  minHeight: 40,
                  alignItems: 'center',
                  justifyContent: 'center',
                  borderRadius: 30,
                  marginTop: 10
                },
                onPress: this._onCancelSchedulButton,
                text: "Cancel a notification",
              }
            }}
          />

        </View>
      </View>
    );
  }

  _onPressButton() {
    NotificationModule.showNotification('Test', 'Haloha');
  }

  _onScheduleNotifButton(){
    NotificationModule.scheduleNotification('ScheduleTest', 'ScheduleText');
  }

  _onCancelSchedulButton(){
    NotificationModule.cancelNotification('ScheduleTest', 'ScheduleText');
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
