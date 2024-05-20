SET REFERENTIAL_INTEGRITY FALSE;

TRUNCATE TABLE member_reservation;
TRUNCATE TABLE member;
TRUNCATE TABLE reservation;
TRUNCATE TABLE reservation_time;
TRUNCATE TABLE theme;

ALTER TABLE member_reservation ALTER COLUMN ID RESTART WITH 1;
ALTER TABLE member ALTER COLUMN ID RESTART WITH 1;
ALTER TABLE reservation ALTER COLUMN ID RESTART WITH 1;
ALTER TABLE reservation_time ALTER COLUMN ID RESTART WITH 1;
ALTER TABLE theme ALTER COLUMN ID RESTART WITH 1;


INSERT INTO member(name, email, password, role)
VALUES ('초코칩', 'dev.chocochip@gmail.com', '$2a$10$DORK.bYhWWXTEiWjwy9mxu.vodUPmuyiBeiShqRnSRcA1.buwN06K', 'USER');
INSERT INTO member(name, email, password, role)
VALUES ('관리자', 'admin@roomescape.com', '$2a$10$g6o5hc6cd2osCIArbIMKFOxnre8qIEDJ5bPzdabiHvcNBxQPEQoQ2', 'ADMIN');
INSERT INTO member(name, email, password, role)
VALUES ('타칸', 'dev.tacan@gmail.com', '$2a$10$xFQbps5IJ6r9h69yhgYFe.hrbU59NA6snxaCVtllkcheE.nqq..UG', 'USER');

-- 예약 시간 추가
INSERT INTO reservation_time(start_at)
VALUES ('12:00');
INSERT INTO reservation_time(start_at)
VALUES ('13:00');
INSERT INTO reservation_time(start_at)
VALUES ('14:00');

-- 테마 추가
INSERT INTO theme(name, description, thumbnail)
VALUES ('추리', '추리 테마입니다.', 'https://image.yes24.com/goods/73161943/L');
INSERT INTO theme(name, description, thumbnail)
VALUES ('아날로그식', '아날로그식 테마입니다.', 'https://image.yes24.com/goods/62087889/L');

SET REFERENTIAL_INTEGRITY TRUE;