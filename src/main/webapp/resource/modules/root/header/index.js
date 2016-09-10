define(function(require, exports, module) {

    var MenuListView = require('./view-menu-list.js');

    var HeaderView = Marionette.LayoutView.extend({

        className : 'header-inner',
        template : _.template(require('./tpl/index.tpl.html')),

        regions : {
            'menu' : '@ui.menu'
        },
        ui : {
            'menu' : '#menu'
        },
        initialize: function () {

        },
        onShow : function () {
            var _this = this;
            app.tips.loading();
            var collection = new Backbone.Collection();
            collection.fetch({
                url : ctx + '/menu/list',
                success : function () {
                    /** 显示菜单 */
                    _this.menu.show(new MenuListView({collection: collection}));
                    _this.setActiveMenu();
                    /** 加延时是因为可能关不掉tip */
                    setTimeout(function () {
                        app.tips.close();
                    }, 200);
                }
            });
        },
        /**
         * 设置选择的menu
         */
        setActiveMenu : function () {
            var hash = location.hash.replace(/\/.*/, '');
            this.$el.find('#menu .menu-item a').removeClass('active');
            this.$el.find('#menu .menu-item a[href="' + hash + '"]').addClass('active');
        }
    });

    exports.init = function () {
        app.view.header.show(new HeaderView());
    }
});