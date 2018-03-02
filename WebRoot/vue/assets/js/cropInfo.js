
const storage = window.localStorage;
const userType = storage.userType;
Vue.http.options.emulateJSON = true;
var app = new Vue({
    el:'#app',
    data:{
        userName:"",
        //设置路径
        setUrl:"",
        //日期选择默认显示
        date_select_show:true,
        baseInfo:{
            baseId:"",
            baseName:"",
            fruits:"",
            baseArea:"",
            baseAddr:""
        },

        activeIndex: '2',
        //实时数据
        realTimeData:{
        	airTemp:"",
            airHumi:"",
            light:"",
            soilTemp:"",
            soilHumi:"",
            //实时数据的时间
            time:"",
        },
        
        //显示实时数据是否超出阈值
        compareToThreshold :{
        	airTempHigh:false,
            airTempLow:false,
            airHumiHigh:false,
            airHumiLow:false,
            lightHigh:false,
            lightLow:false,
            soilTempHigh:false,
            soilTempLow:false,
            soilHumiHigh:false,
            soilHumiLow:false,
        	},
        //昨日数据
        yesterdayData:[{
            yesterday:"昨日平均",
            yesAirTemp:"",
            yesAirHumi:"",
            yesLight:"",
            yesSoilTemp:"",
            yesSoilHumi:""
        },{
            yesterday:"昨日最高",
            yesAirTemp:"",
            yesAirHumi:"",
            yesLight:"",
            yesSoilTemp:"",
            yesSoilHumi:""
        },{
            yesterday:"昨日最低",
            yesAirTemp:"",
            yesAirHumi:"",
            yesLight:"",
            yesSoilTemp:"",
            yesSoilHumi:""
            }],
       
        //存放数据中间值
        interValue:{
        	 JStime:[],
        	 JSairTemp:[],
        	 JSairHumi:[],
        	 JSlight:[],
        	 JSsoilTemp:[],
        	 JSsoilHumi:[],
        	 //曲线标题
        	 airTempTitle:"空气温度",
             airHumiTitle:"空气湿度",
             lightTitle:"光照强度",
             soilTempTitle:"土壤温度",
             soilHumiTitle:"土壤湿度"
            },
        //echarts中option需要的数据
        seriesData: [],
        legendData:[],
        //历史数据按钮
        radio1:"日",
        radio2:"全部",

        //水果种类
        fruits:[],
        value: '',

        //默认是今天的日期
        dateTime:new Date(),

        pickerOptions0: {
            disabledDate:function(time) {
            	return time.getTime() > Date.now() ;
            }
        }

},
//初始化
created: function(){
    this.userName = storage.loginName;
    
    var sessionId =  storage.sessionId;
    if(sessionId==""||sessionId==null){
    	window.location.href="/agriculture/vue/login.html";
    }
    if(userType=="farmowner"){
    	this.setUrl = "userInfo.html";
    }else{
    	this.setUrl = "setInfo.html";
    }
    this.initFarmInfo();
    this.initDataLine();
    this.completeDataLine();
},
watch:{
    //要查询数据的范围监控
    radio1:function(curVal,oldVal){
    	
        if(curVal!=oldVal){
            switch (curVal){
                case "日":
                    this.date_select_show = true;
                    //this.dateTime = new Date();
                    this.dayDataLine();
                    break;
                case "周":
                    this.date_select_show = false;
                    this.weekDataLine();
                    break;
                case "月":
                    this.date_select_show = false;
                    this.monthDataLine();
                    break;
            }
        }
    },
    //要查看的数据监控
    radio2:function(curVal,oldVal){
        if(curVal!=oldVal){
            this.switchDataLine(curVal);
            this.drawGraph();
        }
    },
    //日期监控
    dateTime:function(curVal,oldVal){
        if(curVal!=oldVal){
            this.dayDataLine();
            this.switchDataLine(this.radio2);
        }
    },
    //水果值变化监控
    value:function(curVal,oldVal){
        if(curVal!=oldVal){
        	this.queryRealTimeData();
            switch (this.radio1){
                case "日":
                    this.dayDataLine();
                    this.switchDataLine(this.radio2);
                    break;
                case "周":
                    this.weekDataLine();
                    this.switchDataLine(this.radio2);
                    break;
                case "月":
                    this.monthDataLine();
                    this.switchDataLine(this.radio2);
                    break;
            }
        }
    }
},
methods:{
    handleSelect:function(key, keyPath) {
        console.log(key, keyPath);
    },
    //退出登录
    logout:function(){
        var othis = this;
        this.$http.post("/agriculture/logout.do").then( function(res){
            console.log(res);
            window.localStorage.removeItem("userID");
            window.localStorage.removeItem("sessionId");
            window.location.href="login.html";
        });
    },
    //初始化数据
    initDataLine:function(){
        jQuery.ajax({
            type: 'post',
            data: {farmId:storage.farmId,date:this.dateTime},
            url: "/agriculture/initLineData.do",
            success: function (data) {
            		if(data==null||data==""){
            			//alert("数据暂未加入监测，请稍候再试！");
            			return false;
            		}else{
            			data = JSON.parse(data);
            			//实时数据
            			app.realTimeData = data.realTimeData;
            			
            			//实时数据和阈值比较结果
            			app.compareToThreshold = data.compareToThreshold;
            			//今日数据
            			
            			app.dayData = data.dayData;
            			//赋给中间值
            			app.interValue.JStime = data.dayData.dayTime;
            			app.interValue.JSairTemp = data.dayData.dayAirTemp;
            			app.interValue.JSairHumi = data.dayData.dayAirHumi;
            			app.interValue.JSlight = data.dayData.dayLight;
            			app.interValue.JSsoilTemp = data.dayData.daySoilTemp;
            			app.interValue.JSsoilHumi = data.dayData.daySoilHumi;
            			
            			app.switchDataLine(this.radio2);
            			app.drawGraph();
            			
            			//昨日数据
            			app.yesterdayData = data.yesterdayData;
            		}
            		

            },
            error: function (data){
                alert("请求数据失败，请稍候重试!");
            }
        })
    },
    initFarmInfo:function(){
    	jQuery.ajax({
            type: 'post',
            data: {farmId:storage.farmId},
            url: "/agriculture/getBaseInfo.do",
            success: function (data) {
            		data = JSON.parse(data);
            		
            		 //基地信息
                    app.baseInfo = data;
                    
                    app.fruits = data.fruitsChecked;
                    app.value = data.fruitsChecked[0];
                    
            },
            error: function (data){
                alert("请求数据失败，请稍候重试!");
            }
        })
    },
    //改变曲线条数
    switchDataLine:function(lineType){
        switch(lineType){
            case "空气温度":this.airTempLineOnly();
                break;
            case "空气湿度":this.airHumiLineOnly();
                break;
            case "光照强度":this.lightLineOnly();
                break;
            case "土壤温度":this.soilTempLineOnly();
                break;
            case "土壤湿度":this.soilHumiLineOnly();
                break;
            default:this.completeDataLine();

        }
    },
    //间隔调用
  /*  setIntervalQuery:function(){
    	setInterval(function(){
    		this.queryRealTimeData();
    	},10);
    	
    },*/
    queryRealTimeData:function(){
    	$.ajax({
            // 方式
            type: "post",
            // 是否异步
            async: true,

            data: {farmId:storage.farmId,fruitName:this.value},
            // 路径||API
            url: "/agriculture/queryRealTimeData.do",
            //返回数据形式为json
            dataType: "json",
            // 加载成功
            success: function(result) {

            	app.realTimeData = result;

            },
            // 加载错误
            error: function(errorMsg) {
                 alert("请求数据失败，请稍候重试!");
            }
        })
    },
    //一天的数据曲线
    dayDataLine:function(){
        $.ajax({
            // 方式
            type: "post",
            // 是否异步
            async: true,

            data: {farmId:storage.farmId,date:this.dateTime,fruitName:this.value},
            // 路径||API
            url: "/agriculture/queryDayData.do",
            //返回数据形式为json
            dataType: "json",
            // 加载成功
            success: function(result) {
            	
                //if(result.code=="0"){
                    //今日数据
                 	
            		//app.dayData = result;
                    //赋给中间值
            		
            		app.interValue.JStime = result.dayTime;
            		app.interValue.JSairTemp = result.dayAirTemp;
            		app.interValue.JSairHumi = result.dayAirHumi;
            		app.interValue.JSlight = result.dayLight;
            		app.interValue.JSsoilTemp = result.daySoilTemp;
            		app.interValue.JSsoilHumi = result.daySoilHumi;
                	 
            		app.switchDataLine(this.radio2);
            		app.drawGraph();
            
                  /*}else{
                    this.$message.error(result.message);
                }*/

            },
            // 加载错误
            error: function(errorMsg) {
                 alert("请求数据失败，请稍候重试!");
            }
        })
    },
    //一周的数据曲线
    weekDataLine:function(){
        $.ajax({
            // 方式
            type: "post",
            // 是否异步
            async: true,

            data: {farmId:storage.farmId,fruitName:this.value},
            // 路径||API
            url: "/agriculture/queryWeekData.do",
            //返回数据形式为json
            dataType: "json",
            // 加载成功
            success: function(result) {
            //if(result.code=="0"){

            	//一周数据
                //app.weekData = result;
                //赋给中间值;
                app.interValue.JStime = result.weekTime;
                app.interValue.JSairTemp = result.weekAirTemp;
                app.interValue.JSairHumi = result.weekAirHumi;
                app.interValue.JSlight = result.weekLight;
                app.interValue.JSsoilTemp = result.weekSoilTemp;
                app.interValue.JSsoilHumi = result.weekSoilHumi;
            	 
                app.switchDataLine(this.radio2);
                app.drawGraph();
            	
                /*}else{
                this.$message.error(result.message);
                    }*/

            },
        // 加载错误
        error: function(errorMsg) {
            alert("请求数据失败，请稍候重试!");
                }
            })
    },
    //一个月的数据曲线
    monthDataLine:function(){
        $.ajax({
            // 方式
            type: "post",
            // 是否异步
            async: true,

            data: {farmId:storage.farmId,fruitName:this.value},
            // 路径||API
            url: "/agriculture/queryMonthData.do",
            //返回数据形式为json
            dataType: "json",
            // 加载成功
            success: function(result) {
               // if(result.code=="0"){

            	//一个月数据
                 // app.monthData = result;
                  //赋给中间值;
                  app.interValue.JStime = result.monthTime;
                  app.interValue.JSairTemp = result.monthAirTemp;
                  app.interValue.JSairHumi = result.monthAirHumi;
                  app.interValue.JSlight = result.monthLight;
                  app.interValue.JSsoilTemp = result.monthSoilTemp;
                  app.interValue.JSsoilHumi = result.monthSoilHumi;
                  
                  app.switchDataLine(this.radio2);
                  app.drawGraph();
              	
                    /*}else{
                    this.$message.error(result.message);
                }*/

            },
            // 加载错误
            error: function(errorMsg) {
                alert("请求数据失败，请稍候重试!");
            }
        })
    },
    //显示全部数据曲线
    completeDataLine:function(){
    	legendData = [this.interValue.airTempTitle,this.interValue.airHumiTitle,this.interValue.lightTitle,
    	              this.interValue.soilTempTitle,this.interValue.soilHumiTitle];
    	seriesData = [{
	        name: this.interValue.airTempTitle,
	        type: 'line',
	        data: this.interValue.JSairTemp
	   		},{
	            name: this.interValue.airHumiTitle,
	            type: 'line',
	            data: this.interValue.JSairHumi
	        },{
	            name: this.interValue.lightTitle,
	            type: 'line',
	            data: this.interValue.JSlight
	        },{
	            name: this.interValue.soilTempTitle,
	            type: 'line',
	            data: this.interValue.JSsoilTemp
	        },{
	            name: this.interValue.soilHumiTitle,
	            type: 'line',
	            data: this.interValue.JSsoilHumi
	        }];
    	
    },
    //只显示空气温度曲线
    airTempLineOnly:function(){
    	legendData = [this.interValue.airTempTitle];
    	seriesData = [{
	        name: this.interValue.airTempTitle,
	        type: 'line',
	        data: app.interValue.JSairTemp
	   		}];
    },	
    //只显示空气湿度
    airHumiLineOnly:function(){
    	legendData = [this.interValue.airHumiTitle];
        seriesData = [{
	            name: this.interValue.airHumiTitle,
	            type: 'line',
	            data: this.interValue.JSairHumi
	        }];
    },
    //只显示光照强度
    lightLineOnly:function(){
    	legendData = [this.interValue.lightTitle];
    	seriesData = [{
	            name: this.interValue.lightTitle,
	            type: 'line',
	            data: this.interValue.JSlight
	        }];
    },
    //只显示土壤温度
    soilTempLineOnly:function(){
    	legendData = [this.interValue.soilTempTitle];
    	seriesData = [{
	            name: app.interValue.soilTempTitle,
	            type: 'line',
	            data: this.interValue.JSsoilTemp
	        }];
    },
    //只显示土壤湿度
    soilHumiLineOnly:function(){
    	legendData = [this.interValue.soilHumiTitle];
    	seriesData = [{
	            name: this.interValue.soilHumiTitle,
	            type: 'line',
	            data: this.interValue.JSsoilHumi
	        }];
    },
    drawGraph:function(){
    	
    	var myChart = echarts.init(document.getElementById('historycharts'));

    	// 指定图表的配置项和数据
    	var option = {
    	    title: {
    	        text: '农场数据'
    	    },
    	    tooltip: {//提示
    	        trigger:'axis',
    	        show: true,
    	        formatter: function (params) {//params和echarts的series是关联的
    	            //这里数组的个数取决于series的个数，当series只有一个时，params也就是只有一个对象的数组。
    	            var res='<div><p>时间:'+params[0].name+'</p></div>' //显示日期和时间
    	            for(var i=0;i<params.length;i++){
    	                res+='<p>'+params[i].seriesName+':'+params[i].data+'</p>'
    	            }
    	            return res;

    	        },
    	    },
    	    toolbox: { //可视化的工具箱
    	        show: true,
    	        feature: {
    	            dataView: { //数据视图
    	                show: true
    	            },
    	            restore: { //重置
    	                show: true
    	            },
    	            dataZoom: { //数据缩放视图
    	                show: true
    	            },
    	            saveAsImage: {//保存图片
    	                show: true
    	            },
    	            magicType: {//动态类型切换
    	                show:true,
    	                type: ['line','bar']
    	            }
    	        }
    	    },

    	    legend: {
    	        data:legendData
    	    },
    	    xAxis: {
    	        data: app.interValue.JStime,
    	        splitLine:{show: false},//去除网格线
    	    },
    	    yAxis: {
    	        splitLine:{show: false},//去除网格线
    	        splitArea:{show:true}//保留网格区域
    	         },
    	    series: seriesData
    	};
    	// 使用刚指定的配置项和数据显示图表。
    	myChart.setOption(option);
    }
},
mounted: function(){
	// “将回调延迟到下次 DOM 更新循环之后执行。在修改数据之后立即使用它，然后等待 DOM 更新。”  
	this.$nextTick(function() {  
        app.drawGraph();  
        setInterval(function(){
    		app.queryRealTimeData();
    	},120000);
	
	})  
}
});

