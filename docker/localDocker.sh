echo "Starting local DB"
cd db/
docker stack deploy -c docker-compose.yml LOCAL