package edu.grinnell.csc207.compression;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static edu.grinnell.csc207.compression.Grin.createFrequencyMap;

public class Tests {
    @Test
    @DisplayName("Build tree from normal map test")
    public void treeFromMapTest() throws IOException {
        Map<Short, Integer> m = createFrequencyMap("files/huffman-example.txt");
        HuffmanTree h = new HuffmanTree(m);
        BitOutputStream out_arr = new BitOutputStream("files/test_output.txt", true);
        h.serialize(out_arr);

        Path filepath = Paths.get("files/test_output.txt");
        String s_out = Files.readString(filepath);

        assertEquals("111000010011", s_out);
    }

    @Test
    @DisplayName("Add values to Huffman Tree")
    public void valuesInTreeTest() {
        Map<Short, Integer> m = createFrequencyMap("files/huffman-example.txt");
        HuffmanTree h = new HuffmanTree(m);

        // set of values gotten from the huffman tree
        HashSet<Short> set1 = h.getValues();

        // set of values being added to the huffman tree (including eof)
        HashSet<Short> set2 = new HashSet<>();
        set2.add((short) 97);
        set2.add((short)32);
        set2.add((short)98);
        set2.add((short)122);
        set2.add((short)256);
        assertEquals(set2, set1);
    }

    @Test
    @DisplayName("counts of characters being added")
    public void countsInTreeTest() {
        Map<Short, Integer> m = createFrequencyMap("files/huffman-example.txt");
        HuffmanTree h = new HuffmanTree(m);
        
        // set of values gotten from the huffman tree
        HashSet<Integer> set1 = h.getCounts();

        // set of values being added to the huffman tree (including eof)
        HashSet<Integer> set2 = new HashSet<>();
        set2.add(1);
        set2.add(1);
        set2.add(2);
        set2.add(2);
        set2.add(3);
        assertEquals(set2, set1);
    }

    @Test
    @DisplayName("Test how single char is serialized")
    public void serializeSingleChar() throws IOException {
        Map<Short, Integer> m = createFrequencyMap("files/single-char.txt");
        HuffmanTree h = new HuffmanTree(m);
        BitOutputStream out_arr = new BitOutputStream("files/single-char-out.txt", true);
        h.serialize(out_arr);

        Path filepath = Paths.get("files/single-char-out.txt");
        String s_out = Files.readString(filepath);

        assertEquals("101000000000001100001", s_out);
    }

    @Test
    @DisplayName("Build tree from empty map test")
    public void treeFromEmptyMapTest() {

    }

    @Test
    @DisplayName("Write tree to file test")
    public void treeToFileTest() {

    }

    @Test
    @DisplayName("Write empty tree to file test")
    public void emptyTreeToFileTest() {

    }

    @Test
    @DisplayName("Encode file test")
    public void encodeTest() {

    }

    @Test
    @DisplayName("Decode compressed file test")
    public void decodeTest() {

    }
}