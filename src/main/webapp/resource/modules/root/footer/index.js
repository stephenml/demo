define(function(require, exports, module) {

    var FooterView = Marionette.LayoutView.extend({

        className : 'footer-inner',
        template : _.template(require('./tpl/index.tpl.html')),

        events : {

        },
        initialize: function () {

        },
        onShow : function () {

        }
    });

    exports.init = function () {
        app.view.footer.show(new FooterView());
    }
});