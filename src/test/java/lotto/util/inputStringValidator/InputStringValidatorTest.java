package lotto.util.inputStringValidator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static lotto.util.ErrorMessagePrefix.ERROR_MESSAGE_PREFIX;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputStringValidatorTest {

    @DisplayName("null 을 입력받으면 예외를 발생시킨다.")
    @Test
    void isNotNullTest() {
        assertThatThrownBy(() -> InputStringValidator.isNotNull(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_MESSAGE_PREFIX + "null 을 입력할 수 없습니다.");
    }

    @DisplayName("빈 문자열을 입력받으면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    void isNotEmptyTest(String empty) {
        assertThatThrownBy(() -> InputStringValidator.isNotEmpty(empty))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_MESSAGE_PREFIX + "빈 문자열을 입력할 수 없습니다.");
    }

    @DisplayName("문자열의 시작과 끝에 공백을 입력받으면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {" test", "test ", " test "})
    void hasNotSurroundingWhiteSpaceTest(String surroundingWhiteSpace) {
        assertThatThrownBy(() -> InputStringValidator.hasNotSurroundingWhiteSpace(surroundingWhiteSpace))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_MESSAGE_PREFIX + "문자열의 시작과 끝에 공백을 입력할 수 없습니다.");
    }

    @DisplayName("문자열 중간의 빈 공간은 예외를 발생시키지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = {"te st", "te  st", "te   st"})
    void hasIncludingWhiteSpaceTest(String includingWhiteSpace) {
        assertThatCode(() -> InputStringValidator.hasNotSurroundingWhiteSpace(includingWhiteSpace))
                .doesNotThrowAnyException();
    }
}