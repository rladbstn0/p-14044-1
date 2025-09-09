package com.back.domain.member.memberLog.eventListener

import com.back.domain.member.memberLog.service.MemberLogService
import com.back.domain.post.post.event.PostCommentWrittenEvent
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class MemberLogEventListener(
    private val memberLogService: MemberLogService
) {
    @Async
    @Transactional
    @EventListener
    fun handle(event: PostCommentWrittenEvent) {
        memberLogService.logWhenPostCommentWritten(event)
    }
}