USE `station`;
CREATE USER 'station_user'@'127.0.0.1' IDENTIFIED BY '123321';
GRANT SELECT, INSERT, UPDATE, DELETE ON station.* TO 'station_user'@'127.0.0.1';