define(function(require, exports, module) {

    var FrameworkItemView = require('./view-framework-item.js');

    return Marionette.CollectionView.extend({
        
        className : 'm',
        childView : FrameworkItemView,

        emptyView : app.emptyView,
        emptyViewOptions : {text : '无数据'}
    });
});