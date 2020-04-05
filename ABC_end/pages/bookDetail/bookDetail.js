// pages/bookDetail/bookDetail.js
const app = getApp();
var util = require('../../utils/util.js');
Page({

  /**
   * 页面的初始数据，点击某本书，显示该书籍的详细信息，具体信息在data属性中
   */
  data: {
    book: [],
    // bookInfo: {
    //   book_id: '',
    //   title: "撒野",
    //   lang: '',
    //   cover_url: "/src/images/01.jpg",
    //   isbn: null,
    //   press: null,
    //   publicated: '0000-00-0',
    //   description:"这是巫哲的一本书,讲述了两个少年互相救赎的故事,是一个很温暖的故事，让人充满了力量"
    // },
    bookInfo:'',

    //这是用户的书单列表，在界面初始化时请求用户的书单列表，然后绑定到页面上
    userBookSet_arr: [
      {bookSetId:1, bookSetName:'用户书单1'},
      {bookSetId:2, bookSetName:'用户书单2'},
      {bookSetId:3, bookSetName:'用户书单3'},    
    ],
    bookSet_index: 0,
    userBookSet_arr: [],
    modalName: null,
    bookId:''
  },
  collect: function (e) {
    //点击收藏，首先判断用户是否登录，若用户登录则显示模态框，否则弹出消息需要用户登录
    // var userInfo = app.globalData.userInfo
    // console.log(userInfo)
    // if (userInfo != null) {
    //   var url = '/collect_element?type=b&book_id=' + this.data.bookId;
    //   util.wxRequest(url, 'GET', true).then(res => {
    //     console.log(res.data)
    //     var b_arr = res.data;
    //     b_arr.forEach((val, id, arr) => {
    //       val['checked'] = val['collected']
    //     })
    //     this.setData({
    //       userBookSet_arr: b_arr,
    //       modalName: 'DialogModal2'
    //     })
    //     this.onLoad(this.data._options)
    //   }).catch(err => {
    //     console.log(err)

    //     //测试：正式连后端时请删除以下代码
    //     var b_arr = [{ setId: 1, setName: "自建书单1", collected: 1 },
    //     { setId: 2, setName: "自建书单2", collected: 0 },
    //     { setId: 3, setName: "自建书单3", collected: 0 }];
    //     b_arr.forEach((val, id, arr) => {
    //       val['checked'] = val['collected']
    //     })
    //     this.setData({
    //       userBookSet_arr: b_arr,
    //       modalName: 'DialogModal2'
    //     })

    //   })
    // } else {
    //   util.wxGetUserInfo(app)
    //   this.onLoad(this.data._options)
    // }
    var url = '/collect_element?type=b&entry_id=' + this.data.bookId+'&session_id=2';
    util.wxRequest(url, 'GET', true).then(res => {
      console.log(res.data)
      var b_arr = res.data;
      b_arr.forEach((val, id, arr) => {
        val['checked'] = val['collected']
      })
      this.setData({
        userBookSet_arr: b_arr,
        modalName: 'DialogModal2'
      })
      this.onLoad(this.data._options)
    }).catch(err => {
      console.log(err)

      //测试：正式连后端时请删除以下代码
      var b_arr = [{ setId: 1, setName: "自建书单1", collected: 1 },
      { setId: 2, setName: "自建书单2", collected: 0 },
      { setId: 3, setName: "自建书单3", collected: 0 }];
      b_arr.forEach((val, id, arr) => {
        val['checked'] = val['collected']
      })
      this.setData({
        userBookSet_arr: b_arr,
        modalName: 'DialogModal2'
      })

    })

  },

  chooseSet: function (e) {
    var id = e.currentTarget.dataset.id
    var raw = this.data.userBookSet_arr[id]['checked'];
    this.data.userBookSet_arr[id]['checked'] = (raw ? false : true);
    console.log(this.data.userBookSet_arr[id]['checked'])
  },

  confirmChoose: function (e) {
    this.data.userBookSet_arr.forEach((val, id, arr) => {
      var sub = val['checked'] - val['collected']
      console.log(sub)
      if (sub != 0) {
        var op = (sub === 1 ? 1 : 0);
        var url = '/collect_element?open_id=dsffgreg&set_id=' + val['setId'] + '&entry_id=' + this.data.book_id + 'type=b';
        console.log(url)
        util.wxRequest(url, 'POST').then(res => {
          console.log(res)
        }).catch(err => {
          console.log(err)
        })
      }
    })
    this.hideModal()
  },


  showModal(e) {
    this.setData({
      modalName: 'DialogModal2'
    })
  },
  hideModal(e) {
    this.setData({
      modalName: null
    })
  },
  makeNote: function () {
    wx.navigateTo({
      url: '../note/note',
    })
  },
  // PickerChange:function(e) {
  //   console.log('picker发送选择改变，携带值为：',e.detail.value);
  //   //把用户的书单放进数组中
  //   this.setData({
  //     bookSet_index: e.detail.value,//每次选择了下拉列表的内容同时修改下标然后修改显示的内容，显示的内容和选择的内容一致
  //    // sellectedBookSet: this.data.userBookSet_arr[e.detail.value].bookSetName//这个数据是用户选择将书本收藏到该书单中
  //   }),
  //    // console.log(sellectedBookSet)
  //    console.log(this.data.userBookSet_arr[e.detail.value])

  // },

  /**
   * 点击跳转单词表
   */
  toVocabulary: function(e) {
    var book_id = e.currentTarget.dataset.book
    console.log("book_id:"+book_id)
    wx.navigateTo({
      url: '../vocabulary/vocabulary?book_id='+JSON.stringify(book_id),
    })
  },

  /**
   * 点击跳转评论界面
   */
  comment: function(e) {
    var to_id=this.data.bookId
    console.log("转去comment的bookID"+to_id)
    wx.navigateTo({
      url: '../comment/comment?to_id='+to_id,
    })
  },





  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that=this;
    console.log(options)
    var bookId=options.bookId;
    var title = options.title;
    this.setData({
      bookId:bookId
    })
    console.log("这是这本书的bookID："+bookId)
    wx.request({
      url: 'http://localhost:8080/book?book_id='+bookId,
      data: {

      },
      header: {
        'content-type': 'application/json'
      },
      method: 'GET',
      success(res) {
        console.log(res.data);

        that.setData({
          book: res.data
        })
      }
    })
    console.log("test")
    console.log(this.data.bookInfo)
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