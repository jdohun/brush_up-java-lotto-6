package lotto.domain.model.lottoNumber;

import static lotto.util.ErrorMessagePrefix.ERROR_MESSAGE_PREFIX;

enum LottoNumberErrorMessage {
    ERROR_OUT_OF_RANGE("로또 번호는 1부터 45 사이의 숫자여야 합니다.");

    private final String message;

    LottoNumberErrorMessage(String message) {
        this.message = ERROR_MESSAGE_PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
