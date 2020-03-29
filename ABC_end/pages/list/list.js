// pages/list/list.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    open_id: 1,
    type: '', //用户点击的集合类型:B/W/Q/N
    setId: '',
    setName: '', //用户点击的集合名字

    // isBookSetCollected: false,
    // isWordSetCollected: false,
    // isQuestionSetCollected: false,
    // isNoteSetCollected: false,
    isCollected:false,
    op: [0, 1],

    books_arr: [{
        bookId: 1,
        title: "The Old Man And The Sea",
        author: 'Ernest Hemingway',
        language: 'en',
        coverUrl: 'https://upload.wikimedia.org/wikipedia/zh/thumb/7/73/Oldmansea.jpg/220px-Oldmansea.jpg',
        textUrl: '',
        description: 'The Old Man and the Sea is one of Hemingway\'s most enduring works.Told in language of great simplicity and power, it is the story of an old Cuban fisherman, down on his luck, and his supreme ordeal-- a relentless, agonizing battle with a giant marlin far out in the Gulf Stream.',
        isbn: '978-0684801223',
        press: 'Scribner; Reissue edition',
        publicatedDate: '1995-05-05'
      },
      {
        bookId: 2,
        title: "Indian Campa",
        author: 'Ernest Hemingway',
        language: 'en',
        coverUrl: 'https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1371063379l/13239934.jpg',
        textUrl: '',
        description: 'A young boy is taken by his doctor father to an indian camp to heal an indian.',
        isbn: '978-1842320754',
        press: 'House of Stratus',
        publicatedDate: '2001-12-01'
      },
    ], //如果点击的是书单，则把书的信息放进该数组
    words_arr: [{
        "word_id": "1",
        "content": "abandon",
        "pos": "vt",
        "lang": "En",
        "meaning_raw": "give up something",
        "meaning_zh": "抛弃",
        "pronouce_url": null,
        "pronounce": null,
        "tag": null
      },
      {
        "word_id": "2",
        "content": "abandon",
        "pos": "vt",
        "lang": "En",
        "meaning_raw": "give up something",
        "meaning_zh": "抛弃",
        "pronouce_url": null,
        "pronounce": null,
        "tag": null
      },
      {
        "word_id": "3",
        "content": "abandon",
        "pos": "vt",
        "lang": "En",
        "meaning_raw": "give up something",
        "meaning_zh": "抛弃",
        "pronouce_url": null,
        "pronounce": null,
        "tag": null
      },
    ], //如果点击的是单词集合，则把单词放进该数组
    questions_arr: [{
        questionSetName: '', //这个参数是题集参数（即这个题是来自于哪个题集的）
        questionId: 1,
        creatorId: 1,
        type: '',
        pointId: 1,
        stem: "这是一个题的题干",
        collectedTimes: 20,
        score: '80',
        lastEditTime: '2020-3-21'
      },
      {
        questionId: 2,
        creatorId: 2,
        type: '',
        pointId: 2,
        stem: "这是一个题的题干2",
        collectedTimes: 20,
        score: '80',
        lastEditTime: '2020-3-21'
      }
    ], //如果点击的是题集，则把题目列表放进该数组
    notes_arr: [{
        noteId: '1',
        creatorId: '1',
        creator: "某某某",
        title: "这是一个笔记",
        content: "塔里克是保护者星灵，用超乎寻常的力量守护着符文之地的生命、仁爱以及万物之美。塔里克由于渎职而被放逐，离开了祖国德玛西亚，前去攀登巨神峰寻找救赎，但他找到的却是来自星界的更高层的召唤。现在的塔里克与古代巨神族的神力相融合，以瓦洛兰之盾的身份，永不疲倦地警惕着阴险狡诈的虚空腐化之力"
      },
      {
        noteId: '2',
        creatorId: '2',
        creator: "某某某某",
        title: "这是一个笔记2",
        content: "塔里克是保护者星灵，用超乎寻常的力量守护着符文之地的生命、仁爱以及万物之美。塔里克由于渎职而被放逐，离开了祖国德玛西亚，前去攀登巨神峰寻找救赎，但他找到的却是来自星界的更高层的召唤。现在的塔里克与古代巨神族的神力相融合，以瓦洛兰之盾的身份，永不疲倦地警惕着阴险狡诈的虚空腐化之力"
      }
    ] //如果点击的是笔记集，则把笔记列表放进该列表
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    console.log(options);
    // var cur_type = options.type;
    // var setId = options.setId;
    // var setName = options.setName;
    //console.log(type);

    this.setData({
      type: options.type,
      setId: options.setId,
      setName: options.setName
    })
    console.log('chabc: ' + this.data.type + this.data.setId + this.data.setName);
    var that = this;
    if (that.data.type == 'B') {
      console.log("这是书单")
      that.showBookList()
    }

    if (that.data.type == 'W') {
      console.log("这是词集")
      that.showWordList()
    }

    if (that.data.type == 'Q') {
      console.log("这是题集")
      that.showQuestionList()
    }

    if (that.data.type == 'N') {
      console.log("这是笔记集")
      that.showNoteList()
    }
  },

  showBookList: function() {
    var that = this;
    var setId = that.data.setId;
    var setName = that.data.setName;
    console.log('调用showBookList:' + that.data.setName);
    console.log('http://localhost:8080/book_set_list?set_id=' + setId)
    wx.request({
      url: 'http://localhost:8080/book_set_list?set_id=' + setId ,
      success: function(res) {
        that.setData({
          books_arr: res.data
        })
      }
    })
    console.log(that.data.books_arr)
  },

  showWordList: function() {
    var that = this;
    var setId = that.data.setId;
    var setName = that.data.setName;
    console.log('调用showWordList:' + that.data.setId);
    wx.request({
      url: 'http://localhost:8080/word_set_list?set_id=' + setId ,
      success: function(res) {
        that.setData({
          words_arr: res.data
        })
      }
    })
  },

  showQuestionList: function() {
    var that = this;
    var setId = that.data.setId;
    var setName = that.data.setName;
    console.log('调用showQuestionList:' + that.data.setName);
    wx.request({
      url: 'http://localhost:8080/question_set?set_id=' + setId ,
      success: function(res) {
        that.setData({
          questions_arr: res.data
        })
      }
    })
  },

  showNoteList: function() {
    var that = this;
    var setId = that.data.setId;
    var setName = that.data.setName;
    console.log('调用showNoteList:' + that.data.setName);
    wx.request({
      url: 'http://localhost:8080/note_set?set_id=' + setId,
      success: function(res) {
        that.setData({
          notes_arr: res.data
        })
      }
    })
  },

  collect: function() {
    var that = this;
    console.log('判断是集合id：'+that.data.setId+'   '+that.data.setName);
    // if (that.data.type == 'B') {
      if (that.data.isBookSetCollected == false) {
        wx.request({
          url: 'http://localhost:8080/collect_other',
          method:'POST',
          data:{
            'type':that.data.type,
            'open_id':that.data.open_id,
            'set_id':that.data.setId,
            'op':1
          },
        
          success:function(res){
            console.log(res);
            this.setData({
              // isBookSetCollected:true,
              isCollected:true,
            })
            var newState=this;
            that.showStateTrue();
          }

        })
      }
      else{
         
      }
    // }

  },

  showStateTrue: function() {
     wx.showToast({
       title: '已收藏',
       duration:800,
     })
  },
  toBookDetail:function(e){
    var bookId = e.currentTarget.dataset.item
    wx.navigateTo({
      // url:'../bookSet/bookSet?type=B'+'&bookSetId='+bookSetId+'&bookSetName='+bookSetName,
      url: '../bookDetail/bookDetail?bookId=' + bookId,
    })

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