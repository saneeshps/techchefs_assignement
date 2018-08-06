-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Aug 06, 2018 at 05:02 AM
-- Server version: 5.7.21
-- PHP Version: 5.6.35

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `techchefs`
--

-- --------------------------------------------------------

--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
CREATE TABLE IF NOT EXISTS `accounts` (
  `account_number` int(11) NOT NULL AUTO_INCREMENT,
  `account_type` varchar(50) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `bank_id` int(11) NOT NULL,
  PRIMARY KEY (`account_number`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `accounts`
--

INSERT INTO `accounts` (`account_number`, `account_type`, `customer_id`, `bank_id`) VALUES
(1, 'Savings', 1, 1),
(2, 'Savings', 2, 1),
(3, 'Current', 1, 2),
(4, 'Current', 3, 1),
(5, 'Current', 6, 5),
(6, 'Savings', 5, 2);

-- --------------------------------------------------------

--
-- Table structure for table `banks`
--

DROP TABLE IF EXISTS `banks`;
CREATE TABLE IF NOT EXISTS `banks` (
  `bank_id` int(11) NOT NULL AUTO_INCREMENT,
  `bankname` varchar(255) NOT NULL,
  `corpid` varchar(255) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `corprid` varchar(255) NOT NULL,
  PRIMARY KEY (`bank_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `banks`
--

INSERT INTO `banks` (`bank_id`, `bankname`, `corpid`, `username`, `password`, `corprid`) VALUES
(1, 'SBI', 'SBI001', 'sbi', 'admin', ''),
(2, 'HDFC', 'HDFC001', 'hdfc', 'admin', ''),
(3, 'ICICI', 'ICICI0001', 'icici', 'admin', ''),
(4, 'canara', 'CANARA0001', 'canara', 'admin', ''),
(5, 'bob', 'BOB0001', 'bob', 'admin', '');

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
CREATE TABLE IF NOT EXISTS `transactions` (
  `transaction_id` int(11) NOT NULL AUTO_INCREMENT,
  `account_number` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `transaction_type` varchar(10) NOT NULL,
  `time` timestamp NOT NULL,
  PRIMARY KEY (`transaction_id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`transaction_id`, `account_number`, `amount`, `transaction_type`, `time`) VALUES
(1, 1, 1000, 'Credit', '2018-08-05 04:49:20'),
(2, 2, 5000, 'Debit', '2018-08-04 23:49:20'),
(3, 3, 10000, 'Credit', '2018-08-04 23:49:20'),
(4, 1, 5000, 'Debit', '2018-08-04 23:49:20'),
(5, 3, 1000, 'Credit', '2018-08-04 23:30:00'),
(6, 2, 5000, 'Credit', '2018-08-04 23:49:20'),
(7, 7, 3000, 'Credit', '2018-08-04 23:49:20'),
(8, 4, 10000, 'Credit', '2018-08-04 23:49:20'),
(9, 1, 2500, 'Credit', '2018-08-05 06:30:00'),
(10, 1, 5000, 'Debit', '2018-08-04 19:30:00'),
(11, 2, 2500, 'Credit', '2018-08-04 23:30:00');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`customer_id`, `username`, `password`) VALUES
(1, 'suraj', '54321'),
(2, 'saneesh', '54321'),
(3, 'basil', '12345'),
(4, 'sudhin', '11111'),
(5, 'jyothish', '12345'),
(6, 'sandeep', '00000'),
(7, 'joffin', '12345');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
