# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.6.17)
# Database: vaisala
# Generation Time: 2017-04-14 17:24:56 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table observations
# ------------------------------------------------------------

DROP TABLE IF EXISTS `observations`;

CREATE TABLE `observations` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `observed_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `received_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `value` float NOT NULL DEFAULT '0',
  `quality_code` smallint(6) NOT NULL DEFAULT '0',
  `sensor_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `observations` WRITE;
/*!40000 ALTER TABLE `observations` DISABLE KEYS */;

INSERT INTO `observations` (`id`, `observed_timestamp`, `received_timestamp`, `value`, `quality_code`, `sensor_id`)
VALUES
	(1,'2017-01-02 10:01:00','2017-01-02 10:02:29',0,1,1),
	(2,'2017-01-02 10:03:00','2017-01-02 10:03:10',1.23,0,1),
	(3,'2017-01-02 10:05:00','2017-01-02 10:05:10',999,2,1),
	(4,'2017-01-02 10:05:00','2017-01-02 10:05:30',1,0,2),
	(5,'2017-01-02 10:10:00','2017-01-02 10:10:30',0,0,2);

/*!40000 ALTER TABLE `observations` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sensors
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sensors`;

CREATE TABLE `sensors` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL DEFAULT '',
  `type` varchar(256) NOT NULL DEFAULT '',
  `station_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `sensors` WRITE;
/*!40000 ALTER TABLE `sensors` DISABLE KEYS */;

INSERT INTO `sensors` (`id`, `name`, `type`, `station_id`)
VALUES
	(1,'PW20','PRECIP_AMOUNT',1),
	(2,'PW21','WEATHER_CONDITION',1);

/*!40000 ALTER TABLE `sensors` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table stations
# ------------------------------------------------------------

DROP TABLE IF EXISTS `stations`;

CREATE TABLE `stations` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL DEFAULT '',
  `location` varchar(1024) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `stations` WRITE;
/*!40000 ALTER TABLE `stations` DISABLE KEYS */;

INSERT INTO `stations` (`id`, `name`, `location`)
VALUES
	(1,'BOU-MASTER','123 Local Street, CO – 80027');

/*!40000 ALTER TABLE `stations` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
