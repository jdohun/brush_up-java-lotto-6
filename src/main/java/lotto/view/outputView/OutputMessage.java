package lotto.view.outputView;

enum OutputMessage {
    NUMBER_OF_BOUGHT_LOTTERIES_FORMAT("%d개를 구매했습니다."),
    WINNING_STATISTICS_TITLE("당첨 통계"),
    LINE_SEPARATOR_AS_HYPHEN("---"),
    RESULT_RATE_OF_RETURN_FORMAT("총 수익률은 %.1f%%입니다.");

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
