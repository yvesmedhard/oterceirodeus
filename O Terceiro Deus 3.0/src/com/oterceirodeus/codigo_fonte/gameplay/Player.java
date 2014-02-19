package com.oterceirodeus.codigo_fonte.gameplay;


import jaco.mp3.player.MP3Player;

import java.io.File;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Víctor
 */
public final class Player {

    MP3Player backmusic;
    MP3Player other;

    public Player() {
        this.backmusic = new MP3Player();
        this.other = new MP3Player();

        this.backmusic.setRepeat(true);
        this.other.setRepeat(false);
 }

    public MP3Player setBackMusic(String s) {
        this.backmusic.getPlayList().removeAll(backmusic.getPlayList());
        this.backmusic.addToPlayList(new File("src\\Audio\\" + s));
        //this.backmusic.skipForward();
        return this.backmusic;
    }

    public void stopBackMusic() {
        this.backmusic.stop();
    }

    public void playBackMusic() {
        this.backmusic.play();
    }

    public void pauseBackMusic() {
        this.backmusic.pause();
    }

    public MP3Player setOtherMusic(String s) {
        this.other.getPlayList().retainAll(other.getPlayList());
        this.other.addToPlayList(new File("src\\Audio\\" + s));
        //this.other.skipForward();
        
        return this.other;
    }

    public void stopOtherMusic() {
        this.other.stop();
    }

    public void playOtherMusic() {
        this.other.play();
    }

    public void pauseOtherMusic() {
        this.other.pause();
    }

    public static void main(String[] args) {
        Player player = new Player();
        player.setBackMusic("QuestCompleted.mp3");
        while (player.backmusic.isEnabled()) {
            System.out.println("clik");
            player.playBackMusic();
        }


    }
}