CREATE DATABASE IF NOT EXISTS capco_travel_portal;
use capco_travel_portal;


CREATE TABLE `capco_travel_portal`.`project_details` (
  `uid` INT NOT NULL auto_increment unique,
  `project_code` VARCHAR(20) NOT NULL,
  `project_name` VARCHAR(45) NOT NULL,
  `description` TEXT(255) NULL,
  `activity_code` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`project_code`),
  INDEX(project_code)
  )auto_increment=0;
  
  CREATE TABLE `capco_travel_portal`.`user` (
  `uid` INT NOT NULL auto_increment UNIQUE,
  `capco_user_id` varchar(50) NOT NULL unique,
  `password` varchar(45) NOT NULL,
  `employee_Id` INT(11) NOT NULL,
  `approver_Id` INT(20) NOT NULL,
  `grade` TEXT(2) Not NULL,
  `employee_name` varchar(100) Not NULL,
  `contact_Number` varchar(15) Not NULL,
  PRIMARY KEY (`capco_user_id`),
  INDEX(capco_user_id)
  )auto_increment=0; 
  
CREATE TABLE `capco_travel_portal`.`employee_details` (
  `uid` INT NOT NULL auto_increment UNIQUE,
  `name` VARCHAR(100) NULL,
  `email_id` VARCHAR(100) NULL,
  `employee_id` INT(11) NOT NULL unique,
  `capco_user_id` varchar(50) NOT NULL unique,
  `grade` varchar(5) NOT NULL,
  `mobile_number` varchar(15) NULL,
  `is_approver` TINYINT NULL,
  `is_travelView` TINYINT NULL,
  `created_on` TIMESTAMP(0) NULL,
  `modified_on` TIMESTAMP(0) NULL,
  `employee_location` varchar(45) NOT NULL,
  `project_code` varchar(15) NOT NULL,
  `token_id` int NOT NULL,
  `activity_code` varchar(20) DEFAULT NULL,
  `type_of_project` varchar(20) DEFAULT NULL,
  `purpose_of_travel` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
CONSTRAINT `fk_project_code_id`
  FOREIGN KEY (`project_code`)
    REFERENCES `capco_travel_portal`.`project_details` (`project_code`)    
  )auto_increment=0;
  
  
  CREATE TABLE `capco_travel_portal`.`main_request` (
  `uid` INT NOT NULL auto_increment unique,
  `request_id` BIGINT NOT NULL unique,
  `requested_by` int(11) NULL,
  `request_type` varchar(45) NULL,
  `current_status` VARCHAR(45) NULL,
  `created_on` TIMESTAMP(0) NULL,
  `modified_on` TIMESTAMP(0) NULL,
  `approver_id` INT(11) NOT NULL,
  `billable` TINYINT NULL,
  `requested_for` varchar(100) NULL,
  `project_code` varchar(25) NULL,
  `project_name` varchar(45) NULL,
  `remarks` TEXT(255) NULL,
  PRIMARY KEY (`request_id`),
  CONSTRAINT `fk_approver_id`
  FOREIGN KEY (`approver_id`) REFERENCES `capco_travel_portal`.`employee_details` (`employee_id`),
  FOREIGN KEY (`requested_by`) REFERENCES `capco_travel_portal`.`employee_details` (`employee_id`)
    )auto_increment=0;
  

CREATE TABLE `capco_travel_portal`.`accomodation_details` (
  `uid` INT NOT NULL AUTO_INCREMENT,
  `request_id` BIGINT NOT NULL unique,
  `check_in` TIMESTAMP(0) NOT NULL,
  `check_out` TIMESTAMP(0) NOT NULL,
  `country` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `business_purpose` VARCHAR(45) NULL,
  `currency` VARCHAR(45) NULL,
  `budget` VARCHAR(45) NULL,
  `category` VARCHAR(20) NOT NULL,
  `remarks` TEXT(255) NULL,
  `created_on` TIMESTAMP(0) NULL,
  `modified_on` TIMESTAMP(0) NULL,
  `is_active` TINYINT NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `stay_location` varchar(20) DEFAULT NULL,
  UNIQUE INDEX `uaccomodation_detailsid_UNIQUE` (`uid` ASC),
  PRIMARY KEY (`uid`),
  INDEX `fk_request_id_idx` (`request_id` ASC),
  CONSTRAINT `fk_request_id`
    FOREIGN KEY (`request_id`)
    REFERENCES `capco_travel_portal`.`main_request` (`request_id`)
  )auto_increment=0;


