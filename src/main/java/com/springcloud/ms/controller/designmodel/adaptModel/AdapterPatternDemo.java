package com.springcloud.ms.controller.designmodel.adaptModel;

/**
 * The following is the design idea of this class:
 * <img width="640" height="320" src="https://github.com/yaorp/one-by-one/blob/1e80d54978f5200571a13548493ee9c780913816/src/main/java/com/springcloud/ms/controller/designmodel/adaptModel/img/img.png" alt="">
 */
public class AdapterPatternDemo {
   public static void main(String[] args) {
      AudioPlayer audioPlayer = new AudioPlayer();
 
      audioPlayer.play("mp3", "beyond the horizon.mp3");
      audioPlayer.play("mp4", "alone.mp4");
      audioPlayer.play("vlc", "far far away.vlc");
      audioPlayer.play("avi", "mind me.avi");
   }
}