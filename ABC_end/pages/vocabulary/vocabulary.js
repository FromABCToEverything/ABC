// pages/vocabulary/vocabulary.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    flag:true,
    page:0,
    book_ids:'',
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
      url: '../wordDetail/wordDetail?word_id=' + JSON.stringify(e.currentTarget.dataset.item) + '&book_id=' + this.data.book_ids//、e.currentTarget
    })
  },

/**
 * 点击开始练习，跳转到练习界面
 */

startToPractice:function(){
  wx.navigateTo({
    // url:'../bookSet/bookSet?type=B'+'&bookSetId='+bookSetId+'&bookSetName='+bookSetName,
    url: '../question/question?type=QL&nookId=' + this.data.book_ids,
  })
},

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var book_id=options.book_id
    var self = this
    self.setData({
      book_ids:book_id
    })
    // console.log("bookid?????:"+book_id)
    // // var order
    // // if(this.data.index==0)
    // //   order='DESC';
    // // else
    // //   order='ASC'; 
    // // console.log("这是oder:"+order)   
    // wx.request({
    //   url: 'http://localhost:8080/word_inBook?book_id='+book_id+'&page='+1,
    //   data: {

    //   },
    //   header: {
    //     'content-type': 'application/json'
    //   },
    //   method: 'GET',
    //   success: function (res) {
    //     console.log(res.data);
    //     self.setData({
    //       vocabulary: res.data
    //     })

    //   }
    // })
    console.log("这是book下的单词")
    console.log(this.data.vocabulary)
    self.getMsg()
  },
  onReachBottom: function () {
    var that = this;
    // 显示加载图标
    if(that.data.flag==true)
    that.getMsg()
    else
    return
  },
  getMsg:function()
  {
    wx.showLoading({
      title: 'loading',
    })
    console.log("测试，onload能不能调用啊")
    console.log("bookid?????:" + this.data.book_ids)
    
    var cur_page=this.data.page+1
    this.setData({
      page: cur_page 
    })
    console.log("现在的页数："+cur_page)
    var self=this
    wx.request({
      url: 'http://localhost:8080/word_inBook?book_id=' + self.data.book_ids + '&page=' + cur_page,
      data: {

      },
      header: {
        'content-type': 'application/json'
      },
      method: 'GET',
      success: function (res) {
        console.log(res.data);
        if(res.data!=''){
        self.setData({
          vocabulary: res.data
        })
        wx.hideLoading();
      }
      else
      {
          wx.showToast({
            title: '没有更多数据！'
          })
          self.setData({
            flag:false
          })
      }
      }
    })
  },
  Tocomment: function (e) {
    var to_id = e.currentTarget.dataset.id
    wx.navigateTo({
      url: '../comment/comment?to_id=' + this.data.book_ids,
    })
  },
  makeNote: function () {
    wx.navigateTo({
      url: '../note/note',
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
 

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})