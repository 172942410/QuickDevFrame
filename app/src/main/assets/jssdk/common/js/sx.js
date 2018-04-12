var sixin = {
	//alert
	alertMsg: function(params,sureFn,cancelFn) {
		sx.alertMessage({
			params: params,
			success: function(res) {
				if(sureFn && typeof sureFn == "function") {
					sureFn();
				};
				//点击确认按钮后回调该函数
			},
			cancel: function(err) {
				//点击取消按钮后回调该函数
				if(cancelFn && typeof cancelFn  == "function") {
					cancelFn();
				};
			},
			fail: function(err) {}
		});

	},
	//toast
	toast: function(params,sureFn) {
		sx.showMessage({
			params: params,
			success: function(res) {
				if(sureFn && typeof sureFn == "function") {
					sureFn(res);
				}
			},
			fail: function(err) {
			}
		});
	},
	//查看员工信息
	viewEmployeeInfo: function(id) {
		var params = {
			userId: id
		};
		sx.viewEmployeeInfo({
			params: params,
			success: function(res) {
			},
			fail: function(err) {
			}
		});
	},
	//选人
	chooseContacts: function(sureFn, flag) {
		var params = {
			selectType: flag || 0,
		};
		sx.chooseContacts({
			params: params,
			success: function(res) {
				if(sureFn && typeof sureFn == "function") {
					sureFn(res.contacts);
				}
			},
			cancel: function(err) {
				//用户取消选择联系人操作回调该函数
			},
			fail: function(err) {}
		});
	},
	//关闭当前页
	closeWindow: function(flag, options,sureFn) {
		var params = {
			needCompletionHandler: flag || false,
			handlerResult: options 
		};
		if(flag) {
			sx.closeWindow({
				params: params,
				success: function(res) {
					if(sureFn && typeof sureFn == "function") {
						sureFn();
					}
				},
				fail: function(err) {
					
				}
			});
		} else {
			sx.closeWindow({
				success: function(res) {
				},
				fail: function(err) {}
			});
		}
	},
	//图片预览
	previewImage: function(options) {
		var params = {
			current: options.current,
			urls: options.urls
		};
		sx.previewImage({
			params: params,
			success: function(res) {
			},
			fail: function(err) {
			}
		});
	},
	goError:function(){
		window.location = "../../common/html/error.html";
	},
}