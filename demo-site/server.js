var express = require('express')
    , http = require('http')
    , winston = require('winston')
    , bodyParser = require('body-parser')
    , registrationDao = require('./registrationdao.js');
;

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
                    response.statusCode = 202; // Accepted
                    response.send();
                }
                , function () {
                    response.statusCode = 400; // Bad request
                    response.send();
                });

        });

        var listeningPort = 8888;
        http.createServer(expressApp).listen(listeningPort);
    }
}

