define(function(require, exports, module) {

    var FrameworkListView = require('./view-framework-list.js');

    var IndexView = Marionette.LayoutView.extend({

        className : 'content-inner',
        template : _.template(require('./tpl/index.tpl.html')),

        events : {
            'click .framework-item' : 'showFramework'
        },
        regions : {
            'frameworkList' : '@ui.frameworkList'
        },
        ui : {
            'frameworkList' : '.framework-list'
        },
        initialize: function () {

        },
        onShow : function () {
            var _this = this;
            app.tips.loading();
            var collection = new Backbone.Collection();
            collection.fetch({
                url : ctx + '/framework/list',
                success : function () {
                    _this.frameworkList.show(new FrameworkListView({collection: collection}));
                    app.tips.close();
                }
            });
        },
        /**
         * 显示框架
         * @param event
         */
        showFramework : function (event) {
            var $target = $(event.currentTarget);
            var href = $target.find('.item').attr('href');
            if (href) {
                window.open(href);
            }
        }
    });

    exports.init = function () {
        app.view.content.show(new IndexView());
    }
});