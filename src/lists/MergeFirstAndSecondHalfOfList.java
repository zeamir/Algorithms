package lists;

import static lists.ListUtils.*;

// given list A1,A2,...,An,B1,B2,...,Bn
// create list A1,B1,A2,B2,...,An,Bn
// took me 18 minutes to write. without any  bug :-)
public class MergeFirstAndSecondHalfOfList {

	public static void main(String[] args) {
		Node head = buildList(1, 2, 11, 12);
		printList(head);
		Node newHead = reorderList(head);
		printList(newHead);
	}

	private static Node reorderList(Node head) {
		Node currLast = null;
		Node currB = getMiddleNode(head);
		Node currA = head;
		Node nextA;
		while (currB != null) {
			nextA = currA.next;
			currA.next = currB;
			if (currLast != null) {
				currLast.next = currA;
			}
			currLast = currB;
			currA = nextA;
			currB = currB.next;
		}
		return head;

	}
	private static Node getMiddleNode(Node head) {
		Node backward = head;
		Node forward = head;
		while (forward != null) {
			forward = forward.next.next;
			backward = backward.next;
		}
		return backward;
	}

}
