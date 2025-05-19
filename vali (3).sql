-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 19, 2025 at 02:22 PM
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
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `o_id` int(11) NOT NULL,
  `u_id` int(11) NOT NULL,
  `p_id` int(11) NOT NULL,
  `quantity` int(10) NOT NULL,
  `date` datetime DEFAULT NULL,
  `status` varchar(150) NOT NULL,
  `o_total` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`o_id`, `u_id`, `p_id`, `quantity`, `date`, `status`, `o_total`) VALUES
(3, 0, 8, 1, '2025-05-17 18:21:46', 'Successful', 100000000),
(4, 40, 4, 1, '2025-05-17 18:22:33', 'Successful', 30000000),
(5, 40, 9, 1, '2025-05-17 18:57:51', 'Successful', 1000000000),
(6, 40, 9, 2, '2025-05-18 19:57:17', 'Successful', 2000000000),
(7, 40, 18, 1, '2025-05-18 20:03:44', 'Successful', 30000000),
(8, 40, 17, 1, '2025-05-18 21:00:25', 'Successful', 33000000);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `p_id` int(11) NOT NULL,
  `p_name` varchar(50) NOT NULL,
  `p_price` varchar(50) NOT NULL,
  `p_status` varchar(50) NOT NULL,
  `p_image` varchar(300) NOT NULL,
  `p_quantity` int(255) NOT NULL,
  `p_sold` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`p_id`, `p_name`, `p_price`, `p_status`, `p_image`, `p_quantity`, `p_sold`) VALUES
