package lotto.view.outputView;

import lotto.domain.model.WinningResult;
import lotto.domain.model.lotteries.Lotteries;
import lotto.dto.LottoDto;

import java.util.List;
import java.util.Map;

import static lotto.view.outputView.OutputMessage.*;

public final class OutputView {
    private OutputView() {
    }

    public static OutputView getInstance() {
        return Holder.OUTPUT_VIEW;
    }

    public void showLotteriesInfo(Lotteries lotteries) {
        System.out.println();
        showCountOfLotteries(lotteries.getSize());
        showLotteriesDetail(lotteries.toDtos());
        System.out.println();
    }

    private void showLotteriesDetail(List<LottoDto> lottoDtos) {
        for (LottoDto lottoDto : lottoDtos) {
            System.out.println(lottoDto.numbers());
        }
    }

    private void showCountOfLotteries(int count) {
        System.out.printf((NUMBER_OF_BOUGHT_LOTTERIES_FORMAT.getMessage()) + "%n", count);
    }

    public void showWinningStatistics(Map<WinningResult, Integer> winningResults) {
        System.out.println();
        System.out.println(WINNING_STATISTICS_TITLE.getMessage());
        System.out.println(LINE_SEPARATOR_FOR_STATISTICS.getMessage());
        for (Map.Entry<WinningResult, Integer> winningResult : winningResults.entrySet()) {
            System.out.println(String.format(winningResult.getKey().getResultFormat()) + String.format(COUNT_OF_WINNING_FORMAT.getMessage(), winningResult.getValue()));
        }

    }

    public void showRateOfReturn(double rateOfReturn) {
        System.out.printf((RESULT_RATE_OF_RETURN_FORMAT.getMessage()) + "%n", rateOfReturn);
    }

    private static final class Holder {
        private static final OutputView OUTPUT_VIEW = new OutputView();
    }
}
