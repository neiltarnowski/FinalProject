DROP TABLE IF EXISTS Video_orders;
DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS Video;
DROP TABLE IF EXISTS Customer;

CREATE TABLE Customer (
  customer_id int NOT NULL AUTO_INCREMENT,
  first_name varchar(40) NOT NULL,
  last_name varchar(20) NOT NULL,
  email varchar(45) NOT NULL, 
  phone MEDIUMTEXT NOT NULL,
  PRIMARY KEY (customer_id)
);

CREATE TABLE Video (
	video_id int NOT NULL AUTO_INCREMENT,
    title varchar(40) NOT NULL,
    status varchar(100) NOT NULL,
    start_date varchar(45) NOT NULL,
    deadline varchar(45) NOT NULL,
    price decimal(9,2) NOT NULL,
    PRIMARY KEY (video_id)
    );

CREATE TABLE Orders (
  order_id int NOT NULL AUTO_INCREMENT,
  customer_id int NOT NULL,
  price decimal(9, 2) DEFAULT 0.00,
  order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (order_id),
  FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON DELETE CASCADE
);

CREATE TABLE Video_orders (
video_orders_id INT NOT NULL AUTO_INCREMENT,
video_id INT NOT NULL,
order_id INT NOT NULL,
PRIMARY KEY (video_orders_id),
FOREIGN KEY (video_id) REFERENCES Video (video_id) ON DELETE CASCADE,
FOREIGN KEY (order_id) REFERENCES Orders (order_id) ON DELETE CASCADE
);
