define(function(require, exports, module) {

    return Marionette.ItemView.extend({

        tagName : 'li',
        className : '',
        template : _.template(require('./tpl/messages-item.tpl.html')),

        events : {

        },
        onShow : function () {
            if (window.userInfo.uuid == this.model.get('uuid')) {
                this.$el.addClass('messages-own');
            }
        }
    });
});