// miniprogram/pages/question/question.js
Page({

 
  data:{
    webMsg:[],
    choice:[],
    yourAnswer:'',
    showTheAnser:false,
    //收藏
    index: null,
    picker: [],
    questionId:''
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
      this.data.youeAnswer = "error"
    }
    else
    {
      console.log("回答正确")
      this.data.youeAnswer = "right"
    }
    
  },
  B: function () {
    var right_answer = this.data.webMsg[0].answer
    if ('B' != right_answer) {
      console.log("回答错误,正确答案为" + right_answer)
      this.data.youeAnswer="error"
    }
    else {
      console.log("回答正确")
      this.data.youeAnswer = "right"
      console.log(this.data.youeAnswer)
    }

  },
  C: function () {
    var right_answer = this.data.webMsg[0].answer
    if ('C' != right_answer) {
      console.log("回答错误,正确答案为" + right_answer)
      this.data.youeAnswer = "error"
    }
    else {
      console.log("回答正确")
      this.data.youeAnswer = "right"
      console.log(this.data.youeAnswer)
    }

  },
  D: function () {
    var right_answer = this.data.webMsg[0].answer
    if ('D' != right_answer) {
      console.log("回答错误,正确答案为" + right_answer)
      this.data.youeAnswer = "error"
    }
    else {
      console.log("回答正确")
      this.data.youeAnswer = "right"
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
      url: '../comment/comment',
    })
  },
 


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
   this.data.questionId=options.setId
    console.log("获得题号" + this.data.questionId)
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
        var right=res.data[0].answer
        console.log(choices);
        console.log(res.data);

        self.setData({
          webMsg: res.data,
          choice: choices.split(";"),
          yourAnswer:right
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