/**
 * 树形下拉列表，依赖ZTree控件 作者：高洋 日期：2012-01-08
 */
function SelectTree(selectid, config) {
	var defaults = {
			multiple : false,// 多选
			width : '280px',// 控件的默认宽度
			height : '28px',// 控件高度
			dropWidth : '280px',// 下拉列表的宽度
			dropHeight : '200px',// 下拉列表的高度
			display_suffix : '_text',// 显示值的后缀
			inputwrap_suffix : '_inputwrap',// 输入框外部的后悔明
			droplistwrap_suffix : '_list',// 下拉div的后缀名
			dropmenu_suffix : "_menuTree",// 下拉树形
			icon_suffix : '_icon',// 按钮的后缀
			id_field : "id",// 树的ID
			pid_field : "pid",// 树的父节点
			display_field : 'name',// 显示标题
			icon_field : "icon",// 图标字段名称
			showIcon : true,// 显示图标
			showLine : false,// 显示树的线条
			onChange : null,// 行改变事件
			disabled : false,// 使用标识
			enabled_color : "#ffffff",// 启用的背景颜色
			disabled_color : "#f8f2f2",// 控件禁用的背景颜色
			sibling : "ps",// 影响兄弟节点
			radioType : null,
			selectFlag : '1',// 是否可以选中
			msg : '请选择校区下的班级类型！'
	// radiobutton的选中
	};
	this.options = $.extend(defaults, config);
	// 计算下拉宽度
	if (!("dropWidth" in config)) {
		var wrapwidth = this.options.width.replace("px", "");
		this.options.dropWidth = wrapwidth - 10;
	}
	this.sourceElmentId = selectid;
	this.wrapper = null;
	this.wrapperInput = null;// 封装输入框的Div层
	this.listWrapper = null;// 封装下拉部分的Div层
	this.displayInput = null;// 用于显示选中的数据
	this.m_tree = null;// 树下来列表
	this.list = null;// 下拉的UL对象
	this.treeid = null;
	this.dropIcon = null;// 下载的图标
	this.init();
}
/**
 * 计算输入框的宽度
 */
SelectTree.prototype.computeWidth = function() {
	var innerWidth = this.options.width.replace("px", "");
	return innerWidth - 20;
};
SelectTree.prototype.getAbsPoint = function(e) {
	var x = e.offsetLeft;
	var y = e.offsetTop;
	while (e = e.offsetParent) {
		x += e.offsetLeft;
		y += e.offsetTop;
	}
	return {
		"x" : x,
		"y" : y
	};
};
/**
 * 初始化
 */
