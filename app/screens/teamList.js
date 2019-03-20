import React from 'react';

import { Avatar } from 'react-native-elements';
import Icon from 'react-native-vector-icons/AntDesign';
import {
    Text,
    FlatList,
    View,
    StyleSheet,
    TouchableOpacity,
    Button,
} from 'react-native';
import _ from 'lodash';
import {
    RkStyleSheet,
    RkText,
    RkTextInput,
} from 'react-native-ui-kitten';
import { data } from '../data';
import ToastExample from './ToastExample'

export class TeamList extends React.Component {

    state = {
        data: {
            original: data.getChatList(),
            filtered: data.getChatList(),
        },
        user:''
    };

    constructor(props) {
        super(props);


    }

    extractItemKey = (item) => `${item.withUser.id}`;

    onItemPressed = (item) => {
        this.props.navigation.push('TeamDetail',{
            user:'teammate',
        })
        //Navigation.goToTeamDetail('teammate')

    };

    renderSeparator = () => (
        <View style={styles.separator} />
    );



    renderAvatar=(item)=>
    {
        const firstName=item.withUser.firstName;
        const firstWord=firstName.substr(0,1);
        return (
            <Avatar rounded
                    size="medium"
                    title={firstWord}
            />
        )
    }
    renderItem = ({ item }) => {
        const last = item.messages[item.messages.length - 1];
        return (
            <TouchableOpacity onPress={() => this.onItemPressed(item)}>
              <View style={styles.container}>
                  { this.renderAvatar(item) }

                <View style={styles.content}>
                  <View style={styles.contentHeader}>
                    <RkText rkType='large'>{`${item.withUser.firstName} ${item.withUser.lastName}`}</RkText>

                  </View>
                  <RkText numberOfLines={1} rkType='primary3 mediumLine' style={{ paddingTop: 2 }}>
                      {last.text}
                  </RkText>
                </View>
              </View>
            </TouchableOpacity>
        );
    };

    render = () => (
        <View style={styles.listContainer}>

        <FlatList
            style={styles.root}
            data={this.state.data.filtered}
            extraData={this.state}

            ItemSeparatorComponent={this.renderSeparator}
            keyExtractor={this.extractItemKey}
            renderItem={this.renderItem}
        />
            <TouchableOpacity onPress={() =>this.goToCreateTeam() } style={styles.fab}>
                <Text style={styles.fabIcon}>+</Text>
            </TouchableOpacity>

        </View>

    );
    goToCreateTeam()
    {
        this.props.navigation.push('NewTeam')
    }
}

const styles = RkStyleSheet.create(theme => ({
    root: {
        backgroundColor: theme.colors.screen.base,
    },
    Left:
        {
            padding: 19,
        },
    searchContainer: {
        backgroundColor: theme.colors.screen.bold,
        paddingHorizontal: 16,
        paddingVertical: 10,
        height: 60,
        alignItems: 'center',
    },
    listContainer: {
        flex: 1,
    },
    container: {
        paddingLeft: 19,
        paddingRight: 16,
        paddingBottom: 12,
        paddingTop: 7,
        flexDirection: 'row',
    },
    content: {
        marginLeft: 16,
        flex: 1,
    },
    contentHeader: {
        flexDirection: 'row',
        justifyContent: 'space-between',
        marginBottom: 6,
        paddingTop: 5,
    },
    separator: {
        height: StyleSheet.hairlineWidth,
        backgroundColor: theme.colors.border.base,
    },
    fab: {
        position: 'absolute',
        width: 56,
        height: 56,
        alignItems: 'center',
        justifyContent: 'center',
        right: 20,
        bottom: 20,
        backgroundColor: '#f57e71',
        borderRadius: 30,
        elevation: 8
    },
    fabIcon: {
        fontSize: 40,
        color: 'white'
    }

}))
