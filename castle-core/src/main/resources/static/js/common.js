var parseQueryString = function(arr_url) {
	var reg_para = /([^&=]+)=([\w\W]*?)(&|$|#)/g, ret = {};
	if (arr_url) {
		var str_para = arr_url, result;
		while ((result = reg_para.exec(str_para)) != null) {
			if (result[2]) {
				ret[result[1]] = decodeURIComponent(result[2]);
			}
		}
	}
	return ret;
};

var alertx = function(content) {
	$.alert({
		title : "提示",
		content : content,
		buttons : {
			ok : {
				text : "确定"
			}
		}
	});
}

var confirmx = function(title, content, action) {
	$.confirm({
		title : title,
		content : content,
		buttons : {
			confirm : {
				text : "确定",
				action : action
			},
			cancel : {
				text : "取消"
			}
		}
	});
}

var actionWithSelectedRows = function(grid, action, message) {
	var selectedRows = grid.bootgrid("getSelectedRows");
	if (selectedRows && selectedRows.length > 0) {
		action(grid, selectedRows);
	} else {
		alertx(message);
	}
}

var handleAction = function(action) {
	if (action.type == 'composite') {
		$.each(action.data, handleAction);
	} else {
		if (action.type == 'alert') {
			alertx(action.data);
		} else if (action.type == 'redirect') {
			location.href = action.data;
		} else {
			if(action.code == 2) {
				alertx("验证错误");
			}
		}
	}
}