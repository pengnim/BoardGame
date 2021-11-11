create schema bdgame;
use bdgame;
CREATE TABLE `bdgame`.`user` (
  `id` VARCHAR(45) NOT NULL,
  `passwd` VARCHAR(45) NOT NULL,
  `ip` VARCHAR(45) NULL,
  `group` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);
  select * from user;

insert into user values('1234@naver.com','1234',NULL,NULL);
insert into user values('1235@naver.com','1235',NULL,NULL);
insert into user values('1236@naver.com','1236',NULL,NULL);
insert into user values('1237@naver.com','1237',NULL,NULL);
CREATE TABLE `bdgame`.`player` (
  `id` VARCHAR(45) NOT NULL,
  `online` INT NOT NULL DEFAULT 0,
  `victory_d` INT NOT NULL DEFAULT 0,
  `defeat_d` INT NOT NULL default 0,
  `matching_score` INT NOT NULL DEFAULT 0,
  `victory_h` INT NOT NULL DEFAULT 0,
  `defeat_h` INT NOT NULL default 0,
  `mole_score` INT NOT NULL default 0,
  `victory_b` INT NOT NULL default 0,
  `defeat_b` INT NOT NULL default 0,
  PRIMARY KEY (`id`));
  
insert into player
values('1234@naver.com',0,0,0,0,0,0,0,0,0);
insert into player
values('1235@naver.com',0,0,0,0,0,0,0,0,0);
insert into player
values('1236@naver.com',0,0,0,0,0,0,0,0,0);
insert into player
values('1237@naver.com',0,0,0,0,0,0,0,0,0);

  
  
  CREATE TABLE `bdgame`.`davinci_player` (
  `id` VARCHAR(45) NOT NULL,
  `bl_num` INT NOT NULL DEFAULT 0,
  `bl_color` VARCHAR(45),
  `b2_num` INT NOT NULL DEFAULT 0,
  `b2_color` VARCHAR(45),
  `b3_num` INT NOT NULL DEFAULT 0,
  `b3_color` VARCHAR(45),
  `b4_num` INT NOT NULL DEFAULT 0,
  `b4_color` VARCHAR(45),
  `next_turn` INT NOT NULL DEFAULT 1,
  `bl_open` INT,
  `b2_open` INT,
  `b3_open` INT,
  `b4_open` INT,
  `correct` INT,
  `closed` INT,
  `max_cor` INT,
  PRIMARY KEY (`id`));
  CREATE TABLE `bdgame`.`deque2_num` (
  `grp_name` VARCHAR(45) NOT NULL,
  `b1` INT NOT NULL DEFAULT 0,
  `b2` INT NOT NULL DEFAULT 0,
  `b3` INT NOT NULL DEFAULT 0,
  `b4` INT NOT NULL DEFAULT 0,
  `b5` INT NOT NULL DEFAULT 0,
  `b6` INT NOT NULL DEFAULT 0,
  `b7` INT NOT NULL DEFAULT 0,
  `b8` INT NOT NULL DEFAULT 0,
  `b9` INT NOT NULL DEFAULT 0,
  `b10` INT NOT NULL DEFAULT 0,
  `b11` INT NOT NULL DEFAULT 0,
  `b12` INT NOT NULL DEFAULT 0,
  `b13` INT NOT NULL DEFAULT 0,
  `b14` INT NOT NULL DEFAULT 0,
  `b15` INT NOT NULL DEFAULT 0,
  `b16` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`grp_name`));
