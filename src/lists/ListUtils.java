package lists;

public class ListUtils {
	static void printList(Node n) {
		Node curr = n;
		StringBuilder sb = new StringBuilder();
		sb.append("\nprinting list : ");
		while (curr != null) {
			sb.append(curr.value).append("->");
			curr = curr.next;
		}
		sb.append("NULL");
		System.out.println(sb.toString());
	}

	static Node buildList(int... values) {

		Node curr = null;
		Node prev = null;
		Node ret = null;
		for (int v : values) {
			curr = new Node(v);
			if (prev == null) {
				ret = curr;
			} else {
				prev.next = curr;
			}
			prev = curr;
		}
		if (curr != null) {
			curr.next = null;
		}
		return ret;
	}

	static Node buildList(int from, int to) {
		int[] values = new int[to + 1 - from];
		for (int i = from; i <= to; i++) {
			values[i-from] = i;
		}
		return buildList(values);
	}


	static class Node {
		int value;
		Node next;

		Node(int value) {
			this.value = value;
		}
	}

}
