#!/bin/bash
gcc -W -shared -I"${JAVA_HOME}"/include -I"${JAVA_HOME}"/include/linux -o libnet_ptidej_newjava_restrictJNI_A.so net_ptidej_newjava_restrictJNI_A.cpp
