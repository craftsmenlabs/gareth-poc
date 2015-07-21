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
        var okMessage = document.getElementById("ok-message"),
            nokMessage = document.getElementById("nok-message");

        if(!okMessage.classList.contains("hidden")) okMessage.classList.add("hidden");
        if(!nokMessage.classList.contains("hidden")) nokMessage.classList.add("hidden");

        marmottajax({
            url: "signup",
            method: "post",
            parameters: {
                emailAddress: email
            }
        }).then(function (result) {
            okMessage.classList.remove("hidden")
        }).error(function (message) {
            nokMessage.classList.remove("hidden")
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