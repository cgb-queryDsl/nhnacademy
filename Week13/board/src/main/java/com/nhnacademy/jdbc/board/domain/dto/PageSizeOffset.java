package com.nhnacademy.jdbc.board.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class PageSizeOffset {
    private final int size;
    private final int offset;

    public PageSizeOffset(int size, int offset) {
        this.size = size;
        this.offset = offset;
    }
}
