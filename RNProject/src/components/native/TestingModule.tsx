import { NativeEventEmitter, NativeModules } from 'react-native';
// const { TestingModule } = NativeModules;

// const testingModuleEmitter = new NativeEventEmitter(TestingModule);

// const subscription = testingModuleEmitter.addListener(
//   'TestingModule', (data) => { console.log(data.content); }, null);

// export default TestingModule;

export default NativeModules.TestingModule;