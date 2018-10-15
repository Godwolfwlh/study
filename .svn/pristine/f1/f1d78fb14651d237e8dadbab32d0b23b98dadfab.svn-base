var dataGrid=null;
var cbx_roomType=null;//房间类型
var cbx_param3=null;//房间类型
var cbx_floor=null;//楼层
var cbx_param5=null;//楼层
var cbx_roomState=null;//房间状态
var cbx_param4=null;//房间状态
var cbx_houseNumber=null;//房东浩
var cbx_roomPrice=null;//房东浩
var cbx_roomConfig=null;//房东浩
var cbx_enabledFlag=null;
var roomInfo={
	/**
	 * 初始加载
	 */
	init:function(){
		dataGrid=$('#roomGrid').datagrid({
		    loadMsg:"正在加载..",
		    pagination:true,
		    rownumbers:true,
		    pagePosition:'bottom',
		    pageNumber:1,
		    pageSize:10,
		    fitColumns:true,
		    border: true,
	        striped: true,
	        autoRowHeight: false,
	        singleSelect: true,
		    showRefresh:false,
		    idField:"roomId",
		    //view:'暂无数据',
		    columns:[[
				{field:'roomNumber',title:'房间号',width:100},
				{field:'roomType',title:'房间类型',width:100},
				{field:'floor',title:'楼层',width:100},
				{field:'roomState',title:'房间状态',width:100},
				{field:'roomPrice',title:'房间价格(元)',width:100},
				{field:'deposit',title:'押金(元)',width:100},
				{field:'roomAear',title:'房间面积(m²)',width:100},
				{field:'orderFlag',title:'排序标识',width:100},
				{field:'createByName',title:'创建人',width:100},
				{field:'createDateStr',title:'创建日期',width:100},
				{field:'updateByName',title:'修改人',width:100},
				{field:'updateDateStr',title:'修改日期',width:100},
				{field:'opt',title:'操作',formatter:function(val,rows,index){
					var btn='<button class="layui-btn layui-btn-sm" onclick="roomInfo.tab(\'see\',\''+rows.roomId+'\')"><i class="layui-icon">&#xe615;</i>查看</button>'
				    +'<button class="layui-btn layui-btn-normal layui-btn-sm" onclick="roomInfo.tab(\'edit\',\''+rows.roomId+'\')"><i class="layui-icon">&#xe642;</i>编辑</button>'
				    +'<button class="layui-btn layui-btn-danger layui-btn-sm" onclick="roomInfo.tab(\'delete\',\''+rows.roomId+'\')"><i class="layui-icon">&#xe640;</i>删除</button>';
				    return btn;
				}}
		    ]]
		});
		
		cbx_enabledFlag=new SelectBox("enabledFlag",{
			text_field:'dicName',
			value_field:'dicCode',
			multiple:false,
			width:'100%',
			dropWidth:'200px'
		});
		
		cbx_roomType=new SelectBox("roomType",{
			text_field:'dicName',
			value_field:'dicCode',
			multiple:false,
			width:'100%',
			dropWidth:'200px'
		});
		cbx_param3=new SelectBox("param3",{
			text_field:'dicName',
			value_field:'dicCode',
			multiple:false,
			width:'100%',
			dropWidth:'200px'
		});
		
		cbx_floor=new SelectBox("floor",{
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
		
		cbx_roomState=new SelectBox("roomState",{
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
		
		cbx_houseNumber=new SelectBox("houseNumber",{
			text_field:'dicName',
			value_field:'dicCode',
			multiple:false,
			width:'100%',
			dropWidth:'200px'
		});
		
		cbx_roomPrice=new SelectBox("roomPrice",{
			text_field:'dicName',
			value_field:'dicCode',
			multiple:false,
			width:'100%',
			dropWidth:'200px'
		});
		cbx_roomConfig=new SelectBox("roomConfig",{
			text_field:'dicName',
			value_field:'dicCode',
			multiple:true,
			width:'100%',
			dropWidth:'200px'
		});
		var codeType='ROOM_STATE,ROOM_PRICE,ROOM_FLOOR,ROOM_TYPE,HOURSE_NUMBER,ENABLED_FLAG,ROOM_CONFIG';
		layer.load(3);
		common.ajaxCall(common.excuteUrl("dictionaryController/findByTypeCode.do"),{'typeCode':codeType},function(ret){
			layer.closeAll("loading");
			if(ret.statusCode==0){
				cbx_enabledFlag.bind(ret.result.ENABLED_FLAG);
				cbx_roomType.bind(ret.result.ROOM_TYPE);
				cbx_param3.bind(ret.result.ROOM_TYPE);
				cbx_floor.bind(ret.result.ROOM_FLOOR);
				cbx_param5.bind(ret.result.ROOM_FLOOR);
				cbx_roomState.bind(ret.result.ROOM_STATE);
				cbx_param4.bind(ret.result.ROOM_STATE);
				cbx_houseNumber.bind(ret.result.HOURSE_NUMBER);
				cbx_roomPrice.bind(ret.result.ROOM_PRICE);
				
				//房间配置
				var _data=ret.result.ROOM_CONFIG;
				if(common.isNotBlank(_data)){
					var str='';
					for(var item in _data){
						str+='<div class="checkbox_box"><input type="checkbox" name="roomConfig" value="'+_data[item].dicCode+'" /><label>'+_data[item].dicName+'</label></div>';
					}
					$("#roomConfigDiv").html(str);
				}
				//console.log(ret);
			}else{
				layer.alert(ret.statusMessage,{icon: 2});
			}
		});
	},
	
	//查询按钮事件
	query:function(){
		var params={
			param0:$("#param0").val(),
			param1:$("#param1").val(),
			param2:$("#param2").val(),
			param3:$("#param3").val(),
			param4:$("#param4").val(),
			param5:$("#param5").val()
		}
		roomInfo.loadGrid(params);
	},
	
	//清空按钮事件
	reset:function(){
		$("#param0").val("");
		$("#param1").val("");
		$("#param2").val("");
		$("#param3").val("");
		$("#param4").val("");
		$("#param5").val("");
	},
	
	// 加载表格
	loadGrid:function(params){
		dataGrid.datagrid({
			url:common.excuteUrl("roomInfoController/findByPage.do"),
			queryParams:params,
			method:"post"
		});
	},
	
	//清空
	resetGrid:function(){
		dataGrid.datagrid("loadData",{
			total:0,
	        rows:[]
		});
	},

	/**
	 * 表单内容启用、禁用
	 * @param type
	 * @returns
	 */
	editFlag:function(type){
		cbx_enabledFlag.clear();
		cbx_roomType.clear();
		cbx_floor.clear();
		cbx_roomState.clear();
		cbx_houseNumber.clear();
		cbx_roomPrice.clear();
		roomInfo.resetForm("#roomForm");
		if(type=="add" || type=='edit'){
			$("#roomform").find("input").removeAttr("readOnly");
			$("#roomform").find("select").removeAttr("readOnly");
			cbx_enabledFlag.enabled();
			cbx_roomType.enabled();
			cbx_floor.enabled();
			cbx_roomState.enabled();
			cbx_houseNumber.enabled();
			cbx_roomPrice.enabled();
		}else{
			$("#roomform").find("input").attr("readOnly","readOnly");
			$("#roomform").find("select").attr("readOnly","readOnly");
			cbx_enabledFlag.disabled(true);
			cbx_roomType.disabled(true);
			cbx_floor.disabled(true);
			cbx_roomState.disabled(true);
			cbx_houseNumber.disabled(true);
			cbx_roomPrice.disabled(true);
		}
	},
	
	/**
	 * 编辑、查看、新增、删除 基本操作
	 */
	tab:function(type,id){
		if(type=='delete'){
			layer.confirm('确定要删除该条记录吗？', {
			  btn: ['确定', '取消'],
			  yes:function(index, layero){
				  layer.load(3);
				  common.ajaxCall(common.excuteUrl("roomInfoController/deleteById.do"),{'roomId':id},function(ret){
					  layer.closeAll("loading");
					  if(ret.statusCode==0){
							layer.msg(ret.statusMessage,{icon: 1});
							roomInfo.loadGrid();
							layer.close(index);
						}else{
							layer.msg(ret.statusMessage,{icon: 2});
						}
					});
			  },
			  cancel:function(index, layero){
				  layer.close(index);
			  }
			});
		}else{
			layer.open({
				title:"房间信息",
				type:1,
				zIndex:99,
				shade:0.2,
				area: [(common.getWinWidth()*0.55)+'px',(common.getWinHeight()*0.9)+'px'],
				content:$("#roomForm"),
				btn:['确定', '取消'],
				yes:function(index, layero){
					roomInfo.saveOrUpdate();
					layer.close(index);
				},
				btn2: function(index, layero){
					layer.close(index);
				},
				cancel: function(index, layero){
					layer.close(index);
				},
				success: function(layero, index){
					roomInfo.editFlag(type);
					//编辑、查看
					if(type=="edit" || type=="see"){
						layer.load(3);
						common.ajaxCall(common.excuteUrl("/roomInfoController/findById.do"),{'roomId':id},function(ret){
							layer.closeAll("loading");
							if(ret.statusCode==0){
								roomInfo.bindValue(ret.result);
							}else{
								layer.alert(ret.statusMessage,{icon:2});
							}
						});
					}else{
						//获得最大序列号
						layer.load(3);
						common.ajaxCall(common.excuteUrl("/roomInfoController/getMaxOrderFlag.do"),{},function(ret){
							layer.closeAll("loading");
							if(ret.statusCode==0){
								$("#orderFlag").val(ret.result);
							}else{
								layer.alert(ret.statusMessage,{icon: 2});
							}
						},{"_type":"GET"});
						cbx_enabledFlag.setValue("1");
					}
				}
			});
		}
	},
	
	//清除内容
	resetForm:function(id){
		$(id).find("input").each(function(index,e){
			$(this).val("");
		})
		$(id).find("select").each(function(index){
			$(this).val("");
		});
		$(id).find("textarea").each(function(index){
			$(this).val("");
		});
		$(id).find("input[type='checkbox']").each(function(index){
			$(this).attr("checked",false);
		});
	},

	/**
	 * 绑定表单
	 * @param data
	 * @returns
	 */
	bindValue:function(data){
		console.log(data);
		common.bindFormData(data);//18320204217  18320204217abcd
		cbx_enabledFlag.setValue(data.enabledFlag);
		cbx_roomType.setValue(data.roomType);
		cbx_floor.setValue(data.floor);
		cbx_roomState.setValue(data.roomState);
		cbx_houseNumber.setValue(data.houseNumber);
		cbx_roomPrice.setValue(data.roomPrice);
		//cbx_roomConfig.bind(data.roomConfig);
		//房间配置
		var ret=data.roomConfig;
		if(common.isNotBlank(ret)){
			var item=ret.split(",");
			for(var i=0;i<item.length;i++){
				$("#roomConfigDiv input:checkbox[name='roomConfig']").each(function(index){
					var val=$(this).val();
					if(val==item[i]){
						$(this).attr('checked',true);
					}
				});
			}
		}
	},

	/**
	 * 保存信息
	 * @returns
	 */
	saveOrUpdate:function(){
		 var obj=common.getFormData("#roomForm");
		 //ck选中
		 var roomConfig=common.getCheckBox("#roomConfigDiv input:checkbox[name='roomConfig']");
		 obj.roomConfig=roomConfig;
		 var url="";
		 if(common.getstrlength(obj.roomId)==0){
			 url=common.excuteUrl("roomInfoController/insertRoom.do");
		 }else{
			 url=common.excuteUrl("roomInfoController/updateById.do");
		 }
		 layer.load(3);
		 common.ajaxCall(url,obj,function(ret){
			 layer.closeAll("loading");
			 if(ret.statusCode==0){
				 layer.msg(ret.statusMessage,{icon: 1},function(index,l){
					 layer.close(index);
					 roomInfo.loadGrid();
				 });
			 }else{
				 layer.msg(ret.statusMessage,{icon: 2});
			 }
		 });
	}
}


/**
 * 初始加载
 * @returns
 */
$(document).ready(function(){
	roomInfo.init();
	roomInfo.loadGrid();
});
