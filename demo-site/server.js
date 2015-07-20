var express = require('express')
    , http = require('http')
    , winston = require('winston')
    , bodyParser = require('body-parser')
    , registrationDao = require('./registrationdao.js');
;

var systemExceptionCallback = function (response) {
    console.log("System error");
    response.statusCode = 500; // System exception
    response.send();
};


module.exports = {

    getRestApplication: function () {
        var expressApp = express();

        var i = 0;

        expressApp.use(bodyParser.urlencoded({extended: false}))

        expressApp.use(bodyParser.json())
        expressApp.use(express.static(__dirname + '/public'));

        expressApp.post("/signup", function (request, response) {
            var emailAddress = request.body.emailAddress;
            console.log(emailAddress)
            registrationDao.alreadyRegistered(emailAddress

                , function () {
                    console.log("Bad request");
                    response.statusCode = 400; // Bad request
                    response.send();
                }

                , function () {
                    console.log("OK request");
                    registrationDao.register(emailAddress
                        , function () {
                            response.statusCode = 202; // Accepted
                            response.send();
                        }
                        , function () {
                            systemExceptionCallback(response)
                        });

                }

                , function () {
                    systemExceptionCallback(response)
                }
            );

        });

        var listeningPort = 8888;
        http.createServer(expressApp).listen(listeningPort);
    }
}

