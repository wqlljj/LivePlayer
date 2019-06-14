# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html
cmake_minimum_required(VERSION 3.4.1)

#将静态库的头文件添加进来
include_directories(
src/main/cpp/x264/include
#头文件多的话可以继续往下加
)

add_library(
             pushnative

             SHARED

             src/main/cpp/pushnative.cpp )
#添加自己所需的静态库 库多的话，可以使用下面的方式添加
add_library( x264
            STATIC
            IMPORTED
            )
set_target_properties(
              x264
              PROPERTIES IMPORTED_LOCATION
              ${CMAKE_SOURCE_DIR}/src/main/cpp/x264/libx264.a
              )
find_library(
              log-lib
              log )

#add_library( 库名
#           SHARED
#            IMPORTED
#           )
#set_target_properties(
#                    库名
#                    PROPERTIES IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/src/main/jniLibs/${ANDROID_ABI}/lib库名.so
#                    )


target_link_libraries( # Specifies the target library.
                       pushnative
                       x264


                       ${log-lib} )