package com.back.domain.post.postUser.repository

import com.back.domain.post.postUser.entity.PostUser
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.hibernate.Session

class PostUserRepositoryImpl(
    private val queryFactory: JPAQueryFactory,
    private val entityManager: EntityManager
) : PostUserRepositoryCustom {
    override fun findByUsername(username: String): PostUser? {
        return entityManager.unwrap(Session::class.java)
            .byNaturalId(PostUser::class.java)
            .using(PostUser::username.name, username)
            .load()
    }
}