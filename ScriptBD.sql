CREATE DATABASE  IF NOT EXISTS `clinicamedica` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `clinicamedica`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: clinicamedica
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Table structure for table `direccionesmedicos`
--

DROP TABLE IF EXISTS `direccionesmedicos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `direccionesmedicos` (
  `IDDireccion` int(11) NOT NULL AUTO_INCREMENT,
  `DNI` int(11) NOT NULL,
  `Calle` varchar(45) NOT NULL,
  `Numero` int(11) NOT NULL,
  `IDLocalidad` int(11) NOT NULL,
  PRIMARY KEY (`IDDireccion`),
  UNIQUE KEY `DNI_UNIQUE` (`DNI`),
  KEY `FK_DNIMedico_idx` (`DNI`),
  KEY `FL_IDLocalidad_idx` (`IDLocalidad`),
  CONSTRAINT `FK_DNIMedico` FOREIGN KEY (`DNI`) REFERENCES `medicos` (`DNI`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FL_IDLocalidad` FOREIGN KEY (`IDLocalidad`) REFERENCES `localidades` (`IDLocalidad`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `direccionespacientes`
--

DROP TABLE IF EXISTS `direccionespacientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `direccionespacientes` (
  `IDDireccion` int(11) NOT NULL AUTO_INCREMENT,
  `DNI` int(11) NOT NULL,
  `Calle` varchar(45) NOT NULL,
  `Numero` int(11) NOT NULL,
  `IDLocalidad` int(11) NOT NULL,
  PRIMARY KEY (`IDDireccion`),
  UNIQUE KEY `DNI_UNIQUE` (`DNI`),
  KEY `FK_DNIPaciente_idx` (`DNI`),
  KEY `FK_Localidad` (`IDLocalidad`),
  CONSTRAINT `FK_DNIPaciente` FOREIGN KEY (`DNI`) REFERENCES `pacientes` (`DNI`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Localidad` FOREIGN KEY (`IDLocalidad`) REFERENCES `localidades` (`IDLocalidad`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `especialidades`
--

DROP TABLE IF EXISTS `especialidades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `especialidades` (
  `IDEspecialidad` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`IDEspecialidad`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `estados`
--

DROP TABLE IF EXISTS `estados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estados` (
  `IDEstado` int(11) NOT NULL,
  `Nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`IDEstado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `horariosxmedicos`
--

DROP TABLE IF EXISTS `horariosxmedicos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `horariosxmedicos` (
  `idHorario` int(11) NOT NULL AUTO_INCREMENT,
  `DNIMedico` int(11) NOT NULL,
  `HoraInicio` int(11) NOT NULL,
  `HoraFin` int(11) NOT NULL,
  `DiaAtencion` varchar(10) NOT NULL,
  `Estado` tinyint(1) NOT NULL,
  PRIMARY KEY (`idHorario`),
  KEY `DniMedicos_idx` (`DNIMedico`),
  CONSTRAINT `DniMedicos` FOREIGN KEY (`DNIMedico`) REFERENCES `medicos` (`DNI`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `localidades`
--

DROP TABLE IF EXISTS `localidades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `localidades` (
  `IDLocalidad` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `IDProvincia` int(11) NOT NULL,
  PRIMARY KEY (`IDLocalidad`),
  KEY `IDProvincia_idx` (`IDProvincia`),
  CONSTRAINT `IDProvincia` FOREIGN KEY (`IDProvincia`) REFERENCES `provincias` (`IDProvincia`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `medicos`
--

DROP TABLE IF EXISTS `medicos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medicos` (
  `DNI` int(11) NOT NULL,
  `Apellido` varchar(45) NOT NULL,
  `Nombres` varchar(45) NOT NULL,
  `Sexo` char(1) NOT NULL,
  `FechaNacimiento` date NOT NULL,
  `Nacionalidad` varchar(45) NOT NULL,
  `Mail` varchar(45) NOT NULL,
  `Telefono` varchar(45) NOT NULL,
  `IDEspecialidad` int(11) NOT NULL,
  `Estado` tinyint(1) NOT NULL,
  PRIMARY KEY (`DNI`),
  KEY `IDEspecialidad_idx` (`IDEspecialidad`),
  CONSTRAINT `DNIAUsuarios` FOREIGN KEY (`DNI`) REFERENCES `usuarios` (`DNI`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `IDEspecialidad` FOREIGN KEY (`IDEspecialidad`) REFERENCES `especialidades` (`IDEspecialidad`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pacientes`
--

DROP TABLE IF EXISTS `pacientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pacientes` (
  `DNI` int(11) NOT NULL,
  `Apellido` varchar(45) NOT NULL,
  `Nombres` varchar(45) NOT NULL,
  `Sexo` char(1) DEFAULT NULL,
  `Nacionalidad` varchar(45) NOT NULL,
  `FechaNacimiento` date NOT NULL,
  `Mail` varchar(45) NOT NULL,
  `Telefono` varchar(45) NOT NULL,
  `Estado` tinyint(1) NOT NULL,
  PRIMARY KEY (`DNI`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `provincias`
--

DROP TABLE IF EXISTS `provincias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `provincias` (
  `IDProvincia` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`IDProvincia`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tiposusuario`
--

DROP TABLE IF EXISTS `tiposusuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tiposusuario` (
  `IDTipo` int(11) NOT NULL,
  `Descripcion` varchar(45) NOT NULL,
  PRIMARY KEY (`IDTipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `turnos`
--

DROP TABLE IF EXISTS `turnos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `turnos` (
  `IDTurno` int(11) NOT NULL AUTO_INCREMENT,
  `Fecha` date NOT NULL,
  `Hora` int(11) NOT NULL,
  `DNIMedico` int(11) NOT NULL,
  `DNIPaciente` int(11) DEFAULT NULL,
  `IDEstado` int(11) NOT NULL,
  `Observacion` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`IDTurno`,`Fecha`,`Hora`,`DNIMedico`),
  KEY `IDEstados_idx` (`IDEstado`),
  KEY `FK_DNI_Medicos_idx` (`DNIMedico`),
  KEY `FK_DNI_Pacientes_idx` (`DNIPaciente`),
  CONSTRAINT `FK_DNI_Medicos` FOREIGN KEY (`DNIMedico`) REFERENCES `medicos` (`DNI`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_DNI_Pacientes` FOREIGN KEY (`DNIPaciente`) REFERENCES `pacientes` (`DNI`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `IDEstados` FOREIGN KEY (`IDEstado`) REFERENCES `estados` (`IDEstado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `DNI` int(11) NOT NULL,
  `IDTipoUsuario` int(11) NOT NULL,
  `Contraseña` varchar(10) NOT NULL,
  `Estado` tinyint(1) NOT NULL,
  PRIMARY KEY (`DNI`),
  KEY `TipoUsuario_idx` (`IDTipoUsuario`),
  CONSTRAINT `TipoUsuario` FOREIGN KEY (`IDTipoUsuario`) REFERENCES `tiposusuario` (`IDTipo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-12 21:29:41