SelectTree.prototype.init = function() {
	$("#" + this.sourceElmentId).wrap(
			"<div id='" + this.sourceElmentId + this.options.inputwrap_suffix
					+ "' class='selectbox' style='width: " + this.options.width
					+ ";'></div>");
	this.wrapperInput = $("#" + this.sourceElmentId).parent();
	this.displayInput = $("#" + this.sourceElmentId).clone();
	var displayId = this.sourceElmentId + this.options.display_suffix;
	this.displayInput.attr("id", displayId);
	this.displayInput.removeAttr("valid");
	this.displayInput.addClass("selectbox-field");
	this.displayInput.css("width", this.computeWidth());
	this.displayInput.attr("readonly", "readonly");
	this.displayInput.insertBefore($("#" + this.sourceElmentId));
	this.displayInput.val("");
	this.displayInput.keydown(function(event) {
		var ev = event || window.event;
		if (ev.keyCode == 8) {
			ev.keyCode = 0;
			ev.returnValue = false;
			ev.preventDefault();
		}
		;
	});
	$("#" + this.sourceElmentId).css("display", "none");
	$("#" + this.sourceElmentId).val("");
	var iconid = this.sourceElmentId + this.options.icon_suffix;
	var iconid2 = this.sourceElmentId + this.options.icon_suffix + "2";
	this.dropIcon = $(
			"<div class='selectbox-trigger' id='" + iconid
					+ "'><div class='selectbox-icon' id='" + iconid2
					+ "'></div></div>").appendTo(this.wrapperInput);
	// 给下拉按钮添加mouseMove事件
	this.dropIcon.hover(function() {
		if ($(this).attr("class") == "selectbox-trigger-disabled")
			return;
		$(this).attr("class", "selectbox-trigger-hover");
	}, function() {
		if ($(this).attr("class") == "selectbox-trigger-disabled")
			return;
		$(this).attr("class", "selectbox-trigger");
	});
	this.dropIcon.mousedown(function() {
		if ($(this).attr("class") == "selectbox-trigger-disabled")
			return;
		$(this).attr("class", "selectbox-trigger-pressed");
	});
	this.dropIcon.mouseup(function() {
		if ($(this).attr("class") == "selectbox-trigger-disabled")
			return;
		$(this).attr("class", "selectbox-trigger-hover");
	});
	// 添加单击事件
	var listid = this.sourceElmentId + this.options.droplistwrap_suffix;
	this.wrapperInput.wrap("<div class='selectbox-wapper'></div>");
	this.wrapper = this.wrapperInput.parent();
	this.listWrapper = $(
			"<div class='selecttree-list' id='" + this.sourceElmentId
					+ this.options.droplistwrap_suffix + "'>").appendTo(
			$(document.body));
	this.treeid = this.sourceElmentId + this.options.dropmenu_suffix;
	var temptreeid = this.treeid;
	var tempsourceid = this.sourceElmentId;
	this.list = $(
			"<ul id='"
					+ this.treeid
					+ "'class='ztree' style='margin:0px;border-bottom:1px solid #d9d9d9; border-left:1px solid #d9d9d9;border-right:1px solid #d9d9d9;border-top:0px solid#d9d9d9'></ul>")
			.appendTo(this.listWrapper);
	this.list.css("width", this.options.dropWidth);
	this.list.css("height", this.options.dropHeight);
	this.list.css("overflow-x", "hidden");
	this.list.css("overflow-y", "scroll");
	// overflow:scroll;
	var _this = this;
	this.dropIcon.click(function() {
		if ($(this).attr("class") == "selectbox-trigger-disabled")
			return;
		if ($("#" + listid).css("display") != "block") {
			$(this).attr("class", "selectbox-trigger-pressed");
			_this.displayInput.click();
		} else {
			$(this).attr("class", "selectbox-trigger-hover");
			if (($("#" + listid).css("display") == "block")
					&& (_this.options.multiple)) {
				var nodes = _this.m_tree.getCheckedNodes(true);
				var names = "";
				var values = "";
				for ( var i = 0, l = nodes.length; i < l; i++) {
					names += nodes[i].name + ",";
					values += nodes[i].id + ",";
				}
				if (names.length > 0)
					names = names.substring(0, names.length - 1);
				if (values.length > 0)
					values = values.substring(0, values.length - 1);
				$('#' + displayId).val(names);
				$("#" + tempsourceid).val(values);
			}
			$("#" + listid).css("display", "none");
		}
	});
	this.displayInput.keydown(function(e) {
		if ($(this).attr("disabled"))
			return;
		e = e || window.event;
		if (e.keyCode == 46) { // 退格键
			_this.clear();
		}
	});
	this.displayInput.click(function(e) {
		if ($(this).attr("disabled"))
			return;
		if ($("#" + listid).css("display") != "block") {
			var x = _this.displayInput.offset().top;
			var y = _this.displayInput.offset().left;
			$("#" + listid).css("left", y - 2);
			var tempHeight = _this.options.height.replace("px", "");
			$("#" + listid).css("top", x + parseInt(tempHeight, 10));
			$("#" + listid).css("display", "block");

		} else {
			if (($("#" + listid).css("display") == "block")
					&& (_this.options.multiple)) {
				if (_this.m_tree != null) {

					var nodes = _this.m_tree.getCheckedNodes(true);
					var names = "";
					var values = "";
					for ( var i = 0, l = nodes.length; i < l; i++) {
						names += nodes[i].name + ",";
						values += nodes[i].id + ",";

					}
					if (names.length > 0)
						names = names.substring(0, names.length - 1);
					if (values.length > 0)
						values = values.substring(0, values.length - 1);
					$('#' + displayId).val(names);
					$("#" + tempsourceid).val(values);
					_this.changeEvent(names, values);
				}
			}
			$("#" + listid).css("display", "none");
		}
	});
	$(document).click(
			function(e) {
				e = e || window.event;
				var target = e.target || ev.srcElement;
				if ((target.id == iconid) || (target.id == displayId)
						|| (target.id == iconid2)) {
					return;
				}
				var idName = target.id;
				if ((idName == undefined) || (idName == null)) {
					idName = "";
				}
				
				if (target.tagName == "SPAN"){
					var nodes1 = _this.m_tree.getSelectedNodes();
					if(nodes1!=null&&nodes1[0]!=null){
						if(nodes1[0].selectflag!=1&&_this.options.selectFlag!=1){
							$('#' + displayId).val("");
							$("#" + tempsourceid).val("");
							_this.clear();
							alert(_this.options.msg);
							return;
						}
					}
				}
				
				if ((idName.indexOf(temptreeid) >= 0)
						&& (idName.indexOf("switch") >= 0)) {// 展开和收费按钮
					return;
				}
				if (_this.options.multiple) { // 在有多选框的时候点击展开按钮和check按钮
					if ((idName.indexOf(temptreeid) >= 0)
							&& ((idName.indexOf("check") >= 0)||(idName.indexOf("span") >= 0)
						         || (idName.indexOf("ico") >= 0))) {// check
						return;
					}
				}
				if (($("#" + listid).css("display") == "block")
						&& (_this.options.multiple)) {
					if (_this.m_tree != null) {
						var nodes = _this.m_tree.getCheckedNodes(true);
						var names = "";
						var values = "";
						for ( var i = 0, l = nodes.length; i < l; i++) {
							names += nodes[i].name + ",";
							values += nodes[i].id + ",";
						}
						if (names.length > 0)
							names = names.substring(0, names.length - 1);
						if (values.length > 0)
							values = values.substring(0, values.length - 1);
						$('#' + displayId).val(names);
						$("#" + tempsourceid).val(values);
						_this.changeEvent(names, values);
					}
				} else if (($("#" + listid).css("display") == "block")
						&& (!_this.options.multiple)) {
					if (_this.m_tree == null) {
						$("#" + listid).css("display", "none");
						return;
					}
					nodes = _this.m_tree.getSelectedNodes();
					var names = "";
					var values = "";
					if (nodes.length > 0) {
						values = nodes[0].id;
						names = nodes[0].name;
					}
					$('#' + displayId).val(names);
					$("#" + tempsourceid).val(values);
					_this.changeEvent(names, values);
				}
				
				$("#" + listid).css("display", "none");
			});
};
/**
 * 绑定数据
 */
