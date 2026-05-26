# team1BE

# 📢 백엔드 초기 세팅 안내

> **감정 + 소비 기반 소비 습관 개선 웹 서비스** 🦁

<br>

아래 내용 확인하고 개발 시작해주세요!

<br>

## 🔗 레포지토리

| 구분 | 레포 |
|------|------|
| Organization | `miniproject-team1` |
| 백엔드 | [`team1BE`](https://github.com/miniproject-team1/team1BE) |
| 프론트엔드 | [`team1FE`](https://github.com/miniproject-team1/team1FE) |

> 깃허브 초대 수락 안 하신 분들은 먼저 수락 부탁드려요!

<br>

## 🚀 시작하기 전 체크리스트

- [ ] 깃허브 초대 수락 완료
- [ ] 레포 clone 받기 (`git clone ...`)
- [ ] README와 초기 세팅 파일 한 번 훑어보기
- [ ] 로컬에서 빌드/실행 되는지 확인
- [ ] 막히는 부분 있으면 바로 단톡방에 공유

<br>

---

# ✅ 개발하면서 꼭 지켜야 할 것들

## 1. 🌿 브랜치 전략

- 기능 개발은 항상 브랜치 따로 파서 작업
    - `feature/기능명` (예: `feature/login`, `feature/diary-save`)
    - `fix/버그명` (예: `fix/calendar-date-bug`)
- *****!!작업 시작 전 항상 최신화 하고 시작하기!!*****

```bash
git checkout main
git pull origin main
git checkout -b feature/기능명
```
작성한 코드는 반드시 본인이 만든 브랜치로 푸시해주신 후 
main브랜치에 pull request로 보내주세요!
<br>

## 2. 📝 커밋 컨벤션

**형식:** `타입: 한 줄 설명`

| 타입 | 설명 | 예시 |
|------|------|------|
| `feat` | 새 기능 추가 | `feat: 로그인 API 구현` |
| `fix` | 버그 수정 | `fix: 캘린더 날짜 표시 오류 수정` |
| `refactor` | 리팩토링 (기능 변화 없음) | `refactor: 일기 저장 로직 분리` |
| `style` | 코드 포맷, 세미콜론 등 | `style: 들여쓰기 정리` |
| `docs` | 문서 수정 | `docs: README 업데이트` |
| `chore` | 빌드, 설정 파일 등 | `chore: gitignore 수정` |
| `test` | 테스트 코드 | `test: 회원가입 테스트 추가` |

> 💡 커밋은 **작은 단위로 자주**, 메시지는 **무엇을 왜 바꿨는지** 알 수 있게!

<br>

## 3. 🔀 PR(Pull Request) 규칙

- PR 제목도 **커밋 컨벤션** 따르기
- 본문에 다음 내용 간단히라도 작성
    - 📌 작업 내용
    - 🔧 변경 사항
    - 🧪 테스트 방법
- **본인 PR을 본인이 머지하지 않기** — 최소 1명 리뷰 후 머지
- 리뷰 요청 오면 **24시간 안에** 확인해주기
- 머지된 브랜치는 **삭제**하기 (레포 깔끔하게 유지)

<br>

## 4. 📑 명세서 기준으로 개발

- **기능명세서**와 **API 명세서**가 기준입니다
- 명세와 다르게 구현해야 할 상황이 생기면
  → **반드시 단톡방 공유 → 합의 후 변경**
- 명세 변경되면 **명세서 문서도 같이 업데이트**
  (코드만 바꾸고 끝내지 않기!)

<br>

## 5. 🔌 API 개발 규칙

API 명세서 기준으로 작업해주세요.

- **Base URL:** `/api/v1/...`
- 엔드포인트, HTTP 메서드, 요청/응답 형식 **임의 변경 금지**
- API 하나 끝나면 명세서 상태 업데이트

| 상태 | 의미 |
|------|------|
| 🔘 시작 전 | 아직 작업 안 함 |
| 🟡 진행 중 | 개발 중 |
| 🟢 완료 | 개발 완료 |

> ✅ 프론트 연동 가능해지면 **"API연동" 상태**도 업데이트해주세요!


<br>

<br>

---

## ⚙️ 로컬 환경 설정 (clone 후 필수!)

`application.yaml`은 DB 비밀번호 등 민감정보가 있어 깃허브에 올라가지 않습니다 (`.gitignore` 처리).
**clone 받은 후 직접 만들어야 서버가 실행됩니다.**

### 1. 설정 파일 생성

`src/main/resources/` 경로에 `application.yaml` 파일을 새로 만들고,
아래 내용을 **그대로 복사**해 넣습니다. (들여쓰기가 깨지지 않게 주의!)

```yaml
spring:
  application:
    name: be

  datasource:
    url: jdbc:mysql://localhost:3306/miniproject?serverTimezone=Asia/Seoul&characterEncoding=UTF-8
    username: root
    password: YOUR_DB_PASSWORD        # ← 본인 MySQL 비밀번호로 변경
    driver-class-name: com.mysql.cj.jdbc.Driver

  jpa:
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        format_sql: true
    show-sql: true
    open-in-view: false

server:
  port: 8080

jwt:
  secret: YOUR_JWT_SECRET_KEY        # 팀 공용 값으로 변경
  access-token-expiration: 3600000   # 1시간 (밀리초)
```

### 2. 본인 환경에 맞게 값 변경

| 항목 | 설명 |
|------|------|
| `datasource.password` | 본인 로컬 MySQL 비밀번호 |
| `jwt.secret` | JWT 서명 키 — **단톡방 공지의 팀 공용 값** 사용 |

### 3. MySQL 데이터베이스 생성

로컬 MySQL에 `miniproject` 데이터베이스가 없으면 먼저 생성합니다.

```sql
CREATE DATABASE miniproject CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

> ⚠️ `application.yaml`은 절대 커밋하지 마세요 (`.gitignore`에 등록되어 있음)

<br>

## 👥 Team

| 도희 | 수민 | 윤아 | 윤서 | 다은 | 예은 |
|:---:|:---:|:---:|:---:|:---:|:---:|
| 🦁 | 🦁 | 🦁 | 🦁 | 🦁 | 🦁 |

<br>

**다들 화이팅!! 🦁🔥**
