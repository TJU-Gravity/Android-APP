import React from 'react';
import {AppRegistry} from 'react-native';

import * as Screens from'./app/screens'
import {TeamApp,NewsApp} from './app/app'
import {TeamDetailApp} from './app/teamDetail'
import { data } from './app/data';

data.populateData();
AppRegistry.registerComponent('Test', () => Screens.ChatList);
AppRegistry.registerComponent('TeamList',()=>TeamApp);
AppRegistry.registerComponent('NewsList',()=>NewsApp);
AppRegistry.registerComponent('TeamDetail',()=>TeamDetailApp);