CREATE TABLE `capco_travel_portal`.`cab_details` (
  `uid` INT NOT NULL auto_increment,
  `request_id` BIGINT NOT NULL unique,
  `taxi_travel_type` VARCHAR(45) NOT NULL,
  `pickup_city` VARCHAR(45) NOT NULL,
  `country` VARCHAR(45) NULL,
  `business_purpose` VARCHAR(45) NULL,
  `pickup_location` VARCHAR(255) NOT NULL,
  `drop_location` VARCHAR(255) NOT NULL,
  `drop_city` VARCHAR(255) NULL,
  `from_date` timestamp(0) NOT NULL,
  `to_date` DATE NULL,
  `car_type` VARCHAR(45) NOT NULL,
  `remarks` TEXT(255) NULL,
  `created_on` TIMESTAMP(0) NULL,
  `modified_on` TIMESTAMP(0) NULL,
  `is_active` TINYINT NOT NULL,
  PRIMARY KEY (`uid`),
  CONSTRAINT `fk_cab_request_id`
    FOREIGN KEY (`request_id`)
    REFERENCES `capco_travel_portal`.`main_request` (`request_id`)
  )auto_increment=0;  
  
  
  CREATE TABLE `capco_travel_portal`.`flight_details` (
  `uid` INT NOT NULL auto_increment,
  `request_id` BIGINT NOT NULL unique,
  `given_name` varchar(100) NULL,
  `sur_name` varchar(100) NULL,
  `date_of_birth` DATE NULL,
  `tour_type` varchar(20) NOT NULL,  
  `travel_type` varchar(20) NOT NULL,
  `business_purpose` VARCHAR(45) NULL,
  `pref_class` VARCHAR(45) NULL,
  `meal_pref` VARCHAR(45) NULL,
  `passport_no` VARCHAR(45) null,
  `passport_issue_date` date null,
  `passport_expiry_date` date null,
  `nationality` VARCHAR(45) null,
  `issuing_authority` VARCHAR(45) null,
  `passport_issue_place` VARCHAR(45) null,
  `is_valid_visa` TINYINT NULL DEFAULT NULL,
  `return_date` DATE null,
  `return_time` varchar(45) NULL,
  `remarks` TEXT(255) NULL,
   `origin_country` VARCHAR(100) NULL,
   `destination_country` VARCHAR(100) NULL,
  `departure_location` VARCHAR(100) NULL,
  `departure_date` DATE NULL,
  `departure_time` varchar(45) NULL,
  `destination_location` VARCHAR(100) NULL,
  `created_on` TIMESTAMP(0)  NULL,
  `modified_on` TIMESTAMP(0)  NULL,
  `is_active` TINYINT NOT NULL,
  `isos` varchar(20) NOT NULL,
  PRIMARY KEY (`uid`),
  CONSTRAINT `fk_flight_request_id`
    FOREIGN KEY (`request_id`)
    REFERENCES `capco_travel_portal`.`main_request` (`request_id`)
  )auto_increment=0;
  
	 CREATE TABLE `capco_travel_portal`.`flight_journey_details` (
  `uid` INT NOT NULL auto_increment,
  `flight_details_table_uid` INT NOT NULL,
  `departure_location` VARCHAR(45) NOT NULL,
  `departure_date_time` TIMESTAMP(0) NOT NULL,
  `destination_location` VARCHAR(45) NOT NULL,
  `created_on` TIMESTAMP(0) NULL,
  `modified_on` TIMESTAMP(0) NULL,
  `is_active` TINYINT NOT NULL,
  `isos` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  CONSTRAINT `fk_flight_details_uid`
    FOREIGN KEY (`flight_details_table_uid`)
    REFERENCES `capco_travel_portal`.`flight_details` (`uid`)
    )auto_increment=0;
	
	CREATE TABLE `capco_travel_portal`.`forex_details` (
  `uid` INT NOT NULL auto_increment,
  `request_id` BIGINT NOT NULL unique,
  `to_date` date NOT NULL,
  `from_date` date NOT NULL,
  `currency` VARCHAR(15) NOT NULL,
  `country` VARCHAR(45) NOT NULL,
  `no_of_days` INT NOT NULL default 1,
  `amount` DOUBLE NOT NULL,
  `remarks` TEXT(255) NULL,
  `collection_center` VARCHAR(45) NOT NULL,
  `bank_desk` VARCHAR(45) NOT NULL,
  `created_on` TIMESTAMP(0) NULL,
  `modified_on` TIMESTAMP(0) NULL,
  `is_active` TINYINT NOT NULL,
  `previously_travelled` tinyint(1) DEFAULT NULL,
  `card_number` int(11) DEFAULT NULL,
  `card_holder_name` varchar(45) DEFAULT NULL,
  `bank` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  PRIMARY KEY (`uid`),
  INDEX `fk_forex_request_id_idx` (`request_id` ASC),
  CONSTRAINT `fk_forex_request_id`
    FOREIGN KEY (`request_id`)
    REFERENCES `capco_travel_portal`.`main_request` (`request_id`)
    )auto_increment=0;
	
	CREATE TABLE `capco_travel_portal`.`perdiem_details` (
  `uid` INT NOT NULL auto_increment,
  `country` VARCHAR(45) NOT NULL,
  `perdiem` DOUBLE NOT NULL,
  `currency` varchar(10) NULL,
  PRIMARY KEY (`uid`)
  )auto_increment=0;
  
  
  CREATE TABLE `capco_travel_portal`.`request_history` (
  `uid` INT NOT NULL auto_increment,
  `request_id` BIGINT NOT NULL,
  `status` varchar(45) NOT NULL,
  `remarks` TEXT(255) NULL,
  `updated_by` int NOT NULL default 0,
  `created_on` TIMESTAMP(0) NULL,
  `modified_on` TIMESTAMP(0) NULL,
  PRIMARY KEY (`uid`),
  INDEX `fk_request_history_id_idx` (`request_id` ASC),
  CONSTRAINT `fk_request_history_id`
    FOREIGN KEY (`request_id`)
    REFERENCES `capco_travel_portal`.`main_request` (`request_id`)
  )auto_increment=0;
  
  CREATE TABLE `capco_travel_portal`.`approver_group` (
  `uid` INT NOT NULL AUTO_INCREMENT,
  `approver_id` INT(11) NOT NULL unique,
  `approver_level` int ,
  `email_address` varchar(45) DEFAULT NULL,
  UNIQUE INDEX `uid_UNIQUE` (`uid` ASC),
  PRIMARY KEY (`uid`),
  FOREIGN KEY (`approver_id`)
	REFERENCES `capco_travel_portal`.`employee_details` (`employee_id`)
  )auto_increment=0;
  
