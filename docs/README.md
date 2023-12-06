# 구현 목록 명세

## 도메인
### model
- ### LottoNumber
  - 멤버
    - `int lottoNumber`

  - 메소드
    - 생성 전 검증
      - [x] 로또 번호는 1~45 범위를 벗어나지 않는다.
        - 검증에 실패하면 `IllegalArgumentException` 예외 처리한다.
    - [x] 정수 리스트를 전달받아 로또 번호 리스트를 반환한다.
      - [x] 전달받은 정수 리스트를 오름차순으로 정렬한다.

    - [x] 내부 정보를 Dto 를 통해 반환한다.

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
    - [x] 전달받은 LottoNumber 번호의 포함 여부를 판별한다. : 당첨번호가 bonusNumber 를 포함하지 않음을 검증하기 위함
    - [x] 내부 정보를 Dto 를 통해 반환한다.

- ### Lotteries
  - 멤버
    - `List<Lotto> lotteries`

  - 메소드
    - [x] 보유한 로또 수량을 반환한다.
    - [x] 내부 로또 정보를 Dto List 로 반환한다. : 출력용
    - [x] 내부 읽기 전용(불변) 정보를 List 로 반환한다. : 당첨 통계 계산용

- ### WinningResult
  - 멤버
    - `int matchingNumberCount`
    - `boolean hasBonusNumber`
    - `int prize`
    - `String resultFormat`

  - 메소드
    - [x] 전달받은 일치하는 번호 개수와 보너스 번호 일치 여부에 따라 당첨 결과를 반환한다.
    - [x] 구입금액과 계산된 당첨 통계를 통해 수익률을 계산한다.
    - [x] 필요 보너스 번호의 일치 여부를 반환한다.
    - [x] 당첨 결과 메시지 형식을 반환한다.

- ### WinningLotto
  - 멤버
    - `Lotto winningLotto`
    - `LottoNumber bonusNumber`

  - 메소드
    - 생성 전 검증
      - [x] 전달받은 단일 로또 번호(보너스)는 null 이 아니다.
      - [x] 전달받은 로또는 단일 로또 번호(보너스)를 포함하지 않는다.
        - 검증에 실패하면 `IllegalArgumentException` 예외 처리한다.
    - [x] 전달받은 `Lotteries` 의 당첨 통계를 계산한다.
      - [x] 당첨 통계를 저장할 공간을 초기화한다.
      - [x] 전달받은 `Lotteries` 의 각 `Lotto` 의 당첨 결과 List 를 반환한다.
        - [x] 전달받은 `Lotto` 의 당첨 결과를 반환한다.
      - [x] 당첨 결과 List 를 통해 초기화한 당첨 통계를 업데이트한다.

### generator
- ### AutoLottoGenerator
  - 메소드
    - [x] 입력된 금액을 통해 로또를 생성한다.
      - [x] 입력된 금액을 `Lotto_Price : 1000` 으로 나누어 생산할 Lotto 의 개수를 계산한다.

---

## DTO
- ### LottoNumberDto
  - [x] `LottoNumber` 의 내부 정보를 원시값으로 저장한다.

- ### LottoDto
  - [x] `Lotto` 의 내부 정보를 원시값으로 저장한다.

---

## 어플리케이션 서비스
### 공용 (util)
- ### InputStringValidator
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
      - [x] null 을 입력한다.
      - [x] 빈 값(공백) 을 입력한다.
      - [x] 공백으로 시작하거나 끝난다.
      - [x] 1 또는 2자리 숫자 6개가 공백없이 쉼표(,)로만 구분되는 형태가 아니다.
      - [x] 정수가 아닌 값이 존재한다.
        - 위 조건에 해당하면 `IllegalArgumentException` 예외 처리한다.
 
  - ### InputBonusNumber
    - 입력받은 당첨 번호의 형식을 검증한다.
      - [x] null 을 입력한다.
      - [x] 빈 값(공백) 을 입력한다.
      - [x] 공백으로 시작하거나 끝난다.
      - [x] 정수가 아니다.
        - 위 조건에 해당하면 `IllegalArgumentException` 예외 처리한다. 

### Controller
- [x] 입력받은 구입 금액을 통해 자동으로 `Lotteries` 를 생성한다.
  - [x] 입력받은 구입 금액을 저장한다.
  - [x] 저장된 구입 금액을 통해 자동으로 `Lotteries` 를 생성한다.
    - [x] 잘못된 입력이 존재할 경우 예외 처리하고 재입력 받는다.
  
- [x] 입력받은 당첨 번호와 보너스 번호를 통해 `WinningLotto` 를 생성한다.
    - [x] 당첨 번호를 입력 받는다.
      - [x] 잘못된 입력이 존재할 경우 예외 처리하고 재입력 받는다.
      - [x] 입력된 당첨 번호를 통해 `Lotto` 생성 시 예외가 발생하면 예외 처리하고 재입력 받는다.
    - [x] 보너스 번호를 입력 받는다.
      - [x] 잘못된 입력이 존재할 경우 예외 처리하고 재입력 받는다.
      - [x] 입력된 보너스 번호를 통해 `LottoNumber` 생성 시 예외가 발생하면 예외 처리하고 재입력 받는다.
  - [x] 생성된 `Lotto` 와 `LottoNumber` 를 통해 `WinningLotto` 생성 시 예외가 발생하면 예외 처리하고 재입력 받는다.

- [x] `Lotteries` 를 `WinningLotto` 와 비교하여 당첨 통계를 계산한다.
- [x] 구입 금액과 당첨 통계를 토대로 수익률을 계산한다.

### UI (View)
- ### InputView
  - [x] 구입금액을 입력 받는다.
  - [x] 당첨 번호를 입력 받는다.
  - [x] 보너스 번호를 입력 받는다.

- ### OutputView
  - [x] 발행한 로또 수량 및 번호를 출력한다
  - [x] 당첨 통계를 출력한다.
  - [x] 총 수익률을 출력한다.
    - [x] 수익률은 소수점 둘째 자리에서 반올림한다.