var dataGrid=null;
var cbx_param0=null;
var cbx_isSysMenu=null;
var cbx_isOpen=null;
var treeObj=null;//编辑对话框树形菜单
var paramsTreeObj=null;
var menu={
	/**
	 * 初始加载
	 */
	init:function(){
		dataGrid=$('#menuGrid').datagrid({
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
		    autoRowHeight:false,
		    queryParams:{},
		    idField:"menuId",
		    columns:[[
				{field:'menuName',title:'菜单名称',width:100},
				{field:'url',title:'菜单url',width:180},
				{field:'parentName',title:'所属上级',width:100},
				{field:'css',title:'样式',width:120},
				{field:'isOpen',title:'是否打开',width:80},
				{field:'orderFlag',title:'排序标识',width:80},
				{field:'enabledFlagStr',title:'是否可用',width:80},
				{field:'remark',title:'备注',width:100},
				{field:'createByName',title:'创建人',width:80},
				{field:'createDateStr',title:'创建日期',width:120},
				{field:'updateByName',title:'修改人',width:80},
				{field:'updateDateStr',title:'修改日期',width:120},
				{field:'opt',title:'操作',formatter:function(val,rows,index){
					var btn='<button class="layui-btn layui-btn-sm" onclick="menu.tab(\'see\',\''+rows.menuId+'\')"><i class="layui-icon">&#xe615;</i>查看</button>'
				    +'<button class="layui-btn layui-btn-normal layui-btn-sm" onclick="menu.tab(\'edit\',\''+rows.menuId+'\')"><i class="layui-icon">&#xe642;</i>编辑</button>'
				    +'<button class="layui-btn layui-btn-danger layui-btn-sm" onclick="menu.tab(\'delete\',\''+rows.menuId+'\')"><i class="layui-icon">&#xe640;</i>删除</button>';
				    return btn;
				}}
		    ]]
		});
		
		cbx_param0=new SelectBox("enabledFlag",{
			text_field:'name',
			value_field:'id',
			multiple:false,
			width:'100%',
			dropWidth:'200px'
		});
		cbx_isSysMenu=new SelectBox("isSysMenu",{
			text_field:'name',
			value_field:'id',
			multiple:false,
			width:'100%',
			dropWidth:'200px'
		});
		cbx_isOpen=new SelectBox("isOpen",{
			text_field:'name',
			value_field:'id',
			multiple:false,
			width:'100%',
			dropWidth:'200px'
		});
		cbx_param0.bind([{"id":"1","name":"是"},{"id":"0","name":"否"}]);
		cbx_isSysMenu.bind([{"id":"1","name":"是"},{"id":"0","name":"否"}]);
		cbx_isOpen.bind([{"id":"1","name":"是"},{"id":"0","name":"否"}]);
	},
	
	//条件查询树形菜单
	paramOpenTree:function(){
		layer.load(3);
		common.ajaxCall(common.excuteUrl("/menuController/findTree.do"),{},function(ret){
			layer.closeAll("loading");
			if(ret.statusCode==0){
				//页面层
				layer.open({
					title:"菜单信息",
					type:1,
					skin: 'layui-layer-rim', //加上边框
					zIndex:99,
					shade:0.2,
					area: ['250px','300px'],
					content:$("#paramDemo"),
					btn:['确定','取消','清空'],
					yes:function(index1,la){
						var nodes = paramsTreeObj.getSelectedNodes();
						if(common.isNotBlank(nodes)){
							$("#param1Name").val(nodes[0].name);
							$("#param1").val(nodes[0].id);
							menu.removeTree(paramsTreeObj);
						}
						//console.log(nodes);
						layer.close(index1);
					},
					btn2: function(index1, layero){
						layer.close(index1);
						menu.removeTree(paramsTreeObj);
					},
					btn3: function(index1, layero){
						$("#param1Name").val("");
						$("#param1").val("");
					},
					cancel: function(index1, layero){
						layer.close(index1);
						menu.removeTree(paramsTreeObj);
					},
					success: function(layero, index){
						paramsTreeObj=menu.initTree("#paramTree",ret.result);
					}
				});
			}else{
				layer.msg(ret.statusMessage,{icon: 2});
			}
		});
	},
	
	//查询按钮事件
	query:function(){
		var params={
			param0:$("#param0").val(),
			param1:$("#param1").val()
		}
		menu.loadGrid(params);
	},
	
	//清空按钮事件
	reset:function(){
		$("#param0").val("");
		$("#param1").val("");
		$("#param1Name").val("");
	},
	
	// 加载表格
	loadGrid:function(params){
		dataGrid.datagrid({
			url:common.excuteUrl("menuController/findMenuByPage.do"),
			queryParams:params,
			method:"post"
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
				selectedMulti: false,
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
	selTree:function(){
		layer.load(3);
		common.ajaxCall(common.excuteUrl("menuController/findTree.do"),{},function(ret){
			layer.closeAll("loading");
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
					btn:['确定','取消','清空'],
					yes:function(){
						var nodes = treeObj.getSelectedNodes();
						if(common.isNotBlank(nodes)){
							$("#parentName").val(nodes[0].name);
							$("#parentId").val(nodes[0].id);
							menu.removeTree(treeObj);
						}
						//console.log(nodes);
						layer.close(index);
					},
					btn2: function(index, layero){
						layer.close(index);
						menu.removeTree(treeObj);
					},
					btn3: function(index, layero){
						$("#parentName").val("");
						$("#parentId").val("");
					},
					cancel: function(index, layero){
						layer.close(index);
						menu.removeTree(treeObj);
					},
					success: function(layero, index){
						treeObj=menu.initTree("#tree",ret.result);
					}
				});
			}else{
				layer.msg(ret.statusMessage,{icon: 2});
			}
		});
	},
	
	/**
	 * 移除树
	 */
	removeTree:function(obj){
		var nodes = obj.getSelectedNodes();
		if(common.isNotBlank(nodes)){
			for (var i=0, l=nodes.length; i < l; i++) {
				obj.removeNode(nodes[i]);
			}
		}
		obj.destroy();
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
		cbx_param0.clear();
		cbx_isSysMenu.clear();
		cbx_isOpen.clear();
		if(type=="add" || type=='edit'){
			$("#menuform").find("input").removeAttr("readOnly");
			$("#menuform").find("select").removeAttr("readOnly");
			menu.resetForm();
			cbx_param0.enabled();
			cbx_isSysMenu.enabled();
			cbx_isOpen.enabled();
		}else{
			$("#menuform").find("input").attr("readOnly","readOnly");
			$("#menuform").find("select").attr("readOnly","readOnly");
			cbx_param0.disabled(true)
			cbx_isSysMenu.disabled(true)
			cbx_isOpen.disabled(true)
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
				  common.ajaxCall(common.excuteUrl("menuController/deleteMenu.do"),{'menuId':id},function(ret){
					    layer.closeAll("loading");
						if(ret.statusCode==0){
							layer.alert(ret.statusMessage,{icon: 1});
							menu.loadGrid();
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
				title:"菜单信息",
				type:1,
				zIndex:99,
				shade:0.2,
				area: [(common.getWinWidth()*0.65)+'px',(common.getWinHeight()-10)+'px'],
				content:$("#menuForm"),
				btn:['确定', '取消'],
				yes:function(index, layero){
					menu.saveOrUpdate();
					layer.close(index);
				},
				btn2: function(index, layero){
					menu.resetForm();
					cbx_param0.clear();
					cbx_isSysMenu.clear();
					cbx_isOpen.clear();
					layer.close(index);
				},
				cancel: function(index, layero){
					menu.resetForm();
					cbx_param0.clear();
					cbx_isSysMenu.clear();
					cbx_isOpen.clear();
				},
				success: function(layero, index){
					menu.editFlag(type);
					//编辑、查看
					if(type=="edit" || type=="see"){
						layer.load(3);
						common.ajaxCall(common.excuteUrl("/menuController/findByMenuId.do"),{'menuId':id},function(ret){
							layer.closeAll("loading");
							if(ret.statusCode==0){
								menu.bindValue(ret.result);
							}else{
								layer.msg(ret.statusMessage,{icon:2});
							}
						});
					}else{
						//查询菜单分类
						/*common.ajaxCall(common.excuteUrl("menuTypeController/findByList.do"),{},function(ret){
							if(ret.statusCode==0){
								cbx_menuTypeId.bind(ret.result);
							}else{
								layer.alert(ret.statusMessage,{icon: 2});
							}
						});*/
						
						//获得最大序列号
						layer.load(3);
						common.ajaxCall(common.excuteUrl("/menuController/getMaxOrderFlag.do"),{},function(ret){
							layer.closeAll("loading");
							if(ret.statusCode==0){
								$("#orderFlag").val(ret.result);
							}else{
								layer.msg(ret.statusMessage,{icon: 2});
							}
						},{"_type":"GET"});
						cbx_param0.setValue("1");
						cbx_isSysMenu.setValue("1");
						cbx_isOpen.setValue("0");
					}
					
				}
			});
		}
	},
	
	//清除内容
	resetForm:function(){
		$('#menuForm').find("input").each(function(index,e){
			$(this).val("");
		})
		$('#menuForm').find("select").each(function(index){
			$(this).val("");
		});
		$('#menuForm').find("textarea").each(function(index){
			$(this).val("");
		});
		$('#menuForm').find("input[type='checkbox']").each(function(index){
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
		cbx_param0.setValue(data.enabledFlag);
		cbx_isSysMenu.setValue(data.isSysMenu);
		cbx_isOpen.setValue(data.isOpen);
	},

	/**
	 * 保存信息
	 * @returns
	 */
	saveOrUpdate:function(){
		 var obj=common.getFormData("#menuForm");
		 var url="";
		 if(common.getstrlength(obj.menuId)==0){
			 url=common.excuteUrl("menuController/addMenu.do");
		 }else{
			 url=common.excuteUrl("menuController/updateMenu.do");
		 }
		 layer.load(3);
		 common.ajaxCall(url,obj,function(ret){
			 layer.closeAll("loading");
			 if(ret.statusCode==0){
				menu.resetForm();
				 menu.loadGrid();
				 layer.msg(ret.statusMessage,{icon: 1});
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
	menu.init();
	menu.loadGrid();
});
