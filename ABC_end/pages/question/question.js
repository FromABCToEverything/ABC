// miniprogram/pages/question/question.js
var util = require('../../utils/util.js');
const app = getApp();
Page({

 
  data:{
    webMsg:[],
    choice:[],
    yourAnswer:false,
    showTheAnser:false,
    stem:'',
    questionId:'',
    //收藏
    index: null,
    picker: [],
    questionId:'',

    question_id_set:[],//question_set里的题号
    currentSetId: '',//这是用户从某个题集进来，标记该题集
    currentBookId: '',//如果是某本书的未掌握题目列表，标记该书
    currentType:'',
    num: 0,//题集题号
    flagq: true,
    flagQ: true,
    flagB: true,//三个标志判断有没有下一题
    num_in_book: 0,//书籍题号

    //收藏
    userQuestionSet_arr: [],
    modalName: null,
  },

  //点击收藏，判断用户是否登录，若用户登录，则显示模态框，否则弹出消息需要用户登录
  collect: function (e) {
    var userInfo = app.globalData.userInfo
    if (userInfo != null) {
      var url = '/collect_element?type=q&question_id=' + this.data.questionId_cur;
      util.wxRequest(url, 'GET', true).then(res => {
        console.log(res.data)
        var q_arr = res.data;
        q_arr.forEach((val, id, arr) => {
          val['checked'] = val['collected']
        })
        this.setData({
          userQuestionSet_arr: q_arr,
          modalName: 'DialogModal2'
        })
      }).catch(err => {
        console.log(err);
        //测试：正式连接时删除以下代码
        var q_arr = [{ setId: 1, setName: "自建题集1", collected: 1 },
        { setId: 2, setName: "自建题集2", collected: 0 },
        { setId: 3, setName: "自建题集3", collected: 0 }];
        q_arr.forEach((val, id, arr) => {
          val['checked'] = val['collected']
        })
        this.setData({
          userQuestionSet_arr: q_arr,
          modalName: 'DialogModal2'
        })

      })
    } else {
      util.wxGetUserInfo(app)
      this.onLoad(this.data._options)
    }

  },

  chooseSet: function (e) {
    var id = e.currentTarget.dataset.id
    var raw = this.data.userQuestionSet_arr[id]['checked'];
    this.data.userQuestionSet_arr[id]['checked'] = (raw ? false : true);
    console.log(this.data.userQuestionSet_arr[id]['checked'])
  },

  confirmChoose: function (e) {
    this.data.userQuestionSet_arr.forEach((val, id, arr) => {
      var sub = val['checked'] - val['collected']
      console.log(sub)
      if (sub != 0) {
        var op = (sub === 1 ? 1 : 0);
        var url = '/collect_element?type=q&set_id=' + val['setId'] + '&entry_id=' + this.data.questionId_cur + '&op=' + op;
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
  getMsg:function(){
    // var self=this
    
    // wx.request({
    //   url: 'http://localhost:8080/question?question_id='+self.data.questionId,
    //   data:{
    
    //   },
    //   header:{
    //     'content-type':'application/json'
    //   },
    //   method: 'GET',
      
    //   success(res){
    //     var choices=res.data[0].choices
    //     console.log(choices);
    //     console.log(res.data);
        
    //     self.setData({
    //       webMsg:res.data,
    //       choice:choices.split(";"),
          
    //     })
       
    //   }
    // })

  },
  A:function()
  {
    var right_answer = this.data.webMsg[0].answer
    if('A'!=right_answer)
    {
      console.log("回答错误,正确答案为"+right_answer)
      this.data.youeAnswer = false
    }
    else
    {
      console.log("回答正确")
      this.data.youeAnswer = true
    }
    
  },
  B: function () {
    var right_answer = this.data.webMsg[0].answer
    if ('B' != right_answer) {
      console.log("回答错误,正确答案为" + right_answer)
      this.data.youeAnswer=false
    }
    else {
      console.log("回答正确")
      this.data.youeAnswer = true
      console.log(this.data.youeAnswer)
    }

  },
  C: function () {
    var right_answer = this.data.webMsg[0].answer
    if ('C' != right_answer) {
      console.log("回答错误,正确答案为" + right_answer)
      this.data.youeAnswer = false
    }
    else {
      console.log("回答正确")
      this.data.youeAnswer = true
      console.log(this.data.youeAnswer)
    }

  },
  D: function () {
    var right_answer = this.data.webMsg[0].answer
    if ('D' != right_answer) {
      console.log("回答错误,正确答案为" + right_answer)
      this.data.youeAnswer = false
    }
    else {
      console.log("回答正确")
      this.data.youeAnswer = true
    }

  },

  /**
   * 点击显示解析按钮，显示解析
   */
  showAnswer:function(){
    var showTheAnswer=this.data.showTheAnswer;
    // console.log(this.data.showTheAnswer);
    this.setData({showTheAnswer:!showTheAnswer});
    console.log(showTheAnswer)
    // this.data.youeAnswer = this.data.webMsg[0].answer
    console.log("显示正确答案" + this.data.yourAnswer)
    
  },

  /**
* 点击收藏，跳出提示框
*/
  PickerChange(e) {
    console.log(e);
    //获取用户创建的书单名称,并把名称放进picker数组中

  },

  /**
   * 点击评论进入评论界面
   */
  comment:function(){
    wx.navigateTo({
      url: '../comment/comment?to_id='+this.data.questionId,
    })
  },
  getSingleQuestion:function(){
    var self = this

    wx.request({
      url: 'http://localhost:8080/question?question_id=' + self.data.questionId,
      data: {

      },
      header: {
        'content-type': 'application/json'
      },
      method: 'GET',

      success(res) {
        var choices = res.data[0].choices
        var right = res.data[0].answer
        var stem=res.data[0].stem
        var questionId=res.data[0].questionId
        console.log(choices);
        console.log(res.data);

        self.setData({
          //webMsg: res.data,
          stem:stem,
          questionId:questionId,
          choice: choices.split(";"),
          yourAnswer: right
        })

      }
    })

  },
  getQuestionSet:function()
  {
    var self = this

    wx.request({
      url: 'http://localhost:8080/question?question_id=' + self.data.questionId,
      data: {

      },
      header: {
        'content-type': 'application/json'
      },
      method: 'GET',
      success(res) {
        console.log("这是data")
        console.log(res.data)
        console.log(res.data[0].stem)
        var num=self.data.num
        var choices = res.data[0].choices
        var right = res.data[0].answer
        var stem=res.data[0].stem
        var questionId=res.data[0].questionId
        console.log(choices);
        console.log(res.data);

        self.setData({
          //webMsg: res.data[1],
          stem:stem,
          questionId:questionId,
          choice: choices.split(";"),
          yourAnswer: right
        })

      }
    })
  },
  makeNote: function () {
    wx.navigateTo({
      url: '../note/note',
    })
  },
  //
// getBookQuestion: function () {
//     // util.wxRequest('/question_list?session_id=1&book_id=' + this.data.currentBookId, 'GET', false).then(res => {
//     //   console.log(res.data)
//     //   var self = this
//     //   var number = this.data.num_in_book
      
//     //   if (res.data == '')
//     //     self.setData({
//     //       flagB: false//没有下一题
//     //     })
      
//     //     var choices = res.data[0].choices
//     //     var right = res.data[0].answer
//     //     console.log(choices);
//     //     console.log(res.data);

//     //     self.setData({
//     //       webMsg: res.data,
//     //       choice: choices.split(";"),
//     //       yourAnswer: right
//     //     })

      

//     // })
//     var self = this

//     wx.request({
//       url: 'http://localhost:8080/question_list?session_id=1&book_id=' + this.data.currentBookId,
//       data: {

//       },
//       header: {
//         'content-type': 'application/json'
//       },
//       method: 'GET',

//       success(res) {
//         var choices = res.data[0].choice
//         var right = res.data[0].answer
//         var stem = res.data[0].stem
//         var questionId = res.data[0].questionId
//         console.log(choices);
//         console.log(res.data);

//         self.setData({
//           //webMsg: res.data,
//           stem: stem,
//           questionId: questionId,
//           choice: choices.split(";"),
//           yourAnswer: right
//         })

//       }
//     })


//   },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
   this.data.questionId=options.setId
   this.data.currentType=options.type
    console.log("获得题号" + this.data.questionId)
    console.log(this.data.currentType)
    var that=this

    //that.getSingleQuestion()
    if (that.data.currentType == 'q') {
      console.log("进入q")
      this.setData({
        questionId_cur: options.setId,//当前题号
        question_id_set: options.question_arr_id//从search获得的一组题号数组
      })
      that.getSingleQuestion()
    }
    if (that.data.currentType == 'Q') {
      this.setData({
        currentSetId: options.questionSet,
        questionId:options.questionId
      })
      console.log("问题集：" + this.data.currentSetId)
      var another = this;
      another.getQuestionSet()
    }
    //如果是用户某本书未掌握的题目
    // if (that.data.currentType == 'QL') {
    //   this.setData({
    //     currentBookId: options.bookId
    //   })
    //   console.log("书籍相关问题:" + this.data.currentBookId)
    //   var another = this;
    //   another.getBookQuestion()
    // }

      
  },
  //点击上一题
  lastQ: function () {
    // var that = this;
    // console.log("当前题的id："+that.data.questionId_cur);
    // this.setData({
    //   questionId_cur: parseInt(that.data.questionId_cur)-1
    // })
    // console.log("点击上一题之后：")
    // console.log(this.data.questionId_cur)

    // //重新设置显示的题目
    // var newQuestionId = this.data.questionId_cur;
    // var lastQuestion= this.webMsg[newQuestionId]
    // this.setData({
    //   choice:lastQuestion.choices.split(";"),
    //   yourAnswer: lastQuestion.answer,
    //   cur_stem:lastQuestion.stem
    // })

    var that = this;

    if (that.currentType == 'q') {
      var i = 0
      while (this.data.question_id_set[i] != this.data.questionId_cur)//找到这个题号数组当前questionID的数组下标
        i++;
      if (i == 0)
        return//表示没有上一题    
      var cur_id = this.data.question_id_set[--i]//减一表示上一题
      this.setData({
        questionId_cur: cur_id
      })
      that.getSingleQuestion()
    }
    if (that.currentType == 'Q') {
      var number = this.data.num - 1
      if (number <= 0)
        return
      //表示没有上一题
      this.setData({
        num: number
      })
      console.log("问题集：")
      that.getQuestionSet()
    }
    //如果是用户某本书未掌握的题目
    if (that.data.currentType == 'QL') {
      var number = this.data.num_in_book - 1
      if (number <= 0)
        return
      //表示没有上一题
      this.setData({
        num_in_book: number
      })
      console.log("书籍相关问题:")

      that.getBookQuestion()
    }

  },

  //点击下一题之后
  nextQ: function () {
    // var that = this;
    // console.log("当前题的id：" + that.data.questionId_cur);
    // this.setData({
    //   questionId_cur:parseInt(that.data.questionId_cur) + 1
    // })
    // console.log("点击下一题之后：")
    // console.log(this.data.questionId_cur)

    // //重新设置显示的题目
    // var newQuestionId = this.data.questionId_cur;
    // var nextQuestion = this.webMsg[newQuestionId]
    // this.setData({
    //   choice: nextQuestion.choices.split(";"),
    //   yourAnswer: nextQuestion.answer,
    //   cur_stem: nextQuestion.stem
    // })
    var that = this;

    if (that.currentType == 'q') {
      var number = this.data.questionId_cur + 1
      if (this.data.flagq == false)
        return
      //表示没有下一题
      this.setData({
        questionId_cur: number
      })
      that.getSingleQuestion()
    }
    if (that.currentType == 'Q') {
      var number = this.data.num + 1
      if (this.data.flagQ == false)
        return
      //表示没有下一题
      this.setData({
        num: number
      })
      console.log("问题集：")
      that.getQuestionSet()
    }
    //如果是用户某本书未掌握的题目
    if (that.data.currentType == 'QL') {
      var number = this.data.num_in_book + 1
      if (this.data.flagB == false)
        return
      //表示没有下一题
      this.setData({
        num_in_book: number
      })
      console.log("书籍相关问题:")

      that.getBookQuestion()
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