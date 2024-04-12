CREATE DATABASE KD_Aircon;

USE KD_Aircon;

CREATE TABLE Register (
                          Register_id VARCHAR(10) PRIMARY KEY,
                          Register_Name VARCHAR(50) NOT NULL,
                          Register_Password VARCHAR(30) NOT NULL
);

INSERT INTO Register VALUES('R001','Vishan','Vishan2003###');


CREATE TABLE Customer (
                          Customer_id VARCHAR(10) PRIMARY KEY,
                          Customer_Name VARCHAR(50) NOT NULL,
                          Customer_Address VARCHAR(50) NOT NULL,
                          Customer_Contact INT(15) NOT NULL,
                          Customer_Email VARCHAR(50) UNIQUE
);

CREATE TABLE Employee (
                          Employee_id VARCHAR(10) PRIMARY KEY,
                          Employee_Name VARCHAR(50) NOT NULL,
                          Employee_Age INT(3) NOT NULL,
                          Employee_Address VARCHAR(50) NOT NULL,
                          Employee_Contact INT(15) NOT NULL,
                          Employee_Email VARCHAR(50) UNIQUE
);

CREATE TABLE Location (
                          Location_id VARCHAR(10) PRIMARY KEY,
                          Location_Province VARCHAR(50) NOT NULL,
                          Location_City VARCHAR(50) NOT NULL,
                          Location_Address VARCHAR(50) NOT NULL,
                          Location_ZipCode VARCHAR(50) NOT NULL
);

CREATE TABLE Payment (
                         Payment_id VARCHAR(10) PRIMARY KEY,
                         Payment_Method VARCHAR(50) NOT NULL,
                         Payment_Amount INT(50) NOT NULL,
                         Payment_Date DATE NOT NULL
);

CREATE TABLE Booking (
                         Booking_id VARCHAR(10) PRIMARY KEY,
                         Customer_id VARCHAR(10) NOT NULL,
                         Employee_id VARCHAR(10) NOT NULL,
                         Location_id VARCHAR(10) NOT NULL,
                         Payment_id VARCHAR(10) NOT NULL,
                         Booking_Date DATE NOT NULL,
                         Booking_Time VARCHAR(10) NOT NULL,
                         Booking_Description VARCHAR(100),
                         FOREIGN KEY (Customer_id) REFERENCES Customer(Customer_id) ON UPDATE CASCADE ON DELETE CASCADE,
                         FOREIGN KEY (Employee_id) REFERENCES Employee(Employee_id)ON UPDATE CASCADE ON DELETE CASCADE,
                         FOREIGN KEY (Location_id) REFERENCES Location(Location_id)ON UPDATE CASCADE ON DELETE CASCADE,
                         FOREIGN KEY (Payment_id) REFERENCES Payment(Payment_id)ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE ShowRoom (
                          ShowRoom_id VARCHAR(10) PRIMARY KEY,
                          ShowRoom_Location VARCHAR(50) NOT NULL,
                          ShowRoom_Description VARCHAR(50),
                          ShowRoom_QtyOnHand INT(50) NOT NULL
);

CREATE TABLE Product (
                         Product_id VARCHAR(10) PRIMARY KEY,
                         Product_Description VARCHAR(50) NOT NULL,
                         Product_UnitPrice INT(50) NOT NULL
);

CREATE TABLE Product_ShowRoom (
                                  Product_id VARCHAR(10),
                                  ShowRoom_id VARCHAR(10),
                                  Description VARCHAR(50),
                                  FOREIGN KEY (Product_id) REFERENCES Product(Product_id) ON UPDATE CASCADE ON DELETE CASCADE,
                                  FOREIGN KEY (ShowRoom_id) REFERENCES ShowRoom(ShowRoom_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE `Order` (
                         Order_id VARCHAR(10) PRIMARY KEY,
                         Customer_id VARCHAR(10) NOT NULL,
                         Payment_id VARCHAR(10) NOT NULL,
                         Order_Status VARCHAR(50) NOT NULL,
                         OrderPlaceDate_Date DATE NOT NULL,
                         OrderHandOverDate_Date DATE,
                         FOREIGN KEY (Customer_id) REFERENCES Customer(Customer_id) ON UPDATE CASCADE ON DELETE CASCADE,
                         FOREIGN KEY (Payment_id) REFERENCES Payment(Payment_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE OrderDetails (
                              Product_id VARCHAR(10),
                              Order_id VARCHAR(10),
                              Qty INT(50) NOT NULL,
                              FOREIGN KEY (Product_id) REFERENCES Product(Product_id) ON UPDATE CASCADE ON DELETE CASCADE,
                              FOREIGN KEY (Order_id) REFERENCES `Order`(Order_id) ON UPDATE CASCADE ON DELETE CASCADE
);









