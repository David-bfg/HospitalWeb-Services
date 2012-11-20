-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 01, 2011 at 02:47 AM
-- Server version: 5.1.41
-- PHP Version: 5.3.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `mayo`
--

-- --------------------------------------------------------

--
-- Table structure for table `assistivedevice`
--

CREATE TABLE IF NOT EXISTS `assistivedevice` (
  `AssistiveDeviceID` int(10) NOT NULL,
  `Name` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`AssistiveDeviceID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `assistivedevice`
--

INSERT INTO `assistivedevice` (`AssistiveDeviceID`, `Name`) VALUES
(1, 'walker'),
(2, 'quad'),
(3, 'crutches'),
(4, 'canes'),
(6, 'walker'),
(7, 'quad');

-- --------------------------------------------------------

--
-- Table structure for table `billingcodes`
--

CREATE TABLE IF NOT EXISTS `billingcodes` (
  `billingCodeID` int(10) NOT NULL,
  `visitCharge` double DEFAULT NULL,
  `CPTCode` int(10) DEFAULT NULL,
  `integerIncrement` int(10) DEFAULT NULL,
  `visitID` int(10) DEFAULT NULL,
  PRIMARY KEY (`billingCodeID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `billingcodes`
--

INSERT INTO `billingcodes` (`billingCodeID`, `visitCharge`, `CPTCode`, `integerIncrement`, `visitID`) VALUES
(10000111, 120.32, 1000, 1, 1),
(10000112, 98.58, 1001, 3, 2),
(10000113, 508.13, 1002, 1, 3),
(10000114, 988.46, 1003, 2, 4),
(10000115, 319.01, 1004, 5, 5),
(10000116, 274.99, 1005, 1, 6),
(10000117, 1014.11, 1006, 2, 7),
(10000118, 63.63, 1007, 10, 8),
(10000119, 666.74, 1008, 12, 9),
(10000120, 780, 1009, 6, 10),
(10000121, 381, 1010, 5, 11),
(10000122, 890.54, 1011, 4, 12),
(10000123, 200, 1012, 7, 13),
(10000124, 150.59, 1013, 8, 14),
(10000125, 563.49, 1014, 8, 15);

-- --------------------------------------------------------

--
-- Table structure for table `condition`
--

CREATE TABLE IF NOT EXISTS `condition` (
  `conditionID` int(10) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`conditionID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `condition`
--

INSERT INTO `condition` (`conditionID`, `name`) VALUES
(1, 'Stable'),
(2, 'Recovory'),
(3, 'Critical'),
(4, 'Critical but Stable');

-- --------------------------------------------------------

--
-- Table structure for table `file`
--

CREATE TABLE IF NOT EXISTS `file` (
  `fileId` int(11) NOT NULL,
  `filename` char(25) NOT NULL,
  `clinicNum` int(10) DEFAULT NULL,
  PRIMARY KEY (`fileId`),
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `file`
--

INSERT INTO `file` (`fileId`, `filename`, `clinicNum`) VALUES
(0, 'file1.xml', 100000001),
(1, 'file2.xml', 100000002),
(2, 'file3.xml', 100000003);

-- --------------------------------------------------------

--
-- Table structure for table `icd9diagnostic`
--

CREATE TABLE IF NOT EXISTS `icd9diagnostic` (
  `ICD9DiagnoticID` int(10) NOT NULL,
  `ICD9Diagnostic` varchar(15) DEFAULT NULL,
  `clinicNum` int(10) DEFAULT NULL,
  PRIMARY KEY (`ICD9DiagnoticID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `icd9diagnostic`
--

INSERT INTO `icd9diagnostic` (`ICD9DiagnoticID`, `ICD9Diagnostic`, `clinicNum`) VALUES
(1, '001-139', 100000001),
(2, '771.2', 100000002),
(3, '079.53', 100000002),
(4, '090-099', 100000004),
(5, '062.5', 100000003),
(6, '066.1', 100000005);

-- --------------------------------------------------------

--
-- Table structure for table `icd9procedurecode`
--

CREATE TABLE IF NOT EXISTS `icd9procedurecode` (
  `ICD9ProcedureCodeID` int(10) NOT NULL,
  `ICD9ProcedureCode` varchar(15) DEFAULT NULL,
  `clinicNum` int(10) DEFAULT NULL,
  `Procedure Descriptor` varchar(20) NOT NULL,
  PRIMARY KEY (`ICD9ProcedureCodeID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `icd9procedurecode`
--

INSERT INTO `icd9procedurecode` (`ICD9ProcedureCodeID`, `ICD9ProcedureCode`, `clinicNum`, `Procedure Descriptor`) VALUES
(1, '01.02', 100000001, ''),
(2, '01.41', 100000002, ''),
(3, '01.52', 100000002, ''),
(4, '39.64', 100000004, ''),
(5, '92.32', 100000003, ''),
(6, '84.3', 100000005, '');

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE IF NOT EXISTS `patient` (
  `clinicNum` int(10) NOT NULL,
  `DOB` date DEFAULT NULL,
  `weight` decimal(5,1) DEFAULT NULL,
  `height` decimal(5,1) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `side` varchar(1) DEFAULT NULL,
  `extremity` varchar(1) DEFAULT NULL,
  `lastName` varchar(25) DEFAULT NULL,
  `firstName` varchar(25) DEFAULT NULL,
  `involved` char(1) NOT NULL,
  `dominant` char(1) NOT NULL,
  `Measured Side` varchar(12) NOT NULL,
  `Problem Descriptor` varchar(30) NOT NULL,
  PRIMARY KEY (`clinicNum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`clinicNum`, `DOB`, `weight`, `height`, `gender`, `side`, `extremity`, `lastName`, `firstName`, `involved`, `dominant`, `Measured Side`, `Problem Descriptor`) VALUES
(100000001, '2000-01-01', '35.0', '100.0', 'F', 'L', 'u', 'McKinley', 'Jolene', 'U', 'N', 'R', 'LFKjas'),
(100000002, '1990-03-02', '45.0', '154.0', 'F', 'R', 'u', 'McKinley', 'Sarah', 'U', 'N', 'R', 'iiiii'),
(100000003, '1985-07-03', '100.0', '201.0', 'M', 'L', 'l', 'Smith', 'Joe', 'U', 'N', 'R', 'rp'),
(100000004, '1962-04-05', '120.0', '224.0', 'M', 'R', 'l', 'Jones', 'Mike', 'U', 'N', 'R', 'Prob'),
(100000005, '1976-01-25', '200.0', '220.0', 'F', 'L', 'L', 'Claus', 'Santa', 'U', 'N', 'R', 'prob'),
(1000001, '1998-07-12', '120.0', '23.0', 'M', 'L', 'U', 'leoj', 'Joel', 'U', 'N', 'r', 'problematic');

-- --------------------------------------------------------

--
-- Table structure for table `patientassistivedevice`
--

CREATE TABLE IF NOT EXISTS `patientassistivedevice` (
  `AssistiveDeviceID` int(10) DEFAULT NULL,
  `ClinicNum` int(10) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patientassistivedevice`
--

INSERT INTO `patientassistivedevice` (`AssistiveDeviceID`, `ClinicNum`) VALUES
(1, 100000001),
(2, 100000002),
(3, 100000004),
(4, 100000003),
(6, 100000005);

-- --------------------------------------------------------

--
-- Table structure for table `patientcondition`
--

CREATE TABLE IF NOT EXISTS `patientcondition` (
  `conditionID` int(10) NOT NULL,
  `clinicNum` int(10) NOT NULL,
  PRIMARY KEY (`conditionID`,`clinicNum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patientcondition`
--

INSERT INTO `patientcondition` (`conditionID`, `clinicNum`) VALUES
(1, 100000001),
(2, 100000002),
(3, 100000003),
(4, 100000004);

-- --------------------------------------------------------

--
-- Table structure for table `patientstudy`
--

CREATE TABLE IF NOT EXISTS `patientstudy` (
  `studyID` int(10) NOT NULL,
  `visitID` int(10) NOT NULL,
  PRIMARY KEY (`studyID`,`visitID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patientstudy`
--

INSERT INTO `patientstudy` (`studyID`, `visitID`) VALUES
(1001, 1),
(1001, 2),
(1001, 3),
(1002, 3),
(1002, 5),
(1004, 2),
(1004, 6);

-- --------------------------------------------------------

--
-- Table structure for table `researchstudy`
--

CREATE TABLE IF NOT EXISTS `researchstudy` (
  `IRBNum` int(10) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`IRBNum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `researchstudy`
--

INSERT INTO `researchstudy` (`IRBNum`, `name`, `description`) VALUES
(1, 'ResStudy1', 'Description1'),
(2, 'ResStudy2', 'Description2'),
(3, 'ResStudy3', 'Description3');

-- --------------------------------------------------------

--
-- Table structure for table `study`
--

CREATE TABLE IF NOT EXISTS `study` (
  `studyID` int(10) NOT NULL,
  `studyName` varchar(45) NOT NULL,
  PRIMARY KEY (`studyID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `study`
--

INSERT INTO `study` (`studyID`, `studyName`) VALUES
(1001, 'study A'),
(1002, 'study B'),
(1003, 'study C'),
(1004, 'study D'),
(1005, 'study E'),
(1006, 'study F'),
(1007, 'study G'),
(1008, 'study H'),
(1009, 'study I'),
(1010, 'study J');

-- --------------------------------------------------------

--
-- Table structure for table `systemused`
--

CREATE TABLE IF NOT EXISTS `systemused` (
  `systemUsedID` int(10) NOT NULL,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`systemUsedID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `systemused`
--

INSERT INTO `systemused` (`systemUsedID`, `name`) VALUES
(100, 'HUMAC'),
(200, 'TEKSCAN'),
(300, 'QMA'),
(400, 'UE'),
(500, 'HHD-Lafayette'),
(600, 'HHD-J-TECH'),
(700, 'HHD-MSE-500-Chattillon'),
(800, 'BioDex'),
(900, 'EMG-Surface'),
(1000, 'EMG-FineWire');

-- --------------------------------------------------------

--
-- Table structure for table `tape`
--

CREATE TABLE IF NOT EXISTS `tape` (
  `tapeID` int(10) NOT NULL,
  `clinicNum` int(10) NOT NULL,
  `visitID` int(10) NOT NULL,
  `tapeNum` int(10) DEFAULT NULL,
  `studyID` int(10) DEFAULT NULL,
  `backup` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`tapeID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tape`
--


-- --------------------------------------------------------

--
-- Table structure for table `visit`
--

CREATE TABLE IF NOT EXISTS `visit` (
  `visitID` int(10) NOT NULL,
  `date` date DEFAULT NULL,
  `visitNum` int(10) DEFAULT NULL,
  `clinicNum` int(10) DEFAULT NULL,
  `provider` varchar(25) DEFAULT NULL,
  `kinesiologist` varchar(25) DEFAULT NULL,
  `dateProcessingComplete` date DEFAULT NULL,
  `physicalTherapist` varchar(25) DEFAULT NULL,
  `DateAnalysisComplete` date DEFAULT NULL,
  PRIMARY KEY (`visitID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `visit`
--

INSERT INTO `visit` (`visitID`, `date`, `visitNum`, `clinicNum`, `provider`, `kinesiologist`, `dateProcessingComplete`, `physicalTherapist`, `DateAnalysisComplete`) VALUES
(1, '2010-01-02', 1, 100000001, 'AndersonN', 'SmithK', '2010-01-03', 'Campbell', '2010-01-04'),
(2, '2010-01-04', 1, 100000002, 'JonesF', 'FranksL', '2010-01-05', 'KraftD', '2010-01-06'),
(3, '2010-01-06', 1, 100000003, 'Provider1', 'HyperV', '2010-01-07', 'ClampettJ', '2010-01-08'),
(4, '2010-01-08', 1, 100000004, 'CinderellaM', '', '2010-01-09', 'WhiteS', '2010-01-10'),
(5, '2010-01-10', 1, 100000005, 'VikingsO', 'GreenB', '2010-01-11', '', '2010-01-12'),
(6, '2010-02-02', 2, 100000001, 'AndersonN', 'SmithK', '2010-02-03', 'Campbell', '2010-02-04'),
(7, '2010-02-04', 2, 100000002, 'JonesF', 'FranksL', '2010-02-05', 'KraftD', '2010-02-06'),
(8, '2010-02-06', 2, 100000003, 'Provider2', 'HyperV', '2010-02-07', 'ClampettJ', '2010-02-08'),
(9, '2010-02-08', 2, 100000004, 'CinderellaM', '', '2010-02-09', 'WhiteS', '2010-02-10'),
(10, '2010-02-10', 2, 100000005, 'VikingsO', 'GreenB', '2010-02-11', '', '2010-02-12'),
(11, '2010-03-02', 3, 100000001, 'AndersonN', 'SmithK', '2010-03-03', 'Campbell', '2010-03-04'),
(12, '2010-03-04', 3, 100000002, 'JonesF', 'FranksL', '2010-03-05', 'KraftD', '2010-03-06'),
(13, '2010-03-06', 3, 100000003, 'Provider3', 'HyperV', '2010-03-07', 'ClampettJ', '2010-03-08'),
(14, '2010-03-08', 3, 100000004, 'CinderellaM', '', '2010-03-09', 'WhiteS', '2010-03-10'),
(15, '2010-03-10', 3, 100000005, 'VikingsO', 'GreenB', '2010-03-11', '', '2010-03-12');

-- --------------------------------------------------------

--
-- Table structure for table `visitresearchstudy`
--

CREATE TABLE IF NOT EXISTS `visitresearchstudy` (
  `visitID` int(10) DEFAULT NULL,
  `IRBNum` int(10) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `visitresearchstudy`
--


-- --------------------------------------------------------

--
-- Table structure for table `visitsystemused`
--

CREATE TABLE IF NOT EXISTS `visitsystemused` (
  `systemUsedID` int(10) DEFAULT NULL,
  `visitID` int(10) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `visitsystemused`
--

INSERT INTO `visitsystemused` (`systemUsedID`, `visitID`) VALUES
(300, 8),
(400, 9),
(200, 4);
