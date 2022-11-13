-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 13, 2022 at 03:47 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `schooly`
--

-- --------------------------------------------------------

--
-- Table structure for table `assignment`
--

CREATE TABLE `assignment` (
  `courseID` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `due_date` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `assignment`
--

INSERT INTO `assignment` (`courseID`, `name`, `due_date`, `description`) VALUES
(1, 'HW1', '2022-12-12', 'Answer question 1, 2, 3 and 4'),
(2, 'HW1', '2022-11-12', 'Read chapter 2'),
(3, 'Test 1', '2022-12-03', 'Test chapter 1'),
(4, 'HW1', '2022-12-09', 'Question 3 and 4'),
(5, 'HW1', '2022-12-01', 'Try question 4');

-- --------------------------------------------------------

--
-- Table structure for table `assignment_sequence`
--

CREATE TABLE `assignment_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Stand-in structure for view `assignment_studentview`
-- (See below for the actual view)
--
CREATE TABLE `assignment_studentview` (
`courseID` int(11)
,`assignment_name` varchar(20)
,`course_name` varchar(20)
,`duedate` date
,`description` varchar(255)
);

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `courseID` int(11) NOT NULL,
  `name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`courseID`, `name`) VALUES
(1, 'Biology'),
(2, 'Chemistry'),
(5, 'English'),
(4, 'Math'),
(3, 'Physics');

-- --------------------------------------------------------

--
-- Table structure for table `course_sequence`
--

CREATE TABLE `course_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Stand-in structure for view `grade_studentview`
-- (See below for the actual view)
--
CREATE TABLE `grade_studentview` (
`studID` int(11)
,`courseID` int(11)
,`name` varchar(20)
,`grade` int(11)
,`course_name` varchar(20)
);

-- --------------------------------------------------------

--
-- Table structure for table `instructor`
--

CREATE TABLE `instructor` (
  `instID` int(11) NOT NULL,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `courseID` int(11) DEFAULT NULL,
  `course_name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `instructor`
--

INSERT INTO `instructor` (`instID`, `first_name`, `last_name`, `email`, `courseID`, `course_name`) VALUES
(200, 'Darwin', 'Nunezm', 'darwin@gmail.com', 1, 'Biology'),
(201, 'Michael', 'Thomas', 'michael@gmail.com', 2, 'Chemistry'),
(202, 'Einstein', 'Brooks', 'einstein@gmail.com', 3, 'Physics'),
(203, 'Leonhard', 'Euler', 'leonhard@gmail.com', 4, 'Math'),
(204, 'William', 'Shakespeare', 'william@gmail.com', 5, 'English');

-- --------------------------------------------------------

--
-- Table structure for table `instructor_sequence`
--

CREATE TABLE `instructor_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `studID` int(11) NOT NULL,
  `f_name` varchar(20) DEFAULT NULL,
  `l_name` varchar(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`studID`, `f_name`, `l_name`, `email`) VALUES
(300, 'Claire', 'Ellison', 'claire.ellison@schooly.com'),
(301, 'Dorothy', 'White', 'dorothy.white@schooly.com'),
(302, 'Christian', 'Fraser', 'christian.fraser@schooly.com'),
(303, 'Keith', 'Marshall', 'keith.marshall@schooly.com'),
(304, 'Paul', 'Skinner', 'paul.skinner@schooly.com'),
(305, 'Joshua', 'Taylor', 'joshua.taylor@schooly.com'),
(306, 'Steven', 'Dowd', 'steven.dowd@schooly.com'),
(307, 'Alexandra', 'Butler', 'alexandra.butler@schooly.com'),
(308, 'Rachel', 'Jones', 'rachel.jones@schooly.com'),
(309, 'Stewart', 'Carr', 'stewart.carr@schooly.com');

-- --------------------------------------------------------

--
-- Table structure for table `student_course`
--

CREATE TABLE `student_course` (
  `studid` int(11) NOT NULL,
  `courseid` int(11) NOT NULL,
  `f_name` varchar(20) NOT NULL,
  `l_name` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `student_sequence`
--

CREATE TABLE `student_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `stud_assign`
--

