/*
TODO add license
 */
package io.veredictum.registrar;

import org.web3j.protocol.core.methods.response.TransactionReceipt;

/**
 * A simplified receipt of Ethereum transaction to send back to client
 *
 * @author Fei Yang <fei.yang@veredictum.io>
 */
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
