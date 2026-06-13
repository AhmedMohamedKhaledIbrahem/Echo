package com.echo.feature.topic.domain.usecase

import com.echo.core.error.DomainError
import com.echo.core.error.Resource
import com.echo.core.model.Topic
import com.echo.feature.topic.domain.repoistory.TopicRepository
import javax.inject.Inject

class MatchTopicsUseCase @Inject constructor(
    private val repository: TopicRepository
) {
    suspend operator fun invoke(query: String): Resource<List<Topic>, DomainError> {
        return repository.matchTopics(query)
    }
}
