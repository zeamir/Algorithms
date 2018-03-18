package lists;

//You are given a doubly linked list. Each node contains a char. A space indicates an end of a word.
// for example: H->E->L->L->O->  ->W->O->R->L->D
//
// Reverse the order of the letters in all words. Output should be:
//
// O->L->L->E->H->  ->D->L->R->O->W.
//
// Note: there may be more than one consecutive space. The sentence may begin with space.
public class ReverseWordsInList {

	public static void main(String[] args) {
		Node head1 = buildList("Hello World");
//		Node head2 = buildList("ABC DEF G H");
//		Node head3 = buildList("    ");

		Node head = head1;


		System.out.println("original list...");
		printList(head);

		System.out.println("reverse it...");
		//Node reversed = reverseWords(head);
		Node reversed = reverseWordsTechniqueB(head);
		printList(reversed);

		System.out.println("reverse it again...");
		//reversed = reverseWords(reversed);
		reversed = reverseWordsTechniqueB(reversed);
		printList(reversed);

	}
	private static Node reverseWordsTechniqueB(final Node head) {
		Node lastEntered = null;
		Node last = null;
		Node curr = head;
		Node next;

		Node retHead = calcNewHead(head);
		while (curr != null) {

			next = curr.next;
			if (lastEntered == null) {
				// first element entered. simply add it
				lastEntered = curr;
				last = curr;
				continue;
			}

			if (curr.letter == ' ') {
				// last entered is space
				// add as last element
				last.next = curr;
				curr.prev = last;
				// flag it as the new lastEntered
				lastEntered = curr;
				// flag it as lastNode
				last = curr;
			} else {
				// add a letter
				if (lastEntered.letter != ' ') {
					// when adding a letter after a letter, it needs to be added BEFORE that letter.
					// the new letter also has to be connected to letter before the letter it was connected to
					if (lastEntered.prev != null) {//maybe we need to save the last space entered
						lastEntered.prev.next = curr;
						curr.prev = lastEntered.prev;
					}
					lastEntered.prev = curr;
					curr.next = lastEntered;
					lastEntered = curr;
					// last node does not change
				} else {
					// when adding a letter after a space, the letter need to be added AFTER the space
					lastEntered.next = curr;
					curr.prev = lastEntered;
					lastEntered = curr;
					last = curr;
				}
			}
			curr = next;
		}
		if (last != null) {
			last.next = null;
		}
		if (retHead != null) {
			retHead.prev = null;
		}
		return retHead;
	}

	private static Node calcNewHead(Node head) {
		// if head is null return head
		Node ret;
		if (head == null || head.letter == ' ') {
			ret = head;
		} else {
			// find the first space. if there is such, then the new head is the node before that.
			// if not found the new head is the last element
			Node curr = head;
			while (curr.next != null && curr.next.letter != ' ') {
				curr = curr.next;
			}
			ret = curr;
		}
		return ret;
	}


	private static Node reverseWords(Node head) {

		if (head == null || head.next == null) {
			return head;
		}
		Node ret = head;
		Node curr = head;
		while (curr != null) {
			Node nodeStartingNextWord = findNodeStartingNextWord(curr);
			// start - missed that during solution on paper
			if (nodeStartingNextWord == null) {
				break;
			}
			Node lastNodeBeforeRevese = nodeStartingNextWord.prev;
			// end - missed that during solution on paper

			CharList reversed = reverse(nodeStartingNextWord);
			// fixing the head
			if (reversed.tail == head) {
				ret = reversed.head;
			}

			// start - missed that during solution on paper
			if (lastNodeBeforeRevese != null) {
				lastNodeBeforeRevese.next = reversed.head;
			}
			reversed.head.prev = lastNodeBeforeRevese;
			// end - missed that during solution on paper

			//curr = reversed.tail; // this is what I wrote in the original solution on paper
			curr = reversed.tail.next;
			if (curr != null) {
				curr.prev = reversed.tail;
			}

		}

		// did not cover in the original solution on paper: need to  continue from the char after the tail of the revesed list;
		return ret;

	}

	private static Node findNodeStartingNextWord(Node n) {
		while (n != null && n.letter == ' ') {
			n = n.next;
		}
		return n;
	}

	private static void printList(Node n) {
		Node curr = n;
		Node tail = null;
		StringBuilder sb = new StringBuilder();
		sb.append("\nprinting list forward: '");
		while (curr != null) {
			sb.append(curr.letter);
			tail = curr;
			curr = curr.next;
		}
		sb.append("'");
		sb.append("\nprinting list backwards: '");
		Node backwardCurr = tail;
		while (backwardCurr != null) {
			sb.append(backwardCurr.letter);
			backwardCurr = backwardCurr.prev;
		}
		sb.append("'");

		System.out.println(sb.toString());
	}

	private static Node buildList(String text) {
		Node ret = null;
		Node prev = null;
		for (int i = 0; i < text.length(); i++) {
			Node curr = new Node(text.charAt(i));
			if (i == 0) {
				ret = curr;
			}
			curr.prev = prev;
			if (prev != null) {
				prev.next = curr;
			}
			prev = curr;
		}
		return ret;
	}

	private static CharList reverse(Node n) {
		Node lastCharNode = null;
		Node prev = null;
		Node next;
		Node curr = n;
		while (curr != null && curr.letter != ' ') {
			lastCharNode = curr;
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr.prev = next;
			curr = next;
		}
		// now connect the last letter in the reversed word to the next letter
		n.next = curr;

		return new CharList(lastCharNode, n);
	}

	static class Node {
		char letter;
		Node prev;
		Node next;

		Node(char c) {
			letter = c;
		}
	}

	static class CharList {
		Node head;
		Node tail;

		CharList(Node head, Node tail) {
			this.head = head;
			this.tail = tail;

		}
	}
}
