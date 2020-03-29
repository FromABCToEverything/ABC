// pages/search/search.js
Page({
  data: {
    hidden: false,
    searched:false,
    keyword: '',
    types: [],
    sort: 'd',
    sort_index: 0,
    sort_obj_arr: [
      { name: 'd', value: '默认' },
      { name: 't', value: '时间' },
      { name: 's', value: '评分' },
      { name: 'c', value: '收藏' },
    ],
    order: 0,
    order_obj_arr: [
      { name: 0, value: '降序' },
      { name: 1, value: '升序' },
    ],
    order_index: 0,
    type_obj_arr: [
      { name: 'b', value: '书籍', checked: true },
      { name: 'q', value: '题目', checked: false },
      { name: 'B', value: '书单', checked: false },
      { name: 'W', value: '词集', checked: false },
      { name: 'Q', value: '题集', checked: false },
      { name: 'N', value: '笔记集', checked: false },
    ],
    tab_cur_id: 0,
    tab_cur_name:'',
    tab_to_show_arr: [],
    book_arr:[
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
      // {
      //   bookId: 2,
      //   title: "Indian Campa",
      //   author: 'Ernest Hemingway',
      //   language: 'en',
      //   coverUrl: 'https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1371063379l/13239934.jpg',
      //   textUrl: '',
      //   description: 'A young boy is taken by his doctor father to an indian camp to heal an indian.',
      //   isbn: '978-1842320754',
      //   press: 'House of Stratus',
      //   publicatedDate: '2001-12-01'
      // },
    ],
    question_arr:[
      // {
      //   questionId:1,
      //   creatorId:1,
      //   type:'',
      //   pointId:1,
      //   stem:"这是一个题的题干",
      //   collectedTimes:20,
      //   score:'80',
      //   lastEditTime:'2020-3-21'
      // }
    ],
    book_set_arr:[
      // {
      //   bookSetId:1,
      //   bookSetName:'我是书单',
      //   creatorId:1,
      //   creatorName:'佚名',
      //   createdTime:'2020-3-22',
      //   lastEditTime:'2020-2-1',
      //   collectedTimes:20,
      //   // bookSetCoverUrl: 'https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1371063379l/13239934.jpg'
      // }
    ],
    word_set_arr:[
      // {
      //   wordSetId: 1,
      //   wordSetName: '我是词集',
      //   creatorId: 1,
      //   creatorName: '佚名',
      //   createdTime: '2020-3-22',
      //   lastEditTime: '2020-2-1',
      //   collectedTimes: 20,
      // }
    ],
    question_set_arr:[
      // {
      //   questionSetId: 1,
      //   questionSetName: '我是题集',
      //   creatorId: 1,
      //   creatorName: '佚名',
      //   createdTime: '2020-3-22',
      //   lastEditTime: '2020-2-1',
      //   collectedTimes: 20,
      // }
    ],
    note_set_arr:[
      // {
      //   noteSetId: 1,
      //   noteSetName: '我是笔记集',
      //   creatorId: 1,
      //   creatorName: '佚名',
      //   createdTime: '2020-3-22',
      //   lastEditTime: '2020-2-1',
      //   collectedTimes: 20,
      // }
    ],

  },

  InputBlur: function (e) {
    this.setData({
      keyword: e.detail.value
    })
    console.log(this.data.keyword+"这是我的keyword");
  },
  Search: function (e) {
    var hidden = this.data.hidden;
    var tabs = this.data.type_obj_arr;
    var types = this.data.types;
    var tab_to_show_arr=[];

    var tab_name;
    var tab;
    if(types.length!=0){
      this.setData({
        tab_cur_name: types[0],
      })
    }
    else{
      this.setData({
        tab_cur_name: ''
      })
    }
    for(var i =0,len1 = tabs.length;i<len1;i++){
      tab = tabs[i];
      var choosen=false;
      for (var j = 0, len2 = types.length; j < len2; j++){
        tab_name = types[j];
        if(tab_name==tab.name){
          tab_to_show_arr.push(tab);
          choosen=true;
        }
      }
      tabs[i].checked = (choosen?true:false);
    }
    // console.log(tab_to_show_arr);
    if(!hidden){
    this.setData({
      hidden: !hidden,
      searched: true,
      type_obj_arr: tabs,
      tab_to_show_arr: tab_to_show_arr
    })
    }
    var that = this;
    console.log("check geturl function")
    //choiceb()
    console.log("看看URL" + 'http://localhost:8080/search/book?keyword='+  that.data.keyword + '&sort=' + that.data.sort + '&order=' + that.data.order)
    
    var length=this.data.types.length
    var i=0;
    var url;
    //that.choiceb()
    while(i++<length){
    if(that.data.types[i]=='b')  //book
       { 
      console.log("type["+i+"]" + that.data.types[i]) 
        that.choiceb()
      }
      if (this.data.types[i] == 'B')  //book_set
      {
        console.log("type[" + i + "]" + that.data.types[i]) 
        that.choiceB()
      }
      if (this.data.types[i] == 'q')  //question
      {
        console.log("type[" + i + "]" + that.data.types[i]) 
        that.choicequestion()
      }
      if (this.data.types[i] == 'N')  //note
      {
        console.log("type[" + i + "]" + that.data.types[i]) 
        that.choiceN()
      }
      if (this.data.types[i] == 'Q')  //question_set
      {
        console.log("type[" + i + "]" + that.data.types[i]) 
        that.choiceQ()
      }
      if (this.data.types[i] == 'W')  //word_set
      {
        console.log("type[" + i + "]" + that.data.types[i]) 
        that.choiceW()
      }
      
    }
   
  },


  SortPickerChange(e) {
    console.log(e);
    this.setData({
      sort_index: e.detail.value,
      sort: this.data.sort_obj_arr[e.detail.value].name
    })
  },
  OrderPickerChange(e) {
    this.setData({
      order_index: e.detail.value,
      order: this.data.order_obj_arr[e.detail.value].name
    })
  },
  TypesChange: function (e) {
    this.data.types = e.detail.value;
     console.log(this.data.types);
     console.log(this.data.types.length)
  },
  HiddenChange:function(e){
    var hidden = this.data.hidden;
    this.setData({
      hidden: !hidden
    })
  },
  TabSelect:function(e){
    var dataset = e.target.dataset;
    this.setData({
      tab_cur_id: dataset.id,
      tab_cur_name: dataset.name
    })
    // console.log(dataset.name);
  },
  
  choiceb:function()
  {
    console.log("这是b的request")
    console.log("这是choiceb")
    var self = this
    wx.request({
      url: 'http://localhost:8080/search/book?keyword=' +  self.data.keyword + '&sort=' + self.data.sort + '&order=' + self.data.order,
      data: {

      },
      header: {
        'content-type': 'application/json'
      },
      method: 'GET',
      success(res) {
        console.log(res.data);
        self.setData({
          book_arr: res.data,
        })

      }
    })
    console.log(that.dat.book_arra)
  },

  choiceB:function()
  {
    console.log("这是B的request")
    console.log('http://localhost:8080/search/book?keyword=' + this.data.keyword + '&sort=' + this.data.sort + '&order='+this.data.order)
    var self = this
    wx.request({
      url: 'http://localhost:8080/search/book_set?keyword='+self.data.keyword+'&sort='+self.data.sort+'&order=' + self.data.order,
     
      data: {

      },
      header: {
        'content-type': 'application/json'
      },
      method: 'GET',
      
      success(res) {
        console.log("这是res的数据")
        console.log(res.data);
        self.setData({
          book_set_arr: res.data,
        })

      }
    })
    //console.log(url)
    console.log("book_set")
    console.log(this.data.book_set_arr)
  },
  choicequestion:function()
  {
    console.log("这是q的request")
    var self = this
    wx.request({
      url: 'http://localhost:8080/search/question?keyword=' +  self.data.keyword + '&sort=' + self.data.sort + '&order=' + self.data.order,
      data: {

      },
      header: {
        'content-type': 'application/json'
      },
      method: 'GET',

      success(res) {
        console.log(res.data);
        self.setData({
          question_arr: res.data,
        })

      }
    })
  },
  choiceN:function()
  {
    console.log("这是N的request")
    var self = this
    wx.request({
      url: 'http://localhost:8080/search/note?keyword='+  self.data.keyword + '&sort=' + self.data.sort + '&order=' + self.data.order,
      data: {

      },
      header: {
        'content-type': 'application/json'
      },
      method: 'GET',

      success(res) {
        console.log(res.data);
        self.setData({
          note_set_arr: res.data,
        })

      }
    })
  },
  choiceQ:function()
  {
    console.log("这是Q的request")
    var self = this
    wx.request({
      url: 'http://localhost:8080/search/question_set?keyword='+  self.data.keyword + '&sort=' + self.data.sort + '&order=' + self.data.order,
      data: {

      },
      header: {
        'content-type': 'application/json'
      },
      method: 'GET',

      success(res) {
        console.log(res.data);
        self.setData({
          question_set_arr: res.data,
        })

      }
    })
  },
  choiceW:function()
  {
    console.log("这是W的request")
    var self = this
    wx.request({
      url: 'http://localhost:8080/search/word?keyword=' +  self.data.keyword + '&sort=' + self.data.sort + '&order=' + self.data.order,
      data: {

      },
      header: {
        'content-type': 'application/json'
      },
      method: 'GET',

      success(res) {
        console.log(res.data);
        self.setData({
          word_set_arr: res.data,
        })

      }
    })
  },

  /**
   * 点击书籍详情进入书籍页
   */
  toBookDetail:function(e){
    console.log(e)
    var itemId = e.currentTarget.dataset.itemid;
    console.log(itemId);
    var bookTitle = e.currentTarget.dataset.bookname;
    wx.navigateTo({
      url: '../bookDetail/bookDetail?type=b'+'&bookId='+itemId+'&title='+bookTitle,
    })
  },
  

  /**
   * 点击书单进入书单详情页
   */
  toBookList:function(e){
    console.log(e);
    var type=e.currentTarget.dataset.type;
    var setId=e.currentTarget.dataset.itemid;
    var setName = e.currentTarget.dataset.booksetname;
    wx.navigateTo({
      // url:'../bookSet/bookSet?type=B'+'&bookSetId='+bookSetId+'&bookSetName='+bookSetName,
      url:'../list/list?type=B'+'&setId=' + setId + '&setName=' + setName,
    })
  },

  /**
   * 点击进入单词详情页
   */
  toWordList:function(e){
    console.log(e);
    var type = e.currentTarget.dataset.type;
    var setId = e.currentTarget.dataset.itemid;
    var setName = e.currentTarget.dataset.wordsetname;
    wx.navigateTo({
      url: '../list/list?type=W' + '&setId=' + setId + '&setName=' + setName,
    })
  },

  /**
   * 点击进入题目列表
   */
  toQuestionList:function(e){
    console.log(e);
    var type = e.currentTarget.dataset.type;
    var setId = e.currentTarget.dataset.itemid;
    var setName = e.currentTarget.dataset.questionsetname;
    wx.navigateTo({
      url: '../list/list?type=Q' + '& setId=' + setId + '& setName=' + setName,
    })
  },

  /**
   * 点击进入笔记列表
   */
  toNoteList:function(e){
    console.log(e);
    var type = e.currentTarget.dataset.type;
    var setId = e.currentTarget.dataset.itemid;
    var setName = e.currentTarget.dataset.notesetname;
    wx.navigateTo({
      url: '../list/list?type=N' + '& setId=' + setId + '& setName=' + setName,
    })
  },
  /**
   * 点击进入题目详情
   */
  toQuestionDetail:function(e){
    var id=e.currentTarget.dataset.item
    console.log("这是题号"+id)

   wx.navigateTo({
     url: '../question/question?setId='+id,
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