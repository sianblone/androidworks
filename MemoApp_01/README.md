## RecyclerView를 사용한 List 표현

* RecyclerView 만으로는 List를 표현하기 불편한 부분이 있다
* RecyclerView의 요소를 표현할 Adapter를 생성하고, Adapter 내부에 Holder를 생성하여 Inflater를 이용해 모양을 만든다

1. Holder에 사용할 item view 페이지를 생성

## DB 연동하기
* Android OS에는 SQLite라는 소형 DBMS가 내장되어 있다
* SQLite는 범용으로 사용하는 최소한의 기능을 가진 DBMS이다
* 일반적인 SQL을 사용해서 DB 핸들링 가능
* SQLite를 직접 핸들링하지 않고 room이라는 ORM을 사용해서 추상화하고 DB 핸들링하기

## room DB 사용하기
1. table로 사용할 vo를 entity로 선언
2. Dao로 사용할 interface를 정의
3. MemoDataBase : DB 연결과 dao Imp를 생성할 클래스 정의
4. Repository(스프링의 서비스 클래스) : DB에 접근하는 연결 클래스