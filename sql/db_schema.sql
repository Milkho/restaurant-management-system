DROP TABLE IF EXISTS 'dish';
DROP TABLE IF EXISTS 'employee';
DROP TABLE IF EXISTS 'ordering';
DROP TABLE IF EXISTS 'ordersaccount';
DROP TABLE IF EXISTS 'product';
DROP TABLE IF EXISTS 'position';

-- --------------------------------------------------------

CREATE TABLE `dish` (
  `dishID` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `dishName` varchar(20) NOT NULL,
  `netWeight` double NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `dish`
  ADD UNIQUE KEY `dishName` (`dishName`);

-- --------------------------------------------------------

CREATE TABLE `employee` (
  `employeeID` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `firstName` varchar(50) NOT NULL,
  `middleName` varchar(50) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  `positionName` varchar(50) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `phoneNumber` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `hashPassword` bigint(20) NOT NULL,
  `dateOfEmployment` date NOT NULL,
  `dateOfBirth` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `employee`
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `positionName` (`positionName`);

ALTER TABLE `employee`
  ADD CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`positionName`) REFERENCES `position` (`positionName`) ON DELETE CASCADE ON UPDATE CASCADE;

-- --------------------------------------------------------

CREATE TABLE `ordering` (
  `orderID` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `tableID` int(11) NOT NULL,
  `employeeID` int(11) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `ordering`
  ADD KEY `employeeID` (`employeeID`);
ALTER TABLE `ordering`
  ADD CONSTRAINT `ordering_ibfk_1` FOREIGN KEY (`employeeID`) REFERENCES `employee` (`employeeID`) ON DELETE CASCADE ON UPDATE CASCADE;

-- --------------------------------------------------------

CREATE TABLE `ordersaccount` (
  `orderID` int(11) NOT NULL PRIMARY KEY,
  `dishID` int(11) NOT NULL,
  `amount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `ordersaccount`
  ADD KEY `orderID` (`orderID`),
  ADD KEY `mealID` (`dishID`);
  
ALTER TABLE `ordersaccount`
  ADD CONSTRAINT `ordersaccount_ibfk_1` FOREIGN KEY (`orderID`) REFERENCES `ordering` (`orderID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ordersaccount_ibfk_2` FOREIGN KEY (`dishID`) REFERENCES `dish` (`dishID`) ON DELETE CASCADE ON UPDATE CASCADE;
 
-- --------------------------------------------------------

CREATE TABLE `position` (
  `positionName` varchar(50) NOT NULL PRIMARY KEY,
  `salary` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

CREATE TABLE `product` (
  `productID` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `productName` varchar(20) NOT NULL,
  `amount` int(11) NOT NULL,
  `unitOfMeasurement` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -------------------------------------------------------
