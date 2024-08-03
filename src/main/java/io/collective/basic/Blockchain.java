package io.collective.basic;

import java.security.NoSuchAlgorithmException;

public class Blockchain {
    private int size = 0;
    private Block lastBlock = null;
    private Block currentBlock = null;;

    public Blockchain() {
      size = 0;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void add(Block block) {
      lastBlock = currentBlock;
      currentBlock = block;
      size++;
    }

    public int size() {
        return size;
    }

    public boolean isValid() throws NoSuchAlgorithmException {

        // todo - check mined
        Boolean isMined = isMined(currentBlock);

        // todo - check previous hash matches
        Boolean previousHashMatches = currentBlock.getPreviousHash().equals(lastBlock.getHash());

        // todo - check hash is correctly calculated

        return false;
    }

    /// Supporting functions that you'll need.

    public static Block mine(Block block) throws NoSuchAlgorithmException {
        Block mined = new Block(block.getPreviousHash(), block.getTimestamp(), block.getNonce());

        while (!isMined(mined)) {
            mined = new Block(mined.getPreviousHash(), mined.getTimestamp(), mined.getNonce() + 1);
        }
        return mined;
    }

    public static boolean isMined(Block minedBlock) {
        return minedBlock.getHash().startsWith("00");
    }
}
