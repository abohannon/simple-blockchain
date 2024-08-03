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
        if (isEmpty()) return true;

        if (isMined(currentBlock) &&
            previousHashMatches(lastBlock, currentBlock) &&
            hashIsCorrectlyCalculated(currentBlock)) {
            return true;
        }

        return false;
    }

    private boolean hashIsCorrectlyCalculated(Block currentBlock) throws NoSuchAlgorithmException {
        return currentBlock.getHash().equals(currentBlock.calculatedHash());
    }

    private boolean previousHashMatches(Block lastBlock, Block currentBlock) {
        return lastBlock == null || lastBlock.getHash().equals(currentBlock.getPreviousHash());
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
