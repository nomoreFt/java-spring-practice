package com.my.javaspringpractice.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@DisplayName("[service] 페이징")
@ExtendWith(MockitoExtension.class)
class PaginationServiceTest {

    @InjectMocks private PaginationService paginationService;

    @DisplayName("현재 페이지와 총 페이지를 입력하면, 페이지 바에 표시할 페이지 번호 리스트를 반환한다.")
    @Test
    void givenCurrentPageAndTotalPage_whenRequestingBarList_thenReturnsCalculatedBarList() {
        //given
        int currentPage = 0;
        int totalPage = 10;
        PaginationBarCalculator paginationBarCalculator = PaginationBarCalculator.of(currentPage, totalPage, PaginationService.BAR_LENGTH);
        List<Integer> expected = paginationBarCalculator.calculatePaginationBarNumbers();
        //when
        List<Integer> actual = paginationService.getPaginationBarNumbers(currentPage, totalPage);
        //then
        assertThat(actual).isEqualTo(expected);
    }


}