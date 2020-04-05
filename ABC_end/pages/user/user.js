// pages/user/user.js
var util = require('../../utils/util.js');
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    //长按删除事件
    userInfo: '',

    touchStart: '',
    touchEnd: '',
    setTitle: '标题123',//用户输入创建集合的标题
    private: 1,//0是公开的集合
    checked: false,

    userSet_arr: [
      { name: 'B', value: '书单' },
      { name: 'W', value: '词集' },
      { name: 'Q', value: '题集' },
      { name: 'N', value: '笔记集' }
    ],
    tab_cur_id: 0,
    tab_cur_name: 'B',

    create_book_set: [
      // { 'bookSetId': 1, 'bookSetName': '创建书单1' },
      // { 'bookSetId': 2, 'bookSetName': '创建书单2' },
      // { 'bookSetId': 3, 'bookSetName': '创建书单3' },
    ],
    collect_book_set: [
      // { 'bookSetId_col': 1, 'bookSetName_col': '收藏书单1' },
      // { 'bookSetId_col': 2, 'bookSetName_col': '收藏书单2' },
      // { 'bookSetId_col': 3, 'bookSetName_col': '收藏书单3' },
    ],

    create_word_set: [
      // { 'wordSetId': 1, 'wordSetName': '创建单词集1' },
      // { 'wordSetId': 2, 'wordSetName': '创建单词集2' },
    ],
    collect_word_set: [],

    create_question_set: [],
    collect_question_set: [],

    create_note_set: [],
    collect_note_set: [],

  },

  tabSelect: function (e) {
    var dataset = e.target.dataset;
    console.log(e);
    this.setData({
      tab_cur_id: dataset.id,
      tab_cur_name: dataset.name
    })
    console.log(dataset.name);
  },

  //获取用户输入的标题
  inputBlur: function (e) {
    this.setData({
      setTitle: e.detail.value
    })
    console.log(this.data.setTitle)
  },

  //设置集合私人还是公开
  setPrivate: function (e) {
    var change = this.data.checked
    this.setData({
      checked: !change
    })
    if (this.data.checked == false) {
      this.setData({ private: 0 })
      //console.log(this.data.private)
    } else {
      this.setData({ private: 1 })
      //console.log(this.data.private)
    }
  },
  //确认创建
  confirm: function (e) {
    //util.wxRequest('/collect_self?')
    var title = this.data.setTitle;
    var type = this.data.tab_cur_name;
    var isPrivate = this.data.private;
    console.log("标题加类型:" + title + "..." + type);
    // var newBookSet= this.data.create_book_set;
    var creator_name = app.globalData.userInfo.nickName;//获取全局变量中的userInfo.nickName作为创建者名字
    util.wxRequest('/collect_self?type=' + type + '&set_name=' + title + '&creator_name=' + creator_name + '&private=' + isPrivate, 'POST', true).then(res => {
      console.log(res.data)
      if (res.data == 1) {
        console.log("创建成功")
        //重新发送url显示用户的书单
        util.wxRequest('/collect_self?type=' + type, 'GET', true).then(rees => {
          console.log(res.data)
          this.setData({
            create_set: res.data
          })
          console.log(this.data.create_set)
        })
      } else {
        console.log("创建失败")
      }
    })

    this.setData({
      modalName: null,
      setTitle: ''
    })

  },

  showModal(e) {
    console.log(e.currentTarget.dataset.target);
    this.setData({
      modalName: e.currentTarget.dataset.target
    })
  },
  hideModal(e) {
    this.setData({
      modalName: null,
      setTitle: ''
    })
  },


  //长按删除事件
  touchStart: function (e) {
    var that = this;
    that.setData({
      touchStart: e.timeStamp
    })
  },

  touchEnd: function (e) {
    var that = this;
    that.setData({
      touchEnd: e.timeStamp
    })
  },

  pressTap: function (e) {
    console.log(e)
    var setId = e.currentTarget.dataset.setid;
    console.log(setId)
    var that = this;
    var touchTime = that.data.touchEnd - that.data.touchStart;
    var type = this.data.tab_cur_name;
    console.log("删除的集合类型是" + type)

    if (touchTime > 1000) {
      wx.showModal({
        title: '警告',
        content: '你将删除这个集合',
        success: function (res) {
          if (res.confirm) {
            util.wxRequest('/collect_self?type=' + type + '&set_id=' + setId, 'DELETE', true).then(res => {
              if (res.data == 1) {
                console.log("删除成功")
                //删除成功后重新发送请求显示用户创建的集合
                util.wxRequest('/collect_self?type=' + type, 'GET', true).then(res => {
                  console.log(res.data)
                  this.setData({
                    create_set: res.data
                  })
                  console.log(this.data.create_set)
                })
              }
            })
          }
        }
      })
    }
    //时间短于350ms，进入list界面
    if (touchTime < 350) {
      wx.navigateTo({
        url: '../userlist/userlist?type=' + type + '&setId=' + setId + '&fromWhere=usercreate',
      })
    }

  },

  //点击收藏的某个集合，跳转到list
  toList: function (e) {
    var setId = e.currentTarget.dataset.setid;
    console.log(e)
    wx.navigateTo({
      url: '../userlist/userlist?type=' + this.data.tab_cur_name + '&setId=' + setId + '&fromWhere=usercollect',
    })
  },



  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var userInfo = app.globalData.userInfo
    console.log(userInfo)
    if (userInfo != null) {
      cosole.log("........")
      this.setData({
        userInfo: userInfo
      })
      console.log("调用showxxxset的函数")
      var that = this;
      that.showBookSet();
      that.showWordSet();
      that.showQuestionSet();
      that.showNoteSet();
    } else {
      util.wxGetUserInfo(app)
      //this.onLoad(options)
    }
  },

  showBookSet: function () {
    //请求用户创建的集合
    util.wxRequest('/collect_self?session_id=1&type=B', 'GET', true).then(res => {
      console.log(res.data)
      this.setData({
        create_book_set: res.data
      })
      console.log("showBookSet")
      console.log(this.data.create_book_set)
    })

    //请求用户收藏的集合
    util.wxRequest('/collect_other?session_id=3&type=B', 'GET', true).then(res => {
      console.log(res.data)
      this.setData({
        collect_book_set: res.data
      })
      console.log("showBookSet")
      console.log(this.data.collect_book_set)
    })


  },

  showWordSet: function () {
    util.wxRequest('/collect_self?session_id=3&type=W', 'GET', true).then(res => {
      console.log(res.data)
      this.setData({
        create_word_set: res.data
      })
      console.log("showwordSet")
      console.log(this.data.create_word_set)
    })

    //请求用户收藏的集合
    util.wxRequest('/collect_other?session_id=3&type=W', 'GET', true).then(res => {
      console.log(res.data)
      this.setData({
        collect_word_set: res.data
      })
      console.log("showwordSet")
      console.log(this.data.collect_word_set)
    })

  },

  showQuestionSet: function () {
    util.wxRequest('/collect_self?type=Q', 'GET', true).then(res => {
      console.log(res.data)
      this.setData({
        create_question_set: res.data
      })
      console.log("showquestionset")
      console.log(this.data.create_question_set)
    })

    //请求用户收藏的集合
    util.wxRequest('/collect_other?type=Q', 'GET', true).then(res => {
      console.log(res.data)
      this.setData({
        collect_question_set: res.data
      })
      console.log("showquestionSet")
      console.log(this.data.collect_question_set)
    })

  },

  showNoteSet: function () {
    util.wxRequest('/collect_self?type=N', 'GET', true).then(res => {
      console.log(res.data)
      this.setData({
        create_note_set: res.data
      })
      console.log("shownoteSet")
      console.log(this.data.create_note_set)
    })

    //请求用户收藏的集合
    util.wxRequest('/collect_other?type=N', 'GET', true).then(res => {
      console.log(res.data)
      this.setData({
        collect_note_set: res.data
      })
      console.log(this.data.collect_note_set)
    })

  },


  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})