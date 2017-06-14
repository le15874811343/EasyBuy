package easybuy.pojo;

import java.util.Date;

public class EasyBuyComment {
    private Long ecId;

    private String ecReply;

    private String ecContent;

    private String ecCreateTime;

    private String ecReplyTime;

    private String ecNickTime;

    public Long getEcId() {
        return ecId;
    }

    public void setEcId(Long ecId) {
        this.ecId = ecId;
    }

    public String getEcReply() {
        return ecReply;
    }

    public void setEcReply(String ecReply) {
        this.ecReply = ecReply == null ? null : ecReply.trim();
    }

    public String getEcContent() {
        return ecContent;
    }

    public void setEcContent(String ecContent) {
        this.ecContent = ecContent == null ? null : ecContent.trim();
    }

    public String getEcCreateTime() {
        return ecCreateTime;
    }

    public void setEcCreateTime(String ecCreateTime) {
        this.ecCreateTime = ecCreateTime;
    }

    public String getEcReplyTime() {
        return ecReplyTime;
    }

    public void setEcReplyTime(String ecReplyTime) {
        this.ecReplyTime = ecReplyTime;
    }

    public String getEcNickTime() {
        return ecNickTime;
    }

    public void setEcNickTime(String ecNickTime) {
        this.ecNickTime = ecNickTime == null ? null : ecNickTime.trim();
    }
}