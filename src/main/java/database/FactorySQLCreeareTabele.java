package database;

import java.util.Date;

import static database.Constants.Tables.*;
import static database.Constants.Tables.USER_ROLE;

public class FactorySQLCreeareTabele {

    public String getCreateSQLForTable(String table) {
        switch (table) {


            case USER:
                return "CREATE TABLE IF NOT EXISTS `user` (" +
                        "  id INT NOT NULL AUTO_INCREMENT," +
                        "  username VARCHAR(200) NOT NULL," +
                        "  password VARCHAR(64) NOT NULL," +
                        "  PRIMARY KEY (id))";


            case ROLE:
                return "  CREATE TABLE IF NOT EXISTS `role` (" +
                        "  id INT NOT NULL AUTO_INCREMENT," +
                        "  role VARCHAR(100) NOT NULL," +
                        "  PRIMARY KEY (id))" ;

            case RIGHT:
                return "  CREATE TABLE IF NOT EXISTS `right` (" +
                        "  `id` INT NOT NULL AUTO_INCREMENT," +
                        "  `right` VARCHAR(100) NOT NULL," +
                        "  PRIMARY KEY (`id`))";

            case ROLE_RIGHT:
                return  " CREATE TABLE IF NOT EXISTS role_right (" +
                        "  id INT NOT NULL AUTO_INCREMENT," +
                        "  role_id INT NOT NULL," +
                        "  right_id INT NOT NULL," +
                        "  PRIMARY KEY (id)," +
                        "  UNIQUE INDEX id_UNIQUE (id ASC)," +
                        "  INDEX role_id_idx (role_id ASC)," +
                        "  INDEX right_id_idx (right_id ASC)," +
                        "  CONSTRAINT role_id" +
                        "    FOREIGN KEY (role_id)" +
                        "    REFERENCES role (id)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE," +
                        "  CONSTRAINT right_id" +
                        "    FOREIGN KEY (right_id)" +
                        "    REFERENCES `right` (id)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE);";

            case USER_ROLE:
                return  "CREATE TABLE IF NOT EXISTS user_role (" +
                        "  id INT NOT NULL AUTO_INCREMENT," +
                        "  user_id INT NOT NULL," +
                        "  role_id INT NOT NULL," +
                        "  PRIMARY KEY (id)," +
                        "  UNIQUE INDEX id_UNIQUE (id ASC)," +
                        "  INDEX user_id_idx (user_id ASC)," +
                        "  INDEX role_id_idx (role_id ASC)," +
                        "  CONSTRAINT user_fkid" +
                        "    FOREIGN KEY (user_id)" +
                        "    REFERENCES user (id)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE," +
                        "  CONSTRAINT role_fkid" +
                        "    FOREIGN KEY (role_id)" +
                        "    REFERENCES role (id)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE);";
            case CLIENT:
                return "  CREATE TABLE IF NOT EXISTS `client` (" +
                        "  id INT NOT NULL AUTO_INCREMENT," +
                        "  nume VARCHAR(100) NOT NULL," +
                        "  adresa VARCHAR(100) NOT NULL," +
                        "  codNumericPersonal VARCHAR(100) NOT NULL," +
                        "  idCardNumber int NOT NULL," +
                        "  PRIMARY KEY (id)," +
                        "  UNIQUE INDEX id_UNIQUE (id ASC));";

            case CONT:
                return "  CREATE TABLE IF NOT EXISTS cont (" +
                        "  id INT NOT NULL AUTO_INCREMENT," +
                        "  idClient INT NOT NULL,"+
                        "  numarDeIdentificare INT NOT NULL," +
                        "  tip VARCHAR(100) NOT NULL," +
                        "  sold DOUBLE(40,30) NOT NULL," +
                        "  dataCreeari DATE NOT NULL," +
                        "  CONSTRAINT id_fkclient" +
                        "    FOREIGN KEY (idClient)" +
                        "    REFERENCES client (id)" +
                        "   ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE," +
                        "  PRIMARY KEY (id))" ;
            case REPORT:
                return "CREATE TABLE IF NOT EXISTS `report` (" +
                        "  id INT NOT NULL AUTO_INCREMENT," +
                        "  idEmployee INT NOT NULL," +
                        "  dataEfectuarii DATE NOT NULL," +
                        "  activitate VARCHAR(64) NOT NULL," +
                        "  CONSTRAINT id_fkemployee" +
                        "    FOREIGN KEY (idEmployee)" +
                        "    REFERENCES `user`(id)" +
                        "   ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE," +
                        "  PRIMARY KEY (id));" ;



            default:
                return "";

        }
    }

}
