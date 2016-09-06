define(function(require, exports, module) {

    var ListView = require('./view-list.js');
    var PageInfoView = require('../common/view-page.js');

    var IndexView = Marionette.LayoutView.extend({

        className : 'main',
        template : _.template(require('./tpl/index.tpl.html')),

        pageModel : null,

        events : {

        },
        childEvents: {
            'prevPage' : 'prevPage',
            'nextPage' : 'nextPage'
        },
        regions: {
            'content' : '#index-list',
            'pageInfo' : '#page-info'
        },
        initialize: function () {

        },
        onShow : function () {
            this.pageModel = new Backbone.Model({pageNum : 1, pageSize : 10});
            this.loadList();
        },
        loadList : function () {
            var _this = this;
            var model = new Backbone.Model();
            model.fetch({
                url : ctx + '/list',
                data : {pageNum : this.pageModel.get('pageNum'), pageSize : this.pageModel.get('pageSize')},
                success : function () {
                    /** 显示数据 */
                    _this.content.show(new ListView({collection: new Backbone.Collection(model.get('data'))}));
                    /** 设置分页信息 */
                    var pageNum = parseInt(model.get('pageNum')) || 1;
                    var pageSize = parseInt(model.get('pageSize')) || 20;
                    var totalCount = parseInt(model.get('totalCount')) || 0;
                    var totalPage = totalCount % pageSize != 0 ? parseInt((totalCount / pageSize)) + 1 : totalCount / pageSize || 1;
                    _this.pageModel.set({pageNum : pageNum, pageSize : pageSize, totalCount : totalCount, totalPage : totalPage});
                    _this.pageInfo.show(new PageInfoView({model : _this.pageModel}));
                }
            });
        },
        prevPage : function() {
            if (parseInt(this.pageModel.get("pageNum")) > 1) {
                this.pageModel.set({pageNum : parseInt(this.pageModel.get("pageNum")) - 1});
                this.loadList();
            }
        },
        nextPage : function() {
            if (parseInt(this.pageModel.get("pageNum")) <= parseInt(this.pageModel.get('totalPage')) - 1) {
                this.pageModel.set({pageNum : parseInt(this.pageModel.get("pageNum")) + 1});
                this.loadList();
            }
        }
    });

    exports.init = function () {
        app.view.content.show(new IndexView());
    }
});