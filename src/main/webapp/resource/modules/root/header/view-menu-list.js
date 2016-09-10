define(function(require, exports, module) {

    var MenuItemView = require('./view-menu-item.js');

    return Marionette.CollectionView.extend({
        
        className : 'm',
        childView : MenuItemView,

        emptyView : app.emptyView,
        emptyViewOptions : {text : '无数据'}
    });
});