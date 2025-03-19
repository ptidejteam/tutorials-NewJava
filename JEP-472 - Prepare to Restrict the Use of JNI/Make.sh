#!/bin/bash
gcc -Wl,--add-stdcall-alias -shared -I"${JAVA_HOME}"/include -I"${JAVA_HOME}"/include/win32 -o net_ptidej_newjava_restrictJNI_Main.dll net_ptidej_newjava_restrictJNI_Main.cpp
gcc -Wl,--add-stdcall-alias -shared -I"${JAVA_HOME}"/include -I"${JAVA_HOME}"/include/win32 -o net_ptidej_newjava_restrictJNI_A.dll net_ptidej_newjava_restrictJNI_A.cpp
