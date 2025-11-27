# Project: Grin Compression

Authors: Z Schwab and Matt Malek

## Program Execution

*   to encode a file, the source file must be a .txt file and the target file must be a .grin file
e.i. - mvn exec:java -Dexec.mainClass="edu.grinnell.csc207.compression.Grin" -Dexec.args="encode <"sourcefile.txt"> <"targetfile.grin">"

*   to decode a file, the source file must be a .grin file and the target file must be a .txt file
e.i - mvn exec:java -Dexec.mainClass="edu.grinnell.csc207.compression.Grin" -Dexec.args="decode <"sourcefile.grin"> <"targetfile.txt">"



## Resources

*   Java version 17
*   Java API Documentation

## Revision Log

commit b67428dd061316fbab42d8788f005df54eb672db (HEAD, origin/main, origin/HEAD, HuffmanTree-M)
Merge: 1aa2c97 724a01f
Author: malekmat-grinnell-2028 <malekmat@grinnell.edu>
Date:   Thu Nov 27 16:16:00 2025 -0600

    Merge pull request #2 from malekmat-grinnell-2028/HuffmanTree-M
    
    fixed serialize method, wrote encode, decode, and main in Grin class

commit 724a01f0862c7486dbd89053cd4894331794d4de
Merge: d036bbc 1aa2c97
Author: malekmat-grinnell-2028 <malekmat@grinnell.edu>
Date:   Thu Nov 27 16:15:27 2025 -0600

    Merge branch 'main' into HuffmanTree-M

commit d036bbc857425136b715ef62b40c24043ec90e09 (origin/HuffmanTree-M)
Author: matt <malekmat@grinnell.edu>
Date:   Thu Nov 27 16:05:26 2025 -0600

    fixed serialize method, wrote encode, decode, and main in Grin class

commit 1aa2c97cfeff3a6225b026120f64fa9ef318095d
Author: Z Schwab <zbajraktarischwab@gmail.com>
Date:   Thu Nov 27 14:59:19 2025 -0600

    wrote decode() method

commit c3bb0c7d10d7e110c1b37ebfbd98e2d14e793698
Author: Z Schwab <zbajraktarischwab@gmail.com>
Date:   Thu Nov 27 14:56:27 2025 -0600

    started a slightly broken .grin test

commit 2ef92a11fb5b655013b581e21180eecccba9596f
Author: Z Schwab <zbajraktarischwab@gmail.com>
Date:   Thu Nov 27 14:48:15 2025 -0600

    updated testing files

commit aa2154b884aa971262ee64ddc37715244529ee43
Author: Z Schwab <zbajraktarischwab@gmail.com>
Date:   Thu Nov 27 14:27:39 2025 -0600

    wrote encode() method

commit f84e5b33639734620c7f10e422b9bcdd922a0f85
Author: Z Schwab <zbajraktarischwab@gmail.com>
Date:   Thu Nov 27 14:12:30 2025 -0600

    added encode/decode tests for larger files

commit 6c5a056f34ee9d80508e7d60eaca4e220d206713
Author: Z Schwab <zbajraktarischwab@gmail.com>
Date:   Thu Nov 27 12:25:47 2025 -0600

    updated encode/decode test

commit af27b25c234717d5bbac03236ddf69cf0b3af9dd
Author: Z Schwab <zbajraktarischwab@gmail.com>
Date:   Thu Nov 27 12:25:18 2025 -0600

    wrote helper methods for encode, fixed bug in decode

commit d6e7d1705693de3f4efab1f70f7c916895bd94e2
Author: Z Schwab <zbajraktarischwab@gmail.com>
Date:   Wed Nov 26 22:10:41 2025 -0600

    updating main with least broken code bc we did bad at git

commit bbcf067df2a8e6fb4b5689c73ea7d462c009cfe7
Author: Z Schwab <zbajraktarischwab@gmail.com>
Date:   Wed Nov 26 21:11:44 2025 -0600

    wrote test for encode/decode

commit db33eb38527088346b61b46a9cbe98472674d745
Author: Z Schwab <zbajraktarischwab@gmail.com>
Date:   Wed Nov 26 21:11:19 2025 -0600

    got rid of unused Pair class

commit e4b43f70df3e69e24496370f0b6d523028544ca0
Author: Z Schwab <zbajraktarischwab@gmail.com>
Date:   Wed Nov 26 17:38:51 2025 -0600

    made serializiation helpers private, wrote decode constructor

commit 19c7b3aee71080a32922afb44a6c11b5f5ac09a8
Author: matt <malekmat@grinnell.edu>
Date:   Wed Nov 26 15:01:15 2025 -0600

    bug fix

commit e657d26d0eb656c14a36e55d13dfade365acdb40
Author: matt <malekmat@grinnell.edu>
Date:   Wed Nov 26 15:00:50 2025 -0600

    bug fix

commit a54501dac7e06cf20615df054487bea9e8333e72
Author: matt <malekmat@grinnell.edu>
Date:   Mon Nov 24 23:13:43 2025 -0600

    I wrote some questionable code for the encode method, but Im not sure how to test it yet. I hope its mostly correct

commit 3ba4bb47639748b8e2375acb52d80a79935fcf69
Author: matt <malekmat@grinnell.edu>
Date:   Mon Nov 24 22:06:23 2025 -0600

    wrote serialize method

commit 4751f870219185c93b3f820d32d7aa28a0711e2b
Author: Z Schwab <zbajraktarischwab@gmail.com>
Date:   Mon Nov 24 19:28:11 2025 -0600

    finished encoding tree constructor, made priority queue Nodes instead of Pairs

