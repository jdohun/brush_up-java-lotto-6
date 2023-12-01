package lotto.domain.model;

public enum WinningResult {
    FIRST_PLACE(6, 2_000_000_000),
    SECOND_PLACE(5, true, 30_000_000),
    THIRD_PLACE(5, false, 1_500_000),
    FOURTH_PLACE(4, 50_000),
    FIFTH_PLACE(3, 5_000),
    NO_WIN(0, 0);

    private final int matchingNumberCount;
    private final boolean hasBonusNumber;
    private final int prize;

    WinningResult(int matchingNumberCount, int prize) {
        this.matchingNumberCount = matchingNumberCount;
        this.hasBonusNumber = false;
        this.prize = prize;
    }

    WinningResult(int matchingNumberCount, boolean hasBonusNumber, int prize) {
        this.matchingNumberCount = matchingNumberCount;
        this.hasBonusNumber = hasBonusNumber;
        this.prize = prize;
    }

    public int getMatchingNumberCount() {
        return matchingNumberCount;
    }

    public boolean hasBonusNumber() {
        return hasBonusNumber;
    }

    public int getPrize() {
        return prize;
    }

    public boolean match(int matchingNumberCount, boolean hasBonusNumber) {
        return this.matchingNumberCount == matchingNumberCount &&
                this.hasBonusNumber == hasBonusNumber;
    }
}

