package easybuy.pojo;

import java.util.Date;

public class EasyBuyUser {
    private String euUserId;

    private String euUserName;

    private String euPassword;

    private String euSex;

    private Date euBirthdayl;

    private String euIdentityCode;

    private String euEmail;

    private String euMobile;

    private String euAddress;

    private Integer euStatus;

    public String getEuUserId() {
        return euUserId;
    }

    public void setEuUserId(String euUserId) {
        this.euUserId = euUserId == null ? null : euUserId.trim();
    }

    public String getEuUserName() {
        return euUserName;
    }

    public void setEuUserName(String euUserName) {
        this.euUserName = euUserName == null ? null : euUserName.trim();
    }

    public String getEuPassword() {
        return euPassword;
    }

    public void setEuPassword(String euPassword) {
        this.euPassword = euPassword == null ? null : euPassword.trim();
    }

    public String getEuSex() {
        return euSex;
    }

    public void setEuSex(String euSex) {
        this.euSex = euSex == null ? null : euSex.trim();
    }

    public Date getEuBirthdayl() {
        return euBirthdayl;
    }

    public void setEuBirthdayl(Date euBirthdayl) {
        this.euBirthdayl = euBirthdayl;
    }

    public String getEuIdentityCode() {
        return euIdentityCode;
    }

    public void setEuIdentityCode(String euIdentityCode) {
        this.euIdentityCode = euIdentityCode == null ? null : euIdentityCode.trim();
    }

    public String getEuEmail() {
        return euEmail;
    }

    public void setEuEmail(String euEmail) {
        this.euEmail = euEmail == null ? null : euEmail.trim();
    }

    public String getEuMobile() {
        return euMobile;
    }

    public void setEuMobile(String euMobile) {
        this.euMobile = euMobile == null ? null : euMobile.trim();
    }

    public String getEuAddress() {
        return euAddress;
    }

    public void setEuAddress(String euAddress) {
        this.euAddress = euAddress == null ? null : euAddress.trim();
    }

    public Integer getEuStatus() {
        return euStatus;
    }

    public void setEuStatus(Integer euStatus) {
        this.euStatus = euStatus;
    }
}