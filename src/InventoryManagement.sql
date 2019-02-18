-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Feb 18, 2019 at 12:30 PM
-- Server version: 5.7.23
-- PHP Version: 7.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `inventory_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `categoryId` int(11) NOT NULL,
  `categoryName` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `entryList`
--

CREATE TABLE `entryList` (
  `userId` int(11) NOT NULL,
  `itemId` varchar(20) NOT NULL,
  `entryDate` datetime NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `item`
--

CREATE TABLE `item` (
  `itemId` varchar(20) NOT NULL,
  `itemName` varchar(20) NOT NULL,
  `itemPrice` double NOT NULL,
  `itemStock` int(11) NOT NULL,
  `categoryId` int(11) NOT NULL,
  `storeId` int(11) NOT NULL,
  `minimalStockLevel` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `sale`
--

CREATE TABLE `sale` (
  `saleId` varchar(14) NOT NULL,
  `itemId` varchar(20) NOT NULL,
  `userId` int(11) NOT NULL,
  `saleDate` datetime NOT NULL,
  `itemPrice` double NOT NULL,
  `quantity` int(11) NOT NULL,
  `totalPrice` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `storeLocation`
--

CREATE TABLE `storeLocation` (
  `storeId` int(11) NOT NULL,
  `storePlace` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userId` int(11) NOT NULL,
  `userName` varchar(30) NOT NULL,
  `userLevelId` int(11) NOT NULL,
  `password` varchar(30) NOT NULL,
  `email` varchar(30) DEFAULT NULL,
  `address` varchar(30) NOT NULL,
  `phNumber` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `userLevel`
--

CREATE TABLE `userLevel` (
  `userLevelId` int(11) NOT NULL,
  `userLevel` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`categoryId`);

--
-- Indexes for table `entryList`
--
ALTER TABLE `entryList`
  ADD PRIMARY KEY (`userId`,`itemId`,`entryDate`),
  ADD KEY `itemId` (`itemId`);

--
-- Indexes for table `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`itemId`),
  ADD KEY `categoryId` (`categoryId`),
  ADD KEY `storeId` (`storeId`);

--
-- Indexes for table `sale`
--
ALTER TABLE `sale`
  ADD PRIMARY KEY (`saleId`,`itemId`),
  ADD KEY `userId` (`userId`),
  ADD KEY `itemId` (`itemId`);

--
-- Indexes for table `storeLocation`
--
ALTER TABLE `storeLocation`
  ADD PRIMARY KEY (`storeId`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userId`),
  ADD KEY `userLevelId` (`userLevelId`);

--
-- Indexes for table `userLevel`
--
ALTER TABLE `userLevel`
  ADD PRIMARY KEY (`userLevelId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `categoryId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `storeLocation`
--
ALTER TABLE `storeLocation`
  MODIFY `storeId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `userLevel`
--
ALTER TABLE `userLevel`
  MODIFY `userLevelId` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `entryList`
--
ALTER TABLE `entryList`
  ADD CONSTRAINT `entrylist_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`),
  ADD CONSTRAINT `entrylist_ibfk_2` FOREIGN KEY (`itemId`) REFERENCES `item` (`itemId`);

--
-- Constraints for table `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `item_ibfk_1` FOREIGN KEY (`categoryId`) REFERENCES `category` (`categoryId`),
  ADD CONSTRAINT `item_ibfk_2` FOREIGN KEY (`storeId`) REFERENCES `storeLocation` (`storeId`);

--
-- Constraints for table `sale`
--
ALTER TABLE `sale`
  ADD CONSTRAINT `sale_ibfk_1` FOREIGN KEY (`itemId`) REFERENCES `item` (`itemId`),
  ADD CONSTRAINT `sale_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`userLevelId`) REFERENCES `userLevel` (`userLevelId`);
