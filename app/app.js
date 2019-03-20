import React from 'react';
import {
    createStackNavigator
} from 'react-navigation';
import * as Screens from './screens';

const Navigator = createStackNavigator({
    Main: {screen: Screens.TeamList},
    Chat: {screen: Screens.Chat},
    TeamDetail:{screen: Screens.TeamDetail},
    NewTeam:{screen:Screens.NewTeam},
},
{
    navigationOptions:
        {
            headerStyle: {
                backgroundColor: '#315668',
            },
        }

});

export class TeamApp extends React.Component {

    render() {
        return <Navigator />;
    }
}

const NewsNavigator = createStackNavigator({
        Main: {screen: Screens.NewsList},
    },
    {
        mode: 'modal',
        headerMode: 'none',
    });

export class NewsApp extends React.Component {
    render() {
        return <NewsNavigator />;
    }
}