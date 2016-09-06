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

	require('./router.js');

	Backbone.history.start();
	
	app.on('start', function () {
		if (!location.hash) {
			app.router.navigate('index', true);
		}
	});
	
	app.start();
});