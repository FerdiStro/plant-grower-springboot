echo "Deploy PLANT-stack..."
docker stack deploy -c docker-compose.yml PLANT
watch docker service ls