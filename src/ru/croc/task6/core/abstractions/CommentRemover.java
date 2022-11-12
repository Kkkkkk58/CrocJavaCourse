package ru.croc.task6.core.abstractions;

import org.jetbrains.annotations.NotNull;

public interface CommentRemover {
	String removeComments(@NotNull String source);
}
