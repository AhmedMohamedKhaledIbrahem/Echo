package com.echo.feature.topic.domain.usecase

import com.echo.core.error.DomainError
import com.echo.core.error.Resource
import com.echo.core.model.Topic
import com.echo.feature.topic.domain.repoistory.TopicRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopicsUseCase @Inject constructor(
    private val repository: TopicRepository
) {
    operator fun invoke(): Resource<Flow<List<Topic>>, DomainError> {
        return repository.getTopics()
    }
}
