import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageAttachment;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class DiscordBot {
    public static void main(String[] args) throws IOException {
        // Insert your bot's token here
        Scanner scan = new Scanner(new FileReader("DiscordBotToken.txt"));
        String token = scan.next();
        scan.close();

        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();

        // downloads messages with an image and says fish
        api.addMessageCreateListener(event -> {
            Message message = event.getMessage();
            for (MessageAttachment attachment : message.getAttachments()) {
                // get message
                Message comment = attachment.getMessage();
                String commentStr = comment.getContent();
                if (commentStr.equalsIgnoreCase("fish")) {
                    BufferedImage image = attachment.downloadAsImage().join();
                    String name = attachment.getFileName();
                    try {
                        ImageIO.write(image, "jpg", new File("discordImages/" + name));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        // Print the invite url of your bot
        System.out.println("Invite link: " + api.createBotInvite());
    }
}
