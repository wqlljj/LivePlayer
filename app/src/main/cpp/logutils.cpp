//
// Created by cloud on 2019/4/9.
//
#include <android/log.h>
#include <jni.h>

#define LOG_TAG "JniLog"

#define LOGI(FORMAT, ...) __android_log_print(ANDROID_LOG_INFO,LOG_TAG,FORMAT,##__VA_ARGS__);
#define LOGE(FORMAT, ...) __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,FORMAT,##__VA_ARGS__);
#define LOGD(FORMAT, ...) __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG,FORMAT,##__VA_ARGS__);
#define LOGW(FORMAT, ...) __android_log_print(ANDROID_LOG_WARN,LOG_TAG,FORMAT,##__VA_ARGS__);
