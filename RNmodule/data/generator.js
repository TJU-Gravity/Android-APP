import conversations,users  from './api'

const populateConversations = () => {
    conversations.map(conversation => {
        const userConversation = conversation;
        userConversation.withUser = _.find(users, x => x.id === conversation.withUserId) || users[0];
        return userConversation;
    });
};

const populate = () => {

    populateConversations();
};

export default populate;