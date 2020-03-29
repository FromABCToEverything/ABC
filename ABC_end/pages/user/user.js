// pages/user/user.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userSet_arr:[
      {name:'B',value:'书单'},
      {name:'W',value:'词集'},
      {name:'Q',value:'题集'},
      {name:'N',value:'笔记集'}
    ],
    tab_cur_id:0,
    tab_cur_name:'B',

  },

  tabSelect: function (e) {
    var dataset = e.target.dataset;
    console.log(e);
    this.setData({
      tab_cur_id: dataset.id,
      tab_cur_name: dataset.name
    })
    // console.log(dataset.name);
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