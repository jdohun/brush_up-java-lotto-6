package lotto.handler;

import static lotto.util.ErrorMessagePrefix.ERROR_MESSAGE_PREFIX;

enum InputHandlerErrorMessage {
    ERROR_NOT_INTEGER("정수가 아닙니다.");

    private final String message;

    InputHandlerErrorMessage(String message) {
        this.message = ERROR_MESSAGE_PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
