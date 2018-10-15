var dataGrid=null;
var cbx_isLeaguer=null;
var cbx_enabledFlag=null;
var receipt={
	/**
	 * 初始加载
	 */
	init:function(){
		dataGrid=$('#receiptGrid').datagrid({
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
		    idField:"receiptId",
		    rowStyler:function(index,row){
		    	return "height:30px;line-height:30px;";
		    },
		    //view:'暂无数据',
		    columns:[[
		    	{field:'isLeaguer',title:'房门号',width:100,formatter:function(v,r,i){
					if(common.isNotBlank(r.roomInfo)){
						return r.roomInfo.roomNumber;
					}
				}},
				{field:'custName',title:'客户名称',width:100,formatter:function(v,r,i){
					if(common.isNotBlank(r.customer)){
						return r.customer.custName;
					}
				}},
				{field:'cardNumber',title:'身份证',width:100,formatter:function(v,r,i){
					if(common.isNotBlank(r.customer)){
						return r.customer.cardNumber;
					}
				}},
				{field:'linkPhone',title:'联系电话',width:100,formatter:function(v,r,i){
					if(common.isNotBlank(r.customer)){
						return r.customer.linkPhone;
					}
				}},
				{field:'gender',title:'性别',width:100,formatter:function(v,r,i){
					if(common.isNotBlank(r.customer)){
						return r.customer.gender;
					}
				}},
				/*{field:'birthDate',title:'出生日期',width:100,formatter:function(v,r,i){
					if(common.isNotBlank(r.customer)){
						return r.customer.birthdate;
					}
				}},
				{field:'age',title:'年龄',width:100,formatter:function(v,r,i){
					if(common.isNotBlank(r.customer)){
						return r.customer.age;
					}
				}},
				{field:'address',title:'联系地址',width:100,formatter:function(v,r,i){
					if(common.isNotBlank(r.customer)){
						return r.customer.address;
					}
				}},*/
				{field:'roomPrice',title:'房间价格',width:100,formatter:function(v,r,i){
					if(common.isNotBlank(r.roomInfo)){
						return r.roomInfo.roomPrice;
					}
				}},
				{field:'prepaidAmount',title:'应收价格',width:100,formatter:function(v,r,i){
					if(common.isNotBlank(r.occupancy)){
						return r.occupancy.prepaidAmount;
					}
				}},
				{field:'amountCollect',title:'实收价格',width:100,formatter:function(v,r,i){
					if(common.isNotBlank(r.occupancy)){
						return r.occupancy.amountCollect;
					}
				}},
				{field:'receiptMoney',title:'实收价格',width:100},
				{field:'orderFlagName',title:'是否可用',width:100},
				{field:'createBy',title:'创建人',width:100},
				{field:'createDate',title:'创建日期',width:100,formatter:function(v,r,i){
					if(common.isNotBlank(r.occupancy)){
						return r.occupancy.createDateView;
					}
				}},
				{field:'opt',title:'操作',formatter:function(val,rows,index){
					var btn='<button class="layui-btn layui-btn-sm" onclick="receipt.tab(\'see\',\''+rows.custId+'\')"><i class="layui-icon">&#xe615;</i>查看</button>'
				    +'<button class="layui-btn layui-btn-normal layui-btn-sm" onclick="receipt.tab(\'edit\',\''+rows.custId+'\')"><i class="layui-icon">&#xe642;</i>编辑</button>'
				    +'<button class="layui-btn layui-btn-danger layui-btn-sm" onclick="receipt.tab(\'delete\',\''+rows.custId+'\')"><i class="layui-icon">&#xe640;</i>删除</button>';
				    return btn;
				}}
		    ]]
		});
		
		/*cbx_enabledFlag=new SelectBox("enabledFlag",{
			text_field:'dicName',
			value_field:'dicCode',
			multiple:false,
			width:'100%',
			dropWidth:'200px'
		});
		
		cbx_isLeaguer=new SelectBox("isLeaguer",{
			text_field:'dicName',
			value_field:'dicCode',
			multiple:false,
			width:'100%',
			dropWidth:'200px'
		});*/
		
		/*var codeType='ENABLED_FLAG,IS_LEAGUER';
		layer.load(3);
		common.ajaxCall(common.excuteUrl("dictionaryController/findByTypeCode.do"),{'typeCode':codeType},function(ret){
			layer.closeAll("loading");
			if(ret.statusCode==0){
				cbx_enabledFlag.bind(ret.result.ENABLED_FLAG);
				cbx_isLeaguer.bind(ret.result.IS_LEAGUER);
			}else{
				layer.alert(ret.statusMessage,{icon: 2});
			}
		});*/

		//监听身份证
		/*$('#cardNumber').bind('input propertychange', function(){
			var cardNumber=$("#cardNumber").val();
			receipt.calculation(cardNumber);
		});*/
	},
	
	//根据身份证号计算年龄、性别、出生日期
	/*calculation:function(cardNumber){
		if(common.isNotBlank(cardNumber) && common.isCardID(cardNumber)){
			$("#birthDate").val(common.getBirthday(cardNumber));
			$("#age").val(common.getAge(cardNumber));
			$("#genderName").val(common.getSex(cardNumber));
			$("#gender").val(common.getSex(cardNumber,1));
		}
	},*/
	
	// 加载表格
	loadGrid:function(params){
		dataGrid.datagrid({
			url:common.excuteUrl("receiptController/findByPage.do"),
			params:params,
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
		//cbx_enabledFlag.clear();
		//cbx_isLeaguer.clear();
		if(type=="add" || type=='edit'){
			$("#roomform").find("input").removeAttr("readOnly");
			$("#roomform").find("select").removeAttr("readOnly");
			//cbx_enabledFlag.enabled();
			//cbx_isLeaguer.enabled();
		}else{
			$("#roomform").find("input").attr("readOnly","readOnly");
			$("#roomform").find("select").attr("readOnly","readOnly");
			//cbx_enabledFlag.disabled(true);
			//cbx_isLeaguer.disabled(true);
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
				  common.ajaxCall(common.excuteUrl("receiptController/deleteCustById.do"),{'custId':id},function(ret){
						if(ret.statusCode==0){
							layer.alert(ret.statusMessage,{icon: 1});
							receipt.loadGrid();
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
				title:"房间信息",
				type:1,
				zIndex:99,
				shade:0.2,
				area: [(common.getWinWidth()*0.6)+'px',(common.getWinHeight()*0.9)+'px'],
				content:$("#receiptForm"),
				btn:['确定', '取消'],
				yes:function(index, layero){
					receipt.saveOrUpdate();
					layer.close(index);
				},
				btn2: function(index, layero){
					common.resetContent("receiptForm");
					layer.close(index);
				},
				cancel: function(index, layero){
					common.resetContent("receiptForm");
				},
				success: function(layero, index){
					common.resetContent("receiptForm");
					receipt.editFlag(type);
					//编辑、查看
					if(type=="edit" || type=="see"){
						layer.load(3);
						common.ajaxCall(common.excuteUrl("/receiptController/findCustById.do"),{'custId':id},function(ret){
							debugger
							layer.closeAll("loading");
							if(ret.statusCode==0){
								receipt.bindValue(ret.result);
							}else{
								layer.alert(ret.statusMessage,{icon:2});
							}
						});
					}else{
						//获得最大序列号
						layer.load(3);
						common.ajaxCall(common.excuteUrl("/receiptController/getCustMaxOrderFlag.do"),{},function(ret){
							layer.closeAll("loading");
							if(ret.statusCode==0){
								$("#orderFlag").val(ret.result);
							}else{
								layer.alert(ret.statusMessage,{icon: 2});
							}
						},{"_type":"GET"});
						cbx_enabledFlag.setValue("1");
						cbx_isLeaguer.setValue("0");
					}
				}
			});
		}
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
		cbx_isLeaguer.setValue(data.isLeaguer);
	},

	/**
	 * 保存信息
	 * @returns
	 */
	saveOrUpdate:function(){
		 var obj=common.getFormData("#receiptForm");
		 var url="";
		 if(common.getstrlength(obj.custId)==0){
			 url=common.excuteUrl("receiptController/insertCust.do");
		 }else{
			 url=common.excuteUrl("receiptController/updateCustById.do");
		 }
		 common.ajaxCall(url,obj,function(ret){
			 if(ret.statusCode==0){
				 common.resetContent("receiptForm");
				 receipt.loadGrid();
				 layer.alert(ret.statusMessage,{icon: 1});
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
	receipt.init();
	receipt.loadGrid();
});
