//
//  TestingModule.m
//  RNProject
//
//  Created by Kurnia Eliazar on 5/16/17.
//  Copyright © 2017 Facebook. All rights reserved.
//

#import "TestingModule.h"
#import <React/RCTLog.h>

@implementation TestingModule

RCT_EXPORT_MODULE(TestingModule);

RCT_EXPORT_METHOD(addEvent:(NSString *)name andDetails:(NSDictionary *)details)
{
  RCTLogInfo(@"Pretending to create an event %@ at %@", name, details);
  //NSLog(@"Pretending to create an event %@ at %@", name, details);
}

RCT_REMAP_METHOD(promiseExample,
                 resolver:(RCTPromiseResolveBlock)resolve
                 rejecter:(RCTPromiseRejectBlock)reject)
{
  NSArray *resolved;
  resolve(resolved);
  
//  NSError *error;
//  reject(@"no_events", @"There were no events", error);
  
}

@end
