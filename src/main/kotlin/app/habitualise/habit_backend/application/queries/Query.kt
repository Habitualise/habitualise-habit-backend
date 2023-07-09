package app.habitualise.habit_backend.application.queries

import an.awesome.pipelinr.Command

interface Query<T> : Command<Result<T>>
