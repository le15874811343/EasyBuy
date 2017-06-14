package easybuy.pojo;


public class EasyBuyProduct {
    private Long epId;

    private String epName;

    private String epDescription;

    private double epPrice;

    private Long epStock;

    private Long epcId;

    private Long epcChildId;

    private String epFileName;

    private double epRebate;
    
    private Long saleCount;
    
    private int userCout;
    public Long getEpId() {
        return epId;
    }

    public void setEpId(Long epId) {
        this.epId = epId;
    }

    public String getEpName() {
        return epName;
    }

    public void setEpName(String epName) {
        this.epName = epName == null ? null : epName.trim();
    }

    public String getEpDescription() {
        return epDescription;
    }

    public void setEpDescription(String epDescription) {
        this.epDescription = epDescription == null ? null : epDescription.trim();
    }

    public double getEpPrice() {
        return epPrice;
    }

    public void setEpPrice(double epPrice) {
        this.epPrice = epPrice;
    }

    public Long getEpStock() {
        return epStock;
    }

    public void setEpStock(Long epStock) {
        this.epStock = epStock;
    }

    public Long getEpcId() {
        return epcId;
    }

    public void setEpcId(Long epcId) {
        this.epcId = epcId;
    }

    public Long getEpcChildId() {
        return epcChildId;
    }

    public void setEpcChildId(Long epcChildId) {
        this.epcChildId = epcChildId;
    }

    public String getEpFileName() {
        return epFileName;
    }

    public void setEpFileName(String epFileName) {
        this.epFileName = epFileName == null ? null : epFileName.trim();
    }

	public double getEpRebate() {
		return epRebate;
	}

	public void setEpRebate(double epRebate) {
		this.epRebate = epRebate;
	}

	public Long getSaleCount() {
		return saleCount;
	}

	public void setSaleCount(Long saleCount) {
		this.saleCount = saleCount;
	}

	public int getUserCout() {
		return userCout;
	}

	public void setUserCout(int userCout) {
		this.userCout = userCout;
	}
    
}