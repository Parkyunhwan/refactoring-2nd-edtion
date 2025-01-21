# 요구사항
* 연극 요청에 대한 비용을 계산하여 영수증을 출력하는 프로그램
  * 장르, 규모에 따른 비용 산출
  * 공연료와 별개인 포인트 지급
  * json파일이 input으로 주어짐

| play.json
```json
{
  "hamlet": {
    "name": "hamlet",
    "type": "tragedy"
  },
  "as-like": {
    "name": "As You Like It",
    "type": "comedy"
  },
  "othello": {
    "name": "Othello",
    "type": "tragedy"
  }
}
```
<br>

| invoice.json
```json
[
  {
    "customer": "BigCo",
    "performances": [
      {
        "playID": "hamlet",
        "audience": 55
      },
      {
        "playID": "as-like",
        "audience": 35
      },
      {
        "playID": "othello",
        "audience": 40
      }
    ]
  }
]
```
# 진행사항
### 기본 코드 작성
* 동작하는 실행 코드와 테스트 코드를 작성.
### 공연 요금 계산 함수 추출하기
* 코드를 분석해서 얻은 정보는 머릿속에 저장되는 휘발성 정보이므로 코드 스스로 무슨일을 하는지 얘기하도록 하자.
* '함수'로 추출해 의미있는 이름을 사용
* '함수 추출하기' 수행 시 주의사항
  * 함수 유효범위에 벗어나는 변수가 있는지 확인
  * 파라미터로 들어온 값은 변경되어선 안된다.


