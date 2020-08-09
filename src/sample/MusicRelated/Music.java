package src.sample.MusicRelated;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class Music extends Application {

    private static MediaPlayer mediaPlayer;
    private static String fileName;
    private static String music;
    private static Media media;

    public Music(String name) {
        fileName = "src\\sample\\AudioMP3\\" + name;
    }

    public void start(Stage mainStage) {
        music = new File(fileName.replace('\\', File.separatorChar)).toURI().toString();
        media = new Media(music);
        mediaPlayer = new MediaPlayer(media);

        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.seek(Duration.ZERO);
        });
    }
    public static void clear() {
        mediaPlayer.dispose();
    }
}
