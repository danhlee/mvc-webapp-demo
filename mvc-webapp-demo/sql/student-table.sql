CREATE DATABASE IF NOT EXISTS `web_student_tracker`;
USE `web_student_tracker`;

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
	id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    first_name varchar(45) DEFAULT NULL,
    last_name varchar(45) DEFAULT NULL,
    email varchar(45) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1;

INSERT INTO student(first_name, last_name, email) VALUES 
	('Jimi', 'Hendrix', 'purpleHaze@gmail.com'),
    ('Eric', 'Clapton', 'layla@hotmail.com'),
	('Carlos', 'Santana', 'europa@gmail.com'),
	('Stevie', 'Vaughan', 'prideAndJoy@hotmail.com');