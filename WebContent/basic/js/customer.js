var dataGrid=null;
var cbx_isLeaguer=null;
var cbx_param4=null;
var cbx_param2=null;//性别
var cbx_enabledFlag=null;
var customer={
	/**
	 * 初始加载
	 */
	init:function(){
		dataGrid=$('#customerGrid').datagrid({
		    loadMsg:"正在加载...",
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
		    idField:"custId",
		    //view:'暂无数据',
		    columns:[[
				{field:'custName',title:'客户名称',width:100},
				{field:'cardNumber',title:'身份证',width:150},
				{field:'linkPhone',title:'联系电话',width:100},
				{field:'gender',title:'性别',width:60,formatter:function(v,r,i){
					if(r.gender=='0'){
						return '女';
					}else{
						return '男';
					}
				}},
				{field:'birthDate',title:'出生日期',width:100},
				{field:'age',title:'年龄',width:60},
				{field:'address',title:'联系地址',width:150},
				{field:'custState',title:'状态',width:80,formatter:function(val,r,index){
					//客户状态：1.正常，2，预定 ,3.入住
					if(common.isNotBlank(r)){
						if(r.custState=="1"){
							return "正常";
						}else if(r.custState=="2"){
							return "预定";
						}else if(r.custState=="3"){
							return "入住";
						}
					}
				}},
				{field:'isLeaguer',title:'是否会员',width:80},
				{field:'leagScore',title:'会员积分',width:80},
				{field:'createByName',title:'创建人',width:90},
				{field:'createDateStr',title:'创建日期',width:150},
				{field:'opt',title:'操作',formatter:function(val,rows,index){
					var btn='<button class="layui-btn layui-btn-sm" onclick="customer.tab(\'see\',\''+rows.custId+'\')"><i class="layui-icon">&#xe615;</i>查看</button>'
				    +'<button class="layui-btn layui-btn-normal layui-btn-sm" onclick="customer.tab(\'edit\',\''+rows.custId+'\')"><i class="layui-icon">&#xe642;</i>编辑</button>'
				    +'<button class="layui-btn layui-btn-danger layui-btn-sm" onclick="customer.tab(\'delete\',\''+rows.custId+'\')"><i class="layui-icon">&#xe640;</i>删除</button>';
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
		
		cbx_param4=new SelectBox("param4",{
			text_field:'dicName',
			value_field:'dicCode',
			multiple:false,
			width:'100%',
			dropWidth:'200px',
			dropHeight:'100px'
		});
		cbx_param2=new SelectBox("param2",{
			text_field:'dicName',
			value_field:'dicCode',
			multiple:false,
			width:'100%',
			dropWidth:'200px',
			dropHeight:'100px'
		});
		
		cbx_isLeaguer=new SelectBox("isLeaguer",{
			text_field:'dicName',
			value_field:'dicCode',
			multiple:false,
			width:'100%',
			dropWidth:'200px'
		});
		
		var codeType='ENABLED_FLAG,IS_LEAGUER';
		layer.load(3);
		common.ajaxCall(common.excuteUrl("dictionaryController/findByTypeCode.do"),{'typeCode':codeType},function(ret){
			layer.closeAll("loading");
			if(ret.statusCode==0){
				cbx_enabledFlag.bind(ret.result.ENABLED_FLAG);
				cbx_isLeaguer.bind(ret.result.IS_LEAGUER);
				cbx_param4.bind(ret.result.IS_LEAGUER);
			}else{
				layer.alert(ret.statusMessage,{icon: 2});
			}
		});
		cbx_param2.bind([{"dicCode":"1","dicName":"男"},{"dicCode":"0","dicName":"女"},]);

		//监听身份证
		$('#cardNumber').bind('input propertychange', function(){
			var cardNumber=$("#cardNumber").val();
			customer.calculation(cardNumber);
		});
	},
	
	//根据身份证号计算年龄、性别、出生日期
	calculation:function(cardNumber){
		if(common.isNotBlank(cardNumber) && common.isCardID(cardNumber)){
			$("#birthDate").val(common.getBirthday(cardNumber));
			$("#age").val(common.getAge(cardNumber));
			$("#genderName").val(common.getSex(cardNumber));
			$("#gender").val(common.getSex(cardNumber,1));
		}
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
		customer.loadGrid(params);
	},
	
	//清空按钮事件
	reset:function(){
		$("#param0").val("");
		$("#param1").val("");
		$("#param2").val("");
		$("#param3").val("");
		$("#param4").val("");
	},
	
	// 加载表格
	loadGrid:function(params){
		dataGrid.datagrid({
			url:common.excuteUrl("customerController/findCustByPage.do"),
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
		cbx_isLeaguer.clear();
		customer.resetForm();
		if(type=="add" || type=='edit'){
			$("#roomform").find("input").removeAttr("readOnly");
			$("#roomform").find("select").removeAttr("readOnly");
			cbx_enabledFlag.enabled();
			cbx_isLeaguer.enabled();
		}else{
			$("#roomform").find("input").attr("readOnly","readOnly");
			$("#roomform").find("select").attr("readOnly","readOnly");
			cbx_enabledFlag.disabled(true);
			cbx_isLeaguer.disabled(true);
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
				  common.ajaxCall(common.excuteUrl("customerController/deleteCustById.do"),{'custId':id},function(ret){
					  	layer.closeAll("loading");
						if(ret.statusCode==0){
							layer.msg(ret.statusMessage,{icon: 1},function(index1,l){
								customer.loadGrid();
								layer.close(index1);
								layer.close(index);
							});
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
				area: [(common.getWinWidth()*0.6)+'px',(common.getWinHeight()*0.9)+'px'],
				content:$("#customerForm"),
				btn:['确定', '取消'],
				yes:function(index, layero){
					customer.saveOrUpdate();
					layer.close(index);
				},
				btn2: function(index, layero){
					layer.close(index);
				},
				cancel: function(index, layero){
					layer.close(index);
				},
				success: function(layero, index){
					customer.editFlag(type);
					//编辑、查看
					if(type=="edit" || type=="see"){
						layer.load(3);
						common.ajaxCall(common.excuteUrl("/customerController/findCustById.do"),{'custId':id},function(ret){
							layer.closeAll("loading");
							if(ret.statusCode==0){
								customer.bindValue(ret.result);
							}else{
								layer.alert(ret.statusMessage,{icon:2});
							}
						});
					}else{
						//获得最大序列号
						layer.load(3);
						common.ajaxCall(common.excuteUrl("/customerController/getCustMaxOrderFlag.do"),{},function(ret){
							layer.closeAll("loading");
							if(ret.statusCode==0){
								$("#orderFlag").val(ret.result);
							}else{
								layer.msg(ret.statusMessage,{icon: 2});
							}
						},{"_type":"GET"});
						cbx_enabledFlag.setValue("1");
						cbx_isLeaguer.setValue("0");
					}
				}
			});
		}
	},
	
	//清除内容
	resetForm:function(){
		$('#customerForm').find("input").each(function(index,e){
			$(this).val("");
		})
		$('#customerForm').find("select").each(function(index){
			$(this).val("");
		});
		$('#customerForm').find("textarea").each(function(index){
			$(this).val("");
		});
		$('#customerForm').find("input[type='checkbox']").each(function(index){
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
		if(data.gender=='0'){
			$("#genderName").val('女');
		}else{
			$("#genderName").val('男');
		}
		cbx_enabledFlag.setValue(data.enabledFlag);
		cbx_isLeaguer.setValue(data.isLeaguer);
	},

	/**
	 * 保存信息
	 * @returns
	 */
	saveOrUpdate:function(){
		 var obj=common.getFormData("#customerForm");
		 var url="";
		 if(common.getstrlength(obj.custId)==0){
			 url=common.excuteUrl("customerController/insertCust.do");
		 }else{
			 url=common.excuteUrl("customerController/updateCustById.do");
		 }
		 layer.load(3);
		 common.ajaxCall(url,obj,function(ret){
			 layer.closeAll("loading");
			 if(ret.statusCode==0){
				 layer.msg(ret.statusMessage,{icon: 1});
				 customer.loadGrid();
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
	customer.init();
	customer.loadGrid();
});
