package net.ptidej.newjava.quantumresistance;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.NamedParameterSpec;

public class Main {

	public static void main(final String[] args)
			throws NoSuchAlgorithmException,
			InvalidAlgorithmParameterException {

		final KeyPairGenerator kpgKEM = KeyPairGenerator.getInstance("ML-KEM");
		kpgKEM.initialize(NamedParameterSpec.ML_KEM_512);
		final KeyPair kpKEM = kpgKEM.generateKeyPair(); // an ML-KEM-512 key pair
		System.out.println(kpKEM.getPrivate());
		System.out.println(kpKEM.getPublic());

		final KeyPairGenerator kpgDSA = KeyPairGenerator.getInstance("ML-DSA");
		kpgDSA.initialize(NamedParameterSpec.ML_DSA_44);
		final KeyPair kpDSA = kpgDSA.generateKeyPair(); // an ML-DSA-44 key pair
		System.out.println(kpDSA.getPrivate());
		System.out.println(kpDSA.getPublic());
	}

}
