function map_init() {
    var map = new BMap.Map("map"); //创建Map实例
    var point = new BMap.Point(markerArr[0].lantitude, markerArr[0].longitude); //地图中心点
    map.centerAndZoom(point, 13); //  初始化地图,设置中心点坐标和地图级别
    map.enableScrollWheelZoom(true); //启用滚轮放大缩小  
    //向地图中添加缩放控件  
    var ctrlNav = new window.BMap.NavigationControl({
        anchor: BMAP_ANCHOR_TOP_LEFT,
        type: BMAP_NAVIGATION_CONTROL_LARGE
    });
    map.setCurrentCity("南宁");
    map.addControl(ctrlNav);
    map.addControl(new BMap.OverviewMapControl()); // 缩略地图控件
    map.addControl(new BMap.MapTypeControl()); // 地图类型控件
    
    //向地图中添加缩略图控件  
    var ctrlOve = new window.BMap.OverviewMapControl({
        anchor: BMAP_ANCHOR_BOTTOM_RIGHT,
        isOpen: 1
    });
    
    map.addControl(ctrlOve);

    //向地图中添加比例尺控件
    var ctrlSca = new window.BMap.ScaleControl({
        anchor: BMAP_ANCHOR_BOTTOM_LEFT
    });
    map.addControl(ctrlSca);

    var point ; //存放标注点经纬信息
    var marker;  //存放标注点对象
    var info ; //存放提示信息窗口对象

    for (var i = 0; i < markerArr.length; i++) {
        (function(x){//使用了一个匿名函数，从而造成一个闭包将 i 的值锁定在里面，这样外部的值已经变化，

            var p0 = markerArr[i].lantitude; //   经度
            var p1 = markerArr[i].longitude; //纬度
            point = new window.BMap.Point(p0, p1); //循环生成新的地图点
            marker = new window.BMap.Marker(point); //按照地图点坐标生成标记 
            map.addOverlay(marker);
            marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画  
           
            var label = new window.BMap.Label(markerArr[i].baseName,
                { offset: new window.BMap.Size(20, -10) });

            marker.setLabel(label);
            
            /*var imageurl=markerArr[i].imageurl;//照片地址*/
            var imageid=markerArr[i].baseId;//照片id
             //提示窗口
            info = "<p style='font-size:12px;lineheight:1.8em;'><a id='info'><img id='"+imageid+"'"
            	+"width='80' height='60'></img></a>"+ markerArr[i].baseName +"</br>基地号： "
                +markerArr[i].baseId+"</br>国家： "+markerArr[i].country+"</br>省份： " +markerArr[i].province
                +"</br>城市： "+markerArr[i].city+"</br>所在区或县： "+markerArr[i].district+"</br>基地地址： " 
                + markerArr[i].baseAddr + "</br>基地面积： " + markerArr[i].baseArea + "</br></p>";
            
            // 创建信息窗口对象  
            var info_window=new window.BMap.InfoWindow(info);

            //////////////////////////////
            ///////////鼠标移动事件//////////
            /////////////////////////////

            var x=10;
            var y=20;

            marker.addEventListener("mouseover", function () {
                this.openInfoWindow(info_window);

                ////////////////////////////////////
                ///////鼠标移动到图片上，图片放大显示/////////
                ///////////////////////////////////

                $("#info").mouseover(function(e){
                    var tooltip = "<div id='tooltip' style='position:absolute;z-index: 9999; width: 280px;"
                        +"height: 300px;display:none;text-align:right'>基地图片<img src='" +
                        "' style='width: 500px;height: 450px;'/><div>";//创建 div 元素  	
                    $("body").append(tooltip); //把它追加到文档中
                    $("#map").css("opacity","0.4");
                    $("#tooltip").css({
                        "top": (e.pageY + y) + "px",
                        "left": (e.pageX + x) + "px",
                        "display":"block"
                    }).show("fast");  //设置x坐标和y坐标，并且显示  
                }).mouseout(function () {
                    $("#tooltip").remove();  //移除  
                    $("#map").css("opacity","1");
                }).mousemove(function (e) {
                    $("#tooltip")
                        .css({
                            "top": (e.pageY + y) + "px",
                            "left": (e.pageX + x) + "px"
                        });
                    
                });
                //////////////////////////
                /////////////////////////
            });
           // var Url_="/agriculturePlatform/query/baseList";

            /////////////////////////////////
            /////////鼠标点击定位图标事件//////////
            ////////////////////////////////

           /* marker.addEventListener("click", function () {
                window.location.assign(encodeURI(Url_));//这里不能直接写上面的url，不能解析，无法跳转；
            });*/

        })(i);
    }

}

//异步调用百度地图
function map_load() {
    var load = document.createElement("script");
    load.src = "http://api.map.baidu.com/api?v=1.4&callback=map_init";
    document.body.appendChild(load);
}
window.onload = map_load;
