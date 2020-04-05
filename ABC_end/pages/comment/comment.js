// pages/comment/comment.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    modalName:null,
    commentList: [],
    content:'测试',
    score: 0,
    session_id: 3,
    type:'b',
    to_id:1,
    page:0,
    score_arr:[
      { name: 1, value: '1星' },
      { name: 2, value: '2星' },
      { name: 3, value: '3星', checked:true },
      { name: 4, value: '4星' },
      { name: 5, value: '5星' },
    ]
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
  scoreChange(e){
    var score = e.currentTarget.dataset.name;
    this.setData({
      score: score,
      modalName: null
    })
  },
  inputBlur(e) {
    this.setData({
       content:e.detail.value
    })
    console.log(this.data.content);
  },
  sendComment(e){
    var that = this;
    wx.request({
      url: 'http://localhost:8080/comment?type=' + that.data.type + '&to_id=' + that.data.to_id + '&session_id=' + that.data.session_id + '&score=' + that.data.score + '&content=' + that.data.content,
      method: 'POST',
      data:{
        // 'type': that.data.type,
        // 'to_id': that.data.to_id,
        // 'session_id': that.data.session_id,
        // 'score': that.data.score,
        // 'content': that.data.content
      },

    success: function (res) {
      console.log(res.data);
      wx.showToast({
        title:'评论成功'
      })
    }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var to_id=options.to_id
    var that = this
    wx.request({
      url: 'http://localhost:8080/comment?type=b&to_id='+to_id+'&page='+this.data.page,
      method:"GET",
      data:{

      },
      success:function (res) {
        that.setData({
          commentList: res.data
        })
      }
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