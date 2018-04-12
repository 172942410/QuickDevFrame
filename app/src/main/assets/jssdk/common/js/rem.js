!function(win,opt){
	var count = 0,
		designWidth = opt.designWidth,
		designHeight = opt.designHeight||0,
		designFontSize = opt.designFontSize||20,
		callback = opt.callback||null,
		root = document.documentElement,
		body = document.body,
		rootWidth,newSize,t,self;
		//root.style.width = 100%
	//返回root元素字体计算结果
	
	function getNewFontSize(){
		var scale = designHeight !==0 ? Math.min(win.innerWidth / designWidth, win.innerHeight / designHeight) : win.innerWidth / designWidth;
		return Math.ceil(scale*designFontSize);
	};
	!function(){
		rootWidth = root.getBoundingClientRect().width;
		//alert(rootWidth)
		self = self ? self : arguments.callee;
		if(rootWidth!==win.innerWidth && count<20){
			setTimeout(function(){
				count++;
				self();
			},0)
		}else{
			newSize = getNewFontSize();
//						alert(newSize)
			//如果css已经兼容当前分辨率就不管了
			if(newSize+"px" !==getComputedStyle(root)["font-size"]){
				root.style.fontSize = newSize + "px";						
				return callback && callback(newSize)
			};
		};					
	}();
	
}(window,{
	designWidth:640,
	designFontSize:28,
})
