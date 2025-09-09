package com.back.domain.member.member.dto

import com.back.domain.member.member.entity.BaseMember
import java.time.LocalDateTime

data class MemberDto(
    val id: Int,
    val createDate: LocalDateTime,
    val modifyDate: LocalDateTime,
    val isAdmin: Boolean,
    val name: String,
    val profileImageUrl: String,
) {
    constructor(member: BaseMember) : this(
        member.id,
        member.createDate,
        member.modifyDate,
        member.isAdmin,
        member.name,
        member.profileImgUrlOrDefault
    )
}
