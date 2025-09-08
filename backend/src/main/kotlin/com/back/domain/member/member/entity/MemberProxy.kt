package com.back.domain.member.member.entity

import java.time.LocalDateTime

class MemberProxy(
    id: Int,
    username: String,
    nickname: String,
    private val memer: Member
) : Member(id, username, nickname) {
    override var nickname: String
        get() = super.nickname
        set(value) {
            super.nickname = value
            memer.nickname = value
        }

    override var createDate: LocalDateTime
        get() = memer.createDate
        set(value) {
            memer.createDate = value
        }

    override var modifyDate: LocalDateTime
        get() = memer.modifyDate
        set(value) {
            memer.modifyDate = value
        }

    override var profileImgUrl: String?
        get() = memer.profileImgUrl
        set(value) {
            memer.profileImgUrl = value
        }

    override val profileImgUrlOrDefault: String
        get() = memer.profileImgUrlOrDefault
}
