/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validateRegistrationForm() {
    // get all input fields
    var usrNameInp = document.forms["registerForm"]["username"].value;
    var passwordInp = document.forms["registerForm"]["password"].value;
    var nameInp = document.forms["registerForm"]["real_name"].value;
    var emailInp = document.forms["registerForm"]["email"].value;
    // get all image fields
    var usrNameImg = document.getElementById('userNameValid');
    var passwordImg = document.getElementById('passValid');
    var nameImg = document.getElementById('nameValid');
    var emailImg = document.getElementById('emailValid');
    var ok = true;

    // check user name
    if (usrNameInp === null || usrNameInp === "") {
        usrNameImg.style.visibility = 'visible';
        ok = false;
    } else { usrNameImg.style.visibility = 'hidden'; }

    // check password
    if (passwordInp === null || passwordInp === "") {
        passwordImg.style.visibility = 'visible';
        ok = false;
    } else { passwordImg.style.visibility = 'hidden'; }

    // check name
    if (nameInp === null || nameInp === "") {
        nameImg.style.visibility = 'visible';
        ok = false;
    } else { nameImg.style.visibility = 'hidden'; }

    // check email
    if (emailInp === null || emailInp === "") {
        emailImg.style.visibility = 'visible';
        ok = false;
    } else { emailImg.style.visibility = 'hidden'; }

    if (ok === true)
        return true;
    return false;
}