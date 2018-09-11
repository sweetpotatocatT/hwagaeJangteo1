package www.hwagae.com.hwagae;

import android.location.Address;

public class AccountAddress {
    private String AddressInfo;
    private String BankInfo;
    private String AccountInfo;
    private String id;
    private String PId;

/*    public AccountAddress(String accountInfo, String s) {

    }*/

    public AccountAddress(String AddressInfo, String BankInfo, String AccountInfo,
                          String id, String PId) {
        this.AddressInfo = AddressInfo;
        this.BankInfo = BankInfo;
        this.AccountInfo = AccountInfo;
        this.id = id;
        this.PId = PId;
    }

    public String getAddressInfo() {
        return AddressInfo;
    }

    public String getBankInfo() {
        return BankInfo;
    }

    public String getAccountInfo() {
        return AccountInfo;
    }

    public String getId() {
        return id;
    }

    public String getPId() {
        return PId;
    }

    public void setAddressInfo(String addressInfo) {
        AddressInfo = addressInfo;
    }

    public void setBankInfo(String bankInfo) {
        BankInfo = bankInfo;
    }

    public void setAccountInfo(String accountInfo) {
        AccountInfo = accountInfo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPId(String PId) {
        this.PId = PId;
    }
}
