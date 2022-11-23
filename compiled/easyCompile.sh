cd $(dirname $0)
javac -d ./ ../src/*.java
jar cf cards.jar *.class
jar uf cards.jar ../src/*.java
read -p "Press any key to exit."
