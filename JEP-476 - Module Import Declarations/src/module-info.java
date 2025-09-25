module M1 {
	exports p1;
	exports p2 to M0;
	exports p3 to M3;

	requires transitive M4;
	requires M5;
}

module M3 { ... }

module M4 { exports p10; }

module M5 { exports p11; }
