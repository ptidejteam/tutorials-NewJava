package net.ptidej.newjava.vectorapi;

public class Example1 {
	static final VectorSpecies<Float> SPECIES = FloatVector.SPECIES_PREFERRED;

	void vectorComputation(float[] a, float[] b, float[] c) {
		int i = 0;
		int upperBound = SPECIES.loopBound(a.length);
		for (; i < upperBound; i += SPECIES.length()) {
			// FloatVector va, vb, vc;
			var va = FloatVector.fromArray(SPECIES, a, i);
			var vb = FloatVector.fromArray(SPECIES, b, i);
			var vc = va.mul(va).add(vb.mul(vb)).neg();
			vc.intoArray(c, i);
		}
		for (; i < a.length; i++) {
			c[i] = (a[i] * a[i] + b[i] * b[i]) * -1.0f;
		}
	}

	public static void main(final String[] args) {
	}
}
