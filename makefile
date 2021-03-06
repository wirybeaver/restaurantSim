# Java Makefile
# Reference: http://www.voidcn.com/article/p-hkdryezy-ob.html

# Project Structure
# Makefile
# README
# /src
#	/*.java
# /target
# 	/*.class
# data1.txt
# data2.txt

# set the entry of the java application
ENTRY_POINT = App

# set your source file name
SOURCE_FILES = \
enums/FoodEnum.java \
utils/Log.java \
utils/LogQueue.java \
utils/LogUtil.java \
entity/Machine.java \
entity/OrderNode.java \
entity/Table.java \
MachineQueue.java \
OrderQueue.java \
TableQueue.java \
Diner.java \
Cook.java \
App.java

# set file path related to the makefie location
TARGET = target
SOURCE = src

# set your java compiler:
JAVAC = javac

# set compiler option
ENCODING = -encoding UTF-8
JFLAGS = $(ENCODING)

vpath %.class $(TARGET)
vpath %.java $(SOURCE)

# show help message by default:
Default:
	@echo "make init: build the target directory."
	@echo "make build: compile the source file."
	@echo "make clean: clear classes generated."
	@echo "make rebuild: rebuild project."
	@echo "make run: run the application at the entry point."

build: $(SOURCE_FILES:.java=.class)

%.class: %.java
	$(JAVAC) -classpath $(TARGET) -d $(TARGET) $<

rebuild: clean build

.PHONY: init clean run

init:
	mkdir -pv $(TARGET)

clean:
	rm -frv $(TARGET)/*

run:
	java -classpath $(TARGET) $(ENTRY_POINT) $(FILE)
