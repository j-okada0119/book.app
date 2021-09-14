$(document).ready(function() {
	$('#form').validationEngine('attach', {
        promptPosition: 'topRight',
        allrules: {
            "passwordFormat": {
                "regex": "^(?=.*[a-z])(?=.*[.!*$@[?/-])(?=.*[0-9])[a-zA-Z0-9.!*$@[?/-]",
                "alertText": "* 半角英字・半角数字・半角記号全てを含めて入力してください。",
            }
        }
    });
});