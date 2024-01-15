package com.example.springjobparser.entity;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Job {
    private String title;
    private String link;
    public Job(String title, String link, String description) {
        this.title = title;
        this.link = link;
    }
}
