/**
 * 
 */


function buttonClick() {
	var checked = confirm("削除します");
	if (checked == true) {
		return true;
	} else {
		return false;
	}

	//    if(!window.confirm("入力内容を消去します")){
	//		alert("削除されません")
	//		location.reload()
	//	}else{
	//		alert("削除されます")
	//		location.reload()
	//	}
	//alert("本当に削除してもよろしいでしょうか？")
}
