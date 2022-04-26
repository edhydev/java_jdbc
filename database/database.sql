-- -----------------------------------------------------
-- Schema curso_java
-- -----------------------------------------------------
DROP DATABASE IF EXISTS curso_java;
CREATE SCHEMA IF NOT EXISTS curso_java DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE curso_java;

-- -----------------------------------------------------
-- Table curso_java.category
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS curso_java.category
(
    id   INT         NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NULL DEFAULT NULL,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table curso_java.product
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS curso_java.product
(
    id            INT         NOT NULL AUTO_INCREMENT,
    name          VARCHAR(45) NULL DEFAULT NULL,
    price         DOUBLE      NULL DEFAULT NULL,
    register_date DATETIME    NULL DEFAULT NULL,
    category_id   INT         NULL DEFAULT NULL,
    sku           VARCHAR(10) NULL DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX sku_UNIQUE (sku ASC) VISIBLE,
    INDEX product_category_idx (category_id ASC) VISIBLE,
    CONSTRAINT product_category
        FOREIGN KEY (category_id)
            REFERENCES curso_java.category (id)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_bin;

INSERT INTO category(id, name)
VALUES (1, 'Comida y Bebida'),
       (2, 'Tecnología'),
       (3, 'Deportes');