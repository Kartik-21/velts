-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Apr 01, 2020 at 04:19 PM
-- Server version: 5.6.41-84.1-log
-- PHP Version: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `skinfoso_vehiclerto`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_city`
--

CREATE TABLE `tbl_city` (
  `City_ID` int(11) NOT NULL,
  `City_Name` varchar(30) NOT NULL,
  `District_ID` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_city`
--

INSERT INTO `tbl_city` (`City_ID`, `City_Name`, `District_ID`) VALUES
(1, 'Vadodara', '1'),
(2, 'Padra', '1'),
(3, 'Gotri', '1'),
(4, 'Waghodia', '1');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_designation`
--

CREATE TABLE `tbl_designation` (
  `Designation_ID` int(11) NOT NULL,
  `Designation_Name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_designation`
--

INSERT INTO `tbl_designation` (`Designation_ID`, `Designation_Name`) VALUES
(1, 'Inspector');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_district`
--

CREATE TABLE `tbl_district` (
  `District_ID` int(11) NOT NULL,
  `District_Name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_district`
--

INSERT INTO `tbl_district` (`District_ID`, `District_Name`) VALUES
(1, 'Vadodara');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_employee`
--

CREATE TABLE `tbl_employee` (
  `Employee_ID` int(11) NOT NULL,
  `Name` varchar(30) NOT NULL,
  `District_ID` varchar(5) NOT NULL,
  `City_ID` varchar(5) NOT NULL,
  `Address` varchar(50) NOT NULL,
  `Mobile` varchar(10) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `Designation` varchar(5) NOT NULL,
  `Password` varchar(15) NOT NULL,
  `Status` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_employee`
--

INSERT INTO `tbl_employee` (`Employee_ID`, `Name`, `District_ID`, `City_ID`, `Address`, `Mobile`, `Email`, `Designation`, `Password`, `Status`) VALUES
(1, 'Sunil Chauhan', '1', '1', 'Near Gotri Talav', '9823457800', 'sunil@gmail.com', '1', '1234', 'Active');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_llr`
--

CREATE TABLE `tbl_llr` (
  `LLR_ID` int(11) NOT NULL,
  `FName` varchar(20) NOT NULL,
  `LName` varchar(20) NOT NULL,
  `Aadhar` varchar(12) NOT NULL,
  `BDate` varchar(10) NOT NULL,
  `District_ID` varchar(5) NOT NULL,
  `City_ID` varchar(5) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `Pincode` varchar(8) NOT NULL,
  `VType` varchar(30) NOT NULL,
  `BGroup` varchar(30) NOT NULL,
  `User_ID` varchar(5) NOT NULL,
  `Status` varchar(15) NOT NULL,
  `Apply_Date` varchar(15) NOT NULL,
  `Result` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_llr`
--

INSERT INTO `tbl_llr` (`LLR_ID`, `FName`, `LName`, `Aadhar`, `BDate`, `District_ID`, `City_ID`, `Address`, `Pincode`, `VType`, `BGroup`, `User_ID`, `Status`, `Apply_Date`, `Result`) VALUES
(1, 'umed', 'patel', '252525252552', '1/1/1996', '1', '2', 'nava bazar', '390004', 'LMV-NT-CAR-(LMV)', 'B Positive', '1', 'approve', '2018-05-10 11:5', ''),
(2, 'oo1', 'oo2', '1212121212', '11/10/2010', '1', '1', 'kb', '390006', 'LMV -3 WHEELER NT', 'A Negative', '2', 'Pending', '2019-10-01 12:5', ''),
(3, 'janak', 'patel', '11223344577', '1/1/190', '1', '3', 'kb', '390007', 'LMV -3 WHEELER CAB', 'AB Positive', '13', 'Pending', '2019-10-05 17:4', ''),
(4, 'jd', 'dj', '665533225666', '1/1/1990', '1', '3', 'kb', '390006', 'LMV -3 WHEELER NT', 'B Positive', '14', 'Pending', '2019-10-07 13:2', ''),
(5, 'ok', 'ok', '121212121212', '1/1/2019', '1', '3', 'kb', '390006', 'LMV -3 WHEELER NT', 'A Negative', '16', 'Pending', '2019-10-10 13:0', ''),
(6, 'tinu', 'patel', '123456123456', '11/11/1990', '1', '3', 'kv', '666666', 'LMV -3 WHEELER CAB', 'AB Positive', '17', 'Pending', '2019-11-06 06:3', '');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_payment`
--

CREATE TABLE `tbl_payment` (
  `Payment_ID` int(11) NOT NULL,
  `User_ID` varchar(5) NOT NULL,
  `Amount` varchar(15) NOT NULL,
  `Date` varchar(10) NOT NULL,
  `Time` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_payment`
--

INSERT INTO `tbl_payment` (`Payment_ID`, `User_ID`, `Amount`, `Date`, `Time`) VALUES
(5, '16', '5000', '07/10/2019', '05:35'),
(6, '16', '1000', '10/10/2019', '04:36'),
(7, '17', '5000', '06/11/2019', '11:09'),
(8, '18', '5000', '22/12/2019', '09:51'),
(9, '22', '900', '26/02/2020', '10:48'),
(10, '22', '580', '26/02/2020', '10:16'),
(11, '21', '5000', '09/03/2020', '05:10'),
(12, '23', '12000', '09/03/2020', '05:23'),
(13, '24', '100', '12/03/2020', '10:28'),
(14, '24', '500', '12/03/2020', '10:50'),
(15, '24', '500', '12/03/2020', '10:51'),
(16, '26', '10000000000000', '12/03/2020', '05:30');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_penalty`
--

CREATE TABLE `tbl_penalty` (
  `P_ID` int(11) NOT NULL,
  `Section` varchar(200) NOT NULL,
  `Offence` varchar(200) NOT NULL,
  `Fine` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_penalty`
--

INSERT INTO `tbl_penalty` (`P_ID`, `Section`, `Offence`, `Fine`) VALUES
(1, '180', 'Drive a vehicle without a driving licence', '300'),
(2, '180', 'Drive a vehicle without a transport vehicle authorization', '150'),
(3, '180', 'Drive a vehicle when he is underage', '300'),
(4, '183 1', 'Driving at excessive speed', '300'),
(5, '184', 'Driving at a speed dangerous to the public', '350'),
(6, '186', 'Driving when mentally or physically unfit to drive	', '150'),
(7, '192', 'PUC', '200'),
(13, '420', 'theaft', '3000');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_register`
--

CREATE TABLE `tbl_register` (
  `User_ID` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `District_ID` varchar(10) NOT NULL,
  `City_ID` varchar(10) NOT NULL,
  `Address` varchar(200) NOT NULL,
  `Aadhar` varchar(16) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Mobile` varchar(10) NOT NULL,
  `Password` varchar(16) NOT NULL,
  `Wallet` varchar(15) NOT NULL,
  `vehicalno` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_register`
--

INSERT INTO `tbl_register` (`User_ID`, `Name`, `District_ID`, `City_ID`, `Address`, `Aadhar`, `Email`, `Mobile`, `Password`, `Wallet`, `vehicalno`) VALUES
(16, 'pol', '1', '4', 'kb', '123456123456', 'pol@g.com', '9825397667', '123', '5700', ''),
(17, 'ramesh', '1', '2', 'karelibaug', '123451234512', 'ra@g.com', '1234512345', '1', '2900', ''),
(18, 'abc', '1', '3', 'xyz', '135557566555', 'abc@gmail.com', '682555585', '1234', '5000', ''),
(19, 'abcd', '1', '4', 'xyz', '1234567891234', 'abcd@gmail.com', '1234567890', '1234', '0', ''),
(20, 'janak', '1', '2', 'kv', '123455666', 'jn@g.com', '4433443344', '1', '0', ''),
(21, 'Pqr', '1', '1', 'Xyz', '1234567890', 'pqr@gmail.com', '1234567800', '1234', '5000', ''),
(22, 'vinayak Inamdar', '1', '1', 'anything', '12345678', 'vpi261198@gmail.com', '7600993317', '1234', '1480', ''),
(23, 'piyu', '1', '1', 'kb', '111111111111', 'piyu@gmail.com', '9825398251', '1', '11650', 'gj23ad 7521'),
(24, 'Kartik jain', '1', '4', 'Vadodara', '98765432189', 'Kartikjain2926@gmail.com', '9601884165', '1234', '1100', 'GJ 06 MK 0848'),
(25, 'Jk', '1', '1', 'Vadodara', 'Hhsjeej', 'Jkp.2926@gmail.com', '9157118211', '1234', '0', 'Gj20jk2020'),
(26, 'Madarchod@gmail.com', '1', '3', 'Vadhare and I are now having to go back', '57273579295233', 'Madarchod@gmail.com', '9723920124', '1234@1234', '10000000000000', '0972');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_uploads`
--

CREATE TABLE `tbl_uploads` (
  `Upload_ID` int(11) NOT NULL,
  `User_ID` varchar(5) NOT NULL,
  `UType` varchar(20) NOT NULL,
  `Title` varchar(50) NOT NULL,
  `Image` varchar(200) NOT NULL,
  `Status` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_uploads`
--

INSERT INTO `tbl_uploads` (`Upload_ID`, `User_ID`, `UType`, `Title`, `Image`, `Status`) VALUES
(52, '23', 'Aadhar Card', 'my adhar', 'uploads/Documents/IMG-20180508-WA0006.jpg', ''),
(53, '25', 'Aadhar Card', 'ac my', 'uploads/Documents/IMG-20180508-WA0012.jpg', ''),
(54, '25', 'RC Book', 'rc book', 'uploads/Documents/IMG-20180508-WA0011.jpg', ''),
(55, '1', 'Driving Licence', 'my licence', 'uploads/Documents/IMG-20180510-WA0026.jpg', ''),
(56, '2', 'Aadhar Card', 'adhar card', 'uploads/Documents/Screenshot_2019-08-28-09-53-04.png', ''),
(58, '16', 'Aadhar Card', 'ac', 'uploads/Documents/2.png', ''),
(61, '17', 'Aadhar Card', 'ok', 'uploads/Documents/1554122652969.jpg', ''),
(62, '17', 'Driving Licence', 'dl', 'uploads/Documents/IMG_20191029_154101.jpg', ''),
(63, '17', 'Aadhar Card', 'xbxj', 'uploads/Documents/Screenshot_20191201_071828.jpg', ''),
(64, '17', 'RC Book', 'even book', 'uploads/Documents/IMG_20191207_221554.jpg', ''),
(65, '18', 'Aadhar Card', 'Akshar no', 'uploads/Documents/IMG_20191213_095246.jpg', ''),
(67, '21', 'Driving Licence', 'D', 'uploads/Documents/IMG-20191223-WA0001.jpg', ''),
(70, '23', 'Aadhar Card', 'adhar', 'uploads/Documents/myntra_product.jpg', ''),
(71, '23', 'RC Book', 'myimg', 'uploads/Documents/IMG-20200307-WA0005.jpg', ''),
(72, '24', 'Aadhar Card', 'Kk', 'uploads/Documents/IMG-20200312-WA0003.jpg', ''),
(73, '24', 'Driving Licence', 'Kk', 'uploads/Documents/IMG-20200312-WA0001.jpg', ''),
(75, '26', 'RC Book', 'Book', 'uploads/Documents/IMG_20200312_110814.jpg', ''),
(77, '22', 'Aadhar Card', 'myaadhar', 'uploads/Documents/20200314_170252.jpg', '');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_user_penalty`
--

CREATE TABLE `tbl_user_penalty` (
  `UP_ID` int(11) NOT NULL,
  `User_ID` varchar(5) NOT NULL,
  `RID` varchar(5) NOT NULL,
  `PID` varchar(5) NOT NULL,
  `Fine` varchar(15) NOT NULL,
  `Date` varchar(12) NOT NULL,
  `Status` varchar(12) NOT NULL,
  `Payment_Date` varchar(10) NOT NULL,
  `Time` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_user_penalty`
--

INSERT INTO `tbl_user_penalty` (`UP_ID`, `User_ID`, `RID`, `PID`, `Fine`, `Date`, `Status`, `Payment_Date`, `Time`) VALUES
(77, '16', '1', '1', '300', '07/10/2019', 'Pending', '', ''),
(78, '16', '1', '3', '300', '07/10/2019', 'Pending', '', ''),
(79, '17', '1', '2', '150', '06/11/2019', 'Paid', '20/12/2019', '09:20'),
(80, '17', '1', '3', '300', '06/11/2019', 'Paid', '20/12/2019', '09:20'),
(81, '17', '1', '1', '300', '20/12/2019', 'Paid', '20/12/2019', '09:23'),
(82, '17', '1', '3', '300', '20/12/2019', 'Paid', '20/12/2019', '09:23'),
(83, '17', '1', '6', '150', '20/12/2019', 'Paid', '20/12/2019', '09:23'),
(84, '17', '1', '4', '300', '20/12/2019', 'Pending', '', ''),
(85, '16', '1', '4', '300', '20/12/2019', 'Paid', '20/12/2019', '12:54'),
(86, '19', '1', '4', '300', '22/12/2019', 'Paid', '22/12/2019', '09:56'),
(87, '23', '1', '5', '350', '09/03/2020', 'Paid', '09/03/2020', '05:33'),
(88, '24', '1', '1', '300', '12/03/2020', 'Paid', '12/03/2020', '10:35'),
(89, '24', '1', '4', '300', '12/03/2020', 'Paid', '12/03/2020', '10:35'),
(90, '24', '1', '7', '200', '12/03/2020', 'Paid', '12/03/2020', '10:35'),
(91, '17', '1', '3', '300', '14/03/2020', 'Paid', '14/03/2020', '05:03');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_city`
--
ALTER TABLE `tbl_city`
  ADD PRIMARY KEY (`City_ID`);

--
-- Indexes for table `tbl_designation`
--
ALTER TABLE `tbl_designation`
  ADD PRIMARY KEY (`Designation_ID`);

--
-- Indexes for table `tbl_district`
--
ALTER TABLE `tbl_district`
  ADD PRIMARY KEY (`District_ID`);

--
-- Indexes for table `tbl_employee`
--
ALTER TABLE `tbl_employee`
  ADD PRIMARY KEY (`Employee_ID`);

--
-- Indexes for table `tbl_llr`
--
ALTER TABLE `tbl_llr`
  ADD PRIMARY KEY (`LLR_ID`);

--
-- Indexes for table `tbl_payment`
--
ALTER TABLE `tbl_payment`
  ADD PRIMARY KEY (`Payment_ID`);

--
-- Indexes for table `tbl_penalty`
--
ALTER TABLE `tbl_penalty`
  ADD PRIMARY KEY (`P_ID`);

--
-- Indexes for table `tbl_register`
--
ALTER TABLE `tbl_register`
  ADD PRIMARY KEY (`User_ID`);

--
-- Indexes for table `tbl_uploads`
--
ALTER TABLE `tbl_uploads`
  ADD PRIMARY KEY (`Upload_ID`);

--
-- Indexes for table `tbl_user_penalty`
--
ALTER TABLE `tbl_user_penalty`
  ADD PRIMARY KEY (`UP_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_city`
--
ALTER TABLE `tbl_city`
  MODIFY `City_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tbl_designation`
--
ALTER TABLE `tbl_designation`
  MODIFY `Designation_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tbl_district`
--
ALTER TABLE `tbl_district`
  MODIFY `District_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tbl_employee`
--
ALTER TABLE `tbl_employee`
  MODIFY `Employee_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tbl_llr`
--
ALTER TABLE `tbl_llr`
  MODIFY `LLR_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `tbl_payment`
--
ALTER TABLE `tbl_payment`
  MODIFY `Payment_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `tbl_penalty`
--
ALTER TABLE `tbl_penalty`
  MODIFY `P_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `tbl_register`
--
ALTER TABLE `tbl_register`
  MODIFY `User_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `tbl_uploads`
--
ALTER TABLE `tbl_uploads`
  MODIFY `Upload_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=78;

--
-- AUTO_INCREMENT for table `tbl_user_penalty`
--
ALTER TABLE `tbl_user_penalty`
  MODIFY `UP_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=92;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
