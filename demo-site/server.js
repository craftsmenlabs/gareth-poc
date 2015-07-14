var express = require('express')
    , http = require('http')
    , winston = require('winston')
    , bodyParser = require('body-parser');
;

var expressApp = express();

var i = 0;

// parse application/x-www-form-urlencoded
expressApp.use(bodyParser.urlencoded({extended: false}))

// parse application/json
expressApp.use(bodyParser.json())
expressApp.use(express.static(__dirname + '/public'));

expressApp.post("/signup", function (request, response) {
    var emailAddress = request.body.emailAddress;
    console.log(emailAddress)
    response.statusCode = 202; // Accepted
    response.send();
});

var listeningPort = 8888;
http.createServer(expressApp).listen(listeningPort);

console.log('Server is listening on port ' + listeningPort);
