// pages/vocabulary/vocabulary.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    index: null,//升序是1，降序是0
    picker: ['降序', '升序'],
    //从服务器返回的数据放进vocabulary数组中，然后按照每一个数组元素的属性在界面上进行渲染
    vocabulary:[
      // {"word_id":"1", "content":"abandon", "pos":"vt", "lang":"En", "meaning_raw":"give up something", "meaning_zh":"抛弃", "pronouce_url":null, "pronounce":null, "tag":null},
      // { "word_id": "2", "content": "abandon", "pos": "vt", "lang": "En", "meaning_raw": "give up something", "meaning_zh": "抛弃", "pronouce_url": null, "pronounce": null, "tag": null },
      // { "word_id": "3", "content": "abandon", "pos": "vt", "lang": "En", "meaning_raw": "give up something", "meaning_zh": "抛弃", "pronouce_url": null, "pronounce": null, "tag": null },
    ]

  },

/**
 * 处理选择排序函数
 */
  PickerChange(e) {
    console.log(e);
    this.setData({
      index: e.detail.value
    })
    console.log("这是排序的顺序:"+this.data.index)
    onLoad(e);
  },
/**
 * 点击单词跳转到单词详情
 */
  toWordDetail:function(e){
    // wx.navigateTo({
    //   url: '../wordDetail/wordDetail',
    // })
    console.log("?????" + e.currentTarget.dataset.item)
    wx.navigateTo({
      url: '../wordDetail/wordDetail?word_id=' + JSON.stringify(e.currentTarget.dataset.item)//、e.currentTarget
    })
  },

/**
 * 点击开始练习，跳转到练习界面
 */

startToPractice:function(){
  
},

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var book_id=options.book_id
    var self = this
    console.log("bookid?????:"+book_id)
    // var order
    // if(this.data.index==0)
    //   order='DESC';
    // else
    //   order='ASC'; 
    // console.log("这是oder:"+order)   
    wx.request({
      url: 'http://localhost:8080/word_inBook?book_id='+book_id,
      data: {

      },
      header: {
        'content-type': 'application/json'
      },
      method: 'GET',
      success: function (res) {
        console.log(res.data);
        self.setData({
          vocabulary: res.data
        })

      }
    })
    console.log("这是book下的单词")
    console.log(this.data.vocabulary)

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