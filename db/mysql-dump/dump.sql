-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Mar 05, 2024 alle 23:12
-- Versione del server: 10.4.28-MariaDB
-- Versione PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `houseofpizza`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(3);

-- --------------------------------------------------------

--
-- Struttura della tabella `t_order`
--

CREATE TABLE `t_order` (
  `id` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `person_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `t_order`
--

INSERT INTO `t_order` (`id`, `date`, `email`, `person_name`) VALUES
(1, '2023-12-09', NULL, 'CLIENTE');

-- --------------------------------------------------------

--
-- Struttura della tabella `t_pizza`
--

CREATE TABLE `t_pizza` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `t_pizza`
--

INSERT INTO `t_pizza` (`id`, `name`, `price`) VALUES
(1, 'Margherita', 6.2),
(2, 'Squisita', 8.9);

-- --------------------------------------------------------

--
-- Struttura della tabella `t_pizza_to_order`
--

CREATE TABLE `t_pizza_to_order` (
  `id_status` int(11) NOT NULL,
  `id_pizza` int(11) NOT NULL,
  `id_order` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `t_pizza_to_order`
--

INSERT INTO `t_pizza_to_order` (`id_status`, `id_pizza`, `id_order`) VALUES
(2, 1, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `t_status`
--

CREATE TABLE `t_status` (
  `id` int(11) NOT NULL,
  `status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `t_status`
--

INSERT INTO `t_status` (`id`, `status`) VALUES
(2, 'Completato');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `t_order`
--
ALTER TABLE `t_order`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `t_pizza`
--
ALTER TABLE `t_pizza`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `t_pizza_to_order`
--
ALTER TABLE `t_pizza_to_order`
  ADD PRIMARY KEY (`id_status`,`id_pizza`,`id_order`);

--
-- Indici per le tabelle `t_status`
--
ALTER TABLE `t_status`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;