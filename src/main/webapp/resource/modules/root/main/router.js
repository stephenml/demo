define(function(require, exports, module) {
	
	app.router = new (Backbone.Router.extend({
		routes : {
			'index' : 'index',
			'*path': 'loadjs'
		},
		loadjs : function (path) {
			if (!path) return;
			path = '../' + path;
			var args = _.rest(arguments, 1);
			require.async(path, function(module) {
				module.init.apply(module, args);
			});
		},
		index : function () {
			this.loadjs('index/index');
		}
	}))();

});