-- Reset all data in the bibliotheque database (truncate all tables and reset auto-increment)
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE return_book;
ALTER TABLE return_book AUTO_INCREMENT = 1;
TRUNCATE TABLE extension;
ALTER TABLE extension AUTO_INCREMENT = 1;
TRUNCATE TABLE extension_request;
ALTER TABLE extension_request AUTO_INCREMENT = 1;
TRUNCATE TABLE sanction;
ALTER TABLE sanction AUTO_INCREMENT = 1;
TRUNCATE TABLE loan;
ALTER TABLE loan AUTO_INCREMENT = 1;
TRUNCATE TABLE reservation;
ALTER TABLE reservation AUTO_INCREMENT = 1;
TRUNCATE TABLE subscription;
ALTER TABLE subscription AUTO_INCREMENT = 1;
SET FOREIGN_KEY_CHECKS = 1;
