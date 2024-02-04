package com.example.board.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
public class BoardHasHashtagId implements Serializable {
    private int board;
    private int hashtag;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardHasHashtagId temp = (BoardHasHashtagId) o;
        return board == temp.board && hashtag == temp.hashtag;
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, hashtag);
    }
}
