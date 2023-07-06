echo Deploy PLANT-stack...
cd db
docker stack deploy -c docker-compose.yml PLANT
cd ..

