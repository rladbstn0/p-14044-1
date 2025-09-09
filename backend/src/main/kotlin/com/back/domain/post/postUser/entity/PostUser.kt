package com.back.domain.post.postUser.entity

import com.back.domain.member.member.entity.BaseMember
import com.back.domain.member.member.entity.Member
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "MEMBER")
class PostUser(
    id: Int,
    username: String,
    @Column(name = "nickname") override var name: String,
    profileImgUrl: String? = null,
    @Column(columnDefinition = "INT DEFAULT 0") var postsCount: Int = 0,
    @Column(columnDefinition = "INT DEFAULT 0") var postCommentsCount: Int = 0,
) : BaseMember(id, username, profileImgUrl) {
    constructor(member: Member) : this(
        member.id,
        member.username,
        member.nickname,
        member.profileImgUrl
    ) {

    }

    fun incrementPostsCount() = postsCount++

    fun decrementPostsCount() = postsCount--

    fun incrementPostCommentsCount() = postCommentsCount++

    fun decrementPostCommentsCount() = postCommentsCount--
}
