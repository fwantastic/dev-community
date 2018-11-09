"use strict";

// Enable the tab character onkeypress (onkeydown) inside textarea...
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

$('document').ready(loadHighlight());

function loadHighlight() {

	enableTab('contents');

	hljs.initHighlightingOnLoad();

	showdown
			.extension(
					'codehighlight',
					function() {
						function htmlunencode(text) {
							return (text.replace(/&amp;/g, '&').replace(
									/&lt;/g, '<').replace(/&gt;/g, '>'));
						}
						return [ {
							type : 'output',
							filter : function(text, converter, options) {
								// use new shodown's regexp engine to
								// conditionally parse codeblocks
								var left = '<pre><code\\b[^>]*>', right = '</code></pre>', flags = 'g', replacement = function(
										wholeMatch, match, left, right) {
									// unescape match to prevent double escaping
									match = htmlunencode(match);
									return left
											+ hljs.highlightAuto(match).value
											+ right;
								};
								return showdown.helper.replaceRecursiveRegExp(
										text, replacement, left, right, flags);
							}
						} ];
					});

}

var converter = new showdown.Converter({
	extensions : [ 'codehighlight' ]
});

function convertMarkdown(sourceElement, targetElement) {

	var markdownText = document.getElementById(sourceElement).value;
	document.getElementById(targetElement).innerHTML = converter
			.makeHtml(markdownText);

	// console.log(document.getElementById(targetElement));
	// console.log(document.getElementById(targetElement).innerHTML);

	// This is required if converting text dynamically, otherwise the code block
	// is not highlighted correctly.
	$('pre code').each(function(i, block) {
		hljs.highlightBlock(block);
	});
};

var uploadFile = function() {
	var file = document.getElementById("fileUpload");

	if (file.files.length == 0) {
		alert("Choose file to upload");
		return;
	}

	var _csrf_token = $("#_csrf_token").val();

	var form_data = new FormData();
	form_data.append("file", file.files[0]);
	$
			.ajax({
				data : form_data,
				type : "POST",
				headers : {
					"X-CSRF-Token" : _csrf_token
				},
				url : "/files",
				cache : false,
				contentType : false,
				enctype : "multipart/form-data",
				processData : false,
				success : function(data) {
					alert('File has been uploaded. File URL: ' + data);

					var newRow = "<tr><td><input type='text' readonly='readonly' value='"
							+ data
							+ "' /></td><td>"
							+ file.files[0].name
							+ "</td></tr>";
					$('#fileUploadTable tr:last').after(newRow);

					document.getElementById("fileUpload").value = null;
				},
				error : function() {
					alert("Failed to upload file");
				}
			});
}
