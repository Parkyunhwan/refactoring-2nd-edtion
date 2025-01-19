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

