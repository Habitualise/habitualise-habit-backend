package app.habitualise.habit_backend.application.commands

import an.awesome.pipelinr.Command

interface Command : Command<Result<Unit>>

interface GenericCommand<T> : Command<Result<T>>