SelectTree.prototype.bind = function(data) {
	if (data == null)
		return;
	if (data == undefined)
		return;
	// ztree的设置
	var setting = {
		check : {
			enable : false
		},
		view : {
			dblClickExpand : false,
			showIcon : true,
			showLine : false
		},
		data : {
			simpleData : {
				enable : true
			}
		}
	};

	setting.check.chkboxType = {
		"Y" : this.options.sibling,
		"N" : this.options.sibling
	};

	setting.view.showIcon = this.options.showIcon;
	setting.view.showLine = this.options.showLine;
	setting.check.enable = this.options.multiple;
	// 单选框
	if ((this.options.multiple) && (this.options.radioType != null)) {
		setting.check.chkStyle = "radio";
		setting.check.radioType = this.options.radioType;
	}
	var datalist = new Array();
	for ( var i = 0; i < data.length; i++) {
		var rowdata = new Object();
		rowdata.id = data[i][this.options.id_field];
		rowdata.pId = data[i][this.options.pid_field];
		rowdata.name = data[i][this.options.display_field];
		rowdata.selectflag = data[i][this.options.selectFlag];
		if ((this.options.showIcon)
				&& (data[i][this.options.icon_field] != undefined)
				&& (data[i][this.options.icon_field] != null)) {
			rowdata.icon = data[i][this.options.icon_field];
		} else {
			rowdata.icon="../../images/icons/page1.png";
			rowdata.iconOpen="../../css/ztree/img/folderOpen.gif";
			rowdata.iconClose="../../css/ztree/img/folderClosed.gif";
		}
		if (this.options.multiple) {
			if ((data[i].checked != undefined) && (data[i].checked != null)) {
				rowdata.checked = data[i].checked;
			} else {
				rowdata.checked = false;
			}
		}
		if ("chkDisabled" in data[i]) {
			rowdata.chkDisabled = data[i].chkDisabled;
		}

		datalist[datalist.length] = rowdata;
	}
	$.fn.zTree.init(this.list, setting, datalist);
	this.m_tree = $.fn.zTree.getZTreeObj(this.treeid);
	this.m_tree.expandAll(true);
};

