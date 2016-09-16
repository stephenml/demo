define(function(require, exports, module) {

    require('jquery-cookie');
    require('nomen');

    var MessagesListView = require('./view-messages-list.js');

    var MessagesView = Marionette.LayoutView.extend({

        className : 'content-inner',
        template : _.template(require('./tpl/index.tpl.html')),

        events : {
            'click .messages-send-set' : 'showMenu', /** 显示设置发送快捷键 */
            'click .messages-send-btn' : 'sendMessages' /** 发送消息 */
        },
        regions : {
            'messagesMain' : '@ui.messagesMain'
        },
        ui : {
            'messagesMain' : '.messages-main'
        },
        startId : null, /** 消息开始id */
        messagesInterval : null, /** 消息定时器 */
        initialize : function () {
            /** 获取用户信息 */
            window.userInfo = $.cookie('user_info');
            if (!window.userInfo) {
                /** 初始化用户信息 */
                window.userInfo = {
                    uuid : this.generateUUID(),
                    name : this.generateName()
                };
                $.cookie('user_info', JSON.stringify(window.userInfo)/** , {expires : 7} */);
            } else {
                window.userInfo = JSON.parse(window.userInfo);
            }
        },
        onShow : function () {
            var _this = this;
            /** 生成头像 */
            $.get(ctx + '/messages/head?uuid=' + window.userInfo.uuid, function (head) {
                window.userInfo.head = head;
                _this.$el.find('.head img').attr('src', window.userInfo.head);
            });

            _this.getMessages();

            /** 获取消息定时器 */
            _this.messagesInterval = setInterval(function () {
                _this.getMessages();
            }, 2000);
        },
        /**
         * 获取消息
         */
        getMessages : function () {
            var _this = this;

            if (!_this.startId) {
                app.tips.loading();
            }

            var collection = new Backbone.Collection();
            collection.fetch({
                url : ctx + '/messages/list',
                data : {startId : _this.startId},
                success : function () {
                    /** 显示菜单 */
                    _this.messagesMain.show(new MessagesListView({collection: collection}));
                    if (!_this.startId) {
                        _this.scrollTop();
                    }
                    /** 获取开始id */
                    _this.startId = collection.models[0].get('id');
                    app.tips.close();
                }
            });
        },
        /**
         * 随机生成uuid
         * @returns {string}
         */
        generateUUID : function () {
            return 'xxxxxxxxxx'.replace(/[x]/g, function () {
                return (Math.random() * 16 | 0).toString(16);
            }) + new Date().getTime(); /** 10位随机 + 时间戳 */
        },
        /**
         * 随机生成用户名
         * @returns {*}
         */
        generateName : function () {
            return generateNick(1, 'title');
        },
        /**
         * 滚动到最底部
         */
        scrollTop : function () {
            this.$el.find('.messages-textarea textarea').val('');
            this.messagesMain.$el.scrollTop(this.$el.find('.messages-list').height());
        },
        /**
         * 显示设置发送快捷键
         */
        showMenu : function () {
            // this.$el.find('.set-menu').toggle();
        }/** ,
        serializeData : function () {
            return _.extend({}, window.userInfo);
        } */,
        /**
         * 发送消息
         */
        sendMessages : function () {
            var _this = this;

            /** 消息内容 */
            var content = _this.$el.find('.messages-textarea textarea').val();
            /** 是否为空 */
            if (!content.replace(/\s*/g,'')) {
                return;
            }
            var model = new Backbone.Model();
            model.save(
                {
                    uuid : window.userInfo.uuid,
                    name : window.userInfo.name,
                    content : content.replace(/.\n/g, '\r\n<br/>\r\n')
                },
                {
                    url : ctx + '/messages/save',
                    success : function () {
                        _this.appendMessages(model);
                    }
                }
            );
        },
        /**
         * 添加消息
         * @param model
         */
        appendMessages : function (model) {
            this.$el.find('.messages-list').append([
                '<li class="messages-own">',
                    '<div class="messages-user">',
                        '<img src="' + window.userInfo.head + '">',
                        '<cite><i>' + model.get('commit_date') + '</i>' + window.userInfo.name + '</cite>',
                    '</div>',
                    '<div class="messages-text">' + model.get('content') + '</div>',
                '</li>'
            ].join(''));
            this.scrollTop();
        },
        /**
         * view 销毁事件
         */
        onBeforeDestroy : function () {
            /** 清除定时器 */
            clearInterval(this.messagesInterval);
        }
    });

    exports.init = function () {
        app.view.content.show(new MessagesView());
    }
});