# SQL Manager 2010 for MySQL 4.5.0.9
# ---------------------------------------
# Host     : localhost
# Port     : 3306
# Database : hoteldb


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES latin1 */;

SET FOREIGN_KEY_CHECKS=0;

DROP DATABASE IF EXISTS `hoteldb`;

CREATE DATABASE `hoteldb`
    CHARACTER SET 'latin1'
    COLLATE 'latin1_swedish_ci';

USE `hoteldb`;

#
# Structure for the `analitics` table : 
#

DROP TABLE IF EXISTS `analitics`;

CREATE TABLE `analitics` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `countGuest` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `summPeriod` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

#
# Structure for the `configuration` table : 
#

DROP TABLE IF EXISTS `configuration`;

CREATE TABLE `configuration` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adress` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `hotelEmail` varchar(255) DEFAULT NULL,
  `hotelFax` varchar(255) DEFAULT NULL,
  `hotelName` varchar(255) DEFAULT NULL,
  `hotelPhones` varchar(255) DEFAULT NULL,
  `discountLevelFive` int(11) DEFAULT NULL,
  `discountLevelTen` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

#
# Structure for the `country` table : 
#

DROP TABLE IF EXISTS `country`;

CREATE TABLE `country` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

#
# Structure for the `document_type` table : 
#

DROP TABLE IF EXISTS `document_type`;

CREATE TABLE `document_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

#
# Structure for the `guest` table : 
#

DROP TABLE IF EXISTS `guest`;

CREATE TABLE `guest` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `surname` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `docFileName` varchar(255) DEFAULT NULL,
  `docNumber` varchar(255) DEFAULT NULL,
  `country_id` int(11) DEFAULT NULL,
  `document_id` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5w97l6avtpl8j68u9d4j1ev1o` (`country_id`),
  KEY `FKfnyp9mgi8ppkibmf1157snva0` (`document_id`),
  CONSTRAINT `FK5w97l6avtpl8j68u9d4j1ev1o` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`),
  CONSTRAINT `FKfnyp9mgi8ppkibmf1157snva0` FOREIGN KEY (`document_id`) REFERENCES `document_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

#
# Structure for the `hibernate_sequence` table : 
#

DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `rooms` table : 
#

DROP TABLE IF EXISTS `rooms`;

CREATE TABLE `rooms` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` smallint(6) NOT NULL,
  `classRoom` varchar(20) NOT NULL,
  `capacity` smallint(6) NOT NULL,
  `price` int(11) NOT NULL,
  `status` varchar(20) NOT NULL,
  `hasBathroom` tinyint(4) DEFAULT NULL,
  `hasBed` tinyint(4) DEFAULT NULL,
  `hasFridge` tinyint(4) DEFAULT NULL,
  `hasHairdryer` tinyint(4) DEFAULT NULL,
  `hasSafe` tinyint(4) DEFAULT NULL,
  `hasShower` tinyint(4) DEFAULT NULL,
  `hasTV` tinyint(4) DEFAULT NULL,
  `otherOptions` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

#
# Structure for the `orders` table : 
#

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dateSt` date DEFAULT NULL,
  `dateEnd` date DEFAULT NULL,
  `price` int(11) NOT NULL,
  `orderStatus` varchar(20) NOT NULL,
  `idGuest` int(11) DEFAULT NULL,
  `idRoom` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKadqwwcbq0ju2pqvbbvnwkoqd5` (`idGuest`),
  KEY `FKfwv0rbenw5up1hg25wb1ers2w` (`idRoom`),
  CONSTRAINT `FKadqwwcbq0ju2pqvbbvnwkoqd5` FOREIGN KEY (`idGuest`) REFERENCES `guest` (`id`),
  CONSTRAINT `FKfwv0rbenw5up1hg25wb1ers2w` FOREIGN KEY (`idRoom`) REFERENCES `rooms` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

#
# Structure for the `service` table : 
#

DROP TABLE IF EXISTS `service`;

CREATE TABLE `service` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `price` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

#
# Structure for the `listofservice` table : 
#

DROP TABLE IF EXISTS `listofservice`;

