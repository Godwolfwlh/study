$(document).ready(function() {
	$('.loginbox').css({
		'position' : 'absolute',
		'left' : (common.getWinWidth() - 692) / 2,
		'top':common.getWinHeight()/4
	});
	$(window).resize(function() {
		$('.loginbox').css({
			'position' : 'absolute',
			'left' : (common.getWinWidth() - 692) / 2,
			'top':common.getWinHeight()/4
		});
	});
});

function keypress(e){
	if(!window.event){
		e = e.which;
	}else{
		e = window.event.keyCode;
	}
	if(e==13||e==42)	//'enter' key,*
	{
		loginSys();
	}
}

function refreshImg() {
	$("#captchaimg").attr("src", common.projectName()+"VerifyCode/jpg?"+common.getSysTime());
}

function loginSys(){
	var obj=common.getFormData();
	common.ajaxCall(common.excuteUrl("/userController/userlogin.do"),obj,function(ret){
		refreshImg();
		//alert(ret.statusMessage);
		if(ret.statusCode==0){
			common.winHref("/hotel/main.html","?time="+common.guid());
		}else{
			layer.msg(ret.statusMessage,{icon:2});
		}
	});
}
