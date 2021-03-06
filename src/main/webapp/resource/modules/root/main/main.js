define(function(require, exports, module) {

	window.app = new Marionette.Application();
	
	app.view = new (Marionette.LayoutView.extend({
		el : 'body',
		regions : {
			header : '#header',
			content : '#content',
			footer : '#footer'
		},
		events : {

		}
	}))();

	/** 分页视图 */
	app.page = require('../common/view-page.js');
	/** 加载提示 */
	app.tips = require('../common/view-tips.js');
	/** 空页面 */
	app.emptyView = require('../common/view-empty.js');

	/** 加载路由 */
	require('./router.js');
	/** 加载页眉 */
	require('../header/index.js').init();
	/** 加载页脚 */
	require('../footer/index.js').init();

	Backbone.history.start();
	
	app.on('start', function () {
		if (!location.hash) {
			app.router.navigate('index', true);
		}
	});
	
	app.start();
});