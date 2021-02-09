package com.company;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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

    public Clip readSoundFile(String filename) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(filename)));
            return clip;
        } catch (Exception exc) {
            throw new RuntimeException(exc); //exc.printStackTrace(System.out);
        }
    }
}
