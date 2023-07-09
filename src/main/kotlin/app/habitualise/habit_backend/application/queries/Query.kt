package app.habitualise.habit_backend.application.queries

import an.awesome.pipelinr.Command

abstract class Query<T> : Command<Result<T>>
