define(function(require, exports, module) {

    var MessageView = Marionette.LayoutView.extend({

        className : 'm',
        template : _.template(require('./tpl/index.tpl.html')),

        events : {

        },
        regions: {

        },
        initialize: function () {

        },
        onShow : function () {

        }
    });

    exports.init = function () {
        app.view.content.show(new MessageView());
    }
});