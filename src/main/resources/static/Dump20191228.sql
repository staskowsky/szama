-- MySQL dump 10.13  Distrib 5.7.25, for Linux (x86_64)
--
-- Host: localhost    Database: szama
-- ------------------------------------------------------
-- Server version	5.7.25-0ubuntu0.18.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `day`
--

DROP TABLE IF EXISTS `day`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `day` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `caloricity` float DEFAULT NULL,
  `diet` bigint(20) NOT NULL,
  `days_order` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKre8ni5pvxjgj7l0ovcrsy2d7g` (`diet`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `day`
--

LOCK TABLES `day` WRITE;
/*!40000 ALTER TABLE `day` DISABLE KEYS */;
INSERT INTO `day` VALUES (1,0,1,0),(2,0,1,1),(3,0,1,2),(4,0,1,3),(5,0,1,4),(6,0,1,5),(7,0,1,6),(8,2547.67,2,0),(9,2690.77,2,1),(10,2690.77,2,2),(11,2547.67,2,3),(12,2690.77,2,4),(13,2619.82,2,5),(14,2547.67,2,6);
/*!40000 ALTER TABLE `day` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diet`
--

DROP TABLE IF EXISTS `diet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diet` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `max_caloricity` float DEFAULT NULL,
  `min_caloricity` float DEFAULT NULL,
  `user` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlym6h7m178ic86q9fhehrpimv` (`user`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diet`
--

