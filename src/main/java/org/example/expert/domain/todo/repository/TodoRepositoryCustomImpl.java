package org.example.expert.domain.todo.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.comment.entity.QComment;
import org.example.expert.domain.todo.entity.QTodo;
import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.user.entity.QUser;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class TodoRepositoryCustomImpl implements TodoRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Todo> findByIdWithUser(Long todoId){
        QTodo todo = QTodo.todo;
        QUser user = QUser.user;

        Todo foundTodo = queryFactory
                .selectFrom(todo)
                .leftJoin(todo.user,user).fetchJoin()
                .where(todo.id.eq(todoId))
                .fetchOne();

        return Optional.ofNullable(foundTodo);
    }

    @Override
    public Optional<Todo> findBySearch(String title, String nickname, LocalDateTime startdate, LocalDateTime enddate) {
        QTodo todo = QTodo.todo;
        QUser user = QUser.user;
        QComment comment = QComment.comment;

        // 동적 조건을 추가할 수 있는 BooleanBuilder 객체
        BooleanBuilder predicate = new BooleanBuilder();

        // 제목 조건
        if (title != null && !title.isEmpty()) {
            predicate.and(todo.title.containsIgnoreCase(title));
        }

        // 닉네임 조건
        if (nickname != null && !nickname.isEmpty()) {
            predicate.and(user.nickname.containsIgnoreCase(nickname));
        }

        //일정 범위

        //

        return null;
    }
}
