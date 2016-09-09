define(function(require, exports, module) {

    var IntroView = Marionette.LayoutView.extend({

        className : 'content-inner',
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
        app.view.content.show(new IntroView());
    }
});