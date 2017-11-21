package by.derovi.questbroadcast;

import java.util.List;

/**
 * Created by derovi on 21.11.2017.
 */
public class Message {
    private List<String> text;
    private String title;
    private String subTitle;
    public Message() {
        text = null;
        title = null;
        title = subTitle;
    }

    public void execute(String server) {
        if(text != null && text.size() > 0) {
            for(String string : text) {
                Broadcast.broadcastMessage(server, string);
            }
        }
        if(title != null && subTitle != null) {
            Broadcast.broadcastTitle(server, title, subTitle);
            return;
        }
        if(title != null && title.length() > 0) {
            Broadcast.broadcastTitle(server, title, "");
        }
        if(subTitle != null && subTitle.length() > 0) {
            Broadcast.broadcastTitle(server, "", subTitle);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
}