commit c0f1e1838ed5aafd4d022a40bc7ab7d950a82d23
Author: Z Schwab <zbajraktarischwab@gmail.com>
Date:   Sun Nov 23 20:31:52 2025 -0600

    debugged createFrequencyMap

Date:   Thu Nov 27 14:48:15 2025 -0600

    updated testing files

commit aa2154b884aa971262ee64ddc37715244529ee43
Author: Z Schwab <zbajraktarischwab@gmail.com>
Date:   Thu Nov 27 14:27:39 2025 -0600

    wrote encode() method

commit f84e5b33639734620c7f10e422b9bcdd922a0f85
Author: Z Schwab <zbajraktarischwab@gmail.com>
Date:   Thu Nov 27 14:12:30 2025 -0600

    added encode/decode tests for larger files

commit 6c5a056f34ee9d80508e7d60eaca4e220d206713
Author: Z Schwab <zbajraktarischwab@gmail.com>
Date:   Thu Nov 27 12:25:47 2025 -0600

    updated encode/decode test

commit af27b25c234717d5bbac03236ddf69cf0b3af9dd
Author: Z Schwab <zbajraktarischwab@gmail.com>
Date:   Thu Nov 27 12:25:18 2025 -0600

    wrote helper methods for encode, fixed bug in decode

commit d6e7d1705693de3f4efab1f70f7c916895bd94e2
Author: Z Schwab <zbajraktarischwab@gmail.com>
Date:   Wed Nov 26 22:10:41 2025 -0600

    updating main with least broken code bc we did bad at git

commit bbcf067df2a8e6fb4b5689c73ea7d462c009cfe7
Author: Z Schwab <zbajraktarischwab@gmail.com>
Date:   Wed Nov 26 21:11:44 2025 -0600

    wrote test for encode/decode

commit db33eb38527088346b61b46a9cbe98472674d745
Author: Z Schwab <zbajraktarischwab@gmail.com>
Date:   Wed Nov 26 21:11:19 2025 -0600

    got rid of unused Pair class

commit e4b43f70df3e69e24496370f0b6d523028544ca0
Author: Z Schwab <zbajraktarischwab@gmail.com>
Date:   Wed Nov 26 17:38:51 2025 -0600

    made serializiation helpers private, wrote decode constructor

commit 19c7b3aee71080a32922afb44a6c11b5f5ac09a8
Author: matt <malekmat@grinnell.edu>
Date:   Wed Nov 26 15:01:15 2025 -0600

    bug fix

commit e657d26d0eb656c14a36e55d13dfade365acdb40
Author: matt <malekmat@grinnell.edu>
Date:   Wed Nov 26 15:00:50 2025 -0600

    bug fix

commit a54501dac7e06cf20615df054487bea9e8333e72
Author: matt <malekmat@grinnell.edu>
Date:   Mon Nov 24 23:13:43 2025 -0600

    I wrote some questionable code for the encode method, but Im not sure how to test it yet. I hope its mostly correct

commit 3ba4bb47639748b8e2375acb52d80a79935fcf69
Author: matt <malekmat@grinnell.edu>
Date:   Mon Nov 24 22:06:23 2025 -0600

    wrote serialize method

commit 4751f870219185c93b3f820d32d7aa28a0711e2b
Author: Z Schwab <zbajraktarischwab@gmail.com>
Date:   Mon Nov 24 19:28:11 2025 -0600

    finished encoding tree constructor, made priority queue Nodes instead of Pairs

commit c0f1e1838ed5aafd4d022a40bc7ab7d950a82d23
Author: Z Schwab <zbajraktarischwab@gmail.com>
Date:   Sun Nov 23 20:31:52 2025 -0600

    debugged createFrequencyMap

commit 962e39a3f38227262b4a1050f5cd449e0380b939
Author: Z Schwab <zbajraktarischwab@gmail.com>
Date:   Sun Nov 23 20:22:46 2025 -0600

    added Node class, set up constructors, added getVal method to Pair class

commit b7f076a37c1659b6ef95cc13210f1b0db49aacb0
Author: Z Schwab <zbajraktarischwab@gmail.com>
Date:   Sun Nov 23 19:52:16 2025 -0600

    changed types to equivalent primitives for efficiency

commit c358506b8c12e4f9b5802a6e4ccc934e16efbc86
Merge: c529b1a b1d263b
Author: malekmat-grinnell-2028 <malekmat@grinnell.edu>
Date:   Sat Nov 22 22:22:03 2025 -0600

    Merge pull request #1 from malekmat-grinnell-2028/HuffmanTree-M
    
    implemented HuffmanTree contstructer and Pair private class

commit c529b1a9b80043fb06b97645d33924272fb5613a
Author: Z Schwab <zbajraktarischwab@gmail.com>
Date:   Sat Nov 22 22:12:24 2025 -0600

    merged createFrequencyMap method

commit b1d263bf5699a5c39ccdff42e8421b24169d8131
Author: matt <malekmat@grinnell.edu>
Date:   Sat Nov 22 21:52:14 2025 -0600

    implemented HuffmanTree contstructer and Pair private class

commit 760697f8c6263176c45927308111c30ebaa4f913
Author: Peter-Michael Osera <osera@cs.grinnell.edu>
Date:   Sat Apr 19 00:15:43 2025 -0500

    project files

commit 6ce2aa41fa0186a2c269cc6ce71acf9345984c73
Author: Peter-Michael Osera <osera@cs.grinnell.edu>
Date:   Sat Apr 19 00:02:47 2025 -0500

    initial commit
