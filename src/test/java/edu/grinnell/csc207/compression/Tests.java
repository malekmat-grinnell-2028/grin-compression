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

        HuffmanTree h2 = new HuffmanTree(decIn);
        h2.decode(decIn, decOut);
        decIn.close();
        decOut.close();
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

        HuffmanTree h2 = new HuffmanTree(decIn);
        h2.decode(decIn, decOut);
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

        String[] args1 = {"encode", "files/pg2600.txt", "files/test-output.grin"};
        String[] args2 = {"decode", "files/test-output.grin", "files/test-output.txt"};

        Grin.main(args1);
        Grin.main(args2);

        Path fpathIn = Paths.get("files/pg2600.txt");
        String s_in = Files.readString(fpathIn);

        Path fpathOut = Paths.get("files/test-output.txt");
        String s_out = Files.readString(fpathOut);

        assertEquals(s_in, s_out);
    }

    @Test
    @DisplayName("Encode/decode large .txt file test")
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
        
        HuffmanTree h2 = new HuffmanTree(decIn);
        h2.decode(decIn, decOut);
        decIn.close();
        decOut.close();

        Path fpathIn = Paths.get("files/pg2600.txt");
        String s_in = Files.readString(fpathIn);

        Path fpathOut = Paths.get("files/test-output.txt");
        String s_out = Files.readString(fpathOut);

        assertEquals(s_in, s_out);
    }
}