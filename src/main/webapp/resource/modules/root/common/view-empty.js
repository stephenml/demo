define(function(require, exports, module) {

	return Marionette.ItemView.extend ({

		className: 'm',
		
		template: function (model) {
			if (model.img) {
				return '<div class="help-tip"><img style="margin: 0px auto;" src="' + model.img + '"></img></div>';
			} else if (model.text) {
				return '<div class="help-tip">' + model.text + '</div>';
			} else {
				return model.html || 'empty';
			}
		},
		
		initialize: function( options) {
			this.model = new Backbone.Model(options);
		}
			
	});
});