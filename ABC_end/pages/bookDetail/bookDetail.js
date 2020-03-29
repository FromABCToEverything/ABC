// pages/bookDetail/bookDetail.js
// const app = getApp();
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
    modalName: null

  },
  showModal(e) {
    this.setData({
      modalName: e.currentTarget.dataset.target
    })
  },
  hideModal(e) {
    this.setData({
      modalName: null
    })
  },
  chooseSet(e){
    console.log(e.currentTarget.dataset.id);
    this.setData({
      modalName: null
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
  comment: function() {
    wx.navigateTo({
      url: '../comment/comment',
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