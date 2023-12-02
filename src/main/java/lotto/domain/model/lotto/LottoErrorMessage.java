package lotto.domain.model.lotto;

import static lotto.domain.model.lotto.Lotto.LOTTO_NUMBER_COUNT;
import static lotto.util.ErrorMessagePrefix.ERROR_MESSAGE_PREFIX;

enum LottoErrorMessage {
    ERROR_NOT_MATCH_THE_LOTTO_NUMBER_COUNT("로또는 " + LOTTO_NUMBER_COUNT + "개의 숫자를 가져야 합니다."),
    ERROR_HAS_DUPLICATE_NUMBERS("중복되는 숫자가 존재합니다.");

    private final String message;

    LottoErrorMessage(String message) {
        this.message = ERROR_MESSAGE_PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
