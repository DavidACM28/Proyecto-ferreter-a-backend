CREATE DATABASE  IF NOT EXISTS `ferreteria` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ferreteria`;
-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: ferreteria
-- ------------------------------------------------------
-- Server version	8.4.6

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `auditoriainventario`
--

DROP TABLE IF EXISTS `auditoriainventario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auditoriainventario` (
  `idAuditoria` int NOT NULL AUTO_INCREMENT,
  `idProducto` int NOT NULL,
  `accion` enum('INSERT','UPDATE','DELETE') NOT NULL,
  `cantidadAnterior` int DEFAULT NULL,
  `cantidadNueva` int DEFAULT NULL,
  `idTrabajador` int DEFAULT NULL,
  `fecha` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `referencia` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idAuditoria`),
  KEY `idProducto` (`idProducto`),
  KEY `idTrabajador` (`idTrabajador`),
  CONSTRAINT `auditoriainventario_ibfk_1` FOREIGN KEY (`idProducto`) REFERENCES `producto` (`idProducto`),
  CONSTRAINT `auditoriainventario_ibfk_2` FOREIGN KEY (`idTrabajador`) REFERENCES `trabajador` (`idTrabajador`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auditoriainventario`
--

LOCK TABLES `auditoriainventario` WRITE;
/*!40000 ALTER TABLE `auditoriainventario` DISABLE KEYS */;
INSERT INTO `auditoriainventario` VALUES (2,2,'INSERT',0,200,3,'2025-10-10 21:07:12','Ingreso de productos'),(3,1,'UPDATE',100,99,3,'2025-10-10 21:53:44','Venta'),(4,1,'UPDATE',99,49,3,'2025-10-11 16:46:16','Venta'),(5,2,'UPDATE',200,198,3,'2025-10-16 04:30:15','Venta'),(6,1,'UPDATE',49,45,3,'2025-10-16 04:41:58','Venta'),(7,2,'UPDATE',200,197,3,'2025-10-16 04:41:58','Venta'),(8,2,'UPDATE',200,199,3,'2025-10-16 04:55:17','Venta'),(9,2,'UPDATE',199,194,3,'2025-10-16 04:59:43','Venta'),(10,2,'UPDATE',194,193,3,'2025-10-17 17:08:21','Venta'),(11,2,'UPDATE',193,189,3,'2025-10-17 17:18:19','Venta'),(12,1,'UPDATE',49,50,3,'2025-10-19 01:58:35','Ajuste en el inventario'),(13,1,'UPDATE',50,49,3,'2025-10-19 01:59:15','Reajuste en el inventario'),(14,3,'INSERT',0,100,3,'2025-10-19 03:22:41','Nuevo producto'),(15,3,'UPDATE',100,93,3,'2025-10-19 03:40:59','Venta'),(16,2,'UPDATE',189,188,3,'2025-10-19 04:37:08','Venta'),(17,1,'UPDATE',49,9,3,'2025-10-19 17:32:30','prueba'),(18,1,'UPDATE',9,49,3,'2025-10-19 17:32:45','prueba'),(19,1,'UPDATE',49,0,3,'2025-10-19 17:33:29','prueba'),(20,1,'UPDATE',0,49,3,'2025-10-19 17:33:41','prueba'),(21,1,'UPDATE',49,9,3,'2025-10-19 17:39:50','prueba'),(22,1,'UPDATE',9,49,3,'2025-10-19 17:40:06','prueba'),(23,1,'UPDATE',49,0,3,'2025-10-19 17:40:45','prueba'),(24,1,'UPDATE',0,49,3,'2025-10-19 17:41:13','prueba'),(25,1,'UPDATE',49,0,3,'2025-10-19 17:41:25','prueba'),(26,1,'UPDATE',0,49,3,'2025-10-19 17:44:02','prueba'),(27,1,'UPDATE',49,0,3,'2025-10-19 21:16:26','prueba'),(28,1,'UPDATE',0,9,3,'2025-10-19 21:17:57','prueba'),(29,1,'UPDATE',9,49,3,'2025-10-19 21:52:05','prueba'),(30,1,'UPDATE',49,50,3,'2025-10-20 00:13:28','prueba'),(31,1,'UPDATE',50,49,3,'2025-10-20 00:13:43','prueba'),(32,1,'UPDATE',49,50,3,'2025-10-20 00:14:37','prueba'),(33,1,'UPDATE',50,49,3,'2025-10-20 00:14:43','prueba'),(34,2,'UPDATE',188,189,3,'2025-10-20 00:15:11','prueba'),(35,2,'UPDATE',189,188,3,'2025-10-20 00:15:18','prueba'),(36,4,'INSERT',0,200,3,'2025-10-20 18:34:38','Nuevo producto');
/*!40000 ALTER TABLE `auditoriainventario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `idCategoria` int NOT NULL AUTO_INCREMENT,
  `nombreCategoria` varchar(100) NOT NULL,
  `estadoCategoria` tinyint(1) NOT NULL,
  PRIMARY KEY (`idCategoria`),
  UNIQUE KEY `idCategoria` (`idCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'Materiales de construcción',1),(2,'Ceramicas',1);
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cotizacion`
--

DROP TABLE IF EXISTS `cotizacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cotizacion` (
  `idCotización` int NOT NULL AUTO_INCREMENT,
  `idTrabajador` int NOT NULL,
  `totalCotizacion` double NOT NULL,
  `fechaCotizacion` date NOT NULL,
  PRIMARY KEY (`idCotización`),
  UNIQUE KEY `idCotización` (`idCotización`),
  KEY `idTrabajador` (`idTrabajador`),
  CONSTRAINT `cotizacion_ibfk_1` FOREIGN KEY (`idTrabajador`) REFERENCES `trabajador` (`idTrabajador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cotizacion`
--

LOCK TABLES `cotizacion` WRITE;
/*!40000 ALTER TABLE `cotizacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `cotizacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detallecotizacion`
--

DROP TABLE IF EXISTS `detallecotizacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detallecotizacion` (
  `idDetalleCotizacion` int NOT NULL AUTO_INCREMENT,
  `idCotizacion` int NOT NULL,
  `idProducto` int NOT NULL,
  `precioProducto` double NOT NULL,
  `cantidadProducto` int NOT NULL,
  PRIMARY KEY (`idDetalleCotizacion`),
  UNIQUE KEY `idDetalleCotizacion` (`idDetalleCotizacion`),
  KEY `idProducto` (`idProducto`),
  CONSTRAINT `detallecotizacion_ibfk_1` FOREIGN KEY (`idProducto`) REFERENCES `producto` (`idProducto`),
  CONSTRAINT `detallecotizacion_ibfk_2` FOREIGN KEY (`idDetalleCotizacion`) REFERENCES `cotizacion` (`idCotización`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detallecotizacion`
--

LOCK TABLES `detallecotizacion` WRITE;
/*!40000 ALTER TABLE `detallecotizacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `detallecotizacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalleventa`
--

DROP TABLE IF EXISTS `detalleventa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalleventa` (
  `idDetalleVenta` int NOT NULL AUTO_INCREMENT,
  `idProducto` int NOT NULL,
  `idVenta` int NOT NULL,
  `cantidadProducto` int NOT NULL,
  `precioProducto` double NOT NULL,
  PRIMARY KEY (`idDetalleVenta`),
  UNIQUE KEY `idDetalleVenta` (`idDetalleVenta`),
  KEY `idVenta` (`idVenta`),
  KEY `idProducto` (`idProducto`),
  CONSTRAINT `detalleventa_ibfk_1` FOREIGN KEY (`idVenta`) REFERENCES `venta` (`idVenta`),
  CONSTRAINT `detalleventa_ibfk_2` FOREIGN KEY (`idProducto`) REFERENCES `producto` (`idProducto`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalleventa`
--

LOCK TABLES `detalleventa` WRITE;
/*!40000 ALTER TABLE `detalleventa` DISABLE KEYS */;
INSERT INTO `detalleventa` VALUES (1,1,1,1,30),(2,1,2,50,30),(5,2,5,3,10),(6,1,5,4,30),(7,2,6,1,10),(8,2,7,5,10),(9,2,16,1,10),(10,2,17,4,10),(11,3,18,7,50),(12,2,19,1,10);
/*!40000 ALTER TABLE `detalleventa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingresoproducto`
--

DROP TABLE IF EXISTS `ingresoproducto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingresoproducto` (
  `idIngreso` int NOT NULL AUTO_INCREMENT,
  `idProducto` int NOT NULL,
  `idTrabajador` int NOT NULL,
  `cantidadIngreso` int NOT NULL,
  `fechaIngreso` date NOT NULL,
  PRIMARY KEY (`idIngreso`),
  UNIQUE KEY `idIngreso` (`idIngreso`),
  KEY `idTrabajador` (`idTrabajador`),
  KEY `idProducto` (`idProducto`),
  CONSTRAINT `ingresoproducto_ibfk_1` FOREIGN KEY (`idTrabajador`) REFERENCES `trabajador` (`idTrabajador`),
  CONSTRAINT `ingresoproducto_ibfk_2` FOREIGN KEY (`idProducto`) REFERENCES `producto` (`idProducto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingresoproducto`
--

LOCK TABLES `ingresoproducto` WRITE;
/*!40000 ALTER TABLE `ingresoproducto` DISABLE KEYS */;
/*!40000 ALTER TABLE `ingresoproducto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `idProducto` int NOT NULL AUTO_INCREMENT,
  `idCategoria` int NOT NULL,
  `nombreProducto` varchar(255) NOT NULL,
  `descripcionProducto` varchar(255) NOT NULL,
  `precioProducto` double NOT NULL,
  `cantidadProducto` int NOT NULL,
  `estadoProducto` tinyint NOT NULL,
  PRIMARY KEY (`idProducto`),
  UNIQUE KEY `idProducto` (`idProducto`),
  KEY `idCategoria` (`idCategoria`),
  CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`idCategoria`) REFERENCES `categoria` (`idCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,1,'Bolsa de cemento','Bolsa de cemento 50 kg marca peruana',30,49,1),(2,1,'Ladrillos','Ladrillos king kong',10,188,1),(3,2,'Ceramica de prueba','Esto es una prueba',50,93,1),(4,2,'Ceramica de piso','Ceramica de piso 40x40',80,200,1);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipotrabajador`
--

DROP TABLE IF EXISTS `tipotrabajador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipotrabajador` (
  `idTipoTrabajador` int NOT NULL AUTO_INCREMENT,
  `descripcionTipoTrabajador` varchar(255) NOT NULL,
  PRIMARY KEY (`idTipoTrabajador`),
  UNIQUE KEY `idTipoTrabajador` (`idTipoTrabajador`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipotrabajador`
--

LOCK TABLES `tipotrabajador` WRITE;
/*!40000 ALTER TABLE `tipotrabajador` DISABLE KEYS */;
INSERT INTO `tipotrabajador` VALUES (1,'TI'),(2,'Administrador'),(3,'Trabajador');
/*!40000 ALTER TABLE `tipotrabajador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trabajador`
--

DROP TABLE IF EXISTS `trabajador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trabajador` (
  `idTrabajador` int NOT NULL AUTO_INCREMENT,
  `idTipoTrabajador` int NOT NULL,
  `nombreTrabajador` varchar(255) NOT NULL,
  `apellidoTrabajador` varchar(255) NOT NULL,
  `usuarioTrabajador` varchar(255) NOT NULL,
  `contraseñaTrabajador` varchar(255) NOT NULL,
  `estadoTrabajador` tinyint(1) NOT NULL,
  PRIMARY KEY (`idTrabajador`),
  UNIQUE KEY `idTrabajador` (`idTrabajador`),
  KEY `idTipoTrabajador` (`idTipoTrabajador`),
  CONSTRAINT `trabajador_ibfk_1` FOREIGN KEY (`idTipoTrabajador`) REFERENCES `tipotrabajador` (`idTipoTrabajador`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trabajador`
--

LOCK TABLES `trabajador` WRITE;
/*!40000 ALTER TABLE `trabajador` DISABLE KEYS */;
INSERT INTO `trabajador` VALUES (2,1,'David','Castro','david','$2a$10$F5fipTN6HePSl5lSLmlPDuvU59gn1TJzejYDYng2x.5n3f3HRXRPu',0),(3,2,'Luis','Hume','LHume','$2a$10$hCMdVnjndHoSDeEqPlBb..pl7D1ordS0UmZWqo053lCB1nrQce3HK',0),(4,3,'Carlos','Camacho','CCamacho','$2a$10$nF5OCMYsOhl8z3nDGx1KZuqMimAPAYto.2/kWi9gYMudZF7umqYj6',0);
/*!40000 ALTER TABLE `trabajador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venta`
--

DROP TABLE IF EXISTS `venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venta` (
  `idVenta` int NOT NULL AUTO_INCREMENT,
  `idTrabajador` int NOT NULL,
  `fechaVenta` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `totalVenta` double NOT NULL,
  PRIMARY KEY (`idVenta`),
  UNIQUE KEY `idVenta` (`idVenta`),
  KEY `idTrabajador` (`idTrabajador`),
  CONSTRAINT `venta_ibfk_1` FOREIGN KEY (`idTrabajador`) REFERENCES `trabajador` (`idTrabajador`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venta`
--

LOCK TABLES `venta` WRITE;
/*!40000 ALTER TABLE `venta` DISABLE KEYS */;
INSERT INTO `venta` VALUES (1,3,'2025-10-10 21:43:01',30),(2,3,'2025-10-11 16:45:32',1500),(5,3,'2025-10-16 04:41:58',150),(6,3,'2025-10-16 04:55:17',10),(7,3,'2025-10-16 04:59:43',50),(16,3,'2025-10-17 17:08:21',10),(17,3,'2025-10-17 17:18:19',40),(18,3,'2025-10-19 03:40:59',350),(19,3,'2025-10-19 04:37:08',10);
/*!40000 ALTER TABLE `venta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-20 18:58:19
