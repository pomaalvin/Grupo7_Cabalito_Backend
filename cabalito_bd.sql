drop database if exists db_cabalito;


create database db_cabalito;


use db_cabalito;

CREATE TABLE brand (
    id_brand int NOT NULL AUTO_INCREMENT,
    brand varchar(50) NOT NULL,
    status tinyint NOT NULL,
    tx_date datetime NOT NULL,
    tx_id_user int NOT NULL,
    tx_host varchar(100) NOT NULL,
    tx_update datetime NOT NULL,
    CONSTRAINT brand_pk PRIMARY KEY (id_brand)
);

-- Table: city
CREATE TABLE city (
    id_city int NOT NULL AUTO_INCREMENT,
    city varchar(50) NOT NULL,
    status tinyint NOT NULL,
    tx_date datetime NOT NULL,
    tx_id_user int NOT NULL,
    tx_host varchar(100) NOT NULL,
    tx_update datetime NOT NULL,
    CONSTRAINT city_pk PRIMARY KEY (id_city)
);

-- Table: color
CREATE TABLE color (
    id_color int NOT NULL AUTO_INCREMENT,
    color varchar(50) NOT NULL,
    status tinyint NOT NULL,
    tx_date datetime NOT NULL,
    tx_id_user int NOT NULL,
    tx_host varchar(100) NOT NULL,
    tx_update datetime NOT NULL,
    CONSTRAINT color_pk PRIMARY KEY (id_color)
);



-- Table: h_person
CREATE TABLE h_person (
    id_h_person int NOT NULL AUTO_INCREMENT,
    id_person int NOT NULL,
    first_name varchar(100) NOT NULL,
    last_name varchar(100) NOT NULL,
    phone_number varchar(50) NOT NULL,
    status tinyint NOT NULL,
    tx_date datetime NOT NULL,
    tx_id_user int NOT NULL,
    tx_host varchar(100) NOT NULL,
    tx_update datetime NOT NULL,
    CONSTRAINT h_person_pk PRIMARY KEY (id_h_person)
);

-- Table: h_publication
CREATE TABLE h_publication (
    id_h_publication int NOT NULL AUTO_INCREMENT,
    id_publication int NOT NULL,
    id_seller int NOT NULL,
    id_brand int NOT NULL,
    id_color int NOT NULL,
    id_city int NOT NULL,
    title varchar(100) NULL,
    model int NOT NULL,
    door_number int NOT NULL,
    license_plate varchar(50) NOT NULL,
    description varchar(255) NOT NULL,
    motor varchar(100) NOT NULL,
    price numeric(9,2) NOT NULL,
    date_publication datetime NOT NULL,
    status tinyint NOT NULL,
    tx_date datetime NOT NULL,
    tx_id_user int NOT NULL,
    tx_host varchar(100) NOT NULL,
    tx_update datetime NOT NULL,
    CONSTRAINT h_publication_pk PRIMARY KEY (id_h_publication)
);

-- Table: h_seller
CREATE TABLE h_seller (
    id_h_seller int NOT NULL AUTO_INCREMENT,
    id_seller int NOT NULL,
    id_person int NOT NULL,
    id_user int NOT NULL,
    status tinyint NOT NULL,
    image_path varchar(255) NOT NULL,
    tx_date datetime NOT NULL,
    tx_id_user int NOT NULL,
    tx_host varchar(100) NOT NULL,
    tx_update datetime NOT NULL,
    CONSTRAINT h_seller_pk PRIMARY KEY (id_h_seller)
);

-- Table: h_user
CREATE TABLE h_user (
    id_h_user int NOT NULL AUTO_INCREMENT,
    id_user int NOT NULL,
    password char(64) NOT NULL,
    email varchar(100) NOT NULL,
    status tinyint NOT NULL,
    tx_date datetime NOT NULL,
    tx_id_user int NOT NULL,
    tx_host varchar(100) NOT NULL,
    tx_update datetime NOT NULL,
    CONSTRAINT h_user_pk PRIMARY KEY (id_h_user)
);

-- Table: image_publication
CREATE TABLE image_publication (
    id_image_publication int NOT NULL AUTO_INCREMENT,
    id_publication int NOT NULL,
    path varchar(255) NOT NULL,
    status tinyint NOT NULL,
    tx_date datetime NOT NULL,
    tx_id_user int NOT NULL,
    tx_host varchar(100) NOT NULL,
    tx_update datetime NOT NULL,
    CONSTRAINT image_publication_pk PRIMARY KEY (id_image_publication)
);

-- Table: mechanic

-- Table: person
CREATE TABLE person (
    id_person int NOT NULL AUTO_INCREMENT,
    first_name varchar(100) NOT NULL,
    last_name varchar(100) NOT NULL,
    phone_number varchar(50) NOT NULL,
    status tinyint NOT NULL,
    tx_date datetime NOT NULL,
    tx_id_user int NOT NULL,
    tx_host varchar(100) NOT NULL,
    tx_update datetime NOT NULL,
    CONSTRAINT person_pk PRIMARY KEY (id_person)
);

-- Table: publication
CREATE TABLE publication (
    id_publication int NOT NULL AUTO_INCREMENT,
    id_seller int NOT NULL,
    id_brand int NOT NULL,
    id_color int NOT NULL,
    id_city int NOT NULL,
    title varchar(100) NOT NULL,
    model int NOT NULL,
    door_number int NULL,
    license_plate varchar(50) NULL,
    description varchar(255) NULL,
    motor varchar(100) NULL,
    price numeric(9,2) NOT NULL,
    date datetime NOT NULL,
    status tinyint NOT NULL,
    tx_date datetime NOT NULL,
    tx_id_user int NOT NULL,
    tx_host varchar(100) NOT NULL,
    tx_update datetime NOT NULL,
    CONSTRAINT publication_pk PRIMARY KEY (id_publication)
);