CREATE TABLE `bdgame`.`deque2_color` (
  `grp_name` VARCHAR(45) NOT NULL,
  `b1` VARCHAR(45),
  `b2` VARCHAR(45),
  `b3` VARCHAR(45),
  `b4` VARCHAR(45),
  `b5` VARCHAR(45),
  `b6` VARCHAR(45),
  `b7` VARCHAR(45),
  `b8` VARCHAR(45),
  `b9` VARCHAR(45),
  `b10` VARCHAR(45),
  `b11` VARCHAR(45),
  `b12` VARCHAR(45),
  `b13` VARCHAR(45),
  `b14` VARCHAR(45),
  `b15` VARCHAR(45),
  `b16` VARCHAR(45),
  PRIMARY KEY (`grp_name`));
  CREATE TABLE `bdgame`.`deque3_color` (
  `grp_name` VARCHAR(45) NOT NULL,
  `b1` VARCHAR(45),
  `b2` VARCHAR(45),
  `b3` VARCHAR(45),
  `b4` VARCHAR(45),
  `b5` VARCHAR(45),
  `b6` VARCHAR(45),
  `b7` VARCHAR(45),
  `b8` VARCHAR(45),
  `b9` VARCHAR(45),
  `b10` VARCHAR(45),
  `b11` VARCHAR(45),
  `b12` VARCHAR(45),
  PRIMARY KEY (`grp_name`));
   CREATE TABLE `bdgame`.`deque3_num` (
  `grp_name` VARCHAR(45) NOT NULL,
  `b1` INT NOT NULL DEFAULT 0,
  `b2` INT NOT NULL DEFAULT 0,
  `b3` INT NOT NULL DEFAULT 0,
  `b4` INT NOT NULL DEFAULT 0,
  `b5` INT NOT NULL DEFAULT 0,
  `b6` INT NOT NULL DEFAULT 0,
  `b7` INT NOT NULL DEFAULT 0,
  `b8` INT NOT NULL DEFAULT 0,
  `b9` INT NOT NULL DEFAULT 0,
  `b10` INT NOT NULL DEFAULT 0,
  `b11` INT NOT NULL DEFAULT 0,
  `b12` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`grp_name`));
  CREATE TABLE `bdgame`.`deque4` (
  `grp_name` VARCHAR(45) NOT NULL,
  `b1_num` INT,
  `b1_color` VARCHAR(45),
  `b2_num` INT,
  `b2_color` VARCHAR(45),
  `b3_num` INT,
  `b3_color` VARCHAR(45),
  `b4_num` INT,
  `b4_color` VARCHAR(45),
  `b5_num` INT,
  `b5_color` VARCHAR(45),
  `b6_num` INT,
  `b6_color` VARCHAR(45),
  `b7_num` INT,
  `b7_color` VARCHAR(45),
  `b8_num` INT,
  `b8_color` VARCHAR(45),
  PRIMARY KEY (`grp_name`));
  CREATE TABLE `bdgame`.`group2` (
  `grp_name` VARCHAR(45),
  `p1` VARCHAR(45),
  `p2` VARCHAR(45),
  PRIMARY KEY (`grp_name`));
   CREATE TABLE `bdgame`.`group3` (
  `grp_name` VARCHAR(45),
  `p1` VARCHAR(45),
  `p2` VARCHAR(45),
  `p3` VARCHAR(45),
  PRIMARY KEY (`grp_name`));
   CREATE TABLE `bdgame`.`group4` (
  `grp_name` VARCHAR(45),
  `p1` VARCHAR(45),
  `p2` VARCHAR(45),
  `p3` VARCHAR(45),
  `p4` VARCHAR(45),
  PRIMARY KEY (`grp_name`));
  CREATE TABLE `bdgame`.`4letter_word` (
  `num` INT NOT NULL,
  `letter` VARCHAR(4) NOT NULL,
  `meaning` VARCHAR(45) ,
  PRIMARY KEY (`letter`));
  CREATE TABLE `bdgame`.`5letter_word` (
  `num` INT NOT NULL,
  `letter` VARCHAR(5) NOT NULL,
  `meaning` VARCHAR(45) ,
  PRIMARY KEY (`letter`));

CREATE TABLE `bdgame`.`6letter_word` (
  `num` INT NOT NULL,
  `letter` VARCHAR(6) NOT NULL,
  `meaning` VARCHAR(45) ,
  PRIMARY KEY (`letter`));