CREATE TABLE `listofservice` (
  `lists_id` int(11) NOT NULL AUTO_INCREMENT,
  `count_service` int(11) NOT NULL,
  `service_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `current_sum` int(11) DEFAULT NULL,
  `service_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`lists_id`),
  KEY `FKfs51060cksy34w66fcu4dif1m` (`order_id`),
  KEY `FKlnh25749c42ojjd8fjuaow1wu` (`service_id`),
  CONSTRAINT `FKfs51060cksy34w66fcu4dif1m` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FKlnh25749c42ojjd8fjuaow1wu` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

#
# Structure for the `news` table : 
#

DROP TABLE IF EXISTS `news`;

CREATE TABLE `news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `full_text` text,
  `simple_name` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4538gbwfa03nwr9edl3fdloo9` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

#
# Structure for the `payment` table : 
#

DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comments` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `payments_type` varchar(255) DEFAULT NULL,
  `summ` int(11) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;

#
# Structure for the `preorder` table : 
#

DROP TABLE IF EXISTS `preorder`;

CREATE TABLE `preorder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dateEnd` date DEFAULT NULL,
  `dateSt` date DEFAULT NULL,
  `emailGuest` varchar(255) DEFAULT NULL,
  `nameGuest` varchar(255) DEFAULT NULL,
  `phoneGuest` varchar(255) DEFAULT NULL,
  `additionalInfo` varchar(255) DEFAULT NULL,
  `surnameGuest` varchar(255) DEFAULT NULL,
  `country_id` int(11) DEFAULT NULL,
  `idRoom` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `roomgalery` table : 
#

DROP TABLE IF EXISTS `roomgalery`;

