const storage = window.localStorage;
	//用户角色
	const userType  = storage.userType;
	
    Vue.http.options.emulateJSON = true;
    var app = new Vue({
        el:'#app',
        data:{
            activeIndex: '3',
			
			userName:"",
			//显示日历
			showCalendar:false,
			radio:"实时监控",
			 //基地信息
            baseInfo:{
            baseId:"",
            baseName:"",
            fruits:"",
            baseArea:"",
            baseAddr:""
        	},
            //区域
            areaVideo:"",
            
            value: '',
            
            pickerOptions: {
            	disabledDate:function(time) {
            		return time.getTime() > Date.now() ;
           	 	},
                shortcuts: [{
                    text: '今天',
                    onClick:function(picker) {
                    picker.$emit('pick', new Date());
                           }
                    },{
		            text: '昨天',
		            onClick:function(picker) {
		            const date = new Date();
		            date.setTime(date.getTime() - 3600 * 1000 * 24);
		            picker.$emit('pick', date);
	        			}
	    			}, {
	        		text: '一周前',
	                onClick:function(picker) {
		            const date = new Date();
		            date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
		            picker.$emit('pick', date);
        			}
    			}]
    		},
        dateTime: new Date()
    },
    
    
    created: function(){
    	this.userName = localStorage.loginName;
        var sessionId =  storage.sessionId;
        if(sessionId==""||sessionId==null){
        	window.location.href="login.html";
        }
		
		if(userType=="farmowner"){
        	this.setUrl = "userInfo.html";
        }else{
        	this.setUrl = "setInfo.html";
        }
		
        this.initData();
		this.realTimeVideo();        
    },
    watch:{
    	//监控视频类型
    	radio:function(curVal,oldVal){
    		 if(curVal!=oldVal){
            	switch (curVal){
                case "实时监控":
                	this.showCalendar = false;
                	this.realTimeVideo();
                    break;
                case "历史视频":
                	this.showCalendar = true;
                	this.historyVideo();
                    break;
            	}
    		}
    	},
    	//水果值变化
    	value:function(curVal,oldVal){
    		if(curVal!=oldVal){
    			if(this.showCalendar == true){
    				this.historyVideo();
    			}else{
    			}
    		}
    	},
    	//日期值变化
    	dateTime:function(curVal,oldVal){
    		if(curVal!=oldVal){
    			this.historyVideo();
    		}
    	}
    },
    methods: {
        handleSelect:function(key, keyPath) {
            console.log(key, keyPath);
        },
        initData:function(){
        	$.ajax({
                // 方式
                type: "post",
                // 是否异步
                async: true,

                data: {farmId:storage.farmId},
                // 路径||API
                url: "/agriculture/getBaseInfo.do",
                //返回数据形式为json
                dataType: "json",
                // 加载成功
                success: function(result) {
                	//基地信息
                    app.baseInfo = result;
                    app.areaVideo = result.areas;
                    app.value = result.areas[0].value;

                },
                // 加载错误
                error: function(errorMsg) {
                     alert("请求数据失败，请稍候重试!");
                }
            })
        },
        play:function(){
        	jwplayer.key='JN/mFkn660EKlGZWfWehT+CIdr9FvM5aTL+I3Q==';
            
            var station = this.baseInfo.baseId;
            var fruit = this.value;
            var thePlayer;  //保存当前播放器以便操作
            thePlayer = jwplayer('video').setup({
                flashplayer: '/agriculture/vue/assets/jwplayer-7.9.3/jwplayer.flash.swf',
                file: "http://example.com/videos/video_300k.mp4",
                width:1200,
                height:540,
                //align:center,
                dock: false
            });
        },
        logout:function(){
            var othis = this;
            this.$http.post("/agriculture/logout.do").then( function(res){
                console.log(res);
                window.localStorage.removeItem("userID");
                window.localStorage.removeItem("sessionId");
                window.location.href="login.html";
            });
        },
        realTimeVideo:function(){
        	this.play();
        },
        historyVideo:function(){
        	/*jQuery.ajax({
            type: 'post',
            data: {farmId:storage.farmId},
            url: "/agriculture/getBaseInfo.do",
            success: function (data) {
            		data = JSON.parse(data);
                    app.areaVideo = data.fruits;
                    app.value = data.fruits[0].value;
                    //基地信息
                    app.baseInfo = data.baseInfo;
            	},
            error: function (data){
                alert("请求数据失败，请检查网络!");
            	}
        	})*/
        	jwplayer.key='JN/mFkn660EKlGZWfWehT+CIdr9FvM5aTL+I3Q==';
            
        	var date = this.dateTime.getFullYear()+"-"+(this.dateTime.getMonth()+1)+"-"+this.dateTime.getDate();
            var station = this.baseInfo.baseId;
            var fruit = this.value;
            var videoname=station+"-"+fruit+"-"+date+".mp4";//ID+区域+日期
            var thePlayer;  //保存当前播放器以便操作
            thePlayer = jwplayer('video').setup({
                flashplayer: '/agriculture/vue/assets/jwplayer-7.9.3/jwplayer.flash.swf',
                file: '/video/'+videoname,
                width:1200,
                height:540,
                //align:center,
                dock: false
            });
        }
        	
        	
        //this.play();
    },
    });

   /*  app.$watch('value',function(val){
        app.$nextTick(function() {
            app.play();
        });
    })
    app.$watch('value1',function(val){
        app.$nextTick(function() {
            app.play();
        });
    }) */