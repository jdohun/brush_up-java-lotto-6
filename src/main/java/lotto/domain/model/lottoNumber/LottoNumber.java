package lotto.domain.model.lottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LottoNumber {
    public static final int START_OF_RANGE = 1;
    public static final int END_OF_RANGE = 45;
    public static final int LOTTO_NUMBER_COUNT = 6;

    private final int lottoNumber;
    private final boolean isBonus;

    public LottoNumber(int number) {
        validate(number);
        this.lottoNumber = number;
        this.isBonus = false;
    }

    public LottoNumber(int number, boolean isBonus) {
        validate(number);
        this.lottoNumber = number;
        this.isBonus = isBonus;
    }

    public static List<LottoNumber> from(List<Integer> numbers) {
        Collections.sort(numbers);
        List<LottoNumber> lottoNumbers = new ArrayList<>();

        for (int number : numbers) {
            lottoNumbers.add(new LottoNumber(number));
        }

        return lottoNumbers;
    }

    private void validate(int number) {
        validateLottoNumberRange(number);
    }

    public static Builder builder() {
        return new Builder();
    }

    private void validateLottoNumberRange(int number) {
        if (START_OF_RANGE > number || END_OF_RANGE < number) {
            throw new IllegalArgumentException(LottoNumberErrorMessage.ERROR_OUT_OF_RANGE.getMessage());
        }
    }

    public boolean isBonus() {
        return isBonus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return lottoNumber == that.lottoNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumber);
    }

    public static final class Builder {
        private int lottoNumber;
        private boolean isBonus = false;

        public Builder lottoNumber(int lottoNumber) {
            this.lottoNumber = lottoNumber;
            return this;
        }

        public Builder isBonus(boolean isBonus) {
            this.isBonus = isBonus;
            return this;
        }

        public LottoNumber build() {
            return new LottoNumber(lottoNumber, isBonus);
        }
    }
}
