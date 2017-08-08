package io.veredictum.registrar;

import org.web3j.protocol.core.methods.response.TransactionReceipt;

public class SimpleReceipt {

    private String transactionHash;
    private String blockNumber;

    public SimpleReceipt(TransactionReceipt transactionReceipt) {
        this.transactionHash = transactionReceipt.getTransactionHash();
        this.blockNumber = transactionReceipt.getBlockNumberRaw();
    }

    public String getTransactionHash() {
        return transactionHash;
    }

    public String getBlockNumber() {
        return blockNumber;
    }
}
