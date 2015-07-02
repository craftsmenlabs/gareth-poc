var express = require('express')
    , http = require('http')
    , winston = require('winston');

var expressApp = express();

var i = 0;

expressApp.use(express.static(__dirname + '/public'));
expressApp.get("/login", function (req, response) {
    var statusCodes = [200, 403];
    var statusCode = statusCodes[i];
    if (i >= statusCodes.length-1) {
        i = 0;
    } else {
        i++
    }
    winston.info(statusCode);
    response.status(statusCode).end();

});

var listeningPort = 8888;
http.createServer(expressApp).listen(listeningPort);

console.log('Server is listening on port ' + listeningPort);
