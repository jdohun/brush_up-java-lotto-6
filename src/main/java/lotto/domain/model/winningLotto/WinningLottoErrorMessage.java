package lotto.domain.model.winningLotto;

import static lotto.util.ErrorMessagePrefix.ERROR_MESSAGE_PREFIX;

public enum WinningLottoErrorMessage {
    ERROR_HAS_NO_BONUS_NUMBER("보너스 번호를 전달받지 못했습니다."),
    ERROR_EXISTING_BONUS_NUMBER_IN_LOTTO("보너스 번호와 일치하는 당첨 로또 번호가 존재합니다.");

    private final String message;

    WinningLottoErrorMessage(String message) {
        this.message = ERROR_MESSAGE_PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
