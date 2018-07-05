package com.example.liumengchao20180702.Utills;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SteamString {
    public static String tranfroms(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String lin = null;
        StringBuffer stringBuffer = new StringBuffer();
        while ((lin = bufferedReader.readLine())!= null) {
            stringBuffer.append(lin);
        }
  bufferedReader.close();
        return stringBuffer.toString();

    }

    ;
}
