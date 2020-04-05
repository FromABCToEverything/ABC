const formatTime = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : '0' + n
}

function wxLogin() {
  return new Promise((resolve, reject) => {
    wx.login({
      success(res) {
        if (res.code) {
          resolve(res.code)
        }
        else {
          reject({ message: "登录失败" })
        }
      },
      fail(err) {
        reject(err)
      }
    })
  })
}


function wxRequest(url, method = "GET", auth = false, data = {}, header = { 'content-type': 'application/json' }) {
  if (auth == true) {
    if (wx.getStorageSync("session_id") === '')
      return Promise.reject("请登录后使用")
    else
      url = url + "&session_id=" + wx.getStorageSync("session_id")
  }
  return new Promise((resolve, reject) => {
    wx.request({
      url: "http://localhost:8080" + url,
      method: method,
      header: header,
      data: data,
      success(res) {
        if (res.statusCode === 200) {
          resolve(res.data)
        }
        else {
          reject({ message: "请求失败" })
        }
      },
      fail(err) {
        reject(err)
      }
    })
  })
}

function wxCheckSession() {
  return new Promise((resolve, reject) => {
    wx.checkSession({
      success(res) {
        resolve(res)
      },
      fail(err) {
        reject(err)
      }
    })
  })
}

function wxGetUserInfo(app) {
  wx.getUserInfo({
    success(res) {
      app.globalData.userInfo = res.userInfo;
      console.log(app.globalData.userInfo)
      if (app.globalData.session_id === '') {
        wxLogin().then(code => {
          return wxRequest("/login?code0=" + code + "&name=" + app.globalData.userInfo.nickName + "&avatarUrl=" + app.globalData.userInfo.avatarUrl)
        }).then(res => {
          app.globalData.session_id = res;
          wx.setStorageSync("session_id", res)
        }).catch(reason => {
          console.log(reason)
        })
      }
      else {
        wxCheckSession().then(res => {
          console.log("已在登录态")
        }, err => {
          console.log("需重新登录")
          console.log(err)
          wxLogin().then(code => {
            // console.log(code)
            return wxRequest("/login?code0=" + code)
          }).then(res => {
            app.globalData.session_id = res;
            wx.setStorageSync("session_id", res)
          }).catch(reason => {
            console.log(reason)
          })
        })
      }
    },
    fail(err) {
      wx.showModal({
        title: '提示',
        content: '尚未进行授权，请点击确定跳转到授权页面进行授权，使用更多功能',
        success: function (res) {
          if (res.confirm) {
            console.log('用户点击确定')
            wx.navigateTo({
              url: '../tologin/tologin'
            })
          }
        }
      })
    }
  })
}


module.exports = {
  formatTime: formatTime,
  wxLogin: wxLogin,
  wxRequest: wxRequest,
  wxCheckSession: wxCheckSession,
  wxGetUserInfo: wxGetUserInfo
}

