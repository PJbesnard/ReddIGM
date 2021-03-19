package fr.uge.jee.web.service.reddIGM.dto;

import java.util.Objects;

public class ScoreDto {
    private long id;
    private int score;

    public ScoreDto(long id, int score) {
        this.id = id;
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreDto scoreDto = (ScoreDto) o;
        return id == scoreDto.id &&
                score == scoreDto.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, score);
    }
}
