package net.ptidej.newjava.sealedclasses;

sealed class Person permits Employee, Student, Instructor {
}

final class Employee extends Person {
}

sealed class Student extends Person permits StudentBacc, StudentMaster, StudentPhD {
}

final class StudentBacc extends Student {
}

final class StudentMaster extends Student {
}

final class StudentPhD extends Student {
}

non-sealed class Instructor extends Person {
}

class LTA extends Instructor {
}

class ETA extends Instructor {
}

class Prof extends Instructor {
}

public class Example1 {
	public static void main(final String[] args) {
	}
}
