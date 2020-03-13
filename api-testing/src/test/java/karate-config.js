function fn() {   
  var config = { // base config JSON
    appId: 'my.app.id',
    appSecret: 'my.secret',
    baseUrl: karate.properties['baseUrl'],
    userKey: karate.properties['userKey']
  };

  karate.configure('connectTimeout', 5000);
  karate.configure('readTimeout', 5000);
  return config;
}
