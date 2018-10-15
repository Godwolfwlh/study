var dataGrid=null;
var cbx_enabledFlag=null;
var dictype={
	/**
	 * 初始加载
	 */
	initPage:function(){
		dataGrid=$('#dictypeGrid').datagrid({
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
		    idField:"typeId",
		    columns:[[
				{field:'typeName',title:'分类名称',width:100},
				{field:'typeCode',title:'分类编码',width:100},
				{field:'orderFlag',title:'排序标识',width:100},
				{field:'enabledFlag',title:'是否可用',width:100},
				{field:'remark',title:'备注',width:100},
				{field:'createByName',title:'创建人',width:100},
				{field:'createDateStr',title:'创建日期',width:100},
				{field:'updateByName',title:'修改人',width:100},
				{field:'updateDateStr',title:'修改日期',width:100},
				{field:'opt',title:'操作',formatter:function(val,rows,index){
					var btn='<button class="layui-btn layui-btn-sm" onclick="dictype.tab(\'see\',\''+rows.typeId+'\')"><i class="layui-icon">&#xe615;</i>查看</button>'
				    +'<button class="layui-btn layui-btn-normal layui-btn-sm" onclick="dictype.tab(\'edit\',\''+rows.typeId+'\')"><i class="layui-icon">&#xe642;</i>编辑</button>'
				    +'<button class="layui-btn layui-btn-danger layui-btn-sm" onclick="dictype.tab(\'delete\',\''+rows.typeId+'\')"><i class="layui-icon">&#xe640;</i>删除</button>';
				    return btn;
				}}
		    ]]
		});
		cbx_enabledFlag=new SelectBox("enabledFlag",{
			text_field:'name',
			value_field:'id',
			multiple:false,
			width:'100%',
			dropWidth:'200px'
		});
		cbx_enabledFlag.bind([{"id":"1","name":"是"},{"id":"0","name":"否"}]);
	},
	
	//查询按钮事件
	query:function(){
		var params={
			param0:$("#param0").val(),
			param1:$("#param1").val()
		}
		dictype.loadGrid(params);
	},
	
	//清空按钮事件
	reset:function(){
		$("#param0").val("");
		$("#param1").val("");
	},
	
	// 加载表格
	loadGrid:function(params){
		dataGrid.datagrid({
			url:common.excuteUrl("dictypeController/findDictypeByPage.do"),
			queryParams:params,
			method:"post"
		});
	},
	
	/**
	 * 表单内容启用、禁用
	 * @param type
	 * @returns
	 */
	editFlag:function(type){
		cbx_enabledFlag.clear();
		if(type=="add" || type=='edit'){
			dictype.resetForm();
			$("#dictypeform").find("input").removeAttr("readOnly");
			$("#dictypeform").find("select").removeAttr("readOnly");
			cbx_enabledFlag.enabled();
		}else{
			$("#dictypeform").find("input").attr("readOnly","readOnly");
			$("#dictypeform").find("select").attr("readOnly","readOnly");
			cbx_enabledFlag.disabled(true);
		}
	},
	
	/**
	 * 编辑、查看、新增、删除 基本操作
	 */
	tab:function(type,id){
		if(type=='delete'){
			layer.confirm('确定要删除该条记录吗？', {
			  icon:3,
			  btn: ['确定', '取消'],
			  yes:function(index, layero){
				  layer.load(3);
				  common.ajaxCall(common.excuteUrl("dictypeController/deleteDictype.do"),{'typeId':id},function(ret){
					 layer.closeAll("loading");
					  if(ret.statusCode==0){
						    dictype.reset();
							layer.msg(ret.statusMessage,{icon: 1});
							dictype.loadGrid();
							layer.close(index);
						}else{
							layer.msg(ret.statusMessage,{icon: 2});
						}
					});
			  },
			  btn2: function(index, layero){
				layer.close(index);
			  },
			  cancel:function(index, layero){
				  layer.close(index);
			  }
			});
		}else{
			layer.open({
				title:"菜单信息",
				type:1,
				zIndex:99,
				shade:0.2,
				area: [(common.getWinWidth()*0.5)+'px','auto'],
				content:$("#dictypeForm"),
				btn:['确定', '取消'],
				yes:function(index, layero){
					dictype.saveOrUpdate();
					layer.close(index);
				},
				btn2: function(index, layero){
					layer.close(index);
				},
				cancel: function(index, layero){
					layer.close(index);
				},
				success: function(layero, index){
					dictype.editFlag(type);
					//编辑、查看
					if(type=="edit" || type=="see"){
						layer.load(3);
						common.ajaxCall(common.excuteUrl("/dictypeController/findByDictypeId.do"),{'typeId':id},function(ret){
							layer.closeAll("loading");
							if(ret.statusCode==0){
								dictype.bindValue(ret.result);
							}else{
								layer.msg(ret.statusMessage,{icon:2});
							}
						});
					}else{
						//获得最大序列号
						layer.load(3);
						common.ajaxCall(common.excuteUrl("/dictypeController/getMaxOrderFlag.do"),{},function(ret){
							layer.closeAll("loading");
							if(ret.statusCode==0){
								$("#orderFlag").val(ret.result);
							}else{
								layer.msg(ret.statusMessage,{icon: 2});
							}
						},{"_type":"GET"});
						
						cbx_enabledFlag.setValue("1");
					}
				}
			});
		}
	},
	
	//清除内容
	resetForm:function(){
		$('#dictypeForm').find("input").each(function(index,e){
			$(this).val("");
		})
		$('#dictypeForm').find("select").each(function(index){
			$(this).val("");
		});
		$('#dictypeForm').find("textarea").each(function(index){
			$(this).val("");
		});
		$('#dictypeForm').find("input[type='checkbox']").each(function(index){
			$(this).attr("checked",false);
		});
	},

	/**
	 * 绑定表单
	 * @param data
	 * @returns
	 */
	bindValue:function(data){
		//console.log(data);
		common.bindFormData(data);//18320204217  18320204217abcd
		cbx_enabledFlag.setValue(data.enabledFlag);
	},

	/**
	 * 保存信息
	 * @returns
	 */
	saveOrUpdate:function(){
		 var obj=common.getFormData("#dictypeForm");
		 var url="";
		 if(common.getstrlength(obj.typeId)==0){
			 url=common.excuteUrl("dictypeController/addDictype.do");
		 }else{
			 url=common.excuteUrl("dictypeController/updateDictype.do");
		 }
		 common.ajaxCall(url,obj,function(ret){
			 if(ret.statusCode==0){
				 dictype.loadGrid();
				 layer.msg(ret.statusMessage,{icon: 1},function(index){
					 dictype.resetForm();
					 layer.close(index);
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
	dictype.initPage();
	dictype.loadGrid();
});
