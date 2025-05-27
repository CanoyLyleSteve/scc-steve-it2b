-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 27, 2025 at 05:17 PM
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
  `o_total` decimal(21,2) DEFAULT NULL,
  `payment_type` varchar(20) DEFAULT NULL,
  `payment_amount` decimal(20,2) NOT NULL DEFAULT 0.00
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`o_id`, `u_id`, `p_id`, `quantity`, `date`, `status`, `o_total`, `payment_type`, `payment_amount`) VALUES
(3, 0, 8, 1, '2025-05-17 18:21:46', 'Successful', 99999999.99, NULL, 0.00),
(4, 40, 4, 1, '2025-05-17 18:22:33', 'Successful', 30000000.00, NULL, 0.00),
(5, 40, 9, 1, '2025-05-17 18:57:51', 'Successful', 99999999.99, NULL, 0.00),
(6, 40, 9, 2, '2025-05-18 19:57:17', 'Successful', 99999999.99, NULL, 0.00),
(7, 40, 18, 1, '2025-05-18 20:03:44', 'Successful', 30000000.00, NULL, 0.00),
(8, 40, 17, 1, '2025-05-18 21:00:25', 'Successful', 33000000.00, NULL, 0.00),
(9, 0, 16, 1, '2025-05-20 22:24:16', 'Successful', 55000000.00, 'Installment', 0.00),
(10, 0, 17, 1, '2025-05-20 22:44:45', 'Successful', 30000000.00, 'Cash', 0.00),
(11, 40, 17, 1, '2025-05-20 22:45:27', 'Successful', 33000000.00, 'Cash', 0.00),
(12, 40, 15, 1, '2025-05-26 17:49:56', 'Successful', 99999999.99, 'Cash', 0.00),
(13, 40, 13, 1, '2025-05-26 17:53:52', 'Successful', 99999999.99, 'Cash', 0.00),
(14, 0, 12, 1, '2025-05-26 18:09:59', 'Successful', 12.00, 'Cash', 0.00),
(15, 40, 12, 1, '2025-05-26 18:26:39', 'Successful', 99999999.99, 'Cash', 0.00),
(16, 40, 23, 1, '2025-05-26 19:17:40', 'Successful', 99999999.99, 'Cash', 0.00),
(17, 0, 26, 1, '2025-05-26 20:56:02', 'Successful', 1000000000.00, 'Cash', 0.00),
(18, 40, 26, 1, '2025-05-26 20:58:04', 'Successful', 1000000000.00, 'Cash', 0.00),
(19, 0, 25, 1, '2025-05-26 21:00:29', 'Successful', 10000000000.00, 'Cash', 0.00),
(20, 40, 25, 1, '2025-05-26 21:01:09', 'Successful', 10000000000.00, 'Cash', 0.00),
(21, 40, 27, 1, '2025-05-26 22:54:01', 'Successful', 1000000000.00, 'Cash', 0.00),
(22, 40, 35, 1, '2025-05-26 23:15:16', 'Successful', 20000000.00, 'Installment', 0.00),
(23, 40, 36, 1, '2025-05-26 23:51:46', 'Successful', 100000000.00, 'Installment', 0.00),
(24, 40, 23, 1, '2025-05-27 00:02:29', 'Successful', 1000000000.00, 'Installment', 0.00),
(25, 40, 33, 1, '2025-05-27 00:03:12', 'Successful', 100000000.00, 'Installment', 0.00),
(26, 40, 30, 1, '2025-05-27 00:03:41', 'Successful', 100000000.00, 'Cash', 0.00),
(27, 0, 29, 1, '2025-05-27 00:07:54', 'Successful', 100000000.00, 'Installment', 0.00),
(28, 40, 31, 1, '2025-05-27 00:08:35', 'Successful', 100000000.00, 'Installment', 0.00),
(29, 40, 45, 1, '2025-05-27 18:30:32', 'Successful', 10000000000.00, 'Cash', 0.00),
(30, 40, 46, 1, '2025-05-27 18:32:23', 'Successful', 30000000.00, 'Cash', 0.00),
(31, 0, 45, 1, '2025-05-27 18:41:54', 'Successful', 50000000.00, 'Cash', 0.00),
(32, 40, 55, 1, '2025-05-27 22:03:40', 'Successful', 12000000.00, 'Installment', 0.00);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `p_id` int(11) NOT NULL,
  `p_name` varchar(50) NOT NULL,
  `p_price` decimal(21,2) DEFAULT NULL,
  `p_status` varchar(50) NOT NULL,
  `p_image` varchar(300) NOT NULL,
  `p_quantity` bigint(250) DEFAULT NULL,
  `p_sold` bigint(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`p_id`, `p_name`, `p_price`, `p_status`, `p_image`, `p_quantity`, `p_sold`) VALUES
