package com.my.javaspringpractice.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;


@DisplayName("[view] Pagination Bar List 계산")
class PaginationBarCalculatorTest {

    public static final int BAR_LENGTH = 5;
    @DisplayName("BarLength와 현재 페이지, 총 페이지를 입력하면, 페이지 바에 표시할 페이지 번호 리스트를 반환한다.")
    @MethodSource
    @ParameterizedTest(name = "[{index}] 현재 페이지: {0}, 총 페이지: {1} => {2}")
    void givenBarLengthAndCurrentpageAndTotalPage_whenRequestingBarList_thenReturnsCalculatedBarList(int currentPage,
                                                                                                     int totalPage,
                                                                                                     List<Integer> expected){
        //given
        PaginationBarCalculator paginationBarCalculator = PaginationBarCalculator.of(currentPage, totalPage, BAR_LENGTH);
        //when

        List<Integer> actual = paginationBarCalculator.calculatePaginationBarNumbers();

        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> givenBarLengthAndCurrentpageAndTotalPage_whenRequestingBarList_thenReturnsCalculatedBarList(){
        return Stream.of(
                arguments(0, 13, List.of(0, 1, 2, 3, 4)),
                arguments(1, 13, List.of(0, 1, 2, 3, 4)),
                arguments(2, 13, List.of(0, 1, 2, 3, 4)),
                arguments(3, 13, List.of(1, 2, 3, 4, 5)),
                arguments(4, 13, List.of(2, 3, 4, 5, 6)),
                arguments(5, 13, List.of(3, 4, 5, 6, 7)),
                arguments(6, 13, List.of(4, 5, 6, 7, 8)),
                arguments(10, 13, List.of(8, 9, 10, 11, 12)),
                arguments(11, 13, List.of(9, 10, 11, 12)),
                arguments(12, 13, List.of(10, 11, 12))
        );
    }


}