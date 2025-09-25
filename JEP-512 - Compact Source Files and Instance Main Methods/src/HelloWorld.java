void main() {
	final String name = IO.readln("Please enter your name: ");
	IO.print("Pleased to meet you, ");
	IO.println(name);

	final List<String> characters = List.of("Rick Deckard", "Roy Batty", "Pris",
			"Zhora", "Gaff", "Eldon Tyrell", "J.F. Sebastian", "Leon Kowalski");
	for (var c : characters) {
		IO.println(c + ": " + c.length());
	}
}