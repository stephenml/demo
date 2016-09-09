define(function(require, exports, module) {
	
	app.router = new (Backbone.Router.extend({
		routes : {
			'index(/:tab)' : 'index', /** 首页 */
			'messages(/:tab)' : 'messages', /** 消息 */
			'intro(/:tab)' : 'intro', /** 介绍 */
			'docs(/:tab)' : 'docs', /** 文档 */
			'downloads(/:tab)' : 'downloads', /** 下载 */
			'about(/:tab)' : 'about', /** 关于 */

			'*path': 'loadJs'
		},
		/**
		 * 加载js
		 * @param path
         */
		loadJs : function (path) {
			if (!path) return;
			path = '../' + path;
			var args = _.rest(arguments, 1);
			require.async(path, function(module) {
				module.init.apply(module, args);
			});
			this.setActiveMenu();
		},
		/**
		 * 设置选择的menu
		 */
		setActiveMenu : function () {
			var hash = location.hash.replace(/\/.*/, '');
			$('#menu .menu-item a').removeClass('active');
			$('#menu .menu-item a[href="' + hash + '"]').addClass('active');
		},
		/**
		 * 首页
		 */
		index : function (tab) {
			this.loadJs('index/index');
			if (tab) {
				console.log('tab : ' + tab);
			}
		},
		/**
		 * 消息
		 */
		messages : function (tab) {
			this.loadJs('messages/index');
			if (tab) {
				console.log('tab : ' + tab);
			}
		},
		/**
		 * 介绍
		 */
		intro : function (tab) {
			this.loadJs('intro/index');
			if (tab) {
				console.log('tab : ' + tab);
			}
		},
		/**
		 * 文档
		 */
		docs : function (tab) {
			this.loadJs('docs/index');
			if (tab) {
				console.log('tab : ' + tab);
			}
		},
		/**
		 * 下载
		 */
		downloads : function (tab) {
			this.loadJs('downloads/index');
			if (tab) {
				console.log('tab : ' + tab);
			}
		},
		/**
		 * 关于
		 */
		about : function (tab) {
			this.loadJs('about/index');
			if (tab) {
				console.log('tab : ' + tab);
			}
		}
	}))();

});