(4, 'Chevrolet', 30000000.00, 'Deleted', '', 2, 1),
(5, 'honda', 30000000.00, 'Deleted', 'src/userimages/313856583_887080819129243_8741068835114826621_n.png', 2, 0),
(8, 'Raptors', 100000000.00, 'Deleted', '', 0, 1),
(9, 'BMW', 1000000000.00, 'Deleted', '', -2, 3),
(10, 'Volkswagen', 2147483647.00, 'Deleted', '', 1, 0),
(11, 'Mercedes-Benz', 2147483647.00, 'Deleted', '', 1, 0),
(12, 'Audi', 2147483647.00, 'Deleted', '', 0, 2),
(13, 'Hyundai', 200000000.00, 'Deleted', '', 0, 1),
(14, 'Ferrari', 2147483647.00, 'Deleted', '', 2, 0),
(15, 'Lamborghini', 2147483647.00, 'Deleted', '', 0, 1),
(16, 'Suzuki', 55000000.00, 'Deleted', '', 0, 1),
(17, 'Isuzu', 33000000.00, 'Deleted', '', 0, 3),
(18, 'Mitsubishi', 30000000.00, 'Deleted', '', 0, 1),
(19, 'lam', 100000000000.00, 'Deleted', '', 1, 0),
(20, 'Valkyrie', 10000000000.00, 'Deleted', '', 1, 0),
(22, 'Blaze', 100000000.00, 'Deleted', '', 1, 0),
(23, 'Nitro', 1000000000.00, 'Available', '', 1, 2),
(24, 'Phoenix', 100000000.00, 'Deleted', '', 2, 0),
(25, 'Cobras', 10000000000.00, 'Deleted', '', 0, 2),
(26, 'Miragee', 1000000000.00, 'Available', '', 0, 2),
(27, 'Cyclone', 1000000000.00, 'Available', '', 0, 1),
(28, 'Chaoss', 100000000.00, 'Available', '', 1, 0),
(29, 'Judge Dreddd', 100000000.00, 'Available', '', 0, 1),
(30, 'Optimus Primee', 100000000.00, 'Available', '', 0, 1),
(31, 'Ecto', 100000000.00, 'Available', 'src/userimages/1.png', 2, 1),
(32, 'Mad Max', 10000000000.00, 'Deleted', '', 2, 0),
(33, 'McLovin', 100000000.00, 'Available', '', 0, 1),
(34, 'Undertaker', 100000000000.00, 'Deleted', '', 1, 0),
(35, 'Undertakerss', 20000000.00, 'Available', '', 0, 1),
(36, 'Cobrass', 100000000.00, 'Deleted', '', 0, 1),
(37, '10000cccc', 1000000000000000.00, 'Deleted', '', 1, 0),
(38, '10002cc', 1000000000000.00, 'Deleted', '', 1, 0),
(39, '1cccccccc', 11111111111.00, 'Deleted', '', 1, 0),
(40, '1111111c', 199999999.00, 'Deleted', '', 1, 0),
(41, '1ccccc', 111111111.00, 'Deleted', '', 1, 0),
(42, '1ccccccccccc', 11111111111.00, 'Deleted', '', 1, 0),
(43, 'ccccc11', 1000000000.00, 'Deleted', '', 1, 0),
(44, 'c1111', 1111111111.00, 'Deleted', '', 1, 0),
(45, '250cc', 50000000.00, 'Available', '', 0, 2),
(46, 'supra', 30000000.00, 'Out of Stock', '', 0, 1),
(47, 'supra10', 170000000.00, 'Out of Stock', '', 1, 0),
(48, 'Cookie Monster.', 200000000.00, 'Available', 'src/userimages/ant.PNG', 1, 0),
(49, 'Cookie Monsterrs', 200000000.00, 'Available', 'src/userimages/1.png', 1, 0),
(50, 'nitrooo', 110000000.00, 'Available', 'src/userimages/ant.PNG', 1, 0),
(51, 'sadsa', 1.00, 'Deleted', 'src/userimages/ant.PNG', 21312, 0),
(52, 'assa', 1.00, 'Deleted', 'src/userimages/ant.PNG', 12312, 0),
(53, 'asdsacccc', 1.00, 'Deleted', 'src/userimages/ant.PNG', 1, 0),
(54, 'sas', 1.00, 'Deleted', 'src/userimages/ant.PNG', 1, 0),
(55, 'Poseidon.', 12000000.00, 'Available', 'src/userimages/1039061.jpg', 0, 1),
(56, 'judererr', 1.00, 'Deleted', 'src/userimages/Capture.PNG', 1, 0),
(57, 'sa', 1.00, 'Deleted', 'src/userimages/Bible Quiz No. 4.PNG', 1, 0),
(58, 'daff', 1.00, 'Deleted', 'src/userimages/Quiz No. 6 Score.PNG', 1, 0),
(59, 'ccccc', 1.00, 'Deleted', '', 1, 0),
(60, 'asda', 1222222.00, 'Deleted', 'src/userimages/1.png', 1, 0),
(61, 'x', 1222222.00, 'Deleted', 'src/userimages/2.png', 1, 0),
(62, 'xx', 1222222.00, 'Deleted', 'src/userimages/LESLIE ANN Q. CANOY.jpg', 1, 0),
(63, 'Chaosss', 100000000.00, 'Available', 'src/userimages/CamScanner 02-02-2022 20.21_1.jpg', 1, 0),
(64, 'sss', 111.00, 'Deleted', 'src/userimages/Green Pink Blue Sky and Cat Illustration Desktop Wallpaper.png', 1, 0),
(65, 'qwqwqwqwqwqqw', 111.00, 'Deleted', 'src/userimages/CamScanner 02-02-2022 22.13.jpg', 1, 0);

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
(54, 40, 'agent', '2025-05-18 13:00:26', 'User made transaction ID: Isuzu'),
(57, 40, 'agent', '2025-05-20 14:45:27', 'User made transaction ID: Isuzu'),
(58, 40, 'agent', '2025-05-25 10:51:15', 'User Changed Their Details'),
(59, 40, 'agent', '2025-05-25 10:53:44', 'User Changed Their Details'),
(60, 40, 'agent', '2025-05-25 10:55:09', 'User Changed Their Details'),
(61, 40, 'agent', '2025-05-25 10:58:23', 'User Changed Their Details'),
(62, 40, 'agg', '2025-05-25 11:05:46', 'User Changed Their Details'),
(63, 40, 'lyle', '2025-05-25 11:07:50', 'User Changed Their Details'),
(64, 40, 'lyle', '2025-05-26 09:49:56', 'User made transaction ID: Lamborghini'),
(65, 40, 'lyle', '2025-05-26 09:53:52', 'User made transaction ID: Hyundai'),
(67, 40, 'lyle', '2025-05-26 10:26:39', 'User made transaction ID: Audi'),
(68, 41, 'chan', '2025-05-26 10:27:15', 'Admin Deleted Product: Audi'),
(69, 41, 'chan', '2025-05-26 10:27:29', 'Admin Deleted Product: Mercedes-Benz'),
(70, 41, 'chan', '2025-05-26 10:34:16', 'Admin Deleted Product: Mitsubishi'),
(71, 41, 'chan', '2025-05-26 10:36:58', 'Admin Deleted Product: Suzuki'),
(73, 41, 'chan', '2025-05-26 10:46:30', 'Admin Added a Car Sales: lam'),
(75, 41, 'chan', '2025-05-26 10:56:30', 'Admin Added a Car Sales: Valkyrie'),
(76, 41, 'chan', '2025-05-26 10:56:52', 'Admin Added a Car Sales: Thunderbolt'),
(77, 41, 'chan', '2025-05-26 11:00:12', 'Admin Delete Car: Thunderbolt'),
(78, 41, 'chan', '2025-05-26 11:04:23', 'Admin Added a Car Sales: Blaze'),
(80, 41, 'chan', '2025-05-26 11:07:17', 'Admin Added a Car Sales: Phoenix'),
(81, 41, 'chan', '2025-05-26 11:08:12', 'Admin Added a Car Sales: Cobra'),
(82, 41, 'chan', '2025-05-26 11:08:25', 'Admin Added a Car Sales: Mirage'),
(83, 41, 'chan', '2025-05-26 11:08:48', 'Admin Added a Car Sales: Cyclone'),
(84, 41, 'chan', '2025-05-26 11:09:37', 'Admin Added a Car Sales: Chaos'),
(85, 41, 'chan', '2025-05-26 11:10:54', 'Admin Added a Car Sales: Judge Dredd'),
(86, 41, 'chan', '2025-05-26 11:11:07', 'Admin Added a Car Sales: Optimus Prime'),
(87, 41, 'chan', '2025-05-26 11:11:19', 'Admin Added a Car Sales: Ecto-1'),
(88, 40, 'lyle', '2025-05-26 11:17:40', 'User made transaction ID: Nitro'),
(92, 41, 'chan', '2025-05-26 12:05:23', 'Admin Updated Car: Ecto1'),
(93, 41, 'chan', '2025-05-26 12:05:38', 'Admin Updated Car: Ecto'),
(94, 41, 'chan', '2025-05-26 12:06:01', 'Admin Added a Car Sales: Mad Max'),
(96, 40, 'lyle', '2025-05-26 12:58:04', 'User made transaction for product: Miragee'),
(97, 40, 'lyle', '2025-05-26 13:01:09', 'User made transaction for product: Cobras'),
(98, 41, 'chan', '2025-05-26 13:13:10', 'Admin Updated Car: Nitro'),
(99, 41, 'chan', '2025-05-26 13:32:15', 'Admin Added a Car Sales: McLovin’'),
(100, 41, 'chan', '2025-05-26 13:32:58', 'Admin Added a Car Sales: Undertaker'),
(101, 40, 'lyle', '2025-05-26 14:54:01', 'User made transaction for product: Cyclone'),
(102, 41, 'chan', '2025-05-26 14:55:49', 'Admin Added a Car Sales: Undertakers'),
(103, 41, 'chan', '2025-05-26 14:56:26', 'Admin Added a Car Sales: Cobrass'),
(104, 41, 'chan', '2025-05-26 14:57:40', 'Admin Updated Car: Chaoss'),
(105, 41, 'chan', '2025-05-26 14:57:52', 'Admin Updated Car: Judge Dreddd'),
(106, 41, 'chan', '2025-05-26 14:58:01', 'Admin Updated Car: Optimus Primee'),
(107, 41, 'chan', '2025-05-26 14:58:15', 'Admin Updated Car: Ecto1'),
(108, 41, 'chan', '2025-05-26 14:58:32', 'Admin Updated Car: McLovin'),
(109, 41, 'chan', '2025-05-26 14:58:44', 'Admin Updated Car: Undertakerss'),
(110, 40, 'lyle', '2025-05-26 15:15:16', 'User made transaction for product: Undertakerss'),
(111, 40, 'lyle', '2025-05-26 15:51:46', 'User made transaction for product: Cobrass'),
(112, 40, 'lyle', '2025-05-26 16:02:29', 'User made transaction for product: Nitro'),
(113, 40, 'lyle', '2025-05-26 16:03:12', 'User made transaction for product: McLovin'),
(114, 40, 'lyle', '2025-05-26 16:03:41', 'User made transaction for product: Optimus Primee'),
(115, 40, 'lyle', '2025-05-26 16:08:35', 'User made transaction for product: Ecto1'),
(116, 41, 'chan', '2025-05-26 16:19:41', 'Admin Added a Car Sales: 10000cccc'),
(117, 41, 'chan', '2025-05-26 16:26:40', 'Admin Added a Car Sales: 10002cc'),
(118, 41, 'chan', '2025-05-26 16:26:58', 'Admin Delete Car: 10002cc'),
(119, 41, 'chan', '2025-05-26 16:28:55', 'Admin Added a Car Sales: 1cccccccc'),
(120, 41, 'chan', '2025-05-26 16:29:45', 'Admin Delete Car: 1cccccccc'),
(121, 41, 'chan', '2025-05-26 16:29:57', 'Admin Added a Car Sales: 1111111c'),
(122, 41, 'chan', '2025-05-26 16:30:46', 'Admin Added a Car Sales: 1ccccc'),
(123, 41, 'chan', '2025-05-26 16:34:27', 'Admin Added a Car Sales: 1ccccccccccc'),
(124, 41, 'chan', '2025-05-26 16:37:53', 'Admin Delete Car: 1ccccccccccc'),
(125, 41, 'chan', '2025-05-26 16:38:18', 'Admin Added a Car Sales: ccccc11'),
(126, 41, 'chan', '2025-05-26 16:38:29', 'Admin Delete Car: ccccc11'),
(127, 41, 'chan', '2025-05-26 16:43:30', 'Admin Added a Car Sales: c1111'),
(128, 41, 'chan', '2025-05-27 10:29:09', 'Admin Added a Car Sales: 10000cc'),
(129, 41, 'chan', '2025-05-27 10:29:35', 'Admin Added a Car Sales: supra'),
(130, 40, 'lyle', '2025-05-27 10:30:32', 'User made transaction for product: 10000cc'),
(131, 41, 'chan', '2025-05-27 10:31:14', 'Admin Updated Car: supra'),
(132, 41, 'chan', '2025-05-27 10:31:21', 'Admin Updated Car: supra'),
(133, 41, 'chan', '2025-05-27 10:31:51', 'Admin Updated Car: 250cc'),
(134, 40, 'lyle', '2025-05-27 10:32:23', 'User made transaction for product: supra'),
(135, 41, 'chan', '2025-05-27 10:38:18', 'Admin Added a Car Sales: supra10'),
(136, 41, 'chan', '2025-05-27 12:38:21', 'Admin Added a Car Sales: Cookie Monster.'),
(137, 41, 'chan', '2025-05-27 12:39:07', 'Admin Added a Car Sales: Cookie Monsterrs'),
(138, 40, 'steve', '2025-05-27 12:40:39', 'User Changed Their Details'),
(139, 41, 'canoy', '2025-05-27 12:42:49', 'User Changed Their Details'),
(140, 41, 'canoy', '2025-05-27 12:44:39', 'Admin Added a Car Sales: nitrooo'),
(141, 41, 'canoy', '2025-05-27 12:47:12', 'Admin Added a Car Sales: sadsa'),
(142, 41, 'canoy', '2025-05-27 12:52:54', 'Admin Added a Car Sales: assa'),
(143, 41, 'canoy', '2025-05-27 12:57:39', 'Admin Added a Car Sales: asdsacccc'),
(144, 41, 'canoy', '2025-05-27 13:03:48', 'Admin Added a Car Sales: sas'),
(145, 41, 'canoy', '2025-05-27 13:06:44', 'Admin Updated Car: Ecto1'),
(146, 41, 'canoy', '2025-05-27 13:07:00', 'Admin Updated Car: Ecto'),
(147, 41, 'canoy', '2025-05-27 13:08:25', 'Admin Added a Car Sales: Poseidon.'),
(148, 41, 'canoy', '2025-05-27 13:14:10', 'Admin Added a Car Sales: judererr'),
(149, 41, 'canoy', '2025-05-27 13:19:20', 'Admin Added a Car Sales: sa'),
(150, 41, 'canoy', '2025-05-27 13:23:31', 'Admin Added a Car Sales: daff'),
(151, 41, 'canoy', '2025-05-27 13:33:09', 'Admin Added a Car Sales: ccccc'),
(152, 40, 'steve', '2025-05-27 14:03:40', 'User made transaction for product: Poseidon.'),
(153, 41, 'canoy', '2025-05-27 14:09:53', 'Admin Added The Car: asda'),
(154, 41, 'canoy', '2025-05-27 14:15:41', 'Admin Added a Car Sales: xx'),
(155, 41, 'canoy', '2025-05-27 14:16:27', 'Admin Added a Car Sales: Chaosss'),
(156, 41, 'canoy', '2025-05-27 14:28:21', 'Admin Added a Car Sales: sss'),
(157, 36, 'ambot', '2025-05-27 14:34:53', 'Admin Added a Car Sales: qwqwqwqwqwqqw');

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
  `security_answer` varchar(255) NOT NULL,
  `u_image` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`u_id`, `u_fname`, `u_lname`, `u_email`, `u_contact1`, `u_ty`, `u_usname`, `u_password1`, `u_status`, `security_question`, `security_answer`, `u_image`) VALUES
