package ru.croc.task6.core;

import org.jetbrains.annotations.NotNull;
import ru.croc.task6.core.abstractions.CommentRemover;

public class BaseCommentRemover implements CommentRemover {
	private final String singleLineCommentPattern, multiLineCommentPattern;

	protected BaseCommentRemover(String singleLineCommentPattern, String multiLineCommentPattern) {
		this.singleLineCommentPattern = singleLineCommentPattern;
		this.multiLineCommentPattern = multiLineCommentPattern;
	}

	public String removeComments(@NotNull String source) {
		return source
			.replaceAll(multiLineCommentPattern, "")
			.replaceAll(singleLineCommentPattern, "");
	}
}
