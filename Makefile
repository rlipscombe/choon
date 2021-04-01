all: choon.jar

choon.jar: main.kt
	kotlinc main.kt -d choon.jar

run:
	java -jar choon.jar
