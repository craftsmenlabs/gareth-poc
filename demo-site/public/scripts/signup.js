var application = {};

application.signup = function () {

    var bindSignUpForm = function () {
        var form = document.getElementById("signup-form");
        form.addEventListener("submit", function (e) {
            submitSignUp(e)
        });
    };

    var submitSignUp = function (e) {
        e.preventDefault()
        var email = document.getElementById("email").value;
        if (email) {
            sendSignUp(email)
        }
        return false;
    };

    var sendSignUp = function (email) {
        marmottajax({
            url: "signup",
            method: "post",
            parameters: {
                emailAddress: email
            }
        }).then(function (result) {
            alert("OK");
        }).error(function (message) {
            console.log(message);
        });
    };

    function init() {
        bindSignUpForm();
    };

    return {
        init: init
    };

}();

application.signup.init()