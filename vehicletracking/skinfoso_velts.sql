-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 15, 2020 at 04:51 PM
-- Server version: 5.6.16
-- PHP Version: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `skinfoso_velts`
--

-- --------------------------------------------------------

--
-- Table structure for table `mav`
--

CREATE TABLE IF NOT EXISTS `mav` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `acc` varchar(120) NOT NULL,
  `acl` varchar(120) NOT NULL,
  `vib` varchar(120) NOT NULL,
  `createdat` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `currtime` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=32 ;

--
-- Dumping data for table `mav`
--

INSERT INTO `mav` (`id`, `acc`, `acl`, `vib`, `createdat`, `currtime`) VALUES
(14, 'NO', 'YES', 'NO', '2020-02-28 05:00:12', ''),
(15, 'NO', 'NO', 'YES', '2020-02-28 05:01:16', ''),
(16, 'YES', 'NO', 'NO', '2020-02-28 05:05:37', ''),
(17, 'NO', 'NO', 'YES', '2020-03-09 03:55:27', '9:25 am'),
(18, 'NO', 'NO', 'YES', '2020-03-09 03:55:45', '9:25 am'),
(19, 'NO', 'NO', 'YES', '2020-03-09 03:56:08', '9:26 am'),
(20, 'NO', 'NO', 'YES', '2020-03-09 03:56:27', '9:26 am'),
(21, 'NO', 'NO', 'YES', '2020-03-09 03:56:48', '9:26 am'),
(22, 'YES', 'NO', 'NO', '2020-03-09 03:57:14', '9:27 am'),
(23, 'NO', 'NO', 'YES', '2020-03-09 03:57:53', '9:27 am'),
(24, 'NO', 'NO', 'YES', '2020-03-09 03:58:27', '9:28 am'),
(25, 'NO', 'YES', 'NO', '2020-03-09 03:59:11', '9:29 am'),
(26, 'NO', 'YES', 'NO', '2020-03-09 03:59:28', '9:29 am'),
(27, 'NO', 'YES', 'NO', '2020-03-09 03:59:52', '9:29 am'),
(28, 'YES', 'NO', 'NO', '2020-03-09 04:01:54', '9:31 am'),
(29, 'NO', 'YES', 'NO', '2020-03-09 04:02:22', '9:32 am'),
(30, 'YES', 'NO', 'NO', '2020-03-13 05:44:38', '11:14 am'),
(31, 'NO', 'NO', 'YES', '2020-03-13 05:45:29', '11:15 am');

-- --------------------------------------------------------

--
-- Table structure for table `plcc`
--

CREATE TABLE IF NOT EXISTS `plcc` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `lat` varchar(120) NOT NULL,
  `lon` varchar(120) NOT NULL,
  `pid` int(120) NOT NULL,
  `current` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `plcc`
--

INSERT INTO `plcc` (`id`, `lat`, `lon`, `pid`, `current`) VALUES
(1, '22.320882', '73.206320', 1, '2020-04-06 13:02:20');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
