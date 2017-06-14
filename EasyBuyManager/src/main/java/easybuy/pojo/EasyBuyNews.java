package easybuy.pojo;

import java.util.Date;

public class EasyBuyNews {
    private Long enId;

    private String enTitle;

    private String enContent;

    private String enCreateTime;

    private Integer enType;
    public Long getEnId() {
        return enId;
    }

    public void setEnId(Long enId) {
        this.enId = enId;
    }

    public String getEnTitle() {
        return enTitle;
    }

    public void setEnTitle(String enTitle) {
        this.enTitle = enTitle == null ? null : enTitle.trim();
    }

    public String getEnContent() {
        return enContent;
    }

    public void setEnContent(String enContent) {
        this.enContent = enContent == null ? null : enContent.trim();
    }



	public String getEnCreateTime() {
		return enCreateTime;
	}

	public void setEnCreateTime(String enCreateTime) {
		this.enCreateTime = enCreateTime;
	}

	public Integer getEnType() {
		return enType;
	}

	public void setEnType(Integer enType) {
		this.enType = enType;
	}
    
    
}