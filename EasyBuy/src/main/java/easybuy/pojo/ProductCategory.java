package easybuy.pojo;

public class ProductCategory {
    private Long epcId;

    private String epcName;

    private Long epcParentId;

    public Long getEpcId() {
        return epcId;
    }

    public void setEpcId(Long epcId) {
        this.epcId = epcId;
    }

    public String getEpcName() {
        return epcName;
    }

    public void setEpcName(String epcName) {
        this.epcName = epcName == null ? null : epcName.trim();
    }

    public Long getEpcParentId() {
        return epcParentId;
    }

    public void setEpcParentId(Long epcParentId) {
        this.epcParentId = epcParentId;
    }
}