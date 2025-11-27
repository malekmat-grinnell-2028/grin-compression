package edu.grinnell.csc207.compression;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

        assertEquals("110001100010101000000000001111010100001000000001100001", s_out);
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
    public void treeFromEmptyMapTest() throws IOException {
        Map<Short, Integer> m = createFrequencyMap("files/empty-test.txt");
        HuffmanTree h = new HuffmanTree(m);
        BitOutputStream out_arr = new BitOutputStream("files/test_output.txt", true);
        h.serialize(out_arr);

        Path filepath = Paths.get("files/test_output.txt");
        String s_out = Files.readString(filepath);

        assertEquals("0100000000", s_out);
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

    @Test
    @DisplayName("Encode/decode file test")
    public void enDecodeTest() throws IOException {
        Map<Short, Integer> m = createFrequencyMap("files/single-char.txt");
        HuffmanTree h = new HuffmanTree(m);

        BitInputStream encIn = new BitInputStream("files/single-char.txt");
        BitOutputStream encOut = new BitOutputStream("files/empty-test.txt");

        h.encode(encIn, encOut);
        encIn.close();
        encOut.close();

        BitInputStream decIn = new BitInputStream("files/empty-test.txt");
        BitOutputStream decOut = new BitOutputStream("files/test_output.txt");

        HuffmanTree h2 = new HuffmanTree(decIn);

        h2.decode(decIn, decOut);
        decIn.close();
        decOut.close();

        Path fpathIn = Paths.get("files/single-char.txt");
        String s_in = Files.readString(fpathIn);

        Path fpathOut = Paths.get("files/test_output.txt");
        String s_out = Files.readString(fpathOut);

        assertEquals(s_in, s_out);
    }

    @Test
    @DisplayName("test main")
    public void testMain() throws IOException {

        String[] args1 = {"encode", "files/single-char.txt", "files/single-char-out.grin"};
        String[] args2 = {"decode", "files/single-char-out.grin", "files/test_output.txt"};
     
        Grin.main(args1);
        Grin.main(args2);

        Path fpathIn = Paths.get("files/single-char.txt");
        String s_in = Files.readString(fpathIn);

        Path fpathOut = Paths.get("files/test_output.txt");
        String s_out = Files.readString(fpathOut);

        assertEquals(s_in, s_out);
    }

}