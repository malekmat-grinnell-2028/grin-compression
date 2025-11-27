package edu.grinnell.csc207.compression;

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

        // System.err.println("EOF code = " + freqs.get(256));
        System.out.println("HUFFMAN CONSTRUCTOR CALLED");

        for (Map.Entry<Short, Integer> e : freqs.entrySet()) {
            System.out.printf("%d -> %s%n", e.getKey(), e.getValue());
        }
    }

    /**
     * Constructs a new HuffmanTree from the given file.
     * 
     * @param in the input file (as a BitInputStream)
     */
    public HuffmanTree(BitInputStream in) { // decoding constructor
        root = deserializeHelper(in);
    }

    private Node deserializeHelper(BitInputStream in) {
        int bit = in.readBit();
        if (bit == 0) {
            short val = (short) in.readBits(9);
            return new Node(val, -1, -1, null, null, true);
        } else {
            Node lchild = deserializeHelper(in);
            Node rchild = deserializeHelper(in);
            return new Node((short) -1, -1, -1, lchild, rchild, false);
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

    private void serializeHelper(BitOutputStream out, Node n) {
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
     * Encodes the file given as a stream of bits into a compressed format
     * using this Huffman tree. The encoded values are written, bit-by-bit
     * to the given BitOuputStream.
     * 
     * @param in  the file to compress.
     * @param out the file to write the compressed output to.
     */
    public void encode(BitInputStream in, BitOutputStream out) {
        serialize(out);
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
        root = deserializeHelper(in);
        Node cur = root;
        int bit;
        while ((bit = in.readBit()) != -1) {
            if (bit == 0) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }

            if (cur.isLeaf) {
                if (cur.val == 256) {
                    return;
                }
                out.writeBits(cur.val, 8);
                cur = root;
            }

        }
    }

}