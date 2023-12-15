package lotto.domain.model.winningResult;

enum WinningResultMessageFormat {
    RESULT_NONE("해당 없음"),
    RESULT_MATCH_FORMAT("%d개 일치 (%,d원)"),
    RESULT_MATCH_WITH_BONUS_FORMAT("%d개 일치, 보너스 볼 일치 (%,d원)");

    private final String messageFormat;

    WinningResultMessageFormat(String messageFormat) {
        this.messageFormat = messageFormat;
    }

    public String getMessageFormat() {
        return messageFormat;
    }
}
