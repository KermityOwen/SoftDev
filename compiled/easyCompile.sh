cd $(dirname $0)
javac -d ./ ../src/*.java
jar cf cards.jar *.class
read -p "Press any key to exit."
