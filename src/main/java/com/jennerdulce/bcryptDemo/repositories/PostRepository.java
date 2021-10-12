package com.jennerdulce.bcryptDemo.repositories;

import com.jennerdulce.bcryptDemo.models.Account;
import com.jennerdulce.bcryptDemo.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
