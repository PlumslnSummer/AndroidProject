/***********************************************************************************
 * 文件：EnergyManagement（智能远程抄表系统）
 *
 * 作者：你请不动的电表队      2021.10.12
 *
 * 说明：主js包括导航与按钮功能的实现和数据的连接，实现整个系统的功能
 * *********************************************************************************/

// 定义本地存储参数
var localData = {
    ID : config["id"],
    KEY : config["key"],
    realTimeData : config["server"],
//    ammeterMAC : "00:00:00:00:00:00:00:00",
    ammeterMAC : "01:12:4B:00:4C:F4:59:56",
    socketMAC1: "01:12:4B:00:1E:0C:BA:46",
    socketMAC2: "01:12:4B:00:EE:28:AE:BC",
    energyRange : "500",    //用电量
    powerRange : "3500"      //功率
};
$(function () {
    // 记录上一次用电量
    var energyArr = [];
	// 初始化表盘
	power(0);
    var curMode = '手动模式';
    var connectFlag = 0, macFlag = 0;
    var rtc = 0;
    var energyUsed = 0, powerNew = 0;
    var powerRangeOut = 1, energyRangeOut = 1;
    var relayData, relayFlag = 0;
    var flag_news = 0, flag_news2 = 0;

    var x = window.innerHeight;
    var y = 340;
    var ejectTop = (x-y)/2;
    var time_x=(new Date()).getTime();
    //定义插座的电量和功率/////////////////////////
    var strip_quantity=0,strip_power=0,strip_quantity2=0,strip_power2=0;
    var strip_quantity_now=0,strip_power_now=0,strip_quantity2_now=0,strip_power2_now=0;
    var total_quantity_now=0,total_power_now=0;
    //总用电量和功率////////////////////////
    var total_quantity=0,total_power=0,month_quantity=0;
    var energyUsed_now=0,powerNew_now=0;
    var month_quantity_now=0;
    var time_now=0;

    document.getElementById("eject").style.top = ejectTop + "px";
    console.log(rtc);

    // 获取本地存储的id key server
    get_localStorage();

    // 自动连接函数调用
    newConnect();

    //设置阈值
    $("#inputRange").click(function () {
        localData.energyRange = $("#energyRange").val();
        localData.powerRange = $("#powerRange").val();
        // 本地存储电量阀值和功率阀值
        storeStorage();
        if (connectFlag == 1) {
            console.log(localData.energyRange);
            console.log(localData.powerRange);
            if(macFlag)
            {
                rtc.sendMessage(localData.ammeterMAC, "{A0=?,A1=?}");
                //A1用电功率，A0用电量//////////////////////////////////
                rtc.sendMessage(localData.socketMAC1,"{A0=?,A1=?}");
                rtc.sendMessage(localData.socketMAC2,"{A0=?,A1=?}");
            }
            message_show("设置成功");
        }
        else {
            message_show("请正确输入IDKEY连接智云数据中心");
        }
    });

    //id与key确认事件
    $("#inputID").on("click", function () {
        localData.ID = $("#ID").val()?$("#ID").val():localData.ID;
        localData.KEY = $("#KEY").val()?$("#KEY").val():localData.KEY;
        localData.realTimeData = $("#realTimeData").val()?$("#realTimeData").val():localData.realTimeData;
        $("#inputMac").click();
        storeStorage();
        if(!connectFlag)
            newConnect();
        else
            rtc.disconnect();
    });

    //模式切换
    $(".dropdown-menu li a").on("click", function () {
        var mode = $.trim($("#control_select").text());
        curMode = $(this).text();
        $("#curMode").text(curMode);
        $(this).text(mode);
        console.log('当前模式='+ curMode);
    });

    // 数据连接
    function newConnect() {
        localData.ID = $("#ID").val() ? $("#ID").val():localData.ID;
        localData.KEY = $("#KEY").val() ? $("#KEY").val():localData.KEY;
        localData.realTimeData = $("#realTimeData").val() ?
                                 $("#realTimeData").val() : localData.realTimeData;
        // 本地存储id、key和server
        storeStorage();

        rtc = new WSNRTConnect(localData.ID, localData.KEY);
        rtc.setServerAddr(localData.realTimeData + ":28080");
        rtc.connect();

        //连接成功回调函数
        rtc.onConnect = function () {
            $("#ConnectState").text("数据服务连接成功！");
            connectFlag = 1;
            message_show("数据服务连接成功！");
            if (connectFlag) {
                $("#inputID").text("断开").addClass("btn-primary");
                macFlag = 1;
            } else {
                $("#inputID").text("连接").removeClass("btn-primary");
                macFlag = 0;
                message_show("请正确输入ID、KEY和mac地址连接智云数据中心");
            }
        };

        //数据服务掉线回调函数
        rtc.onConnectLost = function () {
            connectFlag = 0;
            $("#ConnectState").text("数据服务未启动！");
            $("#inputID").text("连接").removeClass("btn-danger");
            message_show("数据服务连接失败，请检查网络或IDKEY");
        };

        //消息处理回调函数
        rtc.onmessageArrive = function (mac, dat) {
            if (mac == localData.ammeterMAC) {
                if (dat[0] == '{' && dat[dat.length - 1] == '}') {
                    dat = dat.substr(1, dat.length - 2);
                    var its = dat.split(',');
                    for (var x in its) {
                        var t = its[x].split('=');
                        if (t.length != 2) continue;
                        if (t[0] == "A0") {
                            flag_news2 = 1;
                            /////////////////////////
                              total_power_now=parseInt(strip_power_now)+parseInt(strip_power2_now);

                             var time_now=(new Date()).getTime();
                             month_quantity=month_quantity+(total_power_now/1000)*((time_now-time_x)/1000/60/60);
                            month_quantity_now=month_quantity.toFixed(4);

                                 energyUsed=parseInt(month_quantity_now);
                             ////////////////////////////
							drawDial(parseInt(t[1]));/////值未改
                            if(curMode == '自动模式') {
                                if (energyUsed > localData.energyRange) {
                                    energyRangeOut = 0;
                                    $("#socket_img").attr("src", "images/strip_off.png");
                                    $("#socketCtrl").text("打开");
                                    rtc.sendMessage(localData.socketMAC1, "{CD1=1,D1=?}");
                                    rtc.sendMessage(localData.socketMAC2, "{CD1=1,D1=?}");
                                    message_show("用电量已超出，已关闭插座");
                                }
                            }
                        }
                        if (t[0] == "A3") {
                             ////////////
                                flag_news = 1;
                                total_power_now=parseInt(strip_power_now)+parseInt(strip_power2_now);
                                powerNew=parseInt(total_power_now);//功率赋值
                             /////////////////

                            if(curMode == "自动模式") {
                                if (powerNew > localData.powerRange) {
                                    powerRangeOut = 0;
                                    $("#socket_img").attr("src", "images/strip_off.png");
                                    $("#socketCtrl").text("打开");
                                    rtc.sendMessage(localData.socketMAC1, "{CD1=1,D1=?}");
                                    rtc.sendMessage(localData.socketMAC2, "{CD1=1,D1=?}");
                                    message_show("当前功率过大，已关闭插座");
                                }
                            }
                        }
                    }
                }
            }
            if (mac == localData.socketMAC1) {
                if (dat[0] == '{' && dat[dat.length - 1] == '}') {
                    dat = dat.substr(1, dat.length - 2);
                    var its = dat.split(',');
                    for (var x in its) {
                        var t = its[x].split('=');
                        if (t.length != 2) continue;
                         ////////////////
                          if (t[0] == "A0") {
                            strip_quantity=t[1];
                           }
                        if (t[0] == "A1") {
                             strip_power=t[1];
                         }
                        ////////////////

                        if (t[0] == "D1") {
                            relayData = parseInt(t[1]);
                            console.log("relayData="+relayData)
                            if (relayData == 1) {
                                relayFlag = 0;
                                $("#socket-img1").attr("src", "images/strip_on.png");
                                $("#stripCtrl").text("关闭");
                                 ////////////////////////
                                 $("#strip_quantity").text(strip_quantity + " kWh");
                                 $("#strip_power").text(strip_power + " W");

                                 strip_quantity_now=strip_quantity;
                                 strip_power_now=strip_power;
                                ////////////////////////
                                message_show("空调已打开");
                            }
                            else if (relayData == 0) {
                                relayFlag = 1;
                                $("#socket-img1").attr("src", "images/strip_off.png");
                                $("#stripCtrl").text("打开");
                                strip_quantity_now = 0;
                                strip_power_now = 0;
                                strip_quantity = 0;
                                strip_power = 0;
                                $("#strip_quantity").text(strip_quantity + " kWh");
                                $("#strip_power").text(strip_power+" W");
                                message_show("空调已关闭");
                            }
                        }
                    }
                }
            }

            if (mac == localData.socketMAC2) {
                if (dat[0] == '{' && dat[dat.length - 1] == '}') {
                    dat = dat.substr(1, dat.length - 2);
                    var its = dat.split(',');
                    for (var x in its) {
                        var t = its[x].split('=');
                        if (t.length != 2) continue;
                          ////////////////插座二

                            if (t[0] == "A0") {
                               strip_quantity2=t[1];
                            }
                            if (t[0] == "A1") {
                                strip_power2=t[1];
                            }
                            ////////////////
                        if (t[0] == "D1") {
                            relayData = parseInt(t[1]);
                            console.log("relayData="+relayData)
                            if (relayData == 1) {
                                relayFlag = 0;
                                $("#socket-img2").attr("src", "images/strip_on2.png");
                                $("#stripCtrl2").text("关闭");
                                   ///////////////////////
                                     $("#strip_quantity2").text(strip_quantity2 + " kWh");
                                     $("#strip_power2").text(strip_power2 + " W");
                                     strip_quantity2_now=strip_quantity2;
                                     strip_power2_now=strip_power2;
                                    //////////////////
                                message_show("冰箱已打开");
                            }
                            else if (relayData == 0) {
                                relayFlag = 1;
                                $("#socket-img2").attr("src", "images/strip_off2.png");
                                $("#stripCtrl2").text("打开");
                                strip_quantity2_now = 0;
                                strip_power2_now = 0;
                                strip_quantity2 = 0;
                                strip_power2 = 0;
                                $("#strip_quantity2").text(strip_quantity2 + " kWh");
                                $("#strip_power2").text(strip_power2+" W");
                                message_show("冰箱已关闭");
                            }
                        }
                    }
                }
            }
                //////////////////////
                total_quantity_now=parseInt(strip_quantity_now)+parseInt(strip_quantity2_now);
                 total_power_now=parseInt(strip_power_now)+parseInt(strip_power2_now);

                 energyUsed_now=total_quantity_now;
                 powerNew_now=total_power_now;
                 if(energyArr.length>2)
                         energyArr.shift();
                 else
                         energyArr.push(total_quantity_now)
                   $("#quantity").text(total_quantity_now + " kWh");
                   console.log('0:'+energyArr[0]+',1:'+energyArr[1])
                   if(energyArr.length>0)
                     if(energyArr[0]!=energyArr[1])
                        power(parseInt(total_quantity_now));
                  $("#quantity").text(total_quantity_now + " kWh");
                  power(total_quantity_now);

                  $("#power").text(total_power_now + " W");
                    time_now=(new Date()).getTime();
                    month_quantity=month_quantity+(total_power_now/1000)*((time_now-time_x)/1000/60/60);
                    month_quantity_now=month_quantity.toFixed(4);
                    window.android.get(month_quantity_now);
                   $("#elect").text(month_quantity_now + " kWh");

                ////////////////////
        }
    }

    function draw(){
        setInterval(function () {
                            localData.ammeterMAC = $("#ammeterMAC").val();
                            localData.socketMAC1 = $("#socketMAC1").val();
                            localData.socketMAC2 = $("#socketMAC2").val();
                            // 本地存储mac地址
                            storeStorage();
                            console.log("flag" + connectFlag);
                            if (connectFlag == 1) {
                                rtc.sendMessage(localData.ammeterMAC, "{A0=?,A1=?,A2=?,A3=?}");
                                console.log(localData.ammeterMAC);
                                rtc.sendMessage(localData.socketMAC1,"{A0=?,A1=?}");
                                rtc.sendMessage(localData.socketMAC2,"{A0=?,A1=?}");
                                console.log(localData.socketMAC1);
                                macFlag = 1;
//                                message_show("设置成功");
                            }
                            else {
                                macFlag = 0;
                                message_show("请正确输入IDKEY连接智云数据中心");
                            }
                        }, 10000);
    }
    draw();

    //打开智能插排
    $("#stripCtrl").click(function () {
        if (connectFlag == 1) {
            if (curMode == "手动模式") {
                if(relayFlag) {
                    rtc.sendMessage(localData.socketMAC1, "{OD1=1,D1=?}");
                } else {
                    rtc.sendMessage(localData.socketMAC1, "{CD1=1,D1=?}");
                }
            }
            else {
                message_show("请在手动模式下操作")
            }
        }
        else {
            message_show("请正确输入IDKEY连接智云数据中心");
        }
    });

    $("#stripCtrl2").click(function () {
        if (connectFlag == 1) {
            if (curMode == "手动模式") {
                if(relayFlag) {
                    rtc.sendMessage(localData.socketMAC2, "{OD1=1,D1=?}");
                } else {
                    rtc.sendMessage(localData.socketMAC2, "{CD1=1,D1=?}");
                }
            }
            else {
                message_show("请在手动模式下操作")
            }
        }
        else {
            message_show("请正确输入IDKEY连接智云数据中心");
        }
    });

    //动态折线图
    $("#powerChart").highcharts({
                             chart: {
                                 type: 'column',
                                 animation: Highcharts.svg,
                                 marginRight: 10,
                                 events: {
                                     load: function () {
                                         var series = this.series[0];
                                         setInterval(function () {
                                             var x = (new Date()).getTime() + 28800*1000;
                                             var y = powerNew_now;
                                             console.log(y);
                                             if (flag_news == 1) {
                                                 series.addPoint([x, y], true, true);
                                             }
                                             flag_news = 0;
                                         }, 2000);
                                     }
                                 }
                             },
                             title: {
                                 text: '家庭用电功率柱形图'
                             },
                             xAxis: {
                                 type: 'datetime',
                                 tickPixelInterval: 150
                             },
                             yAxis: {
                                 title: {
                                     text: '功率（W）'
                                 },
                                 plotLines: [{
                                     value: 0,
                                     width: 1,
                                     color: '#808080'
                                 }]
                             },
                             plotOptions: {
                             	column: {
                             		pointWidth: 30,
                             		dataLabels: {
                                                 enabled: true
                                             }
                             	}
                             },
                             tooltip: {
                                 formatter: function () {
                                     return '<b>' + this.series.name + '</b><br/>' +
                                         Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                                         Highcharts.numberFormat(this.y, 2);
                                 }
                             },
                             legend: {
                                 enabled: false
                             },
                             exporting: {
                                 enabled: false
                             },
                             series: [{
                                 name: '当前功率',
                                 data: (function () {
                                     var data = [],
                                         time = (new Date()).getTime() + 28800*1000,
                                         i;
                                     for (i = -59; i <= 0; i += 1) {
                                         data.push({
                                             x: time + i * 1000,
                                             y: null
                                         });
                                     }
                                     return data;
                                 }())
                             }]
                         });

    $("#energyChart").highcharts({
                             chart: {
                                 type: 'column',
                                 animation: Highcharts.svg,
                                 marginRight: 10,
                                 events: {
                                     load: function () {
                                         var series = this.series[0];
                                         setInterval(function () {
//                                          rtc.sendMessage(localData.socketMAC1,"{A0=?,A1=?}");
//                                          rtc.sendMessage(localData.socketMAC2,"{A0=?,A1=?}");
                                             var x = (new Date()).getTime() + 28800*1000;
                                             var y = energyUsed_now;
                                             if (flag_news2 == 1) {
                                                 series.addPoint([x, y], true, true);
                                             }
                                             flag_news2 = 0;
                                         }, 2000);
                                     }
                                 }
                             },
                             title: {
                                 text: '家庭用电量柱形图'
                             },
                             xAxis: {
                                 type: 'datetime',
                                 tickPixelInterval: 150
                             },
                             yAxis: {
                                 title: {
                                     text: '当前用电量（kWh）'
                                 },
                                 plotLines: [{
                                     value: 0,
                                     width: 1,
                                     color: '#808080'
                                 }]
                             },
                             plotOptions: {
                                     	column: {
                                     		pointWidth: 30,
                                     		dataLabels: {
                                                         enabled: true
                                                     }
                                     	}
                             },
                             tooltip: {
                                 formatter: function () {
                                     return '<b>' + this.series.name + '</b><br/>' +
                                         Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                                         Highcharts.numberFormat(this.y, 2);
                                 }
                             },
                             legend: {
                                 enabled: false
                             },
                             exporting: {
                                 enabled: false
                             },
                             series: [{
                                 name: '当前用电量',
                     			// type: 'bar',//柱图宽度
                                 data: (function () {
                                     var data = [],
                                         time = (new Date()).getTime() + 28800*1000,
                                         i;
                                     for (i = -59; i <= 0; i += 1) {
                                         data.push({
                                             x: time + i * 1000,
                                             y:null
                                         });
                                     }
                                     return data;
                                 }())
                             },
                     		{
                     			barWidth: 3
                     		}]
                         });


});


