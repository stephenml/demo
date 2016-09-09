seajs.config({
	preload : seajs.production ? ['seajs-style'] : ['seajs-style', 'seajs-text'],
	charset : 'utf-8',
    alias : {
		/** Seajs相关 */
    	'seajs-style' : 'seajs/seajs-style/1.0.2/seajs-style',
		'seajs-text' : 'seajs/seajs-text/1.0.2/seajs-text',

		/** jQuery相关 */
		'jquery' : 'jquery/jquery/1.12.0/jquery',

		/** Markdown解析 */
		'remarkable' : 'gallery/remarkable/remarkable'
    }
});