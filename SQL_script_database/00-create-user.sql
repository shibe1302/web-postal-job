-- Drop user first if they exist
DROP USER IF EXISTS 'jobportal'@'%';

-- Now create user with prop privileges
CREATE USER 'shibe1302'@'localhost' IDENTIFIED BY 'shibe1302';

GRANT ALL PRIVILEGES ON * . * TO 'shibe1302'@'localhost';