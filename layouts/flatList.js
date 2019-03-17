import React from 'react';
import { Avatar } from 'react-native-elements';

import {
    FlatList,
    View,
    StyleSheet,
    TouchableOpacity,
} from 'react-native';
import _ from 'lodash';
import {
    RkStyleSheet,
    RkText,
    RkTextInput,
} from 'react-native-ui-kitten';

import { data } from '../data';

const moment = require('moment');

export class ChatList extends React.Component {


    state = {
        data: {
            original: data.getChatList(),
            filtered: data.getChatList(),
        },
    };

    constructor(props) {
        super(props);   //这一句不能省略，照抄即可

    }

    extractItemKey = (item) => `${item.withUser.id}`;

    onInputChanged = (event) => {
        const pattern = new RegExp(event.nativeEvent.text, 'i');
        const chats = _.filter(this.state.data.original, chat => {
            const filterResult = {
                firstName: chat.withUser.firstName.search(pattern),
                lastName: chat.withUser.lastName.search(pattern),
            };
            return filterResult.firstName !== -1 || filterResult.lastName !== -1 ? chat : undefined;
        });
        this.setState({
            data: {
                original: this.state.data.original,
                filtered: chats,
            },
        });
    };

    onItemPressed = (item) => {
        //const navigationParams = { userId: item.withUser.id };
        //this.props.navigation.navigate('Chat', navigationParams);
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
        <FlatList
            style={styles.root}
            data={this.state.data.filtered}
            extraData={this.state}

            ItemSeparatorComponent={this.renderSeparator}
            keyExtractor={this.extractItemKey}
            renderItem={this.renderItem}
        />
    );
}

const styles = RkStyleSheet.create(theme => ({
    root: {
        backgroundColor: theme.colors.screen.base,
    },
    searchContainer: {
        backgroundColor: theme.colors.screen.bold,
        paddingHorizontal: 16,
        paddingVertical: 10,
        height: 60,
        alignItems: 'center',
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

}));
