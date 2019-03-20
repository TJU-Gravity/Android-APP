import React from 'react';
import {
    View,
    ScrollView,
    StyleSheet,
    Text,
    TouchableOpacity,
    Image,
    Button,
} from 'react-native';
import {
    RkText,
    RkButton, RkStyleSheet,
} from 'react-native-ui-kitten';
import { Avatar } from 'react-native-elements';

import { data } from '../data';
import { FontIcons } from '../assets/icons';
import formatNumber from '../utils/textUtils';
import Icon from 'react-native-vector-icons/AntDesign';

export class TeamDetail extends React.Component {
    static navigationOptions = ({ navigation }) => {
        if(navigation.getParam('user')!==null&&navigation.getParam('user')==='teammate')
            return {
            headerRight: (
                <Button
                    onPress={navigation.getParam('edit')}
                    title="编辑"
                    color="#fff"
                />
            ),
        };
    };

    componentDidMount() {
        this.props.navigation.setParams({ edit: this._edit });
        this.props.navigation.setParams({ user: this._user });
    }

    _user = () => {
       return this.state.user;
    };


    _edit = () => {
        //this.setState({ count: this.state.count + 1 });
    };


    state = {
        data: data.getUser(),
        user:null,
    };
    constructor(props) {
        super(props);
        navigation=props.navigation;
        this.state.user=navigation.getParam('user','visitor');


    }
    getTeamDetail=()=> {
        return fetch('https://mywebsite.com/endpoint/', {
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                firstParam: 'yourValue',
                secondParam: 'yourOtherValue',
            }),
        })
            .then((response) => response.json())
            .then((responseJson) => {
                return responseJson.movies;
            })
            .catch((error) => {
                console.error(error);
            });
    };

    onAddUserPressed = () => {

    };
    onAddUserGroupPressed=()=>
    {

    };

    Who=()=>
    {
        if(this.state.user!==null&&this.state.user==='teammate')
        {
            return(
<View>
                <TouchableOpacity style={styles.buttonContainer} onPress={() => this.onAddUserPressed()}>
                    <Text style={styles.textStyle}>邀请成员</Text>
                </TouchableOpacity>
                <TouchableOpacity style={styles.buttonContainer}>
        <Text style={styles.textStyle}>退出团队</Text>
        </TouchableOpacity>
</View>
            )
        }
        else
        {
            return(

                <TouchableOpacity style={styles.buttonContainer} onPress={() => this.onAddUserGroupPressed()}>
                    <Text style={styles.textStyle}>加入团队</Text>
                </TouchableOpacity>
            )
        }
    };
    render = () => (
        <ScrollView style={styles.root}>

                <View style={styles.container}>
                    <View style={styles.header}></View>
                    <Avatar rounded
                            size="large"
                            title="Team"
                            containerStyle={styles.avatar}
                    />
                    <View style={styles.body}>
                    <View style={styles.bodyContent}>
                        <RkText style={styles.name}>TEAM NAME</RkText>

                        <Text style={styles.info}>tab1 / tab2</Text>
                        <Text style={styles.description}>Lorem ipsum dolor sit amet, saepe sapientem eu nam. Qui ne assum electram expetendis, omittam deseruisse consequuntur ius an,</Text>

                        {this.Who()}
                    </View>
            </View>
            </View>

        </ScrollView>
    );
}

const styles = RkStyleSheet.create(theme => ({
    root: {
        backgroundColor: theme.colors.screen.base,
    },
    header:{
        backgroundColor: "#315668",
        height:150,
    },
    row: {
        flexDirection: 'row',

    },
    userInfo: {
        flexDirection: 'row',
        paddingVertical: 18,
    },
    bordered: {
        borderBottomWidth: 1,
        borderColor: theme.colors.border.base,
    },
    section: {
        flex: 1,
        alignItems: 'center',
    },
    space: {
        marginBottom: 3,
    },
    separator: {
        backgroundColor: theme.colors.border.base,
        alignSelf: 'center',
        flexDirection: 'row',
        flex: 0,
        width: 1,
        height: 42,
    },
    buttons: {
        flex: 2,
    },
    contentHeader: {
        flexDirection: 'row',
        justifyContent: 'space-between',
        marginBottom: 6,
        paddingTop: 5,
    },
    button: {
        width: 0,
        borderRadius:0,
        marginTop: 27.5,
        backgroundColor:'white',
        alignSelf: 'center',

    },
    content: {
        marginLeft: 16,
        flex: 1,
    },body:{
        marginTop:40,
    },
    bodyContent: {
        flex: 1,
        alignItems: 'center',
        padding:30,
    },
    name:{
        fontSize:28,
        color: "#696969",
        fontWeight: "600"
    },
    textStyle:{
        fontSize:20,
        color: "#ffffff",
        fontWeight: "600"
    },
    info:{
        fontSize:16,
        color: "#315668",
        marginTop:10
    },
    description:{
        fontSize:16,
        color: "#696969",
        marginTop:10,
        textAlign: 'center'
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
    avatar: {
        width: 130,
        height: 130,

        borderColor: "white",
        marginBottom:10,
        alignSelf:'center',
        position: 'absolute',
        marginTop:80
    },
}));
