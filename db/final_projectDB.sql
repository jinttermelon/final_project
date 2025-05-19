-- MySQL Workbench Forward Engineering

drop schema final_project;

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
    `birth_year` VARCHAR(45) NOT NULL,
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
-- Table `final_project`.`chat_messagechat_message`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `final_project`.`chat_message` (
                                                              `num` INT NOT NULL AUTO_INCREMENT,
                                                              `sender` VARCHAR(45) NOT NULL,
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
    `target_nickname` VARCHAR(45) ,
    `detail` VARCHAR(2000) NULL,
    `reason` VARCHAR(1000) NOT NULL,
    `state` VARCHAR(45) NOT NULL DEFAULT '처리중',
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
    `img_name` VARCHAR(45) NOT NULL DEFAULT 'event_default.png',
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
    `fnum` int NOT NULL auto_increment,
    PRIMARY KEY (`fnum`),
    INDEX `fk/friend/unickname_idx` (`unickname` ASC) VISIBLE,
    CONSTRAINT `fk/friend/unickname`
    FOREIGN KEY (`unickname`)
    REFERENCES `final_project`.`user` (`nickname`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
    ENGINE = InnoDB;

CREATE TABLE event_entry (
     event_num INT,
     nickname VARCHAR(50),
     PRIMARY KEY (event_num, nickname),  -- 복합키: 하나의 이벤트에 한 유저만 1번 참여 가능
     FOREIGN KEY (event_num) REFERENCES event(num) ON DELETE CASCADE,
     FOREIGN KEY (nickname) REFERENCES user(nickname) ON DELETE CASCADE
);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;






ALTER TABLE chat_room ADD chat_info VARCHAR(255);


insert into user(id,pw,nickname,birth_year,gender,sido,riding_year,tel,tel_open,img_name,role,report)
values
    ('admin01','1111','admin01','1999','F','seoul',1,'KT-010-3333-1112','open','default.png','ADMIN','no'),
    ('user02','1111','name02','1999','M','suwon',3,'KT-010-3333-1113','open','default.png','USER','no'),
    ('user03','1111','name03','1999','F','incheon',1,'KT-010-3333-1114','close','default.png','USER','no'),
    ('user04','1111','name04','1999','M','seoul',2,'KT-010-3333-1115','open','default.png','USER','no'),
    ('user05','1111','name05','2000','M','incheon',2,'KT-010-3333-1116','close','default.png','USER','no'),
    ('user06','1111','name06','2000','F','suwon',4,'KT-010-3333-1117','close','default.png','USER','no'),
    ('user07','1111','name07','2000','M','seoul',1,'KT-010-3333-1118','open','default.png','USER','no'),
    ('user08','1111','name08','2010','F','incheon',1,'KT-010-3333-1119','close','default.png','USER','no'),
    ('user09','1111','name09','2020','M','suwon',3,'KT-010-3333-1110','open','default.png','USER','no'),
    ('user10','1111','name10','2020','F','seoul',1,'KT-010-3333-1121','close','default.png','USER','no'),
    ('user11','1111','name11','2010','M','incheon',4,'KT-010-3333-1131','open','default.png','USER','no'),
    ('user12','1111','name12','1990','F','seoul',1,'KT-010-3333-1141','close','default.png','USER','no'),
    ('test01@aaa.com','$2a$10$TJUbAAYslBvpWTSyP4nd7emSqSOW/nvsc.0DObqepI6gO5qeMMmba','testnick01','2002','F','seoul',1,'KT-010-2222-3333','close','default.png','USER','no')

;

INSERT INTO crew (
    name, sido, riding_level, recruit, sche, question, score,
    img_name, leader_nickname, grade, sche_check
)
VALUES
    ('한강 라이더스', '서울', '초급', 10, '5월30일 16시', '자전거를 시작하게 된 계기와 주로 타는 요일은?', 5, 'default.png', 'name02',  '브론즈', 'one'),
    ('부산 해안질주단', '부산', '중급', 12, '6월1일 19시', '바다 근처 라이딩을 좋아하시나요? 그 이유는?', 4, 'default.png', 'name03',  '실버', 'one'),
    ('대전 업힐러즈', '대전', '고급', 8, '격주 토요일 새벽', '업힐 경험이 있으신가요? 어느 정도인가요?', 5, 'default.png', 'name04',  '골드', 'regular'),
    ('광주 슬로우라이더', '광주', '초급', 15, '5월30일 09시', '속도보단 여유로운 라이딩을 좋아하시나요?', 3, 'default.png', 'name05',  '골드', 'one'),
    ('제주 오름러버스', '제주', '중급', 10, '매주 토요일 오전', '가장 좋아하는 오름이 어디인지 알려주세요!', 4, 'default.png', 'name06',  '실버', 'regular');

INSERT INTO cos(nickname, cos_name, info, km, cos_time, route, cdate, like_count, review_count, img, level, open, tag, city)
VALUES
    ('admin01', '한강 야경 코스', '야경이 아름다운 한강변 코스로 초보자도 즐길 수 있습니다.', 15,65, '여의도 한강공원 - 반포 한강공원', now(), 2, 5, 'hangang_dark.jpg', '초급', true, '#한강 #야경 #초보자추천', '서울'),
    ('admin01', '서울 도심 순환 코스', '서울 도심을 한 바퀴 도는 속도감 있는 코스입니다.', 32, 105, '청계천 - 한남대교 - 서울숲 - 광화문', now(), 2, 9, 'seoul_city.jpg', '중급', true, '#도심 #속도감 #서울한바퀴', '서울'),
    ('admin01', '북한산 업힐 코스', '업힐 훈련에 적합한 산악 코스로 체력 향상을 원한다면 추천!', 21,130, '불광역 - 북한산 둘레길 - 우이령길 입구', now(), 2, 4, 'default.png', '고급', true, '#업힐 #북한산 #훈련코스', '서울'),
    ('admin01', '강남 야간 순찰 코스', '야간에도 밝고 안전한 강남 일대 순환 코스.', 13, 50, '강남역 - 압구정 - 삼성동 - 강남역', now(), 2, 6, 'gangnam_dark.jpg', '초급', true, '#야간라이딩 #강남 #안전', '서울'),
    ('admin01', '광안리 해안 코스', '바다를 바라보며 달릴 수 있는 부산 대표 해안 코스입니다.', 14,60, '광안리 해수욕장 - 해운대 해수욕장', now(), 2, 3, 'default.png', '초급', true, '#해안도로 #바다뷰 #부산코스', '부산'),
    ('admin01', '부산 도심 야경 코스', '부산의 야경을 감상하며 달리는 도심 속 힐링 코스입니다.', 17,70, '서면 - 광복로 - 자갈치시장', now(), 1, 7, 'busan_city.jpg', '초급', true, '#부산야경 #힐링코스', '부산'),
    ('admin01', '대전 갑천 순환 코스', '갑천을 따라 순환하는 평탄한 도심형 자전거 코스입니다.', 18,75, '유성온천역 - 엑스포다리 - 대전천 합류점', now(), 2, 4, 'default.png', '중급', true, '#대전 #갑천 #순환코스', '대전'),
    ('admin01', '대전 업힐 챌린지 코스', '대전의 산악지형을 활용한 고난이도 업힐 코스입니다.', 21,140, '계족산 입구 - 장동산림욕장 - 대청호', now(), 1, 2, 'default.png', '고급', true, '#업힐 #대전 #산악코스', '대전'),
    ('admin01', '대구 금호강 코스', '금호강변을 따라 달리는 시원하고 쾌적한 코스입니다.', 19,80, '금호강 체육공원 - 동촌유원지 - 신천 합류점', now(), 2, 5, 'default.png', '중급', true, '#대구 #강변코스 #시원한라이딩', '대구'),
    ('admin01', '대구 도심 탐방 코스', '도심 속 명소들을 자전거로 둘러보는 탐방형 코스.', 10,45, '동성로 - 국채보상운동공원 - 김광석길', now(), 2, 6, 'daegu.jpg', '초급', true, '#대구탐방 #명소투어', '대구'),
    ('admin01', '광주 풍암호수 코스', '풍암호수 주변을 순환하는 도심 속 힐링 코스입니다.', 12,50, '풍암호수공원 - 상무지구 - 유촌동', now(), 2, 3, 'default.png', '초급', true, '#광주 #호수코스 #힐링', '광주'),
    ('admin01', '광주 무등산 업힐 코스', '무등산 자락을 활용한 업힐 및 숲길 혼합 코스입니다.', 18,115, '충장로 - 무등산 국립공원 입구 - 증심사', now(), 2, 2, 'default.png', '고급', true, '#업힐 #광주 #무등산', '광주'),
    ('admin01', '제주 해안 일주 코스', '제주 해안선을 따라 달리는 풍경 좋은 일주 코스.', 40, 165, '이호테우 해변 - 애월 해안도로 - 협재 해수욕장', now(), 1, 11, 'jeju.jpg', '고급', true, '#제주도 #해안도로 #일주코스', '제주'),
    ('admin01', '제주 오름 힐링 코스', '오름과 목장길을 따라 달리는 제주 자연 힐링 코스.', 22,110, '따라비오름 - 성산일출봉 - 섭지코지', now(), 1, 5, 'default.png', '중급', true, '#제주 #오름 #자연라이딩', '제주'),
    ('admin01', '제주 성산 투어 코스', '성산 일대의 주요 관광지를 순회하는 탐방형 코스입니다.', 13,60, '성산일출봉 - 광치기해변 - 우도 선착장', now(), 1, 4, 'default.png', '초급', true, '#성산 #제주여행 #탐방코스', '제주');



INSERT INTO crewboard (
    cnum, title, content, nickname, notice
)
VALUES
    (1, '5월 첫 라이딩 일정', '5월 첫 주 라이딩은 반포 한강공원에서 시작합니다. 오전 9시 집결!',  'name02', '공지' ),
    (1, '초급자 라이딩 후기', '오늘 처음 라이딩 참여했는데 너무 재밌었어요! 다들 친절해서 감사했어요~',  'name07', '일반'),
    (1, '5월 두번째 주 일정', '이번 주는 아라뱃길 코스로 진행됩니다. 모두 헬멧 꼭 착용하세요!',  'name02', '공지'),
    (2, '해운대 야경 라이딩 후기', '해운대 야경 너무 예뻤어요. 밤바다 바라보며 달리는 기분 최고!',   'name08', '일반'),
    (2, '중급 코스 추가 제안', '다음엔 달맞이고개 코스 추가 어때요? 뷰도 좋고 도전 욕구 생겨요!',   'name03', '일반'),
    (2, '6월 라이딩 계획 공유', '6월은 거제도로 원정 라이딩 어때요? 의견 주세요~',   'name09', '공지'),
    (3, '고급 훈련 일정 안내', '계족산 다음은 무학산 업힐 훈련 예정입니다. 각오 단단히!',   'name04', '공지'),
    (3, '훈련 후 스트레칭 방법 공유', '업힐 후 무릎 보호 스트레칭 팁 공유해요~',   'name10', '일반'),
    (3, '고급자 모임 일정 조율', '다음 주 토요일이 좋을까요? 일요일이 좋을까요?',   'name11', '일반'),
    (4, '풍암호수공원 후기도 좋아요', '경치도 좋고 사람도 적어서 좋아요. 다음에도 또 가요!',   'name12', '일반'),
    (4, '초급자용 코스 추천해주세요', '풍암 외에도 다른 초급자 코스 있으면 공유 부탁드려요!',   'name05', '일반'),
    (4, '정기 모임 간식 준비', '이번 주 모임 간식 제가 준비해볼게요~ 과일 어때요?',   'name06', '일반'),
    (5, '성산 투어 사진 모음', '찍은 사진들 구글 드라이브에 업로드했습니다~',   'name02', '일반'),
    (5, '성산 투어 리마인드 공지', '출발 시간 다시 확인 부탁드려요. 오전 7시입니다!',   'name03', '공지'),
    (5, '성산 투어 장비 체크리스트', '라이딩 전 체크할 장비 리스트 공유합니다.',   'name04', '일반'),
    (1, '라이딩 후 식사 장소 추천', '근처 맛집 추천받아요~ 라이딩 후 고기?',   'name08', '일반'),
    (2, '라이딩 중 사고 예방 팁', '무리하지 말고 신호 준수 필수입니다!',   'name09', '일반'),
    (3, 'GPS 트래커 추천', '고급자용 GPS 기기 추천 받아요. 어떤 거 쓰세요?',   'name10', '일반'),
    (4, '풍암 모임 참가자 명단 공유', '현재까지 참가자 12명입니다. 변동 생기면 댓글로!',   'name11', '공지'),
    (5, '수원 투어 소감 나눔', '정말 즐거운 시간이었어요. 다음에도 또 함께해요!',   'testnick01', '일반'),
    (1, '서울 투어 소감 나눔', '정말 즐거운 시간이었어요. 다음에도 또 함께해요!',   'testnick01', '일반'),
    (2, '제주 투어 소감 나눔', '정말 즐거운 시간이었어요. 다음에도 또 함께해요!',   'testnick01', '일반'),
    (3, '부산 투어 소감 나눔', '정말 즐거운 시간이었어요. 다음에도 또 함께해요!',   'testnick01', '일반'),
    (4, '광교 투어 소감 나눔', '정말 즐거운 시간이었어요. 다음에도 또 함께해요!',   'testnick01', '일반'),
    (5, '인천 투어 소감 나눔', '정말 즐거운 시간이었어요. 다음에도 또 함께해요!',   'testnick01', '일반');


INSERT INTO cosreview (cos_num, nickname, grade, content, wdate)
VALUES
    (1, 'name05', 5, '정말 멋진 야경을 즐길 수 있었어요. 초보자도 부담없이 즐길 수 있는 코스입니다. 매번 이 코스로 라이딩을 갈 것 같아요.', NOW()),
    (1, 'name03', 4, '야경이 정말 아름다워요! 다만, 주말이라서 사람들이 많아서 조금 복잡했습니다.', NOW()),
    (2, 'name11', 4, '서울 도심을 한 바퀴 도는 재미있는 코스였어요. 속도감도 좋고 도심의 다양한 매력을 느낄 수 있었습니다.', NOW()),
    (2, 'name02', 5, '도시의 매력을 느낄 수 있는 좋은 코스였습니다. 서울의 랜드마크들을 자전거로 둘러볼 수 있어서 좋았어요.', NOW()),
    (3, 'name08', 3, '업힐이 좀 힘들었지만, 그만큼 성취감도 컸습니다. 다만, 초보자에게는 너무 어려운 코스일 수도 있을 것 같아요.', NOW()),
    (3, 'name07', 4, '힘든 코스지만 끝내고 나면 뿌듯한 느낌이 듭니다. 도전해볼 만한 가치가 있어요!', NOW()),
    (4, 'name10', 5, '강남에서 야간에 라이딩을 즐길 수 있는 좋은 코스였습니다. 안전하고 즐거운 코스였어요.', NOW()),
    (4, 'name06', 4, '조명이 잘 되어 있어서 밤에도 안전하게 라이딩할 수 있었습니다. 강남 일대가 멋지네요.', NOW()),
    (5, 'name12', 5, '부산 해안 코스는 정말 최고예요! 바다와 해변을 보며 라이딩하는 게 너무 좋았습니다.', NOW()),
    (5, 'name09', 5, '광안리에서 해운대까지, 정말 멋진 바다뷰를 즐기며 달릴 수 있는 코스였어요. 꼭 다시 가고 싶습니다.', NOW()),
    (6, 'name04', 4, '부산 도심의 야경을 즐길 수 있는 멋진 코스였어요. 다만, 일방통행 구간이 많아서 조금 불편한 부분이 있었어요.', NOW());

INSERT INTO coslike (nickname, cos_num)
VALUES
    ('name02', 1),
    ('name03', 1),
    ('name04', 2),
    ('name05', 2),
    ('name06', 3),
    ('name07', 3),
    ('name08', 4),
    ('name09', 4),
    ('name10', 5),
    ('name11', 5),
    ('name12', 6),
    ('name02', 6),
    ('name03', 7),
    ('name04', 7),
    ('name05', 8),
    ('name06', 8),
    ('name07', 9),
    ('name08', 9),
    ('testnick01', 10),
    ('name10', 10),
    ('name11', 11),
    ('name12', 11),
    ('testnick01', 12),
    ('name03', 12),
    ('name04', 13),
    ('testnick01', 13),
    ('name06', 14),
    ('testnick01', 14),
    ('name08', 15),
    ('testnick01', 15);

INSERT INTO crewrecord (
    riding_date, member, cnum, riding_cos
)
VALUES
    ('2025-05-15', 15, 2, '서울 도심 순환 코스'),  -- 2번 크루, 5월 15일 활동
    ('2025-05-22', 8, 2, '한강 야경 코스'),  -- 2번 크루, 5월 22일 활동
    ('2025-05-25', 18, 2, '강남 야간 순찰 코스'),  -- 2번 크루, 5월 25일 활동
    ('2025-06-01', 12, 5, '제주 해안 일주 코스'),  -- 5번 크루, 6월 1일 활동
    ('2025-06-05', 10, 5, '제주 성산 투어 코스');  -- 5번 크루, 6월 5일 활동

INSERT INTO comment (bnum, content, nickname, wdate)
VALUES
    (1, '이번 라이딩 너무 좋았어요! 여의도에서 모여서 분위기가 정말 좋았네요.', 'name02', NOW()),
    (1, '정기 라이딩 공지 확인했어요! 모두 열심히 준비합시다!', 'name03', NOW()),
    (2, '해운대 코스는 언제나 좋은 선택이에요! 다음에는 더 많은 사람들이 함께 했으면 좋겠어요.', 'name04', NOW()),
    (2, '날씨가 너무 좋았어요! 이번 라이딩 정말 재미있었습니다.', 'name05', NOW()),
    (3, '계족산 업힐 훈련은 힘들지만 끝나고 나면 뿌듯해요. 도전하는 기분이 최고입니다.', 'name06', NOW()),
    (3, '업힐 훈련은 정말 힘든데, 그래도 도전이 즐겁네요! 다음 주도 기대됩니다.', 'name07', NOW()),
    (4, '정기 모임 공지 확인! 이번 주 일요일 기대돼요. 풍암호수에서 만나요!', 'name08', NOW()),
    (4, '이번 정기 모임도 기대됩니다! 꼭 참석할게요.', 'name09', NOW()),
    (5, '성산 투어 코스 최고였어요! 오름 구간에서 찍은 사진 너무 멋져요.', 'name10', NOW()),
    (5, '성산 투어 코스 정말 재미있었어요! 다음에도 꼭 가고 싶어요.', 'name11', NOW()),
    (5, '성산 투어, 너무 좋았어요. 오름 구간에서의 풍경이 정말 인상 깊었습니다.', 'name12', NOW());

INSERT INTO report (nickname, target_nickname, detail, reason, state, rdate)
VALUES
-- 부적절한 메시지 (댓글)
('name03', 'name05', '커뮤니티 게시글에 다른 사람을 조롱하는 댓글을 남겼습니다. 분위기를 해치는 글입니다.', '부적절한메시지', '처리중', NOW()),
-- 노쇼
('name06', 'name08', '이번 주 정기 라이딩에 참여한다고 했지만, 아무런 연락 없이 나타나지 않았습니다.', '노쇼', '완료', NOW()),
-- 광고성 (댓글)
('name04', 'name07', '라이딩 게시글에 자전거 용품 쇼핑몰 링크를 지속적으로 달고 있습니다.', '광고성', '처리중', NOW()),
-- 부적절한 메시지 (게시글)
('name10', 'name09', '크루 게시판에 정치적인 내용의 게시글을 올려서 분위기를 흐리고 있습니다.', '부적절한메시지', '완료', NOW()),
-- 광고성 (게시글)
('name11', 'name02', '게시판에 개인 블로그 홍보 게시글을 올렸습니다. 커뮤니티 목적과 맞지 않습니다.', '광고성', '처리중', NOW()),
-- 기타
('name12', 'name04', '크루 활동 외적으로 지속적인 사적인 연락을 요구하며 불쾌감을 주었습니다.', '기타', '처리중', NOW()),
('testnick01', 'name02', '크루 활동 외적으로 지속적인 사적인 연락을 요구하며 불쾌감을 주었습니다.', '기타', '처리중', NOW()),
('testnick01', 'name03', '크루 활동 외적으로 지속적인 사적인 연락을 요구하며 불쾌감을 주었습니다.', '기타', '처리중', NOW()),
('name03', 'testnick01', '크루 활동 외적으로 지속적인 사적인 연락을 요구하며 불쾌감을 주었습니다.', '기타', '처리중', NOW()),
('name02', 'testnick01', '크루 활동 외적으로 지속적인 사적인 연락을 요구하며 불쾌감을 주었습니다.', '기타', '처리중', NOW());

INSERT INTO event (
    title, city, prize, dur, partici_num, event_type
)
VALUES
    ('2025 전국 라이딩 페스티벌', '서울', '고급 자전거 + 상금 200만원', '2025-06-01 ~ 2025-06-02', 500, '전체'),
    ('부산 해변 라이딩 챌린지', '부산', '완주 메달 + 라이딩 키트', '2025-05-15 ~ 2025-05-16', 200, '라이딩'),
    ('대전 업힐 라이딩 챌린지', '대전', '우승자 트로피 + 간식 제공', '2025-07-10', 100, '라이딩'),
    ('광주 자전거 제휴 할인 이벤트', '광주', '스포츠 음료 + 제휴상품 할인쿠폰', '2025-05-03 ~ 2025-05-10', 150, '제휴업체'),
    ('제주 오름 자연체험 라이딩', '제주', '제주 특산물 세트 + 기념품', '2025-08-20 ~ 2025-08-21', 120, '기타');

INSERT INTO ad (company, dur, title, content, type)
VALUES
    ('삼성전자', '2025-05-01 ~ 2025-05-31', '갤럭시 신제품 출시', '최신 기술로 무장한 갤럭시 S 시리즈, 사전 예약 시 특별 혜택을 드립니다!', '전체'),
    ('카카오모빌리티', '2025-04-20 ~ 2025-05-20', '카카오T 자전거 할인 이벤트', '자전거 전용 이용자 대상 할인 쿠폰을 지급 중입니다. 지금 확인하세요!', '전체'),
    ('LG유플러스', '2025-04-25 ~ 2025-05-25', 'U+ 자전거 IoT 캠페인', '실시간 위치추적, 충돌 알림까지! 자전거에 IoT를 더하세요.', '전체'),
    ('네이버지도', '2025-04-15 ~ 2025-05-15', '자전거 길찾기 업데이트', '더 정확해진 자전거 전용 경로로 안전하게 라이딩하세요!', '제휴업체'),
    ('스트라바', '2025-04-01 ~ 2025-04-30', '4월 챌린지 이벤트', '이번 달 라이딩 거리 300km 달성 시 스페셜 배지를 드립니다!', '라이딩');

INSERT INTO crewjoin (nickname, answer, approve, join_date, cnum)
VALUES
    ('name05', '자전거를 시작하게 된 계기는 건강을 위해서였고, 주로 주말 오전에 한강 자전거길에서 라이딩을 합니다.', '대기', now(), 1),
    ('name03', '라이딩은 1년 정도 했고, 주로 한강 자전거길을 탑니다.', '승인', now(), 2),
    ('name09', '로드 자전거 사용하며, 빠르게 달리는 걸 좋아합니다.', '거절', now(), 3),
    ('name02', '정기 모임은 주말이면 대부분 참여 가능합니다.', '승인', now(), 4),
    ('name11', '처음이라 기대되고, 함께 달리는 경험을 해보고 싶습니다.', '대기', now(), 5),
    ('name06', '화요일 저녁과 토요일 오전이 가장 편합니다.', '대기', now(), 1),
    ('name07', '자전거 입문 6개월, 서울숲 코스 자주 탑니다.', '승인', now(), 2),
    ('name10', 'MTB 자전거로 업힐 위주로 라이딩합니다.', '거절', now(), 3),
    ('name08', '정기 모임은 매번 참여할 수 있도록 일정을 조율하겠습니다.', '승인', now(), 4),
    ('testnick01', '친목을 기대하고 있고, 협동 라이딩도 배우고 싶습니다.', '승인', now(), 1),
    ('testnick01', '친목을 기대하고 있고, 협동 라이딩도 배우고 싶습니다.', '대기', now(), 2),
    ('testnick01', '친목을 기대하고 있고, 협동 라이딩도 배우고 싶습니다.', '승인', now(), 3);

INSERT INTO friend (fnickname, unickname)
VALUES
-- name02가 여러 명과 친구
('name02', 'name03'),
('name03', 'name02'),
('name02', 'name04'),
('name04', 'name02'),
('name02', 'name05'),
('name05', 'name02'),

-- name03이 여러 명과 친구
('name03', 'name06'),
('name06', 'name03'),
('name03', 'name07'),
('name07', 'name03'),

-- name04가 여러 명과 친구
('name04', 'name08'),
('name08', 'name04'),
('name04', 'name09'),
('name09', 'name04'),

-- name05와 name06이 서로 친구
('name05', 'name06'),
('name06', 'name05'),

-- name07이 name10, name11과 친구
('name07', 'name10'),
('name10', 'name07'),
('name07', 'name11'),
('name11', 'name07'),

-- name08이 name12와 친구
('name08', 'name12'),
('name12', 'name08'),

-- name09가 name10과도 친구
('name09', 'name10'),
('name10', 'name09'),

-- testnick01 의 친구
('name09', 'testnick01'),
('name10', 'testnick01');

commit;