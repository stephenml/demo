define(function(require, exports, module) {

    var MenuItemView = require('./view-menu-item.js');

    return Marionette.CollectionView.extend({
        
        className : 'm',
        childView : MenuItemView,

        emptyView : require('../common/view-empty.js'),
        emptyViewOptions : {html: '无数据'}
    });
});