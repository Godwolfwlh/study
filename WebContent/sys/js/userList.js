var dataGrid=null;
var cbx_param0=null;
var cbx_enabledFlag=null;
var cbx_post=null;
var cbx_staffState=null;
var cbx_param4=null;
var cbx_gender=null;
var cbx_param3=null;
var cbx_education=null;
var cbx_isSysFlag=null;
var cbx_cbx_param2=null;
var userList={
	initLoad:function(){
		//表格
		dataGrid=$('#dg').datagrid({
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
		    idField:"staffId",
		    columns:[[
				{field:'staffNo',title:'登录账号',width:100},
				{field:'staffName',title:'用户名称',width:100},
				{field:'gender',title:'性别',width:100},
				{field:'post',title:'职务',width:100},
				{field:'linkPhone',title:'联系电话',width:100},
				{field:'emailAddr',title:'邮箱',width:100},
				{field:'qq',title:'qq号',width:100},
				{field:'wxCode',title:'微信号',width:100},
				{field:'orderFlag',title:'排序标识',width:100},
				{field:'enabledFlagStr',title:'是否可用',width:100},
				{field:'remark',title:'备注',width:100},
				{field:'createByName',title:'创建人',width:100},
				{field:'createDateStr',title:'创建日期',width:100},
				{field:'updateByName',title:'修改人',width:100},
				{field:'updateDateStr',title:'修改日期',width:100},
				{field:'opt',title:'操作',formatter:function(val,rows,index){
					var btn='<button class="layui-btn layui-btn-sm" onclick="userList.tab(\'see\',\''+rows.staffId+'\')"><i class="layui-icon">&#xe615;</i>查看</button>'
				    +'<button class="layui-btn layui-btn-normal layui-btn-sm" onclick="userList.tab(\'edit\',\''+rows.staffId+'\')"><i class="layui-icon">&#xe642;</i>编辑</button>'
				    +'<button class="layui-btn layui-btn-danger layui-btn-sm" onclick="userList.tab(\'delete\',\''+rows.staffId+'\')"><i class="layui-icon">&#xe640;</i>删除</button>';
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
		cbx_isSysFlag=new SelectBox("isSysFlag",{
			text_field:'dicName',
			value_field:'dicCode',
			multiple:false,
			width:'100%',
			dropWidth:'200px'
		});
		cbx_param2=new SelectBox("param2",{
			text_field:'dicName',
			value_field:'dicCode',
			multiple:false,
			width:'100%',
			dropWidth:'200px'
		});
		cbx_staffState=new SelectBox("staffState",{
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
		cbx_post=new SelectBox("post",{
			text_field:'dicName',
			value_field:'dicCode',
			multiple:false,
			width:'100%',
			dropWidth:'200px'
		});
		cbx_gender=new SelectBox("gender",{
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
		cbx_education=new SelectBox("education",{
			text_field:'dicName',
			value_field:'dicCode',
			multiple:false,
			width:'100%',
			dropWidth:'200px'
		});
		
		var codeType='ENABLED_FLAG,EDUCATION,POST,GENDER,STAFF_STATE,IS_SYSF_LAG';
		layer.load(3);
		common.ajaxCall(common.excuteUrl("dictionaryController/findByTypeCode.do"),{'typeCode':codeType},function(ret){
			layer.closeAll("loading");
			if(ret.statusCode==0){
				cbx_enabledFlag.bind(ret.result.ENABLED_FLAG);
				cbx_post.bind(ret.result.POST);
				cbx_gender.bind(ret.result.GENDER);
				cbx_param3.bind(ret.result.GENDER);
				cbx_education.bind(ret.result.EDUCATION);
				cbx_staffState.bind(ret.result.STAFF_STATE);
				cbx_param4.bind(ret.result.STAFF_STATE);
				cbx_isSysFlag.bind(ret.result.IS_SYSF_LAG);
				cbx_param2.bind(ret.result.IS_SYSF_LAG);
			}else{
				layer.msg(ret.statusMessage,{icon: 2});
			}
		});
	},
	
	//加载表格
	loadGrid:function(params){
		dataGrid.datagrid({
			url:common.excuteUrl("/userController/findByPage.do"),
			queryParams:params,
			method:'post'
		});
	},
	
	//清空表格
	resetGrid:function(){
		dataGrid.datagrid("loadData",{
			total:0,
	        rows:[]
		});
	},
	
	//查询按钮事件
	query:function(){
		var params={
			param0:$("#param0").val(),
			param1:$("#param1").val(),
			param2:$("#param2").val(),
			param3:$("#param3").val(),
			param4:$("#param4").val()
		}
		userList.loadGrid(params);
	},
	
	//清空按钮事件
	reset:function(){
		$("#param0").val("");
		$("#param1").val("");
		$("#param2").val("");
		$("#param3").val("");
		$("#param4").val("");
	},
	
	/**
	 * 表单内容启用、禁用
	 * @param type
	 * @returns
	 */
	editFlag:function(type){
		cbx_enabledFlag.clear();
		cbx_post.clear();
		cbx_staffState.clear();
		cbx_gender.clear();
		cbx_education.clear();
		cbx_isSysFlag.clear();
		
		if(type=="add" || type=='edit'){
			$("#userEditForm").find("input").removeAttr("readOnly");
			$("#userEditForm").find("select").removeAttr("readOnly");
			userList.resetForm();
			cbx_enabledFlag.enabled();
			cbx_post.enabled();
			cbx_staffState.enabled();
			cbx_gender.enabled();
			cbx_education.enabled();
			cbx_isSysFlag.enabled();
		}else{
			$("#userEditForm").find("input").attr("readOnly","readOnly");
			$("#userEditForm").find("select").attr("readOnly","readOnly");
			cbx_enabledFlag.disabled(true)
			cbx_post.disabled(true)
			cbx_staffState.disabled(true)
			cbx_gender.disabled(true)
			cbx_education.disabled(true)
			cbx_isSysFlag.disabled(true)
		}
	},
	
	//增删改查 操作
	tab:function(type,id){
		if(type=='delete'){
			layer.confirm('确定要删除该条记录吗？', {
			  icon:3,
			  btn: ['确定', '取消'],
			  yes:function(index, layero){
				  layer.load(3);
				  common.ajaxCall(common.excuteUrl("/userController/removeUser.do"),{'staffId':id},function(ret){
					    layer.closeAll("loading");
						if(ret.statusCode==0){
							layer.msg(ret.statusMessage,{icon: 1});
							userList.loadGrid();
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
				title:"用户信息编辑",
				type:1,
				zIndex:99,
				shade:0.2,
				area: [(common.getWinWidth()*0.6)+'px',(common.getWinHeight()*0.8)+'px'],
				content:$("#userEditForm"),
				btn:['确定', '取消'],
				yes:function(index, layero){
					userList.saveOrUpdate();
					cbx_enabledFlag.clear();
					cbx_post.clear();
					cbx_staffState.clear();
					cbx_gender.clear();
					cbx_education.clear();
					cbx_isSysFlag.clear();
					layer.close(index);
				},
				btn2: function(index, layero){
					userList.resetForm();
					cbx_enabledFlag.clear();
					cbx_post.clear();
					cbx_staffState.clear();
					cbx_gender.clear();
					cbx_education.clear();
					cbx_isSysFlag.clear();
					layer.close(index);
				},
				cancel: function(index, layero){
					userList.resetForm();
					cbx_enabledFlag.clear();
					cbx_post.clear();
					cbx_staffState.clear();
					cbx_gender.clear();
					cbx_education.clear();
					cbx_isSysFlag.clear();
					layer.close(index);
				},
				success: function(layero, index){
					userList.editFlag(type);
					//编辑、查看
					if(type=="edit" || type=="see"){
						layer.load(3);
						common.ajaxCall(common.excuteUrl("/userController/findById.do"),{'staffId':id},function(ret){
							layer.closeAll("loading");
							if(ret.statusCode==0){
								userList.bindValue(ret.result);
							}else{
								layer.msg(ret.statusMessage,{icon:2});
							}
						});
					}else{
						//获得最大序列号
						layer.load(3);
						common.ajaxCall(common.excuteUrl("/userController/getMaxOrderFlag.do"),{},function(ret){
							layer.closeAll("loading");
							if(ret.statusCode==0){
								$("#orderFlag").val(ret.result);
							}else{
								layer.msg(ret.statusMessage,{icon: 2});
							}
						});
					}
				}
			});
		}
	},
	//清除内容
	resetForm:function(){
		$('#userEditForm').find("input").each(function(index,e){
			$(this).val("");
		})
		$('#userEditForm').find("select").each(function(index){
			$(this).val("");
		});
		$('#userEditForm').find("textarea").each(function(index){
			$(this).val("");
		});
		$('#userEditForm').find("input[type='checkbox']").each(function(index){
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
		cbx_post.setValue(data.post);
		cbx_staffState.setValue(data.staffState);
		cbx_gender.setValue(data.gender);
		cbx_education.setValue(data.education);
		cbx_isSysFlag.setValue(data.isSysFlag);
	},

	/**
	 * 保存信息
	 * @returns
	 */
	saveOrUpdate:function(){
		 var obj=common.getFormData("#userEditForm");
		 var url="";
		 if(common.getstrlength(obj.staffId)==0){
			 url=common.excuteUrl("userController/addUser.do");
		 }else{
			 url=common.excuteUrl("userController/updateUser.do");
		 }
		 layer.load(3);
		 common.ajaxCall(url,obj,function(ret){
			 layer.closeAll("loading");
			 if(ret.statusCode==0){
				 userList.resetForm();
				 userList.loadGrid();
				 layer.msg(ret.statusMessage,{icon: 1});
			 }else{
				 layer.msg(ret.statusMessage,{icon: 2});
			 }
		 });
	}
}

$(document).ready(function(){
	userList.initLoad();
	userList.loadGrid({});
});