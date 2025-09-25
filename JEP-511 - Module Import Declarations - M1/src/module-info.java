module M1 {
	exports net.ptidej.newjava.moduleimports.p1;
	exports net.ptidej.newjava.moduleimports.p2 to M0;
	exports net.ptidej.newjava.moduleimports.p3 to M3;

	requires transitive M4;
	requires M5;
}