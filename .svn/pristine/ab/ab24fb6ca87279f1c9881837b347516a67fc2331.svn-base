var dataGrid=null;
var cbx_enabledFlag=null;
var treeObj=null;
var lbx_select=null;
var lbx_noselect=null;
var role={
	initPage:function(){
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
		    idField:"roleId",
		    columns:[[
				{field:'roleName',title:'角色名称',width:100},
				{field:'orderFlag',title:'排序标识',width:100},
				{field:'enabledFlagStr',title:'是否可用',width:100},
				{field:'remark',title:'备注',width:100},
				{field:'createByName',title:'创建人',width:100},
				{field:'createDateStr',title:'创建日期',width:100},
				{field:'updateByName',title:'修改人',width:100},
				{field:'updateDateStr',title:'修改日期',width:100},
				{field:'opt',title:'操作',formatter:function(val,rows,index){
					var btn='<button class="layui-btn layui-btn-sm" onclick="role.tab(\'see\',\''+rows.roleId+'\')"><i class="layui-icon">&#xe615;</i>查看</button>'
				    +'<button class="layui-btn layui-btn-normal layui-btn-sm" onclick="role.tab(\'edit\',\''+rows.roleId+'\')"><i class="layui-icon">&#xe642;</i>编辑</button>'
				    +'<button class="layui-btn layui-btn-danger layui-btn-sm" onclick="role.tab(\'delete\',\''+rows.roleId+'\')"><i class="layui-icon">&#xe640;</i>删除</button>'
					+'<button class="layui-btn layui-btn-danger layui-btn-sm" onclick="role.tab(\'power\',\''+rows.roleId+'\')"><i class="layui-icon">&#xe642;</i>权限</button>';
				    return btn;
				}}
		    ]]
		});
		
		cbx_enabledFlag=new SelectBox("enabledFlag",{
			text_field:'name',
			value_field:'id',
			multiple:false,
			width:'190px',
		});
		lbx_select=new ListBox("selected",{height:"440px",width:"346px",onclickEvent:role.selectclick,showtitle:true});
		lbx_noselect=new ListBox("noselected",{height:"440px",width:"346px",onclickEvent:role.noselectclick,showtitle:true});
	},
	
	//查询按钮事件
	query:function(){
		var params={
			param0:$("#param0").val()
		}
		role.loadGrid(params);
	},
	
	//清空按钮事件
	reset:function(){
		$("#param0").val("");
	},
	
	//加载表格
	loadGrid:function(params){
		dataGrid.datagrid({
			url:common.excuteUrl("roleController/findRoleByPage.do"),
			queryParams:params,
			method:"post"
		});
	},
	
	/**
	 * 初始化树形caidan
	 * id
	 */
	/*initTree:function(_id,_data,config){
		if(!common.isNotBlank(_id)){
			layer.alert("显示节点树形节点不能为空",{icon:2});
			return;
		}
		var setting = {
			view: {
				selectedMulti: false,
				showIcon: true,
				showLine: true,
				showTitle:true
			},
			data:{
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "pId",
					rootPId: 0
				}
			},
			callback : { //返回函数; 根据需求选择合适的监听事件  //以下事件默认权威null 事件例子参见第83行
				beforeAsync : null,//异步加载之前的事件回调函数，zTree 根据返回值确定是否允许进行异步加载
				beforeCheck : null,//勾选 或 取消勾选 之前的事件回调函数，并且根据返回值确定是否允许 勾选 或 取消勾选
				beforeClick : null,//单击节点之前的事件回调函数，并且根据返回值确定是否允许单击操作
				beforeCollapse : null,//父节点折叠之前的事件回调函数，并且根据返回值确定是否允许折叠操作
				beforeDblClick : null,// zTree 上鼠标双击之前的事件回调函数，并且根据返回值确定触发 onDblClick 事件回调函数
				beforeDrag : null,//节点被拖拽之前的事件回调函数，并且根据返回值确定是否允许开启拖拽操作
				beforeDragOpen : null,//拖拽节点移动到折叠状态的父节点后，即将自动展开该父节点之前的事件回调函数，并且根据返回值确定是否允许自动展开操作
				beforeDrop : null,//节点拖拽操作结束之前的事件回调函数，并且根据返回值确定是否允许此拖拽操作
				beforeEditName : null,//节点编辑按钮的 click 事件，并且根据返回值确定是否允许进入名称编辑状态
				beforeExpand : null,//父节点展开之前的事件回调函数，并且根据返回值确定是否允许展开操作
				beforeMouseDown : null,// zTree 上鼠标按键按下之前的事件回调函数，并且根据返回值确定触发 onMouseDown 事件回调函数
				beforeMouseUp : null,//zTree 上鼠标按键松开之前的事件回调函数，并且根据返回值确定触发 onMouseUp 事件回调函数
				beforeRemove : null,//节点被删除之前的事件回调函数，并且根据返回值确定是否允许删除操作
				beforeRename : null,//节点编辑名称结束（Input 失去焦点 或 按下 Enter 键）之后，更新节点名称数据之前的事件回调函数，并且根据返回值确定是否允许更改名称的操作
				beforeRightClick : null,// zTree 上鼠标右键点击之前的事件回调函数，并且根据返回值确定触发 onRightClick 事件回调函数
				onAsyncError : null,//异步加载出现异常错误的事件回调函数
				onAsyncSuccess : null,//异步加载正常结束的事件回调函数
				onCheck : null,// checkbox / radio 被勾选 或 取消勾选的事件回调函数
				onClick : null,//节点被点击的事件回调函数
				onCollapse : null,//节点被折叠的事件回调函数
				onDblClick : null,// zTree 上鼠标双击之后的事件回调函数
				onDrag : null,//节点被拖拽的事件回调函数
				onDragMove : null,//节点被拖拽过程中移动的事件回调函数
				onDrop : null,//节点拖拽操作结束的事件回调函数
				onExpand : null,//节点被展开的事件回调函数
				onMouseDown : null,// zTree 上鼠标按键按下后的事件回调函数
				onMouseUp : null,// zTree 上鼠标按键松开后的事件回调函数
				onNodeCreated : null,//节点生成 DOM 后的事件回调函数
				onRemove : null,//删除节点之后的事件回调函数。
				onRename : null,//节点编辑名称结束之后的事件回调函数。
				onRightClick : null// zTree 上鼠标右键点击之后的事件回调函数
			}
		};
		if(common.isNotBlank(config)){
			for (var item in config) {
				setting[item]=config[item]
			}
		}
		return $.fn.zTree.init($(_id),setting,_data);
	},*/
	
	/**
	 * 点击树事件
	 */
	/*selTree:function(){
		common.ajaxCall(common.excuteUrl("menuController/findTree.do"),{},function(ret){
			if(ret.statusCode==0){
				//页面层
				var index=layer.open({
					title:"选择上级菜单",
					type:1,
					skin: 'layui-layer-rim', //加上边框
					zIndex:99,
					shade:0.2,
					area: ['250px','300px'],
					content:$("#demo"),
					btn:['确定','取消'],
					yes:function(){
						var nodes = treeObj.getSelectedNodes();
						if(common.isNotBlank(nodes)){
							$("#parentName").val(nodes[0].name);
							$("#parentId").val(nodes[0].id);
							menu.removeTree();
						}
						//console.log(nodes);
						layer.close(index);
					},
					btn2: function(index, layero){
						layer.close(index);
						menu.removeTree();
					},
					cancel: function(index, layero){
						layer.close(index);
						menu.removeTree();
					},
					success: function(layero, index){
						treeObj=menu.initTree("#tree",ret.result);
					}
				});
			}else{
				layer.alert(ret.statusMessage,{icon: 2});
			}
		});
	},*/
	
	/**
	 * 移除树
	 */
	/*removeTree:function(nodes){
		var nodes = treeObj.getSelectedNodes();
		if(common.isNotBlank(nodes)){
			for (var i=0, l=nodes.length; i < l; i++) {
				treeObj.removeNode(nodes[i]);
			}
		}
		treeObj.destroy();
	},*/
	
	//清空
	resetGrid:function(){
		dataGrid.datagrid("loadData",{
			total:0,
	        rows:[]
		});
	},
	
	editFlag:function(type){
		if(type=="add" || type=='edit'){
			$("#roleform").find("input").removeAttr("readOnly");
			$("#roleform").find("textarea").removeAttr("readOnly");
			role.resetForm();
			cbx_enabledFlag.enabled();
		}else{
			$("#roleform").find("input").attr("readOnly","readOnly");
			$("#roleform").find("textarea").attr("readOnly","readOnly");
			cbx_enabledFlag.disabled(true)
		}
	},
	
	//增删改查 操作
	tab:function(type,id){
		//删除
		if(type=='delete'){
			layer.confirm('确定要删除该条记录吗？', {
			  icon:3,
			  btn: ['确定', '取消'],
			  yes:function(index, layero){
				  layer.load(3);
				  common.ajaxCall(common.excuteUrl("roleController/deleteRole.do"),{'typeId':id},function(ret){
					  layer.closeAll("loading");
					  if(ret.statusCode==0){
							layer.alert("删除成功",{icon:1});
							role.loadGrid();
							layer.close(index);
							layer.closeAll('dialog');
						}else{
							layer.alert(ret.statusMessage,{icon:2});
						}
					});
			  },
			  cancel:function(index, layero){
				  layer.close(index);
			  }
			});
		}else if(type=="power"){//赋权
			//页面层
			layer.open({
				type:1,
				area:['850px','400px'], //宽高
				content:$("#maincontent"),
				btn:["确定","取消"],
				yes:function(index,layero){
					var ids="";
					var selectItems=lbx_select.getItems();
					if (selectItems!=null&&selectItems.length>0){
						for (var i=0;i<selectItems.length;i++){								
							if (ids==""){
								ids=selectItems[i].value;
							} else {
								ids=ids+","+selectItems[i].value;
							}
										
						}
					}
					//console.log(ids);
					var obj=new Object();
					obj.userId=ids;
					obj.roleId=id;
					layer.load(3);
					common.ajaxCall(common.excuteUrl("roleController/saveUserRole.do"),obj,function(ret){
						layer.closeAll("loading");
						layer.close(index);	
						 if(ret.statusCode==0){
							 lbx_noselect.removeAll();
							 lbx_select.removeAll();
							 layer.alert(ret.statusMessage,{icon:1});
						 }else{
							 layer.alert(ret.statusMessage,{icon:2});
						 }
					 });
				},
				btn2: function(index, layero){
					lbx_noselect.removeAll();
					lbx_select.removeAll();
					layer.close(index);
				},
				cancel: function(index, layero){
					lbx_noselect.removeAll();
					lbx_select.removeAll();
					layer.close(index);
				},
				success: function(layero, index){
					//加载左边菜单
					layer.load(3);
					common.ajaxCall(common.excuteUrl("/userController/findNoSelectRole.do"),{'roleId':id},function(ret){
						layer.closeAll("loading");
						if(ret.statusCode==0){
							var selectItems=lbx_select.getItems();
							for (var i=0;i<ret.result.length;i++){
								var isfind=false;
								if (selectItems!=null&&selectItems.length>0){
									isfind=role.checkExists(selectItems,ret.result[i].staffId);
									if (isfind==false){
										var obj=new Object();
										obj.value=ret.result[i].staffId;
										obj.text=ret.result[i].staffName+"("+ret.result[i].sysFlag+")";
										lbx_noselect.addItem(obj);
									} 
								} else {
									var obj=new Object();
									obj.value=ret.result[i].staffId;
									obj.text=ret.result[i].staffName+"("+ret.result[i].sysFlag+")";
									lbx_noselect.addItem(obj);
								}
							}
						}else{
							layer.alert(ret.statusMessage,{icon: 2});
						}
					});
					//加载左右边菜单
					layer.load(3);
					common.ajaxCall(common.excuteUrl("/userController/findSelectRole.do"),{'roleId':id},function(ret){
						layer.closeAll("loading");
						if(ret.statusCode==0){
							//console.log(ret);
							var selectItems=lbx_select.getItems();
							for (var i=0;i<ret.result.length;i++){
								var isfind=false;
								if (selectItems!=null&&selectItems.length>0){
									isfind=role.checkExists(selectItems,ret.result[i].staffId);
									if (isfind==false){
										var obj=new Object();
										obj.value=ret.result[i].staffId;
										obj.text=ret.result[i].staffName+"("+ret.result[i].sysFlag+")";
										lbx_select.addItem(obj);
									} 
								} else {
									var obj=new Object();
									obj.value=ret.result[i].staffId;
									obj.text=ret.result[i].staffName+"("+ret.result[i].sysFlag+")";
									lbx_select.addItem(obj);
								}
							}
						}else{
							layer.alert(ret.statusMessage,{icon: 2});
						}
					});
				}
			});
		}else{
			//页面层
			layer.open({
				type:1,
				area: [(common.getWinWidth()*0.48)+'px','auto'],
				content:$("#roleform"),
				btn:["确定","取消"],
				yes:function(index,layero){
					role.saveOrUpdate();
					layer.close(index);
				},
				btn2: function(index, layero){
					layer.close(index);
				},
				cancel: function(index, layero){
					layer.close(index);
				},
				success: function(layero, index){
					role.editFlag(type);
					if(type=="edit" || type=="see"){
						layer.load(3);
						common.ajaxCall(common.excuteUrl("/roleController/findById.do"),{'roleId':id},function(ret){
							layer.closeAll("loading");
							if(ret.statusCode==0){
								role.bindValue(ret.result);
							}else{
								alert(ret.statusMessage);
							}
						});
					}else{
						cbx_enabledFlag.bind([{"id":"1","name":"是"},{"id":"0","name":"否"}]);
					}
				}
			});
		}
	},
	
	//清除内容
	resetForm:function(){
		$('#roleform').find("input").each(function(index,e){
			$(this).val("");
		})
		$('#roleform').find("select").each(function(index){
			$(this).val("");
		});
		$('#roleform').find("textarea").each(function(index){
			$(this).val("");
		});
		$('#roleform').find("input[type='checkbox']").each(function(index){
			$(this).attr("checked",false);
		});
	},

	
	checkExists:function(selectItems,value){
		var isfind=false;
		for (var i=0;i<selectItems.length;i++){
			if (selectItems[i].value==value){
				isfind=true;
				break;
			} 
		}
		return isfind;
	},
	
	//已选中列表
	selectclick:function(value,text,id){
		var obj=new Object();
		obj.value=value;
		obj.text=text;
		lbx_noselect.addItem(obj);
		lbx_select.remove(id);
	},
	//位选中列表
	noselectclick:function(value,text,id){
		var obj=new Object();
		obj.value=value;
		obj.text=text;
		lbx_select.addItem(obj);
		lbx_noselect.remove(id);
	},
	//全部选中
	selectAll:function(){
		var selectItems=lbx_noselect.getItems();
		if (selectItems!=null&&selectItems.length>0){
			for (var i=0;i<selectItems.length;i++){
				var obj=new Object();
				obj.value=selectItems[i].value;
				obj.text=selectItems[i].text;
				lbx_select.addItem(obj);
			}
		}
		lbx_noselect.removeAll();
	},
	
	//全部位选中
	unselectAll:function(){
		var selectItems=lbx_select.getItems();
		if (selectItems!=null&&selectItems.length>0){
			for (var i=0;i<selectItems.length;i++){
				var obj=new Object();
				obj.value=selectItems[i].value;
				obj.text=selectItems[i].text;
				lbx_noselect.addItem(obj);
			}
		}
		lbx_select.removeAll();
	},
	
	
	/**
	 * 保存
	 */
	saveOrUpdate:function(){
		 var obj=common.getFormData("#roleform");
		 var url="";
		 if(common.getstrlength(obj.roleId)==0){
			 url=common.excuteUrl("roleController/addRole.do");
		 }else{
			 url=common.excuteUrl("roleController/updateRole.do");
		 }
		 common.ajaxCall(url,obj,function(ret){
			 if(ret.statusCode==0){
				 role.loadGrid();
				 layer.alert(ret.statusMessage,{icon:1});
			 }else{
				 layer.alert(ret.statusMessage,{icon:2});
			 }
		 });
	},
	
	/**
	 * 绑定表单
	 * @param data
	 * @returns
	 */
	bindValue:function(data){
		//console.log(data.typeName);
		common.bindFormData(data);//18320204217  18320204217abcd
		//cbx_param0.setValue(data.enabledFlag);
		//cbx_isSysMenu.setValue(data.isSysMenu);
	}
}

$(document).ready(function(){
	role.initPage();
	role.loadGrid();
});