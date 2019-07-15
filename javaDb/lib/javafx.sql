-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jul 15, 2019 at 01:10 PM
-- Server version: 5.1.53
-- PHP Version: 5.3.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `javafx`
--

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE IF NOT EXISTS `student` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=50 ;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`id`, `name`, `surname`, `email`) VALUES
(40, 'GH', 'ngj', 'KH'),
(43, 'GH', 'GHGN', 'YKJ'),
(44, '9LO', '7OY', 'IL'),
(46, 'jgjgjn', 'dtrgherte', 'yahoooo'),
(47, '?JHYFK6TU', '/MP.?.IL', 'UI/LYO/MYIO'),
(48, 'tyuh6th', 'ththbt', 'ththyhjy'),
(49, 'gfhbtgf', 'ghbt', 'hjy6th');
