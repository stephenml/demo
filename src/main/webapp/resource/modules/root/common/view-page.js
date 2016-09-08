define(function(require, exports, module) {

    return Marionette.LayoutView.extend({

        className : 'm',
        template : _.template(require('./tpl/page.tpl.html')),

        triggers : {
            'click .prev-page' : 'prevPage',
            'click .next-page' : 'nextPage'
        },
        initialize : function() {

        },
        onShow : function() {

        }
    });
});