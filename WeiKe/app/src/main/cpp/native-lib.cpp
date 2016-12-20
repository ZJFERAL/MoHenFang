#include <jni.h>
#include <string>


extern "C"
JNIEXPORT jstring JNICALL
Java_com_zjf_weike_util_NativeUtil_getKey(JNIEnv *env, jclass type) {

    std::string key = "5858e7502ae85b11940010b2";

    return env->NewStringUTF(key.c_str());
}