CREATE TABLE `capco_travel_portal`.`visa_request` (
  `uid` INT NOT NULL AUTO_INCREMENT,
  `request_id` BIGINT(20) NOT NULL,
  `travel_destination` VARCHAR(45) NOT NULL,
  `visa_type` VARCHAR(45) NOT NULL,
  `type_of_visit` VARCHAR(45) NOT NULL,
  `business_purpose` VARCHAR(45) NULL,
  `collection_desk` VARCHAR(45) NULL,
  `remarks` TEXT(255) NULL,
  `created_on` TIMESTAMP(0) NULL,
  `modified_on` TIMESTAMP(0) NULL,
  `is_active` TINYINT NOT NULL,
  `from_date` date DEFAULT NULL,
  `to_date` date DEFAULT NULL,
  `travelling_days` int(11) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  INDEX `fk_visa_request_id_idx` (`request_id` ASC),
  CONSTRAINT `fk_visa_request_id`
    FOREIGN KEY (`request_id`)
    REFERENCES `capco_travel_portal`.`main_request` (`request_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)auto_increment=0;

CREATE TABLE `hc_action_table` (
  `request_id` bigint(20) DEFAULT NULL,
  `action_id` varchar(45) DEFAULT NULL,
  `action_status` varchar(45) DEFAULT NULL,
  `pending_items` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  KEY `FK_HC_Action_idx` (`request_id`),
  CONSTRAINT `FK_HC_Action` FOREIGN KEY (`request_id`) REFERENCES `main_request` (`request_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) auto_increment=0;

CREATE TABLE `action_details` (
  `Request_id` bigint(20) DEFAULT NULL,
  `Action_id` varchar(40) DEFAULT NULL,
  `Pending_actions` varchar(100) DEFAULT NULL,
  `action_status` varchar(30) DEFAULT NULL,
  `pending_status` varchar(30) DEFAULT NULL,
  `request_status` varchar(30) DEFAULT NULL
) auto_increment=0;

CREATE TABLE `fin_action_table` (
  `request_id` bigint(20) DEFAULT NULL,
  `action_id` varchar(45) DEFAULT NULL,
  `action_status` varchar(45) DEFAULT NULL,
  `pending_items` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  KEY `FK_FIN_Action_idx` (`request_id`),
  CONSTRAINT `FK_FIN_Action` FOREIGN KEY (`request_id`) REFERENCES `main_request` (`request_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) auto_increment=0;

INSERT INTO `capco_travel_portal`.`perdiem_details` (`uid`,`country`, `currency`, `perdiem`) VALUES (1,'India', 'INR', '10000');
INSERT INTO `capco_travel_portal`.`perdiem_details` (`uid`,`country`, `currency`, `perdiem`) VALUES (2,'USA', 'USD', '500');
INSERT INTO `capco_travel_portal`.`perdiem_details` (`uid`,`country`, `currency`, `perdiem`) VALUES (3,'Euro', 'EUR', '300');

INSERT INTO `capco_travel_portal`.`project_details` (`project_code`, `project_name`, `description`) VALUES ('8888', 'capco_internal', 'demo project');
INSERT INTO `capco_travel_portal`.`project_details` (`project_code`, `project_name`, `description`) VALUES ('9999', 'capco_Business', 'demo Business');
INSERT INTO `capco_travel_portal`.`employee_details` (`name`, `email_id`, `employee_id`,`capco_user_id`,`grade`, `mobile_number`, `is_approver`, `created_on`, `modified_on`, `employee_location`, `project_code`,`token_id`) VALUES ('shubham', 'shubham.kale@capco.com', '16294','SKAL','M0', '8446214276', '0', '2017-07-07', '2017-07-07', 'pune', '8888',1234);
INSERT INTO `capco_travel_portal`.`employee_details` (`name`, `email_id`, `employee_id`,`capco_user_id`,`grade`, `mobile_number`, `is_approver`, `created_on`, `modified_on`, `employee_location`, `project_code`,`token_id`) VALUES ('Pranjal', 'shubham.kale@capco.com', '16290','PNRT','M0', '1234567890', '0', '2017-07-07', '2017-07-07', 'pune', '8888',5678);
INSERT INTO `capco_travel_portal`.`employee_details` (`name`, `email_id`, `employee_id`,`capco_user_id`,`grade`, `mobile_number`, `is_approver`, `created_on`, `modified_on`, `employee_location`, `project_code`,`token_id`) VALUES ('Rajnish', 'Rajnish@capco.com', '16292','RDUB','M7', '1234567890', '1', '2017-07-07', '2017-07-07', 'Banglore', '9999',9876);
INSERT INTO `capco_travel_portal`.`employee_details` (`name`, `email_id`, `employee_id`,`capco_user_id`,`grade`, `mobile_number`, `is_approver`, `created_on`, `modified_on`, `employee_location`, `project_code`,`token_id`) VALUES ('Sonali', 'Sonali@capco.com', '16293','STHE','M0', '1234', '0', '2017-07-07', '2017-07-07', 'pune', '8888',98765);
INSERT INTO `capco_travel_portal`.`employee_details` (`name`, `email_id`, `employee_id`, `capco_user_id`,`grade`, `mobile_number`, `is_approver`, `created_on`, `employee_location`, `project_code`, `token_id`) VALUES ('Subramanya Paramashivaiah', 'Subramanya.Paramashivaiah@capco.com', '16300', 'SUBH', 'M6', '9876543210', '1', '2017-07-07 00:00:00', 'Banglore', '9999', '12345');
INSERT INTO `capco_travel_portal`.`employee_details` (`uid`, `name`, `email_id`, `employee_id`, `capco_user_id`, `grade`, `mobile_number`, `is_approver`, `created_on`, `employee_location`, `project_code`, `token_id`) VALUES ('6', 'Alpesh Anil Parekh', 'Alpesh Anil Parekh@capco.com', '16301', 'AAPH', 'M6', '9876543210', '1', '2017-07-07 00:00:00', 'Banglore', '9999', '12345');
INSERT INTO `capco_travel_portal`.`employee_details` (`uid`, `name`, `email_id`, `employee_id`, `capco_user_id`, `grade`, `mobile_number`, `is_approver`, `created_on`, `employee_location`, `project_code`, `token_id`) VALUES ('7', 'Dharani P M', 'DharaniPM@capco.com', '16302', 'DHRN', 'M6', '9876543210', '1', '2017-07-07 00:00:00', 'Banglore', '9999', '12345');
INSERT INTO `capco_travel_portal`.`employee_details` (`uid`, `name`, `email_id`, `employee_id`, `capco_user_id`, `grade`, `mobile_number`, `is_approver`, `created_on`, `employee_location`, `project_code`, `token_id`) VALUES ('8', 'Santhosh Kumar Pallikonda', 'SanthoshKumar.Pallikonda@capco.com', '16303', 'STHO', 'M6', '9876543210', '1', '2017-07-07 00:00:00', 'Banglore', '9999', '12345');
INSERT INTO `capco_travel_portal`.`employee_details` (`uid`, `name`, `email_id`, `employee_id`, `capco_user_id`, `grade`, `mobile_number`, `is_approver`, `created_on`, `employee_location`, `project_code`, `token_id`) VALUES ('9', 'Nidhi Tulsi', 'Nidhi.Tulsi@capco.com', '16304', 'NIDI', 'M5', '9876543210', '1', '2017-07-07 00:00:00', 'Banglore', '9999', '12345');
INSERT INTO `capco_travel_portal`.`employee_details` (`uid`, `name`, `email_id`, `employee_id`, `capco_user_id`, `grade`, `mobile_number`, `is_approver`, `created_on`, `employee_location`, `project_code`, `token_id`) VALUES ('10', 'Sashwati Bhattacharya', 'Sashwati.Bhattacharyai@capco.com', '16305', 'SSBH', 'M6', '9876543210', '1', '2017-07-07 00:00:00', 'Banglore', '9999', '12345');
INSERT INTO `capco_travel_portal`.`employee_details` (`uid`, `name`, `email_id`, `employee_id`, `capco_user_id`, `grade`, `mobile_number`, `is_approver`, `created_on`, `employee_location`, `project_code`, `token_id`) VALUES ('11', 'Girish Utagi', 'Girish.Utagi@capco.com', '16306', 'GIUT', 'M6', '9876543210', '1', '2017-07-07 00:00:00', 'Banglore', '9999', '12345');
INSERT INTO `capco_travel_portal`.`employee_details` (`uid`, `name`, `email_id`, `employee_id`, `capco_user_id`, `grade`, `mobile_number`, `is_approver`, `created_on`, `employee_location`, `project_code`, `token_id`) VALUES ('12', 'Sarmistha Bhattacharjee', 'Sarmistha.Bhattacharjee@capco.com', '16307', 'SABH', 'M5', '9876543210', '1', '2017-07-07 00:00:00', 'Banglore', '9999', '12345');
INSERT INTO `capco_travel_portal`.`employee_details` (`uid`, `name`, `email_id`, `employee_id`, `capco_user_id`, `grade`, `mobile_number`, `is_approver`, `created_on`, `employee_location`, `project_code`, `token_id`) VALUES ('13', 'Anil Kumar Kozhappilly', 'Anil Kumar.Kozhappilly@capco.com', '16308', 'AKKP', 'M5', '9876543210', '1', '2017-07-07 00:00:00', 'Banglore', '9999', '12345');
INSERT INTO `capco_travel_portal`.`employee_details` (`uid`, `name`, `email_id`, `employee_id`, `capco_user_id`, `grade`, `mobile_number`, `is_approver`, `created_on`, `employee_location`, `project_code`, `token_id`) VALUES ('14', 'Kittu Achan', 'Kittu.Achan@capco.com', '16309', 'KITA', 'M5', '9876543210', '1', '2017-07-07 00:00:00', 'Banglore', '9999', '12345');
INSERT INTO `capco_travel_portal`.`employee_details` (`uid`, `name`, `email_id`, `employee_id`, `capco_user_id`, `grade`, `mobile_number`, `is_approver`, `created_on`, `employee_location`, `project_code`, `token_id`) VALUES ('15', 'Shivaji Chakraborty', 'Shivaji.Chakraborty@capco.com', '16310', 'SICH', 'M6', '9876543210', '1', '2017-07-07 00:00:00', 'Banglore', '9999', '12345');
INSERT INTO `capco_travel_portal`.`employee_details` (`uid`, `name`, `email_id`, `employee_id`, `capco_user_id`, `grade`, `mobile_number`, `is_approver`, `created_on`, `employee_location`, `project_code`, `token_id`) VALUES ('16', 'Thomas Kurian Kovoor', 'ThomasKurian.Kovoor@capco.com', '16311', 'TKKK', 'M5', '9876543210', '1', '2017-07-07 00:00:00', 'Banglore', '9999', '12345');
INSERT INTO `capco_travel_portal`.`employee_details` (`uid`, `name`, `email_id`, `employee_id`, `capco_user_id`, `grade`, `mobile_number`, `is_approver`, `created_on`, `employee_location`, `project_code`, `token_id`) VALUES ('17', 'Hiriyanna Keshava Murthy Kowshika', 'HiriyannaKeshavaMurthy Kowshika@capco.com', '16312', 'HIRM', 'M7', '9876543210', '1', '2017-07-07 00:00:00', 'Banglore', '9999', '12345');
INSERT INTO `capco_travel_portal`.`employee_details` (`uid`, `name`, `email_id`, `employee_id`, `capco_user_id`, `grade`, `mobile_number`, `is_approver`, `created_on`, `employee_location`, `project_code`, `token_id`) VALUES ('18', 'Dalbir Singh Dahiya', 'DalbirSinghDahiya@capco.com', '16313', 'DABS', 'M6', '9876543210', '1', '2017-07-07 00:00:00', 'Banglore', '9999', '12345');

#INSERT INTO `capco_travel_portal`.`main_request` (`uid`, `request_id`, `requested_by`, `request_type`, `current_status`, `created_on`, `modified_on`, `approver_id`, `billable`, `requested_for`, `project_code`, `project_name`, `remarks`) VALUES ('1', '123', '16294', 'Self', 'Pending', '2017-07-07 00:00:00', '2017-07-07 00:00:00', '16300', '1', 'Shubham', '8888', 'Capco_internal', 'New1');
#INSERT INTO `capco_travel_portal`.`main_request` (`uid`, `request_id`, `requested_by`, `request_type`, `current_status`, `created_on`, `modified_on`, `approver_id`, `billable`, `requested_for`, `project_code`, `project_name`, `remarks`) VALUES ('2', '456', '16290', 'Other', 'Pending', '2017-07-07 00:00:00', '2017-07-07 00:00:00', '16302', '1', 'Client1', '9999', 'capco_business', 'New2');
#INSERT INTO `capco_travel_portal`.`main_request` (`uid`, `request_id`, `requested_by`, `request_type`, `current_status`, `created_on`, `modified_on`, `approver_id`, `billable`, `requested_for`, `project_code`, `project_name`, `remarks`) VALUES ('3', '789', '16293', 'Self', 'Pending', '2017-07-07 00:00:00', '2017-07-07 00:00:00', '16300', '1', 'Sonali', '8888', 'Capco_internal', 'New3');
#INSERT INTO `capco_travel_portal`.`main_request` (`uid`, `request_id`, `requested_by`, `request_type`, `current_status`, `created_on`, `modified_on`, `approver_id`, `billable`, `requested_for`, `project_code`, `project_name`, `remarks`) VALUES ('4', '987', '16294', 'Self', 'Pending', '2017-07-07 00:00:00', '2017-07-07 00:00:00', '16300', '1', 'Shubham', '8888', 'Capco_internal', 'New4');

#INSERT INTO `capco_travel_portal`.`request_history` (`request_id`, `status`, `remarks`) VALUES ('123', 'pending', 'test');
INSERT INTO `capco_travel_portal`.`approver_group` (`approver_id`, `approver_level`) VALUES ('16292', 2);INSERT INTO `capco_travel_portal`.`approver_group` (`approver_id`, `approver_level`) VALUES ('16300', '1');
INSERT INTO `capco_travel_portal`.`approver_group` (`approver_id`, `approver_level`) VALUES ('16301', '1');
INSERT INTO `capco_travel_portal`.`approver_group` (`approver_id`, `approver_level`) VALUES ('16302', '1');
INSERT INTO `capco_travel_portal`.`approver_group` (`approver_id`, `approver_level`) VALUES ('16303', '1');
INSERT INTO `capco_travel_portal`.`approver_group` (`approver_id`, `approver_level`) VALUES ('16304', '1');
INSERT INTO `capco_travel_portal`.`approver_group` (`approver_id`, `approver_level`) VALUES ('16305', '1');
INSERT INTO `capco_travel_portal`.`approver_group` (`approver_id`, `approver_level`) VALUES ('16306', '1');
INSERT INTO `capco_travel_portal`.`approver_group` (`approver_id`, `approver_level`) VALUES ('16307', '1');
INSERT INTO `capco_travel_portal`.`approver_group` (`approver_id`, `approver_level`) VALUES ('16308', '1');
INSERT INTO `capco_travel_portal`.`approver_group` (`approver_id`, `approver_level`) VALUES ('16309', '1');
INSERT INTO `capco_travel_portal`.`approver_group` (`approver_id`, `approver_level`) VALUES ('16310', '1');
INSERT INTO `capco_travel_portal`.`approver_group` (`approver_id`, `approver_level`) VALUES ('16311', '1');
INSERT INTO `capco_travel_portal`.`approver_group` (`approver_id`, `approver_level`) VALUES ('16312', '2');
INSERT INTO `capco_travel_portal`.`approver_group` (`approver_id`, `approver_level`) VALUES ('16313', '1');
INSERT INTO `capco_travel_portal`.`user` (`uid`, `capco_user_id`, `password`, `employee_Id`, `approver_Id`, `grade`, `employee_name`, `contact_Number`) VALUES ('1', 'SKAL', 'user', '16294', '16292', 'M0', 'Shubham Kale', '8446214276');
INSERT INTO `capco_travel_portal`.`user` (`uid`, `capco_user_id`, `password`, `employee_Id`, `approver_Id`, `grade`, `employee_name`, `contact_Number`) VALUES ('2', 'PNRT', 'user', '16290', '16294', 'M3', 'Pranjal Nartam', '12345');
INSERT INTO `capco_travel_portal`.`user` (`uid`, `capco_user_id`, `password`, `employee_Id`, `approver_Id`, `grade`, `employee_name`, `contact_Number`) VALUES ('3', 'STHE', 'user', '16299', '16292', 'M4', 'Sonali Thote', '56789');
INSERT INTO `capco_travel_portal`.`user` (`uid`, `capco_user_id`, `password`, `employee_Id`, `approver_Id`, `grade`, `employee_name`, `contact_Number`) VALUES ('4', 'RDUB', 'admin', '16292', '16292', 'M7', 'Rajnish Dubey', '1234567');
INSERT INTO `capco_travel_portal`.`user` (`uid`, `capco_user_id`, `password`, `employee_Id`, `approver_Id`, `grade`, `employee_name`, `contact_Number`) VALUES ('18', 'SUBH', 'user', '16300', '16292', 'M4', 'Subramanya Paramashivaiah', '56789');
INSERT INTO `capco_travel_portal`.`user` (`uid`, `capco_user_id`, `password`, `employee_Id`, `approver_Id`, `grade`, `employee_name`, `contact_Number`) VALUES ('5', 'AAPH', 'user', '16301', '16292', 'M4', 'Alpesh Anil Parekh', '56789');
INSERT INTO `capco_travel_portal`.`user` (`uid`, `capco_user_id`, `password`, `employee_Id`, `approver_Id`, `grade`, `employee_name`, `contact_Number`) VALUES ('6', 'DHRN', 'user', '16302', '16292', 'M4', 'Dharani P M', '56789');
INSERT INTO `capco_travel_portal`.`user` (`uid`, `capco_user_id`, `password`, `employee_Id`, `approver_Id`, `grade`, `employee_name`, `contact_Number`) VALUES ('7', 'STHO', 'user', '16303', '16292', 'M4', 'Santhosh Kumar Pallikonda', '56789');
INSERT INTO `capco_travel_portal`.`user` (`uid`, `capco_user_id`, `password`, `employee_Id`, `approver_Id`, `grade`, `employee_name`, `contact_Number`) VALUES ('8', 'NIDI', 'user', '16304', '16292', 'M4', 'Nidhi Tulsi', '56789');
INSERT INTO `capco_travel_portal`.`user` (`uid`, `capco_user_id`, `password`, `employee_Id`, `approver_Id`, `grade`, `employee_name`, `contact_Number`) VALUES ('9', 'SSBH', 'user', '16305', '16292', 'M4', 'Sashwati Bhattacharya', '56789');
INSERT INTO `capco_travel_portal`.`user` (`uid`, `capco_user_id`, `password`, `employee_Id`, `approver_Id`, `grade`, `employee_name`, `contact_Number`) VALUES ('10', 'GIUT', 'user', '16306', '16292', 'M4', 'Girish Utagi', '56789');
INSERT INTO `capco_travel_portal`.`user` (`uid`, `capco_user_id`, `password`, `employee_Id`, `approver_Id`, `grade`, `employee_name`, `contact_Number`) VALUES ('11', 'SABH', 'user', '16307', '16292', 'M4', 'Sarmistha Bhattacharjee', '56789');
INSERT INTO `capco_travel_portal`.`user` (`uid`, `capco_user_id`, `password`, `employee_Id`, `approver_Id`, `grade`, `employee_name`, `contact_Number`) VALUES ('12', 'AKKP', 'user', '16308', '16292', 'M4', 'Anil Kumar Kozhappilly', '56789');
INSERT INTO `capco_travel_portal`.`user` (`uid`, `capco_user_id`, `password`, `employee_Id`, `approver_Id`, `grade`, `employee_name`, `contact_Number`) VALUES ('13', 'KITA', 'user', '16309', '16292', 'M4', 'Kittu Achan', '56789');
INSERT INTO `capco_travel_portal`.`user` (`uid`, `capco_user_id`, `password`, `employee_Id`, `approver_Id`, `grade`, `employee_name`, `contact_Number`) VALUES ('14', 'SICH', 'user', '16310', '16292', 'M4', 'Shivaji Chakraborty', '56789');
INSERT INTO `capco_travel_portal`.`user` (`uid`, `capco_user_id`, `password`, `employee_Id`, `approver_Id`, `grade`, `employee_name`, `contact_Number`) VALUES ('15', 'TKKK', 'user', '16311', '16292', 'M4', 'Thomas Kurian Kovoor', '56789');
INSERT INTO `capco_travel_portal`.`user` (`uid`, `capco_user_id`, `password`, `employee_Id`, `approver_Id`, `grade`, `employee_name`, `contact_Number`) VALUES ('16', 'HIRM', 'user', '16312', '16292', 'M4', 'Hiriyanna Keshava Murthy Kowshika', '56789');
INSERT INTO `capco_travel_portal`.`user` (`uid`, `capco_user_id`, `password`, `employee_Id`, `approver_Id`, `grade`, `employee_name`, `contact_Number`) VALUES ('17', 'DABS', 'user', '16313', '16292', 'M4', 'Dalbir Singh Dahiya', '56789');

CREATE FUNCTION getCabPending(id int)
RETURNS int DETERMINISTIC
RETURN (select count(c.request_id)  
from cab_details c inner join main_request m on m.request_id=c.request_id where m.requested_by=id and m.current_status not in('approved','Cancelled') and c.is_active=1);

CREATE FUNCTION getCabApproved(id int)
RETURNS int DETERMINISTIC
RETURN (select count(c.request_id)  
from cab_details c inner join main_request m on m.request_id=c.request_id where m.requested_by=id and m.current_status ='approved' and c.is_active=1);


CREATE FUNCTION getAccomodationApproved(id int)
RETURNS int DETERMINISTIC
RETURN (select count(c.request_id) 
from accomodation_details c inner join main_request m on m.request_id=c.request_id where m.requested_by=id and m.current_status ='approved' and c.is_active=1);

CREATE FUNCTION getAccomodationPending(id int)
RETURNS int DETERMINISTIC
RETURN (select count(c.request_id) 
from accomodation_details c inner join main_request m on m.request_id=c.request_id where m.requested_by=id and m.current_status not in('approved','Cancelled') and c.is_active=1);

CREATE FUNCTION getFlightPending(id int)
RETURNS int DETERMINISTIC
RETURN (select count(c.request_id) 
from flight_details c inner join main_request m on m.request_id=c.request_id where m.requested_by=id and m.current_status  not in('approved','Cancelled') and c.is_active=1);

CREATE FUNCTION getFlightApproved(id int)
RETURNS int DETERMINISTIC
RETURN (select count(c.request_id) 
from flight_details c inner join main_request m on m.request_id=c.request_id where m.requested_by=id and m.current_status ='approved' and c.is_active=1);

CREATE FUNCTION getForexApproved(id int)
RETURNS int DETERMINISTIC
RETURN (select count(c.request_id) 
from forex_details c inner join main_request m on m.request_id=c.request_id where m.requested_by=id and m.current_status ='approved' and c.is_active=1);

CREATE FUNCTION getForexPending(id int)
RETURNS int DETERMINISTIC
RETURN (select count(c.request_id) 
from forex_details c inner join main_request m on m.request_id=c.request_id where m.requested_by=id and m.current_status  not in('approved','Cancelled') and c.is_active=1);

CREATE FUNCTION getVisaApproved(id int)
RETURNS int DETERMINISTIC
RETURN (select count(c.request_id) 
from visa_request c inner join main_request m on m.request_id=c.request_id where m.requested_by=id and m.current_status ='approved' and c.is_active=1);

CREATE FUNCTION getVisaPending(id int)
RETURNS int DETERMINISTIC
RETURN (select count(c.request_id) 
from visa_request c inner join main_request m on m.request_id=c.request_id where m.requested_by=id and m.current_status  not in('approved','Cancelled') and c.is_active=1);