(1, 'toyota', '100000001', 'Deleted', '', 1, ''),
(2, 'lambo', '20000000', 'Deleted', 'src/userimages/1.png', 2, ''),
(4, 'Chevrolet', '30000000', 'Deleted', '', 2, '1'),
(5, 'honda', '30000000', 'Deleted', 'src/userimages/313856583_887080819129243_8741068835114826621_n.png', 2, ''),
(6, 'ford', '10000000', 'Deleted', 'src/userimages/10426146_755445481238542_1296814044430433972_n.jpg', 1, ''),
(8, 'Raptors', '100000000', 'Deleted', '', 0, '1'),
(9, 'BMW', '1000000000', 'Available', '', -2, '3'),
(10, 'Volkswagen', '10000000000', 'Available', '', 1, ''),
(11, 'Mercedes-Benz', '100000000000', 'Available', '', 1, ''),
(12, 'Audi', '100000000000', 'Available', '', 2, ''),
(13, 'Hyundai', '200000000', 'Available', '', 1, ''),
(14, 'Ferrari', '3000000000', 'Available', '', 2, ''),
(15, 'Lamborghini', '10000000000', 'Available', '', 1, ''),
(16, 'Suzuki', '55000000', 'Available', '', 1, ''),
(17, 'Isuzu', '33000000', 'Available', '', 2, '1'),
(18, 'Mitsubishi', '30000000', 'Available', '', 0, '1');

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
(1, 34, 'mikes', '2025-04-26 14:01:24', 'Forgot, and changed their password'),
(3, 40, 'null', '2025-05-05 06:49:59', 'Admin Added a Car Sales: lambo'),
(4, 40, 'agent', '2025-05-05 06:56:38', 'Admin Deleted Account: agent'),
(5, 40, 'agent', '2025-05-05 06:57:04', 'Admin Added a Car Sales: luxry'),
(6, 40, 'agent', '2025-05-05 06:57:43', 'Admin Deleted Account: agent'),
(7, 40, 'agent', '2025-05-05 06:59:09', 'Admin Deleted Account: agent'),
(9, 40, 'agent', '2025-05-05 07:03:47', 'Admin Added a Car Sales: honda'),
(19, 40, 'agent', '2025-05-05 13:59:26', 'Forgot, and changed their password'),
(20, 40, 'agent', '2025-05-05 13:59:52', 'Forgot, and changed their password'),
(21, 40, 'agent', '2025-05-05 14:08:07', 'Forgot, and changed their password'),
(22, 40, 'agent', '2025-05-05 14:12:37', 'Forgot, and changed their password'),
(23, 40, 'agent', '2025-05-05 14:13:00', 'Forgot, and changed their password'),
(24, 40, 'agent', '2025-05-05 14:13:33', 'Forgot, and changed their password'),
(25, 40, 'agent', '2025-05-05 14:14:10', 'Forgot, and changed their password'),
(26, 40, 'agent', '2025-05-05 14:14:40', 'Forgot, and changed their password'),
(27, 40, 'agent', '2025-05-05 14:15:10', 'Forgot, and changed their password'),
(28, 40, 'agent', '2025-05-07 02:56:15', 'Forgot, and changed their password'),
(29, 40, 'agent', '2025-05-07 03:00:14', 'Forgot, and changed their password'),
(30, 40, 'agent', '2025-05-07 03:03:04', 'Forgot, and changed their password'),
(31, 40, 'agent', '2025-05-07 03:03:37', 'Forgot, and changed their password'),
(32, 40, 'agent', '2025-05-07 03:05:08', 'Forgot, and changed their password'),
(33, 40, 'agent', '2025-05-07 03:08:11', 'Forgot, and changed their password'),
(34, 40, 'agent', '2025-05-07 03:13:34', 'Forgot, and changed their password'),
(35, 40, 'agent', '2025-05-07 03:14:46', 'Forgot, and changed their password'),
(36, 40, 'agent', '2025-05-07 12:56:45', 'Admin Added a Car Sales: Raptors'),
(38, 40, 'agent', '2025-05-17 10:22:33', 'User made transaction ID: Chevrolet'),
(39, 41, 'chan', '2025-05-17 10:23:44', 'Forgot, and changed their password'),
(40, 41, 'chan', '2025-05-17 10:24:27', 'Admin Deleted Product: Chevrolet'),
(41, 40, 'agent', '2025-05-17 10:55:00', 'Admin Added a Car Sales: BMW'),
(42, 40, 'agent', '2025-05-17 10:55:29', 'Admin Added a Car Sales: Volkswagen'),
(43, 40, 'agent', '2025-05-17 10:55:47', 'Admin Added a Car Sales: Mercedes-Benz'),
(44, 40, 'agent', '2025-05-17 10:57:51', 'User made transaction ID: BMW'),
(45, 40, 'agent', '2025-05-18 11:57:18', 'User made transaction ID: BMW'),
(46, 41, 'chan', '2025-05-18 11:59:19', 'Admin Added a Car Sales: Audi'),
(47, 41, 'chan', '2025-05-18 11:59:48', 'Admin Added a Car Sales: Hyundai'),
(48, 41, 'chan', '2025-05-18 12:00:21', 'Admin Added a Car Sales: Ferrari'),
(49, 41, 'chan', '2025-05-18 12:00:44', 'Admin Added a Car Sales: Lamborghini'),
(50, 41, 'chan', '2025-05-18 12:01:25', 'Admin Added a Car Sales: Suzuki'),
(51, 41, 'chan', '2025-05-18 12:02:31', 'Admin Added a Car Sales: Isuzu'),
(52, 41, 'chan', '2025-05-18 12:02:57', 'Admin Added a Car Sales: Mitsubishi'),
(53, 40, 'agent', '2025-05-18 12:03:44', 'User made transaction ID: Mitsubishi'),
(54, 40, 'agent', '2025-05-18 13:00:26', 'User made transaction ID: Isuzu');

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
(39, 'ysys', 'tsts', 'emai@gmail.com', '09567884189', 'Admin', 'yaya', 'ky88G1YlfOhTmsJp16q0JVDaz4gY0HXwvfGZBWKq4+8=', 'Active', 'N/A', 'N/A'),
(40, 'gmail', 'yahoo', 'fck@gmail.com', '09567884189', 'User', 'agent', 'FeKw08M4keuw8e9gnsQZQgwg4yDOlMZfvIwzEkSOsiU=', 'Active', 'What is your dream job?', 'it'),
(41, 'chan', 'yul', 'chan@gmail.com', '09567884189', 'Admin', 'chan', 'FeKw08M4keuw8e9gnsQZQgwg4yDOlMZfvIwzEkSOsiU=', 'Active', 'What\'s the lastname of your Mother?', 'canoy'),
(42, 'shello', 'syee', 'shlo@gmail.com', '09567884418', 'Admin', 'jade', 'ky88G1YlfOhTmsJp16q0JVDaz4gY0HXwvfGZBWKq4+8=', 'Active', 'What is your dream job?', 'IT');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`o_id`),
  ADD KEY `orders` (`u_id`),
  ADD KEY `fk_orders_product` (`p_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`p_id`);

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
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `o_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `p_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `tbl_logs`
--
ALTER TABLE `tbl_logs`
  MODIFY `log_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `u_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `fk_orders_product` FOREIGN KEY (`p_id`) REFERENCES `product` (`p_id`);

--
-- Constraints for table `tbl_logs`
--
ALTER TABLE `tbl_logs`
  ADD CONSTRAINT `tbl_logs_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `users` (`u_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
