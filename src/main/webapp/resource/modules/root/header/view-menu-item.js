define(function(require, exports, module) {

    return Marionette.ItemView.extend({

        tagName : 'li',
        className : 'menu-item',
        template : _.template(require('./tpl/menu-item.tpl.html')),

        events : {

        },
        onShow : function() {

        }
    });

});