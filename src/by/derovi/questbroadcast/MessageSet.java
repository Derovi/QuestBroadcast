package by.derovi.questbroadcast;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by User on 19.11.2017.
 */
public class MessageSet {
    private List<String> servers;
    private int interval;
    private String prefix;
    private List<Message> messages;
    private int current;
    ScheduledExecutorService scheduler;

    public MessageSet() {
        messages = new LinkedList<>();
        servers = new LinkedList<>();
        interval = 30;
        prefix = "§4[QUEB] ";
        current = 0;
    }

    public void start() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(messages.size() == 0) return;
                Message message = messages.get(current);
                for(String server : servers) {
                    message.execute(server);
                }
                current = (current + 1) % messages.size();
            }
        };
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(runnable, interval, interval, TimeUnit.SECONDS);
    }

    public void stop() {
        current = 0;
        scheduler.shutdown();
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<String> getServers() {
        return servers;
    }

    public void setServers(List<String> servers) {
        this.servers = servers;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }
}
