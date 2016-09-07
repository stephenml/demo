define(function(require, exports, module) {
	
	app.router = new (Backbone.Router.extend({
		routes : {
			'index' : 'index', /** 首页 */
			'message' : 'message', /** 消息 */
			'intro' : 'intro', /** 介绍 */
			'docs' : 'docs', /** 文档 */
			'downloads' : 'downloads', /** 下载 */
			'about' : 'about', /** 关于 */
			'*path': 'loadjs'
		},
		/**
		 * 加载js
		 * @param path
         */
		loadjs : function (path) {
			if (!path) return;
			path = '../' + path;
			var args = _.rest(arguments, 1);
			require.async(path, function(module) {
				module.init.apply(module, args);
			});
		},
		/**
		 * 首页
		 */
		index : function () {
			this.loadjs('index/index');
		},
		/**
		 * 消息
		 */
		message : function () {
			this.loadjs('message/index');
		},
		/**
		 * 介绍
		 */
		intro : function () {
			this.loadjs('intro/index');
		},
		/**
		 * 文档
		 */
		docs : function () {
			this.loadjs('docs/index');
		},
		/**
		 * 下载
		 */
		downloads : function () {
			this.loadjs('downloads/index');
		},
		/**
		 * 关于
		 */
		about : function () {
			this.loadjs('about/index');
		}
	}))();

});