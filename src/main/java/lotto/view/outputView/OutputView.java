package lotto.view.outputView;

import lotto.domain.model.lotteries.Lotteries;
import lotto.dto.LottoDto;

import java.util.List;

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
        System.out.println(OutputMessage.NUMBER_OF_BOUGHT_LOTTERIES_FORMAT.getMessage(count));
    }

    private static final class Holder {
        private static final OutputView OUTPUT_VIEW = new OutputView();
    }
}
