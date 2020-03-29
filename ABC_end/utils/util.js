const app = getApp();

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


function wxRequest(url, method = "GET", auth = false, data = {},header = { 'content-type': 'application/json' }) {
  if(auth==true){
    if (wx.getStorageSync("session_id") === '')
      return Promise.reject("请登录后使用")
    else
      url =url+"&session_id="+wx.getStorageSync("session_id")
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

function wxGetUserInfo() {
  return new Promise((resolve, reject) => {
    wx.getUserInfo({
      success(res) {
        console.log("成功获取用户信息")
        resolve(res)
      },
      fail(err) {
        reject(err)
      }
    })
  })
}


module.exports = {
  formatTime: formatTime,
  wxLogin: wxLogin,
  wxRequest: wxRequest,
  wxCheckSession: wxCheckSession,
  wxGetUserInfo: wxGetUserInfo
}


