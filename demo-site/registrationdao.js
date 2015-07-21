var mysql = require('mysql')

var databasePool = mysql.createPool(getConnection());

console.log(process.env);

function getConnection() {
    return {
        host: process.env.DB_PORT_3306_TCP_ADDR || "localhost",
        user: process.env.DB_ENV_MYSQL_USER || "mysql",
        password: process.env.DB_ENV_MYSQL_PASSWORD || "mysql",
        database: process.env.DB_ENV_MYSQL_DATABASE || "registration"
    }
}

function doQuery(databasePool, query, queryValues, callback) {
    if (databasePool) {
        databasePool.getConnection(function (err, connection) {
            console.log("Doing query in connection");
            if (!err) {
                connection.query(query, queryValues, callback);
            } else {
                console.log(err);
            }
            if(connection) connection.release();
        });

    }
}

module.exports = {

    alreadyRegistered: function (emailAddress, registeredCallback, notRegisteredCallback, systemExceptionCallback) {
        doQuery(databasePool, 'select * from registrations where emailaddress = ?', [emailAddress], function (err, rows, fields) {
            if (err) {
                console.log("Error while checking if registristration already exist", err);
                systemExceptionCallback();
            } else {
                if (rows.length === 0) {
                    notRegisteredCallback();
                } else {
                    registeredCallback();
                }
            }
        });
    },

    register: function (emailAddress, okCallback, systemExceptionCallback) {
        doQuery(databasePool, "insert into registrations set ?", {"emailaddress": emailAddress}, function (err) {
            if (err) {
                console.log("Error while inserting registration", err);
                systemExceptionCallback();
            } else {
                okCallback();
            }
        });
    },


    getRegistrationCount: function (okCallback, systemExceptionCallback) {
        doQuery(databasePool, "select count(*) as count from registrations", function (err, rows, fields) {
            if (err) {
                console.log("Count could not be determined.")
                systemExceptionCallback();
            } else {
                okCallback(rows[0]);
            }
        });
    }


}