/**
 * 获取选中的值
 */
SelectTree.prototype.value = function() {
	return $("#" + this.sourceElmentId).val();
};
/**
 * 获取选中的文本
 */
SelectTree.prototype.text = function() {
	return this.displayInput.val();
};
/**
 * 根据值选中
 */
SelectTree.prototype.setValue = function(data) {
	var reqclear = false;
	if ($("#" + this.sourceElmentId).val() != "") {
		reqclear = true;
	}
	$("#" + this.sourceElmentId).val(data);
	var texts = "";
	if (this.m_tree != null) {
		if (this.options.multiple) {
			// 清空原来的选中
			if (reqclear) {
				this.m_tree.checkAllNodes(false);
			}
			var datas = data.split(",");
			for ( var i = 0; i < datas.length; i++) {
				var note = this.m_tree.getNodeByParam('id', datas[i], null);
				if ((note != undefined) && (note != null)) {
					this.m_tree.checkNode(note, true, false);
					if (texts == "") {
						texts = note.name;
					} else {
						texts = texts + "," + note.name;
					}
				}
			}
		} else {
			if (reqclear) {
				nodes = this.m_tree.getSelectedNodes();
				for ( var i = 0; i < nodes.length; i++) {
					this.m_tree.cancelSelectedNode(nodes[i]);
				}
			}
			var note = this.m_tree.getNodeByParam('id', data, null);
			if ((note != undefined) && (note != null)) {
				this.m_tree.checkNode(note, true, false);
				texts = note.name;
			}
			this.m_tree.selectNode(note, true);
		}
	}
	this.displayInput.val(texts);
};

/**
 * 清空值
 */
SelectTree.prototype.clear = function() {
	$("#" + this.sourceElmentId).val("");
	this.displayInput.val("");
	if (this.m_tree != null) {
		if (this.options.multiple) {
			this.m_tree.checkAllNodes(false);
		} else {
			nodes = this.m_tree.getSelectedNodes();
			for ( var i = 0; i < nodes.length; i++) {
				this.m_tree.cancelSelectedNode(nodes[i]);
			}
		}
	}
};
/**
 * 根据值选中 参数为数组
 */
SelectTree.prototype.setValues = function(data) {
	if (data = null) {
		alert("设置的值必须是数组！");
		return;
	}
	if (data = undefined) {
		alert("设置的值必须是数组！");
		return;
	}
	if (data.length == undefined) {
		alert("设置的值必须是数组！");
		return;
	}
	var texts = "";
	for ( var i = 0; i < data.length; i++) {
		if (texts == "") {
			texts = data[i];
		} else {
			texts = texts + "," + data[i];
		}
	}
	this.setValue(texts);
};
/**
 * 设置控件是否可用
 */
SelectTree.prototype.enabled = function(data) {
	if (data) {// 启用
		this.dropIcon.attr("class", "selectbox-trigger");
		this.displayInput.css("background-color", this.options.enabled_color);
		this.displayInput.removeAttr("disabled");
		this.options.disabled = false;
	} else {// 禁用
		this.dropIcon.attr("class", "selectbox-trigger-disabled");
		this.displayInput.css("background-color", this.options.disabled_color);
		this.displayInput.attr("disabled", true);
		this.options.disabled = true;
	}
};
/**
 * 改变事件
 * 
 * @param displayText
 * @param value
 */
SelectTree.prototype.changeEvent = function(displayText, value) {
	if (this.options.onChange != null) {
		this.options.onChange(displayText, value);
	}
};
SelectTree.prototype.destory = function() {
	this.m_tree.remove();
	this.m_tree = null;
	this.wrapper.remove();
	this.wrapper = null;
}