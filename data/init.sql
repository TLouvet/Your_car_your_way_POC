CREATE DATABASE IF NOT EXISTS your_car_your_way;
USE your_car_your_way;

-- Création de la table 'user'
CREATE TABLE user (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    login VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);

-- Création de la table 'customer'
CREATE TABLE customer (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    payment_card VARCHAR(50),
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);

-- Création de la table 'employee'
CREATE TABLE employee (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);

-- Création de la table 'vehicle_category'
CREATE TABLE vehicle_category (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(10) UNIQUE NOT NULL
);

-- Création de la table 'agency'
CREATE TABLE agency (
    agency_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    city VARCHAR(100),
    zip_code VARCHAR(20),
    address VARCHAR(255)
);

-- Création de la table 'vehicle'
CREATE TABLE vehicle (
    vehicle_id INT AUTO_INCREMENT PRIMARY KEY,
    registration VARCHAR(50) UNIQUE NOT NULL,
    vehicle_category_id INT,
    agency_id INT,
    is_available BIT NOT NULL DEFAULT 1,
    FOREIGN KEY (vehicle_category_id) REFERENCES vehicle_category(id),
    FOREIGN KEY (agency_id) REFERENCES agency(agency_id)
);

-- Création de la table 'offer'
CREATE TABLE offer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    starting_city VARCHAR(100),
    starting_date DATE,
    ending_city VARCHAR(100),
    ending_date DATE,
    price FLOAT,
    agency_id INT,
    vehicle_category_id INT,
    FOREIGN KEY (agency_id) REFERENCES agency(agency_id),
    FOREIGN KEY (vehicle_category_id) REFERENCES vehicle_category(id)
);

-- Création de la table 'booking'
CREATE TABLE booking (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    offer_id INT,
    status VARCHAR(20),
    payment_id VARCHAR(50),
    vehicle_id INT,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
    FOREIGN KEY (offer_id) REFERENCES offer(id),
    FOREIGN KEY (vehicle_id) REFERENCES vehicle(vehicle_id)
);

-- Création de la table 'conversation'
CREATE TABLE conversation (
    conversation_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    employee_id INT,
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (employee_id) REFERENCES user(user_id)
);

-- Création de la table 'chat_message'
CREATE TABLE chat_message (
    message_id INT AUTO_INCREMENT PRIMARY KEY,
    conversation_id INT,
    `from` VARCHAR(20),
    message TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (conversation_id) REFERENCES conversation(conversation_id)
);
