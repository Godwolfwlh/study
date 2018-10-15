var cbx_param3=null;//房间类型
var cbx_param4=null;//房间状态
var cbx_param5=null;//楼层
var pageTotal=0;
var query={
	//初始化
	initPage:function(){
		var w=$(".layui-col-md1").width();
		$(".p_first").find("img").attr("width",(w-4)+"px");
		
		cbx_param3=new SelectBox("param3",{
			text_field:'dicName',
			value_field:'dicCode',
			multiple:false,
			width:'100%',
			dropWidth:'200px'
		});
		cbx_param4=new SelectBox("param4",{
			text_field:'dicName',
			value_field:'dicCode',
			multiple:false,
			width:'100%',
			dropWidth:'200px'
		});
		cbx_param5=new SelectBox("param5",{
			text_field:'dicName',
			value_field:'dicCode',
			multiple:false,
			width:'100%',
			dropWidth:'200px'
		});
		
		var codeType='ROOM_TYPE,ROOM_FLOOR';
		layer.load(3);
		common.ajaxCall(common.excuteUrl("dictionaryController/findByTypeCode.do"),{'typeCode':codeType},function(ret){
			layer.closeAll("loading");
			if(ret.statusCode==0){
				//cbx_param4.bind(ret.result.ROOM_STATE);
				cbx_param3.bind(ret.result.ROOM_TYPE);
				cbx_param5.bind(ret.result.ROOM_FLOOR);
			}else{
				layer.msg(ret.statusMessage);
			}
		});
		
		cbx_param4.bind([{'dicName':'空闲','dicCode':'1'},{'dicName':'预定','dicCode':'2'},{'dicName':'入住','dicCode':'3'}]);
	},
	
	//編輯
	tab:function(id){
		layer.confirm('确定要删除该条记录吗？', {
		  icon:3,
		  btn: ['订房', '退房'],
		  yes:function(index, layero){
			  layer.close(index);
		  },
		  cancel:function(index, layero){
			  layer.close(index);
		  }
		});
	},
	//查詢
	search:function(pageNow,pageSize){
		var obj=common.getFormData("#queryForm");
		//obj.param0=$("#param0").val();
		if(common.isNotBlank(pageNow) && pageNow!=""){
			obj.pageNow=common._parseInt(pageNow);
		}else{
			obj.pageNow="1";
		}
		if(common.isNotBlank(pageSize) && pageSize!=""){
			obj.pageSize=common._parseInt(pageSize);
		}else{
			obj.pageSize="18";
		}
		
		layer.load(3);
		common.ajaxCall(common.excuteUrl("roomInfoController/findByWebPage.do"),obj,function(ret){
			layer.closeAll("loading");
			if(ret.statusCode==0){
				var htmlstr='<fieldset class="layui-elem-field layui-field-title"><legend style="color:#0668fa;">房间信息</legend></fieldset>';
				if(common.isNotBlank(ret.result.rows) && ret.result.rows.length>0){
					var list=ret.result.rows;
					for(var item in list){
						htmlstr+='<div class="layui-col-sm6 layui-col-md2">';
						htmlstr+='<div class="layui-card">';
						if(list[item].roomState=="空闲"){
							htmlstr+='<div class="layui-card-header">'+list[item].roomType+'<span class="layui-badge layui-bg-blue layuiadmin-badge">'+list[item].roomState+'</span></div>';
						}else if(list[item].roomState=="预定"){
							htmlstr+='<div class="layui-card-header">'+list[item].roomType+'<span class="layui-badge layui-bg-green layuiadmin-badge">已'+list[item].roomState+'</span></div>';
						}else{
							htmlstr+='<div class="layui-card-header">'+list[item].roomType+'<span class="layui-badge layui-bg-red layuiadmin-badge">已'+list[item].roomState+'</span></div>';
						}
						htmlstr+='<div class="layui-card-body layuiadmin-card-list">';
						htmlstr+='<p class="layuiadmin-big-font">'+list[item].roomNumber+'房</p>';
						htmlstr+='<p>'+list[item].floor+'<span class="layuiadmin-span-color">￥'+list[item].roomPrice+'<i class="layui-inline layui-icon layui-icon-flag"></i></span></p>';
						if(list[item].roomState=="空闲"){
							htmlstr+='<p><button class="layui-btn layui-btn-primary layui-btn-xs"><i class="layui-icon">&#xe655;</i>预定</button><span class="layuiadmin-span-color"><button class="layui-btn layui-btn-primary layui-btn-xs"><i class="layui-icon">&#xe613;</i>入住</button><i class="layui-inline layui-icon layui-icon-flag"></i></span></p>';
						}else if(list[item].roomState=="预定"){
							htmlstr+='<p><button class="layui-btn layui-btn-primary layui-btn-xs"><i class="layui-icon">&#xe63c;</i>换房</button><span class="layuiadmin-span-color"><button class="layui-btn layui-btn-primary layui-btn-xs"><i class="layui-icon">&#xe613;</i>入住</button><i class="layui-inline layui-icon layui-icon-flag"></i></span></p>';
						}else{
							htmlstr+='<p><button class="layui-btn layui-btn-primary layui-btn-xs"><i class="layui-icon">&#xe63c;</i>换房</button><span class="layuiadmin-span-color"><button class="layui-btn layui-btn-primary layui-btn-xs"><i class="layui-icon">&#xe667;</i>退房</button><i class="layui-inline layui-icon layui-icon-flag"></i></span></p>';
						}
						htmlstr+='</div></div></div>';
					}
					$("#wrap1").show();
					//分页不一致，在加载
					if(pageTotal!=ret.result.total){
						var obj1 = {
							wrapid:'wrap1', //页面显示分页器容器id
							total:ret.result.total,//总条数
							pagesize:obj.pageSize,//每页显示10条
							currentPage:obj.pageNow,//当前页
							onPagechange:query.onPagechange,
							btnCount:3 //页数过多时，显示省略号的边界页码按钮数量，可省略，且值是大于5的奇数
						}
						pagination.init(obj1);
						pageTotal=ret.result.total;
					}
				}else{
					htmlstr+='<div class="layui-card"><div class="layui-card-body">';
					htmlstr+='<dl class="layuiadmin-card-status">';
					htmlstr+='<dd><div class="layui-status-img"><a href="javascript:;"><img src="http://www.layui.com/admin/std/dist//layuiadmin/style/res/logo.png"></a></div>';
					htmlstr+='<div><p>对不起 ，找不到相关信息!!!!!</p>';
					htmlstr+='</div></dd></dl></div></div>';
					$("#wrap1").hide();
				}
				$("#content").html(htmlstr);
			}else{
				layer.msg(ret.statusMessage);
			}
		});
	},
	
	//分页改变事件
	onPagechange:function(currentPage){
	    //console.log('当前点击页码',currentPage);
	    var flag=1;
	    if(currentPage>0){
	    	flag++;
	    }
	    if(flag==2){
	    	query.search(currentPage,18);
	    }
	},
	
	//刷新
	refresh:function(){
		query.search(1,10);
	}
}


$(document).ready(function(){
	query.initPage();
	query.search(1,18);
	common._setInterval("query.search(1,18)",1*60*60*60);
});
