#include <jni.h>
#include <string>
#include "logutils.cpp"
extern "C" {
JNIEXPORT void JNICALL
Java_com_example_cloud_atlasdemo_liveplayer_nativeinterf_PushNative_startPush(JNIEnv *env,
                                                                              jobject instance,
                                                                              jstring url_) {
    const char *url = env->GetStringUTFChars(url_, 0);
    LOGI("startPush")

    env->ReleaseStringUTFChars(url_, url);
}

JNIEXPORT void JNICALL
Java_com_example_cloud_atlasdemo_liveplayer_nativeinterf_PushNative_stopPush(JNIEnv *env,
                                                                             jobject instance) {

    LOGI("stopPush")

}

JNIEXPORT void JNICALL
Java_com_example_cloud_atlasdemo_liveplayer_nativeinterf_PushNative_release(JNIEnv *env,
                                                                            jobject instance) {

    LOGI("release")

}

JNIEXPORT void JNICALL
Java_com_example_cloud_atlasdemo_liveplayer_nativeinterf_PushNative_setVideoOptions(JNIEnv *env,
                                                                                    jobject instance,
                                                                                    jint width,
                                                                                    jint height,
                                                                                    jint bitrate,
                                                                                    jint fps) {

    LOGI("setVideoOptions")

}

JNIEXPORT void JNICALL
Java_com_example_cloud_atlasdemo_liveplayer_nativeinterf_PushNative_setAudioOptions(JNIEnv *env,
                                                                                    jobject instance,
                                                                                    jint sampleRateInHz,
                                                                                    jint channel) {

    LOGI("setAudioOptions")

}

JNIEXPORT void JNICALL
Java_com_example_cloud_atlasdemo_liveplayer_nativeinterf_PushNative_fireVideo(JNIEnv *env,
                                                                              jobject instance,
                                                                              jbyteArray data_) {
    jbyte *data = env->GetByteArrayElements(data_, NULL);

    LOGI("fireVideo")

    env->ReleaseByteArrayElements(data_, data, 0);
}

JNIEXPORT void JNICALL
Java_com_example_cloud_atlasdemo_liveplayer_nativeinterf_PushNative_fireAudio(JNIEnv *env,
                                                                              jobject instance,
                                                                              jbyteArray data_,
                                                                              jint len) {
    jbyte *data = env->GetByteArrayElements(data_, NULL);

    LOGI("fireAudio")

    env->ReleaseByteArrayElements(data_, data, 0);
}


JNIEXPORT jstring JNICALL
Java_com_example_cloud_atlasdemo_liveplayer_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
}
