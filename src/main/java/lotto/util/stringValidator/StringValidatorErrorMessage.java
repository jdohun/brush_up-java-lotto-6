package lotto.util.stringValidator;

import static lotto.util.ErrorMessagePrefix.ERROR_MESSAGE_PREFIX;

enum StringValidatorErrorMessage {
    ERROR_NULL("null 을 입력할 수 없습니다."),
    ERROR_EMPTY("빈 문자열을 입력할 수 없습니다."),
    ERROR_HAS_SURROUNDING_WHITE_SPACE("문자열의 시작과 끝에 공백을 입력할 수 없습니다.");
    private final String message;

    StringValidatorErrorMessage(String message) {
        this.message = ERROR_MESSAGE_PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
