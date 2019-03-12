/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the_nights.rainbow_engine.sound;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 *
 * @author Stephanie
 */
public class SoundHandler {

    // sound controls;
    private float musicMasterGain;
    private float soundEffectsMasterGain;
    private FloatControl musicGainControl;
    private FloatControl soundEffectsGainControl;
    // clips 
    private Clip backgroundMusic;

    public SoundHandler() {
        this(0.0f, 0.0f);
    }

    public SoundHandler(float musicMasterGain, float soundEffectsMasterGain) {
        this.musicMasterGain = musicMasterGain;
        this.soundEffectsMasterGain = soundEffectsMasterGain;
    }

    public void playBackgroundMusic(String fileName) {
        try {
            File f = new File(fileName);
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(AudioSystem.getAudioInputStream(f));
            backgroundMusic.loop(Integer.MAX_VALUE);
            musicGainControl = (FloatControl) backgroundMusic.getControl(FloatControl.Type.MASTER_GAIN);
            musicGainControl.setValue(musicMasterGain);
            backgroundMusic.start();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void stopBackgroundMusic() {
        if (backgroundMusic != null) {
            backgroundMusic.stop();
            backgroundMusic.flush();
        }
    }

    public void playSoundEffect(String fileName) {
        try {
            File f = new File(fileName);
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(f));
            soundEffectsGainControl = (FloatControl) backgroundMusic.getControl(FloatControl.Type.MASTER_GAIN);
            soundEffectsGainControl.setValue(soundEffectsMasterGain);
            clip.start();
        } catch (Exception e1) {
            e1.printStackTrace();

        }
    }

    public float getMusicMasterGain() {
        return musicMasterGain;
    }

    public float getSoundEffectsMasterGain() {
        return soundEffectsMasterGain;
    }

    public void setMusicMasterGain(float musicMasterGain) {
        this.musicMasterGain = musicMasterGain;
        if (this.musicGainControl != null) {
            musicGainControl.setValue(musicMasterGain);
        }
    }

    public void setSoundEffectsMasterGain(float soundEffectsMasterGain) {
        this.soundEffectsMasterGain = soundEffectsMasterGain;
        if (this.soundEffectsGainControl != null) {
            soundEffectsGainControl.setValue(soundEffectsMasterGain);
        }
    }
}
