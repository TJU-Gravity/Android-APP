const images = [
  require('../img/Image10.png'),
  require('../img/Image11.png'),
  require('../img/Image2.png'),
  require('../img/Image3.png'),
  require('../img/Image4.png'),
  require('../img/Image1.png'),
  require('../img/Image12.png'),
  require('../img/Image8.png'),
  require('../img/Image6.png'),
  require('../img/Image9.png'),
  require('../img/Image5.png'),
  require('../img/Image7.png'),
];

const users = [{
  id: 1,
  firstName: 'Team1',
  lastName: '',
  phone: '+1 415 670 90 34',
  country: 'Belarus',
  email: 'h.gilbert@akveo.com',
  password: '123456',
  newPassword: '12345678',
  confirmPassword: '12345678',
  photo: require('../img/avatars/Image9.png'),
  postCount: 86,
  followersCount: 22102,
  followingCount: 536,
  images,

},


{
  id: 5,
  firstName: 'Team2',
  lastName: '',
  email: 'cmullaney4@tripadvisor.com',
  country: 'Philippines',
  password: 'ZlzECwoN',
  newPassword: 'N9l5KLpBW',
  confirmPassword: 'N9l5KLpBW',
  postCount: 37,
  phone: '63-(210)188-9126',
  followingCount: 745,
  followersCount: 2703,
  images,
  photo: require('../img/avatars/Image5.png'),
},
];

export default users;
