package com.guang.bole;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ReverseStack {

	public static void main(String[] args) throws InterruptedException {
		Stack<String> stack = new Stack<>();
		stack.push("1");
		stack.push("2");
		stack.push("3");
		stack.push("4");
		reverseStack(stack);
		while (stack.size()>0) {
			System.out.println(stack.pop());
		}
	}
	
	private static <E> void  reverseStack(Stack<E> stack) throws InterruptedException {
		Queue<E> queue = new LinkedList<>();
		while (!stack.isEmpty()) {
			queue.offer(stack.pop());
		}
		while (!queue.isEmpty()) {
			stack.push(queue.poll());
		}
	}

}
