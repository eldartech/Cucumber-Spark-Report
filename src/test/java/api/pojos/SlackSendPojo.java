package api.pojos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
public class SlackSendPojo {
    private String channel;
    private String text;
    private String username;

    public SlackSendPojo(String channel, String text, String username) {
        this.channel = channel;
        this.text = text;
        this.username = username;
    }
}
