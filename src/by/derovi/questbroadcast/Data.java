package by.derovi.questbroadcast;

import net.md_5.bungee.config.Configuration;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by derovi on 21.11.2017.
 */
public class Data {
    public static List<MessageSet> messages;
    public static void loadData() {
        messages = new LinkedList<>();
        for(String sectionName : QuestBroadcast.config.getSection("sets").getKeys()) {
            Configuration set = QuestBroadcast.config.getSection("sets." + sectionName);
            MessageSet messageSet = new MessageSet();
            messageSet.setServers(set.getStringList("servers"));
            messageSet.setInterval(set.getInt("interval"));
            messageSet.setPrefix(TextFormat.format(set.getString("prefix")));
            List<Message> mes = new LinkedList<>();
            for(String messageName : set.getSection("messages").getKeys()) {
                Message message = new Message();
                message.setText(set.getStringList("messages." + messageName + ".text"));
                if(messageSet.getPrefix() != null) {
                    for(int ind = 0; ind < message.getText().size(); ++ ind) {
                        message.getText().set(ind, TextFormat.format(messageSet.getPrefix() + message.getText().get(ind)));
                    }
                }
                message.setTitle(TextFormat.format(set.getString("messages." + messageName + ".title")));
                message.setSubTitle(TextFormat.format(set.getString("messages." + messageName + ".subtitle")));
                mes.add(message);
            }
            messageSet.setMessages(mes);
            messages.add(messageSet);
        }
    }
}
