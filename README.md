# Vacanza - 사내 휴가 및 재택 신청 서비스

## 설명
* 슬랙과 같이 회사내 소통 서비스
* 조금 특이한것은 회사내 사람들과 소통하는 것이아니라 컴퓨터와 소통하는 것이다. 
* 해당 서비스의 목적은 휴가와 재택근무 신청을 처리하는 소프트웨어이다.
* 요즘 상사에게 승인 받지않는 연차나 휴가를 장려하는 회사들이 it를 중심으로 많아지고있다.
* 이것을 바로바로 처리할 수 있게 하는 회사내 시스템이다.
* mvc가 아닌 rest 서버이며, 뷰는 제공하지 않는다.(오직 서버단만)

## 설계
* 신청 조건과 일수, 양식을 회사에서 공지로 제공한다.(최상단)
* 뷰단의 경우 select 박스로 아래의 4가지 신청서비스중 하나를 고르도록한다.
* 시작날짜와 막일 날짜를 입력한다. 입력은 총 4개로 시작 월/일, 끝 월/일
* 부서와 이름을 입력한다.
* 휴가 또는 재택중 연락가능한 연락처(메일은 자동 매핑됨, 전화번호나 카톡만 입력하면됨)을 한 개 입력한다.
* 첫 화면에서 신청버튼으로 신청하고, 첫화면에 여러사람들이 신청한 휴가 및 재택이 올라온다.(페이징)
* 화면에 제목으로 이름이 뜨고, 그 아래 혹은 옆에 신청한 휴가 및 재택 종류가 표시된다.
* 해당 게시물을 클릭하면 연락처 시작일, 막일 등을 알 수 있다.
* 검색 기능은 제공하지 않으며, 페이징 기능은 제공한다.
* 공지 게시판에서 공지를 작성하면된다.

## 신청 서비스 종류
1. 연차 : 유급 휴가
2. 공가 : 예비군 같은 공적업무 휴가
3. 병가 : 질병
4. 재택 : 재택 근무
* 신청서비스는 select 박스로 선택하기 때문에 파라미터로 넘어온다. 
* 따라서 requestpart로 kind(서비스 종류)와 applyDto를 입력받아야한다.
* dddd

## 도메인 
1. apply - 신청
2. users - 유저
3. role - 권한, enum
4. notice - 공지글

## json body
#### users - signup & login
<pre>
{
    "email" : "yc4852@gmail.com",
    "password" : "1234"
}

{
    "email" : "admin@company.com",
    "password" : "1234"
}
</pre>

#### apply - requestpart로 입력받음, form-data로 넘겨야함. 컨텐츠 타입 항상 확인
<pre>
{
	"user" : "chan",
	"startMon" : 1,
	"startDay" : 12,
	"endMon" : 1,
	"endDay" : 17,
	"depart" : "개발",
	"contact" : "010-1234-5678"
}
kind : 연차/공가/병가/재택

{
	"user" : "hoon",
	"startMon" : 1,
	"startDay" : 13,
	"endMon" : 1,
	"endDay" : 18,
	"depart" : "개발",
	"contact" : "010-2345-6789"
}
kind : 연차/공가/병가/재택
</pre>

#### notice
<pre>
{
	"title" : "notice",
	"content" : "this is notice"
}
</pre>

## api
* / - get
* /user/signup - get & post
* /user/login - get & post
* /apply/home - get
* /apply/post - post
* /notice - get(authenticated)
* /notice/post - post

## ResponseEntity 에서 리다이렉트하기
```
HttpHeaders httpHeaders = new HttpHeaders();
httpHeaders.setLocation(URI.create("경로"));  //해당 경로로 리다이렉트
return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
```