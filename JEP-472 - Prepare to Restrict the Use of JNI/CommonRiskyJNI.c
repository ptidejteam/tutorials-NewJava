void Java_pkg_C_setPointerToThree__J(jlong ptr) {
    *(int*)ptr = 3;
}

return (*env)->NewDirectByteBuffer(env, 0, 10);

jclass clazz = (*env)->FindClass(env, "java/lang/String");
jfieldID fid = (*env)->GetFieldID(env, clazz , "value", "[B");
jbyteArray contents = (jbyteArray)(*env)->GetObjectField(env, str, fid);
jbyte b = 0;
(*env)->SetByteArrayRegion(env, contents, 0, 1, &b);

jbyte *a = (*env)->GetPrimitiveArrayCritical(env, arr, 0);
a[500] = 3; // May be out of bounds
(*env)->ReleasePrimitiveArrayCritical(env, arr, a, 0);


java --enable-native-access=ALL-UNNAMED
JDK_JAVA_OPTIONS
Enable-Native-Access: ALL-UNNAMED
jlink --add-options="--enable-native-access=ALL-UNNAMED" ...
