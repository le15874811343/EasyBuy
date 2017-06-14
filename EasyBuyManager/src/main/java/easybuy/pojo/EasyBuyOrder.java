package easybuy.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class EasyBuyOrder {
    private Long eoId;

    private Long eoUserId;

    private String eoProId;

    private String eoCreateTime;

    private double eoCost;

    private String eoStatus;

    private long number;
    public Long getEoId() {
        return eoId;
    }

    public void setEoId(Long eoId) {
        this.eoId = eoId;
    }

    public Long getEoUserId() {
        return eoUserId;
    }

    public void setEoUserId(Long eoUserId) {
        this.eoUserId = eoUserId;
    }

 

    public String getEoProId() {
		return eoProId;
	}

	public void setEoProId(String eoProId) {
		this.eoProId = eoProId;
	}

	public String getEoCreateTime() {
        return eoCreateTime;
    }

    public void setEoCreateTime(String eoCreateTime) {
        this.eoCreateTime = eoCreateTime;
    }

    public double getEoCost() {
        return eoCost;
    }

    public void setEoCost(double eoCost) {
        this.eoCost = eoCost;
    }

    public String getEoStatus() {
        return eoStatus;
    }

    public void setEoStatus(String eoStatus) {
        this.eoStatus = eoStatus == null ? null : eoStatus.trim();
    }

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

}