package com.sid.domain.usecase.message

import com.aurora.data.data.entity.MessageEntity
import com.sid.domain.repository.message.MessageRepository
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetMessagesForSessionUseCaseTest {

    @Mock
    private lateinit var mockMessageRepository: MessageRepository

    private lateinit var getMessagesForSessionUseCase: GetMessagesForSessionUseCase

    @Before
    fun setUp() {
        getMessagesForSessionUseCase = GetMessagesForSessionUseCase(mockMessageRepository)
    }

    @Test
    fun `testGetMessagesForSession returns messages from repository`() = runBlocking {
        // Arrange
        val sessionId = 123L
        val expectedMessages = listOf(
            MessageEntity(id = 1L, sessionId = sessionId, sender = "user", timestamp = 1000L, content = "Hello"),
            MessageEntity(id = 2L, sessionId = sessionId, sender = "bot", timestamp = 1001L, content = "Hi there")
        )
        `when`(mockMessageRepository.getMessagesForSession(sessionId)).thenReturn(flowOf(expectedMessages))

        // Act
        val resultFlow = getMessagesForSessionUseCase(sessionId)
        val actualMessages = resultFlow.firstOrNull()

        // Assert
        assertEquals(expectedMessages, actualMessages)
        verify(mockMessageRepository).getMessagesForSession(sessionId)
    }
}
