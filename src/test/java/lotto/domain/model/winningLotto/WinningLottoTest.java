package lotto.domain.model.winningLotto;

import lotto.domain.model.lotto.Lotto;
import lotto.domain.model.lottoNumber.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static lotto.util.ErrorMessagePrefix.ERROR_MESSAGE_PREFIX;
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
        @DisplayName("당첨 결과를 계산한다.")
        @Test
        void calculateWinningStatisticsTest() {
            // assert

        }
    }
}