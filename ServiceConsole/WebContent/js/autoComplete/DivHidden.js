var ImageUpState;
function switchSysBar1()
{
	if(ImageUpState==1){
		ImageUpState=3;
		//document.all.ImageUp.src="images/23.gif";
		document.getElementById('ImageUp').src="ge/images/23.gif";
		document.all("FilterLayer").style.display="none";
		//document.all("bottom").height="97%"
	}
	else
	{
		ImageUpState=1;
		document.getElementById('ImageUp').src="ge/images/21.gif";
		document.all("FilterLayer").style.display="";
		//document.all("bottom").height="67%"
	}

	return false;
}

function changeImage(id)
{
	if(id==2)
	{
		if(ImageUpState==1)
		{
			document.all.ImageUp.src="ge/images/22.gif";
		}
		else
		{
			document.all.ImageUp.src="ge/images/24.gif";
		}
	}
	return false;
}
function restoreImage(id)
{
	if(id==2)
	{
		if(ImageUpState==1)
		{
			document.all.ImageUp.src="ge/images/21.gif";
		}
		else
		{
			document.all.ImageUp.src="ge/images/23.gif";
		}
	}
}
//如果ifNoSearch为空则不切换隐藏
function hideFilter(ifNoSearch){
  //如果查了则隐藏过滤器
  if(ifNoSearch==null){
    switchSysBar1();
  }
}
ImageUpState=1;

