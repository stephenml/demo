define(function(require, exports, module) {

    require('remarkable');

    var DocsView = Marionette.LayoutView.extend({

        className : 'content-inner',
        template : _.template(require('./tpl/index.tpl.html')),

        events : {

        },
        initialize: function () {

        },
        onShow : function () {
            var _this = this;
            app.tips.loading();

            var url = 'https://raw.githubusercontent.com/stephenml/demo/master/README.md';
            $.get(url, function (result) {
                var md = new Remarkable({
                    html:         false,        // Enable html tags in source
                    xhtmlOut:     false,        // Use '/' to close single tags (<br />)
                    breaks:       false,        // Convert '\n' in paragraphs into <br>
                    langPrefix:   'language-',  // CSS language prefix for fenced blocks
                    linkify:      false,        // Autoconvert url-like texts to links
                    typographer:  false,        // Enable smartypants and other sweet transforms

                    // Highlighter function. Should return escaped html,
                    // or '' if input not changed
                    highlight: function (/*str, , lang*/) { return ''; }
                });
                _this.$el.find('.markdown-body').html(md.render(result));
                app.tips.close();
            });
        }
    });

    exports.init = function () {
        app.view.content.show(new DocsView());
    }
});