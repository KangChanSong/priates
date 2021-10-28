# 인어교주해적단 과제 | 송강찬

## 설치 가이드
해당 프로젝트는 Java 1.8 버전으로 작성됐습니다.  
1.8 보다 높은 버전으로 서버를 실행시키고 싶으시면
다음의 Intellij 설정을 따라해주세요.
* File -> Projcet Structure -> Project 에서 SDK 버전을 변경해주세요.
* 프로젝트 디렉토리의 build.gradle 파일을 열어 sourceCompatibility 를 해당 버전으로 변경해주세요.
  
그리고 build.gradle을 열어 놓은 상태에서 Ctrl + Shift + O 를 눌러 변경을 적용시켜주세요.

## 테이블 생성 가이드
테이블은 product, option 총 두개입니다.  
다음의 SQL 을 실행하여 테이블을 생성해주세요. (H2 Database 기준)
### SQL 
 create table option (  
       option_id bigint generated by default as identity,  
        create_date timestamp,  
        update_date timestamp,  
        name varchar(255),  
        price integer not null,  
        stock integer not null,  
        product_id bigint,  
        primary key (option_id)  
    );  
  
   create table product (  
       product_id bigint generated by default as identity,  
        create_date timestamp,  
        update_date timestamp,  
        closing varchar(255),  
        price integer not null,  
        type varchar(255),  
        description varchar(255),  
        name varchar(255),  
        primary key (product_id)  
    );    


 alter table option 
       add constraint FK5t6etuqa4wl7lyn0ysxnts7q4 
       foreign key (product_id) 
       references product;
      
 테이블이 생성되었으면 다음 쿼리를 실행해 테이블과 제약조건이 잘 생성됐는지 확인해주세요.  
   
show columns from option;  
show columns from product;  

## API 사용 가이드
| 요청 URL  | 메서드 | 응답 형식  | 설명 |
| ------------- | ------------- | ------------- | ------------- |
| /api/v1/product/register | POST  | 없음 | 상품을 등록합니다.  |
| /api/v1/product/get/{id} | GET  | JSON | 상품 id로 상품을 조회합니다.  |
| /api/v1/product/list | GET  | JSON | 상품 목록을 조회합니다.  |
| /api/v1/product/receive-dates/{id} | GET  | JSON | 상품 id로 해당 상품에 대한 수령 날짜를 조회합니다.  |
| /api/v1/product/delete/{id} | DELETE  | 없음 | 상품 id로 해당 상품을 삭제합니다.  |
