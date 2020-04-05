// pages/wordDetail/wordDetail.js
var util = require('../../utils/util.js');
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
    book_id:'',
    index: null,
    picker: [],
    userWordSet_arr: [],
    modalName: null,
 
  },

  /**
 * 点击收藏，跳出提示框
 */
  PickerChange(e) {
    console.log(e);
    //获取用户创建的书单名称,并把名称放进picker数组中

  },
  collect: function (e) {
    var url = '/collect_element?type=w&word_id=' + this.data.word_id;
    util.wxRequest(url, 'GET', true).then(res => {
      console.log(res.data)
      var w_arr = res.data;
      w_arr.forEach((val, id, arr) => {
        val['checked'] = val['collected']
      })
      this.setData({
        userWordSet_arr: w_arr,
        modalName: 'DialogModal2'
      })
    }).catch(err => {
      console.log(err);
      //测试：
      var w_arr = [{ setId: 1, setName: "自建题集1", collected: 1 },
      { setId: 2, setName: "自建题集2", collected: 0 },
      { setId: 3, setName: "自建题集3", collected: 0 }];
      w_arr.forEach((val, id, arr) => {
        val['checked'] = val['collected']
      })
      this.setData({
        userWordSet_arr: w_arr,
        modalName: 'DialogModal2'
      })

    })

  },

  chooseSet: function (e) {
    var id = e.currentTarget.dataset.id
    var raw = this.data.userWordSet_arr[id]['checked'];
    this.data.userWordSet_arr[id]['checked'] = (raw ? false : true);
    console.log(this.data.userWordSet_arr[id]['checked'])
  },

  confirmChoose: function (e) {
    this.data.userWordSet_arr.forEach((val, id, arr) => {
      var sub = val['checked'] - val['collected']
      console.log(sub)
      if (sub != 0) {
        var op = (sub === 1 ? 1 : 0);
        var url = '/collect_element?type=w&set_id=' + val['setId'] + '&entry_id=' + this.data.word_id + '&op=' + op;
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

  /**
   * 跳转评论
   */
  Tocomment:function(e){
    var to_id = e.currentTarget.dataset.id
    wx.navigateTo({
      url: '../comment/comment?to_id=' +this.data.book_id,
    })
  },

  Topractice:function(){
    console.log("这是传过去的书号" + this.data.book_id)
    wx.navigateTo({
      url: '../question/question?type=QL&bookId='+this.data.book_id,
    })
  },
  makeNote:function(){
    wx.navigateTo({
      url: '../note/note',
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
    this.setData({
      book_id:options.book_id
    })
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