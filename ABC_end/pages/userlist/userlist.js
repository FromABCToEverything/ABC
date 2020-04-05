// pages/userlist/userlist.js
var util = require('../../utils/util.js');
var app=getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    type: '', //用户点击的集合类型:B/W/Q/N
    setId: '',
    books_arr:[
      // {
      //   bookId: 1,
      //   title: "The Old Man And The Sea",
      //   author: 'Ernest Hemingway',
      //   language: 'en',
      //   coverUrl: 'https://upload.wikimedia.org/wikipedia/zh/thumb/7/73/Oldmansea.jpg/220px-Oldmansea.jpg',
      //   textUrl: '',
      //   description: 'The Old Man and the Sea is one of Hemingway\'s most enduring works.Told in language of great simplicity and power, it is the story of an old Cuban fisherman, down on his luck, and his supreme ordeal-- a relentless, agonizing battle with a giant marlin far out in the Gulf Stream.',
      //   isbn: '978-0684801223',
      //   press: 'Scribner; Reissue edition',
      //   publicatedDate: '1995-05-05'
      // },
    ],
    words_arr:[],
    questions_arr:[],
    notes_arr:[],
    isCollected:true,
    hidefoot:''

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log("打印options")
    console.log(options)
    this.setData({
      type:options.type,
      setId:options.setId
    })
    if(options.fromWhere=='usercreate')
    this.setData({hidefoot:true})

    var that = this;
    if (that.data.type == 'B') 
      {that.showBookList()
      console.log("调用了booklist")
      }
    if (that.data.type == 'W') 
      that.showWordList()

    if (that.data.type == 'Q')
      that.showQuestionList()

    if (that.data.type == 'N')
       that.showNoteList()
  },

  showBookList: function () {
    var that = this;
    //var setId = that.data.setId;
    var setId=2;//暂定，登录不了
    //var setName = that.data.setName;
    console.log('调用showBookList:' + that.data.setId);
    util.wxRequest('/book_set_list?set_id=' + setId, 'GET', true).then(res => {
      that.setData({
        books_arr: res.data
      })
    }).catch(err => {
      console.log(err)
    })
    console.log(that.data.books_arr)
  },

  showWordList: function () {
    var that = this;
    var setId = that.data.setId;
    //var setName = that.data.setName;
    console.log('调用showWordList:' + that.data.setId);
    util.wxRequest('/word_set_list?set_id=' + setId, 'GET', true).then(res => {
      that.setData({
        words_arr: res.data
      })
    }).catch(err => {
      console.log(err)
    })
    console.log(that.data.words_arr)

  },

  showQuestionList: function () {
    var that = this;
    var setId = that.data.setId;
    //var setName = that.data.setName;
    console.log('调用showQuestionList:' + that.data.setId);
    util.wxRequest('/question_set?set_id=' + setId, 'GET', true).then(res => {
      that.setData({
        questions_arr: res.data
      })
    }).catch(err => {
      console.log(err)
    })
    console.log(that.data.questions_arr)

  },

  showNoteList: function () {
    var that = this;
    var setId = that.data.setId;
    //var setName = that.data.setName;
    console.log('调用showNoteList:' + that.data.setId);
    util.wxRequest('/note_set?set_id=' + setId, 'GET', true).then(res => {
      that.setData({
        notes_set: res.data
      })
    }).catch(err => {
      console.log(err)
    })
    console.log(that.data.notes_arr)

  },

  /**
  * 点击书单的对象进入书籍详情
  */

  toBookDetail: function (e) {
    console.log(e)
    var bookId = e.currentTarget.dataset.itemid;
    wx.navigateTo({
      url: '../bookDetail/bookDetail?bookId=' + bookId,
    })

  },

  /**
   * 点击单词列表的单词，进入该单词的详情页
   */
  toWordDetail: function (e) {
    console.log(e);
    var wordId = e.currentTarget.dataset.itemid;
    wx.navigateTo({
      url: '../wordDetail/wordDetail?wordId=' + wordId,
    })

  },

  /**
   * 点击题目进入题目详情
   */
  toQuestionDetail: function (e) {
    console.log(e);
    var questionId = e.currentTarget.dataset.itemid;
    var that = this;
    wx.navigateTo({
      url: '../question/question?type=Q' + '&questionId=' + questionId + '&questionSet=' + that.data.setId,
    })
  },

  /**
   * 点击笔记进入笔记
   */
  toNote: function (e) {
    console.log(e);
    var noteId = e.currentTarget.dataset.itemid;
    var that = this;
    var note = JSON.stringify(e.currentTarget.dataset.item)
    wx.navigateTo({
      url: '../noteDetail/noteDetail?noteId=' + noteId + '&noteSet=' + that.data.setId + '&note=' + note,
    })
  },

  //收藏
  collect:function(){
    //取消收藏
     if(isCollected==true){
       var url='/collect_other?type='+this.data.type+'&set_id='+this.data.setId+'&op=0';
       util.wxRequest(url,'POST',true).then(res=>{
         if(res.data==1){
           this.setData({ isCollected: false })
           this.showState()
         }
       })
     }
     else{
       var url = '/collect_other?type=' + this.data.type + '&set_id=' + this.data.setId + '&op=1';
       util.wxRequest(url, 'POST', true).then(res => {
         if (res.data == 1) {
           this.setData({ isCollected: true })
           this.showState()
         }
       })
     }
  },

  showState: function () {
    var that = this;
    if (that.data.isCollected == true) {
      wx.showToast({
        title: '已收藏',
        duration: 800,
      })
    } else {
      wx.showToast({
        title: '取消收藏',
        duration: 800,
      })
    }
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