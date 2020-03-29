//app.js
var util =require('./utils/util.js');


App({
  globalData: {
    userInfo:null,
    session_id: ''
  },
  
  onLaunch: function() {
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          this.globalData.session_id = wx.getStorageSync("session_id");

          if (this.globalData.session_id === '') {
            
            util.wxGetUserInfo().then(res => {
              this.globalData.userInfo = res.userInfo
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
              return util.wxLogin()
            }).then(code => {
              // console.log(code)
              return util.wxRequest("/login?code0=" + code + "&name=" + this.globalData.userInfo.nickName + "&avatarUrl=" + this.globalData.userInfo.avatarUrl)
            }).then(res => {
              this.globalData.session_id = res;
              wx.setStorageSync("session_id", res)
            }).catch(reason => {
              console.log(reason)
            })
          }
          else {
            var that = this
            util.wxCheckSession().then(res => {
              console.log("已在登录态")
              // console.log(res)
            }, err => {
              console.log("需重新登录")
              console.log(err)
              util.wxLogin().then(code => {
                // console.log(code)
                return util.wxRequest("/login?code0=" + code)
              }).then(res => {
                this.globalData.session_id = res;
                wx.setStorageSync("session_id", res)
              }).catch(reason => {
                console.log(reason)
              })
            })
          }
          
        }
      }
    })

    
    // 获取系统状态栏信息
    wx.getSystemInfo({
      success: e => {
        this.globalData.StatusBar = e.statusBarHeight;
        let capsule = wx.getMenuButtonBoundingClientRect();
        if (capsule) {
         	this.globalData.Custom = capsule;
        	this.globalData.CustomBar = capsule.bottom + capsule.top - e.statusBarHeight;
        } else {
        	this.globalData.CustomBar = e.statusBarHeight + 50;
        }
      }
    })
  },

})