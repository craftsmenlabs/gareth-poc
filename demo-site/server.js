var express = require('express');
var http = require('http');
 
var expressApp = express();
expressApp.use(express.static(__dirname + '/public'));
 
var listeningPort = 8888;
http.createServer(expressApp).listen(listeningPort)
 
console.log('Server is listening on port ' + listeningPort);