// 获取本地localStorage缓存数据
function get_localStorage(){
    if(localStorage.getItem("energyManagement")){
        localData = JSON.parse(localStorage.getItem("energyManagement"));
        console.log("localData="+localData);
        for(var i in localData){
            if(localData[i]!=""){
                eval("$('#"+i+"').val(localData."+i+")");
                console.log("i="+i+";;  data1:"+localData[i]);
            }
        }
    }
    else{
        get_config();
    }
}

//显示
function display() {
    document.getElementById("eject-bg").style.display = "block";
    document.getElementById("eject").style.display = "block";
}

//隐藏
function hide() {
    document.getElementById("eject-bg").style.display = "none";
    document.getElementById("eject").style.display = "none";
}

function get_config(){
    $("#ID").val(localData.ID);
    $("#KEY").val(localData.KEY);
    $("#realTimeData").val(localData.realTimeData);
}

// 点击分享生成二维码
function share(){
    var txt, title, input, obj;
    obj = {
        "id" : $("#ID").val(),
        "key" : $("#KEY").val(),
        "server" : $("#realTimeData").val(),
        "mac" : $("#ammeterMAC").val(),
    }
    // txt = "ID:"+$("#ID").val()+",KEY:"+$("#KEY").val()+",server:"+$("#server").val();
    title = "IDKey";
    txt = JSON.stringify(obj);
    qrcode.makeCode(txt);
    $("#shareModalTitle").text(title);
    var txt = "id:"+$("#ID").val()+",key:"+$("#KEY").val()+",realTimeData:"+$("#realTimeData").val()+",mac:"+$("#ammeterMAC").val();
}


