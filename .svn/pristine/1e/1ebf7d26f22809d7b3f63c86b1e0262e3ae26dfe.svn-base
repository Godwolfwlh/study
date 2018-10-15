var roleGrid=null;
var menuGrid=null;
var treeObj=null;
var roleMenu={
	initPage:function(){
		roleGrid=$('#roleGrid').datagrid({
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
		    singleSelect:true,
		    checkOnSelect:true,
		    rowStyler: function(index,row){
				return 'cursor:pointer'; // return inline style
			},
		    columns:[[
				{field:'roleId',checkbox:true},
				{field:'roleName',title:'角色名称',width:100},
				{field:'createByNam',title:'创建人',width:100},
				{field:'createDateStr',title:'创建日期',width:100},
				{field:'opt',title:'操作',formatter:function(val,rows,index){
					return '<button class="layui-btn layui-btn-danger layui-btn-sm" onclick="roleMenu.selTree(\''+rows.roleId+'\')"><i class="layui-icon">&#xe642;</i>权限</button>';
				}}
		    ]],
		    onCheck:function(rowIndex,field){
		    	$("#roleId").val(field.roleId)
		    	var _data={roleId:field.roleId};
		    	//roleMenu.selTree();
		    	roleMenu.loadMenuGrid(_data);
		    }
		});
		
		menuGrid=$('#menuGrid').datagrid({
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
		    idField:"menuId",
		    columns:[[
				{field:'menuName',title:'菜单名称',width:100},
				{field:'parentName',title:'所属上级',width:100},
				{field:'parentId',title:'是否父节点',width:100,
					formatter:function(v,r,index){
						if(!common.isNotBlank(r.parentId) || r.parentId=='-1' || r.parentId=='0'){
							return "是";
						}else{
							return "否";
						}
					}
				},
				{field:'opt',title:'操作',formatter:function(val,rows,index){
					return '<button class="layui-btn layui-btn-danger layui-btn-sm" onclick="roleMenu.delMenuIdRoleId(\''+rows.menuId+'\')"><i class="layui-icon">&#xe640;</i>删除</button>';
				}}
		    ]]
		});
	},
	
	loadGrid:function(params){
		roleGrid.datagrid({
			url:common.excuteUrl("roleController/findRoleByPage.do"),
			queryParams:params,
			method:"post"
		});
	},
	
	loadMenuGrid:function(params){
		menuGrid.datagrid({
			url:common.excuteUrl("/menuController/findMenuByRoleId.do"),
			queryParams:params,
			method:"post"
		});
	},
	
	//删除角色菜单中间表
	delMenuIdRoleId:function(menuId){
		layer.confirm('确定要删除该条记录吗？', {
		  icon:3,
		  btn: ['确定', '取消'],
		  yes:function(index, layero){
			  layer.load(3);
			  common.ajaxCall(common.excuteUrl("roleController/deleteRoleIdMenuId.do"),{'menuId':menuId,'roleId':$("#roleId").val()},function(ret){
				  layer.closeAll("loading");
				  if(ret.statusCode==0){
						layer.msg("删除成功",{icon:1},function(index1,layero){
							var _data={'roleId':$("#roleId").val()};
							roleMenu.loadMenuGrid(_data);
							layer.close(index1);
							layer.close(index);
						});
					}else{
						layer.msg(ret.statusMessage,{icon:2});
					}
				});
		  },
		  function(index, layero){
			  layer.close(index);
		  },
		  cancel:function(index, layero){
			  layer.close(index);
		  }
		});
	},
	
	/**
	 * 初始化树形caidan
	 * id
	 */
	initTree:function(_id,_data,config){
		if(!common.isNotBlank(_id)){
			layer.msg("显示节点树形节点不能为空",{icon:2});
			return;
		}
		var setting = {
			view: {
				selectedMulti: true,
				showIcon: true,
				showLine: true,
				showTitle:true
			},
			data:{
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "pid",
					rootPId: 0
				}
			},
			check: {
				enable:true,
				autoCheckTrigger: true,
				chkStyle: "checkbox",
				chkboxType: { "Y": "p", "N": "s" },
				nocheckInherit:false
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
	},
	
	/**
	 * 点击树事件
	 */
	selTree:function(roleId){
		layer.open({
			title:"请选择菜单",
			type:1,
			skin: 'layui-layer-rim', //加上边框
			zIndex:99,
			shade:0.2,
			area: [(common.getWinWidth()*0.3)+'px',(common.getWinHeight()*0.8)+'px'],
			content:$("#settingRoleTree"),
			btn:['确定','取消'],
			yes:function(index,l){
				//var nodes = treeObj.getSelectedNodes();
				var nodes = treeObj.getCheckedNodes(true);
				if(!common.isNotBlank(nodes) || nodes.length==0){
					layer.msg("请选择菜单信息",{icon:2});
					return;
				}
				//console.log(nodes);
				var menuIds="";
				var pIds="";
				for(var item in nodes){
					if(item==0){
						menuIds=nodes[item].id;
					}else{
						menuIds+=","+nodes[item].id;
					}
				}
				//保存菜单角色信息
				layer.load(3);
				var params={'menuId':menuIds,'roleId':roleId};
				common.ajaxCall(common.excuteUrl("menuController/addMenuRole.do"),params,function(ret1){
					layer.closeAll("loading");
					if(ret1.statusCode==0){
						layer.msg(ret1.statusMessage,{icon:1},function(index1,l){
							roleMenu.removeTree();
							roleMenu.loadGrid();
							layer.close(index);
							layer.close(index1);
						});
					}else{
						layer.msg(ret1.statusMessage,{icon:2});
					}
				});
			},
			btn2: function(index, layero){
				roleMenu.removeTree();
				layer.close(index);
			},
			cancel: function(index, layero){
				roleMenu.removeTree();
				layer.close(index);
			},
			success: function(layero, index){
				//加载树
				layer.load(3);
				common.ajaxCall(common.excuteUrl("menuController/findTreeNode.do"),{},function(ret){
					layer.closeAll("loading");
					if(ret.statusCode==0){
						treeObj=roleMenu.initTree("#tree",ret.result);
						layer.load(3);
						common.ajaxCall(common.excuteUrl("menuController/queryMenuByRoleId.do"),{'roleId':roleId},function(ret2){
							layer.closeAll("loading");
							if(ret2.statusCode==0){
								//设置选中
								if(common.isNotBlank(ret2.result)){
									for(var item in ret2.result){
										var node = treeObj.getNodeByParam("id",ret2.result[item].menuId, null);
										if(common.isNotBlank(node)){
											treeObj.checkNode(node,true);
										}
									}
								}
							}else{
								layer.msg(ret2.statusMessage,{icon: 2});
							}
						});
					}else{
						layer.msg(ret.statusMessage,{icon: 2});
					}
				});
			}
		});
	},
	
	/**
	 * 移除树
	 */
	removeTree:function(){
		//debugger
		//var nodes = treeObj.getSelectedNodes();
		var nodes = treeObj.getCheckedNodes(true);
		/*if(common.isNotBlank(nodes)){
			for (var i=0, l=nodes.length; i < l; i++) {
				treeObj.removeNode(nodes[i]);
			}
		}*/
		for (var i=0, l=nodes.length; i < l; i++) {
			treeObj.cancelSelectedNode(nodes[i]);
		}
		treeObj.destroy();
	},
	
	//清空
	resetGrid:function(){
		dataGrid.datagrid("loadData",{
			total:0,
	        rows:[]
		});
	}
}

$(document).ready(function(){
	roleMenu.initPage();
	roleMenu.loadGrid();
});