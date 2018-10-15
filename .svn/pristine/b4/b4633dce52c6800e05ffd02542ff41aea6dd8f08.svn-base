//项目英文名
var web_home="/hotel/";
//项目中文名
var web_name="测试应用";
//服务器地址
//var web_url="http://192.168.3.49:8080/";
var web_url="";

//兼容ie低版本json
if (!this.JSON) {
	JSON = function() {
		function f(n) {
			return n < 10 ? '0' + n : n;
		}
		Date.prototype.toJSON = function() {
			return this.getUTCFullYear() + '-' + f(this.getUTCMonth() + 1)
					+ '-' + f(this.getUTCDate()) + 'T' + f(this.getUTCHours())
					+ ':' + f(this.getUTCMinutes()) + ':'
					+ f(this.getUTCSeconds()) + 'Z';
		};
		var m = {
			'\b' : '\\b',
			'\t' : '\\t',
			'\n' : '\\n',
			'\f' : '\\f',
			'\r' : '\\r',
			'"' : '\\"',
			'\\' : '\\\\'
		};
		function stringify(value, whitelist) {
			var a, // The array holding the partial texts.
			i, // The loop counter.
			k, // The member key.
			l, // Length.
			r = /["\\\x00-\x1f\x7f-\x9f]/g, v; // The member value.
			switch (typeof value) {
			case 'string':
				return r.test(value) ? '"'
						+ value.replace(r, function(a) {
							var c = m[a];
							if (c) {
								return c;
							}
							c = a.charCodeAt();
							return '\\u00' + Math.floor(c / 16).toString(16)
									+ (c % 16).toString(16);
						}) + '"' : '"' + value + '"';
			case 'number':
				return isFinite(value) ? String(value) : 'null';
			case 'boolean':
			case 'null':
				return String(value);
			case 'object':
				if (!value) {
					return 'null';
				}
				if (typeof value.toJSON === 'function') {
					return stringify(value.toJSON());
				}
				a = [];
				if (typeof value.length === 'number'
						&& !(value.propertyIsEnumerable('length'))) {
					l = value.length;
					for (i = 0; i < l; i += 1) {
						a.push(stringify(value[i], whitelist) || 'null');
					}
					return '[' + a.join(',') + ']';
				}
				if (whitelist) {
					l = whitelist.length;
					for (i = 0; i < l; i += 1) {
						k = whitelist[i];
						if (typeof k === 'string') {
							v = stringify(value[k], whitelist);
							if (v) {
								a.push(stringify(k) + ':' + v);
							}
						}
					}
				} else {
					for ( var k in value) {
						if (typeof k === 'string') {
							v = stringify(value[k], whitelist);
							if (v) {
								a.push(stringify(k) + ':' + v);
							}
						}
					}
				}
				return '{' + a.join(',') + '}';
			}
		}

		return {
			stringify : stringify,
			parse : function(text, filter) {
				var j;
				function walk(k, v) {
					var i = 0, n;
					if (v && typeof v === 'object') {
						for (i in v) {
							if (Object.prototype.hasOwnProperty.apply(v, [ i ])) {
								n = walk(i, v[i]);
								if (n !== undefined) {
									v[i] = n;
								}
							}
						}
					}
					return filter(k, v);
				}
				if (/^[\],:{}\s]*$/
						.test(text
								.replace(/\\./g, '@')
								.replace(
										/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(:?[eE][+\-]?\d+)?/g,
										']')
								.replace(/(?:^|:|,)(?:\s*\[)+/g, ''))) {

					j = eval('(' + text + ')');
					return typeof filter === 'function' ? walk('', j) : j;
				}
				throw new SyntaxError('parseJSON');
			}
		};
	}();
}

var common={
	//获取当前网址,如： http://localhost:8080/Tmall/index.jsp 
	curWwwPath:function(){
		return window.document.location.href;
	},
	
	//获取主机地址之后的目录如：/Tmall/index.jsp 
	pathName:function(){
		var pathName=window.document.location.pathname;
		return common.curWwwPath().indexOf(pathName); 
	},
	
	//获取主机地址，如： http://localhost:8080  
	localhostPaht:function(){
		return curWwwPath.substring(0,common.pathName()); 
	},
	
	//获取带"/"的项目名，如：/Tmall 
	projectName:function(){
		var pathName=window.document.location.pathname; 
		return pathName.substring(0,pathName.substr(1).indexOf('/')+1)+"\/"; 
	},
	
	/**
	 * ajax请求
	 * 新一代主流浏览器ajax技术请求(h5)
	 * @parqam url 请求路径
	 * @parqam _data 请求数据
	 * @parqam funsuc 成功回调
	 * @parqam _dataType 数据格式（默认json格式）
	 */
	ajaxExcute:function(_url,_data,funsuc,_config){
		try{
			var default_config={
	            type:"post",
	            url:_url,
	            data:_data,
	            async: false,
                cache: true,
                contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                global: true,
                ifModified: false,
                dataType:"JSON",//数据格式类型
	            success:function(ret){
	            	funsuc(ret);
	            },
	            //调用出错执行的函数
	            error: function(err){
	                //请求出错处理
	            }         
			};
			if (common.isNotBlank(_config)) {
	            for (var item in _config) {
	            	default_config[item] = _config[item];
	            }
	        }
			$.ajax(default_config);
		}catch(e){
			console.error("错误消息:"+e);
			return;
		}
	},
	
	//创建Xhr对象
	createXHR:function() {
		var xmlHttpRequest = null;
		if (window.XMLHttpRequest) {
			try {
				xmlHttpRequest = new XMLHttpRequest();
			} catch (e) {
				 console.error(e.message);
			}
		} else if (this.xmlHttpNameCache != null) {
			xmlHttpRequest = new ActiveXObject(this.xmlHttpNameCache);
		} else {
			var MSXML = [ 'MSXML2.XMLHTTP.6.0', 'MSXML2.XMLHTTP.5.0',
					'MSXML2.XMLHTTP.4.0', 'MSXML2.XMLHTTP.3.0',
					'MsXML2.XMLHTTP.2.6', 'MSXML2.XMLHTTP',
					'Microsoft.XMLHTTP.1.0', 'Microsoft.XMLHTTP.1',
					'Microsoft.XMLHTTP' ];
			var n = MSXML.length;
			for ( var i = 0; i < n; i++) {
				try {
					xmlHttpRequest = new ActiveXObject(MSXML[i]);
					this.xmlHttpNameCache = MSXML[i];
					break;
				} catch (e) {
					xmlHttpRequest = null;
				}
			}
			if (xmlHttpRequest == null) {
				console.error("创建XMLHTTP请求连接失败");
			}
		}
		return xmlHttpRequest;
	},
	
	/**
	 * 通用型ajax请求方法 (兼容老的浏览器)
	 * @param url
	 * @param _data
	 * @param funsuc
	 * @returns {Boolean}
	 * ///////////////////////////////////////////////////////////////////
	 */
	ajaxCall:function(_url,_data,funsuc,_config){
		var loadDiv = layer.load(3);
		try{
			var defaultConfig={
	            _type:"post",
	            _async: false
			};
			
			if (common.isNotBlank(_config)) {
	            for (var item in _config) {
	            	defaultConfig[item] = _config[item];
	            }
	        }
			var xmlhttp = common.createXHR();
			if(xmlhttp!=null&&xmlhttp!=undefined){
				var parastr="?a=b&ajax_params="+common.encode_URI(common.jsonToStr(_data));
				xmlhttp.open(defaultConfig._type,_url+parastr,defaultConfig._async);
				xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
				xmlhttp.onreadystatechange = function(){
					if (xmlhttp.readyState == 4) { // 如果状态正常返回了
						  if(xmlhttp.status== 200) {
							  layer.close(loadDiv);
							  var json=common.parseToJson(xmlhttp.responseText);
							  if(json.statusCode==100){
								  xmlhttp.abort();//请求中止
								  layer.msg(json.statusMessage,{icon:2});
								  top.location.reload();
								  window.location.reload()
								  common.winHref("/hotel/index.html","?time="+common.guid());
							  }else{
								  funsuc(json);
							  }
						  }else if(xmlhttp.status==404){
							  layer.close(loadDiv);
							  console.error("请求的地址不存在，或者该页面已经失效！ : " + xmlhttp.status + " " + xmlhttp.statusText);
						  }else {
							  layer.close(loadDiv);
							  console.error("Request was unsuccessful : " + xmlhttp.status + " " + xmlhttp.statusText);
						  }
				    }
				}
				if (defaultConfig._type== "POST" || defaultConfig._type== "post") {
					xmlhttp.send("ajax_params="+common.encode_URI(common.jsonToStr(_data)));
				} else {
					xmlhttp.send(null);
				}
			}else{
				layer.close(loadDiv);
				console.error("创建XMLHTTP协议异常！");
				return false;
			}
		}catch(e){
			layer.close(loadDiv);
			console.error("错误消息:"+e);
		}
	},
	
	/**
	 * 图片文件上传
	 * @param url
	 * @param _data
	 * @param funsuc
	 * @returns {Boolean}
	 * //////////////////////////////////////////////////////////////////
	 */
	AjaxUpload:function(url,id,funsuc){
		var fileObj = document.getElementById(id).files[0];  
		var form = new FormData();  
	    form.append("myfile", fileObj);  
		var xmlhttp = common.createXHR();
		if(xmlhttp!=null&&xmlhttp!=undefined){
			xmlhttp.open("POST",common.projectName()+url,false);
			//回调方法
			xmlhttp.onreadystatechange = function(){
				if (xmlhttp.readyState == 4){
					if (xmlhttp.status == 200) {
						funsuc(common.parseToJson(xmlhttp.responseText));
					} else if (xmlhttp.status == 404) {
						alert(xmlhttp.status+"，服务器没有找到与请求URI相符的资源");
					}else if(xmlhttp.status ==500){
						alert(xmlhttp.status+"，服务器内部错误");
					}else {
						alert("Error: status code is" + xmlhttp.status);
					}
				}
			};
			xmlhttp.send(form);
		}else{
			alert("创建XMLHTTP协议异常！");
			return false;
		}
	},
	
	//====================================================表单处理=======================================//
	
	/**
	 * 获取页面数据
	 *  //////////////////////////////////////////////////////////////////
	 */
	getFormData:function(el){
		var rtn = new Object();
		if(common.isNotBlank(el)){
			$(el+" input[type=text]").each(function(){
				var element=$(this);
				if ((element.attr("id")!=null)&&(element.attr("id")!="")){
					rtn[element.attr("id")]=element.val();
				}
			});
			$(el+" input[type=hidden]").each(function(){
				var element=$(this);
				if ((element.attr("id")!=null)&&(element.attr("id")!="")){
					rtn[element.attr("id")]=element.val();
				}
			});
			$(el+" input[type=password]").each(function(){
				var element=$(this);
				if ((element.attr("id")!=null)&&(element.attr("id")!="")){
					rtn[element.attr("id")]=element.val();
				}
			});
			$(el+" input[type=checkbox]").each(function(){
				var element=$(this);
				if (element.attr("checked")==true){
					if ((element.attr("id")!=null)&&(element.attr("id")!="")){			
						rtn[element.attr("id")]=element.val();
					
					}
				}
			});
			$(el+" input[type=radio]").each(function(){
				var element=$(this);
				if (element.attr("checked")==true){
					if ((element.attr("name")!=null)&&(element.attr("name")!="")){			
						rtn[element.attr("name")]=element.val();
					}
				}
			});
			$(el+" textarea").each(function(){
				var element=$(this);
				if ((element.attr("id")!=null)&&(element.attr("id")!="")){			
					rtn[element.attr("id")]=element.val();
				}
			});
			$(el+" select").each(function(){
				var element=$(this);
				if ((element.attr("id")!=null)&&(element.attr("id")!="")){			
					rtn[element.attr("id")]=element.val();
				}
			});
		}else{
			$("input[type=text]").each(function(){
				var element=$(this);
				if ((element.attr("id")!=null)&&(element.attr("id")!="")){
					rtn[element.attr("id")]=element.val();
				}
			});
			$("input[type=hidden]").each(function(){
				var element=$(this);
				if ((element.attr("id")!=null)&&(element.attr("id")!="")){
					rtn[element.attr("id")]=element.val();
				}
			});
			$("input[type=password]").each(function(){
				var element=$(this);
				if ((element.attr("id")!=null)&&(element.attr("id")!="")){
					rtn[element.attr("id")]=element.val();
				}
			});
			$('input[type=checkbox]').each(function(){
				var element=$(this);
				if (element.attr("checked")==true){
					if ((element.attr("id")!=null)&&(element.attr("id")!="")){			
						rtn[element.attr("id")]=element.val();
					
					}
				}
			});
			$("input[type=radio]").each(function(){
				var element=$(this);
				if (element.attr("checked")==true){
					if ((element.attr("name")!=null)&&(element.attr("name")!="")){			
						rtn[element.attr("name")]=element.val();
					}
				}
			});
			$("textarea").each(function(){
				var element=$(this);
				if ((element.attr("id")!=null)&&(element.attr("id")!="")){			
					rtn[element.attr("id")]=element.val();
				}
			});
			$("select").each(function(){
				var element=$(this);
				if ((element.attr("id")!=null)&&(element.attr("id")!="")){			
					rtn[element.attr("id")]=element.val();
				}
			});
		}
		return rtn;
	},
	
	/**
	 * 绑定数据
	 * 
	 * @param data
	 */
	bindFormData:function(data,el) {
		if(common.isNotBlank(el)){
			if ((data == null) || (data == "") || (data == undefined)) {
				return;
			}
			if (typeof (data) != "object") {
				return;
			}
			for ( var key in data) {		
				var element=$(el+" #"+key) ;	
				
				if (element.length==0) {
					continue ;
				}	
				if (element.attr("type")=="checkbox"){
					element.attr("checked",true);
					continue;
				}
				element.val(data[key]);
			}
		}else{
			if ((data == null) || (data == "") || (data == undefined)) {
				return;
			}
			if (typeof (data) != "object") {
				return;
			}
			for ( var key in data) {		
				var element=$("#"+key) ;	
				
				if (element.length==0) {
					continue ;
				}	
				if (element.attr("type")=="checkbox"){
					element.attr("checked",true);
					continue;
				}
				element.val(data[key]);
			}
		}
	},
	
	/**
	 * 获得CheckBox选中项
	 * @param id form表单的id
	 * @params el $('input:checkbox[name=goods]:checked')
	 */
	getCheckBox:function(el){
		var val="";
		$(el+':checked').each(function(k){
		     if(k == 0){
		    	 val = $(this).val();
		     }else{
		    	 val += ','+$(this).val();
		     }
		 })
		 return val;
	},
	
	/**
	 * 页面重置
	 * @param id form表单的id
	 */
	resetContent:function(id){
		var vaildForm=$('#'+id);
		vaildForm.find("input").val("");
		vaildForm.find("select").val("");
		vaildForm.find("textarea").val("");
		vaildForm.find('input[type="checkbox"]').each(function(){
			$(this).attr("checked",false);
		});
		
		/*for(i=0;i<document.all.tags("input").length;i++){  
	        if(document.all.tags("input")[i].type=="text"){  
	            document.all.tags("input")[i].value="";  
	        }  
	    }
		for(i=0;i<document.all.tags("textarea").length;i++){  
			document.all.tags("textarea")[i].value="";  
		}
		for(i=0;i<document.all.tags("checkbox").length;i++){  
			document.all.tags("checkbox")[i].value="";  
		}
		for(i=0;i<document.all.tags("select").length;i++){  
			document.all.tags("select")[i].value="";  
		}*/
	},
	
	clearnContent:function(id){
		$(id).find("input,select,textarea").each(function(index){
			$(this).val("");
		});
	},
	
	resetForm:function(id){
		/*debugger
		var vaildForm=$(id);
		vaildForm.find("input").val("");
		vaildForm.find("select").val("");
		vaildForm.find("textarea").val("");
		vaildForm.find('input[type="checkbox"]').each(function(){
			$(this).attr("checked",false);
		});*/
		/*debugger
		$(id).find("input").each(function(index,element){
			debugger
			console.log($(this).val())
			$(this).val("");
		});*/
		/*$(id+" input[type='hidden']").each(function(index){
			debugger
			console.log($(this).val())
			$(this).val("");
		});*/
	},
	
	//====================================================url跳转=======================================//
	/**
	 * 跳转详情方法
	 * @param url  跳转路劲
	 * @param params  参数
	 */
	winOpen:function(url,params){
		if(common.isNotBlank(url)){
			if(common.isNotBlank(url)){
				window.open(url+params);
    		}else{
    			window.open(url);
    		}
		}
	},
	
	/**
	 * 跳转详情方法
	 * @param url  跳转路劲
	 * @param params  参数
	 */
	winHref:function(url,params){
		if(common.isNotBlank(url)){
			if(common.isNotBlank(url)){
				window.location.href=url+params;
    		}else{
    			window.location.href=url;
    		}
		}
	},
	
	//获得项目名称+URL
	excuteUrl:function(url){
		if(common.isNotBlank(url) && common.getstrlength(url)>0){
			return common.projectName()+url;
		}
	},

	//====================================================获得url参数=======================================//
	/**
	 * 从URL获取参数
	 * @param {} paras
	 * @return {String}
	 */
	getParameter : function(key) {
	    var url = location.href;
	    var paraString = url.substring(url.indexOf("?") + 1, url.length).split("&");
	    var paraObj = {};
	    for ( var i = 0; j = paraString[i]; i++) {
	        paraObj[j.substring(0, j.indexOf("=")).toLowerCase()] = j.substring(j
	                .indexOf("=") + 1, j.length);
	    }
	    var returnValue = paraObj[key.toLowerCase()];
	    if (typeof (returnValue) == "undefined") {
	        return "";
	    } else {
	        return returnValue;
	    }
	},
	
	/**
	 * url 加密
	 * @param obj
	 * @returns
	 *  /////////////////////////////////////////////////////////////
	 */
	encode_URI:function(url){
		if(url != null && url !="" && url !=undefined){
			return encodeURI(encodeURI(url));
		}
	},
	
	//====================================================cooking=======================================//
	
	/**
	 * 删除 左右两端 的空格
	 */
	trimAll:function(str) {
		return str.replace(/(^\s*)|(\s*$)/g, "");
	},
	
	/**
	 * 获取cookie
	 */
	getCookie:function(cathy) {// 获取指定名称的cookie的值
		var arrStr = document.cookie.split("; ");
		for (var i = 0; i < arrStr.length; i++) {
			var temp = arrStr[i].split("=");
			if (temp[0] == cathy){
				return unescape(temp[1]);
			}
		}
	},
	
	/**
	 * 設置cookie
	 */
	setCookie:function(key, value) {
		var days = 30; // 此 cookie 将被保存 30 天
		var exp = new Date(); // new Date("December 31, 9998");
		exp.setTime(exp.getTime() + days * 24 * 60 * 60 * 1000);
		document.cookie = key + "=" + escape(value) + ";expires="+ exp.toGMTString();
	},

	/**
	 * 删除cookie
	 */
	deleteCookie:function(key) {
		var exp = new Date();
		exp.setTime(exp.getTime() - 1);
		var cval = cathy.getCookie(key);
		if (cval != null) {
			document.cookie = key + "=" + cval + ";expires=" + exp.toGMTString();
		}
	},
	
	//====================================================处理日期=======================================//
	
	/**
	 * 日期，在原有日期基础上，增加days天数，默认增加1天
	 * @params date_fmt 返回日期格式
	 * @params _date 返回日期格式
	 * @params date
	 */
	_addDate:function(days,_date,date_fmt) {
        if (!common.isNotBlank(days) || days == '') {
            days =0;
        }
        var date = null;
        if (common.isNotBlank(_date) && _date != '') {
        	date = new Date(_date);
        }else{
        	date = new Date();
        }
        date.setDate(date.getDate() + days);
        var year=date.getFullYear();
        var month = date.getMonth() + 1;
        if(month<10){
        	month="0"+month;
        }
        var day = date.getDate();
        if(day<10){
        	day="0"+day;
        }
        var hours = date.getHours();
		if(hours<10){hours = "0"+hours;}
		var minutes = date.getMinutes();
		if(minutes<10){minutes = "0"+minutes;}
		var seconds = date.getSeconds();
		if(seconds<10){seconds = "0"+seconds;}
		if(common.isNotBlank(date_fmt)){
			if(date_fmt=="ymd"){
				return year + "-" + month + "-" + day;
			}else if(date_fmt=="ymdh"){
				return year + "-"+month+"-"+day+" "+hours;
			}else if(date_fmt=="ymdhm"){
				return year+"-"+month+"-"+day+" "+hours+":"+minutes;
			}else if(date_fmt=="ymdhms"){
				return year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
			}
		}else{
			return year + "-" + month + "-" + day;
		}
    },
    
    /**
	 * 日期比较大小
	 * startDate 开始日期
	 * overDate  结束日期
	 */
	compareDate:function(startDate,overDate,days) {
		var arys1 = new Array();
		var arys2 = new Array();
		if(common.isNotBlank(startDate) && common.isNotBlank(overDate)) {
			arys1 = startDate.split('-');
			var sdate = new Date(arys1[0], parseInt(arys1[1] - 1), arys1[2].substring(0,2));
			arys2 = overDate.split('-');
			var edate = new Date(arys2[0], parseInt(arys2[1] - 1), arys2[2].substring(0,2));
			if(common.isNotBlank(days)){
				edate.setDate(edate.getDate() + days);
			}
			if(sdate > edate) {
				return false;
			} else {
				return true;
			}
		}
	},
	 //计算天数差的函数，通用  
	DateDiff: function(sDate1,  sDate2){    //sDate1和sDate2是2006-12-18格式  
		if(!common.isNotBlank(sDate1) || !common.isNotBlank(sDate1)) return false;
       var  aDate,  oDate1,  oDate2,  iDays;
       aDate  =  sDate1.split("-");  
       oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0]);    //转换为12-18-2006格式  
       aDate  =  sDate2.split("-");  
       oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0]);  
       iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24);    //把相差的毫秒数转换为天数  
       return  iDays;
   },    
	
   
   //Js计算时间差（天、小时、分钟、秒）
	countDownTime: function(sDate1,  sDate2){    //sDate1和sDate2是2006-12-18格式  ger
		if(!common.isNotBlank(sDate1) || !common.isNotBlank(sDate1)) return false;
        var date3 = new Date(sDate2).getTime() - new Date(sDate1).getTime();   //时间差的毫秒数      
        //计算出相差天数
        var days=Math.floor(date3/(24*3600*1000))
        //计算出小时数
        var leave1=date3%(24*3600*1000)    //计算天数后剩余的毫秒数
        var hours=Math.floor(leave1/(3600*1000))
        //计算相差分钟数
        var leave2=leave1%(3600*1000)        //计算小时数后剩余的毫秒数
        var minutes=Math.floor(leave2/(60*1000))
        //计算相差秒数
        var leave3=leave2%(60*1000)      //计算分钟数后剩余的毫秒数
        var seconds=Math.round(leave3/1000)
        //alert(" 相差 "+days+"天 "+hours+"小时 "+minutes+" 分钟"+seconds+" 秒")
        return days+"天 "+hours+"小时 "+minutes+" 分钟";
  },    
	

	//日期格式化
    formatDate: function(date, format) {
        if (common.isNotBlank(date)) {
            if (typeof(date) == "string") {
                if ("null" == date) { return ''; }
                return date;
            } else if (typeof(date) == "object") {
                var year = date.year + 1900;
                var month = (date.month + 1) >= 10 ? (date.month + 1) : "0" + (date.month + 1);
                var day = date.date >= 10 ? date.date : "0" + date.date;
                var hours = date.hours >= 10 ? date.hours : "0" + date.hours;
                var minutes = date.minutes >= 10 ? date.minutes : "0" + date.minutes;
                var seconds = date.seconds >= 10 ? date.seconds : "0" + date.seconds;
                if (format && "ymd" == format) {
                    return year + "-" + month + "-" + day;
                } else if (format && "ymdhmis" == format) {
                    return year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
                } else if (format && "ymdhi" == format) {
                    return year + "-" + month + "-" + day + " " + hours + ":" + minutes;
                } else {
                    return year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
                }
            }
            return date;
        }
    },
    
    /**
     * 日期格式：y-M-d h:m
     */
    format3YmdHm:function(time){
    	if(time && !isNaN(time)){
    		var date = new Date(time);
    		var mm = date.getMonth() + 1;
    		var dd = date.getDate();
    		var HH = date.getHours();
    		var MM = date.getMinutes();
    		var SS = date.getSeconds();
    		return date.getFullYear() + "-"+
    		(mm< 10 ? ('0' +mm) : mm) + "-"+
    		(dd< 10 ? ('0' + dd) : dd) + " " +
    		(HH< 10 ? ('0' + HH) : HH) + ":" +
    		(MM< 10 ? ('0' + MM) : MM);
    	}
    	return "";
    },
    
    /**
     * 日期格式：y-m-d
     */
    format2YmdHm:function(time){
    	if(time && !isNaN(time)){
    		var date = new Date(time);
    		var mm = date.getMonth() + 1;
    		var dd = date.getDate();
    		var HH = date.getHours();
    		var MM = date.getMinutes();
    		var SS = date.getSeconds();
    		return date.getFullYear() + "-"+
    		(mm< 10 ? ('0' +mm) : mm) + "-"+
    		(dd< 10 ? ('0' + dd) : dd);
    	}
    	return "";
    },
    
	//获得当前系统时间
	getSysTime:function(){
		return Date.parse(new Date());
	},
	
	//日期格式 yyyy-mm-dd
	getDateFmtYMD:function(){
		var now = new Date();
		var year=""+now.getFullYear();
		var month=now.getMonth()+1;
		if(month<10){month="0"+month;}
		var day = now.getDate();
		if(day<10){day = "0"+day;}
		return year + "-" + month + "-" + day;
	},
	
	//日期格式 yyyy-mm-dd :HH:mm
	getDateFmtYMDHm:function(){
		var now = new Date();
		var year=""+now.getFullYear();
		var month=now.getMonth()+1;
		if(month<10){month="0"+month;}
		var day = now.getDate();
		if(day<10){day = "0"+day;}
		var hours = now.getHours();
		if(hours<10){hours = "0"+hours;}
		var minutes = now.getMinutes();
		if(minutes<10){minutes = "0"+minutes;}
		return year + "-" + month + "-" + day + " " + hours + ":" + minutes;
	},
	
	//日期格式 yyyy-mm-dd :HH:mm:ss
	getDateFmtYMHms:function(){
		var now = new Date();
		var year=""+now.getFullYear();
		var month=now.getMonth()+1;
		if(month<10){month="0"+month;}
		var day = now.getDate();
		if(day<10){day = "0"+day;}
		var hours = now.getHours();
		if(hours<10){hours = "0"+hours;}
		var minutes = now.getMinutes();
		if(minutes<10){minutes = "0"+minutes;}
		var seconds = now.getSeconds();
		if(seconds<10){seconds = "0"+seconds;}
		return year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":"+ seconds;
	},
	
	//日期格式 yyyy-mm-dd :HH:mm:ss
	getDateByFmt:function(date_fmt){
		var now = new Date();
		var year=""+now.getFullYear();
		var month=now.getMonth()+1;
		if(month<10){month="0"+month;}
		var day = now.getDate();
		if(day<10){day = "0"+day;}
		var hours = now.getHours();
		if(hours<10){hours = "0"+hours;}
		var minutes = now.getMinutes();
		if(minutes<10){minutes = "0"+minutes;}
		var seconds = now.getSeconds();
		if(seconds<10){seconds = "0"+seconds;}
		if(common.isNotBlank(date_fmt)){
			if(date_fmt=="ymd"){
				return year + "-" + month + "-" + day;
			}else if(date_fmt=="ymdh"){
				return year + "-"+month+"-"+day+" "+hours;
			}else if(date_fmt=="ymdhm"){
				return year+"-"+month+"-"+day+" "+hours+":"+minutes;
			}else if(date_fmt=="ymdhms"){
				return year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
			}
		}else{
			return year + "-" + month + "-" + day;
		}
	},
    
    
    
    //================================================json处理===========================================//
	
	/**
	 * 字符串转成json格式
	 *  @param str
	 */
	strToJson:function(str){ 
		if(str!=null&&str!=""&&str!=undefined){
			return eval('(' + str + ')'); 
		}
	},
	
	/**
	 * json对象转成字符串格式
	 *  @param obj
	 */
	parseToJson:function(obj){
		if(common.isNotBlank(obj)){
			return JSON.parse(obj);
		}
	},
	
	/**
	 * json转成字符串格式
	 * @param obj
	 */
	jsonToStr:function(obj){ 
		if(common.isNotBlank(obj)){
			return JSON.stringify(obj); 
		}
	},
	
	//================================================键盘事件===========================================//
	
	/**
	 * 监听键盘事件
	 */
	ListenerKeycode:function(){
		var event=common.getEvent();
		return event.keyCode;
	},
	getEvent:function (){
	    if(document.all){
	    	return window.event;
	    }
		func=getEvent.caller;
		while(func!=null){
	    var arg0=func.arguments[0];
	    if(arg0){
	        if((arg0.constructor==Event || arg0.constructor ==MouseEvent)
	        ||(typeof(arg0)=="object" && arg0.preventDefault && arg0.stopPropagation)){
	        	return arg0;
	        }
	    }
	    func=func.caller;
	    }
	    return null;
	},
	
	//================================================验证===========================================//
    
	/**
	 * 判断是否数字
	 */
	isNumber:function(val){
		if(common.isNotBlank(val)){
		    var regPos = /^\d+(\.\d+)?$/; //非负浮点数
		    var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/; //负浮点数
		    if(regPos.test(val) || regNeg.test(val)){
		        return true;
		    }else{
		        return false;
		    }
		}
	},
	
	/**
	 * 身份证号码验证是否合法
	 */
	isCardID:function(i){ 
		if(common.isNotBlank(i)){
			i=i.toUpperCase();
			if(i.length!=18) return false;
			if(!common.is0To9(i.substring(0,17))) return false;
			if(!common.isDate(i.substring(6,14))) return false;
			if(i.charAt(17)!=common.getCheckBit(i)) return false;
			return true;
		}
	},
	//计算出合法身份证（17位或者18位）最后一位的校验位
	getCheckBit:function(idCode){
		var code=idCode.substring(0,17);
		var szVerCode="10X98765432";
		var iw=new Array(7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2);
		var bitValue = 0;
		for (var i=0; i<code.length; i++) {
			bitValue += code.charAt(i).valueOf() * iw[i];
		}
		bitValue = bitValue % 11;
		return szVerCode.charAt(bitValue);
	},
	is0To9:function(s){
		for(var i=0; i<this.length; i++) {
			var codeValue = s.charCodeAt(i);
			if(codeValue < 48 || codeValue > 57) return false;
		}
		return true;
	},
	//日期校验：YYYYMMDD
	isDate:function(s){
		var year=s.substring(0,4);
		var month=s.substring(4,6);
		var day=s.substring(6,8);
		if(!common.is0To9(year)||!common.is0To9(month)||!common.is0To9(day)) return false;
		//year
		var yearInt=parseInt(year);
		if(yearInt<1||yearInt>9999) return false;
		//month
		var monthInt = parseInt(month,10);
		if(monthInt<1||monthInt>12) return false;
		//day
		var dayInt=parseInt(day,10);
		var monthMax;
		if((yearInt%4==0&&yearInt%100!=0)||yearInt%400==0){
			monthMax=new Array(31,29,31,30,31,30,31,31,30,31,30,31);
		}else{
			monthMax=new Array(31,28,31,30,31,30,31,31,30,31,30,31);
		}
		if(dayInt<1||dayInt>monthMax[monthInt-1]) return false;
		return true;
	},
	
	/**
	 * 手机号码校验是否合法
	 */
	isMobileNumber:function(phone) {
		var myreg = /^(((13[0-9]{1})|(14[0-9]{1})|(17[0-9]{1})|(15[0-3]{1})|(15[4-9]{1})|(18[0-9]{1})|(199))+\d{8})$/;
		if(common.isNotBlank(phone) && common.isNumber(phone)){
			if (phone.length != 11) return false;
			if (!myreg.test(phone)) return false;
			return true;
		}
	},
	
	/** 功能：根据身份证号获得出生日期
	*** 参数：身份证号 psidno
	***  返回值：
	***  出生日期
	*/
	getBirthday:function(psidno){
		if(common.isNotBlank(psidno) && common.isCardID(psidno) && common.getstrlength(psidno)>14){
			var birthdayno,birthdaytemp
			if(common.getstrlength(psidno)==15){
				birthdaytemp=psidno.substring(6,12)
				birthdayno="19"+birthdaytemp
			}else{
				birthdayno=psidno.substring(6,14)
			}
			var birthday=birthdayno.substring(0,4)+"-"+birthdayno.substring(4,6)+"-"+birthdayno.substring(6,8)
			return birthday    
		}
	},
	
	/** 功能：根据身份证号获得性别
	*** 参数：身份证号 psidno
	***  返回值：
	***  性别
	***/
	getSex:function(psidno,flag){
		if(common.isNotBlank(psidno) && common.isCardID(psidno) && common.getstrlength(psidno)>14){
			var sexno,sex;
			if(common.getstrlength(psidno)==15){
				sexno=psidno.substring(14,15)
			}else{
				sexno=psidno.substring(16,17)
			}
			var tempid=sexno%2;
			if(common.isNotBlank(flag)){
				return tempid;
			}else{
				if(tempid==0){
					sex='女';
				}else{
					sex='男';
				}
				return sex;
			}
		}else{
			return "";
		}
	},
	
	//获取年龄
	getAge:function(UUserCard){
		if(common.isNotBlank(UUserCard) && common.isCardID(UUserCard) && common.getstrlength(UUserCard)>14){
			var myDate = new Date();
			var month = myDate.getMonth() + 1;
			var day = myDate.getDate();
			var age = myDate.getFullYear() - UUserCard.substring(6, 10) - 1;
			if (UUserCard.substring(10, 12) < month || UUserCard.substring(10, 12) == month && UUserCard.substring(12, 14) <= day) {
				age++;
			}
			return age;
		}
	},
	
	/**函数名：CheckDateTime
	*功能介绍：检查是否为日期时间
	*判断输入框中输入的日期格式为yyyy-mm-dd和正确的日期
	**/
	checkBirthDate:function(str){
		if(!common.isNotBlank(str) || common.getstrlength(str)==0) return false;
		if(!common.isNumber(str)) return false;
		var nYear = parseInt(str.substring(0,4),10) ;
		var nMonth = parseInt(str.substring(4,6),10) ;
		var nDay = parseInt(str.substring( 6,8),10) ;
		var dtDate = new Date( nYear, nMonth - 1, nDay ) ;
		if( nYear != dtDate.getFullYear() || ( nMonth - 1 ) != dtDate.getMonth() || nDay != dtDate.getDate()) return false;
		return true ;
	},
	
	
	/**
	 * 银行卡号码检测
	 * @params bankno 卡号 
	 *判断是否为正确的银行卡号，正确返回true，否则返回false
	 *Luhm校验规则：16位银行卡号（19位通用）:
	 *1.将未带校验位的 15（或18）位卡号从右依次编号 1 到 15（18），位于奇数位号上的数字乘以 2。
	 *2.将奇位乘积的个十位全部相加，再加上所有偶数位上的数字。
	 *3.将加法和加上校验位能被 10 整除。
	 */
	luhnCheck:function(bankno) {
	    var lastNum = bankno.substr(bankno.length - 1, 1); //取出最后一位（与luhn进行比较）
	    var first15Num = bankno.substr(0, bankno.length - 1); //前15或18位
	    var newArr = new Array();
	    for (var i = first15Num.length - 1; i > -1; i--) { //前15或18位倒序存进数组
	        newArr.push(first15Num.substr(i, 1));
	    }
	    var arrJiShu = new Array(); //奇数位*2的积 <9
	    var arrJiShu2 = new Array(); //奇数位*2的积 >9
	    var arrOuShu = new Array(); //偶数位数组
	    for (var j = 0; j < newArr.length; j++) {
	        if ((j + 1) % 2 == 1) { //奇数位
	            if (parseInt(newArr[j]) * 2 < 9) arrJiShu.push(parseInt(newArr[j]) * 2);
	            else arrJiShu2.push(parseInt(newArr[j]) * 2);
	        } else //偶数位
	        arrOuShu.push(newArr[j]);
	    }

	    var jishu_child1 = new Array(); //奇数位*2 >9 的分割之后的数组个位数
	    var jishu_child2 = new Array(); //奇数位*2 >9 的分割之后的数组十位数
	    for (var h = 0; h < arrJiShu2.length; h++) {
	        jishu_child1.push(parseInt(arrJiShu2[h]) % 10);
	        jishu_child2.push(parseInt(arrJiShu2[h]) / 10);
	    }

	    var sumJiShu = 0; //奇数位*2 < 9 的数组之和
	    var sumOuShu = 0; //偶数位数组之和
	    var sumJiShuChild1 = 0; //奇数位*2 >9 的分割之后的数组个位数之和
	    var sumJiShuChild2 = 0; //奇数位*2 >9 的分割之后的数组十位数之和
	    var sumTotal = 0;
	    for (var m = 0; m < arrJiShu.length; m++) {
	        sumJiShu = sumJiShu + parseInt(arrJiShu[m]);
	    }

	    for (var n = 0; n < arrOuShu.length; n++) {
	        sumOuShu = sumOuShu + parseInt(arrOuShu[n]);
	    }

	    for (var p = 0; p < jishu_child1.length; p++) {
	        sumJiShuChild1 = sumJiShuChild1 + parseInt(jishu_child1[p]);
	        sumJiShuChild2 = sumJiShuChild2 + parseInt(jishu_child2[p]);
	    }
	    //计算总和
	    sumTotal = parseInt(sumJiShu) + parseInt(sumOuShu) + parseInt(sumJiShuChild1) + parseInt(sumJiShuChild2);

	    //计算luhn值
	    var k = parseInt(sumTotal) % 10 == 0 ? 10 : parseInt(sumTotal) % 10;
	    var luhn = 10 - k;

	    if (lastNum == luhn) {
	        return true;
	    } else {
	        return false;
	    }
	},
	
	/**
	 * 字符串转成int类型
	 * @params val 字符串
	 */
	_parseInt:function(val){
		if(common.isNotBlank(val)){
			return parseInt(val);
		}else{
			return parseInt("0");
		}
	},
	
	/**
	 * 字符串转成Float类型
	 * @params val 字符串
	 */
	_parseFloat:function(val,fix){
		if(common.isNotBlank(val)){
			if(common.isNotBlank(fix)){
				return parseFloat(val).toFixed(common._parseInt(fix));
			}else{
				return parseFloat(val);
			}
		}else{
			return parseFloat("0");
		}
	},
	
	/**
	 * 删除 左右两端 的空格
	 */
	trim:function(str) {
		return str.replace(/(^\s*)|(\s*$)/g, "");
	},
	
    /**
    *判断对象不为空
    @params obj：对象
    **/
    isNotBlank: function(obj) {
        if (obj == "" || obj == null || obj == undefined) {
            return false;
        } else {
            return true;
        }
    },
    
	//校验是否数字
	_isNum:function(s){
		return /^[0-9]+$/.test(s);
	},
    
    //用于生成uuid
    S4:function(){
        return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
    },
    
    /**
     *获得guid 
     */
    guid:function() {
        return (common.S4()+common.S4()+common.S4()+common.S4()+common.S4()+common.S4()+common.S4()+common.S4());
    },
    
    //隔秒刷新
    _setInterval:function(method,interval){
    	window.setInterval(method,interval);
    },
    
    /**
	 * 获得字符串实际长度，中文2，英文1
	 *要获得长度的字符串</param>
	*/
	getstrlength:function(str) { 
		if(str==null || str=="" || str==undefined){
			return 0;
		}
	    var realLength = 0, len = str.length, charCode = -1;
	    for (var i = 0; i < len; i++) { 
	        charCode = str.charCodeAt(i);
	        if (charCode >= 0 && charCode <= 128) realLength += 1;
	        else realLength += 1;
	    }
	    return realLength;
	},
	
	/**
	 * 分辨率宽度
	 */
	getScreenWidth:function(){
		return window.screen.width;
	},
	
	/**
	 * 分辨率高度
	 */
	getScreenHeight:function(){
		return window.screen.height;
	},

	/**
	 * 获得窗口宽度
	 */
	getWinWidth:function(){
		return $(window).width();
	},
	
	/**
	 * 获得窗口高度
	 */
	getWinHeight:function(){
		return $(window).height();
	},
	
    
    /**
	 * 网络检测
	 * @param element
	 */
	addHandler:function(element,type, handler){
		if(element.addEventListener){
			element.addEventListener(type, handler, false);
		}else if(element.attachEvent){
			element.attachEvent("on" + type, handler); 
		}else{
			element["on" + type] = handler;
		}
	},

	/**
	*加载Flash文件
	*@{param} id 对应的div
	*@{param} path 文件URL资源文件，也可以是本地文件
	*@{param} width 视屏宽度
	*@{param} height 视屏高度
	**/
	swfPlay:function(id,path,width,height){
		if(width==0 || width==undefined || width==null){
			width='100%';
		}
		if(height==0 || height==undefined || height==null){
			height='100%';
		}
		filename=path.substring(path.lastIndexOf("/")+1,path.length);
		var poster_img="js/player.jpg";
		document.write('<video id="'+id+'" src="'+path+'" poster="'+poster_img+'" width="'+width+'" height="'+height+'" controls="controls" autoplay="autoplay" preload="none" loop="loop">');
		document.write('<source src="'+path+'" type="video/mp4">');
		document.write('<source src="'+path+'" type="video/ogg;">');
		document.write('<source src="'+path+'" type="audio/ogg;">');
		document.write('<source src="'+path+'" type="audio/mpeg">');
		document.write('<source src="'+path+'" type="audio/mp4">');
		document.write('<object class="play-main" width="'+width+'" height="'+height+'" classid="clsid:22D6F312-B0F6-11D0-94AB-0080C74C7E95" id="'+id+'" type="application/x-shockwave-flash"');
		document.write(' codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,40,0">');
		document.write('<param name="src" value="'+path+'"" />');
		document.write('<param name="Enabled" value="1" /><param name="controls" value="controls" />');
		document.write('<param name="FileName" value="'+filename+'" />');
		document.write('<param name="AutoStart" value="0" />');
		document.write('<embed width="'+width+'" height="'+height+'" class="play-main" autostart="false" ShowDisplay="true" loop="false" hidden="false"');
		document.write(' ShowTracker="true" ShowStatusBar="true" bgcolor="#000000" src="'+path+'" ');
		document.write('type="application/x-shockwave-flash"></embed></object></video>');
	},
    
    
    //=====================================================前端vue.js======================================//
    /**
     *绑定数据
     *@params selector 选择器
     *@params _data 数据
     *@params config 配置项
     ***/
    vueBind: function(selector, _data, config) {
    	if(!common.isNotBlank(selector)){
    		console.error("el:选择器不能为空");
    		return;
    	}
    	if(!common.isNotBlank(_data)){
    		common.error("data:数据不能为空");
    		return;
    	}
    	if(!common.isNotBlank(config)){
    		config={};
    	}
        return common.newVue(selector, _data, config);
    },

    /**
     *实例化
     *@params selector 选择器
     *@params _data 数据
     *@params config 配置项
     ***/
    newVue: function(selector, _data, config) {
        var vm = new Vue(common.vueConfig(selector, _data, config));
        //vm.$destroy();
        return vm;
    },


    /**
     *配置项封装
     *@params selector 选择器
     *@params _data 数据
     *@params computed 计算属性
     *@params methods  方法
     *@params filters  过滤器 filters:{filterFun:function (input) {...}}
     *@params watch    侦听属性
     *@params beforeCreate 创建vue实例前
     *@params created 创建vue实例后
     *@params beforeMount 渲染dom前
     *@params mounted 渲染dom后
     *@params beforeUpdate 数据变化更新前
     *@params updated 数据变化更新后
     *@params beforeDestroy vue实例销毁前
     *@params destroyed vue实例销毁后
     ***/
    vueConfig: function(selector, _data, config) {
        var vue_config = {
            el: selector,
            data: _data,
            delimiters:['{{', '}}'],//自定义符号：{{}},${},&{}
            //计算属性
            computed: {},
            //方法
            methods: {},
            //过滤器
            filters:{
            	//时间转换 年月日
            	formatDate:function(val){
 					return common.format2YmdHm(val);
 				},
 				//字符限制
 				formatTitle:function(val,i){
 					return val.length > i ? (val.substring(0,i)+'...') : val;
 				}
            },
            //侦听属性
            watch: {},
            //创建vue实例前
            beforeCreate: function() {},
            //创建vue实例后
            created: function() {},
            //渲染dom前
            beforeMount: function() {},
            //渲染dom后 data数据"+this._data+";挂载的对象:"+this.$el
            mounted: function() {},
            //数据变化更新前
            beforeUpdate: function() {},
            //数据变化更新后
            updated: function() {},
            //vue实例销毁前
            beforeDestroy: function() {},
            //vue实例销毁后
            destroyed: function() {},
        }
        if (common.isNotBlank(config)) {
            for (var item in config) {
                vue_config[item] = config[item];
            }
        }

        return vue_config;
    }
    
    
}