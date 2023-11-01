package com.my.javaspringpractice.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaginationService {
    public static final int BAR_LENGTH = 5;
    public List<Integer> getPaginationBarNumbers(int currentPageNum, int totalPages) {
        PaginationBarCalculator paginationBarCalculator = PaginationBarCalculator.of(currentPageNum, totalPages, BAR_LENGTH);
        return paginationBarCalculator.calculatePaginationBarNumbers();
    }
}
