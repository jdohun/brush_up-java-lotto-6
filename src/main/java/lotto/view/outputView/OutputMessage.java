package lotto.view.outputView;

enum OutputMessage {
    NUMBER_OF_BOUGHT_LOTTERIES_FORMAT("%d개를 구매했습니다.");

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getMessage(int value){
        return String.format(message, value);
    }
}
