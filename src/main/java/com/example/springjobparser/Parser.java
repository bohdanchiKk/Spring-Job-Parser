package com.example.springjobparser;

import com.example.springjobparser.entity.Job;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    public static List<Job> parseJobs(String xmlUrl) {
        List<Job> jobs = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(xmlUrl).get();
            Elements jobElements = doc.select("item");

            for (Element jobElement : jobElements) {
                String title = jobElement.select("title").text();
                String link = jobElement.select("link").text();

                Job job = new Job(title, link);
                jobs.add(job);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return jobs;
    }
}
