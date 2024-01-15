package com.example.springjobparser;

import com.example.springjobparser.entity.Job;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;



public class App{

    private static final String XML_URL = "https://jobs.dou.ua/vacancies/feeds/?category=Java";
    private static List<Job> storedJobs = Parser.parseJobs(XML_URL); // Assuming you have a place to store previously checked jobs

    public void main() {
        List<Job> whileStart = new ArrayList<>();
        for (int i = 0; i<5; i++){
            whileStart.add(storedJobs.get(i));
        }
        sendTelegramNotifications(whileStart);
        checkForNewJobs();

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(App::checkForNewJobs, 0, 1, TimeUnit.MINUTES);
        scheduler.scheduleAtFixedRate(App::sayHi,0,5,TimeUnit.HOURS);
    }

    private static void checkForNewJobs() {
        List<Job> newParse = Parser.parseJobs(XML_URL);
        List<Job> new5Jobs = takeFirst5(newParse);
        List<Job> newJobs = checkForNew(storedJobs,new5Jobs);
        sendTelegramNotifications(newJobs);
        System.out.println(newJobs);


    }
    public static List<Job> checkForNew(List<Job> storedJobs, List<Job> currentJobs){
        List<Job> newJobs = new ArrayList<>();
        for (Job job : currentJobs){
            if (!jobContains(storedJobs,job)){
                newJobs.add(job);
            }
        }
        return newJobs;
    }
    public static boolean jobContains(List<Job> storedJobs, Job currentJob){
        for(Job job : storedJobs){
            if (job.getLink().equals(currentJob.getLink())){
                return true;
            }
        }
        return false;
    }

    public static List<Job> takeFirst5(List<Job> listEverything){
        List<Job> top5 = new ArrayList<>();
        for(int i = 0; i<5; i++){
            top5.add(listEverything.get(i));
        }
        return top5;
    }

    private static void sendTelegramNotifications(List<Job> newJobs) {
        MyBot telegramBot = new MyBot();
        String chatId = "-1002101844542";
        for (Job newJob : newJobs) {
            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText("New job available:\nTitle: " + newJob.getTitle() + "\nLink: " + newJob.getLink());
            try {
                telegramBot.execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
    private static void sayHi(){
        MyBot telegramBot = new MyBot();
        String chatId = "-1002101844542";
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("looking for a new job!");
        try {
            telegramBot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
