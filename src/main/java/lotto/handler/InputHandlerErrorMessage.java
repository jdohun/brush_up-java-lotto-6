package lotto.handler;

import static lotto.domain.model.AutoLottoGenerator.AutoLottoGenerator.LOTTO_PRICE;
import static lotto.util.ErrorMessagePrefix.ERROR_MESSAGE_PREFIX;

enum InputHandlerErrorMessage {
    ERROR_NOT_DIVISIBLE_BY_LOTTO_PRICE(LOTTO_PRICE + " 단위의 금액만 입력 가능합니다."),
    ERROR_NOT_INTEGER("정수가 아닙니다.");

    private final String message;

    InputHandlerErrorMessage(String message) {
        this.message = ERROR_MESSAGE_PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
