package BCSD.MusicStream.repository;

import BCSD.MusicStream.domain.Music;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MusicRepository extends JpaRepository<Music, Long> {
    List<Music> findByNameContainingOrSingerNameContaining(String name, String singerName);

    @Query(value = "SELECT m.*, ( (select if(count(*) > 0, 3, 0) from member_like l where l.music_id = m.id and l.is_like = 1) +\n" +
            "        \n" +
            "\t\t(select if(count(*) > 0, -100, 0) from member_like l where l.music_id = m.id and l.is_like = 0) +\n" +
            "        \n" +
            "\t\t((select count(*) from member_like l join music on music.id = l.music_id where singer_name = m.singer_name and is_like = true and music.member_id = :memberId) * 2) -\n" +
            "        \n" +
            "        ((select count(*) from member_like l join music on music.id = l.music_id where singer_name = m.singer_name and is_like = false and music.member_id = :memberId) * 2) +\n" +
            "        \n" +
            "        ((select count(*) from music_play_history h join music on music.id = h.music_id where h.member_id = :memberId and weather_id = m.weather_id) /\n" +
            "\t\t(select count(*) from music_play_history h join music on music.id = h.music_id where h.member_id = :memberId)) + \n" +
            "        \n" +
            "        ((select count(*) from music_play_history h join music on music.id = h.music_id where h.member_id = :memberId and category_id = m.category_id) /\n" +
            "\t\t(select count(*) from music_play_history h join music on music.id = h.music_id where h.member_id = :memberId)) +\n" +
            "        \n" +
            "        ((select count(*) from member_like l join music on music.id = l.music_id where l.member_id = :memberId and music.category_id = m.category_id and is_like = true) /\n" +
            "\t\t(select if(count(*) = 0, 1, count(*)) from member_like l join music on music.id = l.music_id where l.member_id = :memberId  and is_like = true)) +\n" +
            "        \n" +
            "        ((select count(*) from member_like l join music on music.id = l.music_id where l.member_id = :memberId and music.weather_id = m.weather_id  and is_like = true) /\n" +
            "\t\t(select if(count(*) = 0, 1, count(*)) from member_like l join music on music.id = l.music_id where l.member_id = :memberId  and is_like = true)) -\n" +
            "        \n" +
            "        ((select count(*) from member_like l join music on music.id = l.music_id where l.member_id = :memberId and music.weather_id = m.weather_id  and is_like = false) /\n" +
            "\t\t(select if(count(*) = 0, 1, count(*)) from member_like l join music on music.id = l.music_id where l.member_id = :memberId  and is_like = false)) -\n" +
            "        \n" +
            "        ((select count(*) from member_like l join music on music.id = l.music_id where l.member_id = :memberId and music.weather_id = m.weather_id  and is_like = false) /\n" +
            "\t\t(select if(count(*) = 0, 1, count(*)) from member_like l join music on music.id = l.music_id where l.member_id = :memberId  and is_like = false)) ) as weight FROM music m ORDER BY weight DESC",
            countQuery = "select count(*) from music",
            nativeQuery = true)
    List<Music> findMusicWithWeight(@Param("memberId") Long memberId, Pageable pageable);

    @Query(value = "SELECT m.*, ( (select if(count(*) > 0, 3, 0) from member_like l where l.music_id = m.id and l.is_like = 1) +\n" +
            "        \n" +
            "\t\t(select if(count(*) > 0, -100, 0) from member_like l where l.music_id = m.id and l.is_like = 0) +\n" +
            "        \n" +
            "\t\t((select count(*) from member_like l join music on music.id = l.music_id where singer_name = m.singer_name and is_like = true and music.member_id = :memberId) * 2) -\n" +
            "        \n" +
            "        ((select count(*) from member_like l join music on music.id = l.music_id where singer_name = m.singer_name and is_like = false and music.member_id = :memberId) * 2) +\n" +
            "        \n" +
            "        ((select count(*) from music_play_history h join music on music.id = h.music_id where h.member_id = :memberId and weather_id = m.weather_id) /\n" +
            "\t\t(select count(*) from music_play_history h join music on music.id = h.music_id where h.member_id = :memberId)) + \n" +
            "        \n" +
            "        ((select count(*) from music_play_history h join music on music.id = h.music_id where h.member_id = :memberId and category_id = m.category_id) /\n" +
            "\t\t(select count(*) from music_play_history h join music on music.id = h.music_id where h.member_id = :memberId)) +\n" +
            "        \n" +
            "        ((select count(*) from member_like l join music on music.id = l.music_id where l.member_id = :memberId and music.category_id = m.category_id and is_like = true) /\n" +
            "\t\t(select if(count(*) = 0, 1, count(*)) from member_like l join music on music.id = l.music_id where l.member_id = :memberId  and is_like = true)) +\n" +
            "        \n" +
            "        ((select count(*) from member_like l join music on music.id = l.music_id where l.member_id = :memberId and music.weather_id = m.weather_id  and is_like = true) /\n" +
            "\t\t(select if(count(*) = 0, 1, count(*)) from member_like l join music on music.id = l.music_id where l.member_id = :memberId  and is_like = true)) -\n" +
            "        \n" +
            "        ((select count(*) from member_like l join music on music.id = l.music_id where l.member_id = :memberId and music.weather_id = m.weather_id  and is_like = false) /\n" +
            "\t\t(select if(count(*) = 0, 1, count(*)) from member_like l join music on music.id = l.music_id where l.member_id = :memberId  and is_like = false)) -\n" +
            "        \n" +
            "        ((select count(*) from member_like l join music on music.id = l.music_id where l.member_id = :memberId and music.weather_id = m.weather_id  and is_like = false) /\n" +
            "\t\t(select if(count(*) = 0, 1, count(*)) from member_like l join music on music.id = l.music_id where l.member_id = :memberId  and is_like = false)) ) as weight FROM music m where m.weather_id = :weatherId ORDER BY weight DESC",
            countQuery = "select count(*) from music",
            nativeQuery = true)
    List<Music> findMusicByWeatherWithWeight(@Param("memberId") Long memberId, @Param("weatherId") Long weatherId, Pageable pageable);
}
