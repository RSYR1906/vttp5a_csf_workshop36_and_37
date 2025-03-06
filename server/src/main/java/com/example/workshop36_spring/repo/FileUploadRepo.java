package com.example.workshop36_spring.repo;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.example.workshop36_spring.models.Post;

@Repository
public class FileUploadRepo {

    public static final String INSERT_POSTS = "INSERT INTO posts (post_id,comments,picture) VALUES (?,?,?)";

    public static final String SELECT_POSTS = "SELECT post_id,comments,picture FROM posts WHERE post_id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String upload(MultipartFile file, String comments) {
        String postID = UUID.randomUUID().toString()
                .replace("-", "")
                .substring(0, 8);
        try {
            byte[] filesByte = file.getBytes();
            jdbcTemplate.update(INSERT_POSTS, ps -> {
                ps.setString(1, postID);
                ps.setString(2, comments);
                ps.setBytes(3, filesByte);
            });
        } catch (IOException e) {
            throw new RuntimeException("Error while uploading file");
        }
        return postID;
    }

    public Optional<Post> getPostById(String postId) {
        return jdbcTemplate.query(SELECT_POSTS, (ResultSet rs) -> {
            if (rs.next()) {
                return Optional.of(Post.populate(rs));
            } else {
                return Optional.empty();
            }
        }, postId);
    }
}
