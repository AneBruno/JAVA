--
-- User java
--

create user 'admin'@'localhost' identified by 'himitsu';
GRANT SELECT, INSERT, UPDATE, DELETE ON `java`.* TO 'admin'@'localhost';