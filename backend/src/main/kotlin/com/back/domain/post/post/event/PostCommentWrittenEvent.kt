package com.back.domain.post.post.event

import com.back.domain.member.member.dto.MemberDto
import com.back.domain.post.post.dto.PostDto
import com.back.domain.post.post.entity.Post
import com.back.domain.post.postComment.dto.PostCommentDto
import com.back.domain.post.postComment.entity.PostComment
import com.back.domain.post.postUser.entity.PostUser
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

data class PostCommentWrittenEvent(
    @field:JsonIgnore val actorDto: MemberDto,
    @field:JsonIgnore val ownerDto: MemberDto,
    @field:JsonIgnoreProperties("title", "content") val postDto: PostDto,
    @field:JsonIgnoreProperties("content") val postCommentDto: PostCommentDto
) {
    constructor(
        actor: PostUser,
        owner: PostUser,
        post: Post,
        postComment: PostComment
    ) : this(
        MemberDto(actor),
        MemberDto(owner),
        PostDto(post),
        PostCommentDto(postComment)
    )

    @JsonIgnore
    val primaryEntityType = PostComment::class.simpleName!!

    @JsonIgnore
    val primaryEntityId = postCommentDto.id

    @JsonIgnore
    val secondaryEntityType = Post::class.simpleName!!

    @JsonIgnore
    val secondaryEntityId = postDto.id
}
