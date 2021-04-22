DROP TABLE IF EXISTS account_holder;
CREATE TABLE account_holder (
  id INT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(45),
  middle_name VARCHAR(45),
  last_name VARCHAR(45),
  ssn VARCHAR(45),
  PRIMARY KEY (`id`)
  );