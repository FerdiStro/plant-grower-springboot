echo "Build Project"

cd ..

./gradlew clean build -x test

echo "Build Docker-Image"
docker build -t plant-grower-springboot .

