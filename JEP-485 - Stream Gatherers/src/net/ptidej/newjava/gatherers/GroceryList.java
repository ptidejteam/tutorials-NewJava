package net.ptidej.newjava.gatherers;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GroceryList {

	private static final record Transaction(Type type, int id, int value) {
		enum Type {
			GROCERY, ENTERTAINMENT;
		}

		public Type getType() {
			return this.type;
		}

		public int getValue() {
			return this.value;
		}

		public int getId() {
			return this.id;
		}
	}

	public static void main(final String[] args) {
		final List<Transaction> transactions = List.of(
				new Transaction(Transaction.Type.ENTERTAINMENT, 1, 100),
				new Transaction(Transaction.Type.GROCERY, 3, 80),
				new Transaction(Transaction.Type.GROCERY, 6, 120),
				new Transaction(Transaction.Type.ENTERTAINMENT, 7, 40),
				new Transaction(Transaction.Type.GROCERY, 10, 50));

		final List<Integer> transactionsIds = transactions.parallelStream()
				.filter(t -> t.getType() == Transaction.Type.GROCERY)
				.sorted(Comparator.comparing(Transaction::getValue).reversed())
				.map(Transaction::getId).collect(Collectors.toList());

		System.out.println(transactionsIds);
	}

}
