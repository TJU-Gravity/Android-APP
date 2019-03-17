import React, {Component} from 'react';
import {
  StyleSheet,
  View,
  FlatList
} from 'react-native';
import {
  RkStyleSheet,
  RkText,
  RkTextInput,
} from 'react-native-ui-kitten';


import { data } from '../data/api';

export class ChatList extends Component {
state = {
    data: {
      original: data.getChatList(),
      filtered: data.getChatList(),
    },
  };

  renderSeparator = () => (
    <View style={styles.separator} />
  );

  renderInputLabel = () => (
    <RkText rkType='awesome'>{FontAwesome.search}</RkText>
  );
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
  render = () => (
      <FlatList
        style={styles.root}
        data={this.state.data.original}
        extraData={this.state}
        ItemSeparatorComponent={this.renderSeparator}

        renderItem={this.renderItem}
      />
    );

   renderItem = ({ item }) => {
      const last = item.messages[item.messages.length - 1];
      return (
          <View style={styles.container}>

            <View style={styles.content}>
              <View style={styles.contentHeader}>
                <RkText rkType='header5'>{`${item.withUser.firstName} ${item.withUser.lastName}`}</RkText>
                <RkText rkType='secondary4 hintColor'>
                  {moment().add(last.time, 'seconds').format('LT')}
                </RkText>
              </View>
              <RkText numberOfLines={2} rkType='primary3 mediumLine' style={{ paddingTop: 5 }}>
                {last.text}
              </RkText>
            </View>
          </View>

      );
    };


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
  },
  separator: {
    height: StyleSheet.hairlineWidth,
    backgroundColor: theme.colors.border.base,
  },
}));