# Convetion Commits

**메시지 구조**
```angular2html
<type>[optional scope]: <description>

[optional body]

[optional footer(s)]
```

**필드 설명**
1. type: 커밋의 변경 유형을 나타냅니다. 대표적인 유형은 다음과 같습니다
   * feat: 새로운 기능 추가 
   * fix: 버그 수정 
   * docs: 문서 변경 (코드 변경 없음)
   * style: 코드 포맷팅 변경 (기능이나 동작에는 영향 없음)
   * refactor: 리팩토링 (버그 수정이나 기능 추가 없이 코드 개선)
   * test: 테스트 추가 또는 수정 
   * chore: 빌드 프로세스 또는 기타 설정 변경
2.	scope (선택 사항): 변경된 파일이나 기능의 범위를 지정합니다.
3.	description: 변경 사항에 대한 간략한 설명(명령형으로 작성).
4.	body (선택 사항): 변경 사항에 대한 상세 설명.
5.	footer (선택 사항): 참고해야 할 이슈 번호나 브레이킹 체인지 정보.