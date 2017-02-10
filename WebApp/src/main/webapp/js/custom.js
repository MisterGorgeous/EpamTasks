//password equals check
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

var forms = document.getElementsByClassName('addIcon');
for (var i=0; i < forms.length; i++) {
    forms[i].change = function () {
        this.closest('form').submit();
    }
}


//hide feedback after some period of time
setTimeout("$('#modalInfo').hide();", 3000);


//Autosubmit form after change state of some element
var forms = document.getElementsByClassName('subForm');
for (var i=0; i < forms.length; i++) {
    forms[i].onclick = function () {
        this.closest('form').submit();
    }
}


//Add and delete actors tags
var addB = document.getElementById('addactor');
addB.addEventListener("click", addtag);
var delB = document.getElementById('deleteactor');
delB.addEventListener("click", deltag);
var list = document.getElementById('actorlist');

function addtag() {
      var tag = '<div class="actor col-sm-4 col-xs-4 col-md-4 col-lg-4 well" name="actor"> <label class="col-sm-5 col-xs-5 col-md-5 col-lg-5 control-label">First name:</label> <input type="text" name="fname" placeholder="" class="input"> <label class="col-sm-5 col-xs-5 col-md-5 col-lg-5 control-label">Seccond name:</label> <input type="text" name="sname" placeholder="" class="input"> <label class="col-sm-5 col-xs-5 col-md-5 col-lg-5 control-label">Role:</label> <input type="text" name="role" placeholder="" class="input">    <label class="col-sm-5 col-xs-5 col-md-5 col-lg-5 control-label">Profession:</label> <input type="text" name="profession" placeholder="" class="input"> <label class="col-sm-5 col-xs-5 col-md-5 col-lg-5 control-label">Birthday:</label><input class="col-sm-5 col-xs-5 col-md-5 col-lg-5 control-label" type="date" name="birthday" value="2017-01-01" max="2017-01-01" min="1945-01-01"> <label class="col-sm-5 col-xs-5 col-md-5 col-lg-5 control-label">Birth Place:</label> <input class="col-sm-5 col-xs-5 col-md-5 col-lg-5"  type="text" name="birthplace" placeholder="" class="input"></div>';
    $(list).append(tag);
}

function deltag() {
    $(list).empty();
}

