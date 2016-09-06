define(function(require, exports, module) {

    var IndexView = Marionette.LayoutView.extend({
        className : 'main',
        template : _.template(require('./tpl/index.tpl.html')),
        events : {

        },
        initialize: function () {

        },
        onShow : function () {

        },
        onReader : function () {
            
        }
    });

    exports.init = function () {
        app.view.content.show(new IndexView());
    }
});