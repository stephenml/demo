define(function(require, exports, module) {

    var MessagesItemView = require('./view-messages-item.js');

    return Marionette.CollectionView.extend({

        tagName : 'ul',
        className : 'messages-list',
        childView : MessagesItemView,

        emptyView : app.emptyView,
        emptyViewOptions : {text : '无数据'}
    });
});