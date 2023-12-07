package lotto.domain.generator;

import lotto.domain.model.lotteries.Lotteries;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class AutoLottoGeneratorTest {
    private static AutoLottoGenerator autoLottoGenerator = AutoLottoGenerator.getInstance();

    @DisplayName("구입 금액을 1000으로 나눈 몫만큼 Lotto 를 생성한다.")
    @ParameterizedTest
    @CsvSource(value = {"8000,8", "7000,7"})
    void createLotteries(int money, int expected) {
        // act
        Lotteries lotteries = autoLottoGenerator.createLotteries(money);

        // assert
        assertThat(lotteries.getSize()).isEqualTo(expected);
    }
}