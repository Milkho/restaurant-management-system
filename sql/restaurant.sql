
CREATE TABLE `dish` (
  `dishID` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `dishName` varchar(20) NOT NULL,
  `netWeight` double NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



INSERT INTO `dish` (`dishName`, `netWeight`, `price`) VALUES
('dish1', 100, 100),
('dish2', 120, 120),
('dish3', 140, 140),
('dish4', 160, 160);

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


INSERT INTO `employee` (`firstName`, `middleName`, `lastName`, `positionName`, `Address`, `phoneNumber`, `username`, `hashPassword`, `dateOfEmployment`, `dateOfBirth`) VALUES
('dfdfd', 'fgdfs', 'fgsgfg', 'cook', 'fgsfgds', '74878444', 'logggim', 3424, '2017-07-03', '2017-07-12'),
('sgfdsg', 'sgfdsg', 'sgfdsg', 'admin', 'sgfdsg', 'sgfdsg', 'sgfdsg', -904353818, '2017-07-18', '2017-07-04'),
('sgfdsg', 'sgfdsg', 'sgfdsg', 'director', 'sgfdsg', 'sgfdsg', 'admin', 92668751, '2017-07-04', '2017-07-04');

-- --------------------------------------------------------

CREATE TABLE `ordering` (
  `orderID` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `tableID` int(11) NOT NULL,
  `employeeID` int(11) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



INSERT INTO `ordering` (`tableID`, `employeeID`, `date`) VALUES
(10, 1, '2017-07-12'),
(2, 2, '2017-05-16'),
(10, 1, '2017-01-12'),
(4, 2, '2017-03-16');

-- --------------------------------------------------------

CREATE TABLE `ordersaccount` (
  `orderID` int(11) NOT NULL PRIMARY KEY,
  `dishID` int(11) NOT NULL,
  `amount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `ordersaccount` (`orderID`, `dishID`, `amount`) VALUES
(1, 3, 1),
(2, 4, 10);

-- --------------------------------------------------------

CREATE TABLE `position` (
  `positionName` varchar(50) NOT NULL PRIMARY KEY,
  `salary` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



INSERT INTO `position` (`positionName`, `salary`) VALUES
('admin', 4000),
('cook', 4000),
('director', 20000),
('waiter', 3500);

-- --------------------------------------------------------

CREATE TABLE `product` (
  `productID` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `productName` varchar(20) NOT NULL,
  `amount` int(11) NOT NULL,
  `unitOfMeasurement` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



INSERT INTO `product` ( `productName`, `amount`, `unitOfMeasurement`) VALUES
('Potato', 40, 'kg'),
('Cucumber', 10, 'kg'),
('Milk', 3, 'l');

-- -------------------------------------------------------

ALTER TABLE `dish`
  ADD UNIQUE KEY `dishName` (`dishName`);

--

ALTER TABLE `employee`
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `positionName` (`positionName`);

--

ALTER TABLE `ordering`
  ADD KEY `employeeID` (`employeeID`);

--

ALTER TABLE `ordersaccount`
  ADD KEY `orderID` (`orderID`),
  ADD KEY `mealID` (`dishID`);

--

ALTER TABLE `employee`
  ADD CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`positionName`) REFERENCES `position` (`positionName`) ON DELETE CASCADE ON UPDATE CASCADE;

--

ALTER TABLE `ordering`
  ADD CONSTRAINT `ordering_ibfk_1` FOREIGN KEY (`employeeID`) REFERENCES `employee` (`employeeID`) ON DELETE CASCADE ON UPDATE CASCADE;

--

ALTER TABLE `ordersaccount`
  ADD CONSTRAINT `ordersaccount_ibfk_1` FOREIGN KEY (`orderID`) REFERENCES `ordering` (`orderID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ordersaccount_ibfk_2` FOREIGN KEY (`dishID`) REFERENCES `dish` (`dishID`) ON DELETE CASCADE ON UPDATE CASCADE;
