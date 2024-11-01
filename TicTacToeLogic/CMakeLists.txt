cmake_minimum_required(VERSION 3.29)
project(TicTacToeLogic)

set(CMAKE_CXX_STANDARD 20)
set(JAVA_HOME "/Users/tuxqeq/Library/Java/JavaVirtualMachines/openjdk-22.0.1/Contents/Home")
include_directories(${JAVA_HOME}/include)
include_directories(${JAVA_HOME}/include/darwin)

add_library(TicTacToeLogic SHARED library.cpp)
