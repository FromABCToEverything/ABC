//index.js
//获取应用实例
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    page:0,
    session_id:3,
    // bookList: [{
    //     bookId: "1",
    //     coverUrl: "/src/images/01.jpg ",
    //     title: "撒野"
    //   },
    //   {
    //     bookId: "2",
    //     coverUrl: "/src/images/01.jpg ",
    //     title: "撒野"
    //   },
    //   {
    //     bookId: "3",
    //     coverUrl: "/src/images/01.jpg ",
    //     title: "撒野"
    //   },
    //   {
    //     bookId: "4",
    //     coverUrl: "/src/images/01.jpg ",
    //     title: "撒野"
    //   }
    // ]
    bookList:[]
  },
  /**
   * 点击搜索框进入搜索界面
   */
  toSearch:function(e){
    wx.navigateTo({
      url: '../search/search'
    })
  },

  /**
   * 点击书本进入书籍详情
   */
  toBookDetail:function(e){
    console.log(e)
    var bookId=e.currentTarget.dataset.itemid;
    var bookTitle = e.currentTarget.dataset.bookname;
   wx.navigateTo({
     url: '../bookDetail/bookDetail?session_id=3'+'&bookId='+bookId+'&title='+bookTitle,
   })
  },
  

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var self = this

    wx.request({
      url: 'http://localhost:8080/book_set?session_id=3&page=1',
      data: {

      },
      header: {
        'content-type': 'application/json'
      },
      method: 'GET',
      success: function (res) {
        console.log(res.data);
        self.setData({
          bookList: res.data.book
        })
      }
    })

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})




// Page({
//   data: {
//     StatusBar: app.globalData.StatusBar,
//     CustomBar: app.globalData.CustomBar,
//     motto: 'Hi 开发者！',
//     userInfo: {},
//     hasUserInfo: false,
//     canIUse: wx.canIUse('button.open-type.getUserInfo')
//   },
//   //事件处理函数
//   bindViewTap: function() {
//     wx.navigateTo({
//       url: '../logs/logs'
//     })
//   },
//   onLoad: function () {
//     if (app.globalData.userInfo) {
//       this.setData({
//         userInfo: app.globalData.userInfo,
//         hasUserInfo: true
//       })
//     } else if (this.data.canIUse){
//       // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
//       // 所以此处加入 callback 以防止这种情况
//       app.userInfoReadyCallback = res => {
//         this.setData({
//           userInfo: res.userInfo,
//           hasUserInfo: true
//         })
//       }
//     } else {
//       // 在没有 open-type=getUserInfo 版本的兼容处理
//       wx.getUserInfo({
//         success: res => {
//           app.globalData.userInfo = res.userInfo
//           this.setData({
//             userInfo: res.userInfo,
//             hasUserInfo: true
//           })
//         }
//       })
//     }
//   },
//   getUserInfo: function(e) {
//     console.log(e)
//     app.globalData.userInfo = e.detail.userInfo
//     this.setData({
//       userInfo: e.detail.userInfo,
//       hasUserInfo: true
//     })
//   }
// })