package com.back.domain.member.memberLog.entity

import com.back.domain.member.member.entity.Member
import com.back.global.jpa.entity.BaseTime
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType.LAZY
import jakarta.persistence.ManyToOne

@Entity
class MemberLog(
    @field:ManyToOne(fetch = LAZY) val actor: Member,
    @field:ManyToOne(fetch = LAZY) var owner: Member,
    val primaryEntityType: String,
    val primaryEntityId: Int,
    val secondaryEntityType: String,
    val secondaryEntityId: Int,
    @Column(columnDefinition = "JSON")
    val content: String,
) : BaseTime() {

}