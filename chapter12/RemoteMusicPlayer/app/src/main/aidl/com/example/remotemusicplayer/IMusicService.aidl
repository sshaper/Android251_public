// IMusicService.aidl
package com.example.remotemusicplayer;

interface IMusicService {
    void playMusic();
    void pauseMusic();
    void stopMusic();
    String getCurrentTrack();
    int getPlayTime();
}