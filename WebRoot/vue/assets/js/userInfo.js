	const storage = window.localStorage;
	const userType = storage.userType;
    var app = new Vue({
        el:'#app',
        data:{
        	 setUrl:"",
        	//用户表单验证
        	 userRuleForm: {
                 name: '',
                 email: '',
                 phone: '',
                 depict:''
               },
	           userRules: {
	             email: [
	               { required: true, message: '请输入邮箱', trigger: 'change' }
	             ],
	             phone: [
	        	   { required: true, message: '请输入联系电话', trigger: 'change' }
	        	             ],
	           },
               //农场表单验证
	           passwordRuleForm: {
	             name: '',
	             oldpassword: '',
	             newpassword:''
	           },
	           passwordRules: {
	        	 oldpassword: [
	               { required: true, message: '请输入旧密码', trigger: 'change' },
	               { min: 6, max: 16, message: '长度在 6到 16 个字符', trigger: 'blur' }
	             ],
	             newpassword: [
	               { required: true, message: '请输入新密码', trigger: 'change' },
	               { min: 6, max: 16, message: '长度在 6 到 16 个字符', trigger: 'blur' }]
	           },
	           setdata: [{
	               label: '用户设置',
	               children: [{
	                 label: '修改信息',
	               },{
	            	   label: '修改密码'
	               }]
	             }],
             defaultProps: {
               children: 'children',
               label: 'label',
             },
             //设置页面显示
            showSetInfo:{
        		changeUser:true,
        		changePassword:false
            },
            activeIndex: '4',

            userName:"",
        	
        	changeData:false,
        	isChange:true,

    },
    created: function(){
    	this.userName = storage.loginName;
    	var sessionId =  storage.sessionId;
        if(sessionId==""||sessionId==null){
        	window.location.href="login.html";
        }
        if(userType=="farmowner"){
        	this.setUrl = "userInfo.html";
        }else{
        	this.setUrl = "setInfo.html";
        }
        //页面初始化
        jQuery.ajax({
            type: 'post',
            data: {userID:storage.userID},
            url: "/agriculture/userInfoInit.do",
            success: function (data) {
            		data = JSON.parse(data);
            		app.userRuleForm = data;
            },
            error: function (data){
                alert("请求数据失败，请检查网络!");
            }
        })
    },
    
    methods:{
        handleSelect:function(key, keyPath) {
            console.log(key, keyPath);
        },
    /**
     * 修改用户页面显示与隐藏
     */
    changeUser:function(){
    	this.isChange = false;
    	this.changeData = true;
    },
    /**
     * 修改用户提交
     */
    submitForm:function(formName) {
    	this.$refs[formName].validate(function(valid){
          if (valid) {
        	  jQuery.ajax({
                  type: 'post',
                  data: {userID:storage.userID,email:app.userRuleForm.email,
                	  phone:app.userRuleForm.phone,depict:app.userRuleForm.depict},
                  url: "/agriculture/modifyInfo.do",
                  success: function (data) {
                  		data = JSON.parse(data);
                  		alert(data.msg);
                  		window.location="userInfo.html";
                  },
                  error: function (data){
                      alert("请求数据失败，请检查网络!");
                  }
              })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
    /**
     * 修改密码提交
     */
      submitPasswordForm:function(formName){
    	  this.$refs[formName].validate(function(valid){
              if (valid) {
            	  jQuery.ajax({
                      type: 'post',
                      data: {userID:storage.userID,oldpassword:app.passwordRuleForm.oldpassword,
                    	  newpassword:app.passwordRuleForm.newpassword},
                      url: "/agriculture/modifyPassword.do",
                      success: function (data) {
                      		data = JSON.parse(data);
                      		alert(data.msg);
                      		window.location="userInfo.html";
                      },
                      error: function (data){
                          alert("请求数据失败，请检查网络!");
                      }
                  })
              } else {
                console.log('error submit!!');
                return false;
              }
            });
      },
      /**
       * 重置
       */
      resetForm:function(formName) {
        this.$refs[formName].resetFields();
      },
      /**
       * 层显示与隐藏
       */
      handleNodeClick:function(data) {
          switch(data.label){
          	case "修改信息":
          		this.showSetInfo.changeUser = true;
          		this.showSetInfo.changePassword = false;
          		
          		break;
          	case "修改密码":
          		this.showSetInfo.changeUser = false;
          		this.showSetInfo.changePassword = true;
	      		break;
          }
        },
        /**
         * 取消修改
         */
        cancel:function(){
    	   window.location="userInfo.html";
       },
    //退出登录
    logout:function(){
        var othis = this;
        this.$http.post("/agriculture/logout.do").then( function(res){
        	window.localStorage.removeItem("userID");
            window.localStorage.removeItem("sessionId");
            window.location.href="login.html";
        });
    	}
    }
    })