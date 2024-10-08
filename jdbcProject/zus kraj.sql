-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 08, 2024 at 11:57 PM
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
-- Database: `zus`
--

-- --------------------------------------------------------

--
-- Table structure for table `atmosphere`
--

CREATE TABLE `atmosphere` (
  `atmosphere_id` int(11) NOT NULL,
  `oxygen_percentage` float NOT NULL,
  `gas_symbol` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `atmosphere`
--

INSERT INTO `atmosphere` (`atmosphere_id`, `oxygen_percentage`, `gas_symbol`) VALUES
(1, 0.17, 'Ar'),
(2, 0.2, 'N'),
(3, 0.23, 'N'),
(4, 0.3, 'BrCl'),
(5, 0.1, 'COO');

-- --------------------------------------------------------

--
-- Table structure for table `companion`
--

CREATE TABLE `companion` (
  `companion_id` int(11) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `living_space_id` int(11) NOT NULL,
  `human_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `gas`
--

CREATE TABLE `gas` (
  `gas_symbol` varchar(10) NOT NULL,
  `gas_percentage` float NOT NULL,
  `is_soluble` tinyint(1) NOT NULL,
  `is_deadly` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `gas`
--

INSERT INTO `gas` (`gas_symbol`, `gas_percentage`, `is_soluble`, `is_deadly`) VALUES
('Ar', 0.12, 1, 0),
('BrCl', 0.82, 1, 1),
('COO', 0.53, 1, 1),
('N', 0.74, 1, 0),
('O3', 0.64, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `human`
--

CREATE TABLE `human` (
  `human_id` int(11) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `username` varchar(15) NOT NULL,
  `password` varchar(20) NOT NULL,
  `has_passport` tinyint(1) NOT NULL,
  `spaceship_id` int(11) DEFAULT NULL,
  `living_space_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `human`
--

INSERT INTO `human` (`human_id`, `first_name`, `last_name`, `username`, `password`, `has_passport`, `spaceship_id`, `living_space_id`) VALUES
(1, 'Luka', 'Gojkovic', 'LG', '1234', 1, NULL, NULL),
(2, 'Stefan', 'Vuksanovic', 'djamu', '123', 1, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `living_space`
--

CREATE TABLE `living_space` (
  `living_space_id` int(11) NOT NULL,
  `living_space_name` varchar(30) NOT NULL,
  `number_of_people` int(11) NOT NULL,
  `is_occupied` tinyint(1) NOT NULL,
  `planet_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `living_space`
--

INSERT INTO `living_space` (`living_space_id`, `living_space_name`, `number_of_people`, `is_occupied`, `planet_id`) VALUES
(1, 'Stellar Haven', 0, 0, 3),
(2, 'Galactic Residence', 0, 0, 3),
(3, 'Nebula Nest', 0, 0, 4),
(4, 'Orbital Oasis', 0, 0, 5),
(5, 'Celestial Sanctum', 0, 0, 5),
(6, 'Solaris Suite', 0, 0, 5),
(7, 'Eclipse Estate', 0, 0, 6),
(8, 'Interstellar Inn', 0, 0, 6),
(9, 'Voyager Villa', 0, 0, 7),
(10, 'Meteor Mansion', 0, 0, 9),
(11, 'Orbit Overlook', 0, 0, 10),
(12, 'Galaxy Grove', 0, 0, 10);

-- --------------------------------------------------------

--
-- Table structure for table `mission`
--

CREATE TABLE `mission` (
  `mission_id` int(11) NOT NULL,
  `mission_start_date` date NOT NULL,
  `mission_end_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mission`
--

INSERT INTO `mission` (`mission_id`, `mission_start_date`, `mission_end_date`) VALUES
(1, '2105-08-19', '2106-10-14'),
(2, '2213-11-19', '2215-04-21'),
(3, '2213-11-19', '2214-06-17'),
(4, '2244-08-13', '2247-06-17'),
(5, '2277-10-26', '2283-07-23'),
(6, '2143-09-18', '2147-04-07'),
(7, '2225-06-15', '2226-08-17'),
(8, '2213-04-30', '2214-11-26'),
(9, '2250-02-23', '2255-01-10'),
(10, '2222-03-16', '2224-03-05');

-- --------------------------------------------------------

--
-- Table structure for table `planet`
--

CREATE TABLE `planet` (
  `planet_id` int(11) NOT NULL,
  `planet_name` varchar(30) NOT NULL,
  `nearest_star_distance` int(11) NOT NULL,
  `max_temp` int(11) NOT NULL,
  `min_temp` int(11) NOT NULL,
  `grav_field_range` int(11) NOT NULL,
  `rot_speed` int(11) NOT NULL,
  `is_founded` tinyint(1) NOT NULL,
  `is_satellite` tinyint(1) NOT NULL,
  `dead_ppl_under_40_num` int(11) NOT NULL,
  `mission_id` int(11) NOT NULL,
  `atmosphere_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `planet`
--

INSERT INTO `planet` (`planet_id`, `planet_name`, `nearest_star_distance`, `max_temp`, `min_temp`, `grav_field_range`, `rot_speed`, `is_founded`, `is_satellite`, `dead_ppl_under_40_num`, `mission_id`, `atmosphere_id`) VALUES
(1, 'Titan', 120, 300, 200, 900, 40, 1, 1, 100, 1, 4),
(2, 'Triton', 300, 300, 200, 1100, 30, 1, 1, 0, 2, 2),
(3, 'Hyperion', 150, 300, 200, 1300, 27, 1, 0, 5, 3, 3),
(4, 'Elysium', 110, 300, 200, 1650, 34, 1, 0, 0, 4, 2),
(5, 'Nocturna', 120, 320, 240, 1235, 31, 1, 0, 2, 5, 2),
(6, 'Celestia', 130, 340, 240, 1450, 30, 1, 0, 1, 6, 3),
(7, 'Orbis', 120, 330, 210, 1600, 29, 1, 0, 8, 7, 3),
(8, 'Aurelis', 110, 100, 10, 1125, 28, 1, 0, 4, 8, 1),
(9, 'Draconis', 160, 300, 180, 1300, 27, 1, 0, 7, 9, 3),
(10, 'Eclipsa', 180, 270, 160, 1200, 26, 1, 0, 0, 10, 2);

-- --------------------------------------------------------

--
-- Table structure for table `spaceship`
--

CREATE TABLE `spaceship` (
  `spaceship_id` int(11) NOT NULL,
  `spaceship_name` varchar(30) NOT NULL,
  `departure_date` date NOT NULL,
  `departure_hours` int(11) NOT NULL,
  `departure_minutes` int(11) NOT NULL,
  `number_of_seats` int(11) NOT NULL,
  `living_space_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `spaceship`
--

INSERT INTO `spaceship` (`spaceship_id`, `spaceship_name`, `departure_date`, `departure_hours`, `departure_minutes`, `number_of_seats`, `living_space_id`) VALUES
(1, 'Starblazer', '2227-06-30', 11, 25, 30, 11),
(2, 'Nebula Knight', '2230-09-15', 13, 45, 40, 12),
(3, 'Interstellar Odyssey', '2233-07-10', 17, 30, 30, 10),
(4, 'Starlight Voyager', '2235-02-15', 16, 15, 30, 7),
(5, 'Solar Wind', '2240-11-03', 19, 30, 40, 8),
(6, 'Star Seeker	', '2244-08-25', 10, 30, 40, 1),
(7, 'Phoenix Ascendant', '2246-07-10', 17, 30, 40, 2),
(8, 'Quantum Leap', '2246-07-10', 17, 35, 30, 3),
(9, 'Vega Voyager', '2255-04-10', 14, 20, 35, 4),
(10, 'Pulsar Navigator', '2256-05-07', 15, 30, 40, 5),
(11, 'Celestial Wanderer', '2257-09-09', 16, 40, 45, 6),
(12, 'Aurora Ascendant', '2257-12-23', 12, 15, 30, 9);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `atmosphere`
--
ALTER TABLE `atmosphere`
  ADD PRIMARY KEY (`atmosphere_id`),
  ADD KEY `gas_symbol` (`gas_symbol`);

--
-- Indexes for table `companion`
--
ALTER TABLE `companion`
  ADD PRIMARY KEY (`companion_id`),
  ADD KEY `living_space_id` (`living_space_id`),
  ADD KEY `human_id` (`human_id`);

--
-- Indexes for table `gas`
--
ALTER TABLE `gas`
  ADD PRIMARY KEY (`gas_symbol`);

--
-- Indexes for table `human`
--
ALTER TABLE `human`
  ADD PRIMARY KEY (`human_id`),
  ADD KEY `spaceship_id` (`spaceship_id`),
  ADD KEY `living_space_id` (`living_space_id`);

--
-- Indexes for table `living_space`
--
ALTER TABLE `living_space`
  ADD PRIMARY KEY (`living_space_id`),
  ADD KEY `planet_id` (`planet_id`);

--
-- Indexes for table `mission`
--
ALTER TABLE `mission`
  ADD PRIMARY KEY (`mission_id`);

--
-- Indexes for table `planet`
--
ALTER TABLE `planet`
  ADD PRIMARY KEY (`planet_id`),
  ADD KEY `mission_id` (`mission_id`),
  ADD KEY `atmosphere_id` (`atmosphere_id`);

--
-- Indexes for table `spaceship`
--
ALTER TABLE `spaceship`
  ADD PRIMARY KEY (`spaceship_id`),
  ADD KEY `living_space_id` (`living_space_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `atmosphere`
--
ALTER TABLE `atmosphere`
  MODIFY `atmosphere_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `companion`
--
ALTER TABLE `companion`
  MODIFY `companion_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `human`
--
ALTER TABLE `human`
  MODIFY `human_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `living_space`
--
ALTER TABLE `living_space`
  MODIFY `living_space_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `mission`
--
ALTER TABLE `mission`
  MODIFY `mission_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `planet`
--
ALTER TABLE `planet`
  MODIFY `planet_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `spaceship`
--
ALTER TABLE `spaceship`
  MODIFY `spaceship_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `atmosphere`
--
ALTER TABLE `atmosphere`
  ADD CONSTRAINT `atmosphere_ibfk_1` FOREIGN KEY (`gas_symbol`) REFERENCES `gas` (`gas_symbol`),
  ADD CONSTRAINT `atmosphere_ibfk_2` FOREIGN KEY (`gas_symbol`) REFERENCES `gas` (`gas_symbol`) ON UPDATE CASCADE;

--
-- Constraints for table `companion`
--
ALTER TABLE `companion`
  ADD CONSTRAINT `companion_ibfk_1` FOREIGN KEY (`living_space_id`) REFERENCES `living_space` (`living_space_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `companion_ibfk_2` FOREIGN KEY (`human_id`) REFERENCES `human` (`human_id`) ON UPDATE CASCADE;

--
-- Constraints for table `human`
--
ALTER TABLE `human`
  ADD CONSTRAINT `human_ibfk_1` FOREIGN KEY (`spaceship_id`) REFERENCES `spaceship` (`spaceship_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `human_ibfk_2` FOREIGN KEY (`living_space_id`) REFERENCES `living_space` (`living_space_id`) ON UPDATE CASCADE;

--
-- Constraints for table `living_space`
--
ALTER TABLE `living_space`
  ADD CONSTRAINT `living_space_ibfk_1` FOREIGN KEY (`planet_id`) REFERENCES `planet` (`planet_id`) ON UPDATE CASCADE;

--
-- Constraints for table `planet`
--
ALTER TABLE `planet`
  ADD CONSTRAINT `planet_ibfk_1` FOREIGN KEY (`mission_id`) REFERENCES `mission` (`mission_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `planet_ibfk_2` FOREIGN KEY (`atmosphere_id`) REFERENCES `atmosphere` (`atmosphere_id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
