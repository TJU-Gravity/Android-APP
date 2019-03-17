import React from 'react';
import {AppRegistry} from 'react-native';
import * as Layouts from './layouts';
import { data } from './data';
data.populateData();
AppRegistry.registerComponent('Test', () => Layouts.ChatList);
