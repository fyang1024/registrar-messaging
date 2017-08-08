/*
TODO add license
 */
package io.veredictum.registrar;

/**
 * It represents a request to register content ownership from client
 *
 * @author Fei Yang <fei.yang@veredictum.io>
 */
public class RegistrarRequest {

    private String[] addresses;

    private int[] shares;

    private String contentId;

    private byte[] originalFileHash;

    private byte[] transcodedFileHash;

    public String[] getAddresses() {
        return addresses;
    }

    public void setAddresses(String[] addresses) {
        this.addresses = addresses;
    }

    public int[] getShares() {
        return shares;
    }

    public void setShares(int[] shares) {
        this.shares = shares;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        final int fixedLength = 6;
        char[] charArray = contentId.toCharArray();
        char[] paddedCharArray = new char[fixedLength];
        for (int i = 0; i < fixedLength ; i++) {
            if (i < fixedLength - charArray.length) {
                paddedCharArray[i] = '0';
            } else {
                paddedCharArray[i] = charArray[i  + charArray.length - fixedLength];
            }
        }
        this.contentId = new String(paddedCharArray);
    }

    public byte[] getOriginalFileHash() {
        return originalFileHash;
    }

    public void setOriginalFileHash(byte[] originalFileHash) {
        this.originalFileHash = originalFileHash;
    }

    public byte[] getTranscodedFileHash() {
        return transcodedFileHash;
    }

    public void setTranscodedFileHash(byte[] transcodedFileHash) {
        this.transcodedFileHash = transcodedFileHash;
    }
}
