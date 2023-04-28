
-- 회원 테이블 생성 
CREATE TABLE pizza_members (
    member_no NUMBER(2),
    b_day DATE NOT NULL,
    address VARCHAR2(100) NOT NULL,
    phone_no VARCHAR2(13) NOT NULL,
    stamp_cnt NUMBER(2)
);

-- 회원 시퀀스 생성
CREATE SEQUENCE pizza_mem_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 99
    NOCYCLE
    NOCACHE;

    
-- 비회원 테이블 생성
CREATE TABLE non_pizza_members (
    address VARCHAR2(100) NOT NULL,
    phone_no VARCHAR2(13) NOT NULL
);

--------------------------------------------------------------------------------

-- 메뉴 테이블 생성
CREATE TABLE menu (
    menu_no NUMBER(2),
    menu_name VARCHAR2(15) NOT NULL,
    pizza_size VARCHAR2(2) NOT NULL,
    price NUMBER(5) NOT NULL
);

-- 메뉴 시퀀스 생성
CREATE SEQUENCE menu_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 99
    NOCYCLE
    NOCACHE;
    
--------------------------------------------------------------------------------

-- 주문 테이블 생성
CREATE TABLE pizza_order (
    order_no NUMBER(2),
    order_date DATE DEFAULT sysdate,
    member_no NUMBER(2),
    menu_list VARCHAR2(100),
    total_price NUMBER(7)
);

-- 주문 시퀀스 생성
CREATE SEQUENCE pizza_order_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 99
    NOCYCLE
    NOCACHE;
    