(1, 'lyle', 'steve', 'lylesteve7@gmail.com', '09567884189', 'Admin', 'lylesteve1', 'quiachon123', 'Active', '', '', NULL),
(22, 'jade', 'vil', 'vila@gmail.com', '124125', 'Admin', 'stve', '123456789', 'Pending', '', '', NULL),
(23, 'juuu', 'jail', 'jugmail@.com', '123342', 'Admin', 'steee', 'haaaaaaa', 'Pending', '', '', NULL),
(24, 'jake', 'waii', 'wai@gmail.com', '09665645', 'User', 'steha', '12345678', 'Active', '', '', NULL),
(28, 'test', 'teswt', 'lylesteve2@gmail.com', '09567884189', 'Admin', 'test', '123456789', 'Active', '', '', NULL),
(29, 'test', 'test', 'lyeste2@gmail.com', '09567884189', 'Admin', 'swwww', '12345678', 'Active', '', '', NULL),
(30, 'lyle', 'steve', 'canoyy@gmail.com', '09567884189', 'Admin', 'tyy', '123456789', 'Active', '', '', NULL),
(31, 'mike', 'bus', 'mike@gmail.com', '09567884189', 'Admin', 'mike', 'mikemike', 'Active', '', '', NULL),
(34, 'mike', 'mike', 'mikes@gmail.com', '09567884189', 'User', 'mikes', 'WZRHGrsBESr8wYFZ9sx0tPURuZgG2lmzyvWpwXPKz8U=', 'Active', '', '', NULL),
(35, 'bus', 'mike', 'lyle@gmail.com', '09567884189', 'User', 'hasher', 'kyDqEfbUJ67ElJY03IZ2E2svqM2tKJ0iZZtEVBq7jFE=', 'Active', '', '', NULL),
(36, 'test', 'test', 'tes1@gmail.com', '09567884189', 'Admin', 'ambot', '73l8gRjwLftklgfdXT+MdiMEjJwGPVMsyVxe16iYpk8=', 'Active', '', '', NULL),
(37, 'steve', 'steeve', 'ste@gmail.com', '09567884189', 'Admin', 'gmail', 'FeKw08M4keuw8e9gnsQZQgwg4yDOlMZfvIwzEkSOsiU=', 'Active', '', '', NULL),
(38, 'wai', 'huuu', 'yul@gmail.com', '09567884189', 'User', 'yul', 't5ehUXltvl6Ts0EwMOc5WaG0ldhDiOmkNZgkzPmwUaI=', 'Active', '', '', NULL),
(39, 'ysys', 'tsts', 'emai@gmail.com', '09567884189', 'Admin', 'yaya', 'ky88G1YlfOhTmsJp16q0JVDaz4gY0HXwvfGZBWKq4+8=', 'Active', 'N/A', 'N/A', NULL),
(40, 'lyle', 'steve', 'fck@gmail.com', '09567884189', 'User', 'steve', 'FeKw08M4keuw8e9gnsQZQgwg4yDOlMZfvIwzEkSOsiU=', 'Active', '', '', 'src/userimages/Capture.PNG'),
(41, 'lylesteve', 'steve', 'chan@gmail.com', '09567884189', 'Admin', 'canoy', 'FeKw08M4keuw8e9gnsQZQgwg4yDOlMZfvIwzEkSOsiU=', 'Active', '', '', 'src/userimages/ant.PNG'),
(42, 'shello', 'syee', 'shlo@gmail.com', '09567884418', 'Admin', 'jade', 'ky88G1YlfOhTmsJp16q0JVDaz4gY0HXwvfGZBWKq4+8=', 'Active', 'What is your dream job?', 'IT', NULL);

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
  MODIFY `o_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `p_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=66;

--
-- AUTO_INCREMENT for table `tbl_logs`
--
ALTER TABLE `tbl_logs`
  MODIFY `log_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=158;

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
