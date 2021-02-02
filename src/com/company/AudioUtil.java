package com.company;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class AudioUtil {


    private static AudioUtil ourInstance = new AudioUtil();

    public static AudioUtil getInstance(){

        return ourInstance;

    }

    public AudioUtil() {

    }

    public URL transform(File audiofile) throws MalformedURLException {
        if (audiofile.canRead()) {
             return audiofile.toURI().toURL();
        }
        throw new IllegalArgumentException();
    }

}
