var mysql = require('mysql')

var databaseConnection = mysql.createConnection(getConnection());

function getConnection() {
    return {
        host: process.env.DATABASE_PORT_3306_TCP_ADDR || "localhost",
        user: process.env.DATABASE_ENV_MYSQL_USER || "mysql",
        password: process.env.DATABASE_ENV_MYSQL_PASSWORD || "mysql",
        database: process.env.DATABASE_ENV_MYSQL_DATABASE || "registration"
    }
}

function endConnection(databaseConnection) {
    if (databaseConnection) {
        databaseConnection.end(function (err) {
            if (err) {
                console.log(err);
            }
        });
    }
}

function doQuery(databaseConnection, query, callback) {
    if (databaseConnection) {
        databaseConnection.connect(function (err) {
            if (!err) {
                databaseConnection.query(query, callback);
            } else {
                console.log(err);
            }
        });
        endConnection(databaseConnection);
    }
}

module.exports = {

    alreadyRegistered: function (emailAddress, registeredCallback, notRegisteredCallback) {
        doQuery(databaseConnection, 'select * from registration where email_address = ?', [emailAddress], function (err, rows, fields) {
            if (rows.length === 0) {
                notRegisteredCallback
            } else {
                notRegisteredCallback
            }
        });
    },

    register: function (emailAddress) {
        doQuery(databaseConnection, "insert into registration values (emailAddress) values ('?')", [emailAddress], function (err) {
            if (!err) {

            }
        });
    }

}