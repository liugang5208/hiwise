package com.sky.hiwise.algorithms.java;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;

public class ReadUrl {

    public void read(String url) {
        try {
            File file = new File("/tmp/text.txt");
            URL url1 = new URL(url);
            InputStream is =  url1.openStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            OutputStream os = Files.newOutputStream(file.toPath());
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

            char[] chars = new char[1024];
            int len = 0;
            while((len = br.read(chars)) != -1) {
                bw.write(chars, 0, len);
                bw.flush();
            }
            os.close();
            is.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
