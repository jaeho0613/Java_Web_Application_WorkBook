-- drop database web05;
create database web05;
use web05;

-- members 테이블
CREATE TABLE members (
    mno INTEGER NOT NULL COMMENT '회원일련번호',
    email VARCHAR(40) NOT NULL COMMENT '이메일',
    pwd VARCHAR(100) NOT NULL COMMENT '암호',
    mname VARCHAR(50) NOT NULL COMMENT '이름',
    cre_date DATETIME NOT NULL COMMENT '가입일',
    mod_date DATETIME NOT NULL COMMENT '마지막암호변경일'
)  COMMENT '회원기본정보';

-- members PK 설정
alter table members add constraint PK_MEMBERS primary key (mno);

-- members의 PK 자동 증가
alter table members modify column mno integer not null auto_increment comment '회원일련번호';

-- EMALL 칼럼 중복 제거 생성
create unique index UIX_MEMBERS on members (email asc);

-- 더미데이터
insert into members(email, pwd, mname, cre_date, mod_date) values('s1@testcom', '1111', '홍길동', now(), now());
insert into members(email, pwd, mname, cre_date, mod_date) values('s2@testcom', '1111', '임꺽정', now(), now());
insert into members(email, pwd, mname, cre_date, mod_date) values('s3@testcom', '1111', '일지매', now(), now());
insert into members(email, pwd, mname, cre_date, mod_date) values('s4@testcom', '1111', '이몽룡', now(), now());
insert into members(email, pwd, mname, cre_date, mod_date) values('s5@testcom', '1111', '성춘향', now(), now());

select mno, email, mname, cre_date
from members
order by mno asc;

-- delete from members
-- where mno = 1;