CREATE TABLE `stud_assign` (
  `studID` int(11) NOT NULL,
  `courseID` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `grade` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `stud_assign`
--

INSERT INTO `stud_assign` (`studID`, `courseID`, `name`, `grade`) VALUES
(300, 1, 'HW1', 0),
(300, 2, 'HW1', 0),
(300, 3, 'Test 1', 0),
(300, 4, 'HW1', 0),
(300, 5, 'HW1', 0),
(301, 1, 'HW1', 0),
(301, 2, 'HW1', 0),
(301, 3, 'Test 1', 0),
(301, 4, 'HW1', 0),
(301, 5, 'HW1', 0),
(302, 1, 'HW1', 0),
(302, 2, 'HW1', 0),
(302, 3, 'Test 1', 0),
(302, 4, 'HW1', 0),
(302, 5, 'HW1', 0),
(303, 1, 'HW1', 0),
(303, 2, 'HW1', 0),
(303, 3, 'Test 1', 0),
(303, 4, 'HW1', 0),
(303, 5, 'HW1', 0),
(304, 1, 'HW1', 0),
(304, 2, 'HW1', 0),
(304, 3, 'Test 1', 0),
(304, 4, 'HW1', 0),
(304, 5, 'HW1', 0),
(305, 1, 'HW1', 0),
(305, 2, 'HW1', 0),
(305, 3, 'Test 1', 0),
(305, 4, 'HW1', 0),
(305, 5, 'HW1', 0),
(306, 1, 'HW1', 0),
(306, 2, 'HW1', 0),
(306, 3, 'Test 1', 0),
(306, 4, 'HW1', 0),
(306, 5, 'HW1', 0),
(307, 1, 'HW1', 0),
(307, 2, 'HW1', 0),
(307, 3, 'Test 1', 0),
(307, 4, 'HW1', 0),
(307, 5, 'HW1', 0),
(308, 1, 'HW1', 0),
(308, 2, 'HW1', 0),
(308, 3, 'Test 1', 0),
(308, 4, 'HW1', 0),
(308, 5, 'HW1', 0),
(309, 1, 'HW1', 0),
(309, 2, 'HW1', 0),
(309, 3, 'Test 1', 0),
(309, 4, 'HW1', 0),
(309, 5, 'HW1', 0);

-- --------------------------------------------------------

--
-- Table structure for table `takes`
--

CREATE TABLE `takes` (
  `studID` int(11) NOT NULL,
  `courseID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `takes`
--

INSERT INTO `takes` (`studID`, `courseID`) VALUES
(300, 1),
(300, 2),
(300, 3),
(300, 4),
(300, 5),
(301, 1),
(301, 2),
(301, 3),
(301, 4),
(301, 5),
(302, 1),
(302, 2),
(302, 3),
(302, 4),
(302, 5),
(303, 1),
(303, 2),
(303, 3),
(303, 4),
(303, 5),
(304, 1),
(304, 2),
(304, 3),
(304, 4),
(304, 5),
(305, 1),
(305, 2),
(305, 3),
(305, 4),
(305, 5),
(306, 1),
(306, 2),
(306, 3),
(306, 4),
(306, 5),
(307, 1),
(307, 2),
(307, 3),
(307, 4),
(307, 5),
(308, 1),
(308, 2),
(308, 3),
(308, 4),
(308, 5),
(309, 1),
(309, 2),
(309, 3),
(309, 4),
(309, 5);

-- --------------------------------------------------------

--
-- Table structure for table `todo`
--

CREATE TABLE `todo` (
  `ID` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `item_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `todo`
--

INSERT INTO `todo` (`ID`, `status`, `item_name`) VALUES
(1, 0, 'test'),
(2, 0, 'This item is something I need to do');

-- --------------------------------------------------------

--
-- Structure for view `assignment_studentview`
--
DROP TABLE IF EXISTS `assignment_studentview`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `assignment_studentview`  AS   (select distinct `a`.`courseID` AS `courseID`,`a`.`name` AS `assignment_name`,`c`.`name` AS `course_name`,`a`.`due_date` AS `duedate`,`a`.`description` AS `description` from (((`assignment` `a` join `course` `c`) join `takes` `t`) join `student` `s`) where `a`.`courseID` = `c`.`courseID` and `t`.`courseID` = `c`.`courseID` and `s`.`studID` = `t`.`studID`)  ;

-- --------------------------------------------------------

--
-- Structure for view `grade_studentview`
--
DROP TABLE IF EXISTS `grade_studentview`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `grade_studentview`  AS   (select distinct `s`.`studID` AS `studID`,`s`.`courseID` AS `courseID`,`s`.`name` AS `name`,`s`.`grade` AS `grade`,`c`.`name` AS `course_name` from (`stud_assign` `s` join `course` `c` on(`s`.`courseID` = `c`.`courseID`)))  ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `assignment`
--
ALTER TABLE `assignment`
  ADD PRIMARY KEY (`courseID`,`name`);

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`courseID`),
  ADD UNIQUE KEY `course_pk` (`name`);

--
-- Indexes for table `instructor`
--
ALTER TABLE `instructor`
  ADD PRIMARY KEY (`instID`),
  ADD KEY `instructor_course_name_fk` (`course_name`),
  ADD KEY `courseID` (`courseID`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`studID`);

--
-- Indexes for table `student_course`
--
ALTER TABLE `student_course`
  ADD PRIMARY KEY (`studid`,`courseid`,`f_name`,`l_name`,`name`);

--
-- Indexes for table `stud_assign`
--
ALTER TABLE `stud_assign`
  ADD PRIMARY KEY (`studID`,`courseID`,`name`),
  ADD KEY `courseID` (`courseID`,`name`);

--
-- Indexes for table `takes`
--
ALTER TABLE `takes`
  ADD PRIMARY KEY (`studID`,`courseID`),
  ADD KEY `courseID` (`courseID`);

--
-- Indexes for table `todo`
--
ALTER TABLE `todo`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `todo`
--
ALTER TABLE `todo`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `assignment`
--
ALTER TABLE `assignment`
  ADD CONSTRAINT `assignment_ibfk_1` FOREIGN KEY (`courseID`) REFERENCES `course` (`courseID`);

--
-- Constraints for table `instructor`
--
ALTER TABLE `instructor`
  ADD CONSTRAINT `instructor_course_name_fk` FOREIGN KEY (`course_name`) REFERENCES `course` (`name`),
  ADD CONSTRAINT `instructor_ibfk_1` FOREIGN KEY (`courseID`) REFERENCES `course` (`courseID`);

--
-- Constraints for table `stud_assign`
--
ALTER TABLE `stud_assign`
  ADD CONSTRAINT `stud_assign_ibfk_1` FOREIGN KEY (`studID`) REFERENCES `student` (`studID`),
  ADD CONSTRAINT `stud_assign_ibfk_2` FOREIGN KEY (`courseID`,`name`) REFERENCES `assignment` (`courseID`, `name`);

--
-- Constraints for table `takes`
--
ALTER TABLE `takes`
  ADD CONSTRAINT `takes_ibfk_1` FOREIGN KEY (`studID`) REFERENCES `student` (`studID`),
  ADD CONSTRAINT `takes_ibfk_2` FOREIGN KEY (`courseID`) REFERENCES `course` (`courseID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
