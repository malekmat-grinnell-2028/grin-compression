package edu.grinnell.csc207.compression;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * The driver for the Grin compression program.
 */
public class Grin {

    public static final int GRIN_HEADER = 0x736;

    /**
     * Decodes the .grin file denoted by infile and writes the output to the
     * .grin file denoted by outfile.
     * @param infile the file to decode
     * @param outfile the file to ouptut to
     * @throws IOException 
     */
    public static void decode (String infile, String outfile) throws IOException {
        BitInputStream in = new BitInputStream(infile);
        BitOutputStream out = new BitOutputStream(outfile);

        int header = in.readBits(32);
        if (header!= GRIN_HEADER) {
            throw new IllegalArgumentException("Invalid file header: program takes .grin");
        }

        HuffmanTree h = new HuffmanTree(in);
        h.decode(in, out);

        in.close();
        out.close();
    }

    /**
     * Creates a mapping from 8-bit sequences to number-of-occurrences of
     * those sequences in the given file. To do this, read the file using a
     * BitInputStream, consuming 8 bits at a time.
     * @param file the file to read
     * @return a freqency map for the given file
     */
    public static Map<Short, Integer> createFrequencyMap (String file) {
        BitInputStream in = null;
        try {
            in = new BitInputStream(file);
        } catch (IOException e) {
            System.err.println("Failed to read file");
            System.exit(1);
        }

        Map<Short, Integer> m = new HashMap<>();

        short packet;
        while ((packet = (short) in.readBits(8)) != -1) {
            if (m.containsKey(packet)) {
                m.put(packet, m.get(packet)+1);
            } else {
                m.put(packet, 1);
            }
        }

        m.put((short) 256, 1);

        return m;
    }

    private static boolean hasExtension(String path, String extension) {
        if(Files.isRegularFile(Paths.get(path))) {
            String sub = path.substring(path.length()-extension.length(), path.length());
            return sub.equals(extension);
        }
        return false;
    }
  
    /**
     * Encodes the given file denoted by infile and writes the output to the
     * .grin file denoted by outfile.
     * @param infile the file to encode.
     * @param outfile the file to write the output to.
     * @throws IOException 
     */
    public static void encode (String infile, String outfile) throws IOException {
        BitInputStream in = new BitInputStream(infile);
        BitOutputStream out = new BitOutputStream(outfile);

        out.writeBits(GRIN_HEADER, 32);

        Map<Short, Integer> map = createFrequencyMap(infile);
        HuffmanTree h = new HuffmanTree(map);
        h.encode(in, out);

        in.close();
        out.close();
    }

    /**
     * The entry point to the program.
     * @param args the command-line arguments.
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        if(args.length != 3) {
            if(args.length < 3) {
                throw new IOException("Too few aruments, Usage: java Grin <encode|decode> <infile> <outfile>");
            } else {
                throw new IOException("Too many arguments, Usage: java Grin <encode|decode> <infile> <outfile>");
            }
            
        } else if(args[0].equals("encode") && (hasExtension(args[1], "txt") && (hasExtension(args[2], "grin")))) {
            encode(args[1], args[2]);
        } else if((args[0].equals("decode")) && (hasExtension(args[1], "grin") && (hasExtension(args[2], "txt")))) {
            decode(args[1], args[2]);
        } else {
            throw new IOException("Usage: java Grin <encode|decode> <infile> <outfile>");
        }

       
    }
}