-- Table: seller
CREATE TABLE seller (
    id_seller int NOT NULL AUTO_INCREMENT,
    id_person int NOT NULL,
    id_user int NOT NULL,
    status tinyint NOT NULL,
    image_path varchar(255) NULL,
    tx_date datetime NOT NULL,
    tx_id_user int NOT NULL,
    tx_host varchar(100) NOT NULL,
    tx_update datetime NOT NULL,
    CONSTRAINT seller_pk PRIMARY KEY (id_seller)
);

-- Table: user
CREATE TABLE user (
    id_user int NOT NULL AUTO_INCREMENT,
    password char(64) NOT NULL,
    email varchar(100) NOT NULL,
    status tinyint NOT NULL,
    tx_date datetime NOT NULL,
    tx_id_user int NOT NULL,
    tx_host varchar(100) NOT NULL,
    tx_update datetime NOT NULL,
    CONSTRAINT user_pk PRIMARY KEY (id_user)
);





-- foreign keys
-- Reference: image_publication (table: image_publication)
ALTER TABLE image_publication ADD CONSTRAINT image_publication FOREIGN KEY image_publication (id_publication)
    REFERENCES publication (id_publication);



-- Reference: publication_brand (table: publication)
ALTER TABLE publication ADD CONSTRAINT publication_brand FOREIGN KEY publication_brand (id_brand)
    REFERENCES brand (id_brand);

-- Reference: publication_city (table: publication)
ALTER TABLE publication ADD CONSTRAINT publication_city FOREIGN KEY publication_city (id_city)
    REFERENCES city (id_city);

-- Reference: publication_color (table: publication)
ALTER TABLE publication ADD CONSTRAINT publication_color FOREIGN KEY publication_color (id_color)
    REFERENCES color (id_color);

-- Reference: sale_seller (table: publication)
ALTER TABLE publication ADD CONSTRAINT sale_seller FOREIGN KEY sale_seller (id_seller)
    REFERENCES seller (id_seller);

-- Reference: seller_person (table: seller)
ALTER TABLE seller ADD CONSTRAINT seller_person FOREIGN KEY seller_person (id_person)
    REFERENCES person (id_person);

-- Reference: seller_user (table: seller)
ALTER TABLE seller ADD CONSTRAINT seller_user FOREIGN KEY seller_user (id_user)
    REFERENCES user (id_user);

-- End of file.
ALTER TABLE publication ADD FULLTEXT (title,description);
-- Insercion de nuevas tablas mechanic, h_mechanic, star
drop table h_mechanic;
drop table mechanic;
drop table star;
-- Table: h_mechanic
CREATE TABLE h_mechanic (
                            id_h_mechanic int NOT NULL AUTO_INCREMENT,
                            id_mechanic int NOT NULL,
                            id_seller int NOT NULL,
                            id_city int NOT NULL,
                            location varchar(255) NOT NULL,
                            shop_name varchar(255) NOT NULL,
                            shop_phone_number varchar(50) NOT NULL,
                            verification_path varchar(255),
                            stars tinyint NOT NULL,
                            stars_count int NOT NULL,
                            status tinyint NOT NULL,
                            tx_date datetime NOT NULL,
                            tx_id_user int NOT NULL,
                            tx_host varchar(100) NOT NULL,
                            tx_update datetime NOT NULL,
                            CONSTRAINT h_mechanic_pk PRIMARY KEY (id_h_mechanic)
);
CREATE TABLE mechanic (
                          id_mechanic int NOT NULL AUTO_INCREMENT,
                          id_seller int NOT NULL,
                          id_city int NOT NULL,
                          location varchar(255) NOT NULL,
                          shop_name varchar(255) NOT NULL,
                          shop_phone_number varchar(50) NOT NULL,
                          stars tinyint NOT NULL,
                          stars_count int NOT NULL,
                          verification_path varchar(255),
                          status tinyint NOT NULL,
                          tx_date datetime NOT NULL,
                          tx_id_user int NOT NULL,
                          tx_host varchar(100) NOT NULL,
                          tx_update datetime NOT NULL,
                          CONSTRAINT mechanic_pk PRIMARY KEY (id_mechanic)
);
-- Table : star
CREATE TABLE star(
                     id_star int NOT NULL AUTO_INCREMENT,
                     id_seller int NOT NULL,
                     id_mechanic int NOT NULL,
                     score tinyint NOT NULL,
                     status tinyint NOT NULL,
                     tx_date datetime NOT NULL,
                     tx_id_user int NOT NULL,
                     tx_host varchar(100) NOT NULL,
                     tx_update datetime NOT NULL,
                     CONSTRAINT star_pk PRIMARY KEY (id_star)
);
-- Reference: mechanic_city (table: mechanic)
ALTER TABLE mechanic ADD CONSTRAINT mechanic_city FOREIGN KEY mechanic_city (id_city)
    REFERENCES city (id_city);

-- Reference: mechanic_persona (table: mechanic)
ALTER TABLE mechanic ADD CONSTRAINT mechanic_seller FOREIGN KEY mechanic_seller (id_seller)
    REFERENCES seller (id_seller);
ALTER TABLE star ADD CONSTRAINT unic_id UNIQUE(id_seller,id_mechanic);