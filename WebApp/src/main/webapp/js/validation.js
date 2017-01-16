;
var pass = document.getElementById('password');
var pass2 = document.getElementById('confPassword');
var info = document.getElementById('logged');

function checkPasswords() {
    if (pass.value != pass2.value) {
        info.innerHTML = "Passwords must be equal.";
        return false;
    }
    info.innerText = '';
    return true;
}








