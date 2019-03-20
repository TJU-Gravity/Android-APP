import React from 'react';



import {
    createStackNavigator
} from 'react-navigation';
import * as Screens from './screens/index';

const Navigator = createStackNavigator({
        TeamDetail:{screen: Screens.TeamDetail},
    },
    {navigationOptions:
        {
            headerStyle: {
                backgroundColor: '#315668',
            },
        }

    });

export class TeamDetailApp extends React.Component {
    render() {
        return <Navigator />;
    }
}


