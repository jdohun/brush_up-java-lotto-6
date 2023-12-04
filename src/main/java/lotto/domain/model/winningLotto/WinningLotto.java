package lotto.domain.model.winningLotto;

import lotto.domain.model.WinningResult;
import lotto.domain.model.lotteries.Lotteries;
import lotto.domain.model.lotto.Lotto;
import lotto.domain.model.lottoNumber.LottoNumber;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WinningLotto {
    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningLotto, LottoNumber bonusNumber) {
        validate(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    private void validate(Lotto winningLotto, LottoNumber bonusNumber) {
        validateBonusNumberPresent(bonusNumber);
        validateNotExistingBonusNumber(winningLotto, bonusNumber);
    }

    private void validateBonusNumberPresent(LottoNumber bonusNumber) {
        if (!bonusNumber.isBonus()) {
            throw new IllegalArgumentException(WinningLottoErrorMessage.ERROR_HAS_NO_BONUS_NUMBER.getMessage());
        }
    }

    private void validateNotExistingBonusNumber(Lotto winningLotto, LottoNumber bonusNumber) {
        if (winningLotto.hasBonusNumber(bonusNumber)) {
            throw new IllegalArgumentException(WinningLottoErrorMessage.ERROR_EXISTING_BONUS_NUMBER_IN_LOTTO.getMessage());
        }
    }

    public Map<WinningResult, Integer> calculateWinningStatistics(Lotteries usersLotteries) {
        Map<WinningResult, Integer> winningStatistics = initializeWinningStatistics();

        List<WinningResult> winningResults = calculateLotteriesWinningResults(usersLotteries);

        updateWinningStatistics(winningStatistics, winningResults);

        return winningStatistics;
    }

    private List<WinningResult> calculateLotteriesWinningResults(Lotteries usersLotteries) {
        return usersLotteries.getImmutableLotteries().stream()
                .map(this::calculateLottoWinningResult)
                .collect(Collectors.toList());
    }

    private WinningResult calculateLottoWinningResult(Lotto lotto) {
        int matchingNumberCount = lotto.countMatchingNumbers(winningLotto);
        boolean hasBonusNumber = lotto.hasBonusNumber(bonusNumber);

        return WinningResult.getWinningResultBy(matchingNumberCount, hasBonusNumber);
    }

    private Map<WinningResult, Integer> initializeWinningStatistics() {
        Map<WinningResult, Integer> winningResultIntegerMap = new HashMap<>();
        for (WinningResult result : EnumSet.range(WinningResult.FIFTH_PLACE, WinningResult.FIRST_PLACE)) {
            winningResultIntegerMap.put(result, 0);
        }
        return winningResultIntegerMap;
    }

    private void updateWinningStatistics(Map<WinningResult, Integer> winningResultIntegerMap, List<WinningResult> winningResults) {
        winningResults.stream()
                .filter(winningResult -> !winningResult.equals(WinningResult.NO_WIN))
                .forEach(winningResult -> winningResultIntegerMap.merge(winningResult, 1, Integer::sum));
    }
}
