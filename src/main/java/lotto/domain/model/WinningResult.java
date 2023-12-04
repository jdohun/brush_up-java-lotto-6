package lotto.domain.model;

import java.util.Arrays;
import java.util.Map;

public enum WinningResult {
    NO_WIN(0, false, 0L, "해당 없음"),
    FIFTH_PLACE(3, false, 5_000L, "%d개 일치 (%,d원)"),
    FOURTH_PLACE(4, false, 50_000L, "%d개 일치 (%,d원)"),
    THIRD_PLACE(5, false, 1_500_000L, "%d개 일치 (%,d원)"),
    SECOND_PLACE(5, true, 30_000_000L, "%d개 일치, 보너스 볼 일치 (%,d원)"),
    FIRST_PLACE(6, false, 2_000_000_000L, "%d개 일치 (%,d원)");

    private static final int PERCENT = 100;

    private final int matchingNumberCount;
    private final boolean hasBonusNumber;
    private final long prize;
    private final String resultFormat;

    WinningResult(int matchingNumberCount, boolean hasBonusNumber, long prize, String resultFormat) {
        this.matchingNumberCount = matchingNumberCount;
        this.hasBonusNumber = hasBonusNumber;
        this.prize = prize;
        this.resultFormat = String.format(resultFormat, matchingNumberCount, prize);
    }

    public static WinningResult getWinningResultBy(int matchingNumberCount, boolean hasBonusNumber) {
        return Arrays.stream(WinningResult.values())
                .filter(winningResult -> winningResult.matchingNumberCount == matchingNumberCount && winningResult.hasBonusNumber() == hasBonusNumber)
                .findFirst()
                .orElse(NO_WIN);
    }

    public boolean hasBonusNumber() {
        return hasBonusNumber;
    }

    public static double calculateRateOfReturn(final int money, final Map<WinningResult, Integer> winningStatistics) {
        long totalReturn = winningStatistics.entrySet().stream()
                .mapToLong(winningResultIntegerEntry -> winningResultIntegerEntry.getKey().prize * winningResultIntegerEntry.getValue())
                .sum();

        return (totalReturn / (double) money) * PERCENT;
    }

    public String getResultFormat() {
        return resultFormat;
    }
}
