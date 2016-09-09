define(function(require, exports, module) {

    var IndexView = Marionette.LayoutView.extend({

        className : 'content-inner',
        template : _.template(require('./tpl/index.tpl.html')),

        events : {
            'click .words ul li' : 'showFramework'
        },
        regions: {

        },
        initialize: function () {

        },
        onShow : function () {

        },
        /**
         * 显示框架
         * @param event
         */
        showFramework : function (event) {
            var $target = $(event.currentTarget);
            var href = $target.attr('href');
            if (href) {
                window.open(href);
            }
        }
    });

    exports.init = function () {
        app.view.content.show(new IndexView());
    }
});