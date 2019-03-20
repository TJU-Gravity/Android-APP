import { Input } from 'react-native-elements';
import React from 'react';
import {
    Text,
    FlatList,
    View,
    StyleSheet,
    TouchableOpacity,
    Button,
    ScrollView,
} from 'react-native';
import {
    RkText,
    RkButton, RkStyleSheet,
} from 'react-native-ui-kitten';
export class NewTeam extends React.Component {
    render = () => (
        <ScrollView style={styles.root}>
<View style={{height:30}}/>
            <View style={{height:30}}/>

            <View style={styles.section}>
                <Text>团队名称</Text>
                <View style={{height:20}}/>
                <Input
                    containerStyle={{ width: "80%" }}
                    inputStyle={{
                        height: 60,
                        backgroundColor: "#eee",
                        borderColor: "#eee",
                        borderWidth: 2,
                        borderRadius: 15}}
                />
                <View style={{height:30}}/>
                <Text >团队简介</Text>
                <View style={{height:20}}/>

            </View>
            <View style={styles.section}>
        <Input
            multiline
            containerStyle={{ width: "80%" }}
            inputStyle={{
                height: 200,
                backgroundColor: "#eee",
                borderColor: "#eee",
                borderWidth: 2,
                borderRadius: 15}}
        />
            </View>
            <View style={{height:30}}/>
            <View style={styles.section}>
                <TouchableOpacity style={styles.buttonContainer} >
                    <Text style={styles.textStyle}>确定</Text>
                </TouchableOpacity>
                <TouchableOpacity style={styles.buttonContainer} onPress={() => this.props.navigation.goBack()}>
                    <Text style={styles.textStyle}>取消</Text>
                </TouchableOpacity>
            </View>

        </ScrollView>
    );
}
const styles = RkStyleSheet.create(theme => ({
    root: {

        flex: 1,
        backgroundColor: 'white',
    },
    section: {
        flex: 1,
        alignItems: 'center',
    },
    textStyle:{
        fontSize:20,
        color: "#ffffff",
        fontWeight: "600"
    },
    buttonContainer: {
        marginTop:10,
        height:45,
        flexDirection: 'row',
        justifyContent: 'center',
        alignItems: 'center',
        marginBottom:20,
        width:250,
        borderRadius:30,
        backgroundColor: "#315668",
    },
}));