// 扫描处理函数
function scanQR(scanData){
    var dataJson = {};
    if (scanData[0]!='{') {
        var data = scanData.split(',');
        for(var i=0;i<data.length-1;i++){
            var newdata = data[i].split(":");
            dataJson[newdata[0].toLocaleLowerCase()] = newdata[1];
        }
    }else{
        dataJson = JSON.parse(scanData);
    }
    $("#ID").val(dataJson['id']);
    $("#KEY").val(dataJson['key']);
    if(dataJson['server']&&dataJson['server']!=''){
        $("#realTimeData").val(dataJson['server']);
    }
    $("#ammeterMAC").val(dataJson['mac']);

}
// 重置按钮
$("#cameraReset").on("click", function () {
    $(this).parents(".form-horizontal").find("input").val(" ");
    $("#cameraServer").focus();
})
// 定义二维码生成div
var qrcode = new QRCode(document.getElementById("qrDiv"), {
    width : 150,
    height : 150
});

//消息弹出框
var message_timer = null;
function message_show(t) {
    if (message_timer) {
        clearTimeout(message_timer);
    }
    message_timer = setTimeout(function () {
        $("#toast").hide();
    }, 3000);
    $("#toast_txt").text(t);
    $("#toast").show();
}

function getback(){
    $("#backModal").modal("show");
}

function confirm_back(){
    window.droid.confirmBack();
}
function storeStorage(){
    localStorage.setItem("energyManagement",JSON.stringify(localData));
}
function clearLocalstorage(){
    localStorage.removeItem("energyManagement");
    alert("localStorage已清除!")
    window.location.reload();
}