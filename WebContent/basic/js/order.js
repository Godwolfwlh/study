var dataGrid=null;
var custDataGrid=null;
var roomGrid=null;
var cbx_orderState=null;//订单状态
var cbx_param8=null;//缴费状态
var cbx_payState=null;//付款状态
var cbx_param2=null;//房间状态
var cbx_paidType=null;//付款方式
var cbx_paidType2=null;
var cbx_paidType5=null;
var order={
	/**
	 * 初始加载
	 */
	init:function(){
		dataGrid=$('#orderGrid').datagrid({
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
		    idField:"orderId",
		    //view:'暂无数据',
		    columns:[[
				{field:'custName',title:'客户名称',width:80,formatter:function(val,row,index){
					if(common.isNotBlank(row.customerBean)){
						return row.customerBean.custName;
					}
				}},
				{field:'cardNumber',title:'身份证',width:150,formatter:function(val,row,index){
					if(common.isNotBlank(row.customerBean)){
						return row.customerBean.cardNumber;
					}
				}},
				{field:'roomNumber',title:'房间号',width:80,formatter:function(val,row,index){
					if(common.isNotBlank(row.roomInfoBean)){
						return row.roomInfoBean.roomNumber;
					}
				}},
				{field:'roomPrice',title:'标准价(元)',width:80,formatter:function(val,row,index){
					if(common.isNotBlank(row.caseBean)){
						return row.caseBean.roomPrice;
					}
				}},
				{field:'amountOwe',title:'押金(元)',width:60,formatter:function(val,row,index){
					if(common.isNotBlank(row.caseBean)){
						return row.caseBean.deposit;
					}
				}},
				{field:'prepaidAmount',title:'应收金额',width:80,formatter:function(v,r,i){
					if(common.isNotBlank(r.caseBean)){
						return r.caseBean.prepaidAmount;
					}
				}},
				{field:'amountCollect',title:'实收金额',width:80,formatter:function(v,r,i){
					if(common.isNotBlank(r.caseBean)){
						return r.caseBean.receiptMoney;
					}
				}},
				{field:'checkInDateStr',title:'入住时间',width:130},
				{field:'checkOutDateStr',title:'退房时间',width:130},
				{field:'prepaidNumber',title:'入住天数',width:60},
				{field:'payState',title:'缴费状态',width:80,formatter:function(v,r,i){
					if(common.isNotBlank(r)){
						if(r.payState=="1"){
							return "未付款";
						}else if(r.payState=="2"){
							return "已付款";
						}else{
							return "未填写";
						}
					}
				}},
				{field:'orderState',title:'订单状态',width:80,formatter:function(v,r,i){
					//订单状态: 1：预订， 2：入住，3：换房 4：退房 5,：取消， 6,：完成
					if(common.isNotBlank(r)){
						if(r.orderState=="1"){
							return "预订";
						}else if(r.orderState=="2"){
							return "入住";
						}else if(r.orderState=="3"){
							return "换房";
						}else if(r.orderState=="4"){
							return "退房";
						}else if(r.orderState=="5"){
							return "取消";
						}else if(r.orderState=="0"){
							return "完成";
						}
					}
				}},
				{field:'createByName',title:'操作人',width:90},
				{field:'createDateStr',title:'操作日期',width:130},
				{field:'opt',title:'操作',formatter:function(val,rows,index){
					var btn='<button class="layui-btn layui-btn-sm" onclick="order.tab(\'see\',\''+rows.pancyId+'\')"><i class="layui-icon">&#xe615;</i>查看</button>';
					//订单状态: 1：预订， 2：入住，3：换房 4：退房 5,：取消， 6,：完成
					if(common.isNotBlank(rows.orderState) && 
							common.getstrlength(rows.orderState) >0){
						//预定
						if(rows.orderState=='1'){
							btn+='<button class="layui-btn layui-btn-normal layui-btn-sm" onclick="order.editState(\'rz\',\''+index+'\')"><i class="layui-icon">&#xe642;</i>入住</button>'
							btn+='<button class="layui-btn layui-btn-normal layui-btn-sm" onclick="order.editState(\'qx\',\''+index+'\')"><i class="layui-icon">&#xe642;</i>取消</button>'
							btn+='<button class="layui-btn layui-btn-normal layui-btn-sm" onclick="order.editOrder('+index+')"><i class="layui-icon">&#xe642;</i>编辑</button>'
						}else if(rows.orderState=='4' || rows.orderState=='5'){
							btn+='<button class="layui-btn layui-btn-danger layui-btn-sm" onclick="order.tab(\'delete\',\''+rows.pancyId+'\')"><i class="layui-icon">&#xe640;</i>删除</button>'
						}else{//1 3
							btn+='<button class="layui-btn layui-btn-normal layui-btn-sm" onclick="order.editState(\'hf\',\''+index+'\')"><i class="layui-icon">&#xe642;</i>换房</button>'
							btn+='<button class="layui-btn layui-btn-normal layui-btn-sm" onclick="order.editState(\'tf\',\''+index+'\')"><i class="layui-icon">&#xe642;</i>退房</button>'
						}
					}
				    return btn;
				}}
		    ]],
		    //view: order.myview,
	        //emptyMsg: '<h1>很抱歉，没有找到任何记录！！</h1>'
		});
		
		cbx_paidType=new SelectBox("paidType",{
			text_field:'dicName',
			value_field:'dicCode',
			multiple:false,
			width:'100%',
			dropWidth:'200px',
			dropHeight:'130px'
		});
		cbx_paidType2=new SelectBox("paidType2",{
			text_field:'dicName',
			value_field:'dicCode',
			multiple:false,
			width:'100%',
			dropWidth:'200px'
		});
		cbx_paidType5=new SelectBox("paidType5",{
			text_field:'dicName',
			value_field:'dicCode',
			multiple:false,
			width:'100%',
			dropWidth:'200px'
		});
		
		cbx_payState=new SelectBox("payState",{
			text_field:'dicName',
			value_field:'dicCode',
			multiple:false,
			width:'100%',
			dropWidth:'200px',
			onChange:order.chPayState,
			dropHeight:'100px'
		});
		cbx_param2=new SelectBox("param2",{
			text_field:'dicName',
			value_field:'dicCode',
			multiple:false,
			width:'100%',
			dropWidth:'200px'
		});
		cbx_param8=new SelectBox("param8",{
			text_field:'dicName',
			value_field:'dicCode',
			multiple:false,
			width:'100%',
			dropWidth:'200px'
		});
		
		cbx_orderState=new SelectBox("orderState",{
			text_field:'dicName',
			value_field:'dicCode',
			multiple:false,
			width:'100%',
			dropWidth:'200px',
			dropHeight:'100px',
			onChange:order.chOrserState
		});
		
		/*var codeType='PAID_TYPE,PANCY_STATE';
		layer.load(3);
		common.ajaxCall(common.excuteUrl("dictionaryController/findByTypeCode.do"),{'typeCode':codeType},function(ret){
			layer.closeAll("loading");
			if(ret.statusCode==0){
				cbx_paidType.bind(ret.result.PAID_TYPE);
				cbx_paidType2.bind(ret.result.PAID_TYPE);
				cbx_paidType5.bind(ret.result.PAID_TYPE);
				//cbx_payState.bind(ret.result.PAY_STATE);
				cbx_param8.bind(ret.result.PAY_STATE);
				cbx_param2.bind(ret.result.PANCY_STATE);
			}else{
				layer.alert(ret.statusMessage,{icon: 2});
			}
		});*/
		
		cbx_orderState.bind([{'dicName':'入住','dicCode':'2'},{'dicName':'预定','dicCode':'1'}]);
		cbx_payState.bind([{'dicName':'未付款','dicCode':'1'},{'dicName':'已付款','dicCode':'2'}]);
		cbx_paidType.bind([{'dicName':'微信','dicCode':'1'},{'dicName':'支付宝','dicCode':'2'},{'dicName':'现金','dicCode':'3'},{'dicName':'其他','dicCode':'4'}]);
		//cbx_param2.bind([{'dicName':'入住','dicCode':'3'},{'dicName':'预定','dicCode':'2'},{'dicName':'退房','dicCode':'4'}]);
		
		//客户列表
		custDataGrid=$('#customerGrid').datagrid({
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
	        checkOnSelect:true,
	        selectOnCheck:true,
		    showRefresh:false,
		    checkbox:true,
		    idField:"custId",
		    //view:'暂无数据',
		    columns:[[
				{field:'custId',checkbox: true},
				{field:'custName',title:'客户名称',width:80},
				{field:'cardNumber',title:'身份证',width:150},
				{field:'linkPhone',title:'联系电话',width:100},
				{field:'gender',title:'性别',width:50,formatter:function(val,row,index){
					if(common.isNotBlank(row)){
						if(row.gender=="1"){
							return '女';
						}else{
							return '男';
						}
					}else{
						return "其他";
					}
				}},
				{field:'birthDate',title:'出生日期',width:90},
				{field:'age',title:'年龄',width:60},
				{field:'address',title:'联系地址',width:150}
		    ]]
		});
		
		//房间表格
		roomGrid=$('#roomGrid').datagrid({
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
		    	{field:'roomId',checkbox:true,width:60},
				{field:'roomNumber',title:'房间号',width:100},
				{field:'roomPrice',title:'房间价格(元)',width:100},
				{field:'deposit',title:'押金(元)',width:100},
				{field:'roomType',title:'房间类型',width:100},
				{field:'floor',title:'楼层',width:100},
				{field:'roomState',title:'房间状态',width:100}
		    ]]
		});
		
		//条件查询日期
		laydate.render({ 
		  elem: '#param6',
		  type: 'date', //默认，可不填
		  format:'yyyy-MM-dd', //可任意组合
		  value:common._addDate(0)
		});
		laydate.render({ 
			elem: '#param7',
			type: 'date', //默认，可不填
			format:'yyyy-MM-dd', //可任意组合
			value:common._addDate(1)
		});
		
		//监听身份证
		$('#cardNumber').bind('input propertychange', function(){
			var cardNumber=$("#cardNumber").val();
			order.calculation(cardNumber);
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
	
	//缴费状态
	chPayState:function(val,text){
		if(val=='1'){
			cbx_paidType.disabled(true);
		}else{
			cbx_paidType.enabled();
		}
		
	},
	//订单状态
	chOrserState:function(val,text){
		//预定
		if(val=='1'){
			$("#amountCollect").val("");
			$("#amountCollect").attr("readOnly","readOnly");
		}else{
			var roomId=$("#roomId").val();
			if(common.isNotBlank(roomId)){
				$("#amountCollect").val(($("#roomPrice").val()*$("#prepaidNumber").val())+common._parseFloat($("#deposit").val()));
			}
			$("#amountCollect").removeAttr("readOnly");
		}
		
	},
	
	//选择客户
	selectCust:function(){
		layer.open({
			title:"选择用户信息",
			type:1,
			zIndex:99,
			shade:0.2,
			maxHeight:(common.getWinHeight()*0.7),
			area: [(common.getWinWidth()*0.5)+'px',(common.getWinHeight()*0.7)+'px'],
			content:$("#showCustomerGrid"),
			btn:['确定','取消'],
			yes:function(index, layero){
				var row=custDataGrid.datagrid("getSelected");
				if(!common.isNotBlank(row)){
					layer.msg("请选择客户",{icon: 2});
					return;
				}
				common.bindFormData(row,"#custForm");
				$("#birthDate").val(common.getBirthday(row.cardNumber));
				$("#age").val(common.getAge(row.cardNumber));
				$("#genderName").val(common.getSex(row.cardNumber));
				$("#gender").val(common.getSex(row.cardNumber,1));
				custDataGrid.datagrid("uncheckAll");
				layer.close(index);
			},
			btn2: function(index, layero){
				custDataGrid.datagrid("clearChecked");
				layer.close(index);
			},
			cancel: function(index, layero){
				custDataGrid.datagrid("clearChecked");
				layer.close(index);
			},
			success: function(layero, index){
				layer.load(3);
				custDataGrid.datagrid({
					url:common.excuteUrl("customerController/findCustByPage.do"),
					queryParams:{},
					method:"post",
					onLoadSuccess:function(data){
						layer.closeAll("loading");
					}
				});
			}
		});
	},
	
	//选择房间
	selectRoom:function(type){
		layer.open({
			title:"选择房间信息",
			type:1,
			zIndex:99,
			shade:0.2,
			maxHeight:(common.getWinHeight()*0.7),
			area: [(common.getWinWidth()*0.55)+'px',(common.getWinHeight()*0.7)+'px'],
			content:$("#showRoomGrid"),
			btn:['确定', '取消'],
			yes:function(index, layero){
				var row=roomGrid.datagrid("getSelected");
				if(!common.isNotBlank(row)){
					layer.msg("请选择房间",{icon: 2});
					return;
				}
				$("#roomNumber").val(row.roomNumber);
				$("#roomPrice").val(row.roomPrice);
				$("#roomId").val(row.roomId);
				$("#deposit").val(row.deposit);
			    var prepaidNumber=$("#prepaidNumber").val();
			    $("#prepaidAmount").val(($("#roomPrice").val()*prepaidNumber)+common._parseFloat($("#deposit").val()));
			    //判断订单状态
			    var v=cbx_orderState.value();
			    if(v=="2"){
			    	$("#amountCollect").val(($("#roomPrice").val()*prepaidNumber)+common._parseFloat($("#deposit").val()));
			    }
				roomGrid.datagrid("uncheckAll");
				layer.close(index);
				
			},
			btn2: function(index, layero){
				roomGrid.datagrid("clearChecked");
				layer.close(index);
			},
			cancel: function(index, layero){
				roomGrid.datagrid("clearChecked");
				layer.close(index);
			},
			success: function(layero, index){
				layer.load(3);
				roomGrid.datagrid({
					url:common.excuteUrl("roomInfoController/findByPage.do"),
					queryParams:{'roomState':'1'},
					method:"post",
					onLoadSuccess:function(data){
						layer.closeAll("loading");
					}
				});
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
			param5:$("#param5").val(),
			param6:$("#param6").val(),
			param7:$("#param7").val(),
			param8:$("#param8").val()
		}
		order.loadGrid(params);
	},
	
	//清空按钮事件
	reset:function(){
		$("#param0").val("");
		$("#param1").val("");
		$("#param2").val("");
		$("#param3").val("");
		$("#param4").val("");
		$("#param5").val("");
		$("#param6").val("");
		$("#param7").val("");
		$("#param8").val("");
	},
	
	// 加载表格
	loadGrid:function(params){
		dataGrid.datagrid({
			url:common.excuteUrl("orderController/findByPage.do"),
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
	 * 修改订单
	 */
	editOrder:function(index){
		dataGrid.datagrid("selectRow",index);
		var data=dataGrid.datagrid("getSelected");
		if(!common.isNotBlank(data)){
			layer.msg("请选择一条记录",{icon:2});
			return;
		}
		
		layer.open({
			title:"订房信息",
			type:1,
			zIndex:99,
			shade:0.2,
			maxHeight:(common.getWinHeight()*0.95),
			area: [(common.getWinWidth()*0.65)+'px',(common.getWinHeight()*0.9)+'px'],
			content:$("#ruzhuYdFormEdit"),
			btn:['确定', '取消'],
			yes:function(index, layero){
				//保存
				order.saveOrUpdate(index);
			},
			btn2: function(index, layero){
				order.resetForm("#ruzhuYdFormEdit");
				layer.close(index);
			},
			cancel: function(index, layero){
				order.resetForm("#ruzhuYdFormEdit");
				layer.close(index);
			},
			success: function(layero, index){
				order.editFlag("add");
				if(common.isNotBlank(data.customerBean)){
					common.bindFormData(data.customerBean,'#custForm');//18320204217  18320204217abcd
					$("#genderName").val(data.customerBean.genderName);//18320204217  18320204217abcd
				}
				if(common.isNotBlank(data.roomInfoBean)){
					common.bindFormData(data.roomInfoBean,'#roomInfoForm');//18320204217  18320204217abcd
					$("#prepaidAmount").val((common._parseFloat(data.roomInfoBean.roomPrice)*data.prepaidNumber)+common._parseFloat(data.roomInfoBean.deposit));//18320204217  18320204217abcd
				}
				$("#checkInDate").val(data.checkInDateStr);//18320204217  18320204217abcd
				$("#checkOutDate").val(data.checkOutDateStr);//18320204217  18320204217abcd
				$("#prepaidNumber").val(data.prepaidNumber);//18320204217  18320204217abcd
				$("#remark").val(data.remark);//18320204217  18320204217abcd
				$("#orderId").val(data.orderId);//18320204217  18320204217abcd
				cbx_payState.setValue(data.payState);
				order.chPayState(data.payState);
				cbx_orderState.setValue(data.orderState);
				cbx_paidType.setValue(data.paidType);
				
			}
		});
		
		
	},
	
	/**
	 * 住房状态
	 */
	editState:function(type,index){
		dataGrid.datagrid("selectRow",index);
		var row=dataGrid.datagrid("getSelected");
		if(type=='tf'){//退房
			layer.confirm('确定要退房吗？', {
			  btn: ['确定', '取消'],
			  icon:3,
			  yes:function(index, layero){
				  layer.close(index);
				  layer.open({
						title:"退房信息",
						type:1,
						zIndex:99,
						shade:0.2,
						maxHeight:(common.getWinHeight()*0.95),
						area: [(common.getWinWidth()*0.7)+'px',(common.getWinHeight()*0.8)+'px'],
						content:$("#tfForm"),
						btn:['确定', '取消'],
						yes:function(index1, layero1){
							var obj=common.getFormData("#tfForm");
							layer.load(3);
							common.ajaxCall(common.excuteUrl("orderController/checkOutRoom.do"),obj,function(ret){
								layer.closeAll("loading");	
								if(ret.statusCode==0){
										layer.msg(ret.statusMessage,{icon: 1},function(index2,l){
											order.loadGrid();
											layer.close(index);
											layer.close(index1);
											layer.close(index2);
										});
									}else{
										layer.msg(ret.statusMessage,{icon: 2});
								}
							});
						},
						btn2: function(index1, layero1){
							layer.close(index1);
						},
						cancel: function(index1, layero1){
							layer.close(index1);
						},
						success: function(layero1, index1){
							$("#pancyId1").val(row.pancyId);
							$("#roomId1").val(row.roomInfo.roomId);
							$("#custId1").val(row.customer.custId);
							$("#roomPrice1").val(row.roomInfo.roomPrice);
							$("#roomNumber1").val(row.roomInfo.roomNumber);					
							$("#prepaidNumber1").val(row.prepaidNumber);
							$("#amountCollect1").val(row.amountCollect);					
				  			$("#checkInDate1").val(row.checkInDateView);					
							$("#checkOutDate1").val(row.checkOutDateView);	
							$("#zz").val(common.countDownTime(row.checkInDateView,common.getDateFmtYMDHm()));
							$("#deposit1").val(row.deposit);					
							$("#amountOwe1").val(common._parseFloat(row.prepaidAmount)+common._parseFloat(row.deposit));					
						}
					});
			  },
			  cancel:function(index, layero){
				  layer.close(index);
			  }
			});
		}else if(type=='qx'){//取消
			layer.confirm('确定要取消该订单吗？', {
			  icon:3,
			  btn: ['确定', '取消'],
			  yes:function(index, layero){
				  layer.close(index);
				  layer.load(3);
				  common.ajaxCall(common.excuteUrl("orderController/cancelRoom.do"),{'pancyId':row.pancyId},function(ret){
					  layer.closeAll("loading");
					  layer.close(index);
					  if(ret.statusCode==0){
							layer.alert(ret.statusMessage,{icon: 1},function(index1){
								order.loadGrid();
								layer.close(index1);
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
		}else if(type=='hf'){//换房
			layer.confirm('确定更换该房间吗？', {
			  icon:3,
			  btn: ['确定', '取消'],
			  yes:function(index, layero){
				  layer.close(index);
				  layer.open({
						title:"更换房间信息",
						type:1,
						zIndex:99,
						shade:0.2,
						maxHeight:(common.getWinHeight()*0.95),
						area: [(common.getWinWidth()*0.7)+'px',(common.getWinHeight()*0.8)+'px'],
						content:$("#changeRoomForm"),
						btn:['确定', '取消'],
						yes:function(index1, layero1){
							var obj=common.getFormData("#changeRoom");
							obj.custId4=$("#custId4").val();
							obj.roomId4=$("#roomId4").val();
							obj.pancyId4=$("#pancyId4").val();
							if(obj.roomNumber==$("#roomNumber4").val()){
								layer.msg("不能选择相同的房间");
								return;
							}
							layer.load(3);
							common.ajaxCall(common.excuteUrl("orderController/changeRoom.do"),obj,function(ret){
								layer.closeAll("loading");
								if(ret.statusCode==0){
									layer.close(index1);
									layer.msg(ret.statusMessage,{icon: 1},function(index2,l){
										order.loadGrid();
										layer.close(index2);
									});
								}else{
									layer.msg(ret.statusMessage,{icon: 2});
								}
							});
						},
						btn2: function(index1, layero1){
							layer.close(index1);
						},
						cancel: function(index1, layero1){
							layer.close(index1);
						},
						success: function(index1,layero1){
							$("#pancyId4").val(row.pancyId);
							$("#roomId4").val(row.roomId);
							$("#custId4").val(row.custId);
							$("#custName4").val(row.customer.custName);
							$("#linkPhone4").val(row.customer.linkPhone);
							$("#cardNumber4").val(row.customer.cardNumber);
							$("#birthDate4").val(row.customer.birthDate);
							$("#gender4").val(row.customer.gender);
							$("#age4").val(row.customer.age);
							$("#address4").val(row.customer.address);
							
							$("#roomPrice4").val(row.roomInfo.roomPrice);					
							$("#roomNumber4").val(row.roomInfo.roomNumber);					
							$("#prepaidNumber4").val(row.prepaidNumber);					
				  			$("#checkInDate4").val(row.checkInDateView);					
							$("#checkOutDate4").val(row.checkOutDateView);					
							$("#deposit4").val(row.amountOwe);					
							$("#paidType4").val(row.paidType);					
							$("#prepaidAmount4").val(common._parseFloat(row.prepaidAmount)+common._parseFloat(row.deposit));					
							$("#amountCollect4").val(common._parseFloat(row.prepaidAmount)+common._parseFloat(row.deposit));
						}
					});
			  },
			  cancel:function(index, layero){
				  layer.close(index);
			  }
			});
		}else{
			layer.confirm('确定入住该房间吗？', {
			  icon:3,
			  btn: ['确定', '取消'],
			  yes:function(index, layero){
				  layer.close(index);
				  layer.open({
					title:"入住房间信息",
					type:1,
					zIndex:99,
					shade:0.2,
					maxHeight:(common.getWinHeight()*0.95),
					area: [(common.getWinWidth()*0.7)+'px',(common.getWinHeight()*0.8)+'px'],
					content:$("#rzForm"),
					btn:['确定', '取消'],
					yes:function(index1, layero1){
						layer.close(index1);
						var obj=common.getFormData("#rzForm");
						layer.load(3);
						common.ajaxCall(common.excuteUrl("orderController/checkInRoom.do"),obj,function(ret){
							layer.closeAll("loading");
							if(ret.statusCode==0){
								layer.msg(ret.statusMessage,{icon: 1},function(index2,l){
									order.loadGrid();
									layer.close(index2);
								});
							}else{
								layer.msg(ret.statusMessage,{icon: 2});
							}
						});
					},
					btn2: function(index1, layero1){
						layer.close(index1);
					},
					cancel: function(index1, layero1){
						layer.close(index1);
					},
					success: function(layero1, index1){
						$("#pancyId2").val(row.pancyId);
						$("#roomId2").val(row.roomInfo.roomId);
						$("#custId2").val(row.customer.custId);
						$("#roomPrice2").val(row.roomInfo.roomPrice);					
						$("#roomNumber2").val(row.roomInfo.roomNumber);					
						$("#prepaidNumber2").val(row.prepaidNumber);					
			  			$("#checkInDate2").val(row.checkInDateView);					
						$("#checkOutDate2").val(row.checkOutDateView);					
						$("#deposit2").val(row.amountOwe);					
						$("#prepaidAmount2").val(common._parseFloat(row.prepaidAmount)+common._parseFloat(row.deposit));					
						$("#amountCollect2").val(common._parseFloat(row.amountCollect));					
					}
				});
			  },
			  cancel:function(index, layero){
				  layer.close(index);
			  }
			});
		}
		
	},
	
	/**
	 * 表单内容启用、禁用
	 * @param type
	 * @returns
	 */
	editFlag:function(type){
		cbx_payState.clear();
		cbx_paidType.clear();
		cbx_orderState.clear();
		order.resetForm("#orderFormEdit");
		if(type=="add" || type=='edit'){
			$("#roomform").find("input").removeAttr("readOnly");
			$("#roomform").find("select").removeAttr("readOnly");
			cbx_payState.enabled();
			cbx_orderState.enabled();
			cbx_paidType.enabled();
		}else{
			$("#roomform").find("input").attr("readOnly","readOnly");
			$("#roomform").find("select").attr("readOnly","readOnly");
			cbx_payState.disabled(true);
			cbx_paidType.disabled(true);
			cbx_orderState.disabled(true);
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
				  common.ajaxCall(common.excuteUrl("orderController/deletePancyById.do"),{'pancyId':id},function(ret){
					  layer.closeAll("loading");
					  if(ret.statusCode==0){
							layer.msg(ret.statusMessage,{icon: 1},function(index1,l){
								order.loadGrid();
								layer.close(index);
								layer.close(index1);
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
				title:"订房信息",
				type:1,
				zIndex:99,
				shade:0.2,
				maxHeight:(common.getWinHeight()*0.95),
				area: [(common.getWinWidth()*0.65)+'px',(common.getWinHeight()*0.9)+'px'],
				content:$("#ruzhuYdFormEdit"),
				btn:['确定', '取消'],
				yes:function(index, layero){
					order.saveOrUpdate(index);
					//order.resetForm("#ruzhuYdFormEdit");
					//layer.close(index);
				},
				btn2: function(index, layero){
					order.resetForm("#ruzhuYdFormEdit");
					layer.close(index);
				},
				cancel: function(index, layero){
					order.resetForm("#ruzhuYdFormEdit");
					layer.close(index);
				},
				success: function(layero, index){
					order.editFlag(type);
					//编辑、查看
					if(type=="edit" || type=="see"){
						layer.load(3);
						common.ajaxCall(common.excuteUrl("/orderController/queryAssoByPancyId.do"),{'pancyId':id},function(ret){
							layer.closeAll("loading");
							if(ret.statusCode==0){
								order.bindValue(ret.result);
							}else{
								layer.alert(ret.statusMessage,{icon:2});
							}
						});
					}else{
						//获得最大序列号
						layer.load(3);
						common.ajaxCall(common.excuteUrl("/orderController/getPancyMaxOrderFlag.do"),{},function(ret){
							layer.closeAll("loading");
							if(ret.statusCode==0){
								$("#orderFlag").val(ret.result);
							}else{
								layer.alert(ret.statusMessage,{icon: 2});
							}
						},{"_type":"GET"});
						$("#checkInDate").val(common.getDateFmtYMDHm());
						//cbx_payState.setValue("0");
						//cbx_orderState.setValue("1");
						//cbx_paidType.setValue("1");
						//cbx_paidType.disabled(true);
					}
				}
			});
		}
	},
	
	/**
	 * 选择日期事件
	 */
	clickStart:function(_id){
		WdatePicker({el:_id,dateFmt:'yyyy-MM-dd HH:mm'});
		$("#"+_id).unbind("click");
	},
	
	/**
	 * 选择日期事件
	 */
	clickOver:function(_id){
		WdatePicker({
			el:_id,dateFmt:'yyyy-MM-dd HH:mm',
			minDate:common._addDate(1)+' 12:00',
			onpicking:function(dp){
				console.log(dp);
				console.log(dp.cal.getDateStr());
				console.log(dp.cal.date);
				var obj=dp.cal.date;
				var num=common.DateDiff($("#checkInDate").val(),obj.y+"-"+obj.M+"-"+obj.d+" "+obj.H+":"+obj.m);
				$("#prepaidNumber").val(num);
			    $("#prepaidAmount").val(($("#roomPrice").val()*num)+common._parseFloat($("#deposit").val()));
			    $("#amountCollect").val(($("#roomPrice").val()*num)+common._parseFloat($("#deposit").val()));
			}
		});
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
		if(common.isNotBlank(data.customer)){
			common.bindFormData(data.customer,'#custForm');//18320204217  18320204217abcd
			$("#genderName").val(data.customer.genderName);//18320204217  18320204217abcd
		}
		common.bindFormData(data,'#orderForm');//18320204217  18320204217abcd
		if(common.isNotBlank(data.roomInfo)){
			$("#roomNumber").val(data.roomInfo.roomNumber);//18320204217  18320204217abcd
			$("#roomPrice").val(data.roomInfo.roomPrice);//18320204217  18320204217abcd
			$("#deposit").val(data.roomInfo.deposit);//18320204217  18320204217abcd
			$("#roomId").val(data.roomInfo.roomId);//18320204217  18320204217abcd
		}
		$("#checkInDate").val(data.checkInDateView);//18320204217  18320204217abcd
		$("#checkOutDate").val(data.checkOutDateView);//18320204217  18320204217abcd
		cbx_payState.setValue(data.payState);
		cbx_orderState.setValue(data.pancyState);
		cbx_paidType.setValue(data.paidType);
	},

	/**
	 * 保存信息
	 * @returns
	 */
	saveOrUpdate:function(index){
		//客户form
		var custObj=common.getFormData("#custForm");
		var custId=custObj.custId;
		var flag=true;//
		if(!common.isNotBlank(custId) || common.getstrlength(custId)==0){
			if(!common.isNotBlank(custObj.custName) || common.getstrlength(custObj.custName)==0){
				layer.msg("请输入姓名",{icon: 2});
				return;
			}
			if(!common.isNotBlank(custObj.cardNumber) || common.getstrlength(custObj.cardNumber)==0){
				layer.msg("请输入身份证",{icon: 2});
				return;
			}
			layer.load(3);
			common.ajaxCall(common.excuteUrl("/customerController/insertCustRetId.do"),custObj,function(ret){
				 layer.closeAll("loading");
				 if(ret.statusCode==0){
					 custId=ret.result;
					 flag=true;
				 }else{
					 layer.msg(ret.statusMessage,{icon: 2});
					 flag=false;
				 }
			});
		}
		if(!flag){
			layer.msg("保存客户信息失败",{icon: 2});
			return;
		}
		if(!common.isNotBlank(custObj.custId) && common.getstrlength(custId)==0){
			layer.alert("请选择客户信息",{icon: 2});
			return;
		}
		/*if(!common.isCardID(custObj.cardNumber)){
			layer.alert("身份证输入不合法",{icon: 2});
			return;
		}*/
		//订单form
		var obj=common.getFormData("#roomInfoForm");
		obj.custId=custId;
		//obj.remark=obj.custRemark;
		obj.checkInDate=obj.checkInDate+":00";
		obj.checkOutDate=obj.checkOutDate+":00";
		if(common.getstrlength(obj.roomId)==0){
			layer.msg("请选择房间",{icon: 2});
			return;
		}
		if(common.isNotBlank(obj.payState) && common.getstrlength(obj.payState)>0 && obj.payState=='2'){
			if(!common.isNotBlank(obj.amountCollect) || common.getstrlength(obj.amountCollect)==0){
				layer.alert("请输入‘实收金额’",{icon: 2});
				return;
			}
		}
		
		var url="";
		if(common.getstrlength(obj.orderId)==0 || !common.isNotBlank(obj.orderId)){
			url=common.excuteUrl("orderController/insertOrder.do");
		}else{
			url=common.excuteUrl("orderController/updateByIdSelective.do");
		}
		layer.load(3);
		common.ajaxCall(url,obj,function(ret){
			layer.closeAll("loading");
			if(ret.statusCode==0){
				order.resetForm("#ruzhuYdFormEdit");
				layer.close(index);
				order.loadGrid();
				layer.msg(ret.statusMessage,{icon: 1},function(index1,l){
					layer.close(index1);
					order.loadGrid();
				});
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
	order.init();
	//order.loadGrid();
	order.query();
});
