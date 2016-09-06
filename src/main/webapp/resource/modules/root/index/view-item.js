define(function(require, exports, module) {

    return Marionette.ItemView.extend({

        tagName : 'li',
        className : '',
        template : _.template(require('./tpl/item.tpl.html')),

        events : {

        },
        onShow : function() {

        }
    });

});