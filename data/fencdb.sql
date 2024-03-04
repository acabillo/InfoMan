-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 03, 2024 at 12:42 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `fencdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_admins`
--

CREATE TABLE `tbl_admins` (
  `admin_id` int(10) NOT NULL,
  `admin_username` varchar(50) NOT NULL,
  `admin_password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_admins`
--

INSERT INTO `tbl_admins` (`admin_id`, `admin_username`, `admin_password`) VALUES
(1, 'adminjuan', 'passjuan');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_appointments`
--

CREATE TABLE `tbl_appointments` (
  `appointment_id` int(11) NOT NULL,
  `patient_name` varchar(50) NOT NULL,
  `appointment_time` varchar(50) NOT NULL,
  `appointment_date` varchar(50) NOT NULL,
  `service_name` varchar(50) NOT NULL,
  `dentist_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_appointments`
--

INSERT INTO `tbl_appointments` (`appointment_id`, `patient_name`, `appointment_time`, `appointment_date`, `service_name`, `dentist_name`) VALUES
(1, 'Jasmine Venza', '4:00 PM', '2024-03-05', 'Dental Checkup', 'Dr. Francis Earl Celada'),
(2, 'Xander Gomez', '5:00 PM', '2024-03-05', 'Extraction', 'Dra. Anthonette Mae Navarro'),
(3, 'Michaela Traqe', '9:00 AM', '2024-03-09', 'Orthodontics', 'Dr. Francis Earl Celada'),
(4, 'Jose Caroline', '10:30 AM', '2024-03-10', 'Dental Fillings', 'Dr. Dhan Lloyd Ferrer'),
(5, 'Sanchez Yap', '3:00 PM', '2024-03-13', 'Extraction', 'Dra. Katherine Espinosa'),
(6, 'Kyle Yap', '6:00 PM', '2024-03-13', 'Dental Checkup', 'Dra. Katherine Espinosa'),
(7, 'Nadine Samps', '10:00 AM', '2024-03-27', 'Orthodontics', 'Dr. Francis Earl Celada'),
(8, 'Lovely Shane', '2:00 PM', '2024-03-29', 'Prophylaxis', 'Dra. Anthonette Mae Navarro'),
(9, 'Ace Perza', '10:00 AM', '2024-04-01', 'Orthodontics', 'Dr. Dhan Lloyd Ferrer'),
(10, 'Francheska Eileen', '3:00 PM', '2024-04-02', 'Dental Checkup', 'Dr. Dhan Lloyd Ferrer');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_dentists`
--

CREATE TABLE `tbl_dentists` (
  `dentist_id` int(10) NOT NULL,
  `dentist_name` varchar(50) NOT NULL,
  `dentist_specialization` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_dentists`
--

INSERT INTO `tbl_dentists` (`dentist_id`, `dentist_name`, `dentist_specialization`) VALUES
(102838, 'Dhan Ferrer', 'Orthodontics '),
(106739, 'Francis Celada', 'Check Up'),
(104357, 'Anthonette Navarro', 'Root Canal'),
(105190, 'Katherine Espinosa', 'Dental Implant');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_patients`
--

CREATE TABLE `tbl_patients` (
  `patient_id` int(11) NOT NULL,
  `patient_name` varchar(50) NOT NULL,
  `patient_birthdate` varchar(50) NOT NULL,
  `patient_contact` varchar(11) NOT NULL,
  `patient_record` varchar(50) NOT NULL,
  `patient_dentist` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_patients`
--

INSERT INTO `tbl_patients` (`patient_id`, `patient_name`, `patient_birthdate`, `patient_contact`, `patient_record`, `patient_dentist`) VALUES
(1, 'Nalani Casteneda', '1958-04-10', '09538473694', 'Check Up', 'Dr. Francis Celada'),
(2, 'Julia Dela Cruz', '2001-06-22', '09527351098', 'Dental Fillings', 'Dr. Dhan Lloyd Ferrer'),
(3, 'Brady Newton', '2004-10-24', '09543257958', 'Crown', 'Dra. Anthonette Navarro'),
(4, 'Raven Finley', '2003-09-05', '09183461764', 'Extraction', 'Dr. Francis Celada'),
(5, 'Kareem Salazar', '1994-03-18', '09736842714', 'Check Up', 'Dr. Francis Celada'),
(6, 'Eugene George', '1991-05-16', '09204204547', 'Root Canal', 'Dra. Anthonette Navarro'),
(7, 'Poppy Miller', '2005-05-17', '09154542497', 'Prophylaxis', 'Dra. Katherine Espinosa'),
(8, 'Elyse Rios', '1999-04-24', '09998564127', 'Orthodontics', 'Dr. Dhan Lloyd Ferrer'),
(9, 'Lucy Romero', '2000-09-18', '09773254793', 'Check Up', 'Dr. Francis Celada'),
(10, 'Darius Torres', '1997-08-22', '09537951462', 'Implant', 'Dra. Katherine Espinosa');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_services`
--

CREATE TABLE `tbl_services` (
  `service_id` int(10) NOT NULL,
  `service_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_services`
--

INSERT INTO `tbl_services` (`service_id`, `service_name`) VALUES
(101, 'Dental Checkup'),
(102, 'Prophylaxiss'),
(103, 'Dental Fillings'),
(104, 'Orthodontics'),
(105, 'Dental Implant'),
(106, 'Dental Extraction'),
(107, 'Dental Crown'),
(108, 'Root Canal');

-- --------------------------------------------------------

--
-- Table structure for table `test`
--

CREATE TABLE `test` (
  `id` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `try`
--

CREATE TABLE `try` (
  `id` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_appointments`
--
ALTER TABLE `tbl_appointments`
  ADD PRIMARY KEY (`appointment_id`);

--
-- Indexes for table `tbl_patients`
--
ALTER TABLE `tbl_patients`
  ADD PRIMARY KEY (`patient_id`);

--
-- Indexes for table `test`
--
ALTER TABLE `test`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `try`
--
ALTER TABLE `try`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_appointments`
--
ALTER TABLE `tbl_appointments`
  MODIFY `appointment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `tbl_patients`
--
ALTER TABLE `tbl_patients`
  MODIFY `patient_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `test`
--
ALTER TABLE `test`
  MODIFY `id` int(1) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `try`
--
ALTER TABLE `try`
  MODIFY `id` int(2) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
