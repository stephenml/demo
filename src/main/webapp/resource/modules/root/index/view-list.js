define(function(require, exports, module) {

    var ItemView = require('./view-item.js');

    return Marionette.CollectionView.extend({
        
        className : 'main',
        childView : ItemView,

        emptyView : require('../common/view-empty.js'),
        emptyViewOptions : {html: '无数据'}
    });
});