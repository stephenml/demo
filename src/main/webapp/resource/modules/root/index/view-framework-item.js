define(function(require, exports, module) {

    return Marionette.ItemView.extend({

        className : 'framework-item',
        template : _.template(require('./tpl/framework-item.tpl.html')),

        events : {

        },
        onShow : function () {

        }
    });
});