package lotto.domain.model.winningLotto;

import lotto.domain.model.lotteries.Lotteries;
import lotto.domain.model.lotto.Lotto;
import lotto.domain.model.lottoNumber.LottoNumber;
import lotto.domain.model.winningResult.WinningResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static lotto.util.ErrorMessagePrefix.ERROR_MESSAGE_PREFIX;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {
    @DisplayName("생성 테스트")
    @Nested
    class CreateTest {
        @DisplayName("전달받은 BonusNumber 가 null 이면 예외가 발생한다.")
        @Test
        void createByNullBonusNumber() {
            // assert
            assertThatThrownBy(() -> new WinningLotto(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)), null))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ERROR_MESSAGE_PREFIX + "보너스 번호를 전달받지 못했습니다.");
        }

        @DisplayName("전달받은 BonusNumber 를 winningLotto 가 포함하면 예외가 발생한다.")
        @ParameterizedTest
        @ValueSource(ints = {1, 2, 3, 4, 5, 6})
        void createByAlreadyContainsBonusNumber(int number) {
            // assert
            assertThatThrownBy(() -> new WinningLotto(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)), new LottoNumber(number)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ERROR_MESSAGE_PREFIX + "보너스 번호와 일치하는 당첨 로또 번호가 존재합니다.");
        }
    }

    @DisplayName("구현 메서드 테스트")
    @Nested
    class MethodTest {
        private static Stream<Arguments> calculateWinningStatisticsProvider() {
            return Stream.of(
                    Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), WinningResult.FIRST_PLACE),
                    Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 7), WinningResult.SECOND_PLACE),
                    Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 8), WinningResult.THIRD_PLACE),
                    Arguments.of(Arrays.asList(1, 2, 3, 4, 7, 8), WinningResult.FOURTH_PLACE),
                    Arguments.of(Arrays.asList(1, 2, 3, 7, 8, 9), WinningResult.FIFTH_PLACE),
                    Arguments.of(Arrays.asList(1, 2, 7, 8, 9, 10), WinningResult.NO_WIN),
                    Arguments.of(Arrays.asList(1, 7, 8, 9, 10, 11), WinningResult.NO_WIN),
                    Arguments.of(Arrays.asList(7, 8, 9, 10, 11, 12), WinningResult.NO_WIN)
            );
        }

        @DisplayName("일치하는 당첨 결과 key 의 value 만 1이다.")
        @ParameterizedTest
        @MethodSource("calculateWinningStatisticsProvider")
        void calculateWinningStatisticsTest(List<Integer> source, WinningResult expected) {
            // arrange
            WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new LottoNumber(7));
            Lotteries lotteries = new Lotteries(List.of(new Lotto(source)));

            // act
            Map<WinningResult, Integer> winningStatistics = winningLotto.calculateWinningStatistics(lotteries);

            // assert
            boolean allMatchStream = winningStatistics.entrySet().stream()
                    .allMatch(entry ->
                            (entry.getKey().equals(expected) && entry.getValue().equals(1)) ||
                                    (!entry.getKey().equals(expected) && entry.getValue().equals(0))
                    );

            assertThat(allMatchStream).isTrue();
        }
    }
}