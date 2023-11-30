package lotto.util.stringValidator;

import static lotto.util.ErrorMessagePrefix.ERROR_MESSAGE_PREFIX;

enum StringValidatorErrorMessage {
    ERROR_NULL(ERROR_MESSAGE_PREFIX + "null 을 입력할 수 없습니다."),
    ERROR_EMPTY(ERROR_MESSAGE_PREFIX + "빈 문자열을 입력할 수 없습니다."),
    ERROR_HAS_SURROUNDING_WHITE_SPACE(ERROR_MESSAGE_PREFIX + "문자열의 시작과 끝에 공백을 입력할 수 없습니다.");
    private final String message;

    StringValidatorErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
