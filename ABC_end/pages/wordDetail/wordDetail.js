// pages/wordDetail/wordDetail.js
Page({

  /**
   * 页面的初始数据,单词详情（由单词列表进入）
   */
  data: {
    // "wordDetail":{
    //   "content":'',
    //   "pronounce":'',//音标字符
    //   "pronounce_url":'',//音标发音url
    //   "pos":'',//词性
    //   "meaning_raw":'',//源语言释义
    //   "meaning_zh":'',//中文释义
    //   "tag":''//其他标记  （如果有，就在界面显示，反之不渲染
    // },
    wordDetail:[],

    index: null,
    picker: []
 
  },

  /**
 * 点击收藏，跳出提示框
 */
  PickerChange(e) {
    console.log(e);
    //获取用户创建的书单名称,并把名称放进picker数组中

  },


  /**
   * 跳转评论
   */
  comment:function(){
    wx.navigateTo({
      url: '../comment/comment',
      success: function(res) {},
      fail: function(res) {},
      complete: function(res) {},
    })
  },

  practice:function(){
    wx.navigateTo({
      url: '../question/question',
    })
  },


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    // console.log(options.word+"?")
    // this.setData({
    //   wordID: options.word
    // })
    var self = this
    var id = options.word_id
    console.log(id + "这是ID")
    wx.request({
      url: 'http://localhost:8080/word?word_id='+id,
      method: 'GET',
      data: {

      },
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        console.log(res.data );
        self.setData({
          wordDetail: res.data
        })
       
      }
    })
    console.log("这是Worddetail的内容"+this.data.wordDetail)
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