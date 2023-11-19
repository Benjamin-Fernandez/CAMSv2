package CAMSv2;

public class Reply {
    private String name;
    private String reply;

    Reply(String name, String reply) {
        this.name = name;
        this.reply = reply;
    }

    public String getName() {
        return name;
    };
    
    public String getReply() {
        return reply;
    }
}
