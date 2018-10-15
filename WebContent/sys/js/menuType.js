var dataGrid=null;
var cbx_param0=null;
var menuType={
	/**
	 * 绑定表单
	 * @param data
	 * @returns
	 */
	bindValue:function(data){
		console.log(data.typeName);
		common.bindFormData(data);//18320204217  18320204217abcd
		//cbx_param0.setValue(data.enabledFlag);
		//cbx_isSysMenu.setValue(data.isSysMenu);
	}
}
function init(){
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
	    idField:"typeId",
	    columns:[[
			{field:'typeName',title:'分类名称',width:100},
			{field:'orderFlag',title:'排序标识',width:100},
			{field:'enabledFlag',title:'是否可用',width:100},
			{field:'remark',title:'备注',width:100},
			{field:'createBy',title:'创建人',width:100},
			{field:'createDate',title:'创建日期',width:100},
			{field:'updateBy',title:'修改人',width:100},
			{field:'updateDate',title:'修改日期',width:100},
			{field:'opt',title:'操作',formatter:function(val,rows,index){
				var btn='<button class="layui-btn layui-btn-sm" onclick="tab(\'see\',\''+rows.typeId+'\')"><i class="layui-icon">&#xe615;</i>查看</button>'
			    +'<button class="layui-btn layui-btn-normal layui-btn-sm" onclick="tab(\'edit\',\''+rows.typeId+'\')"><i class="layui-icon">&#xe642;</i>编辑</button>'
			    +'<button class="layui-btn layui-btn-danger layui-btn-sm" onclick="tab(\'delete\',\''+rows.typeId+'\')"><i class="layui-icon">&#xe640;</i>删除</button>';
			    return btn;
			}}
	    ]]
	});
	 //layui.form();
	
	/*common.ajaxCall(common.excuteUrl("/menuTypeController/findById.do"),{'typeId':id},function(ret){
	  if(ret.statusCode==0){
		  cbx_param0=$('#cc').combobox({
		    data:ret.ret,
		    valueField:'id',
		    textField:'text'
		});
	  }else{
		  alert(ret.statusMessage);
	  }
  });*/
	
}
function loadGrid(params){
	dataGrid.datagrid({
		url:common.excuteUrl("menuTypeController/findMenuTypeByList.do"),
		params:params,
		method:"post"
	});
}

//清空
function resetGrid(){
	dataGrid.datagrid("loadData",{
		total:0,
        rows:[]
	});
}

function editFlag(type){
	common.resetContent("menuTypeform");
	if(type=="add" || type=='edit'){
		$("#menuTypeform").find("input").removeAttr("readOnly");
	}else{
		$("#menuTypeform").find("input").attr("readOnly","readOnly");
	}
};

function tab(type,id){
	if(type=='delete'){
		layer.confirm('确定要删除该条记录吗？', {
		  btn: ['确定', '取消'],
		  yes:function(index, layero){
			  common.ajaxCall(common.excuteUrl("menuTypeController/deleteMenuType.do"),{'typeId':id},function(ret){
					if(ret.statusCode==0){
						layer.alert("删除成功",{icon:1});
						loadGrid();
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
	}else{
		//页面层
		layer.open({
			type:1,
			skin: 'layui-layer-rim', //加上边框
			area:[800,600], //宽高
			content:$("#menuTypeform"),
			success: function(layero, index){
				editFlag(type);
				if(type=="edit" || type=="see"){
					common.ajaxCall(common.excuteUrl("/menuTypeController/findById.do"),{'typeId':id},function(ret){
						if(ret.statusCode==0){
							menuType.bindValue(ret.result);
						}else{
							alert(ret.statusMessage);
						}
					});
				}
			},
			cancel: function(){
				common.resetContent("menuTypeform");
			}
		});
	}
}

function save(){
	 var obj=common.getFormData("#menuTypeform");
	 var url="";
	 if(common.getstrlength(obj.typeId)==0){
		 url=common.excuteUrl("menuTypeController/addMenuType.do");
	 }else{
		 url=common.excuteUrl("menuTypeController/updateMenuType.do");
	 }
	 common.ajaxCall(url,obj,function(ret){
		 if(ret.statusCode==0){
			 loadGrid();
			 layer.open({title: '消息提示',content:ret.statusMessage});
			 layer.closeAll('dialog');
		 }else{
			 layer.open({title: '消息提示',content:ret.statusMessage});
			 layer.closeAll('dialog');
		 }
	 });
}

$(document).ready(function(){
	init();
	loadGrid();
});