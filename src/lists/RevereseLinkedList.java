package lists;

import static lists.ListUtils.*;

public class RevereseLinkedList {

	public static void main(String[] args) {
		Node node = buildList(1, 100);
		printList(node);
		Node newHead = iterativeReverse(node);
		printList(newHead);

		Node node2 = buildList(1, 100);
		printList(node2);
		printList(recursiveReverse(node2).head);

		Node node3 = buildList(1, 100);
		printList(node3);
		printList(recursiveReverseUsingCurrAndPrev(node3, null));

	}

	// this approach is similar to the tail recursive method provided by GeeksForGeeks
	private static  Node recursiveReverseUsingCurrAndPrev(Node curr, Node prev) {
		if (curr == null) {
			return null;
		}
		Node ret = null;
		if (curr.next == null) {
			// we got to the last element in the original list
			ret = curr;
		}
		// save next element for recursive call
		Node next = curr.next;
		// this is the reverse action since curr should point to prev
		curr.next = prev;
		// as long as we did not find the head, we should reverse the reminder of the list
		if (ret == null) {
			ret = recursiveReverseUsingCurrAndPrev(next, curr);
		}
		return ret;
	}

	private static HTList recursiveReverse(Node node) {
		if (node == null) {
			return null;
		}
		if (node.next == null) {
			HTList htList = new HTList();
			htList.head = node;
			htList.tail = node;
			return htList;
		}

		HTList htList = recursiveReverse(node.next);
		// 1. connect node to the list as its last element
		htList.tail.next = node;
		// 2. set the node as the new tail
		htList.tail = node;
		// 3. the new tail should point to null
		node.next = null;
		return htList;
	}

	private static class HTList {
		Node head;
		Node tail;
	}

	private static Node iterativeReverse(Node head) {
		if (head == null) {
			return null;
		}
		Node curr = head;
		Node prev = null;
		while (curr != null) {
			Node next = curr.next;
			curr.next = prev;
			prev = curr; // prev becomes curr node so that the next node will point to it
			curr = next;
		}
		return prev;
	}



}


