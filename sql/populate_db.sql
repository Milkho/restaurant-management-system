INSERT INTO `dish` (`dishName`, `netWeight`, `price`) VALUES
('dish1', 100, 100),
('dish2', 120, 120),
('dish3', 140, 140),
('dish4', 160, 160);


INSERT INTO `employee` (`firstName`, `middleName`, `lastName`, `positionName`, `Address`, `phoneNumber`, `username`, `hashPassword`, `dateOfEmployment`, `dateOfBirth`) VALUES
('dfdfd', 'fgdfs', 'fgsgfg', 'cook', 'fgsfgds', '74878444', 'logggim', 3424, '2017-07-03', '2017-07-12'),
('sgfdsg', 'sgfdsg', 'sgfdsg', 'admin', 'sgfdsg', 'sgfdsg', 'sgfdsg', -904353818, '2017-07-18', '2017-07-04'),
('sgfdsg', 'sgfdsg', 'sgfdsg', 'director', 'sgfdsg', 'sgfdsg', 'admin', 92668751, '2017-07-04', '2017-07-04');


INSERT INTO `ordering` (`tableID`, `employeeID`, `date`) VALUES
(10, 1, '2017-07-12'),
(2, 2, '2017-05-16'),
(10, 1, '2017-01-12'),
(4, 2, '2017-03-16');

INSERT INTO `ordersaccount` (`orderID`, `dishID`, `amount`) VALUES
(1, 3, 1),
(2, 4, 10);


INSERT INTO `position` (`positionName`, `salary`) VALUES
('admin', 4000),
('cook', 4000),
('director', 20000),
('waiter', 3500);

INSERT INTO `product` ( `productName`, `amount`, `unitOfMeasurement`) VALUES
('Potato', 40, 'kg'),
('Cucumber', 10, 'kg'),
('Milk', 3, 'l');
