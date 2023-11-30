package lotto.view.outputView;

public final class OutputView {
    private OutputView() {
    }

    public static OutputView getInstance() {
        return Holder.OUTPUT_VIEW;
    }

    public void showCountOfLotteries(int count) {
        System.out.println(OutputMessage.NUMBER_OF_BOUGHT_LOTTERIES_FORMAT.getMessage(count));
    }

    private static final class Holder {
        private static final OutputView OUTPUT_VIEW = new OutputView();
    }
}
