package com.lianer.nest.wallet.bean;

/**
 * 钱包地址的事件传递类
 * @author allison
 */
public class WalletAddrEventBean {

    private String walletAddress;//钱包地址

    public WalletAddrEventBean(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public String getWalletAddress() {
        return walletAddress;
    }
}
