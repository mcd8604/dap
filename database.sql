DROP DATABASE IF EXISTS orderHandle;
CREATE DATABASE orderHandle;
use orderHandle;

--
-- Create the tables
--

CREATE TABLE Customer(
   ID  INT,
   LastName  VARCHAR(50) NOT NULL,
   FirstName VARCHAR(50) NOT NULL,
   Address  VARCHAR(50) NOT NULL,
   City  VARCHAR(50) NOT NULL,
   State  VARCHAR(50) NOT NULL,
   Zipcode  VARCHAR(10),
   Phone  VARCHAR(20),
   Email  VARCHAR(50),
   constraint customer_ID_pk PRIMARY KEY (ID)
); 

CREATE TABLE Ordr(
   OrderID  INT,
   ID  INT,
   Completed  ENUM('y','n'),
   Total DOUBLE,
   constraint ordr_OrderID_pk PRIMARY KEY (OrderID),
   constraint ordr_ID_fk FOREIGN KEY (ID) REFERENCES Customer(ID)
);

CREATE TABLE Supplier(
   SupplierID  INT,
   SupplierName  VARCHAR(50) NOT NULL,
   SupplierAddr  VARCHAR(50) NOT NULL,
   SupplierCity  VARCHAR(50) NOT NULL,
   SupplierState  VARCHAR(50) NOT NULL,
   SupplierZipcode  VARCHAR(10),
   SupplierPhone  VARCHAR(20) NOT NULL,
   SupplierEmail  VARCHAR(50),
   constraint supplier_SupplierID_pk PRIMARY KEY (SupplierID)
);


CREATE TABLE Item(
   ItemID  INT,
   ItemName VARCHAR(50),
   ItemDesc VARCHAR(200),
   SalePrice DOUBLE,
   SupplierPrice DOUBLE,
   SupplierID INT,
   constraint item_ItemID_pk PRIMARY KEY (ItemID),
   constraint item_SupplierID_fk FOREIGN KEY (SupplierID) REFERENCES Supplier(SupplierID)
);


CREATE TABLE OrderItem(
   OrderID  INT,
   ItemID  INT,
   Quantity INT,
   constraint orderitem_pk PRIMARY KEY (OrderID, ItemID),
   constraint orderitem_OrderID_fk FOREIGN KEY (OrderID) REFERENCES Ordr(OrderID),
   constraint orderitem_ItemID_fk FOREIGN KEY (ItemID) REFERENCES Item(ItemID)
);





--
-- Dumping data for tables
--

INSERT INTO Customer VALUES (1,'Smtih','John','1 Main Street','Rochester','NY','14623','(585)987-6543','jsmith@rit.net');

INSERT INTO Ordr VALUES (1,'1','y',null);

INSERT INTO Supplier VALUES (1,'Wegmans','1 Wegmans Place','Rochester','NY','14623','(585)220-1234','weg@wegmans.org');

INSERT INTO Item VALUES (1,'Apple','Granny Smith Apples',3.99,1.99,1);
INSERT INTO Item VALUES (2,'Orange','California Oranges',4.99,2.99,1);
INSERT INTO Item VALUES (3,'Bananas','Rochester Bananas',2.99,1.99,1);

INSERT INTO OrderItem VALUES ('1','1','3');
INSERT INTO OrderItem VALUES ('1','2','2');

--
-- Here is a query that will print out the order number and total price
--

-- SELECT Ordr.OrderID, sum(OrderItem.Quantity * Item.SalePrice) AS Total FROM Ordr RIGHT JOIN (OrderItem LEFT JOIN Item ON OrderItem.ItemID = Item.ItemID) ON Ordr.OrderID = OrderItem.OrderID GROUP BY OrderItem.OrderID;

--
-- Here is the insert code to put a value into the total spot of the order table
-- INSERT INTO Ordr (Total) VALUES (???) WHERE Ordr.OrderID = ???;
--