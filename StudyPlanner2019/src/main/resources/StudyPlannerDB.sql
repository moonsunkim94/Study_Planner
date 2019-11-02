/*student 테이블*/
CREATE TABLE student (
    SEQ NUMBER NOT NULL,
    ID VARCHAR(30) NOT NULL PRIMARY KEY,
    PASSWORD VARCHAR(15) NOT NULL,
    NAME VARCHAR(15) NOT NULL,
    LEV VARCHAR(5) NOT NULL
);

/*calendar 테이블*/
CREATE TABLE calendar (
    CAID VARCHAR(30) NOT NULL,
    TITLE VARCHAR(50) NOT NULL,
    start_date VARCHAR(30) ,
    end_date VARCHAR(30) ,
    create_date VARCHAR(30) DEFAULT sysdate
);

/*Ggraph 시퀸스*/
CREATE SEQUENCE ggraph_seq
START WITH 1
INCREMENT BY 1
NOMAXVALUE 
NOMINVALUE;

/*ggraph 테이블*/
create table ggraph (
    seq number PRIMARY KEY,
    graID varchar2(30) not null,
    exam_name varchar2(50) not null,
    exam_date date not null,
    exam_subject varchar2(15) not null,
    exam_grade varchar2(6) not null,
    foreign key (graID) references student(id)
);
/*게시판 원글 테이블*/
create table tbl_board (
    bno number not null, 
    title varchar2(200) not null,
    content varchar2(4000),
    writer varchar2(50) not null,
    regdate date default sysdate,
    viewcnt number default 0,
    primary key(bno),
    foreign key (writer) references student(id) 
);
/* 댓글 테이블 */
create table tbl_reply (

    rno number not null, 
    bno number not null, 
    replytext varchar2(1000) not null,
    replyer varchar2(50) not null,
    secret_reply char(1) not null,
    regdate date default sysdate,
    updatedate date default sysdate,
    primary key(rno),
    foreign key (bno) references tbl_board(bno) 
);
/*댓글 시퀸스*/
CREATE SEQUENCE reply_seq;
