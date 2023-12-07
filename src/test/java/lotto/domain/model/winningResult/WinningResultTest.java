package lotto.domain.model.winningResult;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WinningResultTest {

    @DisplayName("일치하는 번호의 개수와 보너스 번호 포함 여부에 따라 결과를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"6,false,FIRST_PLACE", "5,true,SECOND_PLACE", "5,false,THIRD_PLACE", "4,true,FOURTH_PLACE", "4,false,FOURTH_PLACE", "3,true,FIFTH_PLACE", "3,false,FIFTH_PLACE", "2,true,NO_WIN", "2,false,NO_WIN", "1,true,NO_WIN", "1,false,NO_WIN", "0,true,NO_WIN", "0,false,NO_WIN"})
    void getWinningResultBy(int matchingNumberCount, boolean hasBonusNumber, WinningResult expected) {
        // arrange
        WinningResult result = WinningResult.getWinningResultBy(matchingNumberCount, hasBonusNumber);

        // assert
        assertEquals(result, expected);
    }

    @Test
    void calculateRateOfReturn() {
    }
}