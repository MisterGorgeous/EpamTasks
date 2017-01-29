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



//hide feedback after some period of time
setTimeout("$('#modalInfo').hide();", 3000);


//Autosubmit form after change state of some element
var forms = document.getElementsByClassName('subForm');
for (var i=0; i < forms.length; i++) {
    forms[i].onchange = function () {
        this.closest('form').submit();
    }
}

$(".subForm").click(function(){
    $(this).closest('form').submit();
});




//Add and delete actors tags
var addB = document.getElementById('addactor');
addB.addEventListener("click", addtag);
var delB = document.getElementById('deleteactor');
delB.addEventListener("click", deltag);
var list = document.getElementById('actorlist');

function addtag() {
    //  var tag = '<div class="actor col-md-4 col-lg-4 well" name="actor"> <label class="control-label">First name:</label> <input type="text" name="fname" placeholder="" class="input"> <label class="control-label">Seccond name:</label> <input type="text" name="sname" placeholder="" class="input"> <label class="control-label">Role:</label> <input type="text" name="role" placeholder="" class="input"> </div>';
    var tag = '<div class="actor col-md-4 col-lg-4 well" name="actor"> <label class="control-label">First name:</label> <input type="text" name="fname" placeholder="" class="input"> <label class="control-label">Seccond name:</label> <input type="text" name="sname" placeholder="" class="input"> <label class="control-label">Role:</label> <input type="text" name="role" placeholder="" class="input">    <label class="control-label">Profession:</label> <input type="text" name="profession" placeholder="" class="input"> <label class="control-label">Birthday:</label><input type="date" name="birthday" value="2017-01-01" max="2017-01-01" min="1945-01-01"> <label class="control-label">Birth Place:</label> <input type="text" name="birthplace" placeholder="" class="input"></div>';
    // var tag = ' <actor:custom-actor></actor:custom-actor>';
    $(list).append(tag);
}

function deltag() {
    $(list).empty();
}

