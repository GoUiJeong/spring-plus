package org.example.expert.domain.todo.repository;

import org.example.expert.domain.todo.entity.Todo;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TodoRepositoryCustom {
    Optional<Todo> findByIdWithUser(Long todoId);

    Optional<Todo> findBySearch(String title, String nickname, LocalDateTime stratdate, LocalDateTime enddate);
}
