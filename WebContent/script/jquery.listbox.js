function ListBox(ocontainer,option){	
	var defaults={
		onclickEvent:null,//单击事件
		checkBox:false,//显示复选框
		value_field:"value",//数据值字段
		text_field:"text",//显示标题字段
		disabled:false,//控件禁用标识
		width:'280px',
		border:'1px solid #ccc',
		height:'200px',
		showtitle:false
	};
	this.options = $.extend(defaults,option);
	this.m_container=$('#'+ocontainer);
	this.wrap_id=ocontainer;//容器的ID	
	this.m_currentId="";//当前选中的页面	
	this.mainUL=$('<ul class="listbox"></ul>').appendTo(this.m_container);
	this.mainUL.css("width",this.options.width);
	this.mainUL.css("border",this.options.border);
	this.mainUL.css("height",this.options.height);
}
/**
*添加ListBox项目
*/
ListBox.prototype.addItem=function(objs){
	if (this.options.disabled) return ;
	var itemId=this.getid(objs[this.options.value_field]);
	var oli=$("<LI />").appendTo(this.mainUL);
	var oa=$("<A />").appendTo(oli);
	oa.attr("_value",objs[this.options.value_field]);
	oa.attr("_text",objs[this.options.text_field]);
	oa.attr("id",itemId);	
	var ospan=$("<SPAN />").appendTo(oa);
	if (this.options.checkBox){//创建CheckBox		
		ospan.html("<input type='checkbox' style='margin-right:3px'>"+objs[this.options.text_field]+"</input>");		
	} else {
		ospan.text(objs[this.options.text_field]);
	}
	if (this.options.showtitle){
		ospan.attr("title",objs[this.options.text_field]);
	}
	var _this=this;
	//添加单击事件
	oa.click(function(){
		if ($(this).attr("disabled")) return ;
		//清空其他选中
		_this.mainUL.find(".selected").each(function(){
			$(this).removeClass("selected");
		});
		//添加选中样式
		if (!$(this).hasClass("selected")){
			$(this).addClass("selected");
		}
		var value=$(this).attr("_value");
		var text=$(this).attr("_text");
		if ($.isFunction(_this.options.onclickEvent)) {
			_this.options.onclickEvent(value,text,$(this).attr("id"));
		}		
	});
	return true;
};
/**
*添加列表数据到listbox
*/
ListBox.prototype.addAll=function(items){
	if (this.options.disabled) return ;
	if ((items==undefined)||(items==null)){
		alert("添加的列表数据不能为空！");
		return ;
	}
	if (items.length==undefined){
		alert("添加的列表数据必须为数组对象！");
		return ;
	}
	for (var i=0;i<items.length;i++){
		this.addItem(items[i]);
	}
};
/**
*删除ListBox中的所有数据
*/
ListBox.prototype.removeAll=function(){
	if (this.options.disabled) return ;
	this.mainUL.find("LI").each(function(){
		$(this).remove();
	});
};
ListBox.prototype.getItems=function(){
	var list=[];
	if (this.mainUL==undefined) return;
	this.mainUL.find("A").each(function(){
		var obj=new Object();
		obj.value=$(this).attr("_value");
		obj.text=$(this).attr("_text");
		list[list.length]=obj;
	});
	return list;
};
ListBox.prototype.getItemsValues=function(){
	var list=[];
	if (this.mainUL==undefined) return;
	this.mainUL.find("A").each(function(){
		list[list.length]=$(this).attr("_value");
	});
	return list;
};
ListBox.prototype.getid=function(value){
	return this.wrap_id+"_"+new Date().getTime()+"_"+value;
};
/**
*根据值设置当前选中
*/
ListBox.prototype.setCurrentValue=function(value){
	this.m_container.find("A").each(
		function(){
			if ($(this).attr("_value")==value){
				if (!$(this).hasClass("selected")){
					$(this).addClass("selected");
				}
			} else {
				$(this).removeClass("selected");
			}
		}
	);		
};
/**
*获取当前选中行
*/
ListBox.prototype.getCurrent=function(){
	var data=new Object();
	var index=-1;
	this.m_container.find(".selected").each(
		function(){
			data.text=$(this).attr("_text");
			data.value=$(this).attr("_value");
			data.id=$(this).attr("id");
			index=0;
		}
	);	
	if (index==-1) return null;
	return data;
};

/**
*根据显示值设置当前选中
*/
ListBox.prototype.setCurrentText=function(value){
	this.m_container.find("A").each(function(){
			if ($(this).attr("_text")==value){
				if (!$(this).hasClass("selected")){
					$(this).addClass("selected");
				}
			} else {
				$(this).removeClass("selected");
			}
		}
	);	
};
/**
*删除项目
*/
ListBox.prototype.remove=function(_id){
	if (this.options.disabled) return ;
	var oa=$("#"+_id);
	oa.parent().remove();

};
/**
*获取选中值
*/
ListBox.prototype.getChecks=function(){
	if (!this.options.checkBox) return;
	var rtn=new Array();
	this.m_container.find(":checkbox").each(
		function(){
			$checkbox=$(this);
			if ($checkbox.attr("checked")==true){
				var data=new Object();
				data.text=$checkbox.parent().parent().attr("_text");				
				data.value=$checkbox.parent().parent().attr("_value");
				data.id=$checkbox.parent().parent().attr("id");
				rtn[rtn.length]=data;
			}
		}	
	);
	return rtn;
};
/**
*设置用户选中
*/
ListBox.prototype.setCheckValue=function(value){
	if (!this.options.checkBox) return;
	this.m_container.find(":checkbox").each(function(){
		$checkbox=$(this);
		if ($checkbox.parent().parent().attr("_value")==value){
			$checkbox.attr("checked",true);
		}
	});
};
/**
*设置选中，多个选中值，参数数组
*/
ListBox.prototype.setChecks=function(values){
	if (!this.options.checkBox) return;
	for (var i=0;i<values.length;i++){
		this.setCheckValue(values[i]);
	}
};
/**
*控件的启用
*/
ListBox.prototype.enabled=function(){
	this.options.disabled=false;
	if (this.options.checkBox){//创建CheckBox
		this.m_container.find(":checkbox").each(function(){
				$(this).removeAttr("disabled");					
			}	
		);
	} 
	this.m_container.find("A").each(function(){
		$(this).removeAttr("disabled");				
	});
};
/**
*控件的禁用
*/
ListBox.prototype.disabled=function(){
	this.options.disabled=true;
	if (this.options.checkBox){//创建CheckBox
		this.m_container.find(":checkbox").each(function(){
			$(this).attr("disabled",true);		
		});
	} 
	this.m_container.find("A").each(function(){
		$(this).attr("disabled",true);				
	});
};
