	$(function(){
    const storage = window.localStorage;
	const userType = storage.userType;
    var app = new Vue({
        el:'#app',
        data:{
        	//设置的url
        	 setUrl:"",
        	//添加用户表单验证
        	 userRuleForm: {
        		 userID:'',
                 name: '',
                 password: '',
                 email:'',
                 phone:'',
                 farmsChecked: [],//农场种植的作物
                 authority: 'farmowner',
                 depict:''
               },
	           userRules: {
	             name: [
	               { required: true, message: '请输入用户名', trigger: 'blur' },
	               { min: 3, max: 16, message: '长度在 3 到 16 个字符', trigger: 'blur' }
	             ],
	             password: [
	               { required: true, message: '请输入密码', trigger: 'change' },
	               { min: 6, max: 16, message: '长度在 6 到 16 个字符', trigger: 'blur' }
	             ],
	             email: [
	     	       { required: true, message: '请输入邮箱', trigger: 'change' },
	     	             ],
	     	     phone: [
 	   	           { required: true, message: '请输入联系电话', trigger: 'change' },
 	   	             ],
	             authority: [
	               { required: true, message: '请选择用户权限', trigger: 'change' }
	             ]
	           },
               //农场表单验证
	    	 farmRuleForm: {
	    		 baseId:'',
	    		 farmName: '',
	             fruitsChecked:[],
	             country: '',
	             province:'',
	             baseArea:'',//面积
	             city:'',
	             district: '',//区或县
	             baseAddr:'',//详细地址
	             longitude:'',
	             latitude:'',
	             imageUrl:''
	           },
	           farmRules: {
	        	   farmName: [
	               { required: true, message: '请输入农场名', trigger: 'blur' },
	               { min: 3, max: 30, message: '长度在 3 到 30 个字符', trigger: 'blur' }
	             ],
	             country: [
	               { required: true, message: '请输入所在国家', trigger: 'change' },
	             ],
	             city: [
	               { required: true, message: '请输入所在城市', trigger: 'change' }
	             ],
	             district: [
	 	               { required: true, message: '请输入所在区或县', trigger: 'change' }
	 	             ],
	 	         baseAddr: [
 		               { required: true, message: '请输入详细地址', trigger: 'change' }
 		             ],
	             longitude: [
	 		               { required: true, message: '请输入经度', trigger: 'change' }
	 		             ],
	             latitude: [
	 		               { required: true, message: '请输入纬度', trigger: 'change' }
	 		             ],
	           },
	           //作物表单验证
	        	 fruitRuleForm: {
	        		 fruitID:'',
	                 fruitName: '',
	                 feature: '',
	                 maxAirTemp:'',
	                 minAirTemp:'',
	                 maxAirHumi:'',
	                 minAirHumi:'',
	                 maxSoilTemp:'',
	                 minSoilTemp:'',
	                 maxSoilHumi:'',
	                 minSoilHumi:'',
	                 maxLight:'',
	                 minLight:''
	               },
		           fruitRules: {
		             fruitName: [
		               { required: true, message: '请输入水果名', trigger: 'blur' },
		               { min: 1, max: 20, message: '长度在 3 到 16 个字符', trigger: 'blur' }
		             ],
		             feature: [
		               { required: true, message: '请输入水果特征描述', trigger: 'change' },
		               { min: 6, max: 100, message: '长度在 6 到 100 个字符', trigger: 'blur' }
		             ],
		             maxAirTemp: [
		     	       { required: true, message: '请输入水果最大的空气温度阈值', trigger: 'blur' },
		     	             ],
     	             minAirTemp: [
 		     	       { required: true, message: '请输入水果最小的空气温度阈值', trigger: 'blur' },
 		     	             ],
     	             maxAirHumi: [
 		     	       { required: true, message: '请输入水果最大的空气湿度阈值', trigger: 'blur' },
 		     	             ],
 		     	     minAirHumi: [
		     	       { required: true, message: '请输入水果最小的空气湿度阈值', trigger: 'blur' },
		     	             ],
     	             maxSoilTemp: [
 		     	       { required: true, message: '请输入水果最大的土壤温度阈值', trigger: 'blur' },
 		     	             ],
     	             minSoilTemp: [
 		     	       { required: true, message: '请输入水果最小的土壤温度阈值', trigger: 'blur' },
 	 		     	             ],
     	             maxSoilHumi: [
 		     	       { required: true, message: '请输入水果最大的土壤湿度阈值', trigger: 'blur' },
 	 		     	             ],
 		     	     minSoilHumi: [
 	                   { required: true, message: '请输入水果最小的土壤湿度阈值', trigger: 'blur' },
 			     	             ],
     	             maxLight: [
 		     	       { required: true, message: '请输入水果最大的光照强度阈值', trigger: 'blur' },
 	 		     	             ],
     	             minLight: [
                       { required: true, message: '请输入水果最小的光照强度阈值', trigger: 'blur' },
 	 		     	             ],
		           },
	           setdata: [{
	               label: '用户设置',
	               children: [{
	                 label: '添加用户',
	               },{
	            	   label: '修改用户'
	               }]
	             }, {
	               label: '农场设置',
	               children: [{
	                 label: '添加农场'
	                 },{
	                 label:'修改农场'
	                 }]
	             },{
		               label: '作物设置',
		               children: [{
		                 label: '添加作物'
		                 },{
		                 label:'修改作物'
		                 }]
		             }],
             defaultProps: {
               children: 'children',
               label: 'label',
             },
             //设置页面显示
            userSet:true,
            farmSet:false,
            fruitSet:false,
            showSetInfo:{
            	user:{
            		addUser:true,
            		changeUser:false,
            		changeData:false
            	},
            	farm:{
            		addFarm:false,
            		changeFarm:false,
            		changeData:false
            	},
            	fruit:{
            		addFruit:false,
            		changeFruit:false,
            		changeData:false
            	}
            },
            activeIndex: '4',
            
        	userName:"",
        	registerUser:false,
        	isAddUser:true,
            users:[],//所有农场主
            farms:[],//所有农场
            //用户所拥有的农场
            userFarms:[],
            //数据库中记录的作物
            farmFruits:[],
            image:'',
            hasImage:false//添加农场时，有照片时提交才能成功
    },
    created: function(){
    	this.userName = storage.loginName;
    	var sessionId =  storage.sessionId;
        if(sessionId==""||sessionId==null){
        	window.location.href="login.html";
        }
        
        if(userType=="farmowner"){
        	this.setUrl = "userInfo.html";
        	alert("对不起，您没有权限！");
        	window.location = "userInfo.html";
        }else{
        	this.setUrl = "setInfo.html";
        }
        
        this.initData();
    },
    
    methods:{
    	/**
    	 * 初始化数据
    	 */
    	initData:function(){
    		jQuery.ajax({
    	        type: 'post',
    	        data:{userID:storage.userID},
    	        url: "/agriculture/setInfoInit.do",
    	        success: function (data) {
    	        		data = JSON.parse(data);
    	        		app.farms = data.farms1;
    	        		app.userFarms = data.farms2;//用户所拥有的农场
    	        		app.users = data.farmOwners//所有农场主
    	        		app.farmFruits = data.fruits;//数据库中的作物记录
    	        },
    	        error: function (data){
    	            alert("请求数据失败，请检查网络!");
    	        	}
    	        })
    	},
        handleSelect:function(key, keyPath) {
            console.log(key, keyPath);
        },
        /**
         * 点击添加用户层隐藏与显示
         */
        addUser:function(){
        	this.isAddUser = false;
        	this.registerUser = true;
        },
        
        /**
         * 添加用户提交事件
         */
        addUserSubmit:function(formName) {
        	this.$refs[formName].validate(function(valid) {
              if (valid) {
            	  jQuery.ajax({
                      type: 'post',
                      data: {userForm:JSON.stringify(app.userRuleForm),registTime:new Date()},
                      url: "/agriculture/addUser.do",
                      success: function (data) {
                      		data = JSON.parse(data);
                      		alert(data.msg);
                      		window.location = "setInfo.html";
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
         * 点击用户的"修改信息"触发事件
         */
        changeUserData:function(index,row){
        	this.farmSet = false;
        	this.userSet = true;
        	this.fruitSet = false;
        	this.showSetInfo.user.addUser = false;
      		this.showSetInfo.user.changeUser = false;
      		this.showSetInfo.user.changeData = true;
      		
      		jQuery.ajax({
    	        type: 'post',
    	        //获取用户名
    	        data:{userID:app.users[index].userID},
    	        url: "/agriculture/getUserInfo.do",
    	        success: function (data) {
    	        		data = JSON.parse(data);
    	        		app.userRuleForm = data;
    	        		app.userRuleForm.name = data.userName;
    	        },
    	        error: function (data){
    	            alert("请求数据失败，请检查网络!");
    	        	}
    	        })
      		
        },
       
        /**
         * 管理员修改用户信息提交事件
         */
        changeUserSubmit:function(formName){
        	this.$refs[formName].validate(function(valid){
                if (valid) {
                	jQuery.ajax({
                        type: 'post',
                        data: {userForm:JSON.stringify(app.userRuleForm),date:new Date()},
                        url: "/agriculture/modifyAllInfo.do",
                        success: function (data) {
                        	data = JSON.parse(data);
                    		app.initData();
            				alert(data.msg);
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
        
        deleteUser:function(index,row){
        	this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }).then(function(){
            	  jQuery.ajax({
          	        type: 'post',
          	        //获取用户ID
          	        data:{userID:app.users[index].userID},
          	        url: "/agriculture/deleteUser.do",
          	        success: function (data) {
          	        	app.$message({
                            type: 'success',
                            message: '删除成功!'
                        });
          	        	app.initData();
          	        },
          	        error: function (data){
          	            alert("请求数据失败，请检查网络!");
          	        	}
          	        })
                
              }).catch(function(){
                this.$message({
                  type: 'info',
                  message: '已取消删除'
                });          
              });
        },
        
        /**
         * 添加农场提交事件
         */
        addFarmSubmit:function(formName){
        	if(app.hasImage){
        		this.$refs[formName].validate(function(valid){
        			if (valid) {
        				jQuery.ajax({
        					type: 'post',
        					//获取用户名
        					data:{farmForm:JSON.stringify(app.farmRuleForm),image:app.image},
        					url: "/agriculture/addFarm.do",
        					success: function (data) {
        						data = JSON.parse(data);
        						alert(data.msg);
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
        	}else{
        		alert("请选择农场图片");
        	}
        },
        
        /**
         * 管理员点击农场的"修改信息"事件
         */
        changeFarmData:function(index,row){
        	this.farmSet = true;
        	this.userSet = false;
        	this.fruitSet = false;
        	this.showSetInfo.farm.addFarm = false;
      		this.showSetInfo.farm.changeFarm = false;
      		this.showSetInfo.farm.changeData = true;
      		
      		jQuery.ajax({
    	        type: 'post',
    	        //获取用户ID
    	        data:{farmId:app.farms[index].farmID,},
    	        url: "/agriculture/getBaseInfo.do",
    	        success: function (data) {
    	        		data = JSON.parse(data);
    	        		app.farmRuleForm = data;
    	        		app.farmRuleForm.farmName = data.baseName;
    	        		$("#img2").attr("src",app.farmRuleForm.imageUrl);
    	        },
    	        error: function (data){
    	            alert("请求数据失败，请检查网络!");
    	        	}
    	        })
      		
        },
        deleteFarm:function(index,row){
        	this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }).then(function(){
            	  jQuery.ajax({
          	        type: 'post',
          	        //获取用户ID
          	        data:{farmID:app.farms[index].farmID},
          	        url: "/agriculture/deleteFarm.do",
          	        success: function (data) {
          	        	app.$message({
                            type: 'success',
                            message: '删除成功!'
                        });
          	        	app.initData();
          	        },
          	        error: function (data){
          	            alert("请求数据失败，请检查网络!");
          	        	}
          	        })
                
              }).catch(function(){
                this.$message({
                  type: 'info',
                  message: '已取消删除'
                });          
              });
        },
        /**
         * 修改农场提交事件
         */
        changeFarmSubmit:function(formName){
    		jQuery.ajax({
    			type: 'post',
    			data:{farmForm:JSON.stringify(app.farmRuleForm),userID:storage.userID,image:app.image},
    			url: "/agriculture/modifyFarmInfo.do",
    			success: function (data) {
    				data = JSON.parse(data);
    				app.initData();
    				alert(data.msg);
    			},
    			error: function (data){
    				alert("请求数据失败，请检查网络!");
    			}
    		})
        },
        
        /**
         * 添加作物提交事件
         */
        addFruitSubmit:function(formName) {
        	this.$refs[formName].validate(function(valid) {
              if (valid) {
            	  jQuery.ajax({
                      type: 'post',
                      data: {fruitForm:JSON.stringify(app.fruitRuleForm)},
                      url: "/agriculture/addFruit.do",
                      success: function (data) {
                      		data = JSON.parse(data);
                      		alert(data.msg);
                      		window.location = "setInfo.html";
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
         * 点击作物的"修改信息"触发事件
         */
        changeFruitData:function(index,row){
        	this.farmSet = false;
        	this.userSet = false;
        	this.fruitSet = true;
        	this.showSetInfo.fruit.addFruit = false;
      		this.showSetInfo.fruit.changeFruit = false;
      		this.showSetInfo.fruit.changeData = true;
      		
      		jQuery.ajax({
    	        type: 'post',
    	        //获取用户名
    	        data:{fruitID:app.farmFruits[index].fruitID},
    	        url: "/agriculture/getFruit.do",
    	        success: function (data) {
    	        		data = JSON.parse(data);
    	        		app.fruitRuleForm = data;
    	        },
    	        error: function (data){
    	            alert("请求数据失败，请检查网络!");
    	        	}
    	        })
      		
        },
       
        /**
         * 修改作物信息提交事件
         */
        changeFruitSubmit:function(formName){
        	this.$refs[formName].validate(function(valid){
                if (valid) {
                	jQuery.ajax({
                        type: 'post',
                        data: {fruitForm:JSON.stringify(app.fruitRuleForm)},
                        url: "/agriculture/modifyFruit.do",
                        success: function (data) {
                        		data = JSON.parse(data);
                        		app.initData();
                				alert(data.msg);
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
        
        deleteFruit:function(index,row){
        	this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }).then(function(){
            	  jQuery.ajax({
          	        type: 'post',
          	        //获取用户ID
          	        data:{fruitID:app.farmFruits[index].fruitID},
          	        url: "/agriculture/deleteFruit.do",
          	        success: function (data) {
          	        	app.$message({
                            type: 'success',
                            message: '删除成功!'
                        });
          	        	app.initData();
          	        },
          	        error: function (data){
          	            alert("请求数据失败，请检查网络!");
          	        	}
          	        })
                
              }).catch(function(){
                this.$message({
                  type: 'info',
                  message: '已取消删除'
                });          
              });
        },
        
        /**
         * 重置表单
         */
        resetForm:function(formName) {
            this.$refs[formName].resetFields();
          },
      /**
       * 取消修改
       */
      cancel:function(){
    	  window.location="setInfo.html";
      },
      /**
       * 层隐藏于显示
       */
      handleNodeClick:function(data) {
          switch(data.label){
          	case "添加用户":
          		this.showSetInfo.user.addUser = true;
          		this.showSetInfo.user.changeUser = false;
          		this.showSetInfo.user.changeData = false;
          		this.farmSet = false;
          		this.userSet = true;
          		this.fruitSet = false;
          		//重新置空
          		this.userRuleForm={userID:'', name: '', password: '', email:'',
                        phone:'', farmsChecked: [], authority: 'farmowner', depict:''};
          		break;
          	case "修改用户":
	      		this.showSetInfo.user.addUser = false;
	      		this.showSetInfo.user.changeUser = true;
	      		this.showSetInfo.user.changeData = false;
	      		this.farmSet = false;
	      		this.userSet = true;
	      		this.fruitSet = false;
	      		this.initData();
	      		break;
          	case "添加农场":
          		this.userSet = false;
          		this.farmSet = true;
          		this.fruitSet = false;
	      		this.showSetInfo.farm.addFarm = true;
	      		this.showSetInfo.farm.changeFarm = false;
	      		this.showSetInfo.farm.changeData = false;
	      		//重新置空
	      		this.farmRuleForm = { farmID:'', farmName: '', fruitsChecked:[],
	   	             country: '', province:'', area:'', city:'', distinct: '',//区或县
	   	             address:'', longitude:'', latitude:'', imageUrl:'' };
	      		break;
          	case "修改农场":
          		this.userSet = false;
          		this.farmSet = true;
          		this.fruitSet = false;
	      		this.showSetInfo.farm.addFarm = false;
	      		this.showSetInfo.farm.changeFarm = true;
	      		this.showSetInfo.farm.changeData = false;
	      		this.initData();
	      		break;
          	case "添加作物":
          		this.userSet = false;
          		this.farmSet = false;
          		this.fruitSet = true;
            	this.showSetInfo.fruit.addFruit = true;
          		this.showSetInfo.fruit.changeFruit = false;
          		this.showSetInfo.fruit.changeData = false;
	      		//重新置空
	      		this.fruitRuleForm = { fruitID:'', fruitName: '',
	                 feature: '', maxAirTemp:'', minAirTemp:'', maxAirHumi:'',
	                 minAirHumi:'', maxSoilTemp:'', minSoilTemp:'', maxSoilHumi:'', 
	                 minSoilHumi:'',maxLight:'', minLight:''};
	      		break;
          	case "修改作物":
          		this.userSet = false;
          		this.farmSet = false;
          		this.fruitSet = true;
	      		this.showSetInfo.fruit.addFruit = false;
	      		this.showSetInfo.fruit.changeFruit = true;
	      		this.showSetInfo.fruit.changeData = false;
	      		this.initData();
	      		break;
          }
        },
    //退出登录
    logout:function(){
        var othis = this;
        this.$http.post("/agriculture/logout.do").then( function(res){
        	window.localStorage.removeItem("userID");
            window.localStorage.removeItem("sessionId");
            window.location.href="login.html";
        });
    },
    upload:function($ev){
    	
    	var inputId = $ev.target.id;
    	//获取输入框按钮的ID的最后一位是1还是2
    	var imgId =  inputId.charAt(inputId.length - 1);
    	//得到相片的ID
    	if(imgId=="1"){
    		imgId = "img1";
    	}else{
    		imgId = "img2";
    	}
    	var input = $("#"+inputId)[0];
    	
    	input.addEventListener("change",function(){
    		if(this.files.length>0){
    			app.hasImage = true;
    		}
    		
    		var file = input.files[0];
    		if(!new RegExp("jpeg|png").test(file.type)){  
    			alert("照片上传：文件类型必须是JPG、PNG格式！"); 
    			return false;  
    		}
    	    var reader = new FileReader();
    	    
    	    
    	    reader.readAsDataURL(file);
    	    
    	    reader.onload = function(e) {
    	    	$("#"+imgId).attr("src",this.result);
    	    	app.image = this.result;
    	    }
    	    });
    	}
    }
    });
    
    
	});