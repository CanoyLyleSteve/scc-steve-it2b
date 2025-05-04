-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 04, 2025 at 06:07 AM
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
-- Database: `vali`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_logs`
--

CREATE TABLE `tbl_logs` (
  `log_id` int(11) NOT NULL,
  `u_id` int(11) NOT NULL,
  `u_username` varchar(50) NOT NULL,
  `action_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `log_action` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_logs`
--

INSERT INTO `tbl_logs` (`log_id`, `u_id`, `u_username`, `action_time`, `log_action`) VALUES
(1, 34, 'mikes', '2025-04-26 14:01:24', 'Forgot, and changed their password');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `u_id` int(20) NOT NULL,
  `u_fname` varchar(50) NOT NULL,
  `u_lname` varchar(50) NOT NULL,
  `u_email` varchar(50) NOT NULL,
  `u_contact1` varchar(50) NOT NULL,
  `u_ty` varchar(50) NOT NULL,
  `u_usname` varchar(50) NOT NULL,
  `u_password1` varchar(150) NOT NULL,
  `u_status` varchar(50) NOT NULL,
  `security_question` varchar(255) NOT NULL,
  `security_answer` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`u_id`, `u_fname`, `u_lname`, `u_email`, `u_contact1`, `u_ty`, `u_usname`, `u_password1`, `u_status`, `security_question`, `security_answer`) VALUES
(1, 'lyle', 'steve', 'lylesteve7@gmail.com', '09567884189', 'Admin', 'lylesteve1', 'quiachon123', 'Active', '', ''),
(22, 'jade', 'vil', 'vila@gmail.com', '124125', 'Admin', 'stve', '123456789', 'Pending', '', ''),
(23, 'juuu', 'jail', 'jugmail@.com', '123342', 'Admin', 'steee', 'haaaaaaa', 'Pending', '', ''),
(24, 'jake', 'waii', 'wai@gmail.com', '09665645', 'User', 'steha', '12345678', 'Active', '', ''),
(28, 'test', 'teswt', 'lylesteve2@gmail.com', '09567884189', 'Admin', 'test', '123456789', 'Active', '', ''),
(29, 'test', 'test', 'lyeste2@gmail.com', '09567884189', 'Admin', 'swwww', '12345678', 'Active', '', ''),
(30, 'lyle', 'steve', 'canoyy@gmail.com', '09567884189', 'Admin', 'tyy', '123456789', 'Active', '', ''),
(31, 'mike', 'bus', 'mike@gmail.com', '09567884189', 'Admin', 'mike', 'mikemike', 'Active', '', ''),
(34, 'mike', 'mike', 'mikes@gmail.com', '09567884189', 'User', 'mikes', 'WZRHGrsBESr8wYFZ9sx0tPURuZgG2lmzyvWpwXPKz8U=', 'Active', '', ''),
(35, 'bus', 'mike', 'lyle@gmail.com', '09567884189', 'User', 'hasher', 'kyDqEfbUJ67ElJY03IZ2E2svqM2tKJ0iZZtEVBq7jFE=', 'Active', '', ''),
(36, 'test', 'test', 'tes1@gmail.com', '09567884189', 'Admin', 'ambot', '73l8gRjwLftklgfdXT+MdiMEjJwGPVMsyVxe16iYpk8=', 'Active', '', ''),
(37, 'steve', 'steeve', 'ste@gmail.com', '09567884189', 'Admin', 'gmail', 'FeKw08M4keuw8e9gnsQZQgwg4yDOlMZfvIwzEkSOsiU=', 'Active', '', ''),
(38, 'wai', 'huuu', 'yul@gmail.com', '09567884189', 'User', 'yul', 't5ehUXltvl6Ts0EwMOc5WaG0ldhDiOmkNZgkzPmwUaI=', 'Active', '', ''),
(39, 'ysys', 'tsts', 'emai@gmail.com', '09567884189', 'Admin', 'yaya', 'ky88G1YlfOhTmsJp16q0JVDaz4gY0HXwvfGZBWKq4+8=', 'Active', 'N/A', 'N/A');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_logs`
--
ALTER TABLE `tbl_logs`
  ADD PRIMARY KEY (`log_id`),
  ADD KEY `u_id` (`u_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`u_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_logs`
--
ALTER TABLE `tbl_logs`
  MODIFY `log_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `u_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_logs`
--
ALTER TABLE `tbl_logs`
  ADD CONSTRAINT `tbl_logs_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `users` (`u_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
