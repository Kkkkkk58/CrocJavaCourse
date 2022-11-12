package ru.croc.task6.core;

public class JavaCommentRemover extends BaseCommentRemover {
	public JavaCommentRemover() {
		super("//.*", "/\\*[\\s\\S]*?\\*/");
	}
}
