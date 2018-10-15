var dataGrid=null;
var cbx_enabledFlag=null;
var cbx_typeId=null;
var dictionary={
	/**
	 * 初始加载
	 */
	initPage:function(){
		dataGrid=$('#dictionaryGrid').datagrid({
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
				{field:'dicName',title:'字典名称',width:100},
				{field:'dicCode',title:'字典编码',width:100},
				{field:'typeName',title:'分类名称',width:100},
				{field:'orderFlag',title:'排序标识',width:100},
				{field:'enabledFlagStr',title:'是否可用',width:100},
				{field:'remark',title:'备注',width:100},
				{field:'createByName',title:'创建人',width:100},
				{field:'createDateStr',title:'创建日期',width:100},
				{field:'updateByName',title:'修改人',width:100},
				{field:'updateDateStr',title:'修改日期',width:100},
				{field:'opt',title:'操作',formatter:function(val,rows,index){
					var btn='<button class="layui-btn layui-btn-sm" onclick="dictionary.tab(\'see\',\''+rows.dicId+'\')"><i class="layui-icon">&#xe615;</i>查看</button>'
				    +'<button class="layui-btn layui-btn-normal layui-btn-sm" onclick="dictionary.tab(\'edit\',\''+rows.dicId+'\')"><i class="layui-icon">&#xe642;</i>编辑</button>'
				    +'<button class="layui-btn layui-btn-danger layui-btn-sm" onclick="dictionary.tab(\'delete\',\''+rows.dicId+'\')"><i class="layui-icon">&#xe640;</i>删除</button>';
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
		cbx_typeId=new SelectBox("typeCode",{
			text_field:'typeName',
			value_field:'typeCode',
			multiple:false,
			width:'100%',
			dropWidth:'200px'
		});
		cbx_param2=new SelectBox("param2",{
			text_field:'typeName',
			value_field:'typeCode',
			multiple:false,
			width:'100%',
			dropWidth:'200px'
		});
		//查询菜单分类
		layer.load(3);
		common.ajaxCall(common.excuteUrl("/dictypeController/findDictypeList.do"),{},function(ret){
			layer.closeAll("loading");
			if(ret.statusCode==0){
				cbx_typeId.bind(ret.result);
				cbx_param2.bind(ret.result);
			}else{
				layer.alert(ret.statusMessage,{icon: 2});
			}
		});
		
		cbx_enabledFlag.bind([{"id":"1","name":"是"},{"id":"0","name":"否"}]);
	},
	
	//查询按钮事件
	query:function(){
		var params={
			param0:$("#param0").val(),
			param1:$("#param1").val(),
			param2:$("#param2").val()
		}
		dictionary.loadGrid(params);
	},
	
	//清空按钮事件
	reset:function(){
		$("#param0").val("");
		$("#param1").val("");
		$("#param2").val("");
	},
	
	// 加载表格
	loadGrid:function(params){
		dataGrid.datagrid({
			url:common.excuteUrl("dictionaryController/findDictionaryByPage.do"),
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
		cbx_typeId.clear();
		dictionary.resetForm("#dictionaryForm");
		if(type=="add" || type=='edit'){
			$("#dictionaryform").find("input").removeAttr("readOnly");
			$("#dictionaryform").find("select").removeAttr("readOnly");
			cbx_enabledFlag.enabled();
			cbx_typeId.enabled();
		}else{
			$("#dictionaryform").find("input").attr("readOnly","readOnly");
			$("#dictionaryform").find("select").attr("readOnly","readOnly");
			cbx_enabledFlag.disabled(true);
			cbx_typeId.disabled(true);
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
				  common.ajaxCall(common.excuteUrl("dictionaryController/deleteDictionary.do"),{'dicId':id},function(ret){
					  layer.closeAll("loading");
					  if(ret.statusCode==0){
							layer.msg(ret.statusMessage,{icon: 1});
							dictionary.loadGrid();
							layer.close(index);
						}else{
							layer.alert(ret.statusMessage,{icon: 2});
						}
					});
			  },
			  cancel:function(index, layero){
				  layer.close(index);
			  }
			});
		}else{
			layer.open({
				title:"字典信息",
				type:1,
				zIndex:99,
				shade:0.2,
				area: [(common.getWinWidth()*0.5)+'px','auto'],
				content:$("#dictionaryForm"),
				btn:['确定', '取消'],
				yes:function(index, layero){
					dictionary.saveOrUpdate();
					dictionary.resetForm();
					layer.close(index);
				},
				btn2: function(index, layero){
					dictionary.resetForm();
					layer.close(index);
				},
				cancel: function(index, layero){
					dictionary.resetForm();
					layer.close(index);
				},
				success: function(layero, index){
					dictionary.resetForm();
					dictionary.editFlag(type);
					//编辑、查看
					if(type=="edit" || type=="see"){
						layer.load(3);
						common.ajaxCall(common.excuteUrl("/dictionaryController/findByDictionaryId.do"),{'dicId':id},function(ret){
							layer.closeAll("loading");
							if(ret.statusCode==0){
								dictionary.bindValue(ret.result);
							}else{
								layer.msg(ret.statusMessage,{icon:2});
							}
						});
					}else{
						//获得最大序列号
						common.ajaxCall(common.excuteUrl("/dictionaryController/getMaxOrderFlag.do"),{},function(ret){
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
	resetForm:function(formId){
		$(formId).find("input").each(function(index,e){
			$(this).val("");
		})
		$(formId).find("select").each(function(index){
			$(this).val("");
		});
		$(formId).find("textarea").each(function(index){
			$(this).val("");
		});
		$(formId).find("input[type='checkbox']").each(function(index){
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
		cbx_typeId.setValue(data.typeCode);
	},

	/**
	 * 保存信息
	 * @returns
	 */
	saveOrUpdate:function(){
		 var obj=common.getFormData("#dictionaryForm");
		 var url="";
		 if(common.getstrlength(obj.dicId)==0){
			 url=common.excuteUrl("dictionaryController/addDictionary.do");
		 }else{
			 url=common.excuteUrl("dictionaryController/updateDictionary.do");
		 }
		 common.ajaxCall(url,obj,function(ret){
			 if(ret.statusCode==0){
				 layer.msg(ret.statusMessage,{icon: 1});
				 dictionary.resetForm();
				 dictionary.loadGrid();
			 }else{
				 layer.alert(ret.statusMessage,{icon: 2});
			 }
		 });
	}
}


/**
 * 初始加载
 * @returns
 */
$(document).ready(function(){
	dictionary.initPage();
	dictionary.loadGrid();
});
