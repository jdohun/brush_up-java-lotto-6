package lotto.handler;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static lotto.domain.generator.AutoLottoGenerator.LOTTO_PRICE;
import static lotto.util.ErrorMessagePrefix.ERROR_MESSAGE_PREFIX;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputHandlerTest {

    private static final InputHandler INPUT_HANDLER = InputHandler.getInstance();

    @Test
    void parseBonusNumber() {
    }

    @Nested
    class ParseInputMoneyTest {
        @DisplayName("null 을 입력 받으면 예외를 발생시킨다.")
        @Test
        void parseInputMoneyByNull() {
            assertThatThrownBy(() -> INPUT_HANDLER.parseInputMoney(null))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ERROR_MESSAGE_PREFIX + "null 을 입력할 수 없습니다.");
        }

        @DisplayName("공백을 입력 받으면 예외를 발생시킨다.")
        @ParameterizedTest
        @ValueSource(strings = {"", " ", "   "})
        void parseInputMoneyByEmpty(String empty) {
            assertThatThrownBy(() -> INPUT_HANDLER.parseInputMoney(empty))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ERROR_MESSAGE_PREFIX + "빈 문자열을 입력할 수 없습니다.");
        }

        @DisplayName("공백으로 시작하거나 끝나면 예외를 발생시킨다..")
        @ParameterizedTest
        @ValueSource(strings = {" 1000", "1000 ", " 1000 "})
        void parseInputMoneyBySurroundingWhiteSpace(String surroundingWhiteSpace) {
            assertThatThrownBy(() -> INPUT_HANDLER.parseInputMoney(surroundingWhiteSpace))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ERROR_MESSAGE_PREFIX + "문자열의 시작과 끝에 공백을 입력할 수 없습니다.");
        }

        @DisplayName("정수가 아니면 예외를 발생시킨다.")
        @ParameterizedTest
        @ValueSource(strings = {"1 23", "asd", "12a"})
        void parseInputMoneyByContainsNotInteger(String containsNotInteger) {
            assertThatThrownBy(() -> INPUT_HANDLER.parseInputMoney(containsNotInteger))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ERROR_MESSAGE_PREFIX + "정수가 아닙니다.");
        }

        @DisplayName("1000 단위의 정수가 아니면 예외를 발생시킨다.")
        @ParameterizedTest
        @ValueSource(strings = {"123"})
        void parseInputMoneyByIndivisibleBy1000(String indivisibleBy1000) {
            assertThatThrownBy(() -> INPUT_HANDLER.parseInputMoney(indivisibleBy1000))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ERROR_MESSAGE_PREFIX + LOTTO_PRICE + " 단위의 금액만 입력 가능합니다.");
        }

        @DisplayName("1000 단위의 정수를 입력받으면 정수를 반환한다.")
        @ParameterizedTest
        @ValueSource(strings = {"1000", "2000", "11000"})
        void parseInputMoneyByDivisibleBy1000(String divisibleBy1000) {
            assertThat(INPUT_HANDLER.parseInputMoney(divisibleBy1000))
                    .isEqualTo(Integer.parseInt(divisibleBy1000));
        }
    }

    @Nested
    class ParseWinningNumberTest {
        @DisplayName("null 을 입력 받으면 예외를 발생시킨다.")
        @Test
        void parseWinningNumbersByNull() {
            assertThatThrownBy(() -> INPUT_HANDLER.parseWinningNumbers(null))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ERROR_MESSAGE_PREFIX + "null 을 입력할 수 없습니다.");
        }

        @DisplayName("공백을 입력 받으면 예외를 발생시킨다.")
        @ParameterizedTest
        @ValueSource(strings = {"", " ", "   "})
        void parseWinningNumbersByEmpty(String empty) {
            assertThatThrownBy(() -> INPUT_HANDLER.parseWinningNumbers(empty))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ERROR_MESSAGE_PREFIX + "빈 문자열을 입력할 수 없습니다.");
        }

        @DisplayName("공백으로 시작하거나 끝나면 예외를 발생시킨다..")
        @ParameterizedTest
        @ValueSource(strings = {" 1,2,3,4,5,6", "1,2,3,4,5,6 "})
        void parseWinningNumbersBySurroundingWhiteSpace(String surroundingWhiteSpace) {
            assertThatThrownBy(() -> INPUT_HANDLER.parseWinningNumbers(surroundingWhiteSpace))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ERROR_MESSAGE_PREFIX + "문자열의 시작과 끝에 공백을 입력할 수 없습니다.");
        }


        @Nested
        class FitFormatTest {
            @DisplayName("1 또는 2자리 숫자가 아닌 숫자가 포함되면 예외를 발생시킨다.")
            @Test
            void parseWinningNumbersByContainsExcess2Digit() {
                // arrange
                String containsExcess2Digit = "1,123,3,4,5,6";

                // act
                // assert
                assertThatThrownBy(() -> INPUT_HANDLER.parseWinningNumbers(containsExcess2Digit))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage(ERROR_MESSAGE_PREFIX + "1 또는 2자리의 숫자 6개를 쉼표로 구분하여 입력해야 합니다.");
            }

            @DisplayName("쉼표(,)로만 구분되는 형태가 아니면 예외를 발생시킨다.")
            @Test
            void parseWinningNumbersByDoesNotFitFormat() {
                // arrange
                String notSeparatedByCommas = "1:2,3,4,5,6";

                // act
                // assert
                assertThatThrownBy(() -> INPUT_HANDLER.parseWinningNumbers(notSeparatedByCommas))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage(ERROR_MESSAGE_PREFIX + "1 또는 2자리의 숫자 6개를 쉼표로 구분하여 입력해야 합니다.");
            }

            @DisplayName("숫자가 아닌 값이 포함되면 예외를 발생시킨다.")
            @ParameterizedTest
            @ValueSource(strings = {"1,2,gㅣㅇ,4,5,6", "a,b,c,d,e,f", "1,2,3,ㄱ,5,6", "1,2,3,,5,6"})
            void parseWinningNumbersByContainsNotNumber(String containsNotNumber) {
                assertThatThrownBy(() -> INPUT_HANDLER.parseWinningNumbers(containsNotNumber))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage(ERROR_MESSAGE_PREFIX + "1 또는 2자리의 숫자 6개를 쉼표로 구분하여 입력해야 합니다.");
            }


            @DisplayName("숫자가 6개가 아니면 예외를 발생시킨다.")
            @ParameterizedTest
            @ValueSource(strings = {"1,2,3,4,5", "1,2,3,4,5,6,7"})
            void parseWinningNumbersByOverOrLessNumber(String overOrLessNumber) {
                assertThatThrownBy(() -> INPUT_HANDLER.parseWinningNumbers(overOrLessNumber))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage(ERROR_MESSAGE_PREFIX + "1 또는 2자리의 숫자 6개를 쉼표로 구분하여 입력해야 합니다.");
            }

            @DisplayName("쉼표로 시작하거나 끝나면 예외를 발생시킨다.")
            @ParameterizedTest
            @ValueSource(strings = {"1,2,3,4,5,6,", ",1,2,3,4,5,6", ",1,2,3,4,5,6,"})
            void parseWinningNumbersBySurroundingCommas(String surroundingCommas) {
                assertThatThrownBy(() -> INPUT_HANDLER.parseWinningNumbers(surroundingCommas))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage(ERROR_MESSAGE_PREFIX + "1 또는 2자리의 숫자 6개를 쉼표로 구분하여 입력해야 합니다.");
            }

            @DisplayName("기준을 충족하면 List 를 반환한다.")
            @ParameterizedTest
            @ValueSource(strings = {"1,2,3,4,5,6", "10,20,30,40,50,60", "7,14,21,28,35,42"})
            void parseWinningNumbersByFitFormat(String fitFormat) {
                // arrange
                final String WINNING_NUMBER_DELIMITER = ",";

                // act
                List<Integer> winningNumbers = INPUT_HANDLER.parseWinningNumbers(fitFormat);

                // assert
                String[] expectedNumbers = fitFormat.split(WINNING_NUMBER_DELIMITER);
                assertThat(winningNumbers).containsExactly(
                        Arrays.stream(expectedNumbers)
                                .map(Integer::parseInt)
                                .toArray(Integer[]::new)
                );
            }
        }

        @Nested
        class ParseBonusNumberTest {
            @DisplayName("null 을 입력 받으면 예외를 발생시킨다.")
            @Test
            void parseBonusNumberByNull() {
                assertThatThrownBy(() -> INPUT_HANDLER.parseInputMoney(null))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage(ERROR_MESSAGE_PREFIX + "null 을 입력할 수 없습니다.");
            }

            @DisplayName("공백을 입력 받으면 예외를 발생시킨다.")
            @ParameterizedTest
            @ValueSource(strings = {"", " ", "   "})
            void parseBonusNumberByEmpty(String empty) {
                assertThatThrownBy(() -> INPUT_HANDLER.parseInputMoney(empty))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage(ERROR_MESSAGE_PREFIX + "빈 문자열을 입력할 수 없습니다.");
            }

            @DisplayName("공백으로 시작하거나 끝나면 예외를 발생시킨다..")
            @ParameterizedTest
            @ValueSource(strings = {" 1000", "1000 ", " 1000 "})
            void parseBonusNumberBySurroundingWhiteSpace(String surroundingWhiteSpace) {
                assertThatThrownBy(() -> INPUT_HANDLER.parseInputMoney(surroundingWhiteSpace))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage(ERROR_MESSAGE_PREFIX + "문자열의 시작과 끝에 공백을 입력할 수 없습니다.");
            }

            @DisplayName("정수가 아니면 예외를 발생시킨다.")
            @ParameterizedTest
            @ValueSource(strings = {"1 23", "asd", "12a"})
            void parseBonusNumberByContainsNotInteger(String containsNotInteger) {
                assertThatThrownBy(() -> INPUT_HANDLER.parseInputMoney(containsNotInteger))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage(ERROR_MESSAGE_PREFIX + "정수가 아닙니다.");
            }

            @DisplayName("정수를 입력받으면 정수를 반환한다.")
            @ParameterizedTest
            @ValueSource(strings = {"1", "2", "4", "124"})
            void parseBonusNumberByByInteger(String integer) {
                assertThat(INPUT_HANDLER.parseBonusNumber(integer))
                        .isEqualTo(Integer.parseInt(integer));
            }
        }
    }
}