// https://mccue.dev/pages/11-1-21-smuggling-checked-exceptions
// https://news.ycombinator.com/item?id=37451447

// https://docs.oracle.com/en/java/javase/17/language/sealed-classes-and-interfaces.html
// 	"Alternatively, you can define permitted subclasses in the same file as the sealed class. If you do so, then you can omit the permits clause:"
package net.ptidej.newjava.sealedclasses;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

record User(String name) {
}

class ExampleWithOptionalAndException {
	public static Optional<User> lookupUser(Connection db, int id)
			throws SQLException {

		var statement = db
				.prepareStatement("SELECT name FROM USER where id = ?");
		statement.setInt(1, id);
		var resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return Optional.of(new User(resultSet.getString(1)));
		}
		else {
			return Optional.empty();
		}
	}

	public static List<Optional<User>> lookupMultipleUsers(final Connection db,
			final List<Integer> ids) throws SQLException {

		final List<Optional<User>> users = new ArrayList<>();
		for (int id : ids) {
			users.add(ExampleWithOptionalAndException.lookupUser(db, id));
		}
		return users;
	}

	public static void main(final String[] aregs) {
		Connection db = null;
		try {
			ExampleWithOptionalAndException.lookupUser(db, 123).ifPresentOrElse(
					user -> System.out.println("FOUND USER: " + user),
					() -> System.out.println("NO SUCH USER"));
		}
		catch (final SQLException sqlException) {
			System.out.println("ERROR RUNNING QUERY: " + sqlException);
		}
	}
}

class ExampleWithUnionTypes1 {
	sealed interface UserLookupResult {
		record FoundUser(User user) implements UserLookupResult {
		}

		record NoSuchUser() implements UserLookupResult {
		}

		record ErrorRunningQuery(SQLException sqlException)
				implements UserLookupResult {
		}
	}

	public static UserLookupResult lookupUser(final Connection db,
			final int id) {

		try {
			var statement = db
					.prepareStatement("SELECT name FROM USER where id = ?");
			statement.setInt(1, id);
			var resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return new UserLookupResult.FoundUser(
						new User(resultSet.getString(1)));
			}
			else {
				return new UserLookupResult.NoSuchUser();
			}
		}
		catch (final SQLException e) {
			return new UserLookupResult.ErrorRunningQuery(e);
		}
	}

	public static List<UserLookupResult> lookupMultipleUsers(
			final Connection db, final List<Integer> ids) {

		return ids.stream().map(id -> ExampleWithUnionTypes1.lookupUser(db, id))
				.toList();
	}

	public static void main(final String[] args) {
		final Connection db = null;

		switch (ExampleWithUnionTypes1.lookupUser(db, 123)) {
		case UserLookupResult.FoundUser foundUser ->
			System.out.println("FOUND USER: " + foundUser.user());
		case UserLookupResult.NoSuchUser _ ->
			System.out.println("NO SUCH USER");
		case UserLookupResult.ErrorRunningQuery errorRunningQuery ->
			System.out.println(
					"ERROR RUNNING QUERY: " + errorRunningQuery.sqlException());
		}
	}
}

class ExampleWithUnionType2 {
	sealed interface Shape {
	}

	record Square(int x) implements Shape {
	}

	record Rectangle(int l, int w) implements Shape {
	}

	record Circle(int r) implements Shape {
	}

	double getArea(final Shape s) {
		// Exhaustively checks for all alternatives.
		return switch (s) {
		case Square(var x) -> x * x;
		case Rectangle(var l, var w) -> l * w;
		case Circle(var r) -> Math.PI * r * r;
		};
	}
}