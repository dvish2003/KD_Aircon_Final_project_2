CREATE DATABASE KD_Aircon;

USE KD_Aircon;

CREATE TABLE Register (
                          Register_id VARCHAR(10) PRIMARY KEY,
                          Register_Name VARCHAR(50) NOT NULL,
                          Register_post VARCHAR(50) NOT NULL,
                          Register_Password VARCHAR(30) NOT NULL
);

INSERT INTO Register VALUES('R001','Vishan','Manager','Vishan2003###');


CREATE TABLE Customer (
                          Customer_id VARCHAR(10) PRIMARY KEY,
                          Customer_Name VARCHAR(50) NOT NULL,
                          Customer_Address VARCHAR(50) NOT NULL,
                          Customer_Contact VARCHAR(15) NOT NULL,
                          Customer_Email VARCHAR(50) UNIQUE
);

CREATE TABLE Employee (
                          Employee_id VARCHAR(10) PRIMARY KEY,
                          Employee_Name VARCHAR(50) NOT NULL,
                          Employee_Age Varchar(3) NOT NULL,
                          Employee_Address VARCHAR(50) NOT NULL,
                          Employee_Contact Varchar(15) NOT NULL,
                          Employee_Email VARCHAR(50) UNIQUE
);

CREATE TABLE Location (
                          Customer_id VARCHAR(10),
                          Location_id VARCHAR(10) PRIMARY KEY,
                          Location_Province VARCHAR(50) NOT NULL,
                          Location_City VARCHAR(50) NOT NULL,
                          Location_Address VARCHAR(50) NOT NULL,
                          Location_ZipCode VARCHAR(50) NOT NULL,
                          FOREIGN KEY (Customer_id) REFERENCES Customer(Customer_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Payment (
                         Payment_id VARCHAR(10) PRIMARY KEY,
                         Payment_Amount INT(50) NOT NULL,
                         Payment_Date DATE NOT NULL
);

CREATE TABLE Booking (
                         Booking_id VARCHAR(10) PRIMARY KEY,
                         Employee_id VARCHAR(10) NOT NULL,
                         Location_id VARCHAR(10) NOT NULL,
                         Payment_id VARCHAR(10) NOT NULL,
                         Booking_Date DATE NOT NULL,
                         Place_Date DATE NOT NULL,
                         Booking_Description VARCHAR(100),
                         FOREIGN KEY (Employee_id) REFERENCES Employee(Employee_id)ON UPDATE CASCADE ON DELETE CASCADE,
                         FOREIGN KEY (Location_id) REFERENCES Location(Location_id)ON UPDATE CASCADE ON DELETE CASCADE,
                         FOREIGN KEY (Payment_id) REFERENCES Payment(Payment_id)ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE ShowRoom (
                          ShowRoom_id VARCHAR(10) PRIMARY KEY,
                          ShowRoom_Location VARCHAR(50) NOT NULL

);

CREATE TABLE Product (
                         Product_id VARCHAR(10) PRIMARY KEY,
                         Product_Description VARCHAR(50) NOT NULL,
                         ShowRoom_QtyOnHand INT(50) NOT NULL,
                         Product_UnitPrice INT(50) NOT NULL
);

CREATE TABLE Product_ShowRoom (
                                  Product_id VARCHAR(10),
                                  ShowRoom_id VARCHAR(10),
                                  FOREIGN KEY (Product_id) REFERENCES Product(Product_id) ON UPDATE CASCADE ON DELETE CASCADE,
                                  FOREIGN KEY (ShowRoom_id) REFERENCES ShowRoom(ShowRoom_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE `Order` (
                         Order_id VARCHAR(10) PRIMARY KEY,
                         Customer_id VARCHAR(10) NOT NULL,
                         Payment_id VARCHAR(10) NOT NULL,
                         OrderPlaceDate_Date DATE NOT NULL,
                         FOREIGN KEY (Customer_id) REFERENCES Customer(Customer_id) ON UPDATE CASCADE ON DELETE CASCADE,
                         FOREIGN KEY (Payment_id) REFERENCES Payment(Payment_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE OrderDetails (
                              Product_id VARCHAR(10),
                              Order_id VARCHAR(10),
                              Qty INT(50) NOT NULL,
                              Product_UnitPrice INT(50) NOT NULL,
                              FOREIGN KEY (Product_id) REFERENCES Product(Product_id) ON UPDATE CASCADE ON DELETE CASCADE,
                              FOREIGN KEY (Order_id) REFERENCES `Order`(Order_id) ON UPDATE CASCADE ON DELETE CASCADE
);


Join Query Booking

SELECT
    Booking.Booking_id,
    Employee.Employee_Name,
    Customer.Customer_Name,
    Location.Location_Address,
    Payment.Payment_Amount,
    Booking.Booking_Date
FROM
    Booking
        JOIN
    Employee ON Booking.Employee_id = Employee.Employee_id
        JOIN
    Location ON Booking.Location_id = Location.Location_id
        JOIN
    Payment ON Booking.Payment_id = Payment.Payment_id
        JOIN
    Customer ON Location.Customer_id = Customer.Customer_id;



SELECT o.Order_id, c.Customer_Name, p.Product_Description, od.Qty, od.Product_UnitPrice, py.Payment_Amount
FROM `Order` o
         JOIN Customer c ON o.Customer_id = c.Customer_id
         JOIN OrderDetails od ON o.Order_id = od.Order_id
         JOIN Product p ON od.Product_id = p.Product_id
         JOIN Payment py ON o.Payment_id = py.Payment_id;


SELECT o.Order_id, c.Customer_Name, p.Product_Description, od.Qty, od.Product_UnitPrice, py.Payment_Amount
FROM `Order` o
         JOIN Customer c ON o.Customer_id = c.Customer_id
         JOIN OrderDetails od ON o.Order_id = od.Order_id
         JOIN Product p ON od.Product_id = p.Product_id
         JOIN Payment py ON o.Payment_id = py.Payment_id
WHERE o.Order_id = 'your_order_id_here';


SELECT
    C.Customer_Name,
    L.Location_Address,
    B.Booking_Date
FROM
    Booking B
        INNER JOIN
    Location L ON B.Location_id = L.Location_id
        INNER JOIN
    Customer C ON L.Customer_id = C.Customer_id
WHERE
    B.Booking_Date =  ? ;
INSERT INTO Payment (Payment_id, Payment_Amount, Payment_Date)
VALUES
    ('PAY003', 500, '2024-05-01'),
    ('PAY004', 550, '2024-05-02'),
    ('PAY005', 600, '2024-05-03'),
    ('PAY006', 650, '2024-05-04'),
    ('PAY007', 700, '2024-05-05'),
    ('PAY008', 750, '2024-05-06'),
    ('PAY009', 800, '2024-05-07'),
    ('PAY010', 850, '2024-05-08'),
    ('PAY011', 900, '2024-05-09'),
    ('PAY012', 950, '2024-05-10'),
    ('PAY013', 1000, '2024-05-11'),
    ('PAY014', 1050, '2024-05-12'),
    ('PAY015', 1100, '2024-05-13'),
    ('PAY016', 1150, '2024-05-14'),
    ('PAY017', 1200, '2024-05-15'),
    ('PAY018', 1250, '2024-05-16'),
    ('PAY019', 1300, '2024-05-17'),
    ('PAY020', 1350, '2024-05-18'),
    ('PAY021', 1400, '2024-05-19'),

    ('PAY022', 1450, '2024-05-20'),
    ('PAY023', 1500, '2024-05-21'),
    ('PAY024', 1550, '2024-05-22'),
    ('PAY025', 1600, '2024-05-23'),
    ('PAY026', 1650, '2024-05-24'),
    ('PAY027', 1700, '2024-05-25'),
    ('PAY028', 1750, '2024-05-26'),
    ('PAY029', 1800, '2024-05-27'),
    ('PAY030', 1850, '2024-05-28');

-- Insert data into Order table
INSERT INTO `Order` (Order_id, Customer_id, Payment_id, OrderPlaceDate_Date)
VALUES
    ('O004', 'C004', 'PAY004', '2024-05-02'),
    ('O004', 'C004', 'PAY005', '2024-05-03'),
    ('O004', 'C004', 'PAY006', '2024-05-04'),
    ('O004', 'C004', 'PAY007', '2024-05-05'),
    ('O004', 'C004', 'PAY008', '2024-05-06'),
    ('O004', 'C004', 'PAY009', '2024-05-07'),

    ('O005', 'C006', 'PAY010', '2024-05-08'),
    ('O005', 'C006', 'PAY011', '2024-05-09'),
    ('O005', 'C006', 'PAY012', '2024-05-10'),
    ('O005', 'C006', 'PAY013', '2024-05-11'),

    ('O006', 'C012', 'PAY014', '2024-05-12'),
    ('O006', 'C012', 'PAY015', '2024-05-13'),
    ('O006', 'C012', 'PAY016', '2024-05-14'),
    ('O006', 'C012', 'PAY017', '2024-05-15'),
    ('O006', 'C012', 'PAY018', '2024-05-16'),
    ('O006', 'C012', 'PAY019', '2024-05-17'),

    ('O007', 'C005', 'PAY020', '2024-05-18'),
    ('O007', 'C005', 'PAY021', '2024-05-19'),
    ('O007', 'C005', 'PAY022', '2024-05-20'),
    ('O007', 'C005', 'PAY023', '2024-05-21'),
    ('O007', 'C005', 'PAY024', '2024-05-22'),
    ('O007', 'C005', 'PAY025', '2024-05-23'),
    ('O007', 'C005', 'PAY026', '2024-05-24'),
    ('O007', 'C005', 'PAY027', '2024-05-25'),
    ('O007', 'C005', 'PAY028', '2024-05-26'),
    ('O007', 'C005', 'PAY029', '2024-05-27'),
    ('O007', 'C008', 'PAY030', '2024-05-28');
    ('O008', 'C008', 'PAY004', '2024-05-02'),
    ('O008', 'C008', 'PAY005', '2024-05-03'),
    ('O008', 'C008', 'PAY006', '2024-05-04'),
    ('O008', 'C008', 'PAY007', '2024-05-05'),
    ('O008', 'C008', 'PAY008', '2024-05-06'),
    ('O008', 'C008', 'PAY009', '2024-05-07'),
    ('O008', 'C008', 'PAY010', '2024-05-08'),
    ('O008', 'C008', 'PAY011', '2024-05-09'),
    ('O009', 'C003', 'PAY012', '2024-05-10'),
    ('O009', 'C003', 'PAY013', '2024-05-11'),
    ('O009', 'C003', 'PAY014', '2024-05-12'),
    ('O009', 'C003', 'PAY015', '2024-05-13'),
    ('O009', 'C003', 'PAY016', '2024-05-14'),
    ('O009', 'C003', 'PAY017', '2024-05-15'),
    ('O009', 'C003', 'PAY018', '2024-05-16'),
    ('O009', 'C003', 'PAY019', '2024-05-17'),
    ('O009', 'C003', 'PAY020', '2024-05-18'),
    ('O009', 'C003', 'PAY021', '2024-05-19'),
    ('O009', 'C003', 'PAY022', '2024-05-20'),
    ('O009', 'C003', 'PAY023', '2024-05-21'),
    ('O009', 'C003', 'PAY024', '2024-05-22'),
     ('O010', 'C020', 'PAY004', '2024-05-02'),
     ('O010', 'C020', 'PAY005', '2024-05-03'),
     ('O010', 'C020', 'PAY006', '2024-05-04'),
     ('O010', 'C020', 'PAY007', '2024-05-05'),
     ('O010', 'C020', 'PAY008', '2024-05-06'),
     ('O010', 'C020', 'PAY009', '2024-05-07'),
     ('O010', 'C020', 'PAY010', '2024-05-08'),
     ('O010', 'C020', 'PAY011', '2024-05-09'),
     ('O010', 'C020', 'PAY012', '2024-05-10'),
     ('O010', 'C020', 'PAY013', '2024-05-11'),
     ('O010', 'C020', 'PAY014', '2024-05-12'),
     ('O011', 'C020', 'PAY015', '2024-05-13'),
     ('O011', 'C025', 'PAY016', '2024-05-14'),
     ('O011', 'C025', 'PAY017', '2024-05-15'),
     ('O011', 'C025', 'PAY018', '2024-05-16'),
     ('O011', 'C025', 'PAY019', '2024-05-17'),
     ('O011', 'C025', 'PAY020', '2024-05-18'),
     ('O011', 'C025', 'PAY021', '2024-05-19'),
     ('O011', 'C025', 'PAY022', '2024-05-20'),
     ('O011', 'C025', 'PAY023', '2024-05-21'),
     ('O011', 'C025', 'PAY024', '2024-05-22'),
     ('O011', 'C025', 'PAY025', '2024-05-23'),
     ('O011', 'C025', 'PAY026', '2024-05-24'),
     ('O012', 'C026', 'PAY027', '2024-05-25'),
     ('O012', 'C026', 'PAY028', '2024-05-26'),
     ('O012', 'C026', 'PAY029', '2024-05-27'),
     ('O012', 'C026', 'PAY030', '2024-05-28');
     ('O012', 'C026', 'PAY004', '2024-05-02'),
     ('O012', 'C026', 'PAY005', '2024-05-03'),
     ('O012', 'C026', 'PAY006', '2024-05-04'),
     ('O012', 'C026', 'PAY007', '2024-05-05'),
     ('O012', 'C026', 'PAY008', '2024-05-06'),
     ('O012', 'C026', 'PAY009', '2024-05-07'),
     ('O012', 'C026', 'PAY010', '2024-05-08'),
     ('O012', 'C026', 'PAY011', '2024-05-09'),
     ('O012', 'C026', 'PAY012', '2024-05-10'),
     ('O012', 'C026', 'PAY013', '2024-05-11'),
     ('O012', 'C026', 'PAY014', '2024-05-12'),
     ('O012', 'C026', 'PAY015', '2024-05-13'),
     ('O012', 'C026', 'PAY016', '2024-05-14'),
     ('O013', 'C022', 'PAY017', '2024-05-15'),
     ('O013', 'C022', 'PAY018', '2024-05-16'),
     ('O013', 'C022', 'PAY019', '2024-05-17'),
     ('O013', 'C022', 'PAY020', '2024-05-18'),
     ('O013', 'C022', 'PAY021', '2024-05-19'),
     ('O013', 'C022', 'PAY022', '2024-05-20'),
     ('O013', 'C022', 'PAY023', '2024-05-21'),
     ('O013', 'C022', 'PAY024', '2024-05-22'),