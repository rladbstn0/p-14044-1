package com.back.domain.post.postUser.entity

import com.back.domain.member.member.entity.BaseMember
import com.back.domain.member.member.entity.Member
import com.back.domain.post.postUser.service.PostUserAttrService
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.springframework.data.annotation.Immutable

@Entity
@Immutable
@Table(name = "member")
class PostUser(
    id: Int,
    username: String,
    @field:Column(name = "nickname") var name: String,
    profileImgUrl: String? = null,
) : BaseMember(id, username, profileImgUrl) {

    companion object {
        lateinit var attrService: PostUserAttrService
    }

    @Transient
    private var _postsCountAttr: PostUserAttr? = null

    @Transient
    private var _postCommentsCountAttr: PostUserAttr? = null

    val postsCountAttr: PostUserAttr
        get() {
            var v = _postsCountAttr
            if (v == null) {
                v = attrService.findBySubjectAndName(this, "postsCount")
                    ?: PostUserAttr(this, "postsCount", "0")
                _postsCountAttr = v
            }
            return v
        }

    val postCommentsCountAttr: PostUserAttr
        get() {
            var v = _postCommentsCountAttr
            if (v == null) {
                v = attrService.findBySubjectAndName(this, "postCommentsCount")
                    ?: PostUserAttr(this, "postCommentsCount", "0")
                _postCommentsCountAttr = v
            }
            return v
        }

    var postsCount: Int
        get() = postsCountAttr.value.toInt()
        set(value) {
            postsCountAttr.value = value.toString()
            attrService.save(postsCountAttr)
        }

    var postCommentsCount: Int
        get() = postCommentsCountAttr.value.toInt()
        set(value) {
            postCommentsCountAttr.value = value.toString()
            attrService.save(postCommentsCountAttr)
        }

    fun incrementPostsCount() {
        postsCount++
    }

    fun decrementPostsCount() {
        postsCount--
    }

    fun incrementPostCommentsCount() {
        postCommentsCount++
    }

    fun decrementPostCommentsCount() {
        postCommentsCount--
    }

    constructor(member: Member) : this(
        member.id,
        member.username,
        member.nickname,
        member.profileImgUrl,
    )
}
