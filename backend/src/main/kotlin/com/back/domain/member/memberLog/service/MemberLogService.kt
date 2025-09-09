package com.back.domain.member.memberLog.service

import com.back.domain.member.member.entity.Member
import com.back.domain.member.memberLog.entity.MemberLog
import com.back.domain.member.memberLog.repository.MemberLogRepository
import com.back.domain.post.post.event.PostCommentWrittenEvent
import com.back.standard.util.Ut
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberLogService(
    private val memberLogRepository: MemberLogRepository,
) {
    @Transactional
    fun logWhenPostCommentWritten(event: PostCommentWrittenEvent) = memberLogRepository.save(
        MemberLog(
            Member(event.actorDto.id),
            Member(event.ownerDto.id),
            event.primaryEntityType,
            event.primaryEntityId,
            event.secondaryEntityType,
            event.secondaryEntityId,
            Ut.json.toString(event, "")
        )
    )
}
