seajs.config({
	preload : seajs.production ? ['seajs-style'] : ['seajs-style', 'seajs-text'],
	charset : 'utf-8',
    alias : {
    	'seajs-style' : 'seajs/seajs-style/1.0.2/seajs-style',
		'seajs-text' : 'seajs/seajs-text/1.0.2/seajs-text',
	    
    }
});