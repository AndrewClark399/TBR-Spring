DROP TABLE `buyers`;
DROP TABLE `sellers`;
DROP TABLE `booking_for_sale`;
DROP TABLE `booking_for_let`;
DROP TABLE `properties_for_sale`;
DROP TABLE `properties_to_let`;
CREATE TABLE `buyers`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`address` VARCHAR(255),
	`first_name` VARCHAR(255),
	`last_name` VARCHAR(255),
	`phone_number` VARCHAR(255),
	`postcode` VARCHAR(255)
	);
CREATE TABLE `sellers`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`address` VARCHAR(255),
	`first_name` VARCHAR(255),
	`last_name` VARCHAR(255),
	`phone_number` VARCHAR(255),
	`postcode` VARCHAR(255)
	);
CREATE TABLE `properties_for_sale`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`bathrooms` INT,
	`bedrooms` INT,
	`price` DOUBLE,
	`address` VARCHAR(255),
	`garden` VARCHAR(255),
	`postcode` VARCHAR(255),
	`property_status` VARCHAR(255),
	`type` VARCHAR(255)
	);
	CREATE TABLE `booking_for_sale`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`date` DATE,
	`email` VARCHAR(255),
	`name` VARCHAR(255),
	`phone_number` VARCHAR(255),
	`time_slot` VARCHAR(255),
	`properties_for_sale_id` INT,
	FOREIGN KEY (`properties_for_sale_id`) REFERENCES `properties_for_sale` (`id`)
	);
CREATE TABLE `properties_to_let`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`bathrooms` INT,
	`bedrooms` INT,
	`rent` DOUBLE,
	`address` VARCHAR(255),
	`garden` VARCHAR(255),
	`postcode` VARCHAR(255),
	`property_status` VARCHAR(255),
	`type` VARCHAR(255)
	);
CREATE TABLE `booking_for_let`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`date` DATE,
	`email` VARCHAR(255),
	`name` VARCHAR(255),
	`phone_number` VARCHAR(255),
	`time_slot` VARCHAR(255),
	`properties_to_let_id` INT,
	FOREIGN KEY (`properties_to_let_id`) REFERENCES `properties_to_let` (`id`)
	);