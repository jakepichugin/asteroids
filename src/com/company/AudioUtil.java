package com.company;

public class AudioUtil {


    private static AudioUtil ourInstance = new AudioUtil();

    public static AudioUtil getInstance(){
        return ourInstance;
    }

    public AudioUtil() {

    }

}
