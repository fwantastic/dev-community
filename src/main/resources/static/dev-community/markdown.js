//Enable the tab character onkeypress (onkeydown) inside textarea... 
function enableTab(id) {
	var el = document.getElementById(id);
	el.onkeydown = function(e) {
		if (e.keyCode === 9) { // tab was pressed

			// get caret position/selection
			var val = this.value, start = this.selectionStart, end = this.selectionEnd;

			// set textarea value to: text before caret + tab + text after caret
			this.value = val.substring(0, start) + '\t' + val.substring(end);

			// put caret at right position again
			this.selectionStart = this.selectionEnd = start + 1;

			// prevent the focus lose
			return false;
		}
	};
}

enableTab('contents');

//var Remarkable = require('remarkable');
//var hljs = require('highlight.js') // https://highlightjs.org/

// Actual default values
var md = new Remarkable('full', {
	//html : false, // Enable HTML tags in source
	//xhtmlOut : false, // Use '/' to close single tags (<br />)
	breaks : true, // Convert '\n' in paragraphs into <br>
	//langPrefix : 'language-', // CSS language prefix for fenced blocks
	//linkify : true, // autoconvert URL-like texts to links
	//linkTarget : '', // set target to open link in

	// Enable some language-neutral replacements + quotes beautification
	//typographer : false,

	// Double + single quotes replacement pairs, when typographer enabled,
	// and smartquotes on. Set doubles to '«»' for Russian, '„“' for German.
	//quotes : '“”‘’',

	// Highlighter function. Should return escaped HTML,
	// or '' if input not changed
	highlight : function(str, lang) {
		if (lang && hljs.getLanguage(lang)) {
			try {
				return hljs.highlight(lang, str).value;
			} catch (err) {
			}
		}

		try {
			return hljs.highlightAuto(str).value;
		} catch (err) {
		}

		return ''; // use external default escaping
	}
});

var convert = function() {
	$('#preview').html(md.render($('#contents').val()));
};
convert();
$('#contents').keyup(convert);