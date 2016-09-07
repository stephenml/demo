define(function(require, exports, module) {

    var MenuListView = require('./view-menu-list.js');

    var HeaderView = Marionette.LayoutView.extend({

        className : 'header-inner',
        template : _.template(require('./tpl/index.tpl.html')),

        events : {
            'click .menu-item' : 'clickMenu'
        },
        regions: {
            'menu' : '#menu'
        },
        initialize: function () {

        },
        onShow : function () {
            var _this = this;
            var collection = new Backbone.Collection();
            collection.fetch({
                url : ctx + '/menu',
                success : function () {
                    /** 显示菜单 */
                    _this.menu.show(new MenuListView({collection: collection}));
                }
            });
        },
        /**
         * 点击导航菜单
         * @param event
         */
        clickMenu : function (event) {
            this.$el.find('.menu-item').removeClass('active');
            var $target = $(event.currentTarget);
            $target.addClass('active');
        }
    });

    exports.init = function () {
        app.view.header.show(new HeaderView());
    }
});