CREATE TABLE `roomgalery` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `room_id` int(11) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK527mhbpo1d8p3plt1aei3li2l` (`room_id`),
  CONSTRAINT `FK527mhbpo1d8p3plt1aei3li2l` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

#
# Structure for the `user` table : 
#

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `pass` varchar(255) DEFAULT NULL,
  `roles` varchar(255) DEFAULT NULL,
  `personalInfo` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

#
# Data for the `analitics` table  (LIMIT 0,500)
#

INSERT INTO `analitics` (`id`, `countGuest`, `date`, `summPeriod`) VALUES 
  (1,6,'2017-04-07',256),
  (2,5,'2017-04-08',260),
  (3,8,'2017-05-09',265),
  (4,9,'2017-04-09',275),
  (5,10,'2017-04-10',500),
  (6,11,'2017-04-11',280),
  (7,8,'2017-04-12',356),
  (8,8,'2017-04-13',350),
  (9,8,'2017-05-05',366),
  (12,0,'2017-05-12',475),
  (13,0,'2017-05-13',475),
  (14,0,'2017-05-14',0),
  (16,2,'2017-05-15',699),
  (17,2,'2017-05-16',0);
COMMIT;

#
# Data for the `configuration` table  (LIMIT 0,500)
#

INSERT INTO `configuration` (`id`, `adress`, `date`, `hotelEmail`, `hotelFax`, `hotelName`, `hotelPhones`, `discountLevelFive`, `discountLevelTen`) VALUES 
  (1,'Grodno Vasilka 2-1','2016-10-01','omega-hotel@ggp.by','+375152 526536','Hotel Omega','+37529 6565656',100,2501);
COMMIT;

#
# Data for the `country` table  (LIMIT 0,500)
#

INSERT INTO `country` (`id`, `name`) VALUES 
  (1,'Belarus'),
  (2,'Russia'),
  (3,'Poland'),
  (4,'Lituania'),
  (5,'UK'),
  (6,'Ukr'),
  (7,'Kaz'),
  (8,'Nid'),
  (9,'Cz'),
  (10,'NL');
COMMIT;

#
# Data for the `document_type` table  (LIMIT 0,500)
#

INSERT INTO `document_type` (`id`, `name`) VALUES 
  (1,'Passport'),
  (2,'Card driver'),
  (3,'Viza');
COMMIT;

#
# Data for the `guest` table  (LIMIT 0,500)
#

INSERT INTO `guest` (`id`, `name`, `surname`, `email`, `phone`, `docFileName`, `docNumber`, `country_id`, `document_id`, `amount`) VALUES 
  (1,'Yury','Soukhotski','sasa@sasa.ru','+375295876277','foto.jpg','KH656565',1,1,897),
  (2,'Vasia','Pupkin','sasa@sasa.ru','444444','foto.jpg','h34343',2,2,327),
  (3,'Igor ','Nenasg','sasa@sasa.ru','333333','foto.jpg','xc656565',3,3,200),
  (4,'Alex','Sergeev','alex@nnm.ru','+37598989898','foto2','12554454',3,2,440),
  (5,'Olga','Vlasenka','olga@gmail.com','+3756565244','foto3','21212121',1,1,0),
  (6,'Zoa','Pavlovna','zp@ss.rui','545454521','212121','212323565',3,1,0),
  (7,'Alex','Ninoev','alexa@mail.ru','+3585454','foto6','5656522 /265',1,3,110),
  (8,'Inna','Vika (Yurevna)','vikeg@new.ru','+78565689','foto.jpg','xxxxx',1,1,10),
  (9,'Oleg','Balakin','oleg@new.ru','+78565689','foto.jpg','123456',1,1,160),
  (10,'Olga','Podavalenko','podolga@tut.by','+375295876277',NULL,'kh 25698 /0798 2016-04-09',1,1,0),
  (11,'Zoya','Manak','manak@mail.ru','+3752989',NULL,'XD659898 ROVD',1,1,0),
  (12,'Aleks','Rakevich','rak@nnm.me','+457896565',NULL,'HR 659863',1,1,0),
  (13,'Fiodor','Soukhotski','6565@mail.ru','+3756206065',NULL,'ROVD grodno 549898',1,1,0),
  (14,'Sasha','Nikitcevich','sacha@senla.com','+3752986868698','foto.jpg','11111111',1,1,140);
COMMIT;

#
# Data for the `rooms` table  (LIMIT 0,500)
#

INSERT INTO `rooms` (`id`, `number`, `classRoom`, `capacity`, `price`, `status`, `hasBathroom`, `hasBed`, `hasFridge`, `hasHairdryer`, `hasSafe`, `hasShower`, `hasTV`, `otherOptions`) VALUES 
  (1,101,'Standart',2,55,'FREE',1,1,1,1,1,1,1,'no'),
  (2,102,'Stand',2,33,'FREE',0,1,1,1,0,1,1,'no'),
  (3,103,'Lux',1,50,'BOOKED',1,0,1,0,1,0,1,'no'),
  (4,104,'Lux',2,70,'BOOKED',1,0,1,0,1,0,1,'no'),
  (5,201,'Standart',4,22,'FREE',0,0,0,0,0,0,0,'no'),
  (6,202,'Lux',4,60,'FREE',1,1,1,0,0,0,0,'no'),
  (7,203,'Lux',4,77,'FREE',1,1,1,1,1,1,1,'no'),
  (8,299,'Lux',5,88,'FREE',0,1,0,0,0,1,1,NULL),
  (9,399,'Standart',5,66,'REMONT',1,1,1,1,1,1,1,NULL);
COMMIT;

#
# Data for the `orders` table  (LIMIT 0,500)
#

INSERT INTO `orders` (`id`, `dateSt`, `dateEnd`, `price`, `orderStatus`, `idGuest`, `idRoom`) VALUES 
  (1,'2017-04-01','2017-04-07',368,'CLOSED',1,1),
  (2,'2017-05-01','2017-05-05',197,'CLOSED',1,2),
  (11,'2017-04-27','2017-04-29',100,'CLOSED',7,1),
  (12,'2017-04-27','2017-04-29',110,'CLOSED',9,1),
  (13,'2017-05-01','2017-05-06',475,'CLOSED',14,1),
  (14,'2017-05-20','2017-05-27',231,'CLOSED',1,2),
  (15,'2017-05-07','2017-05-14',331,'CLOSED',1,2),
  (16,'2017-05-15','2017-05-20',440,'CLOSED',4,8),
  (17,'2017-05-19','2017-05-21',119,'CLOSED',1,5),
  (18,'2017-05-19','2017-06-13',0,'BOOKED',3,1),
  (19,'2017-05-28','2017-06-01',0,'BOOKED',1,3),
  (20,'2017-05-15','2017-05-17',0,'BOOKED',14,4);
COMMIT;

#
# Data for the `service` table  (LIMIT 0,500)
#

INSERT INTO `service` (`id`, `name`, `price`, `description`) VALUES 
  (1,'Doctor',12,'Doctor vizit'),
  (2,'Taxi',2,'5 km tarif'),
  (3,'Laundry',15,'for one pc.'),
  (4,'Laundry ',25,'for 5 pc.'),
  (5,'Breakfest',50,'2 person'),
  (6,'Breakfest',44,'1 person'),
  (7,'Alko',55,'Pivo 1 l bazant'),
  (8,'Excursion',5,'Old city Grodno'),
  (9,'Excursion',20,'Avgustov chanel');
COMMIT;

#
# Data for the `listofservice` table  (LIMIT 0,500)
#

INSERT INTO `listofservice` (`lists_id`, `count_service`, `service_id`, `order_id`, `current_sum`, `service_name`) VALUES 
  (1,2,1,1,25,'Dinner'),
  (6,10,2,2,20,'\"Taxi/5 km tarif\"'),
  (7,3,2,1,12,'Test'),
  (8,4,2,1,8,'Taxi/5 km tarif'),
  (9,4,4,15,100,'Laundry /for 5 pc.'),
  (10,5,4,13,125,'Laundry /for 5 pc.'),
  (11,3,4,13,75,'Laundry /for 5 pc.'),
  (12,3,4,17,75,'Laundry /for 5 pc.');
COMMIT;

#
# Data for the `news` table  (LIMIT 0,500)
#

INSERT INTO `news` (`id`, `date`, `full_text`, `simple_name`, `user_id`) VALUES 
  (1,'2017-04-03','ency was rejected in June last year and he was being sought by immigration officials, police said.\r\nMeanwhile, a second suspect has been placed under formal arrest.\r\n\r\n','First news',1),
  (2,'2017-04-04','ency was rejected in June last year and he was being sought \r\nby immigration officials, police said.\r\nMeanwhile, a second suspect has been placed under formal arrest.','Secon news',1),
  (3,'2017-04-05','ency was rejected in June last year and he was being sought by immigration officials, police said.\r\nMeanwhile, a second suspect has been placed under formal arrest. Police said they were investigating the second suspect for a \"terrorist crime [by comency was rejected in June last year and he was being sought by immigration officials, police said.\r\nMeanwhile, a second suspect has been placed under formal arrest. Police said they were investigating the second suspect for a \"terrorist crime [by comency was rejected in June last year and he was being sought by immigration officials, police said.\r\nMeanwhile, a second suspect has been placed under formal arrest. Police said they were investigating the second suspect for a \"terrorist crime [by comency was rejected in June last year and he was being sought by immigration officials, police said.\r\nMeanwhile, a second suspect has been placed under formal arrest. Police said they were investigating the second suspect for a \"terrorist crime [by com','Third news',1),
  (4,'2017-04-06','ency was rejected in June last year and he was being sought by immigration officials, police said.\r\nMeanwhile, a second suspect has been placed under formal arrest. Police said they were investigating the second suspect for a \"terrorist crime [by com','Four news',1),
  (5,'2017-04-07','ency was rejected in June last year and he was being sought by immigration officials, police said.\r\nMeanwhile, a second suspect has been placed under formal arrest. Police said they were investigating the second suspect for a \"terrorist crime [by com','5',1),
  (6,'2017-04-09','ency was rejected in June last year and he was being sought by immigration officials, police said.\r\nMeanwhile, a second suspect has been placed under formal arrest. Police said they were investigating the second suspect for a \"terrorist crime [by com','6',1),
  (7,'2017-04-10','Seven news full','Seven news',1);
COMMIT;

#
# Data for the `payment` table  (LIMIT 0,500)
#

INSERT INTO `payment` (`id`, `comments`, `date`, `payments_type`, `summ`, `order_id`) VALUES 
  (1,'nalik','2017-04-07','CASH',33,1),
  (22,'ERIP','2017-04-26','ERIP',197,2),
  (23,'CASH','2017-04-26','CASH',397,1),
  (24,'CASH','2017-04-26','CASH',100,11),
  (25,'ERIP','2017-04-26','ERIP',550,11),
  (26,'ERIP','2017-04-26','ERIP',550,11),
  (27,'CARD','2017-04-27','CARD',120,12),
  (28,'CASH','2017-04-27','CASH',40,12),
  (29,'CASH','2017-05-06','CASH',331,15),
  (30,'ERIP','2017-05-12','ERIP',470,13),
  (31,'CASH','2017-05-12','CASH',5,13),
  (32,'CARD','2017-05-15','CARD',140,20),
  (33,'CASH','2017-05-15','CASH',440,16),
  (34,'CASH','2017-05-15','CASH',119,17);
COMMIT;

#
# Data for the `roomgalery` table  (LIMIT 0,500)
#

INSERT INTO `roomgalery` (`id`, `room_id`, `file_name`) VALUES 
  (1,1,'room1.jpg'),
  (2,2,'room1-1.jpg'),
  (3,1,'room11.jpg'),
  (4,2,'room21.jpg');
COMMIT;

#
# Data for the `user` table  (LIMIT 0,500)
#

INSERT INTO `user` (`id`, `name`, `pass`, `roles`, `personalInfo`) VALUES 
  (1,'sasa','a','ADMIN','Sasa Alex'),
  (2,'user','qwe','OPERATOR','User Vasia'),
  (3,'oper','a','OPERATOR','Operator Lena');
COMMIT;



/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;