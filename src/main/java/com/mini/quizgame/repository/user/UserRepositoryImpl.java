package com.mini.quizgame.repository.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.mini.quizgame.domain.QUser.user;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public boolean existsByUserName(String userName) {
        Integer result = queryFactory
                .selectOne()
                .from(user)
                .where(user.userName.eq(userName))
                .fetchFirst();
        return result != null;
    }
}
