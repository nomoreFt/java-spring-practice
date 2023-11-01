package com.my.javaspringpractice.service;

import java.util.List;
import java.util.stream.IntStream;

public class PaginationBarCalculator {
    private int currentPageNum;
    private int totalPages;
    private int barLength;
    protected PaginationBarCalculator() {}

    private PaginationBarCalculator(int currentPageNum, int totalPages, int barLength) {
        this.currentPageNum = currentPageNum;
        this.totalPages = totalPages;
        this.barLength = barLength;
    }

    public static PaginationBarCalculator of(int currentPageNum, int totalPages, int barLength) {
        return new PaginationBarCalculator(currentPageNum, totalPages, barLength);
    }

    public List<Integer> calculatePaginationBarNumbers() {
        int startNumber = Math.max(currentPageNum - (barLength / 2), 0);
        int endNumber = Math.min(startNumber + barLength, totalPages);

        return IntStream.range(startNumber, endNumber).boxed().toList();
    }

    public int currentBarLength(){
        return barLength;
    }
}
