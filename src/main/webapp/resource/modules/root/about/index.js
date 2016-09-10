define(function(require, exports, module) {

    var AboutView = Marionette.LayoutView.extend({

        className : 'content-inner',
        template : _.template(require('./tpl/index.tpl.html')),

        events : {

        },
        regions : {

        },
        initialize : function () {

        },
        onShow : function () {
            var _this = this;
            app.tips.loading();

            var url = 'https://raw.githubusercontent.com/stephenml/demo/master/LICENSE';
            $.get(url, function (result) {
                _this.$el.find('.license').html(result);
                app.tips.close();
            });
        }
    });

    exports.init = function () {
        app.view.content.show(new AboutView());
    }
});