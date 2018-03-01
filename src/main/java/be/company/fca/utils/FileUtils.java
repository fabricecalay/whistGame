package be.company.fca.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static byte[] convertToByteArray(InputStream inputStr) throws IOException {

        List<Byte> liste = new ArrayList<Byte>();

        int c = inputStr.read();
        while (c != -1) {
            liste.add((byte) c);
            c = inputStr.read();
        }

        byte[] byteArray = new byte[liste.size()];

        int i = 0;
        for (Byte mot : liste) {
            byteArray[i] = mot;
            i++;
        }

        return byteArray;

    }
}