-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 04, 2017 at 05:08 PM
-- Server version: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `call_doctor_api`
--

-- --------------------------------------------------------

--
-- Table structure for table `ambulance`
--

CREATE TABLE IF NOT EXISTS `ambulance` (
`id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `district_id` int(11) NOT NULL,
  `division_id` int(11) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `ambulance`
--

INSERT INTO `ambulance` (`id`, `name`, `phone`, `address`, `district_id`, `division_id`) VALUES
(1, 'Khulna Service', '01993905476', 'Khulna', 29, 3),
(2, 'akij ambulance', '0185246455434', 'dhaka', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `appoinments_table`
--

CREATE TABLE IF NOT EXISTS `appoinments_table` (
`id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `doc_id` int(11) NOT NULL,
  `date` varchar(255) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Dumping data for table `appoinments_table`
--

INSERT INTO `appoinments_table` (`id`, `user_id`, `doc_id`, `date`, `status`) VALUES
(1, 2, 1, '10-11-2017', 2),
(2, 2, 1, '28-10-2017', 1),
(3, 2, 1, '12-12-2017', 2),
(4, 2, 1, '12-12-2017', 1),
(5, 4, 1, '12-12-2017', 2),
(6, 4, 1, '12-12-2017', 0),
(7, 3, 9, '28-10-2017', 1),
(8, 2, 8, '22', 2),
(9, 2, 8, '22-10-2017', 1),
(10, 2, 8, '23', 1),
(11, 8, 15, '12-11-2017', 1),
(12, 9, 17, '2-11-2017', 1);

-- --------------------------------------------------------

--
-- Table structure for table `diagnostic_center`
--

CREATE TABLE IF NOT EXISTS `diagnostic_center` (
`id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `district_id` int(11) NOT NULL,
  `division_id` int(11) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `diagnostic_center`
--

INSERT INTO `diagnostic_center` (`id`, `name`, `phone`, `address`, `district_id`, `division_id`) VALUES
(1, 'Akter Chamber', '01675439876', 'Khulna', 29, 3),
(2, 'setu diagnostic center', '25645487', 'chittagong', 19, 2);

-- --------------------------------------------------------

--
-- Table structure for table `district`
--

CREATE TABLE IF NOT EXISTS `district` (
`id` int(11) NOT NULL,
  `division_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=65 ;

--
-- Dumping data for table `district`
--

INSERT INTO `district` (`id`, `division_id`, `name`) VALUES
(1, 1, 'Dhaka'),
(2, 1, 'Faridpur'),
(3, 1, 'Gazipur'),
(4, 1, 'Gopalganj'),
(5, 1, 'Kishoregonj'),
(6, 1, 'Madaripur'),
(7, 1, 'Manikganj'),
(8, 1, 'Munshiganj'),
(9, 1, 'Narayanganj'),
(10, 1, 'Narsingdi'),
(11, 1, 'Rajbari'),
(12, 1, 'Shariatpur'),
(13, 1, 'Tangail'),
(14, 2, 'Bandarban'),
(15, 2, 'Brahmanbaria'),
(16, 2, 'Chandpur'),
(17, 2, 'Chittagong'),
(18, 2, 'Comilla'),
(19, 2, 'Coxbazar'),
(20, 2, 'Feni'),
(21, 2, 'Khagrachhari'),
(22, 2, 'Lakshmipur'),
(23, 2, 'Noakhali'),
(24, 2, 'Rangamati'),
(25, 3, 'Bagerhat'),
(26, 3, 'Chuadanga'),
(27, 3, 'Jessore'),
(28, 3, 'Jhenaidah'),
(29, 3, 'Khulna'),
(30, 3, 'Kushtia'),
(31, 3, 'Magura'),
(32, 3, 'Meherpur'),
(33, 3, 'Narail'),
(34, 3, 'Satkhira'),
(35, 4, 'Barisal'),
(36, 4, 'Bhola'),
(37, 4, 'Jhalokati'),
(38, 4, 'Patuakhali'),
(39, 4, 'Pirojpur'),
(40, 4, 'Barguna'),
(41, 5, 'Mymensingh'),
(42, 5, 'Jamalpur'),
(43, 5, 'Netrakona'),
(44, 5, 'Sherpur'),
(45, 6, 'Sirajganj'),
(46, 6, 'Rajshahi'),
(47, 6, 'Natore'),
(48, 6, 'Naogaon'),
(49, 6, 'Pabna'),
(50, 6, 'Joypurhat'),
(51, 6, 'Chapainababganj'),
(52, 6, 'Bogra'),
(53, 7, 'Sylhet'),
(54, 7, 'Sunamganj'),
(55, 7, 'Maulavybazar'),
(56, 7, 'Habiganj'),
(57, 8, 'Thakurgaon'),
(58, 8, 'Rangpur'),
(59, 8, 'Panchagarh'),
(60, 8, 'Nilphamari'),
(61, 8, 'Lalmonirhat'),
(62, 8, 'Kurigram'),
(63, 8, 'Gaibandha'),
(64, 8, 'Dinajpur');

-- --------------------------------------------------------

--
-- Table structure for table `division`
--

CREATE TABLE IF NOT EXISTS `division` (
`id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `division`
--

INSERT INTO `division` (`id`, `name`) VALUES
(1, 'Dhaka'),
(2, 'Chittagong'),
(3, 'Khulna'),
(4, 'Barisal'),
(5, 'Mymenshing'),
(6, 'Rajshahi'),
(7, 'Sylhet'),
(8, 'Rangpur');

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE IF NOT EXISTS `doctor` (
`id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `degree` varchar(255) NOT NULL,
  `latitude` varchar(255) NOT NULL,
  `longitude` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `district_id` int(11) NOT NULL,
  `division_id` int(11) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=18 ;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` (`id`, `name`, `email`, `password`, `phone`, `degree`, `latitude`, `longitude`, `address`, `district_id`, `division_id`) VALUES
(1, 'Dr. Chayan', 'abc@gmail.com', '1234', '01734811761', 'MBBS', '22.808007', '89.565459', 'PTI, Khulna', 25, 3),
(2, 'Dr. Raju Halder', 'xyz@yahoo.com', '5678', '01833770044', 'MBBS, FCPS', '22.806026', '89.565505', 'Miyapara, Khulna', 23, 2),
(3, 'Somethinh', 'qwert@gmail.com', '1234', '0000222', 'MBBS', '33', '333', 'Dhaka', 23, 2),
(4, 'Dr. Pranto Mistry', 'pranto@gmail.com', '1234', '01734811762', 'MBBS, FCPS', '22.505050', '49.505050', 'Digraj, Mongla', 29, 3),
(5, 'Dr. Pranto Mistry', 'pranto@gmail.com', '1234', '01734811763', 'MBBS, FCPS', '22.505050', '49.505050', 'Digraj, Mongla', 29, 3),
(6, 'Dr. Pranto Mistry', 'pranto@gmail.com', '1234', '01734811764', 'MBBS, FCPS', '22.505050', '49.505050', 'Digraj, Mongla', 29, 3),
(7, 'fgg', 'fgg@yahoo.com', '1234', '017348117633', 'gg', '55', '55', 'hh', 25, 3),
(8, 'Dr. Farzana', 'farzna@ymail.com', '1234', '01734811766', 'MBBS', '55', '49', 'Mongla', 3, 1),
(9, 'Dr. Kamal', 'kamal@gmail.com', '1234', '01734811765', 'MBBS', '52', '60', 'Khulna', 29, 3),
(10, 'Dr. Alom', 'akhon@gmail.com', '1234', '01734811769', 'MBBS', '22', '45', 'Khulna', 29, 3),
(11, 'Dr. Pollob Gain', 'pollob@gmail.com', '1234', '01734811764', 'MBBS, FCPS', '22.505050', '49.505050', 'Digraj, Mongla', 29, 3),
(12, 'Unknown', 'unknown@ymail.com', '1234', '44', 'dd', '55', '44', 'dfj', 3, 1),
(17, 'Dr.S.M.Faisal Ahmed', 'dr.faisal@gmail.com', '12345', '01711397939', 'MBBS,FCPS', '22.81815', '89.574494', 'munshipara,khulna', 29, 3);

-- --------------------------------------------------------

--
-- Table structure for table `doctor_absent`
--

CREATE TABLE IF NOT EXISTS `doctor_absent` (
`id` int(11) NOT NULL,
  `doc_id` int(11) NOT NULL,
  `days` varchar(255) NOT NULL,
  `dates` int(11) NOT NULL,
  `range` varchar(255) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `doctor_absent`
--

INSERT INTO `doctor_absent` (`id`, `doc_id`, `days`, `dates`, `range`) VALUES
(1, 1, 'Saturday', 4, '12-25'),
(2, 2, 'Monday', 23, '12-14'),
(3, 12, 'Friday', 12, '10-12'),
(4, 8, 'Friday', 29, '10-20'),
(5, 15, 'Saturday', 10, '20-22'),
(6, 16, 'Friday', 10, '15-20'),
(7, 17, 'Friday', 6, '12-15');

-- --------------------------------------------------------

--
-- Table structure for table `doctor_specialist_relation`
--

CREATE TABLE IF NOT EXISTS `doctor_specialist_relation` (
`id` int(11) NOT NULL,
  `doctor_id` int(11) NOT NULL,
  `specialist_id` int(11) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=34 ;

--
-- Dumping data for table `doctor_specialist_relation`
--

INSERT INTO `doctor_specialist_relation` (`id`, `doctor_id`, `specialist_id`) VALUES
(1, 1, 1),
(2, 1, 3),
(3, 2, 11),
(4, 2, 15),
(5, 2, 3),
(6, 3, 2),
(7, 4, 4),
(8, 5, 1),
(9, 5, 2),
(10, 6, 1),
(11, 6, 2),
(12, 7, 2),
(13, 7, 4),
(14, 8, 2),
(15, 8, 4),
(16, 9, 11),
(17, 9, 13),
(18, 10, 1),
(19, 10, 3),
(20, 11, 1),
(21, 11, 2),
(22, 12, 1),
(23, 12, 2),
(24, 13, 5),
(25, 13, 7),
(26, 14, 5),
(27, 14, 7),
(28, 15, 5),
(29, 15, 7),
(30, 16, 5),
(31, 16, 7),
(32, 17, 5),
(33, 17, 7);

-- --------------------------------------------------------

--
-- Table structure for table `hospital`
--

CREATE TABLE IF NOT EXISTS `hospital` (
`id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `district_id` int(11) NOT NULL,
  `division_id` int(11) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `hospital`
--

INSERT INTO `hospital` (`id`, `name`, `phone`, `address`, `district_id`, `division_id`) VALUES
(1, 'Khulna Medical College', '01700567890', 'Sonadanga, khulna', 29, 3),
(2, 'Dhaka Medical College', '01793405060', 'Dhaka', 1, 1),
(3, 'Jessor medical collage and hospital', '725690', 'Jssore ', 3, 3),
(4, 'Rongpur medical collage and hospital', '852369', 'Rongpur', 6, 2),
(5, 'ferdousy hospital', '017118585555', 'kastomghat', 25, 3),
(6, 'Addin medical hospital', '01524345124', 'jessore.khulna', 27, 3);

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE IF NOT EXISTS `patient` (
`id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`id`, `name`, `email`, `password`) VALUES
(1, 'Kamal', 'kamal@yahoo.com', '1234'),
(2, 'Rahim', 'rahim@gmail.com', '1234'),
(3, 'Rabbi', 'rabbi@gmail.com', '5678'),
(4, 'Raju Halder', 'raju.nwu@gmail.com', '4321'),
(5, 'Nirpom', 'niru@yahoo.com', '5678'),
(6, 'Razz', 'razz@ymail.com', '7890'),
(7, 'Zahid', 'zahid22@gmail.com', '0000'),
(9, 'Mou', 'mou@gmail.com', 'mou');

-- --------------------------------------------------------

--
-- Table structure for table `specialist`
--

CREATE TABLE IF NOT EXISTS `specialist` (
`id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Dumping data for table `specialist`
--

INSERT INTO `specialist` (`id`, `name`) VALUES
(1, 'Urology'),
(2, 'Hematology'),
(3, 'Orthopaedics'),
(4, 'Gynaecology'),
(5, 'Respiratory'),
(6, 'Paediatrics'),
(7, 'Cardiology'),
(8, 'ENT'),
(9, 'Neurology'),
(10, 'Gastroenterology'),
(11, 'Eye'),
(12, 'Psychiatry'),
(13, 'Skin'),
(14, 'Hepatology'),
(15, 'Dental'),
(16, 'Oncology'),
(17, 'Rheumatology'),
(18, 'Nutritionist'),
(19, 'Dermatology'),
(20, 'Endocrinology');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ambulance`
--
ALTER TABLE `ambulance`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `appoinments_table`
--
ALTER TABLE `appoinments_table`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `diagnostic_center`
--
ALTER TABLE `diagnostic_center`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `district`
--
ALTER TABLE `district`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `division`
--
ALTER TABLE `division`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `doctor`
--
ALTER TABLE `doctor`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `doctor_absent`
--
ALTER TABLE `doctor_absent`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `doctor_specialist_relation`
--
ALTER TABLE `doctor_specialist_relation`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `hospital`
--
ALTER TABLE `hospital`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `specialist`
--
ALTER TABLE `specialist`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ambulance`
--
ALTER TABLE `ambulance`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `appoinments_table`
--
ALTER TABLE `appoinments_table`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `diagnostic_center`
--
ALTER TABLE `diagnostic_center`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `district`
--
ALTER TABLE `district`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=65;
--
-- AUTO_INCREMENT for table `division`
--
ALTER TABLE `division`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `doctor`
--
ALTER TABLE `doctor`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT for table `doctor_absent`
--
ALTER TABLE `doctor_absent`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `doctor_specialist_relation`
--
ALTER TABLE `doctor_specialist_relation`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=34;
--
-- AUTO_INCREMENT for table `hospital`
--
ALTER TABLE `hospital`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `patient`
--
ALTER TABLE `patient`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `specialist`
--
ALTER TABLE `specialist`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=21;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
