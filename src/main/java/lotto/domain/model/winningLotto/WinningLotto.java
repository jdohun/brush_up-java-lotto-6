package lotto.domain.model.winningLotto;

import lotto.domain.model.WinningResult;
import lotto.domain.model.lotto.Lotto;
import lotto.domain.model.lottoNumber.LottoNumber;

import java.util.EnumSet;

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

    public WinningResult calculateWinningResult(Lotto lotto) {
        int matchingNumberCount = lotto.countMatchingNumbers(winningLotto);
        boolean hasBonusNumber = lotto.hasBonusNumber(bonusNumber);

        return EnumSet.allOf(WinningResult.class)
                .stream()
                .filter(result -> result.match(matchingNumberCount, hasBonusNumber))
                .findFirst()
                .orElse(WinningResult.NO_WIN);
    }
}
