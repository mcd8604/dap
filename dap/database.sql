DROP DATABASE IF EXISTS orderHandle;
CREATE DATABASE orderHandle;
use orderHandle;

--
-- Create the tables
--

CREATE TABLE Customer(
   ID  INT AUTO_INCREMENT,
   LastName  VARCHAR(50) NOT NULL,
   FirstName VARCHAR(50) NOT NULL,
   Address  VARCHAR(50) NOT NULL,
   City  VARCHAR(50) NOT NULL,
   State  VARCHAR(50) NOT NULL,
   Zipcode  VARCHAR(10),
   Phone  VARCHAR(20),
   Email  VARCHAR(50),
   CrDate DATETIME,
   constraint customer_ID_pk PRIMARY KEY (ID)
); 

CREATE TABLE Ordr(
   OrderID  INT AUTO_INCREMENT,
   ID  INT,
   Completed  ENUM('y','n'),
   Total DOUBLE,
   CrDate DATETIME,
   constraint ordr_OrderID_pk PRIMARY KEY (OrderID),
   constraint ordr_ID_fk FOREIGN KEY (ID) REFERENCES Customer(ID)
);

CREATE TABLE Supplier(
   SupplierID  INT AUTO_INCREMENT,
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
   ItemID  INT AUTO_INCREMENT,
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

INSERT INTO Customer VALUES (1,'Smtih','John','1 Main Street','Rochester','NY','14623','(585)987-6543','jsmith@rit.net',now());
INSERT INTO Customer VALUES (2,'Clark','John','12 West Ave','Rochester','NY','14623','(585)258-6683','jclark@rit.net',now());
INSERT INTO Customer VALUES (3,'Doe','Jane','42 University Ave','Rochester','NY','14623','(585)777-3549','jdoe@rit.net','2004-05-05 23:56:25');
INSERT INTO Customer VALUES (4,'Doe','Jim','94 Fake Street','Rochester','NY','14623','(585)468-5498','jidoe@rit.net','2007-03-05 10:55:25');
INSERT INTO Customer VALUES (5,'March','Eric','15 South Ave','Rochester','NY','14623','(585)944-4678','emarch@rit.net','2008-01-05 21:23:20');

INSERT INTO Ordr VALUES (1,'1','y',null,null);
INSERT INTO Ordr VALUES (2,'2','y',null,null);
INSERT INTO Ordr VALUES (3,'3','y',null,null);

INSERT INTO Supplier VALUES (1,'Wegmans','1 Wegmans Place','Rochester','NY','14623','(585)220-1234','weg@wegmans.org');
INSERT INTO Supplier VALUES (2,'Walmart','1 Walmart Ave','Rochester','NY','14623','(585)985-4657','wal@walmart.com');
INSERT INTO Supplier VALUES (3,'Office Max','3 Jefferson Ave','Rochester','NY','14623','(585)648-4568','office@max.com');
INSERT INTO Supplier VALUES (4,'Circuit City','21 Hylan Ave','Rochester','NY','14623','(585)235-4657','circuit@city.com');

INSERT INTO Item VALUES (1,'Apple','Granny Smith Apples',3.99,1.99,1);
INSERT INTO Item VALUES (2,'Orange','California Oranges',4.99,2.99,1);
INSERT INTO Item VALUES (3,'Bananas','Rochester Bananas',2.99,1.99,1);
INSERT INTO Item VALUES (4,'TV','Flat Screen HDTV',599.99,499.99,2);
INSERT INTO Item VALUES (5,'DVD Player','Multi-layer DVD player',29.99,19.99,2);
INSERT INTO Item VALUES (6,'Paper','300 sheets white paper',12.99,11.99,3);
INSERT INTO Item VALUES (7,'Pens','Black ball point pens',2.99,1.99,3);
INSERT INTO Item VALUES (8,'Sticky Notes','Pad of yellow sticky notes',3.99,2.99,3);
INSERT INTO Item VALUES (9,'Hard Drive','160 GB Hard Drive',199.99,150.99,4);
INSERT INTO Item VALUES (10,'CDs','10 pack of CD-RWs',12.99,10.99,4);

INSERT INTO OrderItem VALUES ('1','1','3');
INSERT INTO OrderItem VALUES ('1','2','5');
INSERT INTO OrderItem VALUES ('1','3','10');
INSERT INTO OrderItem VALUES ('2','4','1');
INSERT INTO OrderItem VALUES ('2','5','2');
INSERT INTO OrderItem VALUES ('3','7','10');
INSERT INTO OrderItem VALUES ('3','8','5');


UPDATE Ordr SET Completed = 'y', Total = '66.82', CrDate=now() WHERE OrderID = '1';
UPDATE Ordr SET Completed = 'y', Total = '659.97', CrDate=now() WHERE OrderID = '2';
UPDATE Ordr SET Completed = 'y', Total = '49.85', CrDate=now() WHERE OrderID = '3';


--
-- Here is a query that will print out the order number and total price
--

-- SELECT Ordr.OrderID, sum(OrderItem.Quantity * Item.SalePrice) AS Total FROM Ordr RIGHT JOIN (OrderItem LEFT JOIN Item ON OrderItem.ItemID = Item.ItemID) ON Ordr.OrderID = OrderItem.OrderID GROUP BY OrderItem.OrderID;

--
-- Here is the insert code to put a value into the total spot of the order table
-- INSERT INTO Ordr (Total) VALUES (???) WHERE Ordr.OrderID = ???;
--