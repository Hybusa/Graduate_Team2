package ru.skypro.homework.dto.comments;

import lombok.Data;

import java.util.List;
@Data
public class ResponseWrapperComments {
    private int count;
    private List<ResponseComment> results;

    public ResponseWrapperComments(int count, List<ResponseComment> results) {
        this.count = count;
        this.results = results;
    }
}