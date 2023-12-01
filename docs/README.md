# 구현 목록 명세

## 도메인
### model
- ### LottoNumber
  - 멤버
    - `int lottoNumber`
    - `boolean isBonus`

  - 메소드
    - 생성 전 검증
      - [x] 로또 번호는 1~45 범위를 벗어나지 않는다.
        - 검증에 실패하면 `IllegalArgumentException` 예외 처리한다.
      - [x] 정수 리스트를 전달 받아 로또 번호 리스트를 반환한다.
        - [x] 전달받은 정수 리스트를 오름차순으로 정렬한다.
      - [x] 보너스 번호 여부를 반환한다.

  - 오버라이드 메소드
    - [x] equals : number 값이 동일한지 판별한다.
    - [x] hashCode : number 값에 따라 독립된 해시 값을 반환한다.

- ### Lotto
  - 멤버
    - `List<LottoNumber> numbers`

  - 메소드
    - 생성 전 검증
      - [x] 전달된 단일 숫자의 개수가 6개이다.
      - [x] 전달된 단일 숫자 중에 중복되는 숫자가 존재하지 않는다.
        - 검증에 실패하면 `IllegalArgumentException` 예외 처리한다.
    - [x] 전달받은 Lotto 와 일치하는 번호의 갯수를 반환한다.
    - [x] 보너스 번호의 포함 여부를 판별한다.

- ### AutoLottoGenerator
  - 메소드
    - [x] 입력된 금액을 통해 로또를 생성한다.
      - [x] 입력된 금액이 `Lotto_Price : 1000` 으로 나누어 떨어지지 않으면 `IllegalArgumentException` 예외 처리한다.

- ### WinningResult
  - 멤버
    - `matchingNumberCount`
    - `hasBonusNumber`
    - `prize`

  - 메소드
    - [x] 일치하는 번호 개수를 반환한다.
    - [x] 보너스 번호의 일치 여부를 반환한다.
    - [x] 상금을 반환한다.
    - [x] 전달받은 일치하는 번호 개수와 보너스 번호 일치 여부에 따라 당첨 결과를 반환한다.

- ### WinningLotto
  - 멤버
    - `Lotto winningLotto`
    - `LottoNumber bonusNumber`

  - 메소드
    - 생성 전 검증
      - [x] 전달된 단일 로또 번호가 보너스 번호이다.
      - [x] 전달된 로또에 보너스 번호와 일치하는 LottoNumber 가 존재하지 않는다.
        - 검증에 실패하면 `IllegalArgumentException` 예외 처리한다.
    - [x] 전달받은 Lotto 의 당첨 결과를 반환한다.

- ### Lotteries
  - 멤버
    - `List<Lotto> lotteries`

  - 메소드
    - [ ] 당첨 통계를 계산한다.
    - [ ] 총 수익률을 계산한다.
      - [ ] 수익률은 소수점 둘째 자리에서 반올림한다.

---

## 어플리케이션 서비스
### 공용 (util)
- ### StringValidator
    - [x] null 이면 `IllegalArgumentException` 예외 처리한다.
    - [x] 빈 문자열 이면 `IllegalArgumentException` 예외 처리한다.
    - [x] 문자열의 시작 또는 끝이 공백이면 `IllegalArgumentException` 예외 처리한다.

### 비즈니스 (Handler)
- ### InputHandler
  - ### InputMoney
    - 입력받은 구입금액의 형식을 검증한다.
      - [x] null 을 입력한다.
      - [x] 빈 문자열(공백) 을 입력한다.
      - [x] 공백으로 시작하거나 끝난다.
      - [x] 정수가 아니다.
        - 위 조건에 해당하면 `IllegalArgumentException` 예외 처리한다.

  - ### InputWinningNumbers
    - 입력받은 당첨 번호의 형식을 검증한다.
      - [ ] null 을 입력한다.
      - [ ] 빈 값(공백) 을 입력한다.
      - [ ] 공백으로 시작하거나 끝난다.
      - [ ] 쉼표(,)로 시작하거나 끝나거나 쉼표(,) 사이에 0회 이상의 공백만 존재한다.
      - [ ] 정수가 아닌 값이 존재한다.
        - 위 조건에 해당하면 `IllegalArgumentException` 예외 처리한다.
 
  - ### InputBonusNumber
    - 입력받은 당첨 번호의 형식을 검증한다.
      - [ ] null 을 입력한다.
      - [ ] 빈 값(공백) 을 입력한다.
      - [ ] 공백으로 시작하거나 끝난다.
      - [ ] 정수가 아니다.
        - 위 조건에 해당하면 `IllegalArgumentException` 예외 처리한다. 

### Controller
- [x] 입력받은 구입 금액을 통해 자동으로 `Lotteries` 를 생성한다.
  - [x] 입력받은 구입 금액을 저장한다.
  - [x] 저장된 구입 금액을 통해 자동으로 `Lotteries` 를 생성한다.
    - [x] 잘못된 입력이 존재할 경우 예외 처리하고 재입력 받는다.
  
- [ ]입력받은 당첨 번호와 보너스 번호를 통해 `WinningLotto` 를 생성한다.
  - [ ] 당첨 번호를 입력 받는다.
    - [ ] 잘못된 입력이 존재할 경우 예외 처리하고 재입력 받는다.
  - [ ] 보너스 번호를 입력 받는다.
    - [ ] 잘못된 입력이 존재할 경우 예외 처리하고 재입력 받는다.

- [ ] `Lotteries` 를 `WinningLotto` 와 비교하여 당첨 결과를 확인한다.
- [ ] 당첨 결과를 토대로 수익률을 계산한다.

### UI (View)
- ### InputView
  - [x] 구입금액을 입력 받는다.
  - [x] 당첨 번호를 입력 받는다.
  - [x] 보너스 번호를 입력 받는다.

- ### OutputView
  - [ ] 발행한 로또 수량 및 번호를 출력한다
  - [ ] 당첨 통계를 출력한다.
  - [ ] 총 수익률을 출력한다.