package vn.edu.iuh.fit.music_steam_app_backend.backend.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "artist")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    @Column(name = "name", nullable = false)
    private String name;

    @NonNull
    @Column(name = "bio", length = 500)
    private String bio; // Tiểu sử nghệ sĩ

    @NonNull
    @Column(name = "profile_pic", nullable = false)
    private String profilePic; // URL ảnh đại diện

    @Column(name = "followers")
    private String followers; // Ví dụ: "1.234K Followers"

    @PrePersist
    public void prePersist() {
        if (followers == null) followers = "0 Followers";
    }
}