package net.ptidej.newjava.classfileapi;

import java.io.IOException;
import java.lang.classfile.AccessFlags;
import java.lang.classfile.ClassFile;
import java.lang.classfile.Label;
import java.lang.constant.ClassDesc;
import java.lang.constant.MethodTypeDesc;
import java.lang.reflect.AccessFlag;
import java.nio.file.Path;

public class Example1 {
	private static final ClassDesc CD_boolean = ClassDesc.of("boolean");
	private static final ClassDesc CD_void = ClassDesc.of("void");
	private static final ClassDesc CD_int = ClassDesc.of("int");

	public static void main(final String[] args) throws IOException {
		ClassFile.of().buildTo(Path.of("A.class"), ClassDesc.of("A"),
				classBuilder -> classBuilder.withMethod("fooBar",
						MethodTypeDesc.of(CD_void, CD_boolean, CD_int),
						AccessFlags.ofMethod(AccessFlag.PUBLIC).flagsMask(),
						methodBuilder -> methodBuilder.withCode(codeBuilder -> {
							final Label label1 = codeBuilder.newLabel();
							final Label label2 = codeBuilder.newLabel();
							codeBuilder.iload(1).ifeq(label1).aload(0).iload(2)
									.invokevirtual(ClassDesc.of("Foo"), "foo",
											MethodTypeDesc.of(CD_void, CD_int))
									.goto_(label2).labelBinding(label1).aload(0)
									.iload(2)
									.invokevirtual(ClassDesc.of("Foo"), "bar",
											MethodTypeDesc.of(CD_void, CD_int))
									.labelBinding(label2).return_();
						})));
	}
}
