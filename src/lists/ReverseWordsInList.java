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
		Node reversed = reverseWords(head);
		printList(reversed);

		System.out.println("reverse it again...");
		reversed = reverseWords(reversed);
		printList(reversed);

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
