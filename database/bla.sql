CREATE DATABASE IF NOT EXISTS registration;

use registration;

CREATE TABLE IF NOT EXISTS registrations (
  emailaddress VARCHAR(255) NOT NULL,
  PRIMARY KEY (emailaddress)
);