;
var pass = document.getElementById('pass1');
var pass2 = document.getElementById('pass2');
var email = document.getElementById('email');
var email2 = document.getElementById('email2');
var addB = document.getElementById('add');
var delB = document.getElementById('delete');
var submit = document.getElementById('submit');


addB.addEventListener("click", show);
delB.addEventListener("click", show);
submit.addEventListener("click", checkValidation);

function checkPass() {
    if (pass.value != pass2.value) {
        alert("Passwords aren't equal.Please try again.");
        return false;
    }
    return true;
}

function checkEmail() {
    if (email.value == email2.value && email2 != "") {
        alert("Emails must be different.Please try again.");
        return false;
    }
    return true;
}

//seccond email
function show() {
    var par = document.getElementById('pforemail');
    var but = event.target;
    par.hidden = but.getAttribute('name') == "delete";
}

//check validation by JS
function checkValidation() {
    if (!checkPass() || !checkEmail()) {
        return;
    }

    var pat;
    var text;

    var textFields = document.getElementsByTagName('input');
    for (i = 0; i < textFields.length; i++) {
        if (textFields[i].className == "textField") { //if it only text
            pat = textFields[i].pattern;   //get regex
            text = textFields[i].value;    //get data

            if (text.search(pat) == -1) { //check regexp with JS
                alert("Mistake");
                return;
            }
        }
    }

    alert("Correct");
}






