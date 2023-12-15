package lotto.domain.model.lotteries;

import static lotto.util.ErrorMessagePrefix.ERROR_MESSAGE_PREFIX;

public enum LotteriesErrorMessage {
    ERROR_NULL("null 을 전달받을 수 없습니다."),
    ERROR_EMPTY_LIST("전달받은 리스트가 비어있습니다."),
    ERROR_CONTAIN_NULL_ELEMENT("전달받은 값에 null 이 포함되어 있습니다.");

    private final String message;

    LotteriesErrorMessage(String message) {
        this.message = ERROR_MESSAGE_PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
