package vn.edu.iuh.fit.music_steam_app_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vn.edu.iuh.fit.music_steam_app_backend.backend.enums.Genre;
import vn.edu.iuh.fit.music_steam_app_backend.backend.enums.Language;
import vn.edu.iuh.fit.music_steam_app_backend.backend.models.Album;
import vn.edu.iuh.fit.music_steam_app_backend.backend.models.Artist;
import vn.edu.iuh.fit.music_steam_app_backend.backend.models.Song;
import vn.edu.iuh.fit.music_steam_app_backend.backend.repositories.AlbumRepository;
import vn.edu.iuh.fit.music_steam_app_backend.backend.repositories.ArtistRepository;
import vn.edu.iuh.fit.music_steam_app_backend.backend.repositories.SongRepository;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootApplication
public class MusicSteamAppBackendApplication implements CommandLineRunner {

    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private SongRepository songRepository;

    public static void main(String[] args) {
        SpringApplication.run(MusicSteamAppBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        // Tạo Artist mẫu
//        Artist artist1 = new Artist(null, "Mer Watson", "A popular artist in the pop genre.", "https://picsum.photos/200/300", "1.234K Followers");
//        artistRepository.save(artist1);
//
//        Artist artist2 = new Artist(null, "Anthony Taylor", "An amazing pop artist.", "https://picsum.photos/200/300", "5.678K Followers");
//        artistRepository.save(artist2);
//
//        // Tạo Album mẫu
//        Album album1 = new Album(null, "Summer Vibes", "https://picsum.photos/200", artist1);
//        albumRepository.save(album1);
//
//        Album album2 = new Album(null, "Shape of You", "https://picsum.photos/200", artist2);
//        albumRepository.save(album2);
//
//
//        // Tạo Song mẫu (với giá trị hợp lệ cho trường artist và album)
//        Song song1 = new Song(null, "FLOWER", Duration.parse("PT3M36S"), "../assets/My Library/Image 101.png", Genre.POP, "lyrics...", LocalDateTime.parse("2024-01-01T00:00:00"), Language.ENGLISH,210000,"Album",false, artist1, album1);
//        songRepository.save(song1);
//
//        Song song2 = new Song(null, "Shape of You", Duration.parse("PT3M35S"), "../assets/My Library/Image 102.png", Genre.POP, "lyrics...", LocalDateTime.parse("2023-10-01T00:00:00"), Language.ENGLISH,12000,"Artist",true, artist2, album2);
//
//
//        Song song3 = new Song(null, "Blinding Lights", Duration.parse("PT3M20S"), "../assets/My Library/Image 103.png", Genre.POP, "lyrics...", LocalDateTime.parse("2024-02-01T00:00:00"), Language.ENGLISH,534000,"Album",false, artist1, album1);
//
//
//        Song song4 = new Song(null, "Lost in Japan", Duration.parse("PT3M45S"), "../assets/My Library/Image 104.png", Genre.POP, "lyrics...", LocalDateTime.parse("2024-05-01T00:00:00"), Language.ENGLISH,45000,"Artist",true, artist2, album2);
//
//
//        Song song5 = new Song(null, "Stay", Duration.parse("PT2M40S"), "../assets/My Library/Image 105.png", Genre.POP, "lyrics...", LocalDateTime.parse("2024-07-01T00:00:00"), Language.ENGLISH,231000,"Album",true, artist1, album1);
//
//
//        Song song6 = new Song(null, "Memories", Duration.parse("PT3M15S"), "../assets/My Library/Image 106.png", Genre.POP, "lyrics...", LocalDateTime.parse("2024-08-01T00:00:00"), Language.ENGLISH,21000,"Artist",false, artist2, album2);
//        songRepository.save(song2);
//        songRepository.save(song3);
//        songRepository.save(song4);
//        songRepository.save(song5);
//        songRepository.save(song6);
    }
}
