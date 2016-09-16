seajs.config({
	preload : seajs.production ? ['seajs-style'] : ['seajs-style', 'seajs-text'],
	charset : 'utf-8',
    alias : {
		/** Seajs相关 */
    	'seajs-style' : 'seajs/seajs-style/1.0.2/seajs-style',
		'seajs-text' : 'seajs/seajs-text/1.0.2/seajs-text',

		/** jQuery相关 */
		'jquery' : 'jquery/jquery/1.12.0/jquery',
		'jquery-poshytip' : 'jquery/jquery-poshytip/1.2/jquery-poshytip',
		'jquery-cookie' : 'jquery/jquery-cookie/1.4.1/jquery.cookie',

		/** Markdown解析 */
		'remarkable' : 'gallery/remarkable/1.6.0/remarkable.min',

		/** 随机生成用户名 */
		'nomen' : 'gallery/nomen/0.0.1/nomen.min'
    }
});