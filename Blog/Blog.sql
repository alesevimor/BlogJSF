-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 18-02-2015 a las 04:51:58
-- Versión del servidor: 5.5.41
-- Versión de PHP: 5.3.10-1ubuntu3.15

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `Blog`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Articulos`
--

CREATE TABLE IF NOT EXISTS `Articulos` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Titulo` varchar(60) COLLATE utf8_spanish2_ci NOT NULL,
  `Contenido` longtext COLLATE utf8_spanish2_ci NOT NULL,
  `Fecha` date NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci AUTO_INCREMENT=3 ;

--
-- Volcado de datos para la tabla `Articulos`
--

INSERT INTO `Articulos` (`Id`, `Titulo`, `Contenido`, `Fecha`) VALUES
(1, 'Primer Titulo', 'Primer Contenido', '2015-02-18'),
(2, 'Segundo Titulo', 'Segundo Contenido<br>', '2015-02-18');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Autores`
--

CREATE TABLE IF NOT EXISTS `Autores` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(40) COLLATE utf8_spanish2_ci NOT NULL,
  `Apellidos` varchar(60) COLLATE utf8_spanish2_ci NOT NULL,
  `Usuario` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  `Contraseña` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci AUTO_INCREMENT=12 ;

--
-- Volcado de datos para la tabla `Autores`
--

INSERT INTO `Autores` (`Id`, `Nombre`, `Apellidos`, `Usuario`, `Contraseña`) VALUES
(1, 'Alejandro', 'Sevillano Moreno', 'alejandros', 'alejandros'),
(2, 'Carlos', 'Sevillano Moreno', 'carlosm', 'carlosm'),
(3, 'Manuel', 'Vera Luqe', 'manuelvl', 'manuelvl'),
(4, 'Antonio', 'Lopez Montesinos', 'antoniolm', 'antoniolm'),
(5, 'Paulino', 'Huerta Sanchez', 'paulino', 'paulino'),
(6, 'Antonio', 'Lopez Montesinos', 'antoniolm', 'antoniolm'),
(7, 'Paulino', 'Huerta Sanchez', 'paulino', 'paulino'),
(8, 'Juan', 'Lopez Montesinos', 'juanlm', 'juanlm');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
