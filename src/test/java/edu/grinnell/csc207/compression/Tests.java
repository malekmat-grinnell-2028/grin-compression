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
        BitOutputStream out_arr = new BitOutputStream("files/test-output.txt", true);
        h.serialize(out_arr);

        Path filepath = Paths.get("files/test-output.txt");
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
        BitOutputStream out_arr = new BitOutputStream("files/test-output.txt", true);
        h.serialize(out_arr);

        Path filepath = Paths.get("files/test-output.txt");
        String s_out = Files.readString(filepath);

        assertEquals("0100000000", s_out);
    }

    @Test
    @DisplayName("Write tree to file test")
    public void treeToFileTest() {

    }

    @Test
    @DisplayName("Encode/decode empty file test")
    public void enDecodeEmptyFileTest() throws IOException {
        Map<Short, Integer> m = createFrequencyMap("files/empty-test.txt");
        HuffmanTree h = new HuffmanTree(m);

        BitInputStream encIn = new BitInputStream("files/empty-test.txt");
        BitOutputStream encOut = new BitOutputStream("files/temp-testdata.txt");

        h.encode(encIn, encOut);
        encIn.close();
        encOut.close();

        BitInputStream decIn = new BitInputStream("files/temp-testdata.txt");
        BitOutputStream decOut = new BitOutputStream("files/test-output.txt");

        h.decode(decIn, decOut);
        decIn.close();
        decOut.close();

        Path fpathIn = Paths.get("files/empty-test.txt");
        String s_in = Files.readString(fpathIn);

        Path fpathOut = Paths.get("files/test-output.txt");
        String s_out = Files.readString(fpathOut);

        assertEquals(s_in, s_out);
    }

    @Test
    @DisplayName("Encode/decode small file test")
    public void enDecodeSmTest() throws IOException {
        Map<Short, Integer> m = createFrequencyMap("files/huffman-example.txt");
        HuffmanTree h = new HuffmanTree(m);

        BitInputStream encIn = new BitInputStream("files/huffman-example.txt");
        BitOutputStream encOut = new BitOutputStream("files/temp-testdata.txt");

        h.encode(encIn, encOut);
        encIn.close();
        encOut.close();

        BitInputStream decIn = new BitInputStream("files/temp-testdata.txt");
        BitOutputStream decOut = new BitOutputStream("files/test-output.txt");

        h.decode(decIn, decOut);
        decIn.close();
        decOut.close();

        Path fpathIn = Paths.get("files/huffman-example.txt");
        String s_in = Files.readString(fpathIn);

        Path fpathOut = Paths.get("files/test-output.txt");
        String s_out = Files.readString(fpathOut);

        assertEquals(s_in, s_out);
    }

    @Test
    @DisplayName("Encode/decode medium file test")
    public void enDecodeMdTest() throws IOException {
        Map<Short, Integer> m = createFrequencyMap("files/test-paragraph.txt");
        HuffmanTree h = new HuffmanTree(m);

        BitInputStream encIn = new BitInputStream("files/test-paragraph.txt");
        BitOutputStream encOut = new BitOutputStream("files/empty-test.txt");

        h.encode(encIn, encOut);
        encIn.close();
        encOut.close();

        BitInputStream decIn = new BitInputStream("files/empty-test.txt");
        BitOutputStream decOut = new BitOutputStream("files/test-output.txt");

        HuffmanTree h2 = new HuffmanTree(decIn);

        h2.decode(decIn, decOut);
        decIn.close();
        decOut.close();

        Path fpathIn = Paths.get("files/test-paragraph.txt");
        String s_in = Files.readString(fpathIn);

        Path fpathOut = Paths.get("files/test-output.txt");
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
      
      
    @DisplayName("Encode/decode large file test")
    public void enDecodeLgTest() throws IOException {
        Map<Short, Integer> m = createFrequencyMap("files/pg2600.txt");
        HuffmanTree h = new HuffmanTree(m);

        BitInputStream encIn = new BitInputStream("files/pg2600.txt");
        BitOutputStream encOut = new BitOutputStream("files/temp-testdata.txt");

        h.encode(encIn, encOut);
        encIn.close();
        encOut.close();

        BitInputStream decIn = new BitInputStream("files/temp-testdata.txt");
        BitOutputStream decOut = new BitOutputStream("files/test-output.txt");

        h.decode(decIn, decOut);
        decIn.close();
        decOut.close();

        Path fpathIn = Paths.get("files/pg2600.txt");
        String s_in = Files.readString(fpathIn);

        Path fpathOut = Paths.get("files/test-output.txt");
        String s_out = Files.readString(fpathOut);

        assertEquals(s_in, s_out);
    }

    @Test
    @DisplayName("Encode/decode small .grin file test")
    public void smGrinTest() throws IOException {
        decode("files/huffman-test.grin", "files/test-output.grin");
    }
}