LOCK TABLES `diet` WRITE;
/*!40000 ALTER TABLE `diet` DISABLE KEYS */;
INSERT INTO `diet` VALUES (1,3227.4,2640.6,1),(2,3227.4,2640.6,1);
/*!40000 ALTER TABLE `diet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorite_meals`
--

DROP TABLE IF EXISTS `favorite_meals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favorite_meals` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `meal` bigint(20) NOT NULL,
  `user` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKonp14sxuc82p43ng9h3n3adyv` (`meal`),
  KEY `FKappdcye2bg4u2iqnry8jiio5v` (`user`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorite_meals`
--

LOCK TABLES `favorite_meals` WRITE;
/*!40000 ALTER TABLE `favorite_meals` DISABLE KEYS */;
/*!40000 ALTER TABLE `favorite_meals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredients`
--

DROP TABLE IF EXISTS `ingredients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ingredients` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` float DEFAULT NULL,
  `meal` bigint(20) NOT NULL,
  `product` bigint(20) NOT NULL,
  `volume` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8kkf2doa9c302eg4u6c4mf4nw` (`meal`),
  KEY `FK1j0rteya3vwdonvvim6cyihaf` (`product`)
) ENGINE=MyISAM AUTO_INCREMENT=111 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredients`
--

LOCK TABLES `ingredients` WRITE;
/*!40000 ALTER TABLE `ingredients` DISABLE KEYS */;
INSERT INTO `ingredients` VALUES (1,168,1,17,'3 jaja rozmiar \"L\"'),(2,5,1,18,'1 łyżeczka'),(6,50,1,19,'2 kromki'),(4,1,1,32,'do smaku'),(5,1,1,30,'do smaku'),(7,200,2,2,'4 średnie marchewki'),(8,120,2,7,'jeden banan'),(9,260,2,6,'jedno mango'),(10,5,2,29,'1 łyżeczka'),(11,5,2,28,'1 łyżeczka'),(12,5,2,13,'1 łyżeczka'),(13,100,2,33,'pół szklanki'),(14,300,3,34,''),(17,150,3,8,'5 plastrów'),(16,75,3,33,'1/3 szklanki'),(18,300,4,35,'4 sztuki'),(19,300,4,1,'2 sztuki'),(20,50,4,5,'2 garści'),(21,112,5,17,'2 jaja \"L\"'),(22,25,5,5,'garść'),(23,25,5,22,'dwa plastry'),(24,65,5,36,'pół kuli'),(25,5,5,18,'łyżeczka'),(26,1,5,31,'szczypta'),(27,1,5,30,'szczypta'),(34,250,6,41,''),(35,120,6,39,''),(36,60,6,38,''),(31,112,6,17,'2 jaja \"L\"'),(32,3,6,30,'szczypta'),(33,2,6,31,'szczypta'),(37,200,7,42,''),(38,5,7,43,'łyżeczka'),(39,50,8,24,''),(40,50,8,2,'średnia marchew'),(41,70,9,45,'2 kromki'),(42,50,9,44,''),(43,40,9,46,''),(44,15,9,3,''),(45,5,9,13,'łyżeczka'),(46,1,9,30,'szczypta'),(47,150,10,47,'5 sztuk'),(48,5,10,18,'łyżeczka'),(49,1,10,32,'szczypta'),(50,1,10,30,'szczypta'),(51,100,11,50,''),(52,30,11,49,''),(53,100,11,48,'pół kostki'),(54,100,11,51,'pół papryki'),(55,10,11,37,'łyżka'),(56,1,11,30,'szczypta'),(57,250,12,52,'4 sztuki'),(58,400,12,26,'puszka'),(59,500,12,53,''),(60,250,12,51,'1 sztuka'),(61,250,12,54,'puszka'),(62,250,12,55,'puszka'),(63,100,12,57,'1 sztuka'),(64,100,12,56,''),(65,5,12,31,'1 łyżeczka'),(66,5,12,30,'łyżeczka'),(67,100,12,58,''),(68,10,12,27,'4-5 ząbków'),(69,500,14,53,''),(70,100,14,3,'1 sztuka'),(71,10,14,27,'2 ząbki'),(72,400,14,26,'puszka'),(73,10,14,37,'1 łyżka'),(74,250,14,41,''),(75,1,14,31,'szczypta'),(76,1,14,30,'szczypta'),(91,500,15,60,''),(78,150,15,39,''),(79,50,15,2,'1 sztuka'),(80,50,15,40,'1 łodyga'),(81,100,15,3,'1 sztuka'),(82,40,15,18,''),(83,100,15,59,''),(84,200,15,33,''),(86,100,15,12,''),(87,5,15,31,'1 łyżeczka'),(88,5,15,30,'1 łyżeczka'),(89,500,15,41,''),(92,500,15,62,''),(93,50,15,38,''),(94,500,16,64,''),(95,250,16,33,''),(96,50,16,65,'4 łyżki'),(97,10,16,31,'dwie łyżeczki'),(98,300,16,63,''),(99,500,16,3,'3 sztuki'),(100,500,16,4,''),(101,20,16,18,'2 łyżki'),(102,5,16,30,'łyżeczka'),(103,40,16,66,'3 łyżki'),(104,100,17,68,''),(105,20,17,69,'jedna du?a sztuka lub dwie ma?e'),(106,25,17,3,'oko?o ?wiartki'),(107,2,17,32,'szczypta'),(108,2,17,30,'szczypta'),(109,20,17,70,'1 sztuka'),(110,35,17,45,'1 kromka');
/*!40000 ALTER TABLE `ingredients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meal_pointer`
--

DROP TABLE IF EXISTS `meal_pointer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meal_pointer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `day` bigint(20) NOT NULL,
  `meal_id` bigint(20) DEFAULT NULL,
  `meal_pointers_order` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcghiisyaq6e6q3ds3ae7efrng` (`day`),
  KEY `FKenmu0knupd4dl8jy9jffbttgm` (`meal_id`)
) ENGINE=MyISAM AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meal_pointer`
--

LOCK TABLES `meal_pointer` WRITE;
/*!40000 ALTER TABLE `meal_pointer` DISABLE KEYS */;
INSERT INTO `meal_pointer` VALUES (1,1,NULL,0),(2,1,NULL,1),(3,1,NULL,2),(4,1,NULL,3),(5,2,NULL,0),(6,2,NULL,1),(7,2,NULL,2),(8,2,NULL,3),(9,3,NULL,0),(10,3,NULL,1),(11,3,NULL,2),(12,3,NULL,3),(13,4,NULL,0),(14,4,NULL,1),(15,4,NULL,2),(16,4,NULL,3),(17,5,NULL,0),(18,5,NULL,1),(19,5,NULL,2),(20,5,NULL,3),(21,6,NULL,0),(22,6,NULL,1),(23,6,NULL,2),(24,6,NULL,3),(25,7,NULL,0),(26,7,NULL,1),(27,7,NULL,2),(28,7,NULL,3),(29,8,15,0),(30,8,7,1),(31,8,16,2),(32,8,15,3),(33,9,15,0),(34,9,9,1),(35,9,16,2),(36,9,15,3),(37,10,15,0),(38,10,9,1),(39,10,16,2),(40,10,15,3),(41,11,15,0),(42,11,7,1),(43,11,16,2),(44,11,15,3),(45,12,15,0),(46,12,9,1),(47,12,16,2),(48,12,15,3),(49,13,15,0),(50,13,17,1),(51,13,16,2),(52,13,15,3),(53,14,15,0),(54,14,7,1),(55,14,16,2),(56,14,15,3);
/*!40000 ALTER TABLE `meal_pointer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meal_products`
--

DROP TABLE IF EXISTS `meal_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meal_products` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` float DEFAULT NULL,
  `weight` float DEFAULT NULL,
  `meal` bigint(20) NOT NULL,
  `product` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4wvqr19xqaljef3d72n00jgw5` (`meal`),
  KEY `FKgkrqubdowi62se6h1iiu6de7m` (`product`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meal_products`
--

LOCK TABLES `meal_products` WRITE;
/*!40000 ALTER TABLE `meal_products` DISABLE KEYS */;
/*!40000 ALTER TABLE `meal_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meals`
--

DROP TABLE IF EXISTS `meals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meals` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` float DEFAULT NULL,
  `carb` float DEFAULT NULL,
  `description` longtext CHARACTER SET utf8,
  `fat` float DEFAULT NULL,
  `kcal` float DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `portions` int(11) DEFAULT NULL,
  `protein` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_m7fx5swdbfocr1qxakmo85m3y` (`name`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meals`
--

LOCK TABLES `meals` WRITE;
/*!40000 ALTER TABLE `meals` DISABLE KEYS */;
INSERT INTO `meals` VALUES (1,50,38.891,'Roztapiamy masło, wbijamy jaja, mieszamy do żądanego ścięcia jaj, doprawiamy solą i pieprzem do smaku. Spożywamy z dwoma kromkami chleba tygrysiego.',22.254,469.5,'Jajecznica z trzech jaj z chlebem',1,27.445),(2,695,83.07,'Wrzucamy wszystkie składniki do pojemnika i blendujemy.',2.34,398.1,'Pomarańczowe smoothie',1,5.37),(3,525,39.75,'Składniki wrzucamy do pojemnika, blendujemy całość.',1.35,196.5,'Smoothie z truskawek',1,2.7),(4,650,66.15,'Składniki wrzucamy do pojemnika i blendujemy.',2.9,341,'Zielone smoothie',1,5.2),(5,234,3.655,'Rozpuszczamy masło na patelni, wlewamy wymieszane, doprawione solą i pieprzem jaja. Po ścięciu obracamy na drugą stronę, na połówce układamy blanszowany szpinak, szynkę i ser, po chwili składamy na pół i przekładamy na talerz.',26.847,401.2,'Omlet ze szpinakiem, szynką i serem',1,35.895),(6,547,49.138,'',34.5565,666.525,'Spaghetti carbonara',2,38.125),(7,205,4.085,'Siekamy szczypiorek, dodajemy do serka wiejskiego i mieszamy.',10.04,195.75,'Serek wiejski ze szczypiorkiem',1,22.205),(8,100,7.55,'Marchew kroimy w słupki i spożywamy nabierając na nią hummus.',14.1,180,'Marchew z hummusem',1,3.5),(9,181,38.978,'Dwie kromki chleba razowego smarujemy serkiem śmietankowym, nakładamy na to łososia w plastrach. Szatkujemy cebulę i posypujemy nią kanapki, kropimy sokiem z cytryny i doprawiamy szczyptą pieprzu czarnego.',14.883,338.85,'Kanapki z serkiem śmietankowym i łososiem',1,17.54),(10,157,3.983,'Rozpuścić masło na patelni, przesmażyć w nim szparagi, dodać sól i pieprz do smaku.',4.458,70.8,'Szparagi na maśle',1,2.995),(11,341,8.678,'Wszystkie składniki kroimy w kostki i wrzucamy do miski. Zalewamy to łyżką oliwy z oliwek, dodajemy szczyptę pieprzu i mieszamy.',35.503,443.35,'Sałatka z fetą, papryką i oliwkami',1,20.73),(12,2220,74.06,'',25.0663,728.612,'Burrito wieprzowe',4,47.935),(15,2350,60.1175,'',45.1912,876.562,'Spaghetti bolognese (ragu)',4,49.0575),(14,1272,59.859,'',27.9965,752.975,'Spaghetti z wieprzowiną w sosie pomidorowym',2,63.425),(16,2175,78.9967,'',21.5108,598.792,'Pierogi ruskie',6,21.1817),(17,204,21.556,'',9.921,267.9,'Tatar',1,25.915);
/*!40000 ALTER TABLE `meals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `metabolism`
--

DROP TABLE IF EXISTS `metabolism`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `metabolism` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activity_level` float NOT NULL,
  `age` int(11) NOT NULL,
  `bmi` float NOT NULL,
  `bmr` float NOT NULL,
  `height` int(11) NOT NULL,
  `mass` int(11) NOT NULL,
  `sex` int(11) NOT NULL,
  `users` bigint(20) NOT NULL,
  `kcal_to_gain` float NOT NULL,
  `kcal_to_hold` float NOT NULL,
  `kcal_to_lose` float NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKovr4abyxr9dvpgw6rnw4ehaxv` (`users`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `metabolism`
--

LOCK TABLES `metabolism` WRITE;
/*!40000 ALTER TABLE `metabolism` DISABLE KEYS */;
INSERT INTO `metabolism` VALUES (12,1.6,25,23.7812,1849,174,72,1,1,3401,2958,2514);
/*!40000 ALTER TABLE `metabolism` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `carb` float DEFAULT NULL,
  `category` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `fat` float DEFAULT NULL,
  `kcal` float DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `protein` float DEFAULT NULL,
  `owner` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_o61fmio5yukmmiqgnxf8pnavn` (`name`),
  KEY `FKdosg5xdetryuhd1btnu92v9iw` (`owner`)
) ENGINE=MyISAM AUTO_INCREMENT=71 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,10.1,'FRUIT',0.4,50,'Jabłko',0.4,1),(2,5.1,'VEGETABLE',0.2,33,'Marchew',1,1),(3,5.2,'VEGETABLE',0.4,33,'Cebula biała',1.4,1),(4,15,'VEGETABLE',0.1,71,'Ziemniak ugotowany',1.8,1),(5,0.9,'VEGETABLE',0.4,22,'Szpinak',2.6,1),(6,15.3,'FRUIT',0.3,69,'Mango',0.5,1),(7,21.8,'FRUIT',0.3,97,'Banan',1,1),(8,14.9,'FRUIT',0.1,65,'Ananas',0.4,1),(9,4.1,'FRUIT',15.3,169,'Awokado',2,1),(10,6.2,'DAIRY',2,60,'Jogurt naturalny',4.3,1),(11,5.3,'FRUIT',0.3,43,'Malina',1.3,1),(12,4.9,'DAIRY',2,51,'Mleko 2%',3.3,1),(13,8.6,'FRUIT',0,25,'Sok z cytryny',0.4,1),(14,17.8,'VEGETABLE',0.7,80,'Imbir',1.8,1),(15,11,'FRUIT',0,43,'Sok jabłkowy TYMBARK',0,1),(16,10,'FRUIT',0,44,'Sok pomarańczowy TYMBARK',0.6,1),(17,0.6,'EGG',9.7,140,'Jajo kurze',12.5,1),(18,0.7,'DAIRY',82.5,735,'Masło extra',0.7,1),(19,49.6,'WHEAT',2.4,260,'Chleb tygrysi',8.4,1),(20,2.9,'VEGETABLE',0.2,19,'Pomidor',0.9,1),(21,2.9,'VEGETABLE',0.2,19,'Pomidor koktajlowy',0.9,1),(22,0.5,'MEAT',5.3,138,'Szynka biała SOKOŁÓW',22,1),(23,1.7,'MEAT',3.1,91,'Szynka konserwowa SOKOŁÓW',14,1),(24,10,'VEGETABLE',28,327,'Hummus klasyczny',6,1),(25,3.5,'MEAT',36,426,'Salami SOKOŁÓW',22,1),(26,4,'VEGETABLE',0.2,23,'Pomidor krojony z puszki',1.3,1),(27,28.5,'VEGETABLE',0.5,152,'Czosnek',6.4,1),(28,65,'SPICES',10,354,'Kurkuma w proszku',8,1),(29,65,'SPICES',6,347,'Imbir mielony',9,1),(30,64.8,'SPICES',3.3,255,'Pieprz czarny',11,1),(31,0,'SPICES',0,0,'Sól kuchenna',0,1),(32,0,'SPICES',0,0,'Sól himalajska różowa',0,1),(33,0,'OTHER',0,0,'Woda',0,1),(34,5.8,'FRUIT',0.4,33,'Truskawka',0.7,1),(35,11.8,'FRUIT',0.5,60,'Kiwi',0.9,1),(36,3,'DAIRY',16,254,'Ser mozarella',24,1),(37,0.2,'OTHER',99.6,882,'Oliwa z oliwek',0,1),(38,0.1,'DAIRY',32,454,'Ser parmezan',40.7,1),(39,0.5,'MEAT',31,351,'Pancetta',17.5,1),(40,1.8,'VEGETABLE',0.2,17,'Seler naciowy',1,1),(41,38,'WHEAT',0.7,190,'Makaron spaghetti LUBELLA',6.6,1),(42,2,'DAIRY',5,97,'Serek wiejski',11,1),(43,1.7,'VEGETABLE',0.8,35,'Szczypiorek',4.1,1),(44,0,'MEAT',8.4,162,'Łosoś wędzony',21.5,1),(45,51.2,'BREAD',1.7,213,'Chleb razowy',5.9,1),(46,3.2,'DAIRY',23.5,250,'Serek śmietankowy',5.8,1),(47,2.2,'VEGETABLE',0.2,21,'Szparagi',1.9,1),(48,1.4,'DAIRY',21,265,'Ser feta',17.5,1),(49,1.7,'VEGETABLE',12.7,132,'Oliwki czarne',1.4,1),(50,1.5,'VEGETABLE',0.2,16,'Sałata lodowa',1.4,1),(51,4.6,'VEGETABLE',0.5,32,'Papryka czerwona',1.3,1),(52,47,'BREAD',8.3,304,'Tortilla pszenna',7.4,1),(53,0,'MEAT',8.6,160,'Łopatka wieprzowa',20.6,1),(54,12.3,'VEGETABLE',0.6,87,'Fasola czerwona',8.1,1),(55,10.8,'VEGETABLE',1.9,80,'Kukurydza',2.9,1),(56,80,'WHEAT',0.2,348,'Ryż biały',6.8,1),(57,5.2,'VEGETABLE',0.4,33,'Cebula czerwona',1.4,1),(58,2.2,'DAIRY',27.4,356,'Ser gouda',24.9,1),(59,0.6,'OTHER',0,67,'Wino białe wytrawne',0.1,1),(60,0,'MEAT',15.6,230,'Łopatka wołowa',20.9,1),(61,19,'PROCESSED',0.5,82,'Koncentrat pomidorowy',4.3,1),(62,6.4,'PROCESSED',0.2,34,'Passata pomidorowa',1.1,1),(63,3.7,'DAIRY',4.7,132,'Twaróg półtłusty',18.3,1),(64,71.7,'WHEAT',1.2,343,'Mąka pszenna typ 500',11.1,1),(65,0,'OTHER',100,900,'Olej słonecznikowy',0,1),(66,0,'OTHER',99.5,880,'Smalec',0,1),(67,0,'MEAT',2.3,110,'Ligawa wołowa',22,1),(68,0,'MEAT',3.5,112,'Pol?dwica wo?owa',20.1,1),(69,4.9,'VEGETABLE',0.1,23,'Ogórek konserwowy',0.4,1),(70,0.3,'EGG',28.2,317,'?ó?tko jaja kurzego',15.5,1);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `role` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `username` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `metabolism` bigint(20) DEFAULT NULL,
  `diet` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`),
  KEY `FKgxiof41hou66vgbmidheskf2n` (`diet`),
  KEY `FKlrd0jq1b8w5gwlokkwbtytrch` (`metabolism`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin@szama.pl','$2a$10$5xeZCuA8M58ERxPqCY40UenpemowKV.Gyi0ciV/xG8ZLKlesnWfZm','ADMIN','admin',12,2);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-28  1:15:22
