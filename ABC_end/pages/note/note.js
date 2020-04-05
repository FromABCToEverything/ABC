// pages/note/note.js
Page({
  data: {
    note:'',
    session_id:3,
    content: '',


  },
  titleBlur:function(e){
    this.setData({
      title: e.detail.value
    })
    console.log(this.data.title+"这是评论标题");
  },
  noteInput:function(e){
    this.setData({
      content: e.detail.value
    })
    console.log(this.data.content+"这是评论内容");
  },
  commit:function(){
    var that = this;
    wx.request({
      url: 'http://localhost:8080/note?title='+that.data.title+'&content='+that.data.content,
      method: 'POST',
      data: {
        
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
      success: function (res) {
        console.log(res.data);
        wx.showModal({
          title: '提交成功'
        })
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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