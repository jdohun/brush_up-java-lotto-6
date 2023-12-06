package lotto.domain.model.winningResult;

import java.util.Arrays;
import java.util.Map;

import static lotto.domain.model.winningResult.WinningResultMessageFormat.*;

public enum WinningResult {
    NO_WIN(0, false, 0L, RESULT_NONE.getMessageFormat()),
    FIFTH_PLACE(3, false, 5_000L, RESULT_MATCH_FORMAT.getMessageFormat()),
    FOURTH_PLACE(4, false, 50_000L, RESULT_MATCH_FORMAT.getMessageFormat()),
    THIRD_PLACE(5, false, 1_500_000L, RESULT_MATCH_FORMAT.getMessageFormat()),
    SECOND_PLACE(5, true, 30_000_000L, RESULT_MATCH_WITH_BONUS_FORMAT.getMessageFormat()),
    FIRST_PLACE(6, false, 2_000_000_000L, RESULT_MATCH_FORMAT.getMessageFormat());

    private static final int PERCENT = 100;

    private final int matchingNumberCount;
    private final boolean hasBonusNumber;
    private final long prize;
    private final String resultMessageFormat;

    WinningResult(int matchingNumberCount, boolean hasBonusNumber, long prize, String resultMessageFormat) {
        this.matchingNumberCount = matchingNumberCount;
        this.hasBonusNumber = hasBonusNumber;
        this.prize = prize;
        this.resultMessageFormat = String.format(resultMessageFormat, matchingNumberCount, prize);
    }

    public static WinningResult getWinningResultBy(int matchingNumberCount, boolean hasBonusNumber) {
        return Arrays.stream(WinningResult.values())
                .filter(winningResult -> winningResult.matchingNumberCount == matchingNumberCount && winningResult.hasBonusNumber() == hasBonusNumber)
                .findFirst()
                .orElse(NO_WIN);
    }

    public static double calculateRateOfReturn(final int money, final Map<WinningResult, Integer> winningStatistics) {
        long totalReturn = winningStatistics.entrySet().stream()
                .mapToLong(winningResultIntegerEntry -> winningResultIntegerEntry.getKey().prize * winningResultIntegerEntry.getValue())
                .sum();

        return (totalReturn / (double) money) * PERCENT;
    }

    public boolean hasBonusNumber() {
        return hasBonusNumber;
    }

    public String getResultMessageFormat() {
        return resultMessageFormat;
    }
}
