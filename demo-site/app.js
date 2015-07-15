var restAPI = require('./server.js');

var databasePassword;

function getDatabasePassword(){
    if(process.argv.indexOf("-p") != -1){
        databasePassword = process.argv[process.argv.indexOf("-p") + 1];
    }
    if(!databasePassword){
        throw new Error("Database password should be given using -p")
    }
}

getDatabasePassword();

restAPI.getRestApplication();