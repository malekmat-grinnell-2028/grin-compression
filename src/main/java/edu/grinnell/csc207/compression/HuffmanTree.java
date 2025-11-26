package edu.grinnell.csc207.compression;

import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * A HuffmanTree derives a space-efficient coding of a collection of byte
 * values.
 *
 * The huffman tree encodes values in the range 0--255 which would normally
 * take 8 bits. However, we also need to encode a special EOF character to
 * denote the end of a .grin file. Thus, we need 9 bits to store each
 * byte value. This is fine for file writing (modulo the need to write in
 * byte chunks to the file), but Java does not have a 9-bit data type.
 * Instead, we use the next larger primitive integral type, short, to store
 * our byte values.
 */
public class HuffmanTree {

    private class Pair implements Comparable<Pair> {
        private final short val;
        private final int count;

        public Pair(short val, int count) {
            this.val = val;
            this.count = count;
        }

        @Override
        public int compareTo(Pair p) {
            return count - p.getCount();
        }

        public int getCount() {
            return count;
        }

        public short getVal() {
            return val;
        }
    }

    private static class Node implements Comparable<Node> {
        private Node left = null;
        private Node right = null;
        private short val;
        private int count = 0;
        private int freq;
        private boolean isLeaf;

        public Node(short val, int count, int freq, Node left, Node right, boolean isLeaf) {
            this.val = val;
            this.count = count;
            this.freq = freq;
            this.left = left;
            this.right = right;
            this.isLeaf = isLeaf;
        }

        // @Override
        public int compareTo(Node other) {
            return Integer.compare(this.freq, other.freq);
        }
    }

    private PriorityQueue<Node> que;
    private Node root;

    /**
     * Constructs a new HuffmanTree from a frequency map.
     * 
     * @param freqs a map from 9-bit values to frequencies.
     */
    public HuffmanTree(Map<Short, Integer> freqs) { // encoding constructor
        que = new PriorityQueue<>();

        // mappings --> leaves --> add to queue
        Short[] arr = freqs.keySet().toArray(new Short[0]);
        for (int i = 0; i < arr.length; i++) {
            int count = freqs.get(arr[i]);
            Node n = new Node(arr[i], count, count, null, null, true);
            que.add(n);
        }

        // add eof to priority que
        // Node eof = new Node((short) 256, 1, 1, null, null, true);
        // que.add(eof);

        // make tree from queue
        while (que.size() > 1) {
            Node lchild = que.poll();
            Node rchild = que.poll();
            // placeholder value of -1
            Node parent = new Node((short) -1, -1, lchild.freq + rchild.freq, lchild, rchild, false);
            que.add(parent);
        }

        // get final node in queue as root
        root = que.poll();
    }

    /**
     * Constructs a new HuffmanTree from the given file.
     * 
     * @param in the input file (as a BitInputStream)
     */
    public HuffmanTree(BitInputStream in) { // decoding constructor

    }

    public void serializeHelper(BitOutputStream out, Node n) {
        // if the node is a leaf, add a 0 to the stream then the bit sequence for that
        // character
        // if the node is not a leaf, add a 1 to the stream then recursively call the
        // left branch and the right branch
        if (n.isLeaf) {
            out.writeBit(0);
            out.writeBits(n.val, 9);
        } else {
            out.writeBit(1);
            serializeHelper(out, n.left);
            serializeHelper(out, n.right);
        }
    }

    /**
     * Writes this HuffmanTree to the given file as a stream of bits in a
     * serialized format.
     * 
     * @param out the output file as a BitOutputStream
     */
    public void serialize(BitOutputStream out) {
        // since the que should contain nodes
        serializeHelper(out, root);
    }

    // public void serializeHelper(BitOutputStream out, Node n) {
    // if (n.isLeaf) {
    // out.writeBit(0);
    // } else {
    // out.writeBit(1);
    // }

    // // write n.val
    // }

    /**
     * Encodes the file given as a stream of bits into a compressed format
     * using this Huffman tree. The encoded values are written, bit-by-bit
     * to the given BitOuputStream.
     * 
     * @param in  the file to compress.
     * @param out the file to write the compressed output to.
     */
    public void encode(BitInputStream in, BitOutputStream out) {
        while (in.hasBits()) {
            int character = in.readBits(8);
            short bitSequence = 0;
            encodeHelper(in, out, root, character, bitSequence);
        }
        // not sure if this works for adding the EOF character, but I hope it does
        encodeHelper(in, out, root, (short) 256, (short) 0);
    }

    public void addToBitOutputStream(BitOutputStream out, short bitSequence) {
        while (bitSequence != 0) {
            out.writeBit(bitSequence % 2);
            bitSequence = (short) (bitSequence >> 1);
        }
    }

    public boolean encodeHelper(BitInputStream in, BitOutputStream out, Node n, int character, short bitSequence) {
        if (n.isLeaf) {
            if (character == (int) n.val) {
                addToBitOutputStream(out, bitSequence);
                return true;
            }
            return false;
        } else {
            return (encodeHelper(in, out, n, character, (short) (bitSequence << 1))
                    || encodeHelper(in, out, n, character, (short) ((bitSequence << 1) + 1)));
        }
    }

    private void getValuesHelper(HashSet<Short> set, Node n) {
        if(n.isLeaf) {
            set.add(n.val);
        } else {
            getValuesHelper(set, n.left);
            getValuesHelper(set, n.right);
        }
    }

    //for testing
    public HashSet<Short> getValues() {
        HashSet<Short> set = new HashSet<>();
        getValuesHelper(set, root);
        return set;
    }

    private void getCountsHelper(HashSet<Integer> set, Node n) {
        if(n.isLeaf) {
            set.add(n.count);
        } else {
            getCountsHelper(set, n.left);
            getCountsHelper(set, n.right);
        }
    }

    //for testing
    public HashSet<Integer> getCounts() {
        HashSet<Integer> set = new HashSet<>();
        getCountsHelper(set, root);
        return set;
    }

    /**
     * Decodes a stream of huffman codes from a file given as a stream of
     * bits into their uncompressed form, saving the results to the given
     * output stream. Note that the EOF character is not written to out
     * because it is not a valid 8-bit chunk (it is 9 bits).
     * 
     * @param in  the file to decompress.
     * @param out the file to write the decompressed output to.
     */
    public void decode(BitInputStream in, BitOutputStream out) {
        // TODO: fill me in!
    }

}