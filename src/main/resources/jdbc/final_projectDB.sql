-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema final_project
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema final_project
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `final_project` DEFAULT CHARACTER SET utf8 ;
USE `final_project` ;

-- -----------------------------------------------------
-- Table `final_project`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `final_project`.`user` (
  `id` VARCHAR(30) NOT NULL,
  `num` INT NOT NULL AUTO_INCREMENT,
  `pw` LONGTEXT NOT NULL,
  `nickname` VARCHAR(45) NOT NULL,
  `birth_year` INT NOT NULL,
  `gender` VARCHAR(45) NOT NULL,
  `sido` VARCHAR(45) NOT NULL,
  `riding_year` VARCHAR(45) NOT NULL DEFAULT 0,
  `tel` VARCHAR(45) NOT NULL,
  `tel_open` VARCHAR(45) NOT NULL DEFAULT 'open',
  `img_name` VARCHAR(45) NOT NULL DEFAULT 'default.png',
  `role` VARCHAR(45) NOT NULL DEFAULT 'USER',
  `report` VARCHAR(45) NOT NULL DEFAULT 'no',
  PRIMARY KEY (`num`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `nickname_UNIQUE` (`nickname` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `final_project`.`crew`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `final_project`.`crew` (
  `cnum` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `sido` VARCHAR(45) NOT NULL,
  `riding_level` VARCHAR(45) NOT NULL,
  `recruit` INT NOT NULL,
  `sche` VARCHAR(45) NULL,
  `score` INT NOT NULL,
  `img_name` VARCHAR(45) NOT NULL DEFAULT 'default.png',
  `leader_nickname` VARCHAR(45) NOT NULL,
  `grade` VARCHAR(45) NOT NULL DEFAULT '브론즈',
  `sche_check` VARCHAR(45) NOT NULL DEFAULT 'regular',
  `question` VARCHAR(2000) NULL,
  PRIMARY KEY (`cnum`),
  INDEX `fk_crew_leader_nickname_idx` (`leader_nickname` ASC) VISIBLE,
  CONSTRAINT `fk_crew_leader_nickname`
    FOREIGN KEY (`leader_nickname`)
    REFERENCES `final_project`.`user` (`nickname`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `final_project`.`crewboard`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `final_project`.`crewboard` (
  `bnum` INT NOT NULL AUTO_INCREMENT,
  `cnum` INT NOT NULL,
  `title` VARCHAR(200) NOT NULL, 
  `content` VARCHAR(2000) NULL,
  `wdate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `nickname` VARCHAR(45) NOT NULL,
  `notice` VARCHAR(45),
  PRIMARY KEY (`bnum`),
  INDEX `fk_crewboard_cnum_idx` (`cnum` ASC) VISIBLE,
  CONSTRAINT `fk_crewboard_cnum`
    FOREIGN KEY (`cnum`)
    REFERENCES `final_project`.`crew` (`cnum`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `final_project`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `final_project`.`comment` (
  `num` INT NOT NULL AUTO_INCREMENT,
  `bnum` INT NOT NULL,
  `content` VARCHAR(2000) NOT NULL,
  `nickname` VARCHAR(45) NOT NULL,
  `wdate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`num`),
  INDEX `fk_comment_bnum_idx` (`bnum` ASC) VISIBLE,
  CONSTRAINT `fk_comment_bnum`
    FOREIGN KEY (`bnum`)
    REFERENCES `final_project`.`crewboard` (`bnum`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `final_project`.`crewrecord`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `final_project`.`crewrecord` (
  `riding_date` VARCHAR(45) NOT NULL,
  `member` INT NOT NULL,
  `cnum` INT NOT NULL,
  `riding_cos` VARCHAR(45) NOT NULL,
  `num` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`num`),
  INDEX `fk_crewrecord_cnum_idx` (`cnum` ASC) VISIBLE,
  CONSTRAINT `fk_crewrecord_cnum`
    FOREIGN KEY (`cnum`)
    REFERENCES `final_project`.`crew` (`cnum`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `final_project`.`crewjoin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `final_project`.`crewjoin` (
  `nickname` VARCHAR(45) NOT NULL,
  `answer` VARCHAR(2000) NOT NULL,
  `num` INT NOT NULL AUTO_INCREMENT,
  `approve` VARCHAR(45) NOT NULL DEFAULT '대기',
  `join_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `cnum` INT NOT NULL,
  PRIMARY KEY (`num`),
  INDEX `fk_crewjoin_nickname_idx` (`nickname` ASC) VISIBLE,
  INDEX `fk_crewjoin_cnum_idx` (`cnum` ASC) VISIBLE,
  CONSTRAINT `fk_crewjoin_nickname`
    FOREIGN KEY (`nickname`)
    REFERENCES `final_project`.`user` (`nickname`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_crewjoin_cnum`
    FOREIGN KEY (`cnum`)
    REFERENCES `final_project`.`crew` (`cnum`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `final_project`.`cos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `final_project`.`cos` (
  `cos_num` INT NOT NULL AUTO_INCREMENT,
  `nickname` VARCHAR(45) NOT NULL,
  `cos_name` VARCHAR(45) NOT NULL,
  `info` VARCHAR(2000) NOT NULL,
  `km` VARCHAR(45) NOT NULL,
  `cos_time` INT NOT NULL,
  `route` VARCHAR(2000) NOT NULL,
  `cdate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `like_count` INT NOT NULL DEFAULT 0,
  `review_count` INT NOT NULL DEFAULT 0,
  `img` VARCHAR(45) NOT NULL DEFAULT 'default.img',
  `level` VARCHAR(45) NOT NULL DEFAULT '하',
  `open` VARCHAR(45) NOT NULL DEFAULT 'open',
  `tag` VARCHAR(45) NULL,
  `city` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`cos_num`),
  INDEX `fk_cos_nickname_idx` (`nickname` ASC) VISIBLE,
  CONSTRAINT `fk_cos_nickname`
    FOREIGN KEY (`nickname`)
    REFERENCES `final_project`.`user` (`nickname`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `final_project`.`cosreview`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `final_project`.`cosreview` (
  `num` INT NOT NULL AUTO_INCREMENT,
  `cos_num` INT NOT NULL,
  `nickname` VARCHAR(45) NOT NULL,
  `grade` INT NOT NULL,
  `content` VARCHAR(2000) NOT NULL,
  `wdate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`num`),
  INDEX `fk_cosreview_cos_num_idx` (`cos_num` ASC) VISIBLE,
  CONSTRAINT `fk_cosreview_cos_num`
    FOREIGN KEY (`cos_num`)
    REFERENCES `final_project`.`cos` (`cos_num`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `final_project`.`coslike`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `final_project`.`coslike` (
  `nickname` VARCHAR(45) NOT NULL,
  `cos_num` INT NOT NULL,
  `num` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`num`),
  INDEX `fk_coslike_nickname_idx` (`nickname` ASC) VISIBLE,
  INDEX `fk_coslike_cos_num_idx` (`cos_num` ASC) VISIBLE,
  CONSTRAINT `fk_coslike_nickname`
    FOREIGN KEY (`nickname`)
    REFERENCES `final_project`.`user` (`nickname`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_coslike_cos_num`
    FOREIGN KEY (`cos_num`)
    REFERENCES `final_project`.`cos` (`cos_num`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `final_project`.`chat_room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `final_project`.`chat_room` (
  `room_name` VARCHAR(45) NOT NULL,
  `cdate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `num` INT NOT NULL AUTO_INCREMENT,
  `room_id` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`num`),
  UNIQUE INDEX `room_id_UNIQUE` (`room_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `final_project`.`chat_message`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `final_project`.`chat_message` (
  `num` INT NOT NULL AUTO_INCREMENT,
  `sender` VARCHAR(45) NOT NULL,
  `nick` VARCHAR(45) NOT NULL,
  `message` VARCHAR(2000) NOT NULL,
  `room_id` VARCHAR(45) NOT NULL,
  `wdate` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`num`),
  INDEX `fk_chat_message_room_id_idx` (`room_id` ASC) VISIBLE,
  CONSTRAINT `fk_chat_message_room_id`
    FOREIGN KEY (`room_id`)
    REFERENCES `final_project`.`chat_room` (`room_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `final_project`.`report`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `final_project`.`report` (
  `num` INT NOT NULL AUTO_INCREMENT,
  `nickname` VARCHAR(45) NOT NULL,
  `target_nickname` VARCHAR(45) NOT NULL,
  `detail` VARCHAR(2000) NULL,
  `reason` VARCHAR(1000) NOT NULL,
  `state` VARCHAR(45) NOT NULL DEFAULT '완료',
  `rdate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`num`),
  INDEX `fk_report_nickname_idx` (`nickname` ASC) VISIBLE,
  INDEX `fk_report_target_nickname_idx` (`target_nickname` ASC) VISIBLE,
  CONSTRAINT `fk_report_nickname`
    FOREIGN KEY (`nickname`)
    REFERENCES `final_project`.`user` (`nickname`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_report_target_nickname`
    FOREIGN KEY (`target_nickname`)
    REFERENCES `final_project`.`user` (`nickname`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `final_project`.`event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `final_project`.`event` (
  `num` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(1000) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `prize` VARCHAR(45) NOT NULL,
  `dur` VARCHAR(45) NOT NULL,
  `partici_num` INT NOT NULL,
  `event_type` VARCHAR(45) NOT NULL DEFAULT '전체',
  PRIMARY KEY (`num`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `final_project`.`ad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `final_project`.`ad` (
  `num` INT NOT NULL AUTO_INCREMENT,
  `company` VARCHAR(45) NOT NULL,
  `dur` VARCHAR(45) NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `content` VARCHAR(2000) NOT NULL,
  `type` VARCHAR(45) NOT NULL DEFAULT '배너형',
  PRIMARY KEY (`num`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `final_project`.`friend`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `final_project`.`friend` (
  `fnickname` VARCHAR(45) NOT NULL,
  `unickname` VARCHAR(45) NOT NULL,
  `fnum` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`fnum`),
  INDEX `fk/friend/unickname_idx` (`unickname` ASC) VISIBLE,
  CONSTRAINT `fk/friend/unickname`
    FOREIGN KEY (`unickname`)
    REFERENCES `final_project`.`user` (`nickname`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


ALTER TABLE chat_room ADD chat_info